package com.linekong.rpc.net.common.codec.binary;

public class PDUMessageHeader {
	
	private int totalLength;         //PDU的总字节长度，包括本字段的2个字节在内
	
	private short version;            //协议版本号，由两位数组成，主版本号.副版本号。版本号为V1.0时，此字段值为0x10，版本号为V1.5时，此字段值为0x15，其它情况依此类推。目前为0x20

	private short remainPackages;     //该字段表示还剩余多少后续包 0 - 表示没有后续包 >0 - 实际后续包的数量；
	
	private long commandId;           //命令字，表示协议类型
	
	private long sequenceId;          //用于请求和响应表示联系的序列号，请求包和对应的响应包的序列号必须保持一致。
	
	private long gameId;              //游戏ID，每一个游戏在每个联运中的Game_ID都不一样，是erating程序判断本条消息来源渠道的重要字段。
	
	private long gatewayId;           //网关ID，服务器的唯一编号。在同一款游戏下，每一个服都有唯一的网关ID。
	/**
	 * 构造函数
	 */
	public PDUMessageHeader(int totalLength,short version,short remainPackages,long commandId,long sequenceId,long gameId,long gatewayId) {
		this.totalLength = totalLength;
		this.version = version;
		this.remainPackages = remainPackages;
		this.commandId = commandId;
		this.sequenceId = sequenceId;
		this.gameId = gameId;
		this.gatewayId = gatewayId;
	}
	
	public int getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public short getVersion() {
		return version;
	}

	public void setVersion(short version) {
		this.version = version;
	}

	public short getRemainPackages() {
		return remainPackages;
	}

	public void setRemainPackages(short remainPackages) {
		this.remainPackages = remainPackages;
	}

	public long getCommandId() {
		return commandId;
	}

	public void setCommandId(long commandId) {
		this.commandId = commandId;
	}

	public long getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(long sequenceId) {
		this.sequenceId = sequenceId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(long gatewayId) {
		this.gatewayId = gatewayId;
	}

	@Override
	public String toString() {
		return String.format("totalLength=%s|version=%s|remainPackage=%s|commandId=%s|sequenceId=%s|gameId=%s|gatewayId=%s", 
				totalLength,version,remainPackages,"Ox"+Long.toHexString(commandId),sequenceId,gameId,gatewayId);
	}
}
