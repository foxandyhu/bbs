package com.jeecms.bbs.entity;

import static com.jeecms.bbs.Constants.DAY_MILLIS;
import static com.jeecms.bbs.Constants.TPLDIR_FORUM;
import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;
import static com.jeecms.bbs.Constants.TPL_SUFFIX;
import static com.jeecms.common.web.Constants.INDEX;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;
import com.jeecms.common.hibernate4.PriorityInterface;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.web.springmvc.MessageResolver;
import com.jeecms.core.entity.CmsSite;

/**
 * 论坛板块
 * 
 * @author andy_hulibo@163.com 2018年10月30日下午2:59:08
 */
@Entity
@Table(name = "BBS_FORUM")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsForum implements PriorityInterface, Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:58:43
	 */
	private static final long serialVersionUID = -7056721636846322063L;

	@Id
	@Column(name="FORUM_ID")
	private Integer id;

	@Column(name="PATH")
	private String path;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="KEYWORDS")
	private String keywords;
	
	@Column(name="FORUM_RULE")
	private String forumRule;
	
	@Column(name="TOPIC_LOCK_LIMIT")
	private Integer topicLockLimit;
	
	@Column(name="PRIORITY")
	private Integer priority;
	
	@Column(name="TOPIC_TOTAL")
	private Integer topicTotal;
	
	@Column(name="POST_TOTAL")
	private Integer postTotal;
	
	@Column(name="POST_TODAY")
	private Integer postToday;
	
	@Column(name="OUTER_URL")
	private String outerUrl;
	
	@Column(name="POINT_TOPIC")
	private Integer pointTopic;
	
	@Column(name="POINT_REPLY")
	private Integer pointReply;
	
	@Column(name="POINT_PRIME")
	private Integer pointPrime;
	
	@Column(name="PRESTIGE_TOPIC")
	private Integer prestigeTopic;
	
	@Column(name="PRESTIGE_REPLY")
	private Integer prestigeReply;
	
	@Column(name="PRESTIGE_PRIME0")
	private Integer prestigePrime0;
	
	@Column(name="PRESTIGE_PRIME1")
	private Integer prestigePrime1;
	
	@Column(name="PRESTIGE_PRIME2")
	private Integer prestigePrime2;
	
	@Column(name="PRESTIGE_PRIME3")
	private Integer prestigePrime3;
	
	@Column(name="POINT_AVAILABLE")
	private Boolean pointAvailable;
	
	@Column(name="PRESTIGE_AVAILABLE")
	private Boolean prestigeAvailable;
	
	@Column(name="LAST_TIME")
	private Date lastTime;
	
	@Column(name="moderators")
	private String moderators;
	
	@Column(name="group_views")
	private String groupViews;
	
	@Column(name="group_topics")
	private String groupTopics;
	
	@Column(name="group_replies")
	private String groupReplies;
	
	@Column(name="support_reward")
	private Boolean supportReward;
		
	@Column(name="topic_need_check")
	private Boolean topicNeedCheck;
	
	@Column(name="post_need_check")
	private Boolean postNeedCheck;
	
	@ManyToOne
	@JoinColumn(name="POST_ID")
	private BbsPost lastPost;
	
	@ManyToOne
	@JoinColumn(name="replyer_id")
	private BbsUser lastReply;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private BbsCategory category;
	
	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;

	@OneToOne(mappedBy="forum",cascade=CascadeType.REMOVE)
	private BbsForumExt forumExt;

	@OneToMany(mappedBy = "forum", cascade = CascadeType.ALL)
	private Set<BbsPostType> postTypes;

	@Transient
    private String tplForum;
    @Transient
    private String tplTopic;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getForumRule() {
		return forumRule;
	}

	public void setForumRule(String forumRule) {
		this.forumRule = forumRule;
	}

	public Integer getTopicLockLimit() {
		return topicLockLimit;
	}

	public void setTopicLockLimit(Integer topicLockLimit) {
		this.topicLockLimit = topicLockLimit;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getTopicTotal() {
		return topicTotal;
	}

	public void setTopicTotal(Integer topicTotal) {
		this.topicTotal = topicTotal;
	}

	public Integer getPostTotal() {
		return postTotal;
	}

	public void setPostTotal(Integer postTotal) {
		this.postTotal = postTotal;
	}

	public Integer getPostToday() {
		return postToday;
	}

	public void setPostToday(Integer postToday) {
		this.postToday = postToday;
	}

	public String getOuterUrl() {
		return outerUrl;
	}

	public void setOuterUrl(String outerUrl) {
		this.outerUrl = outerUrl;
	}

	public Integer getPointTopic() {
		return pointTopic;
	}

	public void setPointTopic(Integer pointTopic) {
		this.pointTopic = pointTopic;
	}

	public Integer getPointReply() {
		return pointReply;
	}

	public void setPointReply(Integer pointReply) {
		this.pointReply = pointReply;
	}

	public Integer getPointPrime() {
		return pointPrime;
	}

	public void setPointPrime(Integer pointPrime) {
		this.pointPrime = pointPrime;
	}

	public Integer getPrestigePrime0() {
		return prestigePrime0;
	}

	public void setPrestigePrime0(Integer prestigePrime0) {
		this.prestigePrime0 = prestigePrime0;
	}

	public Integer getPrestigeTopic() {
		return prestigeTopic;
	}

	public void setPrestigeTopic(Integer prestigeTopic) {
		this.prestigeTopic = prestigeTopic;
	}

	public Integer getPrestigeReply() {
		return prestigeReply;
	}

	public void setPrestigeReply(Integer prestigeReply) {
		this.prestigeReply = prestigeReply;
	}

	public Integer getPrestigePrime1() {
		return prestigePrime1;
	}

	public void setPrestigePrime1(Integer prestigePrime1) {
		this.prestigePrime1 = prestigePrime1;
	}

	public Integer getPrestigePrime2() {
		return prestigePrime2;
	}

	public void setPrestigePrime2(Integer prestigePrime2) {
		this.prestigePrime2 = prestigePrime2;
	}

	public Integer getPrestigePrime3() {
		return prestigePrime3;
	}

	public void setPrestigePrime3(Integer prestigePrime3) {
		this.prestigePrime3 = prestigePrime3;
	}

	public Boolean getPointAvailable() {
		return pointAvailable;
	}

	public void setPointAvailable(Boolean pointAvailable) {
		this.pointAvailable = pointAvailable;
	}

	public Boolean getPrestigeAvailable() {
		return prestigeAvailable;
	}

	public void setPrestigeAvailable(Boolean prestigeAvailable) {
		this.prestigeAvailable = prestigeAvailable;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getModerators() {
		return moderators;
	}

	public void setModerators(String moderators) {
		this.moderators = moderators;
	}

	public String getGroupViews() {
		return groupViews;
	}

	public void setGroupViews(String groupViews) {
		this.groupViews = groupViews;
	}

	public String getGroupTopics() {
		return groupTopics;
	}

	public void setGroupTopics(String groupTopics) {
		this.groupTopics = groupTopics;
	}

	public String getGroupReplies() {
		return groupReplies;
	}

	public void setGroupReplies(String groupReplies) {
		this.groupReplies = groupReplies;
	}

	public Boolean getSupportReward() {
		return supportReward;
	}

	public void setSupportReward(Boolean supportReward) {
		this.supportReward = supportReward;
	}

	public void setTplForum(String tplForum) {
		this.tplForum = tplForum;
	}

	public void setTplTopic(String tplTopic) {
		this.tplTopic = tplTopic;
	}

	public Boolean getTopicNeedCheck() {
		return topicNeedCheck;
	}

	public void setTopicNeedCheck(Boolean topicNeedCheck) {
		this.topicNeedCheck = topicNeedCheck;
	}

	public Boolean getPostNeedCheck() {
		return postNeedCheck;
	}

	public void setPostNeedCheck(Boolean postNeedCheck) {
		this.postNeedCheck = postNeedCheck;
	}

	public BbsPost getLastPost() {
		return lastPost;
	}

	public void setLastPost(BbsPost lastPost) {
		this.lastPost = lastPost;
	}

	public BbsUser getLastReply() {
		return lastReply;
	}

	public void setLastReply(BbsUser lastReply) {
		this.lastReply = lastReply;
	}

	public BbsCategory getCategory() {
		return category;
	}

	public void setCategory(BbsCategory category) {
		this.category = category;
	}

	public CmsSite getSite() {
		return site;
	}

	public void setSite(CmsSite site) {
		this.site = site;
	}

	public BbsForumExt getForumExt() {
		return forumExt;
	}

	public void setForumExt(BbsForumExt forumExt) {
		this.forumExt = forumExt;
	}

	public Set<BbsPostType> getPostTypes() {
		return postTypes;
	}

	public void setPostTypes(Set<BbsPostType> postTypes) {
		this.postTypes = postTypes;
	}

	public JSONObject convertToJson(Integer https) throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getPath() != null) {
			json.put("path", getPath());
		} else {
			json.put("path", "");
		}
		if (getTitle() != null) {
			json.put("title", getTitle());
		} else {
			json.put("title", "");
		}
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
				json.put("lastPostUrl", getLastPost().getWholeUrl());
			} else {
				json.put("lastPostUrl", "");
			}
		}
		json.put("priority", getPriority());
		if (StringUtils.isNotBlank(getDescription())) {
			json.put("description", getDescription());
		} else {
			json.put("description", "");
		}
		if (StringUtils.isNotBlank(getKeywords())) {
			json.put("keywords", getKeywords());
		} else {
			json.put("keywords", "");
		}
		if (StringUtils.isNotBlank(getForumRule())) {
			json.put("forumRule", getForumRule());
		} else {
			json.put("forumRule", "");
		}
		if (getTopicTotal() != null) {
			json.put("topicTotal", getTopicTotal());
		} else {
			json.put("topicTotal", "0");
		}
		if (getPostTotal() != null) {
			json.put("postTotal", getPostTotal());
		} else {
			json.put("postTotal", 0);
		}
		if (getPostToday() != null) {
			json.put("postToday", getPostToday());
		} else {
			json.put("postToday", 0);
		}
		if (getPointTopic() != null) {
			json.put("pointTopic", getPointTopic());
		} else {
			json.put("pointTopic", 0);
		}
		if (getPointReply() != null) {
			json.put("pointReply", getPointReply());
		} else {
			json.put("pointReply", 0);
		}
		if (getPointPrime() != null) {
			json.put("pointPrime", getPointPrime());
		} else {
			json.put("pointPrime", "");
		}
		if (getLastTime() != null) {
			json.put("lastTime", DateUtils.parseDateToTimeStr(getLastTime()));
		} else {
			json.put("lastTime", "");
		}
		if (StringUtils.isNotBlank(getModerators())) {
			json.put("moderators", getModerators());
		} else {
			json.put("moderators", "");
		}
		if (getSupportReward() != null) {
			json.put("supportReward", getSupportReward());
		} else {
			json.put("supportReward", false);
		}
		json.put("categoryId", getCategory().getId());
		json.put("categoryTitle", getCategory().getTitle());
		if (getLastReply() != null) {
			json.put("lastReplyId", getLastReply().getId());
			json.put("lastReplyUsername", getLastReply().getUsername());
		} else {
			json.put("lastReplyId", "");
			json.put("lastReplyUsername", "");
		}
		if (getLastPost() != null) {
			json.put("lastPostId", getLastPost().getId());
			json.put("lastPostTitle", getLastPost().getTitle());
		} else {
			json.put("lastPostId", "");
			json.put("lastPostTitle", "");
		}
		if (StringUtils.isNotBlank(getOuterUrl())) {
			json.put("outerUrl", getOuterUrl());
		} else {
			json.put("outerUrl", "");
		}
		if (StringUtils.isNotBlank(getGroupViews())) {
			json.put("groupViews", getGroupViews());
		} else {
			json.put("groupViews", "");
		}
		if (StringUtils.isNotBlank(getGroupTopics())) {
			json.put("groupTopics", getGroupTopics());
		} else {
			json.put("groupTopics", "");
		}
		if (StringUtils.isNotBlank(getGroupReplies())) {
			json.put("groupReplies", getGroupReplies());
		} else {
			json.put("groupReplies", "");
		}
		if (StringUtils.isNotBlank(getTplForum())) {
			json.put("tplForum", getTplForum());
		} else {
			json.put("tplForum", "");
		}
		if (StringUtils.isNotBlank(getTplTopic())) {
			json.put("tplTopic", getTplTopic());
		} else {
			json.put("tplTopic", "");
		}
		if (StringUtils.isNotBlank(getTplMobileForum())) {
			json.put("tplMobileForum", getTplMobileForum());
		} else {
			json.put("tplMobileForum", "");
		}
		if (StringUtils.isNotBlank(getTplMobileTopic())) {
			json.put("tplMobileTopic", getTplMobileTopic());
		} else {
			json.put("tplMobileTopic", "");
		}
		json.put("prestigeTopic", getPrestigeTopic());
		json.put("prestigeReply", getPrestigeReply());
		json.put("prestigePrime0", getPrestigePrime0());
		json.put("prestigePrime1", getPrestigePrime1());
		if (getTopicNeedCheck() != null) {
			json.put("topicNeedCheck", getTopicNeedCheck());
		} else {
			json.put("topicNeedCheck", false);
		}
		if (getPostNeedCheck() != null) {
			json.put("postNeedCheck", getPostNeedCheck());
		} else {
			json.put("postNeedCheck", false);
		}
		return json;
	}

	public void init() {
		if (getPointPrime() == null) {
			setPointPrime(0);
		}
		if (getPointReply() == null) {
			setPointReply(0);
		}
		if (getPointTopic() == null) {
			setPointTopic(0);
		}
		if (getPostToday() == null) {
			setPostToday(0);
		}
		if (getPostTotal() == null) {
			setPostTotal(0);
		}
		if (getTopicLockLimit() == null) {
			setTopicLockLimit(0);
		}
		if (getTopicTotal() == null) {
			setTopicTotal(0);
		}
		if (getPrestigeAvailable() == null) {
			setPrestigeAvailable(true);
		}
		if (getPointAvailable() == null) {
			setPointAvailable(true);
		}
		if (getSupportReward() == null) {
			setPointAvailable(false);
		}
		if (getPrestigePrime1() == null) {
			setPrestigePrime1(0);
		}
		if (getPrestigePrime2() == null) {
			setPrestigePrime2(getPrestigePrime1());
		}
		if (getPrestigePrime3() == null) {
			setPrestigePrime3(getPrestigePrime1());
		}
		if (getTopicLockLimit() == null) {
			setTopicLockLimit(0);
		}
		if (getPrestigeTopic() == null) {
			setPrestigeTopic(0);
		}
		if (getPrestigePrime0() == null) {
			setPrestigePrime0(0);
		}
		if (getPrestigeReply() == null) {
			setPrestigeReply(0);
		}
		if (getSupportReward() == null) {
			setSupportReward(false);
		}
		if (getPriority() == null) {
			setPriority(10);
		}
		if (getTopicNeedCheck() == null) {
			setTopicNeedCheck(false);
		}
		if (getPostNeedCheck() == null) {
			setPostNeedCheck(false);
		}
	}

	/**
	 * 获得访问路径。如：http://bbs.jeecms.com/luntan/index.htm
	 * 
	 * @return
	 */
	public String getUrl() {
		String url = getOuterUrl();
		if (!StringUtils.isBlank(url)) {
			// 外部链接
			if (url.startsWith("http://")) {
				return url;
			} else if (url.startsWith("/")) {
				return getSite().getUrl() + url;
			} else {
				return "http://" + url;
			}
		} else {
			return getSite().getUrlBuffer(true, null, false).append("/").append(getPath()).append("/").append(INDEX)
					.append(getSite().getDynamicSuffix()).toString();
		}
	}

	public String getWholeUrl() {
		String url = getOuterUrl();
		if (!StringUtils.isBlank(url)) {
			// 外部链接
			if (url.startsWith("http://")) {
				return url;
			} else if (url.startsWith("/")) {
				return getSite().getUrl() + url;
			} else {
				return "http://" + url;
			}
		} else {
			return getSite().getUrlBuffer(true, true, false).append("/").append(getPath()).append("/").append(INDEX)
					.append(getSite().getDynamicSuffix()).toString();
		}
	}

	public String getHttpsUrl() {
		String url = getOuterUrl();
		if (!StringUtils.isBlank(url)) {
			// 外部链接
			if (url.startsWith("https://")) {
				return url;
			} else if (url.startsWith("/")) {
				return getSite().getUrl() + url;
			} else {
				return "https://" + url;
			}
		} else {
			return getSite().getHttpsUrlBuffer(true, null, false).append("/").append(getPath()).append("/")
					.append(INDEX).append(getSite().getDynamicSuffix()).toString();
		}
	}

	public String getWholeHttpsUrl() {
		String url = getOuterUrl();
		if (!StringUtils.isBlank(url)) {
			// 外部链接
			if (url.startsWith("https://")) {
				return url;
			} else if (url.startsWith("/")) {
				return getSite().getUrl() + url;
			} else {
				return "https://" + url;
			}
		} else {
			return getSite().getHttpsUrlBuffer(true, true, false).append("/").append(getPath()).append("/")
					.append(INDEX).append(getSite().getDynamicSuffix()).toString();
		}
	}

	public String getRedirectUrl() {
		String url = "/" + getPath() + "/" + INDEX + getSite().getDynamicSuffix();
		return url;
	}

	/**
	 * 是否锁定主题
	 * 
	 * @param time
	 *            主题发表时间
	 * @return
	 */
	public boolean isTopicLock(long time) {
		if (getTopicLockLimit() == 0) {
			return false;
		}
		return System.currentTimeMillis() - time > getTopicLockLimit() * DAY_MILLIS;
	}

	public String getTplForumOrDef(HttpServletRequest request) {
		String tpl = getTplForum();
		if (!StringUtils.isBlank(tpl)) {
			return tpl;
		} else {
			return getTplForumDef(getSite().getSolutionPath(), true, request);
		}
	}

	public String getMobileTplForumOrDef(HttpServletRequest request) {
		String tpl = getTplMobileForum();
		if (!StringUtils.isBlank(tpl)) {
			return tpl;
		} else {
			return getTplForumDef(getSite().getMobileSolutionPath(), true, request);
		}
	}

	public String getTplTopicOrDef(HttpServletRequest request) {
		String tpl = getTplTopic();
		if (!StringUtils.isBlank(tpl)) {
			return tpl;
		} else {
			return getTplTopicDef(getSite().getSolutionPath(), true, request);
		}
	}

	public String getMobileTplTopicOrDef(HttpServletRequest request) {
		String tpl = getTplMobileTopic();
		if (!StringUtils.isBlank(tpl)) {
			return tpl;
		} else {
			return getTplTopicDef(getSite().getMobileSolutionPath(), true, request);
		}
	}

	public static String getTplTopicDef(String sol, boolean def, HttpServletRequest request) {
		StringBuilder t = new StringBuilder();
		t.append(sol).append("/");
		t.append(TPLDIR_TOPIC);
		t.append("/");
		t.append(MessageResolver.getMessage(request, "tpl.topic"));
		if (def) {
			t.append(TPL_SUFFIX);
		}
		return t.toString();
	}

	public static String getTplForumDef(String sol, boolean def, HttpServletRequest request) {
		StringBuilder t = new StringBuilder();
		t.append(sol).append("/");
		t.append(TPLDIR_FORUM);
		t.append("/");
		t.append(MessageResolver.getMessage(request, "tpl.forum"));
		if (def) {
			t.append(TPL_SUFFIX);
		}
		return t.toString();
	}

	public String getTplForum() {
		BbsForumExt ext = getForumExt();
		if (ext != null) {
			return ext.getTplForum();
		} else {
			return this.tplForum;
		}
	}

	public String getTplTopic() {
		BbsForumExt ext = getForumExt();
		if (ext != null) {
			return ext.getTplTopic();
		} else {
			return this.tplTopic;
		}
	}

	public String getTplMobileForum() {
		BbsForumExt ext = getForumExt();
		if (ext != null) {
			return ext.getTplMobileForum();
		} else {
			return null;
		}
	}

	public String getTplMobileTopic() {
		BbsForumExt ext = getForumExt();
		if (ext != null) {
			return ext.getTplMobileTopic();
		} else {
			return null;
		}
	}
}