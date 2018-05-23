package com.linekong.erating.logic.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linekong.erating.api.pojo.activity.ActivityItemQueryDTO;
import com.linekong.erating.api.pojo.activity.UserItemMinusDTO;
import com.linekong.erating.api.pojo.response.ActivityItemQueryRespDTO;
import com.linekong.erating.api.remote.ActivityTypeProtocalInterface;
import com.linekong.erating.logic.dao.ActivityTypeDao;
import com.linekong.rpc.net.common.annotation.LKRpcService;
@LKRpcService(ActivityTypeProtocalInterface.class)
@Service
public class ActivityTypeService implements ActivityTypeProtocalInterface {

	private static final Logger log = Logger.getLogger(ActivityTypeService.class);
	@Autowired
	private ActivityTypeDao activityTypeDao;
	
	public ActivityItemQueryRespDTO activityItemQuery(ActivityItemQueryDTO activityItemQuery) {
		long begin = System.currentTimeMillis();
		ActivityItemQueryRespDTO activityItemQueryRespData = activityTypeDao.activityItemQuery(activityItemQuery);
		log.info(activityItemQuery.toString() +"|activityItemQueryRespData= " + activityItemQueryRespData + "|time=" + (System.currentTimeMillis() - begin));
		return activityItemQueryRespData;
	}

	public int userItemMinus(UserItemMinusDTO userItemMinus) {
		long begin = System.currentTimeMillis();
		int userItemMinusReapData = activityTypeDao.userItemMinus(userItemMinus);
		log.info(userItemMinus.toString() +"|userItemMinusReapData= " + userItemMinusReapData + "|time=" + (System.currentTimeMillis() - begin));
		return userItemMinusReapData;
	}

}
