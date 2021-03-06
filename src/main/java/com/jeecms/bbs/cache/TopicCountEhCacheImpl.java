package com.jeecms.bbs.cache;

import java.util.List;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.BbsTopicCountEnum;
import com.jeecms.bbs.manager.BbsTopicMng;

/**
 * 
 * @author andy_hulibo@163.com
 * @date 2018/11/13 17:58
 */
@Service
public class TopicCountEhCacheImpl implements TopicCountEhCache, DisposableBean {

    @Override
    public Long getViewCount(Integer topicId) {
        Element e = topicCountCache.get(topicId);
        if (e != null) {
            return (Long) e.getObjectValue();
        } else {
            BbsTopic topic = bbsTopicMng.findById(topicId);
            Long viewCount = 0L;
            if (topic.getViewCount() != null) {
                viewCount = topic.getViewCount();
            }
            topicCountCache.put(new Element(topicId, viewCount));
            return viewCount;
        }
    }

    @Override
    public Long getViewCount(Integer topicId, BbsTopicCountEnum e) {
        Element en = topicCountCache.get(topicId);
        if (e.equals(BbsTopicCountEnum.day)) {
            en = topicDayCountCache.get(topicId);
        } else if (e.equals(BbsTopicCountEnum.week)) {
            en = topicWeekCountCache.get(topicId);
        } else if (e.equals(BbsTopicCountEnum.month)) {
            en = topicMonthCountCache.get(topicId);
        }
        if (en != null) {
            return (Long) en.getObjectValue();
        } else {
            BbsTopic topic = bbsTopicMng.findById(topicId);
            Long viewCount = 0L;
            if (topic.getViewCount() != null) {
                viewCount = topic.getViewCount();
            }
            if (e.equals(BbsTopicCountEnum.day)) {
                viewCount = topic.getViewsDay();
                topicDayCountCache.put(new Element(topicId, viewCount));
            } else if (e.equals(BbsTopicCountEnum.week)) {
                viewCount = topic.getViewsWeek();
                topicWeekCountCache.put(new Element(topicId, viewCount));
            } else if (e.equals(BbsTopicCountEnum.month)) {
                viewCount = topic.getViewsMonth();
                topicMonthCountCache.put(new Element(topicId, viewCount));
            }
            return viewCount;
        }
    }

    @Override
    public Long setViewCount(Integer topicId) {
        Long viewCount = 0L;
        Long viewDayCount = 0L;
        Long viewWeekCount = 0L;
        Long viewMonthCount = 0L;
        Element e = topicCountCache.get(topicId);
        Element eTopicDay = topicDayCountCache.get(topicId);
        Element eTopicWeek = topicWeekCountCache.get(topicId);
        Element eTopicMonth = topicMonthCountCache.get(topicId);
        BbsTopic topic = bbsTopicMng.findById(topicId);
        if (e != null) {
            viewCount = (Long) e.getObjectValue() + 1;
        } else {
            if (topic.getViewCount() == null) {
                viewCount = 1L;
            } else {
                viewCount = topic.getViewCount() + 1;
            }
        }
        if (eTopicDay != null) {
            viewDayCount = (Long) eTopicDay.getObjectValue() + 1;
        } else {
            if (topic.getViewsDay() == null) {
                viewDayCount = 1L;
            } else {
                viewDayCount = topic.getViewsDay() + 1;
            }
        }
        if (eTopicWeek != null) {
            viewWeekCount = (Long) eTopicWeek.getObjectValue() + 1;
        } else {
            if (topic.getViewsWeek() == null) {
                viewWeekCount = 1L;
            } else {
                viewWeekCount = topic.getViewsWeek() + 1;
            }
        }
        if (eTopicMonth != null) {
            viewMonthCount = (Long) eTopicMonth.getObjectValue() + 1;
        } else {
            if (topic.getViewsMonth() == null) {
                viewMonthCount = 1L;
            } else {
                viewMonthCount = topic.getViewsMonth() + 1;
            }
        }
        topicCountCache.put(new Element(topicId, viewCount));
        topicDayCountCache.put(new Element(topicId, viewDayCount));
        topicWeekCountCache.put(new Element(topicId, viewWeekCount));
        topicMonthCountCache.put(new Element(topicId, viewMonthCount));
        refreshToDB(topicId);
        return viewCount;
    }

    private void refreshToDB(Integer topicId) {
        long time = System.currentTimeMillis();
        if (time > refreshTime + interval) {
            refreshTime = time;
            BbsTopic topic = bbsTopicMng.findById(topicId);
            Element e = topicCountCache.get(topicId);
            Long viewCount = (Long) e.getObjectValue();
            topic.setViewCount(viewCount);
            Element eTopicDay = topicDayCountCache.get(topicId);
            Long viewDayCount = (Long) eTopicDay.getObjectValue();
            topic.setViewsDay(viewDayCount);
            Element eTopicWeek = topicWeekCountCache.get(topicId);
            Long viewWeekCount = (Long) eTopicWeek.getObjectValue();
            topic.setViewsWeek(viewWeekCount);
            Element eTopicMonth = topicMonthCountCache.get(topicId);
            Long viewMonthCount = (Long) eTopicMonth.getObjectValue();
            topic.setViewsMonth(viewMonthCount);
            bbsTopicMng.update(topic);
        }
    }

    @Override
    public boolean getLastReply(Integer userId, long time) {
        Element e = replycache.get(userId);
        if (e != null) {
            long reply = (Long) e.getObjectValue();
            long times = System.currentTimeMillis() - reply;
            if (times / 1000 > time) {
                replycache.put(new Element(userId, System.currentTimeMillis()));
                return true;
            }
        } else {
            replycache.put(new Element(userId, System.currentTimeMillis()));
            return true;
        }
        return false;
    }

    /**
     * 销毁BEAN时，缓存入库。
     */
    @SuppressWarnings("unchecked")
    @Override
    public void destroy() throws Exception {
        List<Integer> keys = topicCountCache.getKeys();
        for (Integer topicId : keys) {
            refreshToDB(topicId);
        }
    }

    /**
     * 间隔时间1个小时
     */
    private int interval = 60 * 1000;
    /**
     * 最后刷新时间
     */
    private long refreshTime = System.currentTimeMillis();
    @Autowired
    private BbsTopicMng bbsTopicMng;
    private Ehcache topicCountCache;
    private Ehcache replycache;
    private Ehcache topicDayCountCache;
    private Ehcache topicWeekCountCache;
    private Ehcache topicMonthCountCache;

    @Autowired
    public void setCache(EhCacheCacheManager cacheManager) {
        EhCacheCache cache = (EhCacheCache) cacheManager.getCache("topicCountCache");
        topicCountCache = cache.getNativeCache();

        cache = (EhCacheCache) cacheManager.getCache("lastReplyCache");
        replycache = cache.getNativeCache();

        cache = (EhCacheCache) cacheManager.getCache("topicDayCountCache");
        topicDayCountCache = cache.getNativeCache();

        cache = (EhCacheCache) cacheManager.getCache("topicWeekCountCache");
        topicWeekCountCache = cache.getNativeCache();

        cache = (EhCacheCache) cacheManager.getCache("topicMonthCountCache");
        topicMonthCountCache = cache.getNativeCache();
    }
}
