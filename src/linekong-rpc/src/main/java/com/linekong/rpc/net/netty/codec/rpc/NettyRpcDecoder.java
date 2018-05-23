package com.linekong.rpc.net.netty.codec.rpc;

import java.util.List;

import com.linekong.rpc.serialize.Serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class NettyRpcDecoder extends ByteToMessageDecoder {

	private Class<?> genericClass; // 通用类

	private Serializer serializer; // 序列化类

	public NettyRpcDecoder(Class<?> genericClass, Serializer serializer) {
		this.genericClass = genericClass;
		this.serializer = serializer;
	}
	/**
	 * 解码过程
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

		if (in.readableBytes() < 4) {
			return;
		}
		in.markReaderIndex();
		int dataLength = in.readInt();
		if (dataLength < 0) {
			ctx.close();
		}
		if (in.readableBytes() < dataLength) {
			in.resetReaderIndex();
			return;
		}
		byte[] data = new byte[dataLength];
		in.readBytes(data);
		Object obj = serializer.deserialize(data, genericClass);
		out.add(obj);
	}

}
