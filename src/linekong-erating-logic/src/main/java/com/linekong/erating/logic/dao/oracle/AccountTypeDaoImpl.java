package com.linekong.erating.logic.dao.oracle;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.linekong.erating.api.pojo.resultmapper.UserLogoutInfoDO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.linekong.erating.api.code.EratingCodeConstant;
import com.linekong.erating.api.pojo.account.JointAuthenDTO;
import com.linekong.erating.api.pojo.account.UserAuthDTO;
import com.linekong.erating.api.pojo.account.UserLogoutDTO;
import com.linekong.erating.api.pojo.response.JointAuthenRespDTO;
import com.linekong.erating.api.pojo.response.UserAuthRespDTO;
import com.linekong.erating.api.pojo.response.UserLogoutRespDTO;
import com.linekong.erating.logic.dao.AccountTypeDao;
import com.linekong.erating.logic.dao.db.datasource.DataSourceConfigureFactory;
import com.linekong.erating.logic.dao.db.datasource.core.StoredProcedure;
import com.linekong.erating.logic.exception.LKDBException;
@Repository
public class AccountTypeDaoImpl extends DataSourceConfigureFactory implements AccountTypeDao{

	private static final Logger log = Logger.getLogger(AccountTypeDaoImpl.class);

	public JointAuthenRespDTO jointAuthen(JointAuthenDTO jointAuthen) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{?=call PKG_JOINT.JointAuthenEx(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, jointAuthen.getGameId());
		proc.addParameter("userName", Types.VARCHAR, jointAuthen.getUn());
		proc.addParameter("userPassword", Types.VARCHAR, jointAuthen.getPassword());
		proc.addParameter("gatewayId", Types.INTEGER, jointAuthen.getGatewayId());
		proc.addParameter("userIp", Types.INTEGER, jointAuthen.getUserIP4());
		proc.addParameter("userPort", Types.INTEGER, jointAuthen.getPort());
		proc.addParameter("mac", Types.VARCHAR, jointAuthen.getMac());
		proc.addParameter("adultState", Types.INTEGER, jointAuthen.getAdultState());
		proc.addParameter("idCard", Types.VARCHAR, jointAuthen.getIdCode());
		proc.addParameter("adId", Types.VARCHAR, jointAuthen.getAdId());
		proc.addParameter("hardwareSN1", Types.VARCHAR, "");
		proc.addParameter("hardwareSN2", Types.VARCHAR, "");
		proc.registerOutParameter("retUserId", Types.INTEGER);
		proc.registerOutParameter("retUserType", Types.INTEGER);
		proc.registerOutParameter("retAdultFlag", Types.INTEGER);
		proc.registerOutParameter("retUserClass", Types.INTEGER);
		proc.registerOutParameter("retUserPoint", Types.INTEGER);
		proc.registerOutParameter("retPromoterId", Types.INTEGER);
		proc.registerOutParameter("retUserFlag", Types.INTEGER);
		JointAuthenRespDTO result = new JointAuthenRespDTO();
		try {
			Map<String,Object> map = proc.execute();
			result.setResultCode((Integer)map.get("result"));
			if(result.getResultCode() == EratingCodeConstant.S_SUCCESS) {
				result.setUserId((Integer) (map.get("retUserId") == null ? 0 : map.get("retUserId")));
				result.setUserType((Integer) (map.get("retUserType") == null ? 0 : map.get("retUserType")));
				result.setAdultFlag((Integer) (map.get("retAdultFlag") == null ? 0 : map.get("retAdultFlag")));
				result.setUserClass((Integer) (map.get("retUserClass") == null ? 0 : map.get("retUserClass")));
				result.setUserPoint((Integer) (map.get("retUserPoint") == null ? 0 : map.get("retUserPoint")));
				result.setPromoterId((Integer) (map.get("retPromoterId") == null ? 0 : map.get("retPromoterId")));
				result.setUserFlag((Integer) (map.get("retUserFlag") == null ? 0 : map.get("retUserFlag")));
			}
		} catch (LKDBException e) {
			log.error(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}
		return result;
	}
	
	public UserAuthRespDTO userAuth(UserAuthDTO userAuth) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{ ?=call PKG_USER.authenticateEx3(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, userAuth.getGameId());
		proc.addParameter("gatewayId", Types.VARCHAR, String.valueOf(userAuth.getGatewayId()));
		proc.addParameter("userName", Types.VARCHAR, userAuth.getUserName());
		proc.addParameter("password", Types.VARCHAR, userAuth.getPassword());
		proc.addParameter("passwordType", Types.INTEGER, userAuth.getPasswordType());
		proc.addParameter("userIp", Types.INTEGER, userAuth.getUserIP());
		proc.addParameter("userPort", Types.INTEGER, userAuth.getUserPort());
		proc.addParameter("mac", Types.VARCHAR, userAuth.getMac());
		proc.addParameter("uddi", Types.VARCHAR, userAuth.getUddi());
		proc.addParameter("matrixPassword", Types.VARCHAR, userAuth.getMatrixPassword());
		proc.addParameter("matrixCoord", Types.VARCHAR, userAuth.getMatrixCoord());
		proc.addParameter("adId", Types.VARCHAR, userAuth.getAdId());
		proc.addParameter("hardwareSN1", Types.VARCHAR, userAuth.getHardwareSN1());
		proc.addParameter("hardwareSN2", Types.VARCHAR, userAuth.getHardwareSN2());
		proc.registerOutParameter("retUserId", Types.INTEGER);
		proc.registerOutParameter("retUserType", Types.INTEGER);
		proc.registerOutParameter("retAdultFlag", Types.INTEGER);
		proc.registerOutParameter("retUserClass", Types.INTEGER);
		proc.registerOutParameter("retUserRoleCount", Types.INTEGER);
		proc.registerOutParameter("retUserPoint", Types.INTEGER);
		proc.registerOutParameter("retPromoterId", Types.INTEGER);
		proc.registerOutParameter("retUserFlag", Types.INTEGER);
		UserAuthRespDTO result = new UserAuthRespDTO();
		try {
			Map<String,Object> map = proc.execute();
			result.setResultCode((Integer) map.get("result"));
			if(result.getResultCode() == EratingCodeConstant.S_SUCCESS) {
				result.setUserID((Integer) (map.get("retUserId") == null ? 0 : map.get("retUserId")));
				result.setUserType((Integer) (map.get("retUserType") == null ? 0 : map.get("retUserType")));
				result.setAdultFlag((Integer) (map.get("retAdultFlag") == null ? 0 : map.get("retAdultFlag")));
				result.setUserClass((Integer) (map.get("retUserClass") == null ? 0 : map.get("retUserClass")));
				result.setUserPoint((Integer) (map.get("retUserPoint") == null ? 0 : map.get("retUserPoint")));
				result.setPromoterId((Integer) (map.get("retPromoterId") == null ? 0 : map.get("retPromoterId")));
				result.setUserFlag((Integer) (map.get("retUserFlag") == null ? 0 : map.get("retUserFlag")));
			}
		} catch (LKDBException e) {
			log.error(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}
		return result;
	}

	public UserLogoutRespDTO userLogout(UserLogoutDTO userLogout) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{?=call PKG_ROLE.leaveGameAdapt(?,?,?,?,?,?,?,?,?,?,?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, userLogout.getGameId());
		proc.addParameter("roleId", Types.INTEGER, userLogout.getRoleId());
		proc.addParameter("gatewayId", Types.INTEGER, userLogout.getGatewayId());
		proc.addParameter("logoutFlag", Types.INTEGER, userLogout.getLogoutFlag());
		proc.addParameter("roleLevel", Types.INTEGER, userLogout.getRoleLevel());
		proc.addParameter("communityId", Types.INTEGER, 0);
		proc.addParameter("occupation", Types.INTEGER, userLogout.getRoleOccupation());
		proc.addParameter("money1", Types.INTEGER, userLogout.getMoney1());
		proc.addParameter("money2", Types.INTEGER, userLogout.getMoney2());
		proc.addParameter("experience", Types.BIGINT, userLogout.getExperience());
		proc.addParameter("MaxBalanceCount", Types.INTEGER, 0);
		proc.registerOutParameter("retBalanceCount", Types.INTEGER);
		proc.registerOutArray("retBalanceInfo", "NUMBER_LIST");
		UserLogoutRespDTO result = new UserLogoutRespDTO();
		try {
			Map<String,Object> map = proc.execute();
			result.setResultCode((Integer)(map.get("result")));
			if(result.getResultCode() == EratingCodeConstant.S_SUCCESS) {
				int count = (Integer) map.get("retBalanceCount");
				BigDecimal[] retBalanceInfo = (BigDecimal[]) map.get("retBalanceInfo");
				if (count > 0) {
					List<UserLogoutInfoDO> list = new ArrayList<UserLogoutInfoDO>();
					for (int i = 0; i < count; i++) {
						int step = 2;
						UserLogoutInfoDO info = new UserLogoutInfoDO();
						info.setSubjectId(retBalanceInfo[step * i].intValue());
						info.setAmount(retBalanceInfo[step * i + 1].intValue());
						list.add(info);
					}
					result.setList(list);
				}
			}
		} catch (LKDBException e) {
			log.error(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}
		
		return result;
	}

}
