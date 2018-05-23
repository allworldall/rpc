package com.linekong.erating.router.remote;

import com.linekong.erating.api.pojo.cash.*;
import com.linekong.erating.api.pojo.response.UserAddFreeMoneyRespDTO;
import com.linekong.erating.api.pojo.response.UserBalanceInfoRespDTO;
import com.linekong.erating.api.pojo.response.UserCashExchangeRespDTO;
import com.linekong.erating.api.pojo.response.UserIBPayRespDTO;
import com.linekong.erating.api.remote.CashTypeProtocolInterface;
import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.dataparse.RequestDataParseToObject;
import com.linekong.erating.router.dataparse.ResponseDataParseToByte;
import com.linekong.erating.router.utils.ByteBuferUtils;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 此类用于调用rpc货币服务
 */
@Service
public class CashTypeProtocalRpc  extends BaseBinaryProtocol {

    private static final Logger log = Logger.getLogger(GatewayTypeProtocolRpc.class);

    @Autowired
    private RequestDataParseToObject dataToObject;

    @Autowired
    private ResponseDataParseToByte dataToByte;

    @Autowired
    private CashTypeProtocolInterface cashInterface;

    /**
     * 玩家免费货币添加请求
     * @param msg
     * @return
     */
    public PDUMessage userAddFreeMoney(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //封装调用rpc方法的参数
        UserAddFreeMoneyDTO userAddFreeMoney = dataToObject.userAddFreeMoneyDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("userAddFreeMoney:" + userAddFreeMoney.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_USER_ADD_FREE_MONEY_RES, 28);//checksum值校验不通过
        }
        //调用rpc
        UserAddFreeMoneyRespDTO userAddFreeMoneyResp = cashInterface.userAddFreeMoney(userAddFreeMoney);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_USER_ADD_FREE_MONEY_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(userAddFreeMoney, userAddFreeMoneyResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("userAddFreeMoney:" + userAddFreeMoney.toString() + "|checkSum=" + message.getCheckSum() + "|result userAddFreeMoneyResp: " + userAddFreeMoneyResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }

    /**
     *用户余额查询
     * @param msg
     * @return
     */
    public PDUMessage userBalanceInfo(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //封装调用rpc方法的参数
        UserBalanceInfoDTO userBalanceInfo = dataToObject.userBalanceInfoDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("userBalanceInfo:" + userBalanceInfo.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_USER_BALANCE_INFO_EX1_RES, 28);//checksum值校验不通过
        }
        //调用rpc
        UserBalanceInfoRespDTO userBalanceInfoResp = cashInterface.userBalanceInfo(userBalanceInfo);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_USER_BALANCE_INFO_EX1_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(userBalanceInfo, userBalanceInfoResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("userBalanceInfo:" + userBalanceInfo.toString() + "|checkSum=" + message.getCheckSum() + "|result userBalanceInfoResp: " + userBalanceInfoResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;

    }

    /**
     * 元宝流通
     * @param msg
     * @return
     */
    public PDUMessage userCashExchange(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //封装调用rpc方法的参数
        UserCashExchangeDTO userCashExchange = dataToObject.userCashExchangeDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("userCashExchange:" + userCashExchange.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_USER_CASH_EXCHANGE_EX4_RES, 28);//checksum值校验不通过
        }
        //调用rpc方法
        UserCashExchangeRespDTO userCashExchangeResp = cashInterface.userCashExchange(userCashExchange);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_USER_CASH_EXCHANGE_EX4_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(userCashExchange, userCashExchangeResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("userCashExchange:" + userCashExchange.toString() + "|checkSum=" + message.getCheckSum() + "|result userCashExchangeResp: " + userCashExchangeResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }

    /**
     * 玩家充值
     * @param msg
     * @return
     */
    public PDUMessage userCharge(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //封装调用rpc方法的参数
        UserChargeDTO userCharge = dataToObject.userChargeDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("userCharge:" + userCharge.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_USER_CHAEGE_EX2_RES, 4);//checksum值校验不通过
        }
        //调用rpc方法
        int result = cashInterface.userCharge(userCharge);
        //封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_USER_CHAEGE_EX2_RES);
        message.setHeader(msg.getHeader());
        message.setBody(ByteBuferUtils.intToByte(result, 4));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));

        log.info("userCharge:" + userCharge.toString() + "|checkSum=" + message.getCheckSum() + "|result=" + result + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }

    /**
     * 增值道具消费
     * @param msg
     * @return
     */
    public PDUMessage userIBPay(PDUMessage msg) {
        long begin = System.currentTimeMillis();
        //封装调用rpc方法的参数
        UserIBPayDTO userIBPay = dataToObject.userIBPayDataParse(msg);
        //判断checksum值
        if(!this.validateCheckSum(msg.getCheckSum(), msg)) {
            log.info("userIBPay:" + userIBPay.toString()+ "|checkSum error"  + "|time=" + (System.currentTimeMillis() - begin));
            return this.response(msg, BinaryCMDConstant.CMD_USER_IB_PAY_EX1_RES, 36);//checksum值校验不通过
        }
        //调用rpc
        UserIBPayRespDTO userIBPayResp = cashInterface.userIBPay(userIBPay);
        //3、封装应答数据
        PDUMessage message = new PDUMessage();
        msg.getHeader().setCommandId(BinaryCMDConstant.CMD_USER_IB_PAY_EX1_RES);
        message.setHeader(msg.getHeader());
        message.setBody(dataToByte.getByteBody(userIBPay, userIBPayResp));
        message.getHeader().setTotalLength(24 + message.getBody().length);
        message.setResv(msg.getResv());
        message.setCheckSum(this.checkSum(message));
        log.info("userIBPay:" + userIBPay.toString() + "|checkSum=" + message.getCheckSum() + "|result userIBPayResp: " + userIBPayResp + "|time=" + (System.currentTimeMillis() - begin));
        return message;
    }
}
