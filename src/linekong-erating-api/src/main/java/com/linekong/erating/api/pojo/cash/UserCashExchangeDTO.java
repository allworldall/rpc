package com.linekong.erating.api.pojo.cash;


import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 元宝流通,请求数据对象
 * CMD_USER_CASH_EXCHANGE_EX4 0x10003718
 *①本协议包是变长包，将依据subjectCount表示的个数决定subjectId,subAmount重复的次数
 */
public class UserCashExchangeDTO extends BaseDTO {

    private long detailId;             //元宝流通流水号:流水号规则请参照文档《认证计费系统对接备忘.doc》

    private long srcRoleId;             //源角色ID

    private long dstRoleId;             //目标角色ID

    private long exchangeTime;          //流通时间（1970年1月1日零时到当前的秒数，UTC）

    private int type;                  //流通类型:由游戏自行定义，eRating对其只做采集、记录，供数据统计分析之用

    private int srcGameId;             //源玩家所属Game_ID

    private int dstGameId;             //目标玩家所属Game_ID

    private String itemCode;           //此笔元宝流通所交易的物品代码，如无物品交易可以为空

    private int itemNum;               //此笔元宝流通所交易的物品数量

    private int subjectCount;          //此次元宝流通混合使用了几种货币，如果没有混合使用则填1。此字段将会决定Subject_ID，Sub_Price两个字段的重复次数

    private int[] subjectId;             //货币科目ID。充值货币：5 – 表示已兑换/领取元宝，玩家之间可以流通；
                                       //非充值货币：6 -  可以和充值游戏币混用的免费货币，与充值游戏币具有相等或者相近的购买能力

    private long[] subAmount;             //对应的Subject_ID流通金额。如果同一笔元宝流通使用多种货币，则各Subject_ID的Sub_Price加起来必须和本次元宝流通的总额相等

    @Override
    public String toString() {
        return String.format(super.toString() + "|detailId=%s|srcRoleId=%s|dstRoleId=%s|exchangeTime=%s|type=%s|srcGameId=%s|dstGameId=%s|itemCode=%s|itemNum=%s" +
                        "|subjectCount=%s|subjectId=%s|subAmount=%s",
                detailId,srcRoleId,dstRoleId,exchangeTime,type,srcGameId,dstGameId,itemCode,itemNum,subjectCount,subjectId,subAmount);
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public long getSrcRoleId() {
        return srcRoleId;
    }

    public void setSrcRoleId(long srcRoleId) {
        this.srcRoleId = srcRoleId;
    }

    public long getDstRoleId() {
        return dstRoleId;
    }

    public void setDstRoleId(long dstRoleId) {
        this.dstRoleId = dstRoleId;
    }

    public long getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(long exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSrcGameId() {
        return srcGameId;
    }

    public void setSrcGameId(int srcGameId) {
        this.srcGameId = srcGameId;
    }

    public int getDstGameId() {
        return dstGameId;
    }

    public void setDstGameId(int dstGameId) {
        this.dstGameId = dstGameId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public int getSubjectCount() {
        return subjectCount;
    }

    public void setSubjectCount(int subjectCount) {
        this.subjectCount = subjectCount;
    }

    public int[] getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int[] subjectId) {
        this.subjectId = subjectId;
    }

    public long[] getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(long[] subAmount) {
        this.subAmount = subAmount;
    }
}
