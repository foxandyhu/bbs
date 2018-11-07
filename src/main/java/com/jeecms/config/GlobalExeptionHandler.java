package com.jeecms.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 * 
 * @author andy
 *
 */
@ControllerAdvice
public class GlobalExeptionHandler {

	@ExceptionHandler({ Throwable.class })
	public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		try {
			response.getWriter().write(ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}