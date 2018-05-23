package com.linekong.rpc.net.netty.server;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.codec.rpc.RpcRequest;
import com.linekong.rpc.net.common.codec.rpc.RpcResponse;
import com.linekong.rpc.net.common.server.IServer;
import com.linekong.rpc.net.netty.codec.binary.PDUMessageDecoder;
import com.linekong.rpc.net.netty.codec.binary.PDUMessageEncoder;
import com.linekong.rpc.net.netty.codec.rpc.NettyRpcDecoder;
import com.linekong.rpc.net.netty.codec.rpc.NettyRpcEncoder;
import com.linekong.rpc.serialize.Serializer;
import com.linekong.rpc.utils.ProtocolUtils;
import com.linekong.rpc.utils.TimeUtils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer extends IServer{

	private Thread thread;
	@Override
	public void start(final int port, final Serializer serializer,final String protocol) {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				EventLoopGroup bossGroup = new NioEventLoopGroup();
				EventLoopGroup workGroup = new NioEventLoopGroup();
				try {
					ServerBootstrap bootstrap = new ServerBootstrap();
					if(protocol.equals(ProtocolUtils.RPC_PROTOCOL)) {
					bootstrap.group(bossGroup, workGroup)
							 .channel(NioServerSocketChannel.class)
							 .childHandler(new ChannelInitializer<SocketChannel>() {
								@Override
								protected void initChannel(SocketChannel ch) throws Exception {
									ch.pipeline().addLast(new NettyRpcDecoder(RpcRequest.class, serializer))
												 .addLast(new NettyRpcEncoder(RpcResponse.class, serializer))
												 .addLast(new RpcServerHandler());
								}
							 })
							 .option(ChannelOption.SO_TIMEOUT, 60000)
//							 .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 70000)
							 .option(ChannelOption.SO_BACKLOG, 1024)
							 .option(ChannelOption.TCP_NODELAY, true)
							 .option(ChannelOption.SO_REUSEADDR, true)
							 .childOption(ChannelOption.SO_KEEPALIVE, true);
					ChannelFuture future = bootstrap.bind(port).sync();
					LoggerUtils.info(NettyServer.class, "linekong-rpc tcp server start success...... ");
					future.channel().closeFuture().sync();
					}else if(protocol.equals(ProtocolUtils.BINARY_PROTOCOL)){
						bootstrap.group(bossGroup, workGroup)
						 .channel(NioServerSocketChannel.class)
						 .childHandler(new ChannelInitializer<SocketChannel>() {
							@Override
							protected void initChannel(SocketChannel ch) throws Exception {
								ch.pipeline().addLast(new PDUMessageDecoder())
											 .addLast(new PDUMessageEncoder())
											 .addLast(new BinaryServerHandler());
							}
						 })
						 .option(ChannelOption.SO_TIMEOUT, 60000)
						 .option(ChannelOption.SO_BACKLOG, 1024)
						 .option(ChannelOption.TCP_NODELAY, true)
						 .option(ChannelOption.SO_REUSEADDR, true)
						 .childOption(ChannelOption.SO_KEEPALIVE, true);
						ChannelFuture future = bootstrap.bind(port).sync();
						LoggerUtils.info(NettyServer.class, "linekong-binary server start success...... ");
						future.channel().closeFuture().sync();
					}else {
						LoggerUtils.error(NettyServer.class, "protocol error:"+protocol);
						return;
					}
				} catch (InterruptedException e) {
					LoggerUtils.error(NettyServer.class, "linekong-rpc tcp server start failure:"+e.getMessage());
					e.printStackTrace();
				}finally {
					bossGroup.shutdownGracefully();
					workGroup.shutdownGracefully();
				}
			}
		});
		thread.setDaemon(true); //设置为守护线程
		thread.start();//启动服务器
	}

	@Override
	public void destroy() {
		thread.interrupt();
		LoggerUtils.error(NettyServer.class, "currentTime:"+TimeUtils.getCurrentTime()+" linekong-rpc tcp server destroy success..");
	}

}
