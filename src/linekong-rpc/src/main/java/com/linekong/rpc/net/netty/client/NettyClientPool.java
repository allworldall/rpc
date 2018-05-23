package com.linekong.rpc.net.netty.client;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.pool2.impl.GenericObjectPool;

import com.linekong.rpc.register.ZKDiscoveryService;
import com.linekong.rpc.serialize.Serializer;

public class NettyClientPool {
	
	private GenericObjectPool<NettyClientPoolProxy> pool;
	
	private static ConcurrentHashMap<String, NettyClientPool> clientPoolMap = new ConcurrentHashMap<String, NettyClientPool>();
	
	public NettyClientPool(String host, int port, Serializer serializer) {
		pool = new GenericObjectPool<NettyClientPoolProxy>(new NettyClientPoolFactory(host, port, serializer));
		pool.setTestOnBorrow(true);
		pool.setMaxTotal(-1);
	}
	
	public GenericObjectPool<NettyClientPoolProxy> getPool(){
		return this.pool;
	}
	public static GenericObjectPool<NettyClientPoolProxy> getPool(String serverAddress, String className, Serializer serializer)
			throws Exception {
		// valid serverAddress
		if (serverAddress==null || serverAddress.trim().length()==0) {
			serverAddress = ZKDiscoveryService.discover(className);
		}
		if (serverAddress == null || serverAddress.trim().length() == 0) {
			throw new IllegalArgumentException("serverAddress is null");
		}
		// get from pool
		NettyClientPool clientPool = clientPoolMap.get(serverAddress);
		if (clientPool != null) {
			return clientPool.getPool();
		}
		// init pool
		String[] array = serverAddress.split(":");
		String host = array[0];
		int port = Integer.parseInt(array[1]);

		clientPool = new NettyClientPool(host, port, serializer);
		clientPoolMap.put(serverAddress, clientPool);
		return clientPool.getPool();
	}
	
}
