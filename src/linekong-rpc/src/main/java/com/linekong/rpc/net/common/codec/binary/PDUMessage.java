package com.linekong.rpc.net.common.codec.binary;


public class PDUMessage {
	
	private PDUMessageHeader header;   //消息头
	
	private byte[] body;               //消息体
	
    private short resv;                //保留字
	
	private int checkSum;              //校验位
	public PDUMessage() {}
	public PDUMessage(PDUMessageHeader header,byte[] body,short resv,int checkSum) {
		this.header = header;
		this.body = body;
		this.resv = resv;
		this.checkSum = checkSum;
	}

	public PDUMessageHeader getHeader() {
		return header;
	}

	public void setHeader(PDUMessageHeader header) {
		this.header = header;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
	public short getResv() {
		return resv;
	}

	public void setResv(short resv) {
		this.resv = resv;
	}

	public int getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(int checkSum) {
		this.checkSum = checkSum;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(header.toString()).append("|");
	    //buffer.append(new String(body,"UTF-8")).append("|");
		buffer.append("resv=").append(resv).append("|");
		buffer.append("checkSum=").append(checkSum);
		return buffer.toString();
	}
}
