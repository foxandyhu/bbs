package com.jeecms.bbs.cache;

/**
 * 板块计数器缓存接口
 *
 * @author andy_hulibo@163.com
 * @date 2018/11/13 17:54
 */
public interface ForumCountCache {

    /**
     * 浏览一次
     *
     * @param forumId
     * @return 返回浏览次数。
     * @author andy_hulibo@163.com
     * @date 2018/11/13 17:54
     */
    int[] viewAndGet(Integer forumId);

    /**
     * 发表主题
     *
     * @param forumId
     */
    void addTopic(Integer forumId);

    /**
     * 发表帖子
     *
     * @param forumId
     */
    void addPost(Integer forumId);
}
