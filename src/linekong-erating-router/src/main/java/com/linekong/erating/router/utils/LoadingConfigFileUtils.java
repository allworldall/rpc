package com.linekong.erating.router.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class LoadingConfigFileUtils {

	public static final Map<String, Object> map = new HashMap<String, Object>();

	private static final Logger log = Logger.getLogger(LoadingConfigFileUtils.class);

	/**
	 * 读取配置文件中的内容
	 * 
	 * @param String
	 *            [] args
	 */
	public static void load(String[] args) {
		FileSystemXmlApplicationContext applicationContext = null;
		// 读取配置文件路径
		String path = args[0];
		System.out.println("loading config file path:" + path);
		// 读取配置文件属性
		Path p = Paths.get(path);
		List<String> list = new ArrayList<String>();
		try {
			list = Files.readAllLines(p, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.err.println("read config file error cause by :" + e.toString());
			System.exit(-1);
		}
		if (list.size() <= 0) {
			System.err.println("loading config file error cause by: content is null in config file");
			System.exit(-1);
			;
		}
		// 读取配置文件中的内容
		for (String str : list) {
			String arr[] = str.split("=");
			map.put(arr[0], arr[1]);
		}
		// 加载log4j配置信息
		if (map.containsKey(BootStrapParamsUtils.LOG4J_PATH)) {
			BootStrapParamsUtils.CONFIG_LOG4J = (String) map.get(BootStrapParamsUtils.LOG4J_PATH);
			PropertyConfigurator.configure(BootStrapParamsUtils.CONFIG_LOG4J);
			log.info("loading log4j sucess ...................");
		} else {
			System.err.println("please check config file log4j properties config");
			System.exit(-1);
		}
		// 加载spring配置信息
		if (map.containsKey(BootStrapParamsUtils.SPRING_PATH)) {
			BootStrapParamsUtils.CONFIG_SPRING = (String) map.get(BootStrapParamsUtils.SPRING_PATH);
			log.info("begin loading spring.....................");
			applicationContext = new FileSystemXmlApplicationContext(BootStrapParamsUtils.CONFIG_SPRING);
			applicationContext.start();
			log.info("loading spring sucess...................");
		}else {
			System.err.println("please check config file spring properties config");
			System.exit(-1);
		}

	}

}
