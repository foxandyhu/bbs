package com.jeecms.bbs.cache;

import com.jeecms.bbs.entity.BbsUser;

/**
 * @author andy_hulibo@163.com
 * @date 2018/11/13 17:55
 */
public interface BbsConfigEhCache {

    void setBbsConfigCache(int postToday, int topicTotal, int postTotal,
                           int userTotal, BbsUser lastUser, Integer siteId);

    BbsConfigCache getBbsConfigCache(Integer siteId);
}
