package com.jeecms.bbs.cache;


import java.util.Date;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.jeecms.bbs.manager.BbsSessionMng;

/**
 * 用户访问最后活动时间缓存实现
 * @author andy_hulibo@163.com
 * @date 2018/11/13 17:56
 */
@Service
public class BbsUserCacheImpl implements BbsUserCache, DisposableBean {
	private Logger log = LoggerFactory.getLogger(BbsUserCacheImpl.class);

	@Override
	public void view(Long sessionId,Date lastActiveTime){
		cache.put(new Element(sessionId,lastActiveTime));
	}

	@Override
	public void refreshToDB() {
		bbsSessionMng.freshCacheToDB(cache);
		log.info("refresh cache views to DB: {}");
	}

	@Override
	public void removeCache() {
		// 清除缓存
		cache.removeAll();
		log.info("refresh cache views to DB: {}");
	}

	/**
	 * 销毁BEAN时，缓存入库。
	 */
	@Override
	public void destroy() throws Exception {
		bbsSessionMng.freshCacheToDB(cache);
		log.info("Bean destroy.refresh cache views to DB: {}");
	}

	@Autowired
	private BbsSessionMng bbsSessionMng;

	private Ehcache cache;

	@Autowired
	public void setCache(EhCacheCacheManager cacheManager){
		EhCacheCache ehcache= (EhCacheCache)cacheManager.getCache("bbsUserCache");
		cache=ehcache.getNativeCache();
	}
}
