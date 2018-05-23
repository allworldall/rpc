package com.linekong.erating.router.bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.linekong.erating.router.utils.LoadingConfigFileUtils;

public class BootStrap {
	
	private static ClassPathXmlApplicationContext application = null;

	public static void main(String[] args) {
		if(args.length == 0) {
			System.err.println("没有指定配置文件的路径，请指定配置文件路径！");
			System.exit(-1);
		}else {
			if(args[0].equals("develop")) {
				//PropertyConfigurator.configure("classpath*:/log4j.properties");
				application = new ClassPathXmlApplicationContext("classpath*:/spring-server.xml");
				application.start();
			}else {
				LoadingConfigFileUtils.load(args);
			}
		}
		synchronized (BootStrap.class) {
			while (true) {
				try {
					BootStrap.class.wait();
				} catch (InterruptedException e) {
					System.out.println("system InterruptedException:" + e.toString());
					System.exit(-1);
				}
			}
		}
	}

}
