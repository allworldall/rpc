package com.linekong.erating.api.pojo.activity;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 活动礼品查询,请求数据对象
 * CMD_ACTIVITY_ITEM_QUERY_EX 0x10003512
 */
public class ActivityItemQueryDTO extends BaseDTO {

    private long userId;     //玩家帐号ID

    private long roleId;     //玩家角色ID

    private long activityId; //表示活动编号,若传0,则将全部活动的物品返回,若传具体的活动ID,则返回活动ID前两位数字相同的对应活动的物品

    private int roleLevel;  //角色当前等级

    private int pad1;       //保留位1

    private long pad2;       //保留字段2

    @Override
    public String toString() {
        return String.format(super.toString() + "|userId=%s|roleId=%s|activityId=%s|roleLevel=%s|pad1=%s|pad2=%s",
                userId,roleId,activityId,roleLevel,pad1,pad2);
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

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public int getPad1() {
        return pad1;
    }

    public void setPad1(int pad1) {
        this.pad1 = pad1;
    }

    public long getPad2() {
        return pad2;
    }

    public void setPad2(long pad2) {
        this.pad2 = pad2;
    }
}
