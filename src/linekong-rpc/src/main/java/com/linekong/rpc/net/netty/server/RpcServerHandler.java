package com.linekong.rpc.net.netty.server;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.NetRpcServerFactory;
import com.linekong.rpc.net.common.codec.rpc.RpcRequest;
import com.linekong.rpc.net.common.codec.rpc.RpcResponse;
import com.linekong.rpc.utils.TimeUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RpcServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		RpcRequest request = (RpcRequest)msg;
        RpcResponse response = NetRpcServerFactory.invokeService(request, null);
        ctx.writeAndFlush(response);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LoggerUtils.info(RpcServerHandler.class,"currentTime:"+TimeUtils.getCurrentTime()+" remote client connect:"+ctx.channel().remoteAddress().toString());
	}
	/**
	 * 当channel close 成功以后触发此方法
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		LoggerUtils.info(RpcServerHandler.class,"currentTime:"+TimeUtils.getCurrentTime()+" remote client disconnect:"+ctx.channel().remoteAddress().toString());
	}
	/**
	 * 连接发生异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LoggerUtils.error(RpcServerHandler.class, "currentTime:"+TimeUtils.getCurrentTime()+" linekong-rpc provider netty server caught exception"+cause);
		ctx.close();
	}
}
