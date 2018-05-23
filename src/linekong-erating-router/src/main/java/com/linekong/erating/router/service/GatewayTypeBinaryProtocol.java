package com.linekong.erating.router.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.remote.BaseBinaryProtocol;
import com.linekong.erating.router.remote.GatewayTypeProtocolRpc;
import com.linekong.rpc.net.common.annotation.LKBinaryService;
import com.linekong.rpc.net.common.annotation.LKCMDService;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;

/**
 * 网关相关
 */
@LKBinaryService(GatewayTypeBinaryProtocol.class)
@Service
public class GatewayTypeBinaryProtocol extends BaseBinaryProtocol{
	@Autowired
	private GatewayTypeProtocolRpc gateway;
	/**
	 * 网关信息绑定
	 * @param PDUMessage msg
	 * @return
	 */
	@LKCMDService(BinaryCMDConstant.CMD_BIND)
	public PDUMessage gatewayBind(PDUMessage msg) {
		return gateway.gatewayBind(msg);
	}
	/**
	 * 网关解绑
	 * @param PDUMessage msg
	 * @return
	 */
	@LKCMDService(BinaryCMDConstant.CMD_UNBIND)
	public PDUMessage gatewayUnBind(PDUMessage msg) {
		return gateway.gatewayUnBind(msg);
	}
	/**
	 * 网关数据上报
	 * @param PDUMessage msg
	 * @return
	 */
	@LKCMDService(BinaryCMDConstant.CMD_GW_DATA_REPORT)
	public PDUMessage gatewayDataReport(PDUMessage msg) {
		return gateway.gatewayDataReport(msg);
	}

}
