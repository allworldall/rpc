package com.linekong.erating.api.pojo.account;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 玩家登出对象，请求数据对象
 * CMD_USER_LOGOUT 0x10003303
 */
public class UserLogoutDTO extends BaseDTO {

    private long userId;          //玩家帐号ID。

    private long roleId;          //玩家的角色ID

    private int logoutFlag;      //登出标志。 1 – 角色正常登出； 2 – 报告角色在线状态。用于更新角色的级别等信息，以及计算防沉迷时间；
                                 //3 – 异常登出。此处的异常指游戏相应接口服务无法获取角色级别等数据的情况，而非掉线等对玩家而言的异常

    private int roleOccupation;  //职业（或门派等，依具体游戏情节而定）。具体的取值依据游戏而定

    private int roleLevel;       //当前角色的级别

    private int ratingId;        //网关ID

    private long money1;          //金钱1，非绑定金币。包括存款和身上携带的

    private long money2;          //金钱2，绑定金币

    private long experience;     //当前经验值

    @Override
    public String toString() {
        return String.format(super.toString() + "|userId=%s|roleId=%s|logoutFlag=%s|roleOccupation=%s|roleLevel=%s|ratingId=%s|money1=%s|money2=%s|experience=%s",
                userId,roleId,logoutFlag,roleOccupation,roleLevel,ratingId,money1,money2,experience);
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

    public int getLogoutFlag() {
        return logoutFlag;
    }

    public void setLogoutFlag(int logoutFlag) {
        this.logoutFlag = logoutFlag;
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

    public long getMoney1() {
        return money1;
    }

    public void setMoney1(long money1) {
        this.money1 = money1;
    }

    public long getMoney2() {
        return money2;
    }

    public void setMoney2(long money2) {
        this.money2 = money2;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }
}
