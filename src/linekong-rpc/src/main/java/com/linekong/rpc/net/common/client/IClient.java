package com.linekong.rpc.net.common.client;

import com.linekong.rpc.net.common.codec.rpc.RpcRequest;
import com.linekong.rpc.net.common.codec.rpc.RpcResponse;
import com.linekong.rpc.serialize.Serializer;

public abstract class IClient {
	/**
	 * 客户端初始化
	 */
	protected String serverAddress;      //服务暴露地址
	
	protected Serializer serializer;     //序列化方式
	
	protected long timeoutMillis;        //连接超时时间
	/**
	 * 初始化
	 */
	public void init(String serverAddress,Serializer serializer,long timeoutMillis) {
		this.serverAddress = serverAddress;
		this.serializer = serializer;
		this.timeoutMillis = timeoutMillis;
	}
	
	public abstract RpcResponse send(RpcRequest request) throws Exception;

}
