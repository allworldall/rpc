package com.linekong.rpc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	/**
	 * 格式化时间时间格式是 yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(getCurrentTime());
	}

}
