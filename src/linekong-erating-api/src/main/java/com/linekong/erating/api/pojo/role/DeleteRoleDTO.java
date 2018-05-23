package com.linekong.erating.api.pojo.role;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 非必接协议
 *
 * 删除角色，接口请求对象
 * CMD_DELETE_ROLE 0x10003103
 */
public class DeleteRoleDTO extends BaseDTO {

    private int userId;     //玩家帐号ID

    private int roleId;    //角色名称ID

    @Override
    public String toString() {
        return String.format(super.toString() + "|userId=%s|roleId=%s",
                userId,roleId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
