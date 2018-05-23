package com.linekong.erating.api.pojo.gateway;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 网关去连接
 * CMD_UNBIND 0x10000002
 */
public class GatewayUnBindDTO extends BaseDTO{
	
	private int serverId;   //网关ID
	
	public int getServerId() {
		return serverId;
	}


	public void setServerId(int serverId) {
		this.serverId = serverId;
	}


	@Override
	public String toString() {
		return String.format(super.toString() + "|serverId=", serverId);
	}
}
