package com.jeecms.bbs.entity;

import static com.jeecms.bbs.web.FrontUtils.replaceSensitivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.bbs.api.Constants;
import com.jeecms.bbs.web.StrUtils;
import com.jeecms.common.util.DateUtils;
import com.jeecms.core.bbcode.BbcodeHandler;
import com.jeecms.core.entity.CmsSite;

/**
 * 论坛帖子
 * @author andy_hulibo@163.com
 * 2018年11月2日下午3:12:16
 */
@Entity
@Table(name="bbs_post")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsPost implements Serializable {
	
	/**
	 * 正常状态
	 */
	public static final short NORMAL = 0;
	/**
	 * 屏蔽状态
	 */
	public static final short SHIELD = -1;
	/**
	 * 待审核状态
	 */
	public static final boolean CHECKING = false;
	/**
	 * 审核状态
	 */
	public static final boolean CHECKED = true;
	/**
	 * 帖子的锚点
	 */
	public static final String ANCHOR = "#pid";
	/**
	 * 查询 主题下所有贴
	 */
	public static final Integer OPT_ALL = 1;
	/**
	 * 查询主题下针对题主的贴
	 */
	public static final Integer OPT_TO_AUTHOR = 2;
	
	private static final long serialVersionUID = 7386412084173390279L;

	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 标题
	 */
	@Transient
	private String title;

	/**
	 * 发贴时间
	 */
	@Column(name="CREATE_TIME")
	private Date createTime;

	/**
	 * 发贴IP
	 */
	@Column(name="POSTER_IP")
	private String posterIp;

	/**
	 * 编辑时间
	 */
	@Column(name="EDIT_TIME")
	private Date editTime;

	/**
	 * 编辑者IP
	 */
	@Column(name="EDITER_IP")
	private String editerIp;

	/**
	 * 编辑次数
	 */
	@Column(name="EDIT_COUNT")
	private Integer editCount;

	/**
	 * 第几楼
	 */
	@Column(name="INDEX_COUNT")
	private Integer indexCount;

	/**
	 * 状态
	 */
	@Column(name="STATUS")
	private Short status;

	/**
	 * 是否上传附件
	 */
	@Column(name="IS_AFFIX")
	private Boolean affix;

	/**
	 * 是否有隐藏内容
	 */
	@Column(name="IS_HIDDEN")
	private Boolean hidden;

	/**
	 * 是否匿名
	 */
	@Column(name="ANONYMOUS")
	private Boolean anonymous;

	/**
	 * 发帖来源：1 PC  2手机  3平板
	 */
	@Column(name="post_source")
	private Short postSource;

	/**
	 * 发帖时纬度
	 */
	@Column(name="post_latitude")
	private Float postLatitude;

	/**
	 * 发帖时经度
	 */
	@Column(name="post_longitude")
	private Float postLongitude;
	
	/**
	 * 是否已审核
	 */
	@Column(name="check_status")
	private Boolean checkStatus;

	@OneToOne(mappedBy="post",cascade=CascadeType.ALL)
	private BbsPostText postText;
	
	@OneToOne(mappedBy="post",cascade=CascadeType.ALL)
	private BbsPostCount postCount;

	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;
	
	@ManyToOne
	@JoinColumn(name="CONFIG_ID")
	private BbsConfig config;

	@ManyToOne
	@JoinColumn(name="TOPIC_ID")
	private BbsTopic topic;

	@ManyToOne
	@JoinColumn(name="creater_id")
	private BbsUser creater;
	
	@ManyToOne
	@JoinColumn(name="editer_id")
	private BbsUser editer;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private BbsPost parent;

	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	private Set<BbsGrade> grade;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private Set<Attachment> attachments;
	
	@OneToMany(cascade=CascadeType.REMOVE)
	@JoinColumn(name="parent_id")
	private Set<BbsPost> child;

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}

	public String getPosterIp () {
		return posterIp;
	}

	public void setPosterIp (String posterIp) {
		this.posterIp = posterIp;
	}

	public java.util.Date getEditTime () {
		return editTime;
	}

	public void setEditTime (java.util.Date editTime) {
		this.editTime = editTime;
	}

	public String getEditerIp () {
		return editerIp;
	}

	public void setEditerIp (String editerIp) {
		this.editerIp = editerIp;
	}

	public Integer getEditCount () {
		return editCount;
	}

	public void setEditCount (Integer editCount) {
		this.editCount = editCount;
	}

	public Integer getIndexCount () {
		return indexCount;
	}

	public void setIndexCount (Integer indexCount) {
		this.indexCount = indexCount;
	}

	public Short getStatus () {
		return status;
	}

	public void setStatus (Short status) {
		this.status = status;
	}

	public Boolean getAffix () {
		return affix;
	}

	public void setAffix (Boolean affix) {
		this.affix = affix;
	}

	public Boolean getHidden () {
		return hidden;
	}

	public void setHidden (Boolean hidden) {
		this.hidden = hidden;
	}

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}
	
	public Short getPostSource() {
		return postSource;
	}

	public void setPostSource(Short postSource) {
		this.postSource = postSource;
	}

	public Float getPostLatitude() {
		return postLatitude;
	}

	public void setPostLatitude(Float postLatitude) {
		this.postLatitude = postLatitude;
	}

	public Float getPostLongitude() {
		return postLongitude;
	}

	public void setPostLongitude(Float postLongitude) {
		this.postLongitude = postLongitude;
	}

	public Boolean getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Boolean checkStatus) {
		this.checkStatus = checkStatus;
	}

	public BbsPostText getPostText () {
		return postText;
	}

	public BbsPostCount getPostCount() {
		return postCount;
	}

	public void setPostCount(BbsPostCount postCount) {
		this.postCount = postCount;
	}

	public CmsSite getSite () {
		return site;
	}

	public void setSite (CmsSite site) {
		this.site = site;
	}

	public BbsConfig getConfig () {
		return config;
	}

	public void setConfig (BbsConfig config) {
		this.config = config;
	}

	public BbsTopic getTopic () {
		return topic;
	}

	public void setTopic (BbsTopic topic) {
		this.topic = topic;
	}

	public BbsUser getCreater () {
		return creater;
	}

	public void setCreater (BbsUser creater) {
		this.creater = creater;
	}

	public BbsUser getEditer () {
		return editer;
	}

	public void setEditer (BbsUser editer) {
		this.editer = editer;
	}

	public BbsPost getParent() {
		return parent;
	}

	public void setParent(BbsPost parent) {
		this.parent = parent;
	}

	public Set<BbsGrade> getGrade () {
		return grade;
	}

	public void setGrade (Set<com.jeecms.bbs.entity.BbsGrade> grade) {
		this.grade = grade;
	}


	public Set<Attachment> getAttachments () {
		return attachments;
	}

	public void setAttachments (Set<Attachment> attachments) {
		this.attachments = attachments;
	}


	public Set<BbsPost> getChild() {
		return child;
	}

	public void setChild(Set<BbsPost> child) {
		this.child = child;
	}


	public JSONObject convertToJson(Integer https) throws JSONException {
		JSONObject json=new JSONObject();
		json.put("id", getId());
		json.put("title", getTitle());
		String urlPrefix="";
		CmsSite site=getSite();
		json.put("createTime", DateUtils.parseDateToTimeStr(getCreateTime()));
		json.put("posterIp", getPosterIp());
		json.put("position", getIndexCount());
		json.put("status", getStatus());
		json.put("hasAttach", getAffix());
		json.put("hidden", getHidden());
		json.put("anonymous", getAnonymous());
		//来源1 PC 2手机 3平板
		json.put("postSource", getPostSource());
		if(https==com.jeecms.bbs.api.Constants.URL_HTTP){
			json.put("url", getWholeUrl());
			urlPrefix=site.getUrlPrefixWithNoDefaultPort();
		}else{
			json.put("url", getWholeHttpsUrl());
			urlPrefix=site.getSafeUrlPrefix();
		}
		json.put("content", replaceTxt(getContentHtml(), urlPrefix));
		json.put("quoteContent", "[quote]"+replaceTxt(getQuoteContent(), urlPrefix)+"[/quote]");
		json.put("createrId", getCreater().getId());
		json.put("createrUsername", getCreater().getUsername());
		
		json.put("topicId", getTopic().getId());
		json.put("topicTitle", getTopic().getTitle());
		
		if(getParent()!=null){
			json.put("parentId", getParent().getId());
			json.put("parentTitle", getParent().getTitle());
		}else{
			json.put("parentId", "");
			json.put("parentTitle", "");
		}
		if(getPostLongitude()!=null){
			json.put("postLongitude", getPostLongitude());
		}else{
			json.put("postLongitude", "");
		}
		if(getPostLatitude()!=null){
			json.put("postLatitude", getPostLatitude());
		}else{
			json.put("postLatitude", "");
		}
		JSONArray gradeArray=new JSONArray();
		if(getGrade()!=null&&getGrade().size()>0){
			for(BbsGrade grade:getGrade()){
				gradeArray.put(grade.convertToJson());
			}
		}
		json.put("grades", gradeArray);
		json.put("upCount", getUps());
		json.put("replyCount", getReplys());
		return json;
	}
	
	private String replaceTxt(String txt,String urlPrefix){
		if(StringUtils.isNotBlank(txt)){
			//替换图片地址
			List<String>imgUrls=com.jeecms.common.util.StrUtils.getImageSrc(txt);
			for(String img:imgUrls){
				String imgRealUrl=img;
				if(!img.startsWith("http://")&&!img.startsWith("https://")&&!img.startsWith("ftp://")){
					imgRealUrl= urlPrefix+img;
				}
				txt=txt.replace(img, imgRealUrl);
			}
			//替换链接地址
			List<String>linkUrls=com.jeecms.common.util.StrUtils.getLinkSrc(txt);
			for(String linkUrl:linkUrls){
				String linkRealUrl=linkUrl;
				if(!linkUrl.startsWith("http://")&&!linkUrl.startsWith("https://")&&!linkUrl.startsWith("ftp://")){
					linkRealUrl= urlPrefix+linkUrl;
				}
				txt=txt.replace(linkUrl, linkRealUrl);
			}
			//图片替换特殊标记，客户端自己下载图片并替换标记
			//<img[^>]*/>
			String regular="<img(.*?)src=\"(.*?)/>";  
	        String img_pre="<img(.*?)src=\"";
	        String img_sub="\"(.*?)/>";
	        Pattern p=Pattern.compile(regular,Pattern.CASE_INSENSITIVE);
	        Matcher m = p.matcher(txt);  
	        String src = null;  
	        String newSrc=null;
	        while (m.find()) {  
	        	src=m.group();
	        	newSrc=src.replaceAll(img_pre, Constants.API_PLACEHOLDER_IMAGE_BEGIN)
	        			.replaceAll(img_sub,  Constants.API_PLACEHOLDER_IMAGE_END).trim();
	        	txt=txt.replace(src, newSrc);
	        }  
	        //原图
	        String originImgregular="<a class=\"zoomImg\"(.*?)href=\"(.*?)\"(.*?)</a>";  
	        Pattern originImgp=Pattern.compile(originImgregular,Pattern.CASE_INSENSITIVE);
	        Matcher originImgm = originImgp.matcher(txt);  
	        String originsrc = null;  
	        while (originImgm.find()) {  
	        	originsrc=originImgm.group();
	        	txt=txt.replace(originsrc, "");
	        }  
	        
	        String linkregular="<a(.*?)href=\"(.*?)\"(.*?)</a>";  
	        String link_pre="<a(.*?)href=\"";
	        String link_sub="\"(.*?)</a>";
	        Pattern linkp=Pattern.compile(linkregular,Pattern.CASE_INSENSITIVE);
	        Matcher linkm = linkp.matcher(txt);  
	        String linksrc = null;  
	        String linknewSrc=null;
	        while (linkm.find()) {  
	        	linksrc=linkm.group();
	        	linknewSrc=linksrc.replaceAll(link_pre, Constants.API_PLACEHOLDER_LINK_BEGIN)
	        			.replaceAll(link_sub,  Constants.API_PLACEHOLDER_LINK_END).trim();
	        	txt=txt.replace(linksrc, linknewSrc);
	        }  
		}
		return txt;
	}

	/**
	 * URL地址
	 * 
	 * @return
	 */
	public String getUrl() {
		Integer index = getIndexCount();
		if (index == 1) {
			// 第一个帖子和主题url相同
			return getTopic().getUrl();
		} else {
			StringBuilder buff = getTopic().getUrlPerfix();
			Integer pageSize = getConfig().getPostCountPerPage();
			int pageNo = (index - 1) / pageSize;
			if (pageNo > 0) {
				buff.append('_').append(pageNo + 1);
			}
			buff.append(getSite().getDynamicSuffix()).append(ANCHOR).append(
					getId());
			return buff.toString();
		}
	}
	
	public String getWholeUrl() {
		Integer index = getIndexCount();
		if (index == 1) {
			// 第一个帖子和主题url相同
			return getTopic().getWholeUrl();
		} else {
			StringBuilder buff = getTopic().getWholeUrlPerfix();
			Integer pageSize = getConfig().getPostCountPerPage();
			int pageNo = (index - 1) / pageSize;
			if (pageNo > 0) {
				buff.append('_').append(pageNo + 1);
			}
			buff.append(getSite().getDynamicSuffix()).append(ANCHOR).append(
					getId());
			return buff.toString();
		}
	}
	
	public String getWholeHttpsUrl() {
		Integer index = getIndexCount();
		if (index == 1) {
			// 第一个帖子和主题url相同
			return getTopic().getWholeHttpsUrl();
		} else {
			StringBuilder buff = getTopic().getWholeHttpsUrlPerfix();
			Integer pageSize = getConfig().getPostCountPerPage();
			int pageNo = (index - 1) / pageSize;
			if (pageNo > 0) {
				buff.append('_').append(pageNo + 1);
			}
			buff.append(getSite().getDynamicSuffix()).append(ANCHOR).append(
					getId());
			return buff.toString();
		}
	}

	public String getRedirectUrl() {
		String path = getTopic().getForum().getPath();
		String url = "/" + path + "/" + getTopic().getId()
				+ getSite().getDynamicSuffix();
		return url;
	}

	/**
	 * 是否楼主
	 * 
	 * @return
	 */
	public boolean isFirst() {
		return getTopic().getFirstPost().equals(this);
	}

	public boolean isShield() {
		if (getStatus() == SHIELD) {
			return true;
		}
		return false;
	}

	/**
	 * 获得标题
	 * 
	 * @return
	 */
	public String getTitle() {
		BbsPostText text = getPostText();
		if (text == null) {
			return null;
		} else {
			return replaceSensitivity(text.getTitle()==null?this.title:text.getTitle());
		}
	}

	/**
	 * 获得内容
	 * 
	 * @return
	 */
	public String getContent() {
		BbsPostText text = getPostText();
		if (text == null) {
			return null;
		} else {
			return replaceSensitivity(text.getContent());
		}
	}
	
	/**
	 * 获得转换后的内容
	 * 
	 * @return
	 */
	public String getContentHtml() {
		String s = getContent();
		if (StringUtils.isBlank(s)) {
			return "";
		} else {
			if (getAffix()) {
				Set<Attachment> att = getAttachments();
				for (Attachment t : att) {
					String oldcontent = "[attachment]" + t.getId()
							+ "[/attachment]";
					if (t.getPicture()) {
						String newcontent = "[img]" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "[/img]";
						/*
						String newcontent ="[zoomImg]" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "[/zoomImg]"
								+"[img]" + getSite().getUrl()
								+ t.getZoomPicPath().substring(1) + "[/img]";
								*/
						s = StrUtils.replace(s, oldcontent, newcontent);
					} else {
						String newcontent = "[url=" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "]"
								+ t.getFileName() + "[/url]";
						s = StrUtils.replace(s, oldcontent, newcontent);
					}
				}
			}
			if (getHidden()) {
				List<String> list = getHideContent(s);
				for (String str : list) {
					s = StrUtils.replace(s, "[hide]" + str + "[/hide]",
							"[quote]" + str + "[/quote]");
				}
			}
			return BbcodeHandler.toHtml(s);
		}
	}
	
	public String getAppContentHtml() {
		String s = getContent();
		if (StringUtils.isBlank(s)) {
			return "";
		} else {
			if (getAffix()) {
				Set<Attachment> att = getAttachments();
				for (Attachment t : att) {
					String oldcontent = "[attachment]" + t.getId()+ "[/attachment]";
					if (t.getPicture()) {
						//String newcontent = "[img]" + getSite().getAppUrl()+ t.getFilePath().substring(1) + "[/img]";
						String newcontent = "[img]" + getSite().getAppUrl()
								+ t.getZoomPicPath().substring(1) + "[/img]";
						s = StrUtils.replace(s, oldcontent, newcontent);
					} else {
						String newcontent = "[url=" + getSite().getAppUrl() + t.getFilePath().substring(1) + "]" + t.getFileName() + "[/url]";
						s = StrUtils.replace(s, oldcontent, newcontent);
					}
				}
			}
			if (getHidden()) {
				List<String> list = getHideContent(s);
				for (String str : list) {
					s = StrUtils.replace(s, "[hide]" + str + "[/hide]","[quote]" + str + "[/quote]");
				}
			}
			return BbcodeHandler.toHtml(s);
		}
	}
	
	public String getShortContentHtml() {
		String s = getContent();
		if (StringUtils.isBlank(s)) {
			return "";
		} else {
			if (getAffix()) {
				Set<Attachment> att = getAttachments();
				for (Attachment t : att) {
					String oldcontent = "[attachment]" + t.getId()
							+ "[/attachment]";
					s = StrUtils.replace(s, oldcontent, "");
				}
			}
			if(isShield()){
				return getShieldContent();
			}
			if (getHidden()) {
				List<String> list = getHideContent(s);
				for (String str : list) {
					s = StrUtils.replace(s, "[hide]" + str + "[/hide]",
							"这是隐藏内容.需要回复才能浏览");
				}
			}
			//return BbcodeHandler.toHtml(s);
			return s;
		}
	}

	/**
	 * 获取引用内容
	 * 
	 * @return
	 */
	public String getQuoteContent() {
		String s = getContent();
		if (getHidden()) {
			List<String> list = getHideContent(s);
			if (list != null) {
				for (String str : list) {
					String newcontent = "[color=red]此处是被引用的隐藏帖[/color]";
					s = StrUtils.replace(s, "[hide]" + str + "[/hide]",
							newcontent);
				}
			}
		}
		if (haveQuote(s)) {
			s = s.substring(s.lastIndexOf("[/quote]") + 8);
		}
		return s;
	}
	
	public String getShieldContent(){
		if(isShield()){
			return "该贴已经被屏蔽，仅版主可见";
		}else{
			return getContentHtml();
		}
	} 

	/**
	 * 分离隐藏内容
	 * 
	 * @param s
	 * 
	 * @return
	 */
	private List<String> getHideContent(String content) {
		String ems = "\\[hide\\]([\\s\\S]*?)\\[/hide\\]";
		Matcher matcher = Pattern.compile(ems).matcher(content);
		List<String> list = new ArrayList<String>();
		while (matcher.find()) {
			String url = matcher.group(1);
			list.add(url);
		}
		return list;
	}

	private boolean haveQuote(String content) {
		String ems = "\\[quote]([\\s\\S]*)\\[/quote\\]";
		Matcher matcher = Pattern.compile(ems).matcher(content);
		while (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 未回复的隐藏内容
	 * 
	 * @return
	 */
	public String getHideContent() {
		String s = getContent();
		if (StringUtils.isBlank(s)) {
			return "";
		} else {
			if (getAffix()) {
				Set<Attachment> att = getAttachments();
				for (Attachment t : att) {
					String oldcontent = "[attachment]" + t.getId()
							+ "[/attachment]";
					if (t.getPicture()) {
						String newcontent = "[img]" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "[/img]";
						/*
						String newcontent = "[zoomImg]" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "[/zoomImg]"
								+"[img]" + getSite().getUrl()
								+ t.getZoomPicPath().substring(1) + "[/img]";
								*/
						s = StrUtils.replace(s, oldcontent, newcontent);
					} else {
						String newcontent = "[url=" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "]"
								+ t.getFileName() + "[/url]";
						s = StrUtils.replace(s, oldcontent, newcontent);
					}
				}
			}
			if (getHidden()) {
				List<String> list = getHideContent(s);
				for (String str : list) {
					s = StrUtils.replace(s, "[hide]" + str + "[/hide]",
							"[quote]这是隐藏内容.需要回复才能浏览[/quote]");
				}
			}
			return BbcodeHandler.toHtml(s);
		}
	}

	/**
	 * 覆盖父类同名方法。增加反向引用
	 */
	public void setPostText(BbsPostText text) {
		if (text != null) {
			text.setPost(this);
		}
		this.postText = text;
	}
	
	public Integer getUps(){
		BbsPostCount count = getPostCount();
		if (count == null) {
			return 0;
		} else {
			return count.getUps();
		}
	}
	
	public Integer getReplys(){
		BbsPostCount count = getPostCount();
		if (count == null) {
			return 0;
		} else {
			return count.getReplys();
		}
	}
	
	public String getCreateTimeHtml(){
		Date time=getCreateTime();
		Date now=Calendar.getInstance().getTime();
		if(DateUtils.isInHour(time)){
			int minute=DateUtils.getDiffIntMinuteTwoDate(time, now);
			if(minute>10){
				return minute+"分钟前";
			}else{
				return "刚刚";
			}
		}else if(DateUtils.isToday(time)){
			int hour=DateUtils.getDiffIntHourTwoDate(time, now);
			if(hour<=0){
				hour=1;
			}
			return hour+"小时前"; 
		}else{
			return DateUtils.parseDateToDateStr(time);
		}
	}
}