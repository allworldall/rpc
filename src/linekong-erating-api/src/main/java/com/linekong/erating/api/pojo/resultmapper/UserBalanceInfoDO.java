package com.linekong.erating.api.pojo.resultmapper;

public class UserBalanceInfoDO {

    private int subjectId;     //货币科目ID。充值货币： 5 – 表示已兑换/领取元宝，玩家之间可以流通； 非充值货币： 4 -  拥有专属商城的免费货币，不能和5混用；
    //6 -  可以和充值游戏币混用的免费货币，与充值游戏币具有相等或者相近的购买能力。10  - 奖池活动积分

    private int totalCharge;   //此种货币的充值总额。（免费货币由于无充值，此字段总是为0）

    private int balance;       //此种货币的剩余数量

    @Override
    public String toString() {
        return String.format("subjectId=%s|totalCharge=%s|balance=%s",
                subjectId, totalCharge, balance);
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(int totalCharge) {
        this.totalCharge = totalCharge;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
