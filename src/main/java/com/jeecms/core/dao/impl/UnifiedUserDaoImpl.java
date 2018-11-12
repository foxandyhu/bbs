package com.jeecms.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.UnifiedUserDao;
import com.jeecms.core.entity.UnifiedUser;

@Repository
public class UnifiedUserDaoImpl extends HibernateBaseDao<UnifiedUser, Integer>
		implements UnifiedUserDao {

	@Override
	public UnifiedUser getByUsername(String username) {
		return findUniqueByProperty("username", username);
	}

	@Override
	public List<UnifiedUser> getByEmail(String email) {
		return findByProperty("email", email);
	}

	@Override
	public int countByEmail(String email) {
		String hql = "select count(*) from UnifiedUser bean where bean.email=:email";
		Query query = getSession().createQuery(hql);
		query.setParameter("email", email);
		return ((Number) query.iterate().next()).intValue();
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	@Override
	public UnifiedUser findById(Integer id) {
		UnifiedUser entity = get(id);
		return entity;
	}

	@Override
	public UnifiedUser save(UnifiedUser bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public UnifiedUser deleteById(Integer id) {
		UnifiedUser entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<UnifiedUser> getEntityClass() {
		return UnifiedUser.class;
	}
}