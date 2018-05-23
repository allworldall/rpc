package com.linekong.erating.api.pojo.response;


import com.linekong.erating.api.pojo.resultmapper.UserCashExchangeDO;

import java.util.List;

/**
 * 元宝流通,应答数据对象
 * CMD_USER_CASH_EXCHANGE_EX4_RES 0x20003718
 *本协议包是变长包，将依据balanceCount表示的个数决定subjectId,srcBalance,dstBalance重复的次数
 */
public class UserCashExchangeRespDTO {

    private int resultCode;                //返回结果。

    private List<UserCashExchangeDO>   list;

    @Override
    public String toString() {
        return String.format("resultCode=%s|userCashExchangeInfo=%s",
                resultCode,list == null ? "" : list.toString());
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<UserCashExchangeDO> getList() {
        return list;
    }

    public void setList(List<UserCashExchangeDO> list) {
        this.list = list;
    }
}
