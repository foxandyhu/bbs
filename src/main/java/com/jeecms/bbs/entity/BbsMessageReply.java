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

import com.jeecms.common.util.DateUtils;

@Entity
@Table(name = "jb_message_reply")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsMessageReply implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:57:30
	 */
	private static final long serialVersionUID = -5437423454906687480L;

	@Id
	@Column(name="reply_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="content")
	private String content;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="is_read")
	private Boolean status;
	private Boolean isnotification;

	@ManyToOne
	@JoinColumn(name = "msg_id")
	private BbsMessage message;
	
	@ManyToOne
	@JoinColumn(name="sender")
	private BbsUser sender;
	
	@ManyToOne
	@JoinColumn(name="receiver")	
	private BbsUser receiver;

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

	
	public BbsMessage getMessage() {
		return message;
	}

	
	public void setMessage(BbsMessage message) {
		this.message = message;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getIsnotification() {
		return isnotification;
	}

	public void setIsnotification(Boolean isnotification) {
		this.isnotification = isnotification;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("content", getContent());
		json.put("status", getStatus());
		if (getSender() != null) {
			json.put("sender", getSender().getUsername());
		}
		if (getReceiver() != null) {
			json.put("receiver", getReceiver().getUsername());
		}
		json.put("createTime", DateUtils.parseDateToTimeStr(getCreateTime()));
		return json;
	}

	public BbsMessageReply() {
		
	}
	
	public BbsMessageReply(BbsMessage message) {
		setContent(message.getContent());
		setCreateTime(message.getCreateTime());
		setMessage(message);
		setSender(message.getSender());
		setReceiver(message.getReceiver());
	}
}