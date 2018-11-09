package com.context.front;

import com.jeecms.common.web.springmvc.ExtCookieLocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
*  @Description: 页面访问上下文配置
*  @Author: andy_hulibo@163.com
*  @CreateDate: 2018/11/9 11:20
*/
@Configuration
@ComponentScan(basePackages= {"com.jeecms.bbs.action.front","com.jeecms.bbs.api.front","com.jeecms.plug.live.action.front"},includeFilters=@Filter(type=FilterType.ANNOTATION,value=Controller.class),useDefaultFilters=false)
@EnableWebMvc
public class FrontContextConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private FrontContextInterceptor frontContextInterceptor;

	@Autowired
	private FrontLocaleInterceptor frontLocaleInterceptor;

	@Autowired
	private TokenInterceptor tokenInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(frontContextInterceptor).addPathPatterns("/**");
		registry.addInterceptor(frontLocaleInterceptor).addPathPatterns("/**");
		registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
	}

	@Bean
	public ExtCookieLocaleResolver localeResolver() {
		ExtCookieLocaleResolver resolver=new ExtCookieLocaleResolver();
		resolver.setCookieName("clientlanguage");
		resolver.setCookieMaxAge(-1);
		resolver.setCookieSecure(true);
		resolver.setCookieHttpOnly(true);
		return resolver;
	}
	
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver resolver=new SimpleMappingExceptionResolver();
		
		Properties p=new Properties();
		p.setProperty("org.springframework.web.bind.MissingServletRequestParameterException", "/WEB-INF/error/requiredParameter.html");
		p.setProperty("org.springframework.beans.TypeMismatchException", "/WEB-INF/error/mismatchParameter.html");
		p.setProperty("org.springframework.web.bind.ServletRequestBindingException","/WEB-INF/error/bindException.html");
		resolver.setExceptionMappings(p);
		return resolver;
	}

	@Value("#{'${spring.resource.suffix}'.split(',')}")
	private List<String> suffixs;

	/**
	*  @Description: 手动开启Servlet容器默认的Servlet对静态资源的处理
	*  @Author: andy_hulibo@163.com
	*  @CreateDate: 2018/11/9 14:19
	*/
	@Bean
	public HandlerMapping defaultServletHandlerMapping(ServletContext ctx) {
		DefaultServletHttpRequestHandler handler=new DefaultServletHttpRequestHandler();
		handler.setServletContext(ctx);
		Map<String, HttpRequestHandler> urlMap = new HashMap<>(15);
		for (String suffix:suffixs){
			urlMap.put("/**/"+suffix, handler);
		}
		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		handlerMapping.setOrder(Integer.MIN_VALUE);
		handlerMapping.setUrlMap(urlMap);
		return handlerMapping;
	}
}
