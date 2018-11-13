package com.jeecms.bbs.manager;

import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.BbsTopicCountEnum;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.common.page.Pagination;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 论坛帖子业务接口
 * @author: andy_hulibo@163.com
 * @date: 2018/11/13 12:14
 */
public interface BbsTopicMng {
    void recommend(Integer[] ids, short recommend);

    void move(Integer[] ids, Integer forumId, String reason,
              BbsUser operator);

    void shieldOrOpen(Integer[] ids, boolean shield, String reason,
                      BbsUser operator);

    void lockOrOpen(Integer[] ids, boolean lock, String reason,
                    BbsUser operator);

    void upOrDown(Integer[] ids, Date time, String reason,
                  BbsUser operator);

    void prime(Integer[] ids, short primeLevel, String reason,
               BbsUser operator);

    void upTop(Integer[] ids, short topLevel, String reason,
               BbsUser operator);

    void highlight(Integer[] ids, String color, boolean bold,
                   boolean italic, Date time, String reason, BbsUser operator);

    void highlightWithNoLog(Integer[] ids, String color, boolean bold,
                            boolean italic, Date time, String reason, BbsUser operator);

    BbsTopic updateTitle(Integer id, String title, BbsUser editor);

    BbsTopic postTopic(Integer userId, Integer siteId, Integer forumId,
                       String title, String content, String ip,
                       Integer category, Integer categoryType,
                       Integer[] topicTypeIds, String[] name, List<MultipartFile> file,
                       Boolean hasAttach, List<String> code, Short equipSource,
                       Short charge, Double chargeAmount,
                       Float postLatitude, Float postLongitude,
                       Boolean rewardPattern, Double rewardRandomMin,
                       Double rewardRandomMax, Double[] rewardFix);

    BbsTopic postTopic(Integer userId, Integer siteId, Integer forumId,
                       String title, String ip, Short equipSource);

    Pagination getForTag(Integer siteId, Integer forumId,
                         Integer parentPostTypeId, Integer postTypeId, Short status,
                         Short primeLevel, String keyWords, String creater,
                         Integer createrId, Short topLevel, Integer topicTypeId, Integer excludeId,
                         Integer checkStatus, int pageNo, int pageSize, String jinghua, Integer orderBy, Short recommend);

    List<BbsTopic> getListForTag(Integer siteId, Integer forumId,
                                 Integer parentPostTypeId, Integer postTypeId, Short status,
                                 Short primeLevel, String keyWords, String creater,
                                 Integer createrId, Short topLevel, Integer topicTypeId, Integer excludeId,
                                 Integer checkStatus, int first, int count, String jinghua, Integer orderBy, Short recommend);

    Pagination getMemberTopic(Integer webId, Integer memberId,
                              int pageNo, int pageSize);

    Pagination getMemberReply(Integer webId, Integer memberId,
                              int pageNo, int pageSize);

    List<BbsTopic> getMemberReply(Integer siteId, Integer userId,
                                  Integer first, Integer count);

    Pagination getForSearchDate(Integer siteId, Integer forumId,
                                Short primeLevel, Integer day, int pageNo, int pageSize);

    Pagination getPage(int pageNo, int pageSize);

    BbsTopic findById(Integer id);

    BbsTopic save(BbsTopic bean);

    BbsTopic update(BbsTopic bean);

    BbsTopic deleteById(Integer id);

    BbsTopic[] deleteByIds(Integer[] ids);

    List<BbsTopic> getList(Integer forumId, String keywords, Integer userId, Short topLevel, Integer first, Integer count);

    List<BbsTopic> getNewList(Short topLevel, Integer first, Integer count, Integer orderby);

    List<BbsTopic> getTopList(Short topLevel, Integer count, Integer orderby);

    List<BbsTopic> getTopicList();

    void updateAllTopicCount(BbsTopicCountEnum e);

    void updateAllTopTime();

    /**
     * @param tid       主题id
     * @param magicName 道具名称
     */
    String useMagic(HttpServletRequest request, Integer siteId,
                    Integer tid, Integer postId, String magicName, Integer userId, String ip,
                    String color, Integer postCreaterId);

    List<BbsTopic> getTopicList(Integer userId, Integer bigId, Integer smallId, Integer count);

}
