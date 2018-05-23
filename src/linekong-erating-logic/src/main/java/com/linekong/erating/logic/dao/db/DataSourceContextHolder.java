package com.linekong.erating.logic.dao.db;

public class DataSourceContextHolder {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setDataSource(String dataSource) {  
        contextHolder.set(dataSource);  
	}  

	public static String getDataSource() {  
        return ((String) contextHolder.get());  
	}  

	public static void removeDataSource() {  
        contextHolder.remove();
	}

}
