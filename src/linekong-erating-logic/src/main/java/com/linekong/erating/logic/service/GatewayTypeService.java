package com.linekong.erating.logic.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linekong.erating.api.pojo.gateway.GatewayBindDTO;
import com.linekong.erating.api.pojo.gateway.GatewayDataReportDTO;
import com.linekong.erating.api.pojo.gateway.GatewayUnBindDTO;
import com.linekong.erating.api.remote.GatewayTypeProtocolInterface;
import com.linekong.erating.logic.dao.GatewayTypeDao;
import com.linekong.rpc.net.common.annotation.LKRpcService;
/**
 * 网关类型协议处理
 */
@LKRpcService(GatewayTypeProtocolInterface.class)
@Service
public class GatewayTypeService implements GatewayTypeProtocolInterface{
	
	private static final Logger log = Logger.getLogger(GatewayTypeService.class);

	@Autowired
	private GatewayTypeDao gatewayTypeDao;
	/**
	 * 网关绑定
	 */
	public int gatewayBind(GatewayBindDTO gatewayBind) {
		long begin = System.currentTimeMillis();
		int gatwayBindRespData = gatewayTypeDao.gatewayBind(gatewayBind);
		log.info(gatewayBind.toString() +"|gatwayBindRespData= " + gatwayBindRespData + "|time=" + (System.currentTimeMillis() - begin));
		return gatwayBindRespData;
	}
	/**
	 * 网关解绑
	 */
	public int gatewayUnBind(GatewayUnBindDTO gatewayUnBind) {
		long begin = System.currentTimeMillis();
		int gatewayUnBindRespData = gatewayTypeDao.gatewayUnBind(gatewayUnBind);
		log.info(gatewayUnBind.toString() +"|gatewayUnBindRespData= " + gatewayUnBindRespData + "|time=" + (System.currentTimeMillis() - begin));
		return gatewayUnBindRespData;
	}
	/**
	 * 数据上报
	 */
	public int gatewayDataReport(GatewayDataReportDTO gatewayDataReport) {
		long begin = System.currentTimeMillis();
		int gatewayDataReportRespData = gatewayTypeDao.gatewayDataReport(gatewayDataReport);
		log.info(gatewayDataReport.toString() +"|gatewayDataReportRespData= " + gatewayDataReportRespData + "|time=" + (System.currentTimeMillis() - begin));
		return gatewayDataReportRespData;
	}

}
