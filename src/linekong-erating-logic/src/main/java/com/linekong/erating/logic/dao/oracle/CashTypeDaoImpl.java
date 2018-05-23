package com.linekong.erating.logic.dao.oracle;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import com.linekong.erating.api.pojo.resultmapper.UserBalanceInfoDO;
import com.linekong.erating.api.pojo.resultmapper.UserCashExchangeDO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.linekong.erating.api.code.EratingCodeConstant;
import com.linekong.erating.api.pojo.cash.UserAddFreeMoneyDTO;
import com.linekong.erating.api.pojo.cash.UserBalanceInfoDTO;
import com.linekong.erating.api.pojo.cash.UserCashExchangeDTO;
import com.linekong.erating.api.pojo.cash.UserChargeDTO;
import com.linekong.erating.api.pojo.cash.UserIBPayDTO;
import com.linekong.erating.api.pojo.response.UserAddFreeMoneyRespDTO;
import com.linekong.erating.api.pojo.response.UserBalanceInfoRespDTO;
import com.linekong.erating.api.pojo.response.UserCashExchangeRespDTO;
import com.linekong.erating.api.pojo.response.UserIBPayRespDTO;
import com.linekong.erating.logic.dao.CashTypeDao;
import com.linekong.erating.logic.dao.db.datasource.DataSourceConfigureFactory;
import com.linekong.erating.logic.dao.db.datasource.core.StoredProcedure;
import com.linekong.erating.logic.exception.LKDBException;
@Repository
public class CashTypeDaoImpl extends DataSourceConfigureFactory implements CashTypeDao {
	
	private static final Logger log = Logger.getLogger(CashTypeDaoImpl.class);

