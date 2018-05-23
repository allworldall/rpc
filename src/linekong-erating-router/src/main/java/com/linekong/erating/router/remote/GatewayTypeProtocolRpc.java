package com.linekong.erating.router.remote;

import org.apache.log4j.Logger;
import com.linekong.erating.router.dataparse.RequestDataParseToObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.linekong.erating.api.pojo.gateway.GatewayBindDTO;
import com.linekong.erating.api.pojo.gateway.GatewayDataReportDTO;
import com.linekong.erating.api.pojo.gateway.GatewayUnBindDTO;
import com.linekong.erating.api.remote.GatewayTypeProtocolInterface;
import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.utils.ByteBuferUtils;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
/**
 * 此类用于调用rpc网关服务
 */
@Service
public class GatewayTypeProtocolRpc extends BaseBinaryProtocol {
	
	private static final Logger log = Logger.getLogger(GatewayTypeProtocolRpc.class);
	@Autowired
	private GatewayTypeProtocolInterface gatewayInterface;

	@Autowired
	private RequestDataParseToObject dataToObject;
	

	/**
	 * 网关绑定
	 */
	public PDUMessage gatewayBind(PDUMessage msg) {
		long begin = System.currentTimeMillis();
		//封装调用rpc方法的参数
		GatewayBindDTO gatewayBind = dataToObject.gatewayBindDataParse(msg);
		//判断checksum值
		if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
			log.info("gatewayBind:" + gatewayBind.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
			return this.response(msg, BinaryCMDConstant.CMD_BIND_RES, 4);//checksum值校验不通过
		}
		//调用rpc接口
		int result = gatewayInterface.gatewayBind(gatewayBind);
		//封装应答数据
		PDUMessage message = new PDUMessage();
		msg.getHeader().setCommandId(BinaryCMDConstant.CMD_BIND_RES);
		message.setHeader(msg.getHeader());
		message.setBody(ByteBuferUtils.intToByte(result, 4));
		message.getHeader().setTotalLength(24 + message.getBody().length);
		message.setResv(msg.getResv());
		message.setCheckSum(this.checkSum(message));
		log.info("gatewayBind:" + gatewayBind.toString() + "|checkSum=" + message.getCheckSum() + "|result=" + result + "|time=" + (System.currentTimeMillis() - begin));
		return message;
	}
	/**
	 * 网关解绑
	 * @param PDUMessage msg
	 * @return PDUMessage
	 */
	public PDUMessage gatewayUnBind(PDUMessage msg) {
		long begin = System.currentTimeMillis();
		//封装调用rpc方法的参数
		GatewayUnBindDTO gatewayUnBind = dataToObject.gatewayUnBindDataParse(msg);
		//判断checksum值
		if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
			log.info("gatewayUnBind:" + gatewayUnBind.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
			return this.response(msg, BinaryCMDConstant.CMD_UNBIND_RES, 4);//checksum值校验不通过
		}
		//调用Rpc接口
		int result = gatewayInterface.gatewayUnBind(gatewayUnBind);
		//封装应答数据
		PDUMessage message = new PDUMessage();
		msg.getHeader().setCommandId(BinaryCMDConstant.CMD_UNBIND_RES);
		message.setHeader(msg.getHeader());
		message.setBody(ByteBuferUtils.intToByte(result, 4));
		message.getHeader().setTotalLength(24 + message.getBody().length);
		message.setResv(msg.getResv());
		message.setCheckSum(this.checkSum(message));
		log.info("gatewayUnBind:" + gatewayUnBind.toString() + "|checkSum=" + message.getCheckSum() + "|result=" + result + "|time=" + (System.currentTimeMillis() - begin));
		return message;
	}
	/**
	 * 网关数据信息上报 GS须每隔15秒进行一次网关数据上传操作。
	 */
	public PDUMessage gatewayDataReport(PDUMessage msg) {
		long begin = System.currentTimeMillis();
		//封装调用rpc方法的参数
		GatewayDataReportDTO gatewayDataReport = dataToObject.gatewayDataReportDataParse(msg);
		//判断checksum值
		if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
			log.info("gatewayDataReport:" + gatewayDataReport.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
			return this.response(msg, BinaryCMDConstant.CMD_GW_DATA_REPORT_RES, 4);//checksum值校验不通过
		}
		//调用rpc接口
		int result = gatewayInterface.gatewayDataReport(gatewayDataReport);
		//封装应答数据
		PDUMessage message = new PDUMessage();
		msg.getHeader().setCommandId(BinaryCMDConstant.CMD_GW_DATA_REPORT_RES);
		message.setHeader(msg.getHeader());
		message.setBody(ByteBuferUtils.intToByte(result, 4));
		message.getHeader().setTotalLength(24 + message.getBody().length);
		message.setResv(msg.getResv());
		message.setCheckSum(this.checkSum(message));
		log.info("gatewayDataReport:" + gatewayDataReport.toString() + "|checkSum=" + message.getCheckSum() + "|result=" + result + "|time=" + (System.currentTimeMillis() - begin));
		return message;
	}
}
