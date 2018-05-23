package com.linekong.erating.api.pojo.response;


import com.linekong.erating.api.pojo.resultmapper.UserIBPayInfoDO;

import java.util.List;

/**
 * 增值道具消费，应到数据对象
 * CMD_USER_IB_PAY_EX1_RES 0x20003717
 * 本协议包是变长包，将依据balanceCount表示的个数决定subjectId,balance重复的次数
 */
public class UserIBPayRespDTO {

    private int resultCode;     //返回结果

    private long detailId;      //消费流水号

    private long userId;         //玩家账号ID

    private int ratingId;       //网关ID

    private long costAmount;     //此次消费所有货币加在一起消耗的总金额

//    private int balanceCount;   //此次购买混合使用了几种货币，和请求包中的Subject_Count字段保持一致。此字段将会决定Subject_ID，Balance两个字段的重复次数

    private List<UserIBPayInfoDO> list;

    @Override
    public String toString() {
        return String.format("resultCode=%s|detailId=%s|userId=%s|ratingId=%s|costAmount=%s|userIBPayInfo%s",
                resultCode,detailId,userId,ratingId,costAmount,list == null ? "" : list.toString());
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
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

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public long getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(long costAmount) {
        this.costAmount = costAmount;
    }

    public List<UserIBPayInfoDO> getList() {
        return list;
    }

    public void setList(List<UserIBPayInfoDO> list) {
        this.list = list;
    }
}
