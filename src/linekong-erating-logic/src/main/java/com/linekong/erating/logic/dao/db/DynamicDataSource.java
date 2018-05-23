package com.linekong.erating.logic.dao.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSource();
	}

}
