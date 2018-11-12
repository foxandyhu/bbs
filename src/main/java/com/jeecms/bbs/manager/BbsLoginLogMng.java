package com.jeecms.bbs.manager;

import com.jeecms.bbs.entity.BbsLoginLog;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.common.page.Pagination;

/**
 * BBS用户登录日志业务接口
 *
 * @author: andy_hulibo@163.com
 * @date: 2018/11/12 16:19
 */
public interface BbsLoginLogMng {

    Pagination getPage(int pageNo, int pageSize);

    BbsLoginLog findById(Integer id);

    BbsLoginLog save(BbsLoginLog bean);

    /**
     * 保存用户登录日志并返回该日志
     *
     * @param user 登录用户
     * @param ip   登录ip
     * @return 登录日志
     * @author: andy_hulibo@163.com
     * @date: 2018/11/12 16:20
     */
    BbsLoginLog loginLog(BbsUser user, String ip);

    BbsLoginLog update(BbsLoginLog bean);

    BbsLoginLog deleteById(Integer id);

    BbsLoginLog[] deleteByIds(Integer[] ids);
}