package com.linekong.erating.logic.dao;

import com.linekong.erating.api.pojo.gateway.GatewayBindDTO;
import com.linekong.erating.api.pojo.gateway.GatewayDataReportDTO;
import com.linekong.erating.api.pojo.gateway.GatewayUnBindDTO;

public interface GatewayTypeDao {

	/**
	 * 网关绑定
	 * @param GatewayBindDTO gatewayBind
	 * @return int
	 */
	public int gatewayBind(GatewayBindDTO gatewayBind);
	
	/**
	 * 网关解绑
	 * @param GatewayUnBindDTO gatewayUnBind
	 * @return int
	 */
	public int gatewayUnBind(GatewayUnBindDTO gatewayUnBind);
	/**
	 * 网关数据上报
	 * @param GatewayDataReportDTO gatewayDataReport
	 * @return int
	 */
	public int gatewayDataReport(GatewayDataReportDTO gatewayDataReport);

}
