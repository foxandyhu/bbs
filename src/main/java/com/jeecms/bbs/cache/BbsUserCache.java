package com.jeecms.bbs.cache;

import java.util.Date;

/**
 * 用户缓存接口
 *
 * @author andy_hulibo@163.com
 * @date 2018/11/13 17:53
 */
public interface BbsUserCache {

    /**
     * 浏览一次
     * @param sessionId
     * @param lastActiveTime
     * @author andy_hulibo@163.com
     * @date 2018/11/13 17:53
     */
    void view(Long sessionId, Date lastActiveTime);

    void refreshToDB();

    void removeCache();
}
