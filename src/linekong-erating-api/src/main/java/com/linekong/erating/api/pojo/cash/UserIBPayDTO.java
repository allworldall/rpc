package com.linekong.erating.api.pojo.cash;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 增值道具消费,请求数据对象
 * CMD_USER_IB_PAY_EX1 0x10003717
 * ①本协议包是变长包，将依据subjectCount的值决定subjectId和subAmount重复的次数。
 */
public class UserIBPayDTO extends BaseDTO {


    private long detailId;         //消费流水号:流水号规则请参照文档《认证计费系统对接备忘.doc》

    private long userId;            //玩家账号ID

    private long roleId;            //角色ID

    private int roleGender;        //角色性别

    private int roleOccupation;    //角色职业（或门派）

    private int roleLevel;         //角色等级

    private int ratingId;          //网关id

    private String iBCode;         //产品名称

    private int packageFlag;       //1 – 表示普通产品。2 – 表示组合产品

    private int pad1;              //保留字

    private int count;             //购买个数

    private int payTime;           //GS端的消费时间（1970年1月1日零时到当前的秒数，UTC）

    private long userIP;           //玩家IP地址。如点分形式的ip地址127.0.0.1需要传入的整数形式为127*2563+0*2562+0*256+1=2130706433

    private int price;             //单位价格。若是包，则指包的总价格

    private int discountPrice;     //表示折扣后的价格

    private int subjectCount;      //此次购买混合使用了几种货币，如果没有混合使用则填1。此字段将会决定Subject_ID，Sub_Price两个字段的重复次数

    private int[] subjectId;         //货币科目ID。充值货币：5 – 表示已兑换/领取元宝，玩家之间可以流通；非充值货币：
                                   //4 - 拥有专属商城的免费货币，不能和3或者5混用；6 - 可以和充值游戏币混用的免费货币，与充值游戏币具有相等或者相近的购买能力

    private long[] subAmount;         //对应的Subject_ID消费金额。如果同一笔消费使用多种货币混合购买，则各Subject_ID的Sub_Price加起来必须和本次消费的总额相等

    @Override
    public String toString() {
        return String.format(super.toString() + "|detailId=%s|userId=%s|roleId=%s|roleGender=%s|roleOccupation=%s|roleLevel=%s|ratingId=%s|iBCode=%s|packageFlag=%s|pad1=%s" +
                        "|count=%s|payTime=%s|userIP=%s|price=%s|discountPrice=%s|subjectCount=%s|subjectId=%s|subAmount=%s",
                detailId,userId,roleId,roleGender,roleOccupation,roleLevel,ratingId,iBCode,packageFlag,pad1,count,payTime,userIP,
                price,discountPrice,subjectCount,subjectId,subAmount);
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

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public int getRoleGender() {
        return roleGender;
    }

    public void setRoleGender(int roleGender) {
        this.roleGender = roleGender;
    }

    public int getRoleOccupation() {
        return roleOccupation;
    }

    public void setRoleOccupation(int roleOccupation) {
        this.roleOccupation = roleOccupation;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getiBCode() {
        return iBCode;
    }

    public void setIBCode(String iBCode) {
        this.iBCode = iBCode;
    }

    public int getPackageFlag() {
        return packageFlag;
    }

    public void setPackageFlag(int packageFlag) {
        this.packageFlag = packageFlag;
    }

    public int getPad1() {
        return pad1;
    }

    public void setPad1(int pad1) {
        this.pad1 = pad1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPayTime() {
        return payTime;
    }

    public void setPayTime(int payTime) {
        this.payTime = payTime;
    }

    public long getUserIP() {
        return userIP;
    }

    public void setUserIP(long userIP) {
        this.userIP = userIP;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
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
