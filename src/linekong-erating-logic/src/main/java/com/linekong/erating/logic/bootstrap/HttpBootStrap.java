package com.linekong.erating.logic.bootstrap;
import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.Logger;

public class HttpBootStrap {
	
	private static Tomcat tomcat;

	private static StandardServer server;

	private static AprLifecycleListener listener;
	
	private static Logger log = Logger.getLogger(HttpBootStrap.class);
	
	
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		tomcat = new Tomcat();
		tomcat.setHostname("127.0.0.1");
		tomcat.setPort(8080);
		tomcat.setBaseDir(".");
		server = (StandardServer) tomcat.getServer();
		listener = new AprLifecycleListener();
		server.addLifecycleListener(listener);
		tomcat.getHost().setAppBase(System.getProperty("user.dir") + File.separator + ".");
		// tomcat.getHost().setAppBase(System.getProperty("user.dir") +
		// File.separator + "src/main/webapp");
		// 第二个参数对应docBase为web应用路径，目录下应有WEB-INF,WEB-INF下要有web.xml
		// /src/main/webapp
		try {
			// tomcat.addWebapp("", "/Users/fangming/config/webapp");
			//tomcat.addWebapp("", "src/main/webapp");
			tomcat.addWebapp("", "/Users/fangming/config/webapp");
			// 启动tomcat
			tomcat.start();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("tomcat start success using time "+(System.currentTimeMillis() - begin)+" mills");
		// 维持
		tomcat.getServer().await();
	}

}
