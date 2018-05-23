package com.linekong.erating.api.pojo.account;

import com.linekong.erating.api.pojo.BaseDTO;

/**
 * 合作运营认证操作
 *CMD_JOINT_AUTHEN_EX 0x10003802
 * 保存各参数的以‘\0’结束的字符串，最大长度限制长(含结束符)为1000Byte。根据需要可以选择性的包含下述字段（顺序不限）。
 * 在进行手游对接时，以下字段在未进行手游说明的情况下，无需传送，若有手游标识，则表示有值的情况下必须传送：
 *
 *腾讯的Token的长度可能大于256字节，游戏服务器在处理时需要注意此字段的长度
 */
public class JointAuthenDTO extends BaseDTO {

    private String un;        //合作运营方用来标识其用户的唯一ID，一般情况下为第三方的用户ID；（手游）

    private String token;     //认证加密串；（手游）

    private long userIP4;   //用户登录的IPv4地址,ip需使用整数形式。如点分形式的ip地址127.0.0.1需要传入的整数形式为127*2563+0*2562+0*256+1=2130706433；（手游）

    private String port;      //用户登录的端口；（手游）

    private String mac;       //客户端主机MAC地址。ios7.0及以上传IDFA:格式为大写带横杠，例如：421389E5-4AAB-40B0-903F-836529768748    其它情况传MAC:格式为小写不带冒号，例如：02eaff21aa20；（手游）

    private String clientType;//客户端类型:1 ios;2 android;3 wp;4其它 （手游）

    private String sdkVersion;//版本号，为空的情况下，默认使用最初的版本（手游）

    private String unixTime;   //时间戳（手游）

    private String cpId;       //渠道编号（手游）

    private String pad;        //扩展字段，用于临时数据传输（手游）

    private String adId;       //广告ID（手游）

    private String uId;        //合作运营方帐号ID

    private String adultState; //成人标志

    private String password;   //帐号密码（加密后的密码，采用合作运营方passport系统自己的加密规则）

    private String passwordType;//密码类型；1 - 表示一级登录密码。3 - 表示密保卡密码。4 - 表示动态密码。5 - 表示声讯密码（电话锁）。注意：无论密码类型如何，PW字段总为玩家的登录密码。

    private String idCode;      //玩家身份证

    @Override
    public String toString() {
        return String.format(super.toString() + "|un=%s|token=%s|userIP4=%s|port=%s|mac=%s|clientType=%s|sdkVersion=%s|unixTime=%s|cpId=%s" +
                        "|pad=%s|adId=%s|uId=%s|adultState=%s|password=%s|passwordType=%s|idCode=%s",
                un,token,userIP4,port,mac,clientType,sdkVersion,unixTime,cpId,pad,adId,uId,adultState,password,passwordType,idCode);
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserIP4() {
        return userIP4;
    }

    public void setUserIP4(long userIP4) {
        this.userIP4 = userIP4;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(String unixTime) {
        this.unixTime = unixTime;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getPad() {
        return pad;
    }

    public void setPad(String pad) {
        this.pad = pad;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getAdultState() {
        return adultState;
    }

    public void setAdultState(String adultState) {
        this.adultState = adultState;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
}
