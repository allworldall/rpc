package com.linekong.erating.api.pojo.response;

import com.linekong.erating.api.pojo.resultmapper.UserBalanceInfoDO;

import java.util.List;

/**
 *用户余额查询, 应答数据对象
 * CMD_USER_BALANCE_INFO_EX1_RES 0x200034 11
 *① 本协议包是变长包，将依据subjectCount表示的个数决定subjectId,totalCharge,balance重复的次数
 */
public class UserBalanceInfoRespDTO {

    private int resultCode;   //返回结果

    private long userId;       //玩家账号ID

    private long roleId;       //玩家的角色ID

//    private int subjectCount;  //玩家拥有的货币种类数量

    private List<UserBalanceInfoDO> list;

    @Override
    public String toString() {
        return String.format("resultCode=%s|userId=%s|roleId=%s|userBalanceInfo=%s",
                resultCode, userId, roleId,list == null ? "" : list.toString());
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
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

    public List<UserBalanceInfoDO> getList() {
        return list;
    }

    public void setList(List<UserBalanceInfoDO> list) {
        this.list = list;
    }
}
