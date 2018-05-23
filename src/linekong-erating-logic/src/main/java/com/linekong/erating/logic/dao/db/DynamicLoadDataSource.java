package com.linekong.erating.logic.dao.db;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 动态加载spring配置的数据源
 */
public class DynamicLoadDataSource implements ApplicationContextAware {

	private ConfigurableApplicationContext applicationContext = null;

	/**
	 * 设置spring应用上下文
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}
	public ConfigurableApplicationContext getApplicationContext() {
		return applicationContext;
	}
	/**
	 * 判断是否已经加载了此bean对象
	 * @param bean 名称id
	 */
	public boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}
	/**
	 * 向spring的beanFactory动态地装载bean 
     * @param configLocationString 要装载的bean所在的xml配置文件位置
	 */
	public void loadBean(String configLocationString) {
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)getApplicationContext().getBeanFactory());  
        beanDefinitionReader.setResourceLoader(getApplicationContext());  
        beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(getApplicationContext()));
        try {
			beanDefinitionReader.loadBeanDefinitions(getApplicationContext().getResources(configLocationString));
		} catch (BeanDefinitionStoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * bean 注册
	 */
	public void registerBean(String beanName,String parentName) {
		DefaultListableBeanFactory bean = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
		BeanDefinition beanDefinition = new ChildBeanDefinition(parentName);
		bean.registerBeanDefinition(beanName, beanDefinition);
	}
}
