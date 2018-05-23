package com.linekong.erating.router.remote;

import org.apache.log4j.Logger;
import com.linekong.erating.api.code.EratingCodeConstant;
import com.linekong.erating.router.utils.ByteBuferUtils;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import com.linekong.rpc.net.common.codec.binary.PDUMessageHeader;
import com.linekong.rpc.utils.ByteUtils;
import com.linekong.rpc.utils.eRatingProtocolUtis;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class BaseBinaryProtocol {
	
	private static final Logger log = Logger.getLogger(BaseBinaryProtocol.class);
	/**
	 * 计算checksum值
	 * @param PDUMessage
	 */
	public int checkSum(PDUMessage msg) {
		PDUMessageHeader header = msg.getHeader();
		ByteBuf byteBuf = Unpooled.buffer(header.getTotalLength() - 2);
		byteBuf.writeShort(header.getTotalLength());
		byteBuf.writeBytes(ByteUtils.short2Byte1(header.getVersion()));
		byteBuf.writeBytes(ByteUtils.short2Byte1(header.getRemainPackages()));
		byteBuf.writeInt((int)header.getCommandId());
		byteBuf.writeInt((int)header.getSequenceId());
		byteBuf.writeInt((int)header.getGameId());
		byteBuf.writeInt((int)header.getGatewayId());
		byteBuf.writeBytes(msg.getBody());
		byteBuf.writeShort(msg.getResv());
		int checkSum = eRatingProtocolUtis.crc16(byteBuf.array(), header.getTotalLength() - 2);
		return checkSum;
	}
	/**
	 * 检测checksum值
	 * @param long reciveCheckSum 接收到的checksum值
	 * @param PDUMessage msg      自定义消息内容
	 */
	public boolean validateCheckSum(int reciveCheckSum,PDUMessage msg) {
		int checkSum = checkSum(msg);
		if(checkSum == reciveCheckSum) {
			return true;
		}else {
			log.error(String.format("checkSum error:recive checksum=%s,calculation=%s",reciveCheckSum, checkSum));
			return false;
		}
	}
	/**
	 * 返回checksum值异常
	 * @param PDUMessage msg 自定义消息内容
	 * @param long commandId 返回协议的命令ID
	 */
	public PDUMessage response(PDUMessage msg,long commandId) {
		PDUMessage message = new PDUMessage();
		msg.getHeader().setCommandId(commandId);
		message.setHeader(msg.getHeader());
		message.setBody(ByteBuferUtils.intToByte(EratingCodeConstant.E_PDU_CHECKSUM_ERROR, 4));
		message.setResv(msg.getResv());
		message.setCheckSum(checkSum(message));
		return message;
	}

	/**
	 * 返回checksum值异常，因为有些协议应答的数据不止状态码，所以body的长度是可变的，估加一个body的长度参数
	 * @param PDUMessage msg       自定义消息内容
	 * @param long commandId       协议的命令ID
	 * @param int bodyLength       body的长度
	 * @return
	 */
	public PDUMessage response(PDUMessage msg, long commandId, int bodyLength) {
		PDUMessage message = new PDUMessage();
		message.setBody(ByteBuferUtils.createMessageBody(EratingCodeConstant.E_PDU_CHECKSUM_ERROR, bodyLength));
		message.setResv(msg.getResv());
		msg.getHeader().setCommandId(commandId);          //设置命令ID
		msg.getHeader().setTotalLength(24 + message.getBody().length);     //设置协议的数据长度
		message.setHeader(msg.getHeader());
		message.setCheckSum(checkSum(message));
		return message;
	}

}
