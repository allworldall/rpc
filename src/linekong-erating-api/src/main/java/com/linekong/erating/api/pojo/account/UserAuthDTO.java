package com.linekong.erating.api.pojo.account;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 账号认证对象
 * CMD_USER_AUTH_EX4 0x10003313
 */
public class UserAuthDTO extends BaseDTO {

    private String userName;   //用户名

    private String password;   //密码或其它序列号

    private int passwordType;  //密码类型。1 - 表示一级登录密码。3 - 表示密保卡密码。4 - 表示动态密码。（注：使用密保卡或动态密保认证时必须同时输入一级登录密码）

    private int gameClientType;  //游戏客户端类型：1 表示微型客户端登陆，需要到ePassport系统进行密码校验
                                   // 0 表示其他客户端登陆手游中：1 ios  2 安卓  3 其它

    private int userPort;        //用户端口

    private long userIP;          //用户IP地址：用户登录的IPv4地址，
                                // 如点分形式的ip地址127.0.0.1需要传入的整数形式为127*2563+0*2562+0*256+1=2130706433

    private String matrixPassword;     //密保卡密码。 当字段2值为1时，忽略此字段。 当字段2值为3时，为加密后的密保卡密码。（此处是按照蓝港在线统一加密规则加密后的密码）
                                      // 当字段2值为4时，为加密后的动态密保密码。

    private String matrixCoord;  //密保卡密码对应坐标。（如：A3D9G4） 该字段仅在字段2值为3时有效。

    private String mac;          //客户端主机MAC地址。 ios7.0及以上传IDFA:格式为大写带横杠,例如: 421389E5-4AAB-40B0-903F-836529768748
                                 // 其它情况传MAC:格式为小写不带冒号, 例如: 02eaff21aa20

    private String adId;         //客户端广告ID，要求可以转换为数字。

    private String hardwareSN1;  //客户端主机硬件序列号，如含有英文字母请做大写转换。 如：CPU序列号、BIOS序列号等硬件的原始或处理后的序列号。
                                 //如不使用或无法获取时请不要设置。

    private String hardwareSN2;  //客户端主机硬件序列号，如含有英文字母请做大写转换。 如：CPU序列号、BIOS序列号等硬件的原始或处理后的序列号。
                                 //如不使用或无法获取时请不要设置
    private String uddi;         //记录移动设备的唯一识别码, 如含有英文字母请做大写转换,如不使用或无法获取时请不要设置。

    @Override
    public String toString() {
        return String.format(super.toString() + "|userName=%s|password=%s|passwordType=%s|gameClientType=%s|userPort=%s|userIP=%s|matrixPassword=%s|matrixCoord=%s" +
                        "|mac=%s|adId=%s|hardwareSN1=%s|hardwareSN2=%s|uddi=%s",
                userName,password,passwordType,gameClientType,userPort,userIP,matrixPassword,matrixCoord,mac,adId,hardwareSN1,hardwareSN2,uddi);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(int passwordType) {
        this.passwordType = passwordType;
    }

    public int getGameClientType() {
        return gameClientType;
    }

    public void setGameClientType(int gameClientType) {
        this.gameClientType = gameClientType;
    }

    public int getUserPort() {
        return userPort;
    }

    public void setUserPort(int userPort) {
        this.userPort = userPort;
    }

    public long getUserIP() {
        return userIP;
    }

    public void setUserIP(long userIP) {
        this.userIP = userIP;
    }

    public String getMatrixPassword() {
        return matrixPassword;
    }

    public void setMatrixPassword(String matrixPassword) {
        this.matrixPassword = matrixPassword;
    }

    public String getMatrixCoord() {
        return matrixCoord;
    }

    public void setMatrixCoord(String matrixCoord) {
        this.matrixCoord = matrixCoord;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getHardwareSN1() {
        return hardwareSN1;
    }

    public void setHardwareSN1(String hardwareSN1) {
        this.hardwareSN1 = hardwareSN1;
    }

    public String getHardwareSN2() {
        return hardwareSN2;
    }

    public void setHardwareSN2(String hardwareSN2) {
        this.hardwareSN2 = hardwareSN2;
    }

    public String getUddi() {
        return uddi;
    }

    public void setUddi(String uddi) {
        this.uddi = uddi;
    }
}
