package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.util.DateUtils;

@Entity
@Table(name="jb_message")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsMessage implements Cloneable, Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:56:42
	 */
	private static final long serialVersionUID = 7452257210089068708L;

	// 消息
	public static int MESSAGE_TYPE_MESSAGE = 1;
	// 留言
	public static int MESSAGE_TYPE_GUESTBOOK = 2;
	// 打招呼
	public static int MESSAGE_TYPE_GREET = 3;

	public static Short MESSAGE_SEND_TYPE_ALL = 2;

	public static Short MESSAGE_SEND_TYPE_GROUP = 1;

	public static Short MESSAGE_SEND_TYPE_USER = 0;

	@Id
	@Column(name="msg_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="content")
	private String content;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="is_sys")
	private Boolean sys;
	
	@Column(name="msg_type")
	private Integer msgType;
	
	@Column(name="is_read")
	private Boolean status;
	
	@Column(name="msg_send_type")
	private Short msgSendType;

	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser user;
	
	@ManyToOne
	@JoinColumn(name="sender")
	private BbsUser sender;
	
	@ManyToOne
	@JoinColumn(name="receiver")
	private BbsUser receiver;
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private BbsUserGroup msgGroup;

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="message")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
	private Set<BbsMessageReply> reply;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getSys() {
		return sys;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public void setSys(Boolean sys) {
		this.sys = sys;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Short getMsgSendType() {
		return msgSendType;
	}

	public void setMsgSendType(Short msgSendType) {
		this.msgSendType = msgSendType;
	}

	public BbsUserGroup getMsgGroup() {
		return msgGroup;
	}

	public void setMsgGroup(BbsUserGroup msgGroup) {
		this.msgGroup = msgGroup;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public BbsUser getSender() {
		return sender;
	}

	public void setSender(BbsUser sender) {
		this.sender = sender;
	}

	public BbsUser getReceiver() {
		return receiver;
	}

	public void setReceiver(BbsUser receiver) {
		this.receiver = receiver;
	}

	public Set<BbsMessageReply> getReply() {
		return reply;
	}

	public void setReply(Set<BbsMessageReply> reply) {
		this.reply = reply;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getContent())) {
			json.put("content", getContent());
		} else {
			json.put("content", "");
		}
		if (getSys() != null) {
			json.put("sys", getSys());
		} else {
			json.put("sys", "");
		}
		if (getMsgType() != null) {
			json.put("msgType", getMsgType());
		} else {
			json.put("msgType", "");
		}
		if (getStatus() != null) {
			json.put("status", getStatus());
		} else {
			json.put("status", "");
		}

		if (getUser() != null) {
			if (StringUtils.isNotBlank(getUser().getUsername())) {
				json.put("user", getUser().getUsername());
			} else {
				json.put("user", "");
			}
		} else {
			json.put("user", "");
		}
		if (getSender() != null) {
			if (StringUtils.isNotBlank(getSender().getUsername())) {
				json.put("sender", getSender().getUsername());
			} else {
				json.put("sender", "");
			}
		} else {
			json.put("sender", "");
		}
		if (getReceiver() != null) {
			if (StringUtils.isNotBlank(getReceiver().getUsername())) {
				json.put("receiver", getReceiver().getUsername());
			} else {
				json.put("receiver", "");
			}
		} else {
			json.put("receiver", "");
		}
		if (getCreateTime() != null) {
			json.put("createTime", DateUtils.parseDateToTimeStr(getCreateTime()));
		} else {
			json.put("createTime", "");
		}
		return json;
	}

	public BbsMessage clone() {
		BbsMessage clone;
		try {
			clone = (BbsMessage) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Clone not support?");
		}
		return clone;
	}

	public BbsMessage putDataAndClone(BbsUser sender, BbsUser receiver) {
		Date now = new Date();
		setUser(sender);
		setSender(sender);
		setReceiver(receiver);
		setCreateTime(now);
		init();
		BbsMessage clone = clone();
		clone.setUser(receiver);
		return clone;
	}

	public BbsMessageReply createReply() {
		BbsMessageReply bean = new BbsMessageReply();
		bean.setContent(getContent());
		bean.setCreateTime(getCreateTime());
		bean.setMessage(this);
		bean.setSender(getSender());
		bean.setReceiver(getReceiver());
		return bean;
	}

	public void init() {
		if (getSys() == null) {
			setSys(false);
		}
	}
}