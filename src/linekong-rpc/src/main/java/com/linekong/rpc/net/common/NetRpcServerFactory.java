package com.linekong.rpc.net.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.zookeeper.server.NettyServerCnxnFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.annotation.LKRpcService;
import com.linekong.rpc.net.common.codec.rpc.RpcRequest;
import com.linekong.rpc.net.common.codec.rpc.RpcResponse;
import com.linekong.rpc.net.common.server.IServer;
import com.linekong.rpc.net.netty.server.NettyServer;
import com.linekong.rpc.register.ZKRegisterService;
import com.linekong.rpc.serialize.Serializer;
import com.linekong.rpc.utils.ZKEnvirmentUtils;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

public class NetRpcServerFactory implements ApplicationContextAware, InitializingBean,DisposableBean {

	/**
	 * 服务器初始化配置
	 */
	private int port = 7080;           //初始化端口    8081
	
	private Serializer serializer;     //初始化序列化方式   ProtostuffSerializer
	
	private boolean zkSwitch = false;  //是否启用zookeeper注册中心 默认不启动    false
	
	private String protocol = "rpcProtocol";  //协议方式 二进制 binaryProtocol,rpc rpcProtocol
	
	private IServer server; 
	/**
	 * 初始化注册中心zookeeper地址以及zk会话超时时间
	 */
	public NetRpcServerFactory(int port,Serializer serializer,boolean zkSwitch,String zkAddress,int zkSessionTimeout) {
		this.setPort(port);
		this.setSerializer(serializer);
		this.setZkSwitch(zkSwitch);
		ZKEnvirmentUtils.ZK_ADDDRESS = zkAddress;
		ZKEnvirmentUtils.ZK_SESSION_TIMEOUT = zkSessionTimeout;
	}
	/**
	 * 初始化 rpc server map
	 */
	private static Map<String, Object> serviceMap = new ConcurrentHashMap<String, Object>();
	
	/**
	 * rpc调用返回
	 * @param RpcRequest request
	 * @param Object serviceBean
	 * @return RpcResponse
	 */
	public static RpcResponse invokeService(RpcRequest request, Object serviceBean) {
		if (serviceBean==null) {
			serviceBean = serviceMap.get(request.getClassName());
		}
		RpcResponse response = new RpcResponse();
		response.setRequestId(request.getRequestId());
		try {
			Class<?> serviceClass = serviceBean.getClass();
			String methodName = request.getMethodName();
			Class<?>[] parameterTypes = request.getParameterTypes();
			Object[] parameters = request.getParameters();
			/**
			 * 通过cglib进行代理
			 */
			FastClass serviceFastClass = FastClass.create(serviceClass);
			FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
			Object result = serviceFastMethod.invoke(serviceBean, parameters);
			response.setResult(result);
		} catch (Throwable t) {
			t.printStackTrace();
			response.setError(t);
		}
		return response;
	}
	@Override
	public void destroy() throws Exception {
		//zookeeper服务
		if (zkSwitch) {
			ZKRegisterService.destory();
		}
		server.destroy();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// init rpc provider
		LoggerUtils.info(NettyServerCnxnFactory.class, "begin init tcp server port="+port+","
				+ "serializer=protostuff,zkSwith="+zkSwitch+",zkAddress="+ZKEnvirmentUtils.ZK_ADDDRESS
				+",zkSessionTimeout="+ZKEnvirmentUtils.ZK_SESSION_TIMEOUT);
		server = new NettyServer();
		server.start(port, serializer,protocol);
		//zookeeper注册中心服务
		if (zkSwitch) {
			ZKRegisterService.registerServices(port, serviceMap.keySet());
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(LKRpcService.class);
		if (serviceBeanMap!=null && serviceBeanMap.size()>0) {
			for (Object serviceBean : serviceBeanMap.values()) {
				String interfaceName = serviceBean.getClass().getAnnotation(LKRpcService.class).value().getName();
				LoggerUtils.info(NetRpcServerFactory.class, "provider services[" + interfaceName + "]");
				serviceMap.put(interfaceName, serviceBean);
			}
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Serializer getSerializer() {
		return serializer;
	}

	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}
	public boolean isZkSwitch() {
		return zkSwitch;
	}
	public void setZkSwitch(boolean zkSwitch) {
		this.zkSwitch = zkSwitch;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}
