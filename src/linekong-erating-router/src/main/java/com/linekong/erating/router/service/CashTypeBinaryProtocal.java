package com.linekong.erating.router.service;

import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.remote.CashTypeProtocalRpc;
import com.linekong.rpc.net.common.annotation.LKBinaryService;
import com.linekong.rpc.net.common.annotation.LKCMDService;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 货币相关
 */
@LKBinaryService(CashTypeBinaryProtocal.class)
@Service
public class CashTypeBinaryProtocal {

    @Autowired
    private CashTypeProtocalRpc cash;

    /**
     * 玩家免费货币添加请求
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_USER_ADD_FREE_MONEY)
    public PDUMessage userAddFreeMoney(PDUMessage msg) {
        return cash.userAddFreeMoney(msg);
    }


    /**
     * 用户余额查询
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_USER_BALANCE_INFO_EX1)
    public PDUMessage userBalanceInfo(PDUMessage msg) {
        return cash.userBalanceInfo(msg);
    }

    /**
     * 元宝流通
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_USER_CASH_EXCHANGE_EX4)
    public PDUMessage userCashExchange(PDUMessage msg) {
        return cash.userCashExchange(msg);
    }

    /**
     * 玩家充值
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_USER_CHAEGE_EX2)
    public PDUMessage userCharge(PDUMessage msg) {
        return cash.userCharge(msg);
    }

    /**
     * 增值道具消费
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_USER_IB_PAY_EX1)
    public PDUMessage userIBPay(PDUMessage msg) {
        return cash.userIBPay(msg);
    }
}
