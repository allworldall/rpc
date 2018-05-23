package com.linekong.erating.logic.dao.oracle;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.linekong.erating.api.code.EratingCodeConstant;
import com.linekong.erating.api.pojo.response.CreateRoleRespDTO;
import com.linekong.erating.api.pojo.response.RoleEnterGameRespDTO;
import com.linekong.erating.api.pojo.resultmapper.RoleEnterGameDO;
import com.linekong.erating.api.pojo.role.CreateRoleDTO;
import com.linekong.erating.api.pojo.role.RoleEnterGameDTO;
import com.linekong.erating.logic.dao.RoleTypeDao;
import com.linekong.erating.logic.dao.db.datasource.DataSourceConfigureFactory;
import com.linekong.erating.logic.dao.db.datasource.core.StoredProcedure;
import com.linekong.erating.logic.exception.LKDBException;
@Repository
public class RoleTypeDaoImpl extends DataSourceConfigureFactory implements RoleTypeDao{

	private static final Logger log = Logger.getLogger(RoleTypeDaoImpl.class);
	/**
	 * 创建角色
	 */
	public CreateRoleRespDTO createRole(CreateRoleDTO createRole) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{ ?=call PKG_ROLE.createRole(?,?,?,?,?,?,?,?,?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, createRole.getGameId());
		proc.addParameter("gatewayId", Types.INTEGER, createRole.getGatewayId());
		proc.addParameter("nationalityId", Types.INTEGER, createRole.getCommunityId());
		proc.addParameter("userId", Types.INTEGER, createRole.getUserId());
		proc.addParameter("roleName", Types.VARCHAR, createRole.getRoleName());
		proc.addParameter("gender", Types.INTEGER, createRole.getRoleGender());
		proc.addParameter("occupation", Types.INTEGER, createRole.getRoleOccupation());
		proc.addParameter("initialLevel", Types.INTEGER, createRole.getInitialLevel());
		proc.addParameter("userIp", Types.INTEGER, createRole.getUserIP());
		proc.addParameter("userPort", Types.INTEGER, createRole.getUserPort());
		proc.registerOutParameter("roleId", Types.INTEGER);
		CreateRoleRespDTO result = new CreateRoleRespDTO();
		try {
			Map<String,Object> map = proc.execute();
			result.setResultCode((Integer)map.get("result"));
			if(result.getResultCode() == EratingCodeConstant.S_SUCCESS) {
				result.setRoleId((Integer) map.get("roleId"));
			}
		} catch (LKDBException e) {
			log.error(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}
		return result;
	}
	/**
	 * 角色进入游戏
	 */
	public RoleEnterGameRespDTO roleEnterGame(RoleEnterGameDTO roleEnterGame) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{?=call PKG_ROLE.enterGameEx5Adapt(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, roleEnterGame.getGameId());
		proc.addParameter("gatewayId", Types.INTEGER, roleEnterGame.getGatewayId());
		proc.addParameter("serverId", Types.INTEGER, roleEnterGame.getServerId());
		proc.addParameter("userId", Types.INTEGER, roleEnterGame.getUserId());
		proc.addParameter("roleId", Types.INTEGER, roleEnterGame.getRoleId());
		proc.addParameter("roleLevel", Types.INTEGER, roleEnterGame.getLevel());
		proc.addParameter("roleGender", Types.INTEGER, roleEnterGame.getGender());
		proc.addParameter("occupationId", Types.INTEGER, roleEnterGame.getOccupationId());
		proc.addParameter("corpsId", Types.INTEGER, roleEnterGame.getCorpsId());
		proc.addParameter("communityId", Types.INTEGER, roleEnterGame.getCommunityId());
		proc.addParameter("clientPort", Types.INTEGER, roleEnterGame.getClientPort());
		proc.addParameter("clientIp", Types.INTEGER, roleEnterGame.getClientIP());
		proc.addParameter("clientType", Types.INTEGER, roleEnterGame.getClientType());
		proc.addParameter("mac", Types.VARCHAR, roleEnterGame.getClientMAC());
		proc.addParameter("hardwareSN1", Types.VARCHAR, roleEnterGame.getHardwareSN1());
		proc.addParameter("hardwareSN2", Types.VARCHAR, roleEnterGame.getHardwareSN2());
		proc.addParameter("uddi", Types.VARCHAR, roleEnterGame.getUddi());
		proc.addParameter("modelVersion", Types.VARCHAR, roleEnterGame.getModelVersion());
		proc.addParameter("ldid", Types.VARCHAR, roleEnterGame.getLdId());
		proc.addParameter("provinceCode", Types.VARCHAR, "11");
		proc.addParameter("cityCode", Types.VARCHAR, "bj");
		proc.addParameter("countryCode", Types.VARCHAR, "");
		proc.addParameter("maxBalanceCount", Types.INTEGER, 1);
//		proc.registerOutParameter("retIsFirstRole", Types.INTEGER);
//		proc.registerOutParameter("retIsRoleFirstLogin", Types.INTEGER);
		proc.registerOutParameter("retBalanceCount", Types.INTEGER);
		proc.registerOutArray("retBalanceInfo", "NUMBER_LIST");
		RoleEnterGameRespDTO result = new RoleEnterGameRespDTO();
		try {
			Map<String,Object> map = proc.execute();
			result.setResultCode((Integer)map.get("result"));
			if(result.getResultCode() == EratingCodeConstant.S_SUCCESS) {
				BigDecimal[] retBalanceInfo = (BigDecimal[]) map.get("retBalanceInfo");
				int count = (Integer) map.get("retBalanceCount");
				List<RoleEnterGameDO> list = new ArrayList<RoleEnterGameDO>();
				for (int i = 0; i < count; i++) {
					int step = 2;
					RoleEnterGameDO info = new RoleEnterGameDO();
					info.setSubjectId(retBalanceInfo[step * i].intValue());
					info.setAmount(retBalanceInfo[step * i + 1].intValue());
					list.add(info);
				}
				result.setList(list);
			}
		} catch (LKDBException e) {
			log.info(e.toString());
			result.setResultCode(EratingCodeConstant.E_SYS_DATABASE_ERROR);
		}
		return result;
	}

}
