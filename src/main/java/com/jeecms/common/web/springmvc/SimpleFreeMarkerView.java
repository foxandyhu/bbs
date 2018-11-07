package com.jeecms.common.web.springmvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 轻量级的FreeemarkerView
 * 
 * 不支持jsp标签、不支持request、session、application等对象，可用于前台模板页面。
 * 
 * @author tom
 * 
 */
public class SimpleFreeMarkerView extends FreeMarkerView {
	/**
	 * 部署路径调用名称
	 */
	public static final String CONTEXT_PATH = "base";

	@Override
	protected void renderMergedTemplateModel(Map<String,Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		model.put(CONTEXT_PATH, request.getContextPath());
		super.renderMergedTemplateModel(model, request, response);
	}
}
