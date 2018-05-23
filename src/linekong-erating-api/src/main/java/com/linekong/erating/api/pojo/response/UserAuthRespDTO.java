package com.linekong.erating.api.pojo.response;


/**
 * 账号认证接口返回响应对象
 * CMD_USER_AUTH_EX4_RES 0x20003313
 */
public class UserAuthRespDTO {
    private int resultCode;     //返回结果

    private long userID;         //用户ID

    private int userType;       //用户类型

    private int adultFlag;      //成年人标志。 1 – 表示成年人； 0 – 表示非成年人。

    private int userClass;     //用户VIP等级

    private int pad;           //该字段保留不使用

    private int userPoint;     //用户积分

    private long promoterId;    //该字段保留不使用

    private long userFlag;      //用户标识，需要跨区控制的状态都由此值维护。 此字段按bit位对各状态进行标识，对应bit位为1时表示： bit11（2048）：首次登录；

    @Override
    public String toString() {
        return String.format("resultCode=%s|userID=%s|userType=%s|adultFlag=%s|userClass=%s|pad=%s|userPoint=%s|promoterId=%s|userFlag=%s",
                              resultCode,userID,userType,adultFlag,userClass,pad,userPoint,promoterId,userFlag);
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
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

    public int getPad() {
        return pad;
    }

    public void setPad(int pad) {
        this.pad = pad;
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
}
