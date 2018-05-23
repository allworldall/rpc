package com.linekong.erating.api.pojo.resultmapper;

public class UserLogoutInfoDO {

    private int subjectId;      //科目编码（货币类型）:5 – 表示通过充值获取的游戏币 6 -  可以和充值游戏币混用的免费货币，与充值游戏币具有相等或者相近的购买能力。
    //100 - 表示被纳入防沉迷玩家的剩余收益时间（单位：秒）

    private int amount;         //若字段2为100，则表示字段2相应科目的剩余数值；其他情况下表示字段2相应科目的充值

    @Override
    public String toString() {
        return String.format("subjectId=%s|amount=%s",
                subjectId, amount);
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
