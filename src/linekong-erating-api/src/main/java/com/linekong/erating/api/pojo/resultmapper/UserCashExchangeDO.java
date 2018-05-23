package com.linekong.erating.api.pojo.resultmapper;

public class UserCashExchangeDO {

    private int subjectId;            //货币科目ID

    private int srcBalance;           //源用户的余额

    private int dstBalance;           //目标用户的余额

    @Override
    public String toString() {
        return String.format("subjectId=%s|srcBalance=%s|dstBalance=%s",
                subjectId, srcBalance, dstBalance);
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getSrcBalance() {
        return srcBalance;
    }

    public void setSrcBalance(int srcBalance) {
        this.srcBalance = srcBalance;
    }

    public int getDstBalance() {
        return dstBalance;
    }

    public void setDstBalance(int dstBalance) {
        this.dstBalance = dstBalance;
    }
}
