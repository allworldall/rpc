package com.linekong.erating.api.pojo.resultmapper;

public class RoleEnterGameDO {

    private int subjectId;       //科目编码（货币类型）:5 – 表示充值获取的货币；6  - 表示非充值获取的货币，此货币具有与充值货币相同或相近的购买能力。
    //100 –被纳入防沉迷的玩家剩余有效游戏时间（单位：秒）；其它未列出的货币类型根据具体的游戏而定

    private int amount;          //余额信息。表示字段3相应科目的剩余数值

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
