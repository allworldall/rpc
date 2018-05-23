package com.linekong.erating.api.pojo.gateway;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 网关绑定传输对象
 * CMD_BIND  0x10000001
 */
public class GatewayBindDTO extends BaseDTO{
	
	private String gatewayCode;        //网关接入码
	
	private String gatewayPassword;    //网关密码
	
	private String mac;               //网关的MAC地址 如：0016E6DE3C74，字母大写
	
	private int reconnectFlag;        //重连标志，默认传0
	
	private int pad;                  //保留字段 保留字 长度为1 默认传递0
	
	private int pad1;                 //保留字段 长度为2 默认传递00
	
	private int serverId;             //接口GS标识，一般情况下与协议头中的网关ID一致

	public String getGatewayCode() {
		return gatewayCode;
	}

	public void setGatewayCode(String gatewayCode) {
		this.gatewayCode = gatewayCode;
	}

	public String getGatewayPassword() {
		return gatewayPassword;
	}

	public void setGatewayPassword(String gatewayPassword) {
		this.gatewayPassword = gatewayPassword;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getReconnectFlag() {
		return reconnectFlag;
	}

	public void setReconnectFlag(int reconnectFlag) {
		this.reconnectFlag = reconnectFlag;
	}

	public int getPad() {
		return pad;
	}

	public void setPad(int pad) {
		this.pad = pad;
	}

	public int getPad1() {
		return pad1;
	}

	public void setPad1(int pad1) {
		this.pad1 = pad1;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	@Override
	public String toString() {
		return String.format(super.toString() + "|gatewayCode=%s|gatewayPassword=%s|mac=%s|reconnectFlag=%s|pad=%s|pad1=%s|serverId=%s",
				gatewayCode,gatewayPassword,mac,reconnectFlag,pad,pad1,serverId);
	}
	
	
}
