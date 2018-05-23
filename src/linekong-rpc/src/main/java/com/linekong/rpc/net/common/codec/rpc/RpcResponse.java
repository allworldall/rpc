package com.linekong.rpc.net.common.codec.rpc;

import java.io.Serializable;

public class RpcResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6193502851617322473L;
	
	private String requestId;   //请求ID
	
	private long requestTimeMillis; //返回时间毫秒
	
	private String reponseTime; //返回时间
	
    private Throwable error;    //错误信息
    
    private Object result;      //结果
    
    public boolean isError() {
        return error != null;
    }
    
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Throwable getError() {
		return error;
	}

	public void setError(Throwable error) {
		this.error = error;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	public long getRequestTimeMillis() {
		return requestTimeMillis;
	}

	public void setRequestTimeMillis(long requestTimeMillis) {
		this.requestTimeMillis = requestTimeMillis;
	}

	public String getReponseTime() {
		return reponseTime;
	}

	public void setReponseTime(String reponseTime) {
		this.reponseTime = reponseTime;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("rpc responseInfo:").append("requestId=").append(requestId).append("|");
		buffer.append("requestTimeMillis=").append(requestTimeMillis).append("|");
		buffer.append("reponseTime=").append(reponseTime).append("|");
		buffer.append("error=").append(error).append("|");
		buffer.append("result=").append(result);
		return buffer.toString();
	}
    
    

}
