package com.jeecms.core.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 执行时间过滤器
 * 
 * @author tom
 * 
 */
@WebFilter(filterName="processTime",urlPatterns={"*.html"})
public class ProcessTimeFilter implements Filter {
	protected final Logger log = LoggerFactory
			.getLogger(ProcessTimeFilter.class);
	/**
	 * 请求执行开始时间
	 */
	public static final String START_TIME = "_start_time";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		long time = System.currentTimeMillis();
		request.setAttribute(START_TIME, time);
		chain.doFilter(request, response);
		time = System.currentTimeMillis() - time;
		log.debug("process in {} ms: {}", time, request.getRequestURI());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
