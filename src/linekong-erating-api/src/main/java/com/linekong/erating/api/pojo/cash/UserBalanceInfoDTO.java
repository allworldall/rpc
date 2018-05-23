package com.linekong.erating.api.pojo.cash;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 用户余额查询, 请求数据对象
 *CMD_USER_BALANCE_INFO_EX1 0x100034 11
 */
public class UserBalanceInfoDTO extends BaseDTO {

    private long userId;    //玩家帐号ID

    private long roleId;    //玩家的角色ID

    private int ratingId;  //网关Id

    @Override
    public String toString() {
        return String.format(super.toString() + "|userId=%s|roleId=%s|ratingId=%s",
                userId, roleId, ratingId);
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

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }
}
