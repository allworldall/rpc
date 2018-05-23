package com.linekong.erating.logic.dao.db.datasource;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.druid.pool.DruidDataSource;

public class DataSourceConfigureFactory implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	protected DataSource getDataSource() {
		return (DruidDataSource) applicationContext.getBean("dataSource");
	}
	
	protected DataSource getDataSource(String db) {
		return (DruidDataSource) applicationContext.getBean("dataSource_"+db);
	}
	
	
	

}
