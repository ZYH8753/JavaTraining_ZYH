package io.kimmking.rpcfx.demo.consumer;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.demo.consumer.netty.OperationResultFuture;
import io.kimmking.rpcfx.demo.consumer.netty.RequestPendingCenter;
import io.kimmking.rpcfx.demo.consumer.netty.ResponseDispatcherHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpVersion;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.URI;

@Component
public class NettyClient {
    private static final String URL = "localhost";
    private static final int PORT = 8081;

    private Bootstrap bootstrap;

    private EventLoopGroup group;

    private RequestPendingCenter requestPendingCenter;

    public NettyClient() {
        group = new NioEventLoopGroup();
        requestPendingCenter = new RequestPendingCenter();
        try {
            bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline()
                            .addLast(new HttpClientCodec())
                            .addLast(new HttpObjectAggregator(1024 * 1024))
                            .addLast(new HttpContentDecompressor())
                            .addLast(new ResponseDispatcherHandler(requestPendingCenter));
                }
            });

        } catch (Exception e) {

        } finally {
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            group.shutdownGracefully();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ChannelFuture connect() {
        ChannelFuture channelFuture = null;
        try {
            System.out.println("netty connect");
            channelFuture = bootstrap.connect(URL, PORT).sync();
        } catch (Exception e) {
            System.out.println("connect failed:" + e);
        }
        return channelFuture;
    }

    private ChannelFuture getChannelFuture() {
        return connect();
    }


    public RpcfxResponse getRpcfxResponse(RpcfxRequest req) {
        RpcfxResponse operationResult = null;
        int id = Integer.parseInt(req.getParams()[0].toString());
        System.out.println("nettyclient req(id=" + id +") json: " + req);
        try {
            OperationResultFuture operationResultFuture = new OperationResultFuture();

            requestPendingCenter.add(id, operationResultFuture);
            ChannelFuture cf = getChannelFuture();
            if (cf == null) {
                return operationResult;
            }
            cf.channel().writeAndFlush(getHttpRequest(req));
            operationResult = operationResultFuture.get();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("nettyclient res json: " + operationResult);
        return operationResult;
    }

    private FullHttpRequest getHttpRequest(RpcfxRequest req) {
        FullHttpRequest request = null;
        try {
            URI uri = new URI("/");
            request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, uri.toASCIIString(),
                    Unpooled.wrappedBuffer(JSON.toJSONString(req).getBytes("UTF-8")));
            request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }
}
