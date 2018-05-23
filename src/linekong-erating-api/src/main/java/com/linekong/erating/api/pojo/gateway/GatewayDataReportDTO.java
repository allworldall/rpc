package com.linekong.erating.api.pojo.gateway;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 网关数据上传对象
 *CMD_GW_DATA_REPORT  0x10002003
 * ①　本协议包是变长包，将依据dataCount表示的个数决定dataType,dataValue重复的次数。
 * GS须每隔15秒进行一次网关数据上传操作
 */
public class GatewayDataReportDTO extends BaseDTO {

    private int serverId;     //接口GS标识，一般情况下与协议头中的网关ID一致

    private int dataCount;    //上报的数据项数量

    private int[] dataType;     //数据项类型。1 – 在线人数；4 – 服务器玩家已消费银元宝总量；5 – 服务器玩家持有银元宝总量；6 – 服务器启动；7 – 服务器关闭。 8 - 奖池活动在线人数

    private int[] dataValue;    //数据项的具体值

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int[] getDataType() {
        return dataType;
    }

    public void setDataType(int[] dataType) {
        this.dataType = dataType;
    }

    public int[] getDataValue() {
        return dataValue;
    }

    public void setDataValue(int[] dataValue) {
        this.dataValue = dataValue;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "|serverId=%s|dataCount=%s|dataType=%s|dataValue=%s",
                serverId, dataCount, dataType, dataValue);
    }

}
