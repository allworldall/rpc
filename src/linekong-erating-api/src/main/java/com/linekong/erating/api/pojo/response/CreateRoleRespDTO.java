package com.linekong.erating.api.pojo.response;


/**
 * 创建角色接口返回对象
 * CMD_CREATE_ROLE_RES 0x20003102
 */
public class CreateRoleRespDTO {

    private int resultCode;    //返回结果

    private long roleId;        //角色ID

    @Override
    public String toString() {
        return String.format("resultCode=%s|roleId=%s",
                resultCode,roleId);
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
