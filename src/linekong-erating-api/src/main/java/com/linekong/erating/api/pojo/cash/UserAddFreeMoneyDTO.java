package com.linekong.erating.api.pojo.cash;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 玩家免费货币添加请求,请求数据对象
 * CMD_USER_ADD_FREE_MONEY  0x10003412
 */
public class UserAddFreeMoneyDTO extends BaseDTO {

    private long detailId;       //添加货币的流水号:流水号规则请参照文档《认证计费系统对接备忘.doc》

    private long userId;          //玩家账号ID

    private long roleId;          //玩家角色ID，如果所有角色通用可填0

    private int subjectId;       //货币科目ID。 非充值货币： 6 - 可以和充值游戏币混用的免费货币。 与充值游戏币具有相等或者相近的购买能力

    private int amount;          //货币添加金额

    private int addTime;         //货币添加的时间（1970年1月1日零时到当前的秒数，UTC）

    private String source;       //货币添加来源，由游戏自己定义，仅作统计使用

    @Override
    public String toString() {
        return String.format(super.toString() + "|detailId=%s|userId=%s|roleId=%s|subjectId=%s|amount=%s|addTime=%s|source=%s",
                detailId,userId,roleId,subjectId,amount,addTime,source);
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

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
