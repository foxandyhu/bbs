package com.jeecms.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.jeecms.common.hibernate4.TreeIntercptor;
import com.jeecms.common.image.ImageScaleImpl;
import com.jeecms.common.web.springmvc.SimpleFreeMarkerView;

import freemarker.template.SimpleHash;

@Configuration
@EnableTransactionManagement
@EnableCaching
public class ApplicationConfig {
	
	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
	
	@Bean
	public TreeIntercptor treeInterceptor() {
		TreeIntercptor tor=new TreeIntercptor();
		return tor;
	}
	
	@Bean
    public HibernateTransactionManager txManager(SessionFactory sessionFactory) throws SQLException {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

	@Bean(initMethod="init")
	public ImageScaleImpl imageScale() {
		ImageScaleImpl image=new ImageScaleImpl();
		image.setTryMagick(false);
		return image;
	}
	
	@Bean
	public CommandLineRunner customFreemarkerView(FreeMarkerViewResolver resolver) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				resolver.setViewClass(SimpleFreeMarkerView.class);	//设置Freemarker解析视图
			}
		};
	}
	
	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) throws Exception {
		ApplicationContext context = event.getApplicationContext();
		
		DirectiveConfig directiveConfig=context.getBean(DirectiveConfig.class);
		Map<String,String> directives=directiveConfig.getMap();
		FreeMarkerConfigurer config=context.getBean(FreeMarkerConfigurer.class);
		Map<String,Object> map =new HashMap<String,Object>() {
			private static final long serialVersionUID = 723383891389861471L;
			{
				for (String key:directives.keySet()) {
					Object obj=context.getBean(directives.get(key));
					put(key,obj);
				}
			}};
		config.getConfiguration().setAllSharedVariables(new SimpleHash(map, config.getConfiguration().getObjectWrapper()));
	}
}
