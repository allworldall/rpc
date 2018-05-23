package com.linekong.erating.api.pojo.response;


/**
 * 查询帐号生日返回对象
 *CMD_USER_BIRTHDAY_QUERY 0x20003014
 */
public class UserBirthdayQueryRespDTO {

    private int resultCode;     //返回结果

    private long Birthday;       //生日(日期时间戳)

    @Override
    public String toString() {
        return String.format("resultCode=%s|Birthday=%s",
                resultCode,Birthday);
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public long getBirthday() {
        return Birthday;
    }

    public void setBirthday(long birthday) {
        Birthday = birthday;
    }
}
