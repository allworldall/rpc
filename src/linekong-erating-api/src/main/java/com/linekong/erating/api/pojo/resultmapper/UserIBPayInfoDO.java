package com.linekong.erating.api.pojo.resultmapper;

public class UserIBPayInfoDO {

    private int subjectId;      //货币科目ID。充值货币：5 – 表示已兑换/领取元宝，玩家之间可以流通；
    //非充值货币：4 - 拥有专属商城的免费货币，不能和3或者5混用；6 - 可以和充值游戏币混用的免费货币，与充值游戏币具有相等或者相近的购买能力。

    private int balance;        //此种货币的剩余金额

    @Override
    public String toString() {
        return String.format("subjectId=%s|balance=%s",
                subjectId, balance);
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
