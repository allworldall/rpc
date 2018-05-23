package com.linekong.erating.api.remote;

import com.linekong.erating.api.pojo.gateway.GatewayBindDTO;
import com.linekong.erating.api.pojo.gateway.GatewayDataReportDTO;
import com.linekong.erating.api.pojo.gateway.GatewayUnBindDTO;

/**
 * 网关类服务
 */
public interface GatewayTypeProtocolInterface {
	
	/**
	 * 网关信息绑定
	 * @param GatewayBindDTO gatewayBind
	 * @return Integer
	 */
	public int gatewayBind(GatewayBindDTO gatewayBind);
	/**
	 * 网关解绑 该协议用于网关与eRating之间的连接解除
	 * 网关只有在连接上eRating之后才可以发该数据包，通常是在网关关闭时发送此协议
	 * @param GatewayUnBindDTO gatewayUnBind
	 * @return Integer
	 */
	public int gatewayUnBind(GatewayUnBindDTO gatewayUnBind);
	/**
	 * 网关数据上传操作
	 * @param GatewayDataReportDTO gatewayDataReport
	 * @return Integer
	 */
	public int gatewayDataReport(GatewayDataReportDTO gatewayDataReport);

}
