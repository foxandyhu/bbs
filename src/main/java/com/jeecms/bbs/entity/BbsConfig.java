package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeecms.core.entity.CmsSite;

/**
 * 论坛配置
 * @author andy_hulibo@163.com
 * 2018年10月31日上午9:24:26
 */
@Entity
@Table(name="bbs_config")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsConfig implements Serializable{

	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午3:59:06
	 */
	private static final long serialVersionUID = 5573803333826907247L;

	/**
	 * 关闭注册
	 */
	public static final short REGISTER_CLOSE = 0;
	/**
	 * 开发注册
	 */
	public static final short REGISTER_OPEN = 1;
	/**
	 * 邀请注册
	 */
	public static final short REGISTER_INVITATION = 2;

	@Id
    @Column(name="CONFIG_ID")
	private Integer id;

	@Column(name="DEF_AVATAR")
	private String defAvatar;
	
	@Column(name="AVATAR_WIDTH")
	private Integer avatarWidth;
	
	@Column(name="AVATAR_HEIGHT")
	private Integer avatarHeight;
	
	@Column(name="TOPIC_COUNT_PER_PAGE")
	private Integer topicCountPerPage;
	
	@Column(name="POST_COUNT_PER_PAGE")
	private Integer postCountPerPage;
	
	@Column(name="KEYWORDS")
	private String keywords;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="REGISTER_STATUS")
	private Short registerStatus;
	
	@Column(name="REGISTER_RULE")
	private String registerRule;
	
	@Column(name="TOPIC_HOT_COUNT")
	private Integer topicHotCount;
	
	@Column(name="CACHE_POST_TODAY")
	private Integer postToday;
	
	@Column(name="CACHE_POST_YESTERDAY")
	private Integer postYesterday;
	
	@Column(name="CACHE_POST_MAX")
	private Integer postMax;
	
	@Column(name="CACHE_POST_MAX_DATE")
	private Date postMaxDate;
	
	@Column(name="CACHE_TOPIC_TOTAL")
	private Integer topicTotal;
	
	@Column(name="CACHE_POST_TOTAL")
	private Integer postTotal;
	
	@Column(name="CACHE_USER_TOTAL")
	private Integer userTotal;
	
	@Column(name="AUTO_REGISTER")
	private Boolean autoRegister;
	
	@Column(name="EMAIL_VALIDATE")
	private Boolean emailValidate;
	
	@Column(name="pic_zoom_def_width")
	private Integer picZoomDefWidth;

	@JoinColumn(name="CONFIG_ID")
	@OneToOne(cascade=CascadeType.ALL)
	private CmsSite site;

	@ManyToOne
	@JoinColumn(name="last_user_id")
	private BbsUser lastUser;
	
	@ManyToOne
	@JoinColumn(name="REGISTER_GROUP_ID")
	private BbsUserGroup registerGroup;
	
	@ManyToOne
	@JoinColumn(name="default_group_id")	
	private BbsUserGroup defaultGroup;


	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}


	public String getDefAvatar () {
		return defAvatar;
	}

	public void setDefAvatar (String defAvatar) {
		this.defAvatar = defAvatar;
	}

	public Integer getAvatarWidth () {
		return avatarWidth;
	}

	public void setAvatarWidth (Integer avatarWidth) {
		this.avatarWidth = avatarWidth;
	}

	public Integer getAvatarHeight () {
		return avatarHeight;
	}

	public void setAvatarHeight (Integer avatarHeight) {
		this.avatarHeight = avatarHeight;
	}

	public Integer getTopicCountPerPage () {
		return topicCountPerPage;
	}

	public void setTopicCountPerPage (Integer topicCountPerPage) {
		this.topicCountPerPage = topicCountPerPage;
	}

	public Integer getPostCountPerPage () {
		return postCountPerPage;
	}

	public void setPostCountPerPage (Integer postCountPerPage) {
		this.postCountPerPage = postCountPerPage;
	}

	public String getKeywords () {
		return keywords;
	}

	public void setKeywords (String keywords) {
		this.keywords = keywords;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public Short getRegisterStatus () {
		return registerStatus;
	}

	public void setRegisterStatus (Short registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getRegisterRule () {
		return registerRule;
	}

	public void setRegisterRule (String registerRule) {
		this.registerRule = registerRule;
	}

	public Integer getTopicHotCount () {
		return topicHotCount;
	}

	public void setTopicHotCount (Integer topicHotCount) {
		this.topicHotCount = topicHotCount;
	}

	public Integer getPostToday () {
		return postToday;
	}

	public void setPostToday (Integer postToday) {
		this.postToday = postToday;
	}

	public Integer getPostYesterday () {
		return postYesterday;
	}

	public void setPostYesterday (Integer postYesterday) {
		this.postYesterday = postYesterday;
	}

	public Integer getPostMax () {
		return postMax;
	}

	public void setPostMax (Integer postMax) {
		this.postMax = postMax;
	}

	public Date getPostMaxDate () {
		return postMaxDate;
	}

	public void setPostMaxDate (Date postMaxDate) {
		this.postMaxDate = postMaxDate;
	}

	public Integer getTopicTotal () {
		return topicTotal;
	}

	public void setTopicTotal (Integer topicTotal) {
		this.topicTotal = topicTotal;
	}

	public Integer getPostTotal () {
		return postTotal;
	}

	public void setPostTotal (Integer postTotal) {
		this.postTotal = postTotal;
	}

	public Integer getUserTotal () {
		return userTotal;
	}

	public void setUserTotal (Integer userTotal) {
		this.userTotal = userTotal;
	}

	public Boolean getAutoRegister () {
		return autoRegister;
	}

	public void setAutoRegister (Boolean autoRegister) {
		this.autoRegister = autoRegister;
	}
	
	public Boolean getEmailValidate() {
		return emailValidate;
	}

	public void setEmailValidate(Boolean emailValidate) {
		this.emailValidate = emailValidate;
	}

	public Integer getPicZoomDefWidth() {
		return picZoomDefWidth;
	}

	public void setPicZoomDefWidth(Integer picZoomDefWidth) {
		this.picZoomDefWidth = picZoomDefWidth;
	}

	public CmsSite getSite () {
		return site;
	}

	public void setSite (CmsSite site) {
		this.site = site;
	}

	public BbsUser getLastUser () {
		return lastUser;
	}

	public void setLastUser (BbsUser lastUser) {
		this.lastUser = lastUser;
	}

	public BbsUserGroup getRegisterGroup () {
		return registerGroup;
	}

	public void setRegisterGroup (BbsUserGroup registerGroup) {
		this.registerGroup = registerGroup;
	}

	public BbsUserGroup getDefaultGroup () {
		return defaultGroup;
	}

	public void setDefaultGroup (BbsUserGroup defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
}