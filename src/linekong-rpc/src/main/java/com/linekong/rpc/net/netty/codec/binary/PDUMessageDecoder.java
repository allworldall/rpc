package com.linekong.rpc.net.netty.codec.binary;

import java.util.List;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import com.linekong.rpc.net.common.codec.binary.PDUMessageHeader;
import com.linekong.rpc.utils.GlobalConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PDUMessageDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes() < GlobalConstant.MIN_READ_ABLE_BYTES) {
			LoggerUtils.error(PDUMessageDecoder.class, "readableBytes length < Min readableBytes 4");
			return;
		}
		//in.markReaderIndex();
		//读写消息头
		int totalLength = in.readUnsignedShort();
//		if(totalLength <= GlobalConstant.MIN_TOTAL_BYTES_LENGTH || totalLength > GlobalConstant.MAX_TOTAL_BYTES_LENGTH) {
//			LoggerUtils.error(PDUMessageDecoder.class, String.format("totalLength=%s,cause by totalLength <= %s or totalLength > %s", totalLength,
//					GlobalConstant.MIN_TOTAL_BYTES_LENGTH,GlobalConstant.MAX_TOTAL_BYTES_LENGTH));
//			ctx.close();
//			return;
//		}
		if (in.readableBytes() < (totalLength - 2)) {
			in.resetReaderIndex();
			return;
		}
		short version = in.readUnsignedByte();
		short remainPackages = in.readUnsignedByte();
		long commandId = in.readUnsignedInt();
		long sequenceId = in.readUnsignedInt();
		long gameId = in.readUnsignedInt();
		long gatewayId = in.readUnsignedInt();
		//组装消息头
		PDUMessageHeader header = new PDUMessageHeader(totalLength, version, remainPackages, commandId, sequenceId, gameId, gatewayId);
		//获取剩余消息体内容
		byte [] body = new byte[in.readableBytes() - 4];
		in.readBytes(body);
		short resv = in.readShort();
		int checkSum = in.readUnsignedShort();
		//读取消息体
		PDUMessage message = new PDUMessage(header, body, resv, checkSum);
		out.add(message);
	}
}
