package com.linekong.rpc.log;

import org.apache.log4j.Logger;
/**
 * 日志记录公用方法
 */
public class LoggerUtils {
	
	private static Logger log = null;
	/**
	 * 正常消息记录
	 * @param Class<?> clazz
	 *                 日志产生类
	 * @param String msg
	 *                 产生的日志消息
	 */
	public static void info(Class<?> clazz,String msg) {
		log = Logger.getLogger(clazz);
		log.info(msg);
	}
	/**
	 * 异常或者错误信息记录
	 * @param Class<?> clazz
	 *                 日志产生类
	 * @param String msg
	 *                 产生的日志消息
	 */
	public static void error(Class<?> clazz,String msg) {
		log = Logger.getLogger(clazz);
		log.error(msg);
	}
	/**
	 * 记录debug消息
	 */
	public static void debug(Class<?> clazz,String msg) {
		log = Logger.getLogger(clazz);
		log.debug(msg);
	}


}
