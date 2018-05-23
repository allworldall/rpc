package com.linekong.erating.api.pojo.response;


import com.linekong.erating.api.pojo.resultmapper.RoleEnterGameDO;

import java.util.List;

/**
 * 角色进入游戏，应答数据对象
 * CMD_ROLE_ENTER_GAME_EX5_RES 0x20003119
 * ①本协议包是变长包，将依据balanceCount表示的个数决定subjectId和amount重复的次数
 */
public class RoleEnterGameRespDTO {

    private int resultCode;      //返回结果

//    private int balanceCount;    //表示后续的余额信息个数

    List<RoleEnterGameDO> list;


    @Override
    public String toString() {
        return String.format("resultCode=%s|roleEnterGameInfo=%s",
                resultCode,list == null ? "" : list.toString());
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<RoleEnterGameDO> getList() {
        return list;
    }

    public void setList(List<RoleEnterGameDO> list) {
        this.list = list;
    }
}
