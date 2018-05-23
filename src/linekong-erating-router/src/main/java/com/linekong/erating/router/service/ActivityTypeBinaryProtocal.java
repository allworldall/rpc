package com.linekong.erating.router.service;

import com.linekong.erating.router.cmd.BinaryCMDConstant;
import com.linekong.erating.router.remote.ActivityTypeProtocalRpc;
import com.linekong.rpc.net.common.annotation.LKBinaryService;
import com.linekong.rpc.net.common.annotation.LKCMDService;
import com.linekong.rpc.net.common.codec.binary.PDUMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动相关
 */
@LKBinaryService(ActivityTypeBinaryProtocal.class)
@Service
public class ActivityTypeBinaryProtocal {

    @Autowired
    private ActivityTypeProtocalRpc activity;

    /**
     * 活动物品查询
     * @param PDUMessage msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_ACTIVITY_ITEM_QUERY_EX)
    public PDUMessage activityItemQuery(PDUMessage msg) {
        return activity.activityItemQuery(msg);
    }

    /**
     * 活动礼品领取
     * @param msg
     * @return
     */
    @LKCMDService(BinaryCMDConstant.CMD_USER_ITEM_MINUS_EX)
    public PDUMessage userItemMinus(PDUMessage msg) {
        return activity.userItemMinus(msg);
    }
}
