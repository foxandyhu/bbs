package com.jeecms.common.web.springmvc;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 数据绑定初始化类
 * 
 * @author tom
 * 
 */
public class BindingInitializer implements WebBindingInitializer {
	/**
	 * 初始化数据绑定
	 */
	@Override
	public void initBinder(WebDataBinder binder, WebRequest arg1) {
		binder.registerCustomEditor(Date.class, new DateTypeEditor());
	}
}
