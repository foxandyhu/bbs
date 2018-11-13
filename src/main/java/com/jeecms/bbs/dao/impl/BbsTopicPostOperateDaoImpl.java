package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsTopicPostOperateDao;
import com.jeecms.bbs.entity.BbsTopicPostOperate;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsTopicPostOperateDaoImpl extends HibernateBaseDao<BbsTopicPostOperate, Long>
implements BbsTopicPostOperateDao {

	@Override
	public Pagination getPage(Short dataType,
			Integer userId,Integer operate,int pageNo, int pageSize) {
		String hql="from BbsTopicPostOperate bean where 1=1 ";
		Finder f=Finder.create(hql);
		if(dataType!=null){
			f.append(" and bean.dataType=:dataType").setParam("dataType", dataType);
		}
		if(userId!=null){
			f.append(" and bean.user.id=:userId").setParam("userId", userId);
		}
		if(operate!=null){
			f.append(" and bean.operate=:operate").setParam("operate", operate);
		}
		Pagination page=find(f, pageNo, pageSize);
		return page;
	}
	
	@Override
	public List<BbsTopicPostOperate> getList(Integer dataId,Short dataType,
			Integer userId,Integer operate,Integer first,Integer count){
		String hql="from BbsTopicPostOperate bean where 1=1 ";
		Finder f=Finder.create(hql);
		if(dataId!=null){
			f.append(" and bean.dataId=:dataId").setParam("dataId", dataId);
		}
		if(dataType!=null){
			f.append(" and bean.dataType=:dataType").setParam("dataType", dataType);
		}
		if(userId!=null){
			f.append(" and bean.user.id=:userId").setParam("userId", userId);
		}
		if(operate!=null){
			f.append(" and bean.operate=:operate").setParam("operate", operate);
		}
		if(first!=null){
			f.setFirstResult(first);
		}
		if(count!=null){
			f.setMaxResults(count);
		}
		return find(f);
	}

	@Override
	public BbsTopicPostOperate findById(Long id) {
		BbsTopicPostOperate entity = get(id);
		return entity;
	}

	@Override
	public BbsTopicPostOperate save(BbsTopicPostOperate bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public BbsTopicPostOperate deleteById(Long id) {
		BbsTopicPostOperate entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<BbsTopicPostOperate> getEntityClass() {
		return BbsTopicPostOperate.class;
	}
}