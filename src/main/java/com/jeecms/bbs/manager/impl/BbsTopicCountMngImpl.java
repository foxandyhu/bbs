package com.jeecms.bbs.manager.impl;

import com.jeecms.bbs.dao.BbsTopicCountDao;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.BbsTopicCount;
import com.jeecms.bbs.manager.BbsTopicCountMng;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author: andy_hulibo@163.com
 * @date: 2018/11/13 11:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BbsTopicCountMngImpl implements BbsTopicCountMng {

	@Override
	@Transactional(readOnly = true,rollbackFor = Exception.class)
	public Pagination getPage(int pageNo, int pageSize) {
		return dao.getPage(pageNo, pageSize);
	}

	@Override
	@Transactional(readOnly = true,rollbackFor = Exception.class)
	public BbsTopicCount findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public int topicUp(Integer id) {
		BbsTopicCount c = dao.findById(id);
		if (c == null) {
			return 0;
		}
		int count = c.getUps() + 1;
		c.setUps(count);
		return count;
	}

	@Override
	public int topicCancelUp(Integer id) {
		BbsTopicCount c = dao.findById(id);
		if (c == null||c.getUps()<=0) {
			return 0;
		}
		int count = c.getUps() - 1;
		c.setUps(count);
		return count;
	}

	@Override
	public int topicCollect(Integer id) {
		BbsTopicCount c = dao.findById(id);
		if (c == null) {
			return 0;
		}
		int count = c.getCollections() + 1;
		c.setCollections(count);
		return count;
	}

	@Override
	public int topicCancelCollect(Integer id) {
		BbsTopicCount c = dao.findById(id);
		if (c == null||c.getCollections()<=0) {
			return 0;
		}
		int count = c.getCollections() - 1;
		c.setCollections(count);
		return count;
	}

	@Override
	public int topicAttent(Integer id) {
		BbsTopicCount c = dao.findById(id);
		if (c == null) {
			return 0;
		}
		int count = c.getAttentions() + 1;
		c.setAttentions(count);
		return count;
	}

	@Override
	public int topicCancelAttent(Integer id) {
		BbsTopicCount c = dao.findById(id);
		if (c == null||c.getAttentions()<=0) {
			return 0;
		}
		int count = c.getAttentions() - 1;
		c.setAttentions(count);
		return count;
	}

	@Override
	public BbsTopicCount save(BbsTopicCount count, BbsTopic topic) {
		count.setTopic(topic);
		count.init();
		dao.save(count);
		topic.setTopicCount(count);
		return count;
	}

	@Override
	public BbsTopicCount update(BbsTopicCount bean) {
		Updater<BbsTopicCount> updater = new Updater<>(bean);
		return dao.updateByUpdater(updater);
	}

	@Override
	public BbsTopicCount deleteById(Integer id) {
		return dao.deleteById(id);
	}

	@Override
	public BbsTopicCount[] deleteByIds(Integer[] ids) {
		BbsTopicCount[] beans = new BbsTopicCount[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private BbsTopicCountDao dao;
}