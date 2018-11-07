package com.jeecms.bbs.entity;

import static com.jeecms.bbs.Constants.DAY_MILLIS;
import static com.jeecms.bbs.web.FrontUtils.replaceSensitivity;
import static com.jeecms.common.web.Constants.SPT;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.util.DateUtils;
import com.jeecms.core.entity.CmsSite;

/**
 * 论坛主题
 * @author andy_hulibo@163.com 2018年10月30日下午2:16:51
 */
@Entity
@Table(name = "bbs_topic")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(name="CATEGORY",discriminatorType=DiscriminatorType.INTEGER)	//帖子类型
@DiscriminatorValue("100")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class BbsTopic implements Serializable {

	/**
	 * 正常状态
	 */
	public static final short NORMAL = 0;
	/**
	 * 屏蔽状态
	 */
	public static final short SHIELD = -1;
	/**
	 * 锁定状态
	 */
	public static final short LOCKED = 1;
	/**
	 * 待审核状态
	 */
	public static final Integer CHECKING = 0;
	/**
	 * 审核状态
	 */
	public static final Integer CHECKED = 1;
	/**
	 * 拒绝状态
	 */
	public static final Integer REJECT = 2;

	/**
	 * 普通帖
	 */
	public static final int TOPIC_NORMAL = 100;

	/**
	 * 投票帖(多选)
	 */
	public static final int TOPIC_VOTE = 101;
	/**
	 * 投票帖（单选）
	 */
	public static final int TOPIC_VOTE_SINGLE = 102;
	/**
	 * 未推荐
	 */
	public static final short TOPIC_NO_RECOMMEND = 0;
	/**
	 * 版主推荐
	 */
	public static final short TOPIC_MODERATOR_RECOMMEND = 1;

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:55:24
	 */
	private static final long serialVersionUID = -5065897576978082515L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TOPIC_ID")
	private Integer id;

	@Column(name="TITLE")
	private String title;		//标题
	
	@Column(name="CREATE_TIME")
	private Date createTime;	//创建时间
	
	@Column(name="LAST_TIME")
	private Date lastTime;		//最后回帖时间
	
	@Column(name="SORT_TIME")
	private Date sortTime;		//用于排序
	
	@Column(name="VIEW_COUNT")
	private Long viewCount;		//浏览次数
	
	@Column(name="views_day")
	private Long viewsDay;		//日访问量
	
	@Column(name="views_week")
	private Long viewsWeek;		//周访问量
	
	@Column(name="views_month")
	private Long viewsMonth;		//月访问量
	
	@Column(name="REPLY_COUNT")
	private Integer replyCount;		//回复次数
	
	@Column(name="replycount_day")
	private Integer replyCountDay;	//日回复量
	
	@Column(name="TOP_LEVEL")
	private Short topLevel;			//固定级别
	
	@Column(name="PRIME_LEVEL")
	private Short primeLevel;		//精华级别
	
	@Column(name="STYLE_BOLD")
	private Boolean styleBold;		//粗体
	
	@Column(name="STYLE_ITALIC")
	private Boolean styleItalic;	//斜体
	
	@Column(name="STYLE_COLOR")
	private String styleColor;		//颜色
	
	@Column(name="STYLE_TIME")
	private Date styleTime;			//样式有效时间
	
	@Column(name="OUTER_URL")
	private String outerUrl;		//外部链接
	
	@Column(name="STATUS")
	private Short status;			//状态
	
	@Column(name="IS_AFFIX")
	private Boolean affix;			//是否上传附件
	
	@Column(name="moderator_reply")
	private Boolean moderatorReply;	//版主是否回复
	
	@Column(name="HAVA_REPLY")
	private String haveReply;		//回复列表
	
	@Column(name="ALLAY_REPLY")
	private Boolean allayReply;		//主题是否允许回复
	
	@Column(name="HAS_SOFAED")
	private Boolean hasSofeed;		//主题是否已经被抢走沙发
	
	@Column(name="check_status")
	private Integer checkStatus;	//是否已审核

	//新增推荐、置顶时限字段
	@Column(name="RECOMMEND")
	private Short recommend;		//0未推荐 1版主推荐
	
	@Column(name="TOP_TIME")
	private Date topTime;			//推荐时限

	@OneToOne(mappedBy="topic",cascade=CascadeType.ALL)
	private BbsTopicText topicText;

	@OneToOne(mappedBy="topic",cascade=CascadeType.ALL)
	private BbsTopicCount topicCount;
	
	@ManyToOne
	@JoinColumn(name = "FIRST_POST_ID")
	private BbsPost firstPost;			//主题帖

	@ManyToOne
	@JoinColumn(name = "LAST_POST_ID")
	private BbsPost lastPost;			//最后帖

	@ManyToOne
	@JoinColumn(name = "site_id")	
	private CmsSite site;				//站点

	@ManyToOne
	@JoinColumn(name = "FORUM_ID")
	private BbsForum forum;				//板块

	@ManyToOne
	@JoinColumn(name = "creater_id")
	private BbsUser creater;			//发帖会员
	
	@ManyToOne
	@JoinColumn(name = "replyer_id")	
	private BbsUser lastReply;			//最后回帖会员

	@ManyToMany
	@JoinTable(name="bbs_topic_type_relation",joinColumns=@JoinColumn(name="topic_id",referencedColumnName="TOPIC_ID"),inverseJoinColumns=@JoinColumn(name="type_id",referencedColumnName="type_id"))
	private Set<BbsTopicType> types;	//帖子分类

	@OneToOne(cascade=CascadeType.ALL,mappedBy="topic")
	private BbsTopicCharge topicCharge;
	
	@ElementCollection
	@OrderBy("priority")
	@CollectionTable(name="bbs_topic_reward_fix",joinColumns=@JoinColumn(name="topic_id"))
	private List<BbsTopicRewardFix> rewardFixs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getRecommend() {
		return recommend;
	}

	public void setRecommend(Short recommend) {
		this.recommend = recommend;
	}

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public Date getLastTime() {
		return lastTime;
	}

	
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	
	public Date getSortTime() {
		return sortTime;
	}

	
	public void setSortTime(Date sortTime) {
		this.sortTime = sortTime;
	}

	
	public Long getViewCount() {
		return viewCount;
	}

	
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Long getViewsDay() {
		return viewsDay;
	}

	public void setViewsDay(Long viewsDay) {
		this.viewsDay = viewsDay;
	}

	public Long getViewsWeek() {
		return viewsWeek;
	}

	public void setViewsWeek(Long viewsWeek) {
		this.viewsWeek = viewsWeek;
	}

	public Long getViewsMonth() {
		return viewsMonth;
	}

	public void setViewsMonth(Long viewsMonth) {
		this.viewsMonth = viewsMonth;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getReplyCountDay() {
		return replyCountDay;
	}

	public void setReplyCountDay(Integer replyCountDay) {
		this.replyCountDay = replyCountDay;
	}

	public Short getTopLevel() {
		return topLevel;
	}

	public void setTopLevel(Short topLevel) {
		this.topLevel = topLevel;
	}

	public Short getPrimeLevel() {
		return primeLevel;
	}

	public void setPrimeLevel(Short primeLevel) {
		this.primeLevel = primeLevel;
	}

	public Boolean getStyleBold() {
		return styleBold;
	}

	public void setStyleBold(Boolean styleBold) {
		this.styleBold = styleBold;
	}

	public Boolean getStyleItalic() {
		return styleItalic;
	}

	public void setStyleItalic(Boolean styleItalic) {
		this.styleItalic = styleItalic;
	}

	public String getStyleColor() {
		return styleColor;
	}

	public void setStyleColor(String styleColor) {
		this.styleColor = styleColor;
	}

	public Date getStyleTime() {
		return styleTime;
	}

	
	public void setStyleTime(Date styleTime) {
		this.styleTime = styleTime;
	}

	public String getOuterUrl() {
		return outerUrl;
	}

	public void setOuterUrl(String outerUrl) {
		this.outerUrl = outerUrl;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Boolean getAffix() {
		return affix;
	}

	public void setAffix(Boolean affix) {
		this.affix = affix;
	}

	public Boolean getModeratorReply() {
		return moderatorReply;
	}

	public void setModeratorReply(Boolean moderatorReply) {
		this.moderatorReply = moderatorReply;
	}

	public String getHaveReply() {
		return haveReply;
	}

	public void setHaveReply(String haveReply) {
		this.haveReply = haveReply;
	}

	public Boolean getAllayReply() {
		return allayReply;
	}

	public void setAllayReply(Boolean allayReply) {
		this.allayReply = allayReply;
	}

	public Boolean getHasSofeed() {
		return hasSofeed;
	}

	public void setHasSofeed(Boolean hasSofeed) {
		this.hasSofeed = hasSofeed;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public BbsTopicText getTopicText() {
		return topicText;
	}

	public void setTopicText(BbsTopicText topicText) {
		this.topicText = topicText;
	}

	public BbsPost getFirstPost() {
		return firstPost;
	}

	public void setFirstPost(BbsPost firstPost) {
		this.firstPost = firstPost;
	}

	public com.jeecms.core.entity.CmsSite getSite() {
		return site;
	}

	public void setSite(com.jeecms.core.entity.CmsSite site) {
		this.site = site;
	}

	public BbsForum getForum() {
		return forum;
	}

	public void setForum(BbsForum forum) {
		this.forum = forum;
	}

	public BbsPost getLastPost() {
		return lastPost;
	}

	public void setLastPost(BbsPost lastPost) {
		this.lastPost = lastPost;
	}

	public BbsUser getCreater() {
		return creater;
	}

	public void setCreater(BbsUser creater) {
		this.creater = creater;
	}

	public BbsUser getLastReply() {
		return lastReply;
	}

	public void setLastReply(BbsUser lastReply) {
		this.lastReply = lastReply;
	}

	public BbsTopicCount getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(BbsTopicCount topicCount) {
		this.topicCount = topicCount;
	}

	public Set<BbsTopicType> getTypes() {
		return types;
	}

	public void setTypes(Set<BbsTopicType> types) {
		this.types = types;
	}

	public BbsTopicCharge getTopicCharge() {
		return topicCharge;
	}

	public void setTopicCharge(BbsTopicCharge topicCharge) {
		this.topicCharge = topicCharge;
	}

	public List<BbsTopicRewardFix> getRewardFixs() {
		return rewardFixs;
	}

	public void setRewardFixs(List<BbsTopicRewardFix> rewardFixs) {
		this.rewardFixs = rewardFixs;
	}


	/**
	 * 前台状态
	 * 
	 * @return 3:锁;2:旧;1:新
	 */
	public short getFrontStatus() {
		if (isLocked()) {
			return 3;
		} else if (System.currentTimeMillis() - getLastTime().getTime() > DAY_MILLIS) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * 是否热帖
	 * 
	 * @return
	 */
	public boolean isHot() {
		return getReplyCount() >= 30;
	}

	/**
	 * 是否锁定
	 * 
	 * @return
	 */
	public boolean isLocked() {
		return getStatus() == LOCKED || getForum().isTopicLock(getCreateTime().getTime());
	}

	/**
	 * 是否屏蔽
	 * 
	 * @return
	 */
	public boolean isShield() {
		return getStatus() == SHIELD;
	}

	/**
	 * 样式是否有效
	 * 
	 * @return
	 */
	public boolean isStyle() {
		Date d = getStyleTime();
		if (d == null) {
			return true;
		}
		long time = d.getTime();
		return time - System.currentTimeMillis() > 0;
	}

	public String getUrl() {
		return getSite().getUrlBuffer(true, null, false).append("/").append(getForum().getPath()).append("/")
				.append(getId()).append(getSite().getDynamicSuffix()).toString();
	}

	public String getWholeUrl() {
		return getSite().getUrlBuffer(true, true, false).append("/").append(getForum().getPath()).append("/")
				.append(getId()).append(getSite().getDynamicSuffix()).toString();
	}

	public String getWholeHttpsUrl() {
		return getSite().getHttpsUrlBuffer(true, true, false).append("/").append(getForum().getPath()).append("/")
				.append(getId()).append(getSite().getDynamicSuffix()).toString();
	}

	/**
	 * 获得访问路径前缀。如：http://bbs.jeecms.com/luntan/2
	 * 
	 * @return
	 */
	public StringBuilder getUrlPerfix() {
		return getSite().getUrlBuffer(true, null, false).append("/").append(getForum().getPath()).append("/")
				.append(getId());
	}

	public StringBuilder getWholeUrlPerfix() {
		return getSite().getUrlBuffer(true, true, false).append("/").append(getForum().getPath()).append("/")
				.append(getId());
	}

	public StringBuilder getWholeHttpsUrlPerfix() {
		return getSite().getHttpsUrlBuffer(true, true, false).append("/").append(getForum().getPath()).append("/")
				.append(getId());
	}

	public String getRedirectUrl() {
		String path = getForum().getPath();
		String url = "/" + path + "/" + getId() + getSite().getDynamicSuffix();
		return url;
	}

	public JSONObject convertToJson(Integer https, boolean hasCollect, boolean hasAttent, boolean hasUp, Boolean voted,
			List<BbsVoteItem> items) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("title", getTitle());
		json.put("createTime", DateUtils.parseDateToTimeStr(getCreateTime()));
		json.put("viewCount", getViewCount());
		json.put("viewsDay", getViewsDay());
		json.put("viewsWeek", getViewsWeek());
		json.put("viewsMonth", getViewsMonth());
		json.put("replyCount", getReplyCount());
		json.put("replyCountDay", getReplyCountDay());
		json.put("topLevel", getTopLevel());
		json.put("primeLevel", getPrimeLevel());
		json.put("status", getStatus());
		json.put("hasAttach", getAffix());
		json.put("moderatorReply", getModeratorReply());
		json.put("replyIds", getHaveReply());
		json.put("allowReply", getAllayReply());
		if (https == com.jeecms.bbs.api.Constants.URL_HTTP) {
			json.put("url", getWholeUrl());
			if (getLastPost() != null) {
				json.put("lastPostUrl", getLastPost().getWholeUrl());
			} else {
				json.put("lastPostUrl", "");
			}
		} else {
			json.put("url", getWholeHttpsUrl());
			if (getLastPost() != null) {
				json.put("lastPostUrl", getLastPost().getWholeHttpsUrl());
			} else {
				json.put("lastPostUrl", "");
			}
		}
		if (getLastTime() != null) {
			json.put("lastTime", DateUtils.parseDateToTimeStr(getLastTime()));
		} else {
			json.put("lastTime", "");
		}
		if (getLastReply() != null) {
			json.put("lastReplyId", getLastReply().getId());
			json.put("lastReplyUsername", getLastReply().getUsername());
		} else {
			json.put("lastReplyId", "");
			json.put("lastReplyUsername", "");
		}
		json.put("createrId", getCreater().getId());
		json.put("createrUsername", getCreater().getUsername());
		json.put("forumId", getForum().getId());
		json.put("forumTitle", getForum().getTitle());
		if (getLastPost() != null) {
			json.put("lastPostId", getLastPost().getId());
			json.put("lastPostTitle", getLastPost().getTitle());
		} else {
			json.put("lastPostId", "");
			json.put("lastPostTitle", "");
		}
		if (getFirstPost() != null) {
			json.put("firstPostId", getFirstPost().getId());
			json.put("firstPostTitle", getFirstPost().getTitle());
		} else {
			json.put("firstPostId", "");
			json.put("firstPostTitle", "");
		}
		json.put("hasCollect", hasCollect);
		json.put("hasAttent", hasAttent);
		json.put("collections", getCollections());
		json.put("rewardCount", getRewards());
		json.put("upCount", getUps());
		json.put("attentCount", getAttentions());
		json.put("charge", getCharge());
		json.put("chargeModel", getChargeModel());
		json.put("chargeAmount", getChargeAmount());
		if (voted != null) {
			json.put("voted", voted);
		}
		if (items != null && items.size() > 0) {
			JSONArray voteItemArray = new JSONArray();
			for (BbsVoteItem item : items) {
				voteItemArray.put(item.convertToJson());
			}
			json.put("voteItems", voteItemArray);
		}
		return json;
	}

	public String getUrlWhole() {
		return getUrlDynamic(true);
	}

	public String getUrlDynamic(Boolean whole) {
		CmsSite site = getSite();
		StringBuilder url = site.getUrlBuffer(true, whole, false);
		url.append(SPT).append(getForum().getPath());
		url.append(SPT).append(getId()).append(site.getDynamicSuffix());
		return url.toString();
	}

	public void init() {
		Date now = new Timestamp(System.currentTimeMillis());
		if (getCreateTime() == null) {
			setCreateTime(now);
		}
		if (getLastTime() == null) {
			setLastTime(now);
		}
		if (getPrimeLevel() == null) {
			setPrimeLevel(NORMAL);
		}
		if (getSortTime() == null) {
			setSortTime(now);
		}
		if (getViewCount() == null) {
			setViewCount(0L);
		}
		if (getReplyCount() == null) {
			setReplyCount(0);
		}
		if (getReplyCountDay() == null) {
			setReplyCountDay(0);
		}
		if (getTopLevel() == null) {
			setTopLevel(NORMAL);
		}
		if (getStyleBold() == null) {
			setStyleBold(false);
		}
		if (getStatus() == null) {
			setStatus(NORMAL);
		}
		if (getModeratorReply() == null) {
			setModeratorReply(false);
		}
		if (getAllayReply() == null) {
			setAllayReply(true);
		}
		if (getHasSofeed() == null) {
			setHasSofeed(false);
		}
		if (getViewsDay() == null) {
			setViewsDay(0L);
		}
		if (getViewsWeek() == null) {
			setViewsWeek(0L);
		}
		if (getViewsMonth() == null) {
			setViewsMonth(0L);
		}
		if (getAffix() == null) {
			setAffix(true);
		}
		if (getCheckStatus() == null) {
			setCheckStatus(CHECKED);
		}
		if (getRecommend() == null) {
			setRecommend(TOPIC_NO_RECOMMEND);
		}
	}

	public String getTitle() {
		BbsTopicText text = getTopicText();
		if (text == null) {
			return null;
		} else {
			return replaceSensitivity(text.getTitle()==null?this.title:text.getTitle());
		}
	}

	public void setTitle(String title) {
		BbsTopicText text = getTopicText();
		if (text != null) {
			text.setTitle(title);
		}
	}

	public void setTopicTitle(String title) {
		this.title=title;
	}

	

	public short getCategory() {
		return TOPIC_NORMAL;
	}

	public void addToTypes(BbsTopicType type) {
		Set<BbsTopicType> types = getTypes();
		if (types == null) {
			types = new HashSet<BbsTopicType>();
			setTypes(types);
		}
		types.add(type);
	}

	public Integer getCollections() {
		BbsTopicCount count = getTopicCount();
		if (count == null) {
			return 0;
		} else {
			return count.getCollections();
		}
	}

	public Integer getRewards() {
		BbsTopicCount count = getTopicCount();
		if (count == null) {
			return 0;
		} else {
			return count.getRewards();
		}
	}

	public Integer getUps() {
		BbsTopicCount count = getTopicCount();
		if (count == null) {
			return 0;
		} else {
			return count.getUps();
		}
	}

	public Integer getAttentions() {
		BbsTopicCount count = getTopicCount();
		if (count == null) {
			return 0;
		} else {
			return count.getAttentions();
		}
	}

	

	public boolean getCharge() {
		BbsTopicCharge c = getTopicCharge();
		return c != null && c.getChargeAmount() > 0 && c.getChargeReward().equals(BbsTopicCharge.MODEL_CHARGE);
	}

	public Short getChargeModel() {
		BbsTopicCharge c = getTopicCharge();
		if (c == null) {
			return BbsTopicCharge.MODEL_FREE;
		} else {
			return c.getChargeReward();
		}
	}

	public Double getChargeAmount() {
		BbsTopicCharge charge = getTopicCharge();
		if (charge != null) {
			return charge.getChargeAmount();
		} else {
			return 0d;
		}
	}

	public void addToRewardFixs(Double fixVal) {
		List<BbsTopicRewardFix> list = getRewardFixs();
		if (list == null) {
			list = new ArrayList<BbsTopicRewardFix>();
			setRewardFixs(list);
		}
		BbsTopicRewardFix rewardFix = new BbsTopicRewardFix();
		rewardFix.setFixVal(fixVal);
		list.add(rewardFix);
	}

	public Boolean getRewardPattern() {
		BbsTopicCharge charge = getTopicCharge();
		if (charge != null) {
			return charge.getRewardPattern();
		} else {
			return false;
		}
	}

	public Double getRewardRandomMax() {
		BbsTopicCharge charge = getTopicCharge();
		if (charge != null) {
			return charge.getRewardRandomMax();
		} else {
			return 0d;
		}
	}

	public Double getRewardRandomMin() {
		BbsTopicCharge charge = getTopicCharge();
		if (charge != null) {
			return charge.getRewardRandomMin();
		} else {
			return 0d;
		}
	}

	public Double[] getRewardFixValues() {
		Double[] fixs = null;
		List<BbsTopicRewardFix> list = getRewardFixs();
		if (list != null && list.size() > 0) {
			fixs = new Double[list.size()];
			for (int i = 0; i < list.size(); i++) {
				fixs[i] = list.get(i).getFixVal();
			}
		}
		return fixs;
	}

	public Double getDayAmount() {
		BbsTopicCharge charge = getTopicCharge();
		if (charge != null) {
			return charge.getDayAmount();
		} else {
			return 0d;
		}
	}

	public Double getMonthAmount() {
		BbsTopicCharge charge = getTopicCharge();
		if (charge != null) {
			return charge.getMonthAmount();
		} else {
			return 0d;
		}
	}

	public Double getYearAmount() {
		BbsTopicCharge charge = getTopicCharge();
		if (charge != null) {
			return charge.getYearAmount();
		} else {
			return 0d;
		}
	}

	public Double getTotalAmount() {
		BbsTopicCharge charge = getTopicCharge();
		if (charge != null) {
			return charge.getTotalAmount();
		} else {
			return 0d;
		}
	}

	public Date getLastBuyTime() {
		BbsTopicCharge charge = getTopicCharge();
		if (charge != null) {
			return charge.getLastBuyTime();
		} else {
			return null;
		}
	}

	public String getCreateTimeHtml() {
		Date time = getCreateTime();
		Date now = Calendar.getInstance().getTime();
		if (DateUtils.isInHour(time)) {
			return DateUtils.getDiffIntMinuteTwoDate(time, now) + "分钟前";
		} else if (DateUtils.isToday(time)) {
			return DateUtils.getDiffIntHourTwoDate(time, now) + "小时前";
		} else {
			return DateUtils.parseDateToDateStr(time);
		}
	}

	public String getLastReplyTimeHtml() {
		Date time = getLastTime();
		Date now = Calendar.getInstance().getTime();
		if (DateUtils.isInHour(time)) {
			return DateUtils.getDiffIntMinuteTwoDate(time, now) + "分钟前";
		} else if (DateUtils.isToday(time)) {
			return DateUtils.getDiffIntHourTwoDate(time, now) + "小时前";
		} else {
			return DateUtils.parseDateToDateStr(time);
		}
	}

	public static Integer[] fetchIds(Collection<BbsTopicType> types) {
		if (types == null) {
			return null;
		}
		Integer[] ids = new Integer[types.size()];
		int i = 0;
		for (BbsTopicType c : types) {
			ids[i++] = c.getId();
		}
		return ids;
	}

	public Integer getTypeId() {
		Set<BbsTopicType> typeSet = getTypes();
		if (typeSet != null && typeSet.size() > 0) {
			return typeSet.iterator().next().getId();
		} else {
			return 0;
		}
	}

	public String getTypeIds() {
		StringBuffer buff = new StringBuffer();
		Set<BbsTopicType> typeSet = getTypes();
		for (BbsTopicType t : typeSet) {
			buff.append(t.getId() + ",");
		}
		return buff.toString();
	}
}