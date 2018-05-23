package com.linekong.rpc.net.netty.server;

import org.apache.log4j.Logger;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.NetBinaryServerFactory;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import com.linekong.rpc.utils.TimeUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

public class BinaryServerHandler extends ChannelInboundHandlerAdapter{

	private static Logger log = Logger.getLogger(BinaryServerHandler.class);
	/**
	 * 网关绑定
	 */
	private static AttributeKey<String> SESSION_BIND = AttributeKey.valueOf("SESSION_BIND");

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		PDUMessage message = (PDUMessage) msg;
		if(ctx.channel().attr(SESSION_BIND).get() == null) {
			if(message.getHeader().getCommandId() == 0x10000001) {//进行网关绑定
				PDUMessage resMessage = NetBinaryServerFactory.invoke(message);
				ByteBuf by = Unpooled.buffer();
				by.writeBytes(resMessage.getBody());
				if(by.readInt() == 1) {
					ctx.channel().attr(SESSION_BIND).set(new String(resMessage.getBody()));
				}
				ctx.writeAndFlush(resMessage);
			}else {
				log.error("gateway not bind disconnection remote client "+ ctx.channel().remoteAddress().toString() + " conneticon msg:"+message.toString());
				ctx.close();//断开连接
			}
		}else {
			if(message.getHeader().getCommandId() == 0x10000002) { //网关解绑
				log.info("gateway unbind disconnection remote client "+ ctx.channel().remoteAddress().toString() + " conneticon msg:"+message.toString());
				PDUMessage resMessage = NetBinaryServerFactory.invoke(message);
				ByteBuf by = Unpooled.buffer();
				by.writeBytes(resMessage.getBody());
				if(by.readInt() == 1) {
					ctx.channel().attr(SESSION_BIND).remove(); //移除会话
					ctx.writeAndFlush(resMessage);
					ctx.close();//断开连接
				}else {
					ctx.writeAndFlush(resMessage);
				}
			}else {
				PDUMessage resMessage = NetBinaryServerFactory.invoke(message);
				ctx.writeAndFlush(resMessage);
			}
		}
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LoggerUtils.info(BinaryServerHandler.class,"currentTime:"+TimeUtils.getCurrentTime()+" remote client connect:"+ctx.channel().remoteAddress().toString());
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LoggerUtils.error(BinaryServerHandler.class, cause.getMessage());
		ctx.close();
	}
	 @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		 LoggerUtils.info(BinaryServerHandler.class,"remote client disconnect:"+ctx.channel().remoteAddress().toString());
	}
	
	

}
