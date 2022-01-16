package com.zyh.javatraining.weekthree.gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class NettyInboundInitializer extends ChannelInitializer<SocketChannel> {
	private List<String> proxyServer;

	public NettyInboundInitializer(List<String> proxyServer) {
		this.proxyServer = proxyServer;
	}

	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline p = ch.pipeline();
		p.addLast(new HttpServerCodec());
		//p.addLast(new HttpServerExpectContinueHandler());
		p.addLast(new HttpObjectAggregator(1024 * 1024));
		p.addLast(new NettyInboundHandler(proxyServer));
	}
}
