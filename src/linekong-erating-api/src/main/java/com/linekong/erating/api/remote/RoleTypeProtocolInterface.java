package com.linekong.erating.api.remote;

import com.linekong.erating.api.pojo.response.CreateRoleRespDTO;
import com.linekong.erating.api.pojo.response.RoleEnterGameRespDTO;
import com.linekong.erating.api.pojo.role.CreateRoleDTO;
import com.linekong.erating.api.pojo.role.RoleEnterGameDTO;

/**
 * 角色类服务
 */
public interface RoleTypeProtocolInterface {

    /**
     * 创建新角色服务
     * @param CreateRoleDTO createRole 请求数据对象
     * @return CreateRoleRespDTO 应答对象
     */
    public CreateRoleRespDTO createRole(CreateRoleDTO createRole);

    /**
     * 角色进入游戏
     * @param RoleEnterGameDTO roleEnterGame 请求数据对象
     * @return RoleEnterGameRespDTO 应答数据对象
     */
    public RoleEnterGameRespDTO roleEnterGame(RoleEnterGameDTO roleEnterGame);
}
