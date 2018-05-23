package com.linekong.erating.logic.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.linekong.erating.api.pojo.response.SdkConfigDTO;
import com.linekong.erating.logic.dao.db.datasource.core.RowMapper;

public class SDKConfigInfoRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int rsIndex) throws SQLException {
		SdkConfigDTO sdk = new SdkConfigDTO();
		sdk.setName(rs.getString("name"));
		sdk.setValue(rs.getString("value"));
		return sdk;
	}

}
