package com.jeecms.bbs.manager;

import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.BbsTopicCount;
import com.jeecms.common.page.Pagination;

/**
 * 帖子相关数据统计
 * @author: andy_hulibo@163.com
 * @date: 2018/11/13 11:38
 */
public interface BbsTopicCountMng {

    Pagination getPage(int pageNo, int pageSize);

    BbsTopicCount findById(Integer id);

    /**
     * 点赞帖子
     * @param id
     * @author: andy_hulibo@163.com
     * @date: 2018/11/13 11:41
     * @return
     */
    int topicUp(Integer id);

    /**
     * 收藏帖子
     * @param id
     * @author: andy_hulibo@163.com
     * @date: 2018/11/13 11:41
     * @return
     */
    int topicCollect(Integer id);

    /**
     * 关注帖子
     * @param id
     * @author: andy_hulibo@163.com
     * @date: 2018/11/13 11:39
     * @return
     */
    int topicAttent(Integer id);

    /**
     * 取消点赞
     * @param id
     * @author: andy_hulibo@163.com
     * @date: 2018/11/13 11:41
     * @return
     */
    int topicCancelUp(Integer id);

    /**
     * 取消收藏
     * @param id
     * @author: andy_hulibo@163.com
     * @date: 2018/11/13 11:41
     * @return
     */
    int topicCancelCollect(Integer id);

    /**
     * 取消关注
     * @param id
     * @author: andy_hulibo@163.com
     * @date: 2018/11/13 11:4
     * @return
     */
    int topicCancelAttent(Integer id);

    BbsTopicCount save(BbsTopicCount count, BbsTopic topic);

    BbsTopicCount update(BbsTopicCount bean);

    BbsTopicCount deleteById(Integer id);

    BbsTopicCount[] deleteByIds(Integer[] ids);
}