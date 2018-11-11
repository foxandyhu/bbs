package com.jeecms.bbs.action;

import static com.jeecms.common.page.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.bbs.entity.BbsWebservice;
import com.jeecms.bbs.manager.BbsWebserviceMng;

@Controller
public class BbsWebserviceAct {
	private static final Logger log = LoggerFactory.getLogger(BbsWebserviceAct.class);

	@RequiresPermissions("webservice:v_list")
	@RequestMapping("/webservice/v_list.html")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		return "webservice/list";
	}

	@RequiresPermissions("webservice:v_add")
	@RequestMapping("/webservice/v_add.html")
	public String add(ModelMap model) {
		return "webservice/add";
	}

	@RequiresPermissions("webservice:v_edit")
	@RequestMapping("/webservice/v_edit.html")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		model.addAttribute("bbsWebservice", manager.findById(id));
		return "webservice/edit";
	}

	@RequiresPermissions("webservice:o_save")
	@RequestMapping("/webservice/o_save.html")
	public String save(BbsWebservice bean, String[] paramName, String[] defaultValue,
			HttpServletRequest request, ModelMap model) {
		bean = manager.save(bean,paramName,defaultValue);
		log.info("save BbsWebservice id={}", bean.getId());
		return "redirect:v_list.html";
	}

	@RequiresPermissions("webservice:o_update")
	@RequestMapping("/webservice/o_update.html")
	public String update(BbsWebservice bean,String[] paramName, String[] defaultValue,
			Integer pageNo, HttpServletRequest request,ModelMap model) {
		bean = manager.update(bean,paramName,defaultValue);
		log.info("update BbsWebservice id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("webservice:o_delete")
	@RequestMapping("/webservice/o_delete.html")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		manager.deleteByIds(ids);
		return list(pageNo, request, model);
	}
	
	@Autowired
	private BbsWebserviceMng manager;
}