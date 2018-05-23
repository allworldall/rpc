package com.linekong.erating.api.pojo.role;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 非必接协议
 *
 * 角色改名，请求数据对象
 * CMD_RENAME_ROLE 0x10003107
 */
public class RenameRoleDTO extends BaseDTO {

    private int userId;      //玩家帐号ID

    private int roleId;      //角色Id

    private String roleName; //角色名称

    @Override
    public String toString() {
        return String.format(super.toString() + "|userId=%s|roleId=%s|roleName=%s",
                userId,roleId,roleName);
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
