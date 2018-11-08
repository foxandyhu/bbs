package com.jeecms.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.jeecms.core.security.BbsAuthenticationFilter;
import com.jeecms.core.security.BbsLogoutFilter;
import com.jeecms.core.security.BbsUserFilter;
import org.apache.axis.transport.http.AxisServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.util.IntrospectorCleanupListener;

import java.util.EventListener;

@Configuration
public class ContextConfig{

	@Bean
	public ServletListenerRegistrationBean<EventListener> introspectorCleanupListener(){
		ServletListenerRegistrationBean<EventListener> listener=new ServletListenerRegistrationBean<>(new IntrospectorCleanupListener());
		return listener;
	}

	@Bean
	public FilterRegistrationBean delegatingFilterProxy() {
		FilterRegistrationBean filter=new FilterRegistrationBean();
		filter.setFilter(new DelegatingFilterProxy());
		filter.addUrlPatterns("/*");
		filter.addInitParameter("targetFilterLifecycle","true");
		filter.setName("shiroFilter");
		return filter;
	}
	
	@Bean
	public FilterRegistrationBean openSessionInViewFilter() {
		FilterRegistrationBean filter=new FilterRegistrationBean();
		filter.setFilter(new OpenSessionInViewFilter());
		filter.addUrlPatterns("/*");
		filter.addInitParameter("encoding","UTF-8");
		filter.setName("openSessionInViewFilter");
		return filter;
	}
	
	@Bean
	public ServletRegistrationBean axisServlet(){
		AxisServlet axisServlet=new AxisServlet();
		ServletRegistrationBean registrationBean= new ServletRegistrationBean(axisServlet);
		registrationBean.setLoadOnStartup(2);
		registrationBean.addUrlMappings("/services/*");
		registrationBean.setName("axisServlet");
		return registrationBean;
	}
	
	@Bean
	public ServletRegistrationBean bbsAdminApiServlet(){
		AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();

		context.scan("com.context.admin","com.jeecms.bbs.api.admin","com.jeecms.plug.live.action.admin");

		DispatcherServlet bbsAdminApiServlet=new DispatcherServlet(context);
		ServletRegistrationBean registrationBean= new ServletRegistrationBean(bbsAdminApiServlet);
		registrationBean.setLoadOnStartup(3);
		registrationBean.addUrlMappings("/api/admin/*");
		registrationBean.setName("bbsAdminApiServlet");
		return registrationBean;
	}
	
	@Bean
	public ServletRegistrationBean bbsMemberApiServlet(){
		AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
		context.scan("com.context.member","com.jeecms.bbs.action.member","com.jeecms.bbs.api.member");
		
		DispatcherServlet bbsMemberApiServlet=new DispatcherServlet(context);
		
		ServletRegistrationBean registrationBean= new ServletRegistrationBean(bbsMemberApiServlet);
		registrationBean.setLoadOnStartup(4);
		registrationBean.addUrlMappings("/api/member/*");
		registrationBean.setName("bbsMemberApiServlet");
		return registrationBean;
	}

    @Bean
    public ServletRegistrationBean bbsFrontServlet(){
        AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
        context.scan("com.context.front","com.jeecms.bbs.action.front","com.jeecms.bbs.api.front","com.jeecms.plug.live.action.front");

        DispatcherServlet bbsFrontServlet=new DispatcherServlet(context);
        context.setServletContext(bbsFrontServlet.getServletContext());

        ServletRegistrationBean registrationBean= new ServletRegistrationBean(bbsFrontServlet);
        registrationBean.setLoadOnStartup(5);
        registrationBean.addUrlMappings("/");
        registrationBean.setName("bbsFrontServlet");
        return registrationBean;
    }

	@Bean
	public ServletRegistrationBean druidStatViewServlet(){
       ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
       servletRegistrationBean.addInitParameter("allow","127.0.0.1");
       servletRegistrationBean.addInitParameter("deny","192.168.1.73");
       servletRegistrationBean.addInitParameter("loginUsername","admin");
       servletRegistrationBean.addInitParameter("loginPassword","123456");
       servletRegistrationBean.addInitParameter("resetEnable","false");
       servletRegistrationBean.setLoadOnStartup(6);
       return servletRegistrationBean;
   }
	
	@Bean
	public FilterRegistrationBean bbsUserFilterRegistration(BbsUserFilter userFilter) {
		FilterRegistrationBean filter=new FilterRegistrationBean();
		filter.setFilter(userFilter);
		//不注册到FilterChain中
		filter.setEnabled(false);
		return filter;
	}
	
	@Bean
	public FilterRegistrationBean authcFilterRegistration(BbsAuthenticationFilter authcFilter) {
		FilterRegistrationBean filter=new FilterRegistrationBean(authcFilter);
		filter.setFilter(authcFilter);
		//不注册到FilterChain中
		filter.setEnabled(false);
		return filter;
	}

	@Bean
	public FilterRegistrationBean logoutFilterRegistration(BbsLogoutFilter logoutFilter) {
		FilterRegistrationBean filter=new FilterRegistrationBean(logoutFilter);
		filter.setFilter(logoutFilter);
		//不注册到FilterChain中
		filter.setEnabled(false);
		return filter;
	}
}
