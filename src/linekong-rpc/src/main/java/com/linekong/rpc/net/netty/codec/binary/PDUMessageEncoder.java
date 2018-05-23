package com.linekong.rpc.net.netty.codec.binary;

import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import com.linekong.rpc.net.common.codec.binary.PDUMessageHeader;
import com.linekong.rpc.utils.ByteUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PDUMessageEncoder extends MessageToByteEncoder<PDUMessage>{

	@Override
	protected void encode(ChannelHandlerContext ctx, PDUMessage msg, ByteBuf out) throws Exception {
		//协议头
		PDUMessageHeader header = msg.getHeader();
		out.writeShort(header.getTotalLength());
		out.writeBytes(ByteUtils.short2Byte1(header.getVersion()));
		out.writeBytes(ByteUtils.short2Byte1(header.getRemainPackages()));
		out.writeInt((int)header.getCommandId());
		out.writeInt((int)header.getSequenceId());
		out.writeInt((int)header.getGameId());
		out.writeInt((int)header.getGatewayId());
		out.writeBytes(msg.getBody());
		out.writeShort(msg.getResv());
		out.writeShort(msg.getCheckSum());
		
	}

}
