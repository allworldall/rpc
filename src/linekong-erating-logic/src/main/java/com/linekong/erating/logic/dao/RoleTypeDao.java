package com.linekong.erating.logic.dao;

import com.linekong.erating.api.pojo.response.CreateRoleRespDTO;
import com.linekong.erating.api.pojo.response.RoleEnterGameRespDTO;
import com.linekong.erating.api.pojo.role.CreateRoleDTO;
import com.linekong.erating.api.pojo.role.RoleEnterGameDTO;

public interface RoleTypeDao {
	
	/**
     * 创建新角色服务
     * @param CreateRoleDTO createRole 请求数据对象
     * @return CreateRoleResp 应答对象
     */
    public CreateRoleRespDTO createRole(CreateRoleDTO createRole);

    /**
     * 角色进入游戏
     * @param RoleEnterGameDTO roleEnterGame 请求数据对象
     * @return RoleEnterGameResp 应答数据对象
     */
    public RoleEnterGameRespDTO roleEnterGame(RoleEnterGameDTO roleEnterGame);
	
	

}
