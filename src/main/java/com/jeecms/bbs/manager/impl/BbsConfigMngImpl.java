package com.jeecms.bbs.manager.impl;

import com.jeecms.bbs.cache.BbsConfigCache;
import com.jeecms.bbs.dao.BbsConfigDao;
import com.jeecms.bbs.entity.BbsConfig;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.bbs.manager.BbsForumMng;
import com.jeecms.common.hibernate4.Updater;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author andy_hulibo@163.com
 * @date 2018/11/13 17:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BbsConfigMngImpl implements BbsConfigMng {

	@Override
	@Transactional(readOnly = true,rollbackFor = Exception.class)
	public BbsConfig findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true,rollbackFor = Exception.class)
	public BbsConfig get() {
		return dao.findById(1);
	}

	@Override
	public BbsConfig update(BbsConfig bean) {
		Updater<BbsConfig> updater = new Updater<>(bean);
		return dao.updateByUpdater(updater);
	}

	@Override
	public BbsConfig updateConfigForDay(Integer siteId) {
		List<BbsForum> flist = bbsForumMng.getList(siteId);
		for (BbsForum forum : flist) {
			forum.setPostToday(0);
			bbsForumMng.update(forum);
		}
		BbsConfig bbsConfig = dao.findById(siteId);
		Element e = cache.get(siteId);
		if (e != null) {
			BbsConfigCache configCache = (BbsConfigCache) e.getObjectValue();
			bbsConfig.setLastUser(configCache.getLastUser());
			bbsConfig.setPostMax(configCache.getPostMax());
			bbsConfig.setPostMaxDate(configCache.getPostMaxDate());
			bbsConfig.setPostToday(0);
			bbsConfig.setPostTotal(configCache.getPostTotal());
			bbsConfig.setPostYesterday(configCache.getPostToday());
			bbsConfig.setTopicTotal(configCache.getTopicTotal());
			bbsConfig.setUserTotal(configCache.getUserTotal());
			configCache.setPostYestoday(configCache.getPostToday());
			configCache.setPostToday(0);
			cache.put(new Element(siteId, configCache));
		}
		update(bbsConfig);
		return bbsConfig;
	}

	@Autowired
	private BbsConfigDao dao;
	@Autowired
	private BbsForumMng bbsForumMng;
	private Ehcache cache;

	@Autowired
	public void setCache(EhCacheCacheManager cacheManager){
		EhCacheCache ehcache= (EhCacheCache)cacheManager.getCache("beanCache");
		cache=ehcache.getNativeCache();
	}
}
