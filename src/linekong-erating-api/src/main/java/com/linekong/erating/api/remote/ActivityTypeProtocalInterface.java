package com.linekong.erating.api.remote;

import com.linekong.erating.api.pojo.activity.ActivityItemQueryDTO;
import com.linekong.erating.api.pojo.activity.UserItemMinusDTO;
import com.linekong.erating.api.pojo.response.ActivityItemQueryRespDTO;

/**
 * 活动类服务
 */
public interface ActivityTypeProtocalInterface {

    /**
     * 活动礼品查询服务
     * @param ActivityItemQueryDTO activityItemQuery 请求数据对象
     * @return ActivityItemQueryRespDTO 应答对象
     */
    public ActivityItemQueryRespDTO activityItemQuery(ActivityItemQueryDTO activityItemQuery);

    /**
     *活动礼品领取服务
     * @param UserItemMinusDTO userItemMinus 请求数据对象
     * @return Integer 应答状态码
     */
    public int userItemMinus(UserItemMinusDTO userItemMinus);
}
