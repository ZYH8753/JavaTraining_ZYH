package com.zyh.javatraining.weekthree.gateway.inbound;

import com.zyh.javatraining.weekthree.gateway.filter.HeaderHttpRequestFilter;
import com.zyh.javatraining.weekthree.gateway.filter.HttpRequestFilter;
import com.zyh.javatraining.weekthree.gateway.outbound.HttpOutboundHandler;
import com.zyh.javatraining.weekthree.gateway.outbound.NettyOutboundClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NettyInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger LOGGER = LoggerFactory.getLogger(NettyInboundHandler.class);

    private HttpOutboundHandler httpOutboundHandler;

    private HttpRequestFilter httpRequestFilter;

    private NettyOutboundClient nettyOutboundClient;

    public NettyInboundHandler(List<String> proxyServer) {
        httpOutboundHandler = new HttpOutboundHandler(proxyServer);
        httpRequestFilter = new HeaderHttpRequestFilter();
        nettyOutboundClient = new NettyOutboundClient(proxyServer);
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
//            logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            LOGGER.info("接收到的请求url为{}", uri);
//            httpOutboundHandler.handle(fullRequest, ctx, httpRequestFilter);
            nettyOutboundClient.handler(ctx);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
