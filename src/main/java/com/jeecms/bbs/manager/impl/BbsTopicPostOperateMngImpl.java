package com.jeecms.bbs.manager.impl;

import com.jeecms.bbs.dao.BbsTopicPostOperateDao;
import com.jeecms.bbs.entity.BbsPost;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.BbsTopicPostOperate;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsPostMng;
import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.bbs.manager.BbsTopicPostOperateMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * 
 * @author: andy_hulibo@163.com
 * @date: 2018/11/13 10:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BbsTopicPostOperateMngImpl implements BbsTopicPostOperateMng {

	@Override
	@Transactional(readOnly = true,rollbackFor = Exception.class)
	public Pagination getPage(Short dataType,
			Integer userId,Integer operate,int pageNo, int pageSize) {
		return dao.getPage(dataType,userId,operate,pageNo, pageSize);
	}

	@Override
	@Transactional(readOnly = true,rollbackFor = Exception.class)
	public BbsTopicPostOperate findById(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true,rollbackFor = Exception.class)
	public List<BbsTopicPostOperate> getList(Integer dateId,Short dateType,
			Integer userId,Integer operate,Integer first,Integer count) {
		return dao.getList(dateId,dateType,userId,operate,first,count);
	}

	@Override
	public BbsTopicPostOperate topicOperate(Integer topicId,Integer userId,
			Integer operate){
		BbsTopic topic=bbsTopicMng.findById(topicId);
		BbsUser user=userMng.findById(userId);
		if(topic==null|| user==null){
			return null;
		}
		BbsTopicPostOperate ope=new BbsTopicPostOperate();
		List<BbsTopicPostOperate> list;
		boolean isPositive=false;
		if(operate.equals(BbsTopicPostOperate.OPT_CANCEL_ATTENT)){
			list=getList(topicId,BbsTopicPostOperate.DATA_TYPE_TOPIC,userId, BbsTopicPostOperate.OPT_ATTENT,0,1);
		}else if(operate.equals(BbsTopicPostOperate.OPT_CANCEL_UP)){
			list=getList(topicId,BbsTopicPostOperate.DATA_TYPE_TOPIC,userId, BbsTopicPostOperate.OPT_UP,0,1);
		}else if(operate.equals(BbsTopicPostOperate.OPT_CANCEL_COLLECT)){
			list=getList(topicId,BbsTopicPostOperate.DATA_TYPE_TOPIC,userId, BbsTopicPostOperate.OPT_COLLECT,0,1);
		}else{
			isPositive=true;
			list=getList(topicId,BbsTopicPostOperate.DATA_TYPE_TOPIC,userId, operate,0,1);
		}

		//点赞收藏关注
		if(isPositive){
			if(list==null||list.size()<=0){
				ope.setOperate(operate);
				ope.setOpTime(Calendar.getInstance().getTime());
				ope.setDataId(topic.getId());
				ope.setDataType(BbsTopicPostOperate.DATA_TYPE_TOPIC);
				ope.setUser(user);
				save(ope);
			}
		}else{
			//取消点赞收藏关注
			if(list!=null&&list.size()>0){
				for(BbsTopicPostOperate op:list){
					deleteByIds(op.getId());
				}
			}
		}
		return ope;
	}

	@Override
	public BbsTopicPostOperate postOperate(Integer postId,Integer userId,
			Integer operate){
		BbsTopicPostOperate ope=new BbsTopicPostOperate();
		BbsPost post=bbsPostMng.findById(postId);
		BbsUser user=userMng.findById(userId);
		if(post!=null&&user!=null){
			List<BbsTopicPostOperate> list;
			if(operate.equals(BbsTopicPostOperate.OPT_UP)){
				list=getList(postId,BbsTopicPostOperate.DATA_TYPE_POST,
						userId, operate,0,1);
				//点赞
				if(list==null||list.size()<=0){
					ope.setOperate(operate);
					ope.setOpTime(Calendar.getInstance().getTime());
					ope.setDataId(post.getId());
					ope.setDataType(BbsTopicPostOperate.DATA_TYPE_POST);
					ope.setUser(user);
					save(ope);
				}
			}else if(operate.equals(BbsTopicPostOperate.OPT_CANCEL_UP)){
				list=getList(postId, BbsTopicPostOperate.DATA_TYPE_POST,
						userId, BbsTopicPostOperate.OPT_UP,0,1);
				//取消点赞
				if(list!=null&&list.size()>0){
					for(BbsTopicPostOperate op:list){
						deleteByIds(op.getId());
					}
				}
			}
		}
		return ope;
	}

	@Override
	public BbsTopicPostOperate save(BbsTopicPostOperate bean) {
		dao.save(bean);
		return bean;
	}

	@Override
	public BbsTopicPostOperate update(BbsTopicPostOperate bean) {
		Updater<BbsTopicPostOperate> updater = new Updater<>(bean);
		return dao.updateByUpdater(updater);
	}

	@Override
	public BbsTopicPostOperate[] deleteByIds(Long... ids) {
		BbsTopicPostOperate[] beans = new BbsTopicPostOperate[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = dao.deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private BbsTopicPostOperateDao dao;
	@Autowired
	private BbsTopicMng bbsTopicMng;
	@Autowired
	private BbsPostMng bbsPostMng;
	@Autowired
	private BbsUserMng userMng;
}