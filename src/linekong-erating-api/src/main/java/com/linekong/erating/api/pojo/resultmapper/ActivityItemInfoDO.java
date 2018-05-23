package com.linekong.erating.api.pojo.resultmapper;

import java.sql.Timestamp;

public class ActivityItemInfoDO {
	
	private long activityId;
	
	private String activityDes;
	
	private String itemCode;
	
	private int itemNum; 
	
	private Timestamp beginTime;
	
	private Timestamp endTime;

	@Override
	public String toString() {
		return String.format("activityId=%s|activityDes=%s|itemCode=%s|itemNum=%s|beginTime=%s|endTime=%s",
				activityId, activityDes, itemCode, itemNum, beginTime, endTime);
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public String getActivityDes() {
		return activityDes;
	}

	public void setActivityDes(String activityDes) {
		this.activityDes = activityDes;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
}
