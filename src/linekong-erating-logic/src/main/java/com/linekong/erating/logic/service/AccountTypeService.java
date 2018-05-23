package com.linekong.erating.logic.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linekong.erating.api.pojo.account.JointAuthenDTO;
import com.linekong.erating.api.pojo.account.UserAuthDTO;
import com.linekong.erating.api.pojo.account.UserLogoutDTO;
import com.linekong.erating.api.pojo.response.JointAuthenRespDTO;
import com.linekong.erating.api.pojo.response.UserAuthRespDTO;
import com.linekong.erating.api.pojo.response.UserLogoutRespDTO;
import com.linekong.erating.api.remote.AccountTypeProtocolInterface;
import com.linekong.erating.logic.dao.AccountTypeDao;
import com.linekong.rpc.net.common.annotation.LKRpcService;
@LKRpcService(AccountTypeProtocolInterface.class)
@Service
public class AccountTypeService implements AccountTypeProtocolInterface{
	private static final Logger log = Logger.getLogger(AccountTypeService.class);
	@Autowired
	private AccountTypeDao accountTypeDao;
	
	public JointAuthenRespDTO jointAuthen(JointAuthenDTO jointAuthen) {
		long begin = System.currentTimeMillis();
		JointAuthenRespDTO jointAuthenRespData = accountTypeDao.jointAuthen(jointAuthen);
		log.info(jointAuthen.toString() +"|jointAuthenRespData= " + jointAuthenRespData + "|time=" + (System.currentTimeMillis() - begin));
		return jointAuthenRespData;
	}

	public UserAuthRespDTO userAuth(UserAuthDTO userAuth) {
		long begin = System.currentTimeMillis();
		UserAuthRespDTO userAuthRespData = accountTypeDao.userAuth(userAuth);
		log.info(userAuth.toString() +"|userAuthRespData= " + userAuthRespData + "|time=" + (System.currentTimeMillis() - begin));
		return userAuthRespData;
	}

	public UserLogoutRespDTO userLogout(UserLogoutDTO userLogout) {
		long begin = System.currentTimeMillis();
		UserLogoutRespDTO userLogoutRespData = accountTypeDao.userLogout(userLogout);
		log.info(userLogout.toString() +"|userLogoutRespData= " + userLogoutRespData + "|time=" + (System.currentTimeMillis() - begin));
		return userLogoutRespData;
	}

}
