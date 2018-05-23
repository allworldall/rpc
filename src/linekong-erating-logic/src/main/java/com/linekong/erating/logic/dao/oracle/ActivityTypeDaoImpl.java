package com.linekong.erating.logic.dao.oracle;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.linekong.erating.api.code.EratingCodeConstant;
import com.linekong.erating.api.pojo.activity.ActivityItemQueryDTO;
import com.linekong.erating.api.pojo.activity.UserItemMinusDTO;
import com.linekong.erating.api.pojo.response.ActivityItemQueryRespDTO;
import com.linekong.erating.api.pojo.resultmapper.ActivityItemInfoDO;
import com.linekong.erating.logic.dao.ActivityTypeDao;
import com.linekong.erating.logic.dao.db.datasource.DataSourceConfigureFactory;
import com.linekong.erating.logic.dao.db.datasource.core.StoredProcedure;
import com.linekong.erating.logic.dao.rowmapper.ActivityItemInfoRowMapper;
import com.linekong.erating.logic.exception.LKDBException;
@Repository
public class ActivityTypeDaoImpl extends DataSourceConfigureFactory implements ActivityTypeDao{
	
	private static final Logger log = Logger.getLogger(ActivityTypeDaoImpl.class);

	@SuppressWarnings("unchecked")
	public ActivityItemQueryRespDTO activityItemQuery(ActivityItemQueryDTO activityItemQuery) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{?=call PKG_ITEM.itemListEx(?,?,?,?,?,?,?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, activityItemQuery.getGameId());
		proc.addParameter("gatewayId", Types.INTEGER, activityItemQuery.getGatewayId());
		proc.addParameter("activityId", Types.BIGINT, activityItemQuery.getActivityId());
		proc.addParameter("userId", Types.INTEGER, activityItemQuery.getUserId());
		proc.addParameter("roleId", Types.INTEGER, activityItemQuery.getRoleId());
		proc.addParameter("roleLevel", Types.INTEGER, activityItemQuery.getRoleLevel());
		proc.addParameter("maxItemCount", Types.INTEGER, 10000);
		proc.registerOutParameter("retItemCount", Types.INTEGER);
		proc.addCursor("curActivityItemInfo", new ActivityItemInfoRowMapper());
		ActivityItemQueryRespDTO result = new ActivityItemQueryRespDTO();
		try {
			Map<String,Object> map = proc.execute();
			result.setResultCode((Integer)map.get("result"));
			if(result.getResultCode() == EratingCodeConstant.S_SUCCESS) {
				result.setActivityCount((Integer) map.get("retItemCount"));
				result.setList((List<ActivityItemInfoDO>) map.get("curActivityItemInfo"));
			}
		} catch (LKDBException e) {
			log.error(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}
		return result;
	}

	public int userItemMinus(UserItemMinusDTO userItemMinus) {
		Connection conn = null;
		int result =0;
		try {
			conn = this.getDataSource().getConnection();
			StoredProcedure proc = new StoredProcedure(conn, "{?=call PKG_ITEM.itemMinus(?,?,?,?,?,?,?,?,?)}", false);
			int length = userItemMinus.getActivityId().length;
			for(int i = 0; i<length; i++) {
				proc.registerOutParameter("result", Types.INTEGER);
				proc.addParameter("gameId", Types.INTEGER, userItemMinus.getGameId());
				proc.addParameter("gatewayId", Types.INTEGER, userItemMinus.getGatewayId());
				proc.addParameter("itemGatewayId", Types.INTEGER, userItemMinus.getGatewayId());
				proc.addParameter("activityId", Types.INTEGER, userItemMinus.getActivityId()[i]);
				proc.addParameter("userId", Types.INTEGER, userItemMinus.getUserId());
				proc.addParameter("roleId", Types.INTEGER, userItemMinus.getRoleId());
				proc.addParameter("itemCode", Types.VARCHAR, userItemMinus.getItemCode()[i]);
				proc.addParameter("itemNum", Types.INTEGER, userItemMinus.getItemNum()[i]);
				proc.addParameter("orderCode", Types.VARCHAR, 0);
				
				//注意，执行方法只用判断失败的时候回滚，成功的不要提交，因为后续的循环可能失败需要全部回滚
				Map<String, Object> map = proc.executeForeach();
				int resultCode = (Integer) map.get("result");
				result=resultCode;
				if(resultCode < 1) {
					break;
				}
				//如果执行到最后一次循环，成功返回了，则需要进行提交操作
				if(i == length-1) {
					proc.executeCommitOrRollback(conn, resultCode);
					break;
				}
				//每次循环结束需要把调用存储过程的参数映射全部清除
				proc.setList(new ArrayList<Map<String,Object>>());
				proc.setRegisterList(new ArrayList<Map<String,Object>>());
				proc.setCommitKeyMap(new HashMap<String,String>());
				proc.setParamIndexMap(new HashMap<String,Object>());
			}
			return result;
			
		} catch (Exception e) {
			log.error(e.toString());
			return EratingCodeConstant.E_SYS_DATABASE_ERROR;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					log.error(e.toString());
				}
			}
		}
	}

}
