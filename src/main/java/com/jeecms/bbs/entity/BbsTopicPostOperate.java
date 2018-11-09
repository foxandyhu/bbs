package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "bbs_topic_post_operate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsTopicPostOperate implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:54:50
	 */
	private static final long serialVersionUID = 4180230271497185419L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="operate")
	private Integer operate;
	
	@Column(name="data_id")
	private Integer dataId;
	
	@Column(name="data_type")
	private Short dataType;
	
	@Column(name="op_time")
	private Date opTime;

	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOperate() {
		return operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Short getDataType() {
		return dataType;
	}

	public void setDataType(Short dataType) {
		this.dataType = dataType;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getDataType() != null) {
			json.put("dataType", getDataType());
		} else {
			json.put("dataType", "");
		}
		if (getDataId() != null) {
			json.put("dataId", getDataId());
		} else {
			json.put("dataId", "");
		}
		return json;
	}

	/**
	 * 操作类型 0点赞
	 */
	public static final Integer OPT_UP = 0;
	/**
	 * 操作类型 1收藏
	 */
	public static final Integer OPT_COLLECT = 1;
	/**
	 * 操作类型 2关注
	 */
	public static final Integer OPT_ATTENT = 2;

	/**
	 * 操作类型 3取消点赞
	 */
	public static final Integer OPT_CANCEL_UP = 3;
	/**
	 * 操作类型 4取消收藏
	 */
	public static final Integer OPT_CANCEL_COLLECT = 4;
	/**
	 * 操作类型 5取消关注
	 */
	public static final Integer OPT_CANCEL_ATTENT = 5;
	/**
	 * 主题数据
	 */
	public static final Short DATA_TYPE_TOPIC = 0;
	/**
	 * 帖子数据
	 */
	public static final Short DATA_TYPE_POST = 1;
}