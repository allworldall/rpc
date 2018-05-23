package com.linekong.rpc.net.netty.client;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.codec.rpc.RpcResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
		RpcCallbackFuture future = RpcCallbackFuture.futurePool.get(msg.getRequestId());
		future.setResponse(msg);
		RpcCallbackFuture.futurePool.put(msg.getRequestId(), future);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LoggerUtils.error(NettyClientHandler.class, "linekong-rpc netty client caught exception" + cause);
		ctx.close();
	}

}
