package com.linekong.erating.api.pojo.role;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 非必接协议
 *
 *生成角色名,请求数据对象
 * CMD_GENERATE_ROLE_NAME 0x10003114
 */
public class GenerateRoleNameDTO extends BaseDTO {

    private int roleGender;    //角色性别:1 男 2 女 0 未定

    private int roleCount;    //请求获取角色名的个数

    @Override
    public String toString() {
        return String.format(super.toString() + "|roleGender=%s|roleCount=%s",
                roleGender,roleCount);
    }

    public int getRoleGender() {
        return roleGender;
    }

    public void setRoleGender(int roleGender) {
        this.roleGender = roleGender;
    }

    public int getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(int roleCount) {
        this.roleCount = roleCount;
    }
}
