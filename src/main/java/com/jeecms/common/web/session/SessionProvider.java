package com.jeecms.common.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session提供者
 *
 * @author tom
 */
public interface SessionProvider {
    Serializable getAttribute(HttpServletRequest request, String name);

    void setAttribute(HttpServletRequest request,
                      HttpServletResponse response, String name, Serializable value);

    /**
     * 获得HttpServletRequest Session Id
     *
     * @param request
     * @param response
     * @return 返回sessionID
     * @author: andy_hulibo@163.com
     * @date: 2018/11/12 16:14
     */
    String getSessionId(HttpServletRequest request,
                        HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
