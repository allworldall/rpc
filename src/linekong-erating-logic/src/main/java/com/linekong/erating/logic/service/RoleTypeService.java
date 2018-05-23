package com.linekong.erating.logic.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linekong.erating.api.pojo.response.CreateRoleRespDTO;
import com.linekong.erating.api.pojo.response.RoleEnterGameRespDTO;
import com.linekong.erating.api.pojo.role.CreateRoleDTO;
import com.linekong.erating.api.pojo.role.RoleEnterGameDTO;
import com.linekong.erating.api.remote.RoleTypeProtocolInterface;
import com.linekong.erating.logic.dao.RoleTypeDao;
import com.linekong.rpc.net.common.annotation.LKRpcService;
@LKRpcService(RoleTypeProtocolInterface.class)
@Service
public class RoleTypeService implements RoleTypeProtocolInterface{

	private static final Logger log = Logger.getLogger(RoleTypeService.class);
	@Autowired
	private RoleTypeDao roleTypeDao;
	
	public CreateRoleRespDTO createRole(CreateRoleDTO createRole) {
		long begin = System.currentTimeMillis();
		CreateRoleRespDTO createRoleRespData = roleTypeDao.createRole(createRole);
		log.info(createRole.toString() +"|createRoleRespData= " + createRoleRespData + "|time=" + (System.currentTimeMillis() - begin));
		return createRoleRespData;
	}

	public RoleEnterGameRespDTO roleEnterGame(RoleEnterGameDTO roleEnterGame) {
		long begin = System.currentTimeMillis();
		RoleEnterGameRespDTO roleEnterGameRespData = roleTypeDao.roleEnterGame(roleEnterGame);
		log.info(roleEnterGame.toString() +"|roleEnterGameRespData= " + roleEnterGameRespData + "|time=" + (System.currentTimeMillis() - begin));
		return roleEnterGameRespData;
	}
}
