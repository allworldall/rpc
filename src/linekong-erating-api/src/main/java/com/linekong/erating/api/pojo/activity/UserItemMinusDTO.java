package com.linekong.erating.api.pojo.activity;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 活动礼品领取，请求数据对象
 * CMD_USER_ITEM_MINUS_EX 0x10003506
 * ①本协议是变长包,将依据itemCount表示activityId,itemCode,itemNum的重复次数
 */
public class UserItemMinusDTO extends BaseDTO {

    private long userId;      //玩家帐号ID

    private long roleId;      //玩家角色ID

    private int itemCount;   //表示要领取的物品条数

    private long activityId[];  //表示活动编号

    private String itemCode[]; //表示虚拟物品的唯一代码

    private int itemNum[];     //表示虚拟物品减少的数目

    @Override
    public String toString() {
        return String.format(super.toString() + "|userId=%s|roleId=%s|itemCount=%s|activityId=%s|itemCode=%s|itemNum=%s",
                userId,roleId,itemCount,activityId,itemCode,itemNum);
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

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public long[] getActivityId() {
        return activityId;
    }

    public void setActivityId(long[] activityId) {
        this.activityId = activityId;
    }

    public String[] getItemCode() {
        return itemCode;
    }

    public void setItemCode(String[] itemCode) {
        this.itemCode = itemCode;
    }

    public int[] getItemNum() {
        return itemNum;
    }

    public void setItemNum(int[] itemNum) {
        this.itemNum = itemNum;
    }
}
