package com.jeecms.plug.live.entity;

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

import com.jeecms.bbs.entity.BbsUser;

/**
 * 活动live在线交流消息
 * @author andy_hulibo@163.com
 * 2018年11月1日下午4:11:53
 */
@Entity
@Table(name="bbs_live_message")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsLiveMessage implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:40:16
	 */
	private static final long serialVersionUID = 2841723128414686980L;

	public static final Short MST_TYPE_TEXT = 0;
	public static final Short MST_TYPE_IMAGE = 1;
	public static final Short MST_TYPE_GIFT = 2;

	public static final String SYSTEM_MSG_LIVE_STOP = "_liveStop";

	@Id
	@Column(name="msg_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="msg_time")
	private Date msgTime;
	
	@Column(name="content")
	private String content;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="msg_type")
	private Short msgType;

	@ManyToOne
	@JoinColumn(name = "live_id")
	private BbsLive live;
	
	@ManyToOne
	@JoinColumn(name = "user_id")	
	private BbsUser user;
	
	@ManyToOne
	@JoinColumn(name = "to_user",referencedColumnName="user_id")	
	private BbsUser toUser;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Date getMsgTime() {
		return msgTime;
	}

	
	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public String getImageUrl() {
		return imageUrl;
	}

	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Short getMsgType() {
		return msgType;
	}

	
	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	
	public BbsLive getLive() {
		return live;
	}

	public void setLive(BbsLive live) {
		this.live = live;
	}

	
	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public BbsUser getToUser() {
		return toUser;
	}

	public void setToUser(BbsUser toUser) {
		this.toUser = toUser;
	}

}