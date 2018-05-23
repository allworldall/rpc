package com.linekong.rpc.net.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.zookeeper.server.NettyServerCnxnFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.linekong.rpc.log.LoggerUtils;
import com.linekong.rpc.net.common.annotation.LKBinaryService;
import com.linekong.rpc.net.common.annotation.LKCMDService;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import com.linekong.rpc.net.common.codec.binary.PDUMessageHeader;
import com.linekong.rpc.net.common.server.IServer;
import com.linekong.rpc.net.netty.server.NettyServer;
import com.linekong.rpc.serialize.Serializer;
import com.linekong.rpc.utils.GlobalConstant;
import com.linekong.rpc.utils.ProtocolUtils;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

public class NetBinaryServerFactory implements ApplicationContextAware, InitializingBean,DisposableBean{

	/**
	 * 服务器初始化配置
	 */
	private int port = 7080;           //初始化端口
	
	private boolean zkSwitch = false;  //是否启用zookeeper注册中心 默认不启动
	
	private Serializer serializer;
	
	private String protocol = ProtocolUtils.BINARY_PROTOCOL;  //协议方式 二进制 binaryProtocol,rpc rpcProtocol
	
	private IServer server;
	
	private static Map<Object, Map<String,Object>> serviceMap = new ConcurrentHashMap<Object, Map<String,Object>>();
	
	public NetBinaryServerFactory(int port,boolean zkSwitch) {
		this.setPort(port);
		this.setZkSwitch(zkSwitch);
	}
	
	public static PDUMessage invoke(PDUMessage msg) {
		PDUMessageHeader header = msg.getHeader();
		Map<String,Object> map = new HashMap<String,Object>();
		if(serviceMap.containsKey(header.getCommandId())) {
			map = serviceMap.get(header.getCommandId());
		}else {
			LoggerUtils.error(NetBinaryServerFactory.class, header.getCommandId() + "没有对应的实现");
			return null;
		}
		try {
			Class<?> serviceClass = map.get(GlobalConstant.BINARY_CLASS).getClass();
			String methodName = (String) map.get(GlobalConstant.BINARY_METHOD);
			Class<?>[] parameterTypes = new Class<?>[] {PDUMessage.class};
			Object[] parameters = new Object[] {msg};
			/**
			 * 通过cglib进行代理
			 */
			FastClass serviceFastClass = FastClass.create(serviceClass);
			FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
			Object result = serviceFastMethod.invoke(map.get(GlobalConstant.BINARY_CLASS), parameters);
			return (PDUMessage) result;
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// init binary provider
		LoggerUtils.info(NettyServerCnxnFactory.class, "begin init binary server port="+port);
		server = new NettyServer();
		server.start(port, serializer,protocol);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(LKBinaryService.class);
		if (serviceBeanMap!=null && serviceBeanMap.size()>0) {
			for (Object serviceBean : serviceBeanMap.values()) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("binary services[");
				buffer.append(serviceBean).append("[");
				Method [] methods = serviceBean.getClass().getMethods();
				for(Method method : methods) {
					if(method.isAnnotationPresent(LKCMDService.class)) {
						long annotationValue = method.getAnnotation(LKCMDService.class).value();
						String methodName = method.getName();
						buffer.append("0x" + Long.toHexString(annotationValue) ).append("-->").append(methodName).append(",");
						Map<String,Object> map = new ConcurrentHashMap<String,Object>();
						map.put(GlobalConstant.BINARY_METHOD, methodName);
						map.put(GlobalConstant.BINARY_CLASS, serviceBean);
						serviceMap.put(annotationValue, map);
					}
				}
				buffer.append("]");
				buffer.append("]");
				LoggerUtils.info(NetBinaryServerFactory.class, buffer.toString());
			}
		}
	}
	
	@Override
	public void destroy() throws Exception {
		server.destroy();
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isZkSwitch() {
		return zkSwitch;
	}
	public void setZkSwitch(boolean zkSwitch) {
		this.zkSwitch = zkSwitch;
	}

}