	public UserAddFreeMoneyRespDTO userAddFreeMoney(UserAddFreeMoneyDTO userAddFreeMoney) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{?=call PKG_BALANCE.userAddFreeMoney(?,?,?,?,?,?,?,?,?,?)}", false) ;
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, userAddFreeMoney.getGameId());
		proc.addParameter("gatewayId", Types.INTEGER, userAddFreeMoney.getGatewayId());
		proc.addParameter("detailId", Types.BIGINT, userAddFreeMoney.getDetailId());
		proc.addParameter("userId", Types.INTEGER, userAddFreeMoney.getUserId());
		proc.addParameter("roleId", Types.INTEGER, userAddFreeMoney.getRoleId());
		proc.addParameter("subjectId", Types.INTEGER, userAddFreeMoney.getSubjectId());
		proc.addParameter("amount", Types.INTEGER, userAddFreeMoney.getAmount());
		proc.addParameter("addTime", Types.DATE, new Date(userAddFreeMoney.getAddTime()));
		proc.addParameter("source", Types.VARCHAR, userAddFreeMoney.getSource());
		proc.registerOutParameter("balance", Types.INTEGER);
 		UserAddFreeMoneyRespDTO result = new UserAddFreeMoneyRespDTO();
		try {
			Map<String,Object> map = proc.execute();
			result.setResultCode((Integer)map.get("result"));
			if(result.getResultCode() == EratingCodeConstant.S_SUCCESS) {
				result.setBalance((Integer) map.get("balance"));
			}
		} catch (LKDBException e) {
			log.error(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}
		return result;
	}

	public UserBalanceInfoRespDTO userBalanceInfo(UserBalanceInfoDTO userBalanceInfo) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{?=call PKG_BALANCE.getBalanceInfoEx1Adapt(?,?,?,?,?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, userBalanceInfo.getGameId());
		proc.addParameter("userId", Types.INTEGER, userBalanceInfo.getUserId());
		proc.addParameter("roleId", Types.INTEGER, userBalanceInfo.getRoleId());
		proc.addParameter("ratingId", Types.INTEGER, userBalanceInfo.getRatingId());
		proc.addParameter("maxBalanceCount", Types.INTEGER, 10000);
		proc.registerOutParameter("retBalanceCount", Types.INTEGER);
		proc.registerOutArray("retBalanceInfo", "NUMBER_LIST");
		UserBalanceInfoRespDTO result = new UserBalanceInfoRespDTO();
		try {
			Map<String,Object> map = proc.execute();
			result.setResultCode((Integer)map.get("result"));
			if(result.getResultCode() == EratingCodeConstant.S_SUCCESS) {
				int count = (Integer) map.get("retBalanceCount");
				BigDecimal[] retBalanceInfo = (BigDecimal[]) map.get("retBalanceInfo");
				List<UserBalanceInfoDO> list = new ArrayList<UserBalanceInfoDO>();
				for (int i = 0; i < count; i++) {
					int step = 3;
					UserBalanceInfoDO info = new UserBalanceInfoDO();
					info.setSubjectId(retBalanceInfo[step * i].intValue());
					info.setTotalCharge(retBalanceInfo[step * i + 1].intValue());
					info.setBalance(retBalanceInfo[step * i + 2].intValue());
					list.add(info);
				}
				result.setList(list);
			}
		} catch (LKDBException e) {
			log.error(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}
		return result;
	}

	public UserCashExchangeRespDTO userCashExchange(UserCashExchangeDTO userCashExchange) {
		UserCashExchangeRespDTO result = new UserCashExchangeRespDTO();
		List<UserCashExchangeDO> list = new ArrayList<UserCashExchangeDO>();
		Connection conn = null;
		try {
			conn = this.getDataSource().getConnection();
			StoredProcedure proc = new StoredProcedure(conn, "{?=call PKG_EXCHANGE.userCashExchangeEx4(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", false) ;
			for(int i=0 ; i < userCashExchange.getSubjectCount(); i++) {
				proc.registerOutParameter("result", Types.INTEGER);
				proc.addParameter("gameId", Types.INTEGER, userCashExchange.getGameId());
				proc.addParameter("gatewayId", Types.BIGINT, userCashExchange.getGatewayId());
				proc.addParameter("detailId", Types.BIGINT, userCashExchange.getDetailId());
				proc.addParameter("srcRoleId", Types.INTEGER, userCashExchange.getSrcRoleId());
				proc.addParameter("dstRoleId", Types.INTEGER, userCashExchange.getDstRoleId());
				proc.addParameter("exchangeTime", Types.DATE, new Date(userCashExchange.getExchangeTime()));
				proc.addParameter("type", Types.INTEGER, userCashExchange.getType());
				proc.addParameter("srcGameId", Types.INTEGER, userCashExchange.getSrcGameId());
				proc.addParameter("dstGameId", Types.INTEGER, userCashExchange.getDstGameId());
				proc.addParameter("itemCode", Types.VARCHAR, userCashExchange.getItemCode());
				proc.addParameter("itemNum", Types.INTEGER, userCashExchange.getItemNum());
				proc.addParameter("subjectId", Types.INTEGER, userCashExchange.getSubjectId()[i]);
				proc.addParameter("amount", Types.INTEGER, userCashExchange.getSubAmount()[i]);
				proc.registerOutParameter("srcBalance", Types.INTEGER);
				proc.registerOutParameter("dstBalance", Types.INTEGER);
				//注意，执行方法只用判断失败的时候回滚，成功的不要提交，因为后续的循环可能失败需要全部回滚
				Map<String, Object> map = proc.executeForeach();
				int resultCode = (Integer) map.get("result");
				result.setResultCode(resultCode);
				if(resultCode < 1) {
					break;
				}
				UserCashExchangeDO info = new UserCashExchangeDO();
				info.setSubjectId(userCashExchange.getSubjectId()[i]);
				info.setSrcBalance((Integer)map.get("srcBalance"));
				info.setDstBalance((Integer)map.get("dstBalance"));
				list.add(info);
				//如果执行到最后一次循环，成功返回了，则需要进行提交操作
				if(i == userCashExchange.getSubjectCount()-1) {
					proc.executeCommitOrRollback(conn, resultCode);
					break;
				}
				//每次循环结束需要把调用存储过程的参数映射全部清除
				proc.setList(new ArrayList<Map<String,Object>>());
				proc.setRegisterList(new ArrayList<Map<String,Object>>());
				proc.setCommitKeyMap(new HashMap<String,String>());
				proc.setParamIndexMap(new HashMap<String,Object>());
			}
			result.setList(list);

		} catch (LKDBException e) {
			log.error(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}catch (Exception e) {
			log.error(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (final SQLException e) {
					log.error(e.toString());
				}
			}
		}
		return result;

	}

	public int userCharge(UserChargeDTO userCharge) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{}", false) ;
		return 0;
	}

	public UserIBPayRespDTO userIBPay(UserIBPayDTO userIBPay) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{}", false) ;
		return null;
	}

}
