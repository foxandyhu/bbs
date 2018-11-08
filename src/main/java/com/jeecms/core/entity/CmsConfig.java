package com.jeecms.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * CMS配置
 */
@Entity
@Table(name="jc_config")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class CmsConfig implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:12:08
	 */
	private static final long serialVersionUID = 6474978314877267137L;

	public static final String REWARD_FIX_PREFIX = "reward_fix_";

	@Id
	@Column(name="config_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="context_path")
	private String contextPath;
	
	@Column(name="servlet_point")
	private String servletPoint;
	
	@Column(name="port")
	private Integer port;
	
	@Column(name="db_file_uri")
	private String dbFileUri;
	
	@Column(name="is_upload_to_db")
	private Boolean uploadToDb;
	
	@Column(name="def_img")
	private String defImg;
	
	@Column(name="login_url")
	private String loginUrl;
	
	@Column(name="process_url")
	private String processUrl;
	
	@Column(name="count_clear_time")
	private Date countClearTime;
	
	@Column(name="count_copy_time")
	private Date countCopyTime;
	
	@Column(name="download_code")
	private String downloadCode;
	
	@Column(name="download_time")
	private Integer downloadTime;
	
	@Column(name="allow_suffix")
	private String allowSuffix;

	@Embedded
	private MarkConfig m_markConfig;

	@ElementCollection
	@CollectionTable(name="jc_config_attr",joinColumns=@JoinColumn(name="config_id"))
	@MapKeyColumn(name="attr_name")
	@Column(name="attr_value")
	private Map<String, String> attr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getServletPoint() {
		return servletPoint;
	}

	public void setServletPoint(String servletPoint) {
		this.servletPoint = servletPoint;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getDbFileUri() {
		return dbFileUri;
	}

	public void setDbFileUri(String dbFileUri) {
		this.dbFileUri = dbFileUri;
	}

	public Boolean getUploadToDb() {
		return uploadToDb;
	}

	public void setUploadToDb(Boolean uploadToDb) {
		this.uploadToDb = uploadToDb;
	}

	public String getDefImg() {
		return defImg;
	}

	public void setDefImg(String defImg) {
		this.defImg = defImg;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getProcessUrl() {
		return processUrl;
	}

	public void setProcessUrl(String processUrl) {
		this.processUrl = processUrl;
	}

	public Date getCountClearTime() {
		return countClearTime;
	}

	public void setCountClearTime(Date countClearTime) {
		this.countClearTime = countClearTime;
	}

	public Date getCountCopyTime() {
		return countCopyTime;
	}

	public void setCountCopyTime(Date countCopyTime) {
		this.countCopyTime = countCopyTime;
	}

	public String getDownloadCode() {
		return downloadCode;
	}

	public void setDownloadCode(String downloadCode) {
		this.downloadCode = downloadCode;
	}

	public Integer getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(Integer downloadTime) {
		this.downloadTime = downloadTime;
	}

	public String getAllowSuffix() {
		return allowSuffix;
	}

	public void setAllowSuffix(String allowSuffix) {
		this.allowSuffix = allowSuffix;
	}

	public com.jeecms.core.entity.MarkConfig getMarkConfig() {
		return m_markConfig;
	}

	public void setMarkConfig(com.jeecms.core.entity.MarkConfig m_markConfig) {
		this.m_markConfig = m_markConfig;
	}

	public Map<String, String> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, String> attr) {
		this.attr = attr;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getContextPath())) {
			json.put("contextPath", getContextPath());
		} else {
			json.put("contextPath", "");
		}
		if (getPort() != null) {
			json.put("port", getPort());
		} else {
			json.put("port", "");
		}
		if (StringUtils.isNotBlank(getDbFileUri())) {
			json.put("dbFileUri", getDbFileUri());
		} else {
			json.put("dbFileUri", "");
		}
		if (getUploadToDb() != null) {
			json.put("uploadToDb", getUploadToDb());
		} else {
			json.put("uploadToDb", "");
		}
		if (StringUtils.isNotBlank(getDefImg())) {
			json.put("defImg", getDefImg());
		} else {
			json.put("defImg", "");
		}
		if (StringUtils.isNotBlank(getAllowSuffix())) {
			json.put("allowSuffix", getAllowSuffix());
		} else {
			json.put("allowSuffix", "");
		}
		return json;
	}

	public BbsConfigAttr getConfigAttr() {
		BbsConfigAttr configAttr = new BbsConfigAttr(getAttr());
		return configAttr;
	}

	public void setConfigAttr(BbsConfigAttr configAttr) {
		getAttr().putAll(configAttr.getAttr());
	}

	public Boolean getSsoEnable() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getSsoEnable();
	}

	public Map<String, String> getSsoAttr() {
		Map<String, String> ssoMap = new HashMap<String, String>();
		Map<String, String> attr = getAttr();
		for (String ssoKey : attr.keySet()) {
			if (ssoKey.startsWith("sso_")) {
				ssoMap.put(ssoKey, attr.get(ssoKey));
			}
		}
		return ssoMap;
	}

	public List<String> getSsoAuthenticateUrls() {
		Map<String, String> ssoMap = getSsoAttr();
		List<String> values = new ArrayList<String>();
		for (String key : ssoMap.keySet()) {
			values.add(ssoMap.get(key));
		}
		return values;
	}

	public Map<String, String> getRewardFixAttr() {
		Map<String, String> attrMap = new HashMap<String, String>();
		Map<String, String> attr = getAttr();
		for (String fixKey : attr.keySet()) {
			if (fixKey.startsWith(REWARD_FIX_PREFIX)) {
				attrMap.put(fixKey, attr.get(fixKey));
			}
		}
		return attrMap;
	}

	public Object[] getRewardFixValues() {
		Map<String, String> attrMap = getRewardFixAttr();
		Collection<String> fixStrings = attrMap.values();
		return fixStrings.toArray();
	}

	public Boolean getQqEnable() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getQqEnable();
	}

	public Boolean getSinaEnable() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getSinaEnable();
	}

	public Boolean getQqWeboEnable() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getQqWeboEnable();
	}

	public String getQqID() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getQqID();
	}

	public String getSinaID() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getSinaID();
	}

	public String getQqWeboID() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getQqWeboID();
	}

	public String getWeixinAppId() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getWeixinAppId();
	}

	public String getWeixinAppSecret() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getWeixinAppSecret();
	}

	public Boolean getWeixinLoginEnable() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getWeixinEnable();
	}

	public String getWeixinLoginId() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getWeixinLoginId();
	}

	public String getWeixinLoginSecret() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getWeixinLoginSecret();
	}

	public Integer getDefaultActiveLevel() {
		BbsConfigAttr configAttr = getConfigAttr();
		String defaultActiveLevel = configAttr.getDefaultActiveLevel();
		int defaultActiveLevelId = 1;
		try {
			defaultActiveLevelId = Integer.parseInt(defaultActiveLevel);
		} catch (Exception e) {
		}
		return defaultActiveLevelId;
	}

	public int getKeepMinute() {
		BbsConfigAttr configAttr = getConfigAttr();
		String keep = configAttr.getKeepMinute();
		int keepMinut = 10;
		try {
			keepMinut = Integer.parseInt(keep);
		} catch (Exception e) {
		}
		return keepMinut;
	}

	public int getUserOnlineTopNum() {
		BbsConfigAttr configAttr = getConfigAttr();
		String top = configAttr.getUserOnlineTopNum();
		int topNum = 0;
		try {
			topNum = Integer.parseInt(top);
		} catch (Exception e) {
		}
		return topNum;
	}

	public Boolean getAutoChangeGroup() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getAutoChangeGroup();
	}

	public Boolean getServiceExpirationEmailNotice() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getServiceExpirationEmailNotice();
	}

	public Integer getServiceExpirationEmailNoticeCount() {
		BbsConfigAttr configAttr = getConfigAttr();
		String noticeCount = configAttr.getServiceExpirationEmailNoticeCount();
		int emailNoticeCount = 0;
		try {
			emailNoticeCount = Integer.parseInt(noticeCount);
		} catch (Exception e) {
		}
		return emailNoticeCount;
	}

	public Integer getChangeToGroupId() {
		BbsConfigAttr configAttr = getConfigAttr();
		String changeToGroup = configAttr.getChangeGroup();
		int changeToGroupId = 1;
		try {
			changeToGroupId = Integer.parseInt(changeToGroup);
		} catch (Exception e) {
		}
		return changeToGroupId;
	}

	public String getUserOnlineTopDay() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getUserOnlineTopDay();
	}

	public Boolean getSensitivityInputOn() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getSensitivityInputOn();
	}

	public Boolean getReportMsgAuto() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getReportMsgAuto();
	}

	public String getReportMsgTxt() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getReportMsgTxt();
	}

	public Boolean getLiveCheck() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getLiveCheck();
	}

	public Double getAdDayCharge() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getAdDayCharge();
	}

	public Double getAdClickCharge() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getAdClickCharge();
	}

	public Double getAdDisplayCharge() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getAdDisplayCharge();
	}

	public String getAdOrderTitle() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getAdOrderTitle();
	}

	public String getTencentApiAuthKey() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getTencentApiAuthKey();
	}

	public String getTencentBizId() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getTencentBizId();
	}

	public String getTencentPushFlowKey() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getTencentPushFlowKey();
	}

	public String getTencentAppId() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getTencentAppId();
	}

	public String getLivePlat() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getLivePlat();
	}

	public String getBaiduPlayDomain() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getBaiduPlayDomain();
	}

	public String getBaiduPushDomain() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getBaiduPushDomain();
	}

	public String getBaiduSecretAccessKey() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getBaiduSecretAccessKey();
	}

	public String getBaiduAccessKeyId() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getBaiduAccessKeyId();
	}

	public String getBaiduStreamSafeKey() {
		BbsConfigAttr configAttr = getConfigAttr();
		return configAttr.getBaiduStreamSafeKey();
	}

	public void blankToNull() {
		if (StringUtils.isBlank(getProcessUrl())) {
			setProcessUrl(null);
		}
		if (StringUtils.isBlank(getContextPath())) {
			setContextPath(null);
		}
		if (StringUtils.isBlank(getServletPoint())) {
			setServletPoint(null);
		}
	}
}