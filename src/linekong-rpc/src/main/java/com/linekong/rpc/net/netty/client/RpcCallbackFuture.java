package com.linekong.rpc.net.netty.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeoutException;

import com.linekong.rpc.net.common.codec.rpc.RpcRequest;
import com.linekong.rpc.net.common.codec.rpc.RpcResponse;

public class RpcCallbackFuture {
	
public static ConcurrentMap<String, RpcCallbackFuture> futurePool = new ConcurrentHashMap<String, RpcCallbackFuture>();	// 过期，失效
	
	// net codec
	private RpcRequest request;
	
	private RpcResponse response;
	// future lock
	private boolean isDone = false;
	
	private Object lock = new Object();
	
	public RpcCallbackFuture(RpcRequest request) {
		this.request = request;
		futurePool.put(request.getRequestId(), this);
	}
	
	public RpcResponse getResponse() {
		return response;
	}
	
	public void setResponse(RpcResponse response) {
		this.response = response;
		// notify future lock
		synchronized (lock) {
			isDone = true;
			lock.notifyAll();
		}
	}

	public RpcResponse get(long timeoutMillis) throws InterruptedException, TimeoutException{
		if (!isDone) {
			synchronized (lock) {
				try {
					lock.wait(timeoutMillis);
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
		if (!isDone) {
			throw new TimeoutException("linekong-rpc, netty request timeout at:"+System.currentTimeMillis()+", request:"+request.toString()+"");
		}
		return response;
	}

}
