package com.jeecms.bbs.cache;

import com.jeecms.bbs.entity.BbsTopicCountEnum;

/**
 * @author andy_hulibo@163.com
 * @date 2018/11/13 17:55
 */
public interface TopicCountEhCache {

    Long getViewCount(Integer topicId);

    Long getViewCount(Integer topicId, BbsTopicCountEnum e);

    Long setViewCount(Integer topicId);

    boolean getLastReply(Integer userId, long time);

}
