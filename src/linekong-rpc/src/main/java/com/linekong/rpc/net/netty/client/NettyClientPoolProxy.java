package com.linekong.rpc.net.netty.client;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.codec.rpc.RpcRequest;
import com.linekong.rpc.net.common.codec.rpc.RpcResponse;
import com.linekong.rpc.net.netty.codec.rpc.NettyRpcDecoder;
import com.linekong.rpc.net.netty.codec.rpc.NettyRpcEncoder;
import com.linekong.rpc.serialize.Serializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClientPoolProxy {

	private Channel channel;

	/**
	 * 创建代理
	 */
	public void createProxy(String host, int port, final Serializer serializer) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel channel) throws Exception {
				channel.pipeline().addLast(new NettyRpcEncoder(RpcRequest.class, serializer))
						.addLast(new NettyRpcDecoder(RpcResponse.class, serializer)).addLast(new NettyClientHandler());
			}
		}).option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_REUSEADDR, true)
				.option(ChannelOption.SO_KEEPALIVE, true);
		this.channel = bootstrap.connect(host, port).sync().channel();
	}

	public boolean isValidate() {
		if (this.channel != null) {
			return this.channel.isActive();
		}
		return false;
	}

	public void close() {
		if (this.channel != null) {
			if (this.channel.isOpen()) {
				this.channel.close();
			}
		}
		LoggerUtils.info(NettyClientPoolProxy.class, "netty channel close.");
	}
	public void send(RpcRequest request) throws Exception {
		this.channel.writeAndFlush(request).sync();
	}

}
