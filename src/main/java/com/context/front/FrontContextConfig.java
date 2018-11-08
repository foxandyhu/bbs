package com.context.front;

import java.util.Properties;

import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.jeecms.common.web.springmvc.ExtCookieLocaleResolver;

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

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();        //启用容器默认的servlet 过滤静态资源
    }
}
