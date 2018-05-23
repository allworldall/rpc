package com.linekong.erating.api.pojo.role;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 角色进入游戏，请求数据对象
 * CMD_ROLE_ENTER_GAME_EX5 0x10003119
 * 字段4、5、6、7、12、13无法获取时可置零。
   字段5、6、7、10具体取值依据游戏而定
 */
public class RoleEnterGameDTO extends BaseDTO {

    private int serverId;    //接口GS标识，一般情况下与协议头中的网关ID一致

    private long userId;      //帐号ID

    private long roleId;      //角色ID

    private int level;       //角色当前级别

    private int gender;      //角色性别（0-未定，1-男，2-女）

    private int occupationId;//角色职业（或门派）

    private long corpsId;     //角色所在军团、工会等

    private int communityId; //角色所在国家、阵营等

    private int clientPort;  //客户端端口

    private long clientIP;    //客户端IP地址。如点分形式的ip地址127.0.0.1需要传入的整数形式为127*2563+0*2562+0*256+1=2130706433

    private int clientType;  //客户端操作系统类型：1 ios；2 安卓；3 WP；4 其它

    private String clientMAC; //客户端主机MAC地址。 ios7.0及以上传IDFA: 格式为大写带横杠，例如：421389E5-4AAB-40B0-903F-836529768748
                              //其它情况传MAC地址:格式为小写不带冒号，例如：02eaff21aa20

    private String hardwareSN1;//客户端主机硬件序列号，如含有英文字母请做大写转换。如：CPU序列号、BIOS序列号等硬件的原始或处理后的序列号。
                               //如不使用或无法获取时请不要设置

    private String HardwareSN2;//客户端主机硬件序列号，如含有英文字母请做大写转换。如：CPU序列号、BIOS序列号等硬件的原始或处理后的序列号。
                                //如不使用或无法获取时请不要设置

    private String uddi;       //记录移动设备的唯一识别码, 如含有英文字母请转换为小写,如不使用或无法获取时请不要设置

    private String modelVersion;//设备型号相关信息(未获取到则传空):按照设备类型###网络环境###操作系统版本号组合的形式进行传输(###为分割符号),并且字母全部小写,例如

    private String ldId;        //蓝港sdk所计算的客户端所在设备的唯一编号,未接入蓝港sdk则传空

    @Override
    public String toString() {
        return String.format(super.toString() + "|serverId=%s|userId=%s|roleId=%s|level=%s|gender=%s|occupationId=%s|corpsId=%s|communityId=%s" +
                        "|clientPort=%s|clientIP=%s|clientType=%s|clientMAC=%s|hardwareSN1=%s|HardwareSN2=%s|uddi=%s|modelVersion=%s|ldId=%s",
                serverId,userId,roleId,level,gender,occupationId,corpsId,communityId,clientPort,clientIP,clientType,clientMAC,hardwareSN1,
                HardwareSN2,uddi,modelVersion,ldId);
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(int occupationId) {
        this.occupationId = occupationId;
    }

    public long getCorpsId() {
        return corpsId;
    }

    public void setCorpsId(long corpsId) {
        this.corpsId = corpsId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public long getClientIP() {
        return clientIP;
    }

    public void setClientIP(long clientIP) {
        this.clientIP = clientIP;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getClientMAC() {
        return clientMAC;
    }

    public void setClientMAC(String clientMAC) {
        this.clientMAC = clientMAC;
    }

    public String getHardwareSN1() {
        return hardwareSN1;
    }

    public void setHardwareSN1(String hardwareSN1) {
        this.hardwareSN1 = hardwareSN1;
    }

    public String getHardwareSN2() {
        return HardwareSN2;
    }

    public void setHardwareSN2(String hardwareSN2) {
        HardwareSN2 = hardwareSN2;
    }

    public String getUddi() {
        return uddi;
    }

    public void setUddi(String uddi) {
        this.uddi = uddi;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public String getLdId() {
        return ldId;
    }

    public void setLdId(String ldId) {
        this.ldId = ldId;
    }
}
