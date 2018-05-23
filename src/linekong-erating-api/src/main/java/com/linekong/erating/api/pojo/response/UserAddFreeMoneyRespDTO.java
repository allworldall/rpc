package com.linekong.erating.api.pojo.response;


/**
 * 玩家免费货币添加请求,应答数据对象
 * CMD_USER_ADD_FREE_MONEY_RES 0x20003412
 */
public class UserAddFreeMoneyRespDTO {

    private int resultCode;    //返回结果

    private int balance;       //此种货币的剩余数量

    @Override
    public String toString() {
        return String.format("resultCode=%s|balance=%s",
                resultCode,balance);
    }

    public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
