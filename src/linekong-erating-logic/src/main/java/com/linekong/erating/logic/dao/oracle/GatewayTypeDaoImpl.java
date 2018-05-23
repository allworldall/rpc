package com.linekong.erating.logic.dao.oracle;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.linekong.erating.api.code.EratingCodeConstant;
import com.linekong.erating.api.pojo.gateway.GatewayBindDTO;
import com.linekong.erating.api.pojo.gateway.GatewayDataReportDTO;
import com.linekong.erating.api.pojo.gateway.GatewayUnBindDTO;
import com.linekong.erating.logic.dao.GatewayTypeDao;
import com.linekong.erating.logic.dao.db.datasource.DataSourceConfigureFactory;
import com.linekong.erating.logic.dao.db.datasource.core.StoredProcedure;
import com.linekong.erating.logic.exception.LKDBException;
@Repository
public class GatewayTypeDaoImpl extends DataSourceConfigureFactory implements GatewayTypeDao {

	private static final Logger log = Logger.getLogger(GatewayTypeDaoImpl.class);
	/**
	 * 网关绑定
	 */
	public int gatewayBind(GatewayBindDTO gatewayBind) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{ ?=call PKG_GATEWAY.bind(?,?,?,?,?,?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, gatewayBind.getGameId());
		proc.addParameter("gatewayCode", Types.VARCHAR, gatewayBind.getGatewayCode());
		proc.addParameter("password", Types.VARCHAR, gatewayBind.getGatewayPassword());
		proc.addParameter("ipAddress", Types.INTEGER, 0);
		proc.addParameter("mac", Types.VARCHAR, gatewayBind.getMac().toUpperCase());
		proc.addParameter("reconnectFlag", Types.INTEGER, gatewayBind.getReconnectFlag());
		proc.registerOutParameter("zoneId", Types.INTEGER);
		proc.registerOutParameter("gatewayId", Types.INTEGER);
		Map<String, Object> map;
		try {
			map = proc.execute();
		} catch (LKDBException e) {
			log.error(e.toString());
			return EratingCodeConstant.E_SYS_DATABASE_ERROR;
		}
		return (Integer)map.get("result");
	}
	/**
	 * 网关解绑
	 */
	public int gatewayUnBind(GatewayUnBindDTO gatewayUnBind) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{ ?=call PKG_GATEWAY.unbind(?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, gatewayUnBind.getGameId());
		proc.addParameter("gatewayId", Types.INTEGER, gatewayUnBind.getGatewayId());
		proc.registerOutParameter("retConnectId", Types.INTEGER);
		Map<String, Object> map;
		try {
			map = proc.execute();
		} catch (LKDBException e) {
			log.error(e.toString());
			return EratingCodeConstant.E_SYS_DATABASE_ERROR;
		}
		return (Integer)map.get("result");
	}
	/**
	 * 网关数据上报
	 */
	public int gatewayDataReport(GatewayDataReportDTO gatewayDataReport) {
		StoredProcedure proc = new StoredProcedure(this.getDataSource(), "{ ?=call PKG_GATEWAY.GWDataReportAdapt(?,?,?,?,?)}", false);
		proc.registerOutParameter("result", Types.INTEGER);
		proc.addParameter("gameId", Types.INTEGER, gatewayDataReport.getGameId());
		proc.addParameter("gatewayId", Types.INTEGER, gatewayDataReport.getGatewayId());
		proc.addParameter("serverId", Types.INTEGER, gatewayDataReport.getGatewayId());
		proc.addParameter("dataCount", Types.INTEGER, gatewayDataReport.getDataCount());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ;i < gatewayDataReport.getDataType().length; i++) {
			sb.append(String.valueOf(gatewayDataReport.getDataType()[i])).append(EratingCodeConstant.E_STRING_SEPARATOR)
					.append(String.valueOf(gatewayDataReport.getDataValue()[i])).append(EratingCodeConstant.E_STRING_SEPARATOR);
		}
		proc.addParameter("gatewayData", Types.VARCHAR, sb.toString().substring(0, sb.toString().length()-1));
		Map<String, Object> map;
		try {
			map = proc.execute();
		} catch (LKDBException e) {
			log.error(e.toString());
			return EratingCodeConstant.E_SYS_DATABASE_ERROR;
		}
		return (Integer)map.get("result");
	}

}
