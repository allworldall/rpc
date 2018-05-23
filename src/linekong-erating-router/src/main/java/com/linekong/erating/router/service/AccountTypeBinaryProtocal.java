package com.linekong.erating.router.service;

import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.remote.AccountTypeProtocalRpc;
import com.linekong.rpc.net.common.annotation.LKBinaryService;
import com.linekong.rpc.net.common.annotation.LKCMDService;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账号相关
 */
@LKBinaryService(AccountTypeBinaryProtocal.class)
@Service
public class AccountTypeBinaryProtocal {

    @Autowired
    private AccountTypeProtocalRpc account;

    @LKCMDService(BinaryCMDConstant.CMD_JOINT_AUTHEN_EX)
    public PDUMessage jointAuthen(PDUMessage msg) {
        return account.jointAuthen(msg);
    }

    /**
     * 账号认证
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_USER_AUTH_EX4)
    public PDUMessage userAuth(PDUMessage msg) {
        return account.userAuth(msg);

    }


    /**
     * 玩家登出
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_USER_LOGOUT)
    public PDUMessage userLogout(PDUMessage msg) {
        return account.userLogout(msg);
    }
}
