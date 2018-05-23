package com.linekong.rpc.net.netty.codec.rpc;

import com.linekong.rpc.serialize.Serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyRpcEncoder extends MessageToByteEncoder<Object>{

	private Class<?> genericClass;   //通用类
	
    private Serializer serializer;   //序列化
    
    public NettyRpcEncoder(Class<?> genericClass,Serializer serializer) {
    		this.genericClass = genericClass;
    		this.serializer = serializer;
    }
    /**
     * 进行编码
     */
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		if (genericClass.isInstance(msg)) {
            byte[] data = serializer.serialize(msg);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
	}

}
