package com.linekong.erating.api.pojo.response;

import com.linekong.erating.api.pojo.resultmapper.UserLogoutInfoDO;

import java.util.List;

/**
 * 玩家登出接口返回对象
 * CMD_USER_LOGOUT 0x20003303
 * ①　该返回包为变长包，balanceCount的数值决定了subjectId和amount重复次数。
 * ②　当前仅在玩家被纳入防沉迷系统时，返回玩家的剩余有收益游戏时间
 */
public class UserLogoutRespDTO {

    private int resultCode;     //返回结果

//    private int balanceCount;   //表示后续的余额信息个数

    private List<UserLogoutInfoDO> list;
    @Override
    public String toString() {
        return String.format("resultCode=%s|userLogoutInfo=%s",
                resultCode,list == null? "" : list.toString());
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<UserLogoutInfoDO> getList() {
        return list;
    }

    public void setList(List<UserLogoutInfoDO> list) {
        this.list = list;
    }
}
