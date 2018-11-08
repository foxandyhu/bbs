package com.jeecms.core.servlet;

import com.jeecms.bbs.manager.BbsUserMng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Description:静态资源过滤器
 * @Author:andy_hulibo@163.com
 * @CreateDate:2018/11/8 21:30
 */
@WebFilter(filterName = "resourceFilter", urlPatterns = {"/*"})
public class ResourceFilter implements Filter {
    protected final Logger log = LoggerFactory
            .getLogger(ResourceFilter.class);

    @Value("#{'${spring.resource.suffix}'.split(',')}")
    private List<String> suffixs;
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String url=((HttpServletRequest)req).getRequestURI();
        System.out.println(suffixs);
        chain.doFilter(req,response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
