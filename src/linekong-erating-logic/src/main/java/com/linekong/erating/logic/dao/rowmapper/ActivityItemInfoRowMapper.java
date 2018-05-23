package com.linekong.erating.logic.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.linekong.erating.api.pojo.resultmapper.ActivityItemInfoDO;
import com.linekong.erating.logic.dao.db.datasource.core.RowMapper;

public class ActivityItemInfoRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rsIndex) throws SQLException {
		ActivityItemInfoDO obj = new ActivityItemInfoDO();
		obj.setActivityId(rs.getLong("activity_id"));
		obj.setActivityDes(rs.getString("activity_desc"));
		obj.setItemCode(rs.getString("item_code"));
		obj.setItemNum(rs.getInt("item_num"));
		obj.setBeginTime(rs.getTimestamp("begin_time"));
		obj.setEndTime(rs.getTimestamp("end_time"));
		return obj;
	}

}
