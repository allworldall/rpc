package com.linekong.rpc.net.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.client.IClient;
import com.linekong.rpc.net.common.codec.rpc.RpcRequest;
import com.linekong.rpc.net.common.codec.rpc.RpcResponse;
import com.linekong.rpc.net.netty.client.NettyClient;
import com.linekong.rpc.serialize.Serializer;
import com.linekong.rpc.utils.TimeUtils;

public class NetPrcClientProxy implements FactoryBean<Object>, InitializingBean {

	/**
	 * 客户端初始化配置
	 */
	private String serverAddress; // 服务端地址

	private Serializer serializer; // 初始化序列化方式

	private Class<?> iface; // rpc接口

	private long timeout = 5000; // 超时时间

	public IClient client = null;

	public NetPrcClientProxy(String serverAddress, Serializer serializer, Class<?> iface, long timeout) {
		this.setServerAddress(serverAddress);
		this.setSerializer(serializer);
		this.setIface(iface);
		this.setTimeout(timeout);
		LoggerUtils.info(NetPrcClientProxy.class, "begin invoke client init info:serverAddress=" + serverAddress
				+ ",serializer=protostuff,iface=" + iface + ",timeout=" + timeout);
		try {
			this.afterPropertiesSet();
		} catch (Exception e) {
			LoggerUtils.error(NetPrcClientProxy.class, e.getMessage());
		}
	}

	/**
	 * 初始化客户端
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		client = new NettyClient();
		client.init(serverAddress, serializer, timeout);
	}

	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { iface },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// request
						RpcRequest request = new RpcRequest();
						request.setRequestId(UUID.randomUUID().toString());
						request.setRequestTimeMillis(System.currentTimeMillis());
						request.setRequestTime(TimeUtils.getCurrentTime());
						request.setClassName(method.getDeclaringClass().getName());
						request.setMethodName(method.getName());
						request.setParameterTypes(method.getParameterTypes());
						request.setParameters(args);
						// send
						RpcResponse response = client.send(request);
						// valid response
						if (response == null) {
							LoggerUtils.error(NetPrcClientProxy.class, "linekong-rpc netty response not found.");
							throw new Exception("linekong-rpc netty response not found.");
						}
						if (response.isError()) {
							throw response.getError();
						} else {
							return response.getResult();
						}
					}
				});
	}

	@Override
	public Class<?> getObjectType() {
		return iface;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public Serializer getSerializer() {
		return serializer;
	}

	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	public Class<?> getIface() {
		return iface;
	}

	public void setIface(Class<?> iface) {
		this.iface = iface;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
