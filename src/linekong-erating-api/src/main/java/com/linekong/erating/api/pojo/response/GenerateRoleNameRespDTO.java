package com.linekong.erating.api.pojo.response;


import java.util.List;

/**
 * 非必接
 *生成角色名，应答数据对象
 *CMD_GENERATE_ROLE_NAME_RES 0x20003114
 * 本协议包是变长包，将依据roleCount表示的个数决定roleName重复的次数
 */
public class GenerateRoleNameRespDTO {

    private int resultCode;    //返回结果

    private int roleCount;     //本次应答包内所含角色名个数

    private List<String> roleName;   //角色名称

    @Override
    public String toString() {
        return String.format("resultCode=%s|roleCount=%s|roleName=%s",
                resultCode,roleCount,roleName);
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(int roleCount) {
        this.roleCount = roleCount;
    }

    public List<String> getRoleName() {
        return roleName;
    }

    public void setRoleName(List<String> roleName) {
        this.roleName = roleName;
    }
}
