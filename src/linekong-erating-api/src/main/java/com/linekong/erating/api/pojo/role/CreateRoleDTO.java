package com.linekong.erating.api.pojo.role;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 创建新角色，请求数据对象
 * CMD_CREATE_ROLE 0x10003102
 */
public class CreateRoleDTO extends BaseDTO {

    private long userId;         //帐号ID

    private String roleName;    //角色名称

    private int roleGender;     //性别。 1 – 男性； 2 – 表示女性

    private int roleOccupation; //角色职业（或门派）

    private int initialLevel;   //表示角色被创建时的初始级别。例如：初始级别为0级或1级

    private long userIP;         //玩家IP地址。如点分形式的ip地址127.0.0.1需要传入的整数形式为127*2563+0*2562+0*256+1=2130706433；

    private int userPort;       //玩家端口号

    private int communityId;    //社区编号，默认为0。例如，国家

    private int pad1;           //保留字

    @Override
    public String toString() {
        return String.format(super.toString() + "|userId=%s|roleName=%s|roleGender=%s|roleOccupation=%s|initialLevel=%s|userIP=%s|userPort=%s|communityId=%s|pad1=%s",
                userId,roleName,roleGender,roleOccupation,initialLevel,userIP,userPort,communityId,pad1);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleGender() {
        return roleGender;
    }

    public void setRoleGender(int roleGender) {
        this.roleGender = roleGender;
    }

    public int getRoleOccupation() {
        return roleOccupation;
    }

    public void setRoleOccupation(int roleOccupation) {
        this.roleOccupation = roleOccupation;
    }

    public int getInitialLevel() {
        return initialLevel;
    }

    public void setInitialLevel(int initialLevel) {
        this.initialLevel = initialLevel;
    }

    public long getUserIP() {
        return userIP;
    }

    public void setUserIP(long userIP) {
        this.userIP = userIP;
    }

    public int getUserPort() {
        return userPort;
    }

    public void setUserPort(int userPort) {
        this.userPort = userPort;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getPad1() {
        return pad1;
    }

    public void setPad1(int pad1) {
        this.pad1 = pad1;
    }
}
