package com.linekong.rpc.net.netty.client;

import org.apache.commons.pool2.impl.GenericObjectPool;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.client.IClient;
import com.linekong.rpc.net.common.codec.rpc.RpcRequest;
import com.linekong.rpc.net.common.codec.rpc.RpcResponse;

public class NettyClient extends IClient{

	/**
	 * 发送请求方法
	 */
	@Override
	public RpcResponse send(RpcRequest request) throws Exception {
		{
			// client pool	[tips03 : may save 35ms/100invoke if move it to constructor, but it is necessary. cause by ConcurrentHashMap.get]
	        GenericObjectPool<NettyClientPoolProxy> clientPool = NettyClientPool.getPool(serverAddress, request.getClassName(), serializer);
	        // client proxy
	        NettyClientPoolProxy clientPoolProxy = null;
			try {
				// future init	[tips04 : may save 20ms/100invoke if remove and wait for channel instead, but it is necessary. cause by ConcurrentHashMap.get]
				RpcCallbackFuture future = new RpcCallbackFuture(request);
				RpcCallbackFuture.futurePool.put(request.getRequestId(), future);
				// rpc invoke
				clientPoolProxy = clientPool.borrowObject();
				clientPoolProxy.send(request);
				// future get
				return future.get(timeoutMillis);
			} catch (Exception e) {
				LoggerUtils.error(NettyClient.class, e.getMessage());
				throw e;
			} finally{
				RpcCallbackFuture.futurePool.remove(request.getRequestId());
				clientPool.returnObject(clientPoolProxy);
			}
			
		}
	}

}
