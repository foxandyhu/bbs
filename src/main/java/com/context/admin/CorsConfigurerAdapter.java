package com.context.admin;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jeecms.core.entity.CmsSite;

@Configuration
public class CorsConfigurerAdapter  extends WebMvcConfigurerAdapter {
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration corsRe=registry.addMapping("/**");
        corsRe.allowedHeaders("*").allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS");
        corsRe.allowedOrigins(getUrl());
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminApiInterceptor);
		super.addInterceptors(registry);
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		Session session = sessionFactory.openSession();
		Query query=session.createQuery("select bean from CmsSite bean where id=1");
		query.setMaxResults(1);
		List<CmsSite>sites=query.list();
		if(sites.size()>0){
			setUrl(sites.get(0).getCorsUrl());
		}
		session.close();
	}
	
	private String url="";
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Autowired
	private AdminApiInterceptor adminApiInterceptor;
	@Autowired  
	private SessionFactory sessionFactory; 
    
}