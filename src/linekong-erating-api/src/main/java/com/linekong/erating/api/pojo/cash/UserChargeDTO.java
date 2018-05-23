package com.linekong.erating.api.pojo.cash;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 玩家充值请求对象
 * CMD_USER_CHAEGE_EX2 0x10003413
 */
public class UserChargeDTO extends BaseDTO {

    private long detailId;      //充值流水号

    private long userId;         //玩家账号ID

    private int subjectId;      //货币类型:5 – 表示通过充值获取的游戏币

    private int pad;            //填充字段

    private int amount;         //充值游戏币数量

    private int pad2;           //填充字段，请勿使用

    private long chargeTime;     //充值时间（1970年1月1日零时到当前的秒数，UTC）

    private int totalAmount;    //玩家充值获取的游戏币总额：游戏服务器可根据此数值来进行玩家的VIP等级设定

    private int balance;        //玩家当前剩余的充值游戏币

    private String attachCode;  //充值附加码。由游戏自己定义，充值时作为附加参数传递，充值推送时会透传给GS

    @Override
    public String toString() {
        return String.format(super.toString() + "|detailId=%s|userId=%s|subjectId=%s|pad=%s|amount=%s|pad2=%s|chargeTime=%s|totalAmount=%s|balance=%s|attachCode=%s",
                detailId,userId,subjectId,pad,amount,pad2,chargeTime,totalAmount,balance,attachCode);
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getPad() {
        return pad;
    }

    public void setPad(int pad) {
        this.pad = pad;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPad2() {
        return pad2;
    }

    public void setPad2(int pad2) {
        this.pad2 = pad2;
    }

    public long getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(long chargeTime) {
        this.chargeTime = chargeTime;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAttachCode() {
        return attachCode;
    }

    public void setAttachCode(String attachCode) {
        this.attachCode = attachCode;
    }
}
