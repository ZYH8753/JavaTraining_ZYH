package com.zyh.javatraining.weekthree.gateway.outbound;

import com.zyh.javatraining.weekthree.gateway.router.HttpEndpointRouter;
import com.zyh.javatraining.weekthree.gateway.router.RoundRibbonHttpEndpointRouter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.util.concurrent.Future;

import java.util.List;
import java.util.stream.Collectors;

public class NettyOutboundClient {
    HttpEndpointRouter router = new RoundRibbonHttpEndpointRouter();

    private List<String> backendUrls;

    public NettyOutboundClient(List<String> backends) {
        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    public void handler(ChannelHandlerContext inCtx) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new HttpClientCodec())
                        .addLast(new HttpObjectAggregator(1024 * 1024))
                        .addLast(new HttpContentDecompressor())
                        .addLast(new NettyOutboundHandler(inCtx));
            }
        });

        String url = router.route(backendUrls);
        System.out.println("NettyOutboundClient url " + url);
        String[] urls = url.split(":");

        ChannelFuture future = bootstrap.connect(urls[0], Integer.parseInt(urls[1])).sync();
        future.channel().closeFuture().sync();
    }
}
