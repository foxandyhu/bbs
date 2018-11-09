package com.jeecms.config;

import java.util.HashMap;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.jeecms.core.security.BbsAdminUrl;
import com.jeecms.core.security.BbsAuthenticationFilter;
import com.jeecms.core.security.BbsAuthorizingRealm;
import com.jeecms.core.security.BbsLogoutFilter;
import com.jeecms.core.security.BbsUserFilter;

/**
*  @Description: Shiro配置
*  @Author: andy_hulibo@163.com
*  @CreateDate: 2018/11/9 16:35
*/
@Configuration
public class ShiroConfig {
	
	@Bean
	public BbsAdminUrl adminUrlBean() {
		BbsAdminUrl admin=new BbsAdminUrl();
		admin.setAdminLogin("/jeeadmin/jeebbs/login.do");
		admin.setAdminPrefix("/jeeadmin/jeebbs/");
		return admin;
	}
	
	@Bean
	public BbsUserFilter userFilter(BbsAdminUrl adminUrlBean) {
		BbsUserFilter user=new BbsUserFilter();
		user.setAdminLogin(adminUrlBean.getAdminLogin());
		user.setAdminPrefix(adminUrlBean.getAdminPrefix());
		return user;
	}
	
	@Bean
	public BbsLogoutFilter logoutFilter(BbsAdminUrl adminUrlBean) {
		BbsLogoutFilter logout=new BbsLogoutFilter();
		logout.setAdminLogin(adminUrlBean.getAdminLogin());
		logout.setAdminPrefix(adminUrlBean.getAdminPrefix());
		return logout;
	}
	
	@Bean
	public BbsAuthenticationFilter authcFilter(BbsAdminUrl adminUrlBean) {
		BbsAuthenticationFilter authc=new BbsAuthenticationFilter();
		authc.setAdminIndex("/jeeadmin/jeebbs/index.do");
		authc.setAdminPrefix(adminUrlBean.getAdminPrefix());
		authc.setRememberMeParam("rememberMe");
		return authc;
	}
	
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(WebSecurityManager securityManager) {
		ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		bean.setLoginUrl("/login.jspx");
		bean.setSuccessUrl("/");
		bean.setFilterChainDefinitionMap(new HashMap<String,String>(){
			private static final long serialVersionUID = -4794995092802667876L;
			{
				put("*.jspx","anon");
				put("*.html","anon");
				put("/member/forgot_password.jspx","anon");
				put("/member/password_reset.jspx","anon");
				put("/login.jspx","authc");
				put("/logout.jspx","logout");
				put("/member/**","user");
			}});
		return bean;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(WebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	@Bean
	public WebSecurityManager securityManager(EhCacheManager cacheManager,RememberMeManager rememberMeManager) {
		DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
		manager.setCacheManager(cacheManager);
		manager.setRememberMeManager(rememberMeManager);
		return manager;
	}
	
	@Bean
	public EhCacheManager shiroEhcacheManager(EhCacheCacheManager cacheManager) {
		EhCacheManager manager=new EhCacheManager();
		manager.setCacheManager(cacheManager.getCacheManager());
		return manager;
	}
	
	@Bean
	public CredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");
		matcher.setStoredCredentialsHexEncoded(true);
		matcher.setHashIterations(1);
		return matcher;
	}
	
	@Bean
	public Cookie rememberMeCookie() {
		Cookie cookie=new SimpleCookie("rememberMe");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(31536000);
		return cookie;
	}
	
	@Bean
	public CookieRememberMeManager rememberMeManager(Cookie cookie) {
		CookieRememberMeManager manager=new CookieRememberMeManager();
		manager.setCookie(cookie);
		manager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		return manager;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		LifecycleBeanPostProcessor lifecycleBeanPostProcessor=new LifecycleBeanPostProcessor();
		return lifecycleBeanPostProcessor;
	}
	
	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		DefaultWebSecurityManager securityManager = context.getBean(DefaultWebSecurityManager.class);
		
		BbsAuthorizingRealm realm=context.getBean(BbsAuthorizingRealm.class);
		BbsAuthenticationFilter authcFilter=context.getBean(BbsAuthenticationFilter.class);
		BbsUserFilter userFilter=context.getBean(BbsUserFilter.class);
		BbsLogoutFilter logoutFilter=context.getBean(BbsLogoutFilter.class);
		
		ShiroFilterFactoryBean shiroFilter=context.getBean(ShiroFilterFactoryBean.class);
		shiroFilter.setFilters(new HashMap<String,Filter>() {
			private static final long serialVersionUID = -8348672305577977754L;
		{
			put("user",userFilter);
			put("logout",logoutFilter);
			put("authc", authcFilter);
		}});
		securityManager.setRealm(realm);
	}
}
