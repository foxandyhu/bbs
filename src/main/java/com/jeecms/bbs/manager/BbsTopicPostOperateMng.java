package com.jeecms.bbs.manager;

import com.jeecms.common.page.Pagination;

import java.util.List;

import com.jeecms.bbs.entity.BbsTopicPostOperate;

/**
 * 主题互动记录业务接口
 *
 * @author: andy_hulibo@163.com
 * @date: 2018/11/13 10:46
 */
public interface BbsTopicPostOperateMng {
    Pagination getPage(Short dataType,
                       Integer userId, Integer operate, int pageNo, int pageSize);

    BbsTopicPostOperate findById(Long id);

    List<BbsTopicPostOperate> getList(Integer dateId, Short dateType,
                                      Integer userId, Integer operate, Integer first, Integer count);

    /**
     * 主题操作
     *
     * @param topicId 主题Id
     * @param userId  用户Id
     * @param operate 操作类型 0点赞 1收藏 2关注  3取消点赞 4取消收藏 5取消关注
     * @return
     */
    BbsTopicPostOperate topicOperate(Integer topicId,
                                     Integer userId, Integer operate);

    BbsTopicPostOperate postOperate(Integer postId,
                                    Integer userId, Integer operate);

    BbsTopicPostOperate save(BbsTopicPostOperate bean);

    BbsTopicPostOperate update(BbsTopicPostOperate bean);

    /**
     * 删除主题操纵数据
     * @param ids  操纵记录ID
     * @author: andy_hulibo@163.com
     * @date: 2018/11/13 11:28
     * @return
     */
    BbsTopicPostOperate[] deleteByIds(Long... ids);
}