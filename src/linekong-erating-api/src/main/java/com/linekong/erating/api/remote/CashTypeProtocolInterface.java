package com.linekong.erating.api.remote;

import com.linekong.erating.api.pojo.cash.*;
import com.linekong.erating.api.pojo.response.UserAddFreeMoneyRespDTO;
import com.linekong.erating.api.pojo.response.UserBalanceInfoRespDTO;
import com.linekong.erating.api.pojo.response.UserCashExchangeRespDTO;
import com.linekong.erating.api.pojo.response.UserIBPayRespDTO;

/**
 * 货币类服务
 */
public interface CashTypeProtocolInterface {

    /**
     * 玩家免费货币添加服务
     * @param UserAddFreeMoneyDTO userAddFreeMoney 请求数据对象
     * @return UserAddFreeMoneyRespDTO 应答对象
     */
    public UserAddFreeMoneyRespDTO userAddFreeMoney(UserAddFreeMoneyDTO userAddFreeMoney);

    /**
     * 用户余额查询
     * @param UserBalanceInfoDTO userBalanceInfo 请求数据对象
     * @return UserBalanceInfoRespDTO 应答对象
     */
    public UserBalanceInfoRespDTO userBalanceInfo(UserBalanceInfoDTO userBalanceInfo);

    /**
     * 元宝流通服务
     * @param UserCashExchangeDTO userCashExchange 请求数据对象
     * @return UserCashExchangeRespDTO 应答对象
     */
    public UserCashExchangeRespDTO userCashExchange(UserCashExchangeDTO userCashExchange);

    /**
     * 玩家充值
     * @param UserChargeDTO userCharge 请求数据对象
     * @return Integer 应答状态码
     */
    public int userCharge(UserChargeDTO userCharge);

    /**
     * 增值道具消费
     * @param UserIBPayDTO userIBPay 请求数据对象
     * @return UserIBPayRespDTO 应答对象
     */
    public UserIBPayRespDTO userIBPay(UserIBPayDTO userIBPay);
}
