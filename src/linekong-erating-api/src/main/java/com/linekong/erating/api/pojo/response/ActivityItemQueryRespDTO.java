package com.linekong.erating.api.pojo.response;

import java.util.*;

import com.linekong.erating.api.pojo.resultmapper.ActivityItemInfoDO;

/**
 * 活动礼品查询,应答数据对象
 * CMD_ACTIVITY_ITEM_QUERY_EX_RES 0x20003512
 * ①以下将字段5～字段8称为“物品信息”，字段2～字段8称为“活动信息”。
 * ②本协议包是双层变长包，一个活动信息内可能有多个物品信息（以字段4表示物品信息在此活动信息下的重复次数），一个应答包内可能有多个活动信息（以字段1表示活动信息重复次数）。
 * ③在不同的活动信息内，物品信息的重复次数可能不一样
 */
public class ActivityItemQueryRespDTO {

    private int resultCode;        //返回结果

    private int activityCount;     //表示后续的活动个数
    
    private List<ActivityItemInfoDO> list;
    
    @Override
    public String toString() {
        return String.format("resultCode=%s|activityCount=%s|activityItemInfo=%s",
                resultCode,activityCount,list==null? "" : list.toString());
    }

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public int getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}

	public List<ActivityItemInfoDO> getList() {
		return list;
	}

	public void setList(List<ActivityItemInfoDO> list) {
		this.list = list;
	}
}
