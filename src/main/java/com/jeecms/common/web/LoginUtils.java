package com.jeecms.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.subject.WebSubject.Builder;

/**
 * Shiro登录工具类
 * @author: andy_hulibo@163.com
 * @date: 2018/11/12 16:31
 */
public class LoginUtils {
	
	public static void loginShiro(HttpServletRequest request,
			HttpServletResponse response,String username){
		PrincipalCollection principals = new SimplePrincipalCollection(username, username);
		Builder builder = new WebSubject.Builder( request,response);  
		builder.principals(principals);  
		builder.authenticated(true);  
		WebSubject subject = builder.buildWebSubject();  
		ThreadContext.bind(subject); 
	}

	/**
	 * 登录--记住我
	 * @author: andy_hulibo@163.com
	 * @date: 2018/11/12 16:32
	 */
	public static void loginShiroRemember(HttpServletRequest request,
			HttpServletResponse response,String username,String password,boolean rememberMe){
		PrincipalCollection principals = new SimplePrincipalCollection(username, username);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);    
		Builder builder = new WebSubject.Builder( request,response);  
		builder.principals(principals);  
		builder.authenticated(true);  
		WebSubject subject = builder.buildWebSubject();  
		subject.login(token);
		ThreadContext.bind(subject); 
	}
	
	public static void logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}
	
	
}
