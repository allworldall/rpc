package com.linekong.erating.api.pojo.response;

/**
 * 合作运营认证操作，接口返回对象
 * CMD_JOINT_AUTHEN_EX_RES 0x20003802
 */
public class JointAuthenRespDTO {

    private int resultCode;       //返回结果

    private long userId;           //玩家ID号，表示蓝港为用户分配的唯一数字ID。

    private int userType;         //玩家类型

    private int adultFlag;        //成年人标志。1 – 表示成年人；0 – 表示非成年人

    private int userClass;        //玩家VIP等级

    private int userRoleCount;    //该字段保留不使用

    private int userPoint;        //玩家积分

    private long promoterId;      //该字段保留不使用

    private long userFlag;        //用户标识，需要跨区控制的状态都由此值维护。此字段按bit位对各状态进行标识，对应bit位为1时表示：bit11（2048）：首次登录

    private int cpReturnValue;   //合作方认证返回值。取值含义视具体游戏及合作方而定

    private String cpErrMsg;      //合作方错误信息。合作运营认证失败时，该字段存储了从合作方传回的详细错误信息，可能需要通过客户端显示给玩家

    private String userName;       //账号名称：若用户为蓝港自营用户，则表示用户的蓝港账号；若用户为联运用户，则表示联运方的用户唯一标示符。注意：此字段在客户端使用充值和使用激活码时都会用到

    private int appendix1;        //扩展字段1

    private int appendix2;            //扩展字段2

    @Override
    public String toString() {
        return String.format("resultCode=%s|userId=%s|userType=%s|adultFlag=%s|userClass=%s|userRoleCount=%s|userPoint=%s|promoterId=%s|userFlag=%s" +
                        "|cpReturnValue=%s|cpErrMsg=%s|userName=%s|appendix1=%s|appendix2=%s",
                resultCode,userId,userType,adultFlag,userClass,userRoleCount,userPoint,promoterId,userFlag,cpReturnValue,
                cpErrMsg,userName,appendix1,appendix2);
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getAdultFlag() {
        return adultFlag;
    }

    public void setAdultFlag(int adultFlag) {
        this.adultFlag = adultFlag;
    }

    public int getUserClass() {
        return userClass;
    }

    public void setUserClass(int userClass) {
        this.userClass = userClass;
    }

    public int getUserRoleCount() {
        return userRoleCount;
    }

    public void setUserRoleCount(int userRoleCount) {
        this.userRoleCount = userRoleCount;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public long getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(long promoterId) {
        this.promoterId = promoterId;
    }

    public long getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(long userFlag) {
        this.userFlag = userFlag;
    }

    public int getCpReturnValue() {
        return cpReturnValue;
    }

    public void setCpReturnValue(int cpReturnValue) {
        this.cpReturnValue = cpReturnValue;
    }

    public String getCpErrMsg() {
        if(cpErrMsg == null)
            cpErrMsg = "";
        return cpErrMsg;
    }

    public void setCpErrMsg(String cpErrMsg) {
        this.cpErrMsg = cpErrMsg;
    }

    public String getUserName() {
        if(userName == null)
            userName = "";
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAppendix1() {
        return appendix1;
    }

    public void setAppendix1(int appendix1) {
        this.appendix1 = appendix1;
    }

    public int getAppendix2() {
        return appendix2;
    }

    public void setAppendix2(int appendix2) {
        this.appendix2 = appendix2;
    }
}
