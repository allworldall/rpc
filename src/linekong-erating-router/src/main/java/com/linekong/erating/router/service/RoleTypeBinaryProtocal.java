package com.linekong.erating.router.service;

import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.remote.RoleTypeProtocalRpc;
import com.linekong.rpc.net.common.annotation.LKBinaryService;
import com.linekong.rpc.net.common.annotation.LKCMDService;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色相关
 */
@LKBinaryService(RoleTypeBinaryProtocal.class)
@Service
public class RoleTypeBinaryProtocal {

    @Autowired
    private RoleTypeProtocalRpc role;

    /**
     * 创建新角色
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_CREATE_ROLE)
    public PDUMessage createRole(PDUMessage msg) {
        return role.createRole(msg);
    }

    /**
     * 角色进入游戏
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_ROLE_ENTER_GAME_EX5)
    public PDUMessage roleEnterGame(PDUMessage msg) {
        return role.roleEnterGame(msg);
    }
}
