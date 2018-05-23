package com.linekong.erating.logic.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linekong.erating.api.pojo.cash.UserAddFreeMoneyDTO;
import com.linekong.erating.api.pojo.cash.UserBalanceInfoDTO;
import com.linekong.erating.api.pojo.cash.UserCashExchangeDTO;
import com.linekong.erating.api.pojo.cash.UserChargeDTO;
import com.linekong.erating.api.pojo.cash.UserIBPayDTO;
import com.linekong.erating.api.pojo.response.UserAddFreeMoneyRespDTO;
import com.linekong.erating.api.pojo.response.UserBalanceInfoRespDTO;
import com.linekong.erating.api.pojo.response.UserCashExchangeRespDTO;
import com.linekong.erating.api.pojo.response.UserIBPayRespDTO;
import com.linekong.erating.api.remote.CashTypeProtocolInterface;
import com.linekong.erating.logic.dao.CashTypeDao;
import com.linekong.rpc.net.common.annotation.LKRpcService;
@LKRpcService(CashTypeProtocolInterface.class)
@Service
public class CashTypeService implements CashTypeProtocolInterface {

	private static final Logger log = Logger.getLogger(CashTypeService.class);
	@Autowired
	private CashTypeDao cashTypeDao;
	
	public UserAddFreeMoneyRespDTO userAddFreeMoney(UserAddFreeMoneyDTO userAddFreeMoney) {
		long begin = System.currentTimeMillis();
		UserAddFreeMoneyRespDTO userAddFreeMoneyRespData = cashTypeDao.userAddFreeMoney(userAddFreeMoney);
		log.info(userAddFreeMoney.toString() +"|userAddFreeMoneyRespData= " + userAddFreeMoneyRespData + "|time=" + (System.currentTimeMillis() - begin));
		return userAddFreeMoneyRespData;
	}

	public UserBalanceInfoRespDTO userBalanceInfo(UserBalanceInfoDTO userBalanceInfo) {
		long begin = System.currentTimeMillis();
		UserBalanceInfoRespDTO userBalanceInfoRespData = cashTypeDao.userBalanceInfo(userBalanceInfo);
		log.info(userBalanceInfo.toString() +"|userBalanceInfoRespData= " + userBalanceInfoRespData + "|time=" + (System.currentTimeMillis() - begin));
		return userBalanceInfoRespData;
	}

	public UserCashExchangeRespDTO userCashExchange(UserCashExchangeDTO userCashExchange) {
		long begin = System.currentTimeMillis();
		UserCashExchangeRespDTO userCashExchangeRespData = cashTypeDao.userCashExchange(userCashExchange);
		log.info(userCashExchange.toString() +"|userCashExchangeRespData= " + userCashExchangeRespData + "|time=" + (System.currentTimeMillis() - begin));
		return userCashExchangeRespData;
	}

	public int userCharge(UserChargeDTO userCharge) {
		long begin = System.currentTimeMillis();
		int userChargeRespData = cashTypeDao.userCharge(userCharge);
		log.info(userCharge.toString() +"|userChargeRespData= " + userChargeRespData + "|time=" + (System.currentTimeMillis() - begin));
		return userChargeRespData;
	}

	public UserIBPayRespDTO userIBPay(UserIBPayDTO userIBPay) {
		long begin = System.currentTimeMillis();
		UserIBPayRespDTO userIBPayRespData = cashTypeDao.userIBPay(userIBPay);
		log.info(userIBPay.toString() +"|userIBPayRespData= " + userIBPayRespData + "|time=" + (System.currentTimeMillis() - begin));
		return userIBPayRespData;
	}

}
