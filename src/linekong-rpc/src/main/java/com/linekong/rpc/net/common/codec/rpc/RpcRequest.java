package com.linekong.rpc.net.common.codec.rpc;

import java.io.Serializable;

public class RpcRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6261729295955020897L;
	
	private String requestId;           //请求ID
	
	private long   requestTimeMillis;  //请求时间精确到毫秒
	
	private String requestTime;	       //请求创建时间
	
    private String className;           //类
    
    private String methodName;          //方法
    
    private Class<?>[] parameterTypes;  //参数类型
    
    private Object[] parameters;        //参数
    
    private String version;             //版本号
    
    private String extend;              //拓展信息

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public long getRequestTimeMillis() {
		return requestTimeMillis;
	}

	public void setRequestTimeMillis(long requestTimeMillis) {
		this.requestTimeMillis = requestTimeMillis;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("rpc requestInfo:").append("requestId=").append(this.requestId).append("|");
		buffer.append("requestTimeMillis=").append(this.requestTimeMillis).append("|");
		buffer.append("requestTime=").append(this.requestTime).append("|");
		buffer.append("className=").append(this.className).append("|");
		buffer.append("methodName=").append(this.methodName).append("|");
		buffer.append("parameterTypes=").append(this.getParameterTypes()).append("|");
		buffer.append("parameters=").append(this.parameters).append("|");
		buffer.append("version=").append(this.version).append("|");
		buffer.append("extend=").append(this.extend);
		return buffer.toString();
	}
}
