package com.linekong.erating.logic.dao.db.datasource.core;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import com.linekong.erating.logic.exception.LKDBException;

import oracle.jdbc.driver.OracleTypes;
import oracle.sql.ArrayDescriptor;
import oracle.sql.ARRAY;

public class StoredProcedure{

	private String sql;

	private boolean autoCommit = false;

	private DataSource dataSource;

	private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

	private List<Map<String,Object>> registerList = new ArrayList<Map<String,Object>>();

	private Map<String,String> commitKeyMap = new HashMap<String,String>();

	private Map<String,Object> paramIndexMap = new HashMap<String,Object>();

	private CallableStatement call;

	private Connection conn;

	private ResultSet rs;

	public StoredProcedure(DataSource dataSource,String sql,boolean autoCommit) {
		this.dataSource = dataSource;
		this.sql = sql;
		this.autoCommit = autoCommit;
	}
	public StoredProcedure(Connection conn,String sql,boolean autoCommit) {
		this.conn = conn;
		this.sql = sql;
		this.autoCommit = autoCommit;
	}
	/**
	 * 存储过程入参
	 */
	public void addParameter(String paramName,int type,Object value) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put(JdbcConstant.paramType, JdbcConstant.inParam);
		param.put(JdbcConstant.paramValue, value);
		list.add(param);
	}
	/**
	 * 添加存储过程出参
	 */
	public void registerOutParameter(String paramName,int value) {
		Map<String,Object> param = new HashMap<String,Object>();
		Map<String,Object> registerMap = new HashMap<String,Object>();
		param.put(JdbcConstant.paramType, JdbcConstant.outParam);
		param.put(JdbcConstant.paramValue, value);
		param.put(JdbcConstant.paramName, paramName);
		registerMap.put(JdbcConstant.paramType, JdbcConstant.outParam);
		registerMap.put(JdbcConstant.paramName, paramName);
		registerList.add(registerMap);
		list.add(param);
	}
	/**
	 * @param String paramName
	 * @param RowMapper rowMapper
	 */
	public void addCursor(String paramName,RowMapper rowMapper) {
		Map<String,Object> param = new HashMap<String,Object>();
		Map<String,Object> registerMap = new HashMap<String,Object>();
		param.put(JdbcConstant.paramType, OracleTypes.CURSOR);
		param.put(JdbcConstant.paramName, paramName);
		registerMap.put(JdbcConstant.paramType, "cursor");
		registerMap.put(JdbcConstant.paramName, paramName);
		registerMap.put(JdbcConstant.rowMapper, rowMapper);
		registerList.add(registerMap);
		list.add(param);
	}
	/**
	 * 传递oracle plsql 自定义数组类型
	 * @param String name
	 * @param String defType oracle 自定义类型
	 * @param
	 */
	public void addArray(String name,String defType,ArrayList<?> data) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put(JdbcConstant.paramType, OracleTypes.ARRAY);
		param.put(JdbcConstant.paramName, defType);
		param.put(JdbcConstant.paramValue, data.toArray());
		list.add(param);
	}
	/**
	 * 获取oracle plsql 自定义数组类型
	 * @param String name
	 * @param String defType oracle 自定义类型
	 * @param
	 */
	public void registerOutArray(String name,String defType) {
		Map<String,Object> param = new HashMap<String,Object>();
		Map<String,Object> registerMap = new HashMap<String,Object>();
		param.put(JdbcConstant.paramType, OracleTypes.ARRAY);
		param.put(JdbcConstant.paramName, name);
		param.put(JdbcConstant.paramValue, JdbcConstant.outParam);
		param.put(JdbcConstant.arrayType, defType);
		registerMap.put(JdbcConstant.paramType, OracleTypes.ARRAY);
		registerMap.put(JdbcConstant.paramName, name);
		registerMap.put(JdbcConstant.paramValue, defType);
		registerList.add(registerMap);
		list.add(param);
	}
	/**
	 * 设置提交key
	 */
	public void addCommitKey(String key) {
		commitKeyMap.put(key, key);
	}
	/**
	 * @return Map<String,Object>
	 * @throws LKDBException
	 */
	public Map<String,Object> execute() throws LKDBException{
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(autoCommit);
			call = conn.prepareCall(sql);
			setParameters(conn,call,list);
			call.execute();
			return dealResult(conn,call,rs);//执行结果集处理
		} catch (SQLException e) {
			throw new LKDBException("execute database error:"+e.toString());
		}  finally {
			try {
				//添加回滚操作
				conn.rollback();
				this.close(conn, call, rs);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new LKDBException("execute close database connection error:"+e.toString());
			}
		}
	}

	/**
	 * @return Map<String,Object>
	 * @throws LKDBException
	 */
	public Map<String,Object> executeForeach() throws LKDBException{
		try {
			conn.setAutoCommit(autoCommit);
			call = conn.prepareCall(sql);
			setParameters(conn,call,list);
			call.execute();
			return dealResultForEach(conn,call,rs);//执行结果集处理
		} catch (SQLException e) {
			throw new LKDBException("execute database error:"+e.toString());
		}  finally {
			try {
				this.close(call, rs);
			} catch (SQLException e) {
				throw new LKDBException("execute close database connection error:"+e.toString());
			}
		}
	}

	private Map<String,Object> dealResult(Connection conn,CallableStatement call,ResultSet rs) throws SQLException{
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int rollback = 1;
		for (int i = 0; i < registerList.size(); i++) {
			Map<String, Object> map = registerList.get(i);
			if(map.get(JdbcConstant.paramType).equals(JdbcConstant.outParam)) {
				if(commitKeyMap.containsKey(map.get(JdbcConstant.paramName))) {
					rollback = i+1;
				}
				returnMap.put((String)map.get(JdbcConstant.paramName),call.getObject((Integer)paramIndexMap.get(map.get(JdbcConstant.paramName))));
				String paramName = map.get(JdbcConstant.paramName).toString();
				if(paramName.equals("result") && (Integer)call.getObject((Integer)paramIndexMap.get(paramName)) != 1){
					break;
				}
			}else if(map.get(JdbcConstant.paramType).equals(OracleTypes.ARRAY)){
				java.sql.Array arr = call.getArray((Integer)paramIndexMap.get(map.get(JdbcConstant.paramName)));

				returnMap.put((String)map.get(JdbcConstant.paramName), arr.getArray());
			}else { //游标类型结果集
				List<Object> list = new ArrayList<Object>();
				rs = (ResultSet) call.getObject((Integer)paramIndexMap.get(map.get(JdbcConstant.paramName)));
				while(rs.next()) {
					RowMapper rowMapper = (RowMapper) map.get(JdbcConstant.rowMapper);
					list.add(rowMapper.mapRow(rs, rs.getRow()));
				}
				returnMap.put((String)map.get(JdbcConstant.paramName), list);
			}
		}
		//设置是否回滚
		commitOrRollback(conn,call.getInt(rollback));
		return returnMap;
	}

	private Map<String,Object> dealResultForEach(Connection conn,CallableStatement call,ResultSet rs) throws SQLException{
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int rollback = 1;
		for (int i = 0; i < registerList.size(); i++) {
			Map<String, Object> map = registerList.get(i);
			if(map.get(JdbcConstant.paramType).equals(JdbcConstant.outParam)) {
				if(commitKeyMap.containsKey(map.get(JdbcConstant.paramName))) {
					rollback = i+1;
				}
				returnMap.put((String)map.get(JdbcConstant.paramName),call.getObject((Integer)paramIndexMap.get(map.get(JdbcConstant.paramName))));
			}else if(map.get(JdbcConstant.paramType).equals(OracleTypes.ARRAY)){
				java.sql.Array arr = call.getArray((Integer)paramIndexMap.get(map.get(JdbcConstant.paramName)));
				returnMap.put((String)map.get(JdbcConstant.paramName), arr.getArray());
			}else { //游标类型结果集
				List<Object> list = new ArrayList<Object>();
				rs = (ResultSet) call.getObject((Integer)paramIndexMap.get(map.get(JdbcConstant.paramName)));
				while(rs.next()) {
					RowMapper rowMapper = (RowMapper) map.get(JdbcConstant.rowMapper);
					list.add(rowMapper.mapRow(rs, rs.getRow()));
				}
				returnMap.put((String)map.get(JdbcConstant.paramName), list);
			}
		}
		//设置是否回滚
		doRollback(conn,call.getInt(rollback));
		return returnMap;
	}

	private void commitOrRollback(Connection conn ,int result) throws SQLException {
		if(result > 0) {
			if(!conn.getAutoCommit()) {
				conn.commit();
			}
		}else {
			if(!conn.getAutoCommit()) {
				conn.rollback();
			}
		}
	}

	/**
	 * 本方法只作回滚操作
	 * @param conn
	 * @param result
	 * @throws SQLException
	 */
	private void doRollback(Connection conn ,int result) throws SQLException {
		if(result > 0) {
			if(!conn.getAutoCommit()) {

			}
		}else {
			if(!conn.getAutoCommit()) {
				conn.rollback();
			}
		}
	}
	/**
	 * @param call
	 * @param parameters
	 * @throws SQLException
	 */
	private void setParameters(Connection conn,CallableStatement call, List<Map<String,Object>> parameters)
			throws SQLException {
		for (int i = 1; i <= list.size(); i++) {
			Map<String,Object> map = parameters.get(i - 1);
			Object paramType = map.get(JdbcConstant.paramType);
			Object paramValue = map.get(JdbcConstant.paramValue);
			if(paramType.equals(OracleTypes.CURSOR)) {
				call.registerOutParameter(i, OracleTypes.CURSOR);
				paramIndexMap.put((String)map.get(JdbcConstant.paramName), i);
			}else if(paramType.equals(JdbcConstant.outParam)) {
				call.registerOutParameter(i, (Integer)paramValue);
				paramIndexMap.put((String)map.get(JdbcConstant.paramName), i);
			}else if(paramType.equals(OracleTypes.ARRAY)){ //自定义数组
			    if(paramValue.equals(JdbcConstant.outParam)) {
			    		call.registerOutParameter(i, OracleTypes.ARRAY,(String)map.get(JdbcConstant.arrayType));
			    	//call.registerOutParameter(i, OracleTypes.PLSQL_INDEX_TABLE);
			    		paramIndexMap.put((String)map.get(JdbcConstant.paramName), i);
			    }else {

					ArrayDescriptor array = ArrayDescriptor.createDescriptor((String)map.get(JdbcConstant.paramName), conn);
					ARRAY arr = new ARRAY(array,conn,paramValue);
					call.setArray(i, arr);
			    }
			}else{
				setInParameter(call,i,paramValue);
			}
		}
	}
	/**
	 * @param CallableStatement call
	 * @param int i
	 * @param Object value
	 * @throws SQLException
	 */
	private void setInParameter(CallableStatement call, int i, Object value) throws SQLException {
		if (value instanceof String) {
			call.setString(i, (String) value);
		} else if (value instanceof Integer) {
			call.setInt(i, ((Integer) value).intValue());
		} else if (value instanceof Long) {
			call.setLong(i, ((Long) value).longValue());
		} else if (value instanceof Double) {
			call.setDouble(i, ((Double) value).doubleValue());
		} else if (value instanceof Float) {
			call.setFloat(i, ((Float) value).floatValue());
		} else if (value instanceof Short) {
			call.setShort(i, ((Short) value).shortValue());
		} else if (value instanceof Byte) {
			call.setByte(i, ((Byte) value).byteValue());
		} else if (value instanceof BigDecimal) {
			call.setBigDecimal(i, (BigDecimal) value);
		} else if (value instanceof Boolean) {
			call.setBoolean(i, ((Boolean) value).booleanValue());
		} else if (value instanceof Timestamp) {
			call.setTimestamp(i, (Timestamp) value);
		} else if (value instanceof java.util.Date) {
			call.setDate(i, new java.sql.Date(((java.util.Date) value).getTime()));
		} else if (value instanceof java.sql.Date) {
			call.setDate(i, (java.sql.Date) value);
		} else if (value instanceof Time) {
			call.setTime(i, (Time) value);
		} else if (value instanceof Blob) {
			call.setBlob(i, (Blob) value);
		} else if (value instanceof Clob) {
			call.setClob(i, (Clob) value);
		} else {
			call.setObject(i, value);
		}
	}
	
	/**
	 * 关闭连接设置
	 * @param Connection conn
	 * @param CallableStatement call
	 * @param ResultSet rs
	 */
	private void close(Connection conn,CallableStatement call, ResultSet rs) throws SQLException {
		if(call != null) {
			call.close();
		}
		if(rs != null) {
			rs.close();
		}
		if(conn != null) {
			conn.close();
		}
	}

	/**
	 * 关闭结果集设置
	 * @param Connection conn
	 * @param CallableStatement call
	 * @param ResultSet rs
	 */
	private void close(CallableStatement call, ResultSet rs) throws SQLException {
		if(call != null) {
			call.close();
		}
		if(rs != null) {
			rs.close();
		}
	}

	/**
	 * 因为上面的commitOrRollback方法是个受保护的方法，因为userCashExchange方法的特殊性，
	 * 则建立此方法，在此方法中调用commitOrRollback方法
	 *@param Connection conn
	 *@param int resultCode    返回结果
	 */
	public void executeCommitOrRollback(Connection conn, int resultCode) throws SQLException {
		commitOrRollback(conn, resultCode);
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public List<Map<String, Object>> getRegisterList() {
		return registerList;
	}

	public void setRegisterList(List<Map<String, Object>> registerList) {
		this.registerList = registerList;
	}

	public Map<String, String> getCommitKeyMap() {
		return commitKeyMap;
	}

	public void setCommitKeyMap(Map<String, String> commitKeyMap) {
		this.commitKeyMap = commitKeyMap;
	}

	public Map<String, Object> getParamIndexMap() {
		return paramIndexMap;
	}

	public void setParamIndexMap(Map<String, Object> paramIndexMap) {
		this.paramIndexMap = paramIndexMap;
	}
}
