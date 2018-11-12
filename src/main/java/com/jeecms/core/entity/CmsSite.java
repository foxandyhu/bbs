package com.jeecms.core.entity;

import static com.jeecms.bbs.Constants.RES_PATH;
import static com.jeecms.bbs.Constants.TPL_BASE;
import static com.jeecms.bbs.Constants.UPLOAD_PATH;
import static com.jeecms.common.web.Constants.DEFAULT;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.bbs.entity.BbsCreditExchange;

/**
 * 站点
 * @author andy_hulibo@163.com 2018年10月30日下午2:58:00
 */
@Entity
@Table(name="jc_site")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class CmsSite implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月25日上午10:32:14
	 */
	private static final long serialVersionUID = 5903809221240109670L;

	public CmsSite(){}
	public CmsSite(Integer id) {
		this.setId(id);
	}

	@Id
	@Column(name="site_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="domain")
	private String domain;
	
	@Column(name="site_path")
	private String path;
	
	@Column(name="site_name")
	private String name;
	
	@Column(name="short_name")
	private String shortName;
	
	@Column(name="protocol")
	private String protocol;
	
	@Column(name="dynamic_suffix")
	private String dynamicSuffix;
	
	@Column(name="static_suffix")
	private String staticSuffix;
	
	@Column(name="static_dir")
	private String staticDir;
	
	@Column(name="is_index_to_root")
	private Boolean indexToRoot;
	
	@Column(name="is_static_index")
	private Boolean staticIndex;
	
	@Column(name="locale_admin")
	private String localeAdmin;
	
	@Column(name="locale_front")
	private String localeFront;
	
	@Column(name="tpl_solution")
	private String tplSolution;
	
	@Column(name="tpl_mobile_solution")
	private String tplMobileSolution;
	
	@Column(name="final_step")
	private Byte finalStep;
	
	@Column(name="after_check")
	private Byte afterCheck;
	
	@Column(name="is_relative_path")
	private Boolean relativePath;
	
	@Column(name="is_recycle_on")
	private Boolean resycleOn;
	
	@Column(name="domain_alias")
	private String domainAlias;
	
	@Column(name="domain_redirect")
	private String domainRedirect;
	
	@Column(name="cors_url")
	private String corsUrl;

	@ManyToOne
	@JoinColumn(name="ftp_upload_id")
	private Ftp uploadFtp;
	
	@ManyToOne
	@JoinColumn(name="config_id")	
	private CmsConfig config;
	
	@ManyToOne
	@JoinColumn(name="creditex_id")	
	private BbsCreditExchange creditExchange;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getDynamicSuffix() {
		return dynamicSuffix;
	}

	public void setDynamicSuffix(String dynamicSuffix) {
		this.dynamicSuffix = dynamicSuffix;
	}

	public String getStaticSuffix() {
		return staticSuffix;
	}

	public void setStaticSuffix(String staticSuffix) {
		this.staticSuffix = staticSuffix;
	}

	public String getStaticDir() {
		return staticDir;
	}

	public void setStaticDir(String staticDir) {
		this.staticDir = staticDir;
	}

	public Boolean getIndexToRoot() {
		return indexToRoot;
	}

	public void setIndexToRoot(Boolean indexToRoot) {
		this.indexToRoot = indexToRoot;
	}

	public Boolean getStaticIndex() {
		return staticIndex;
	}

	public void setStaticIndex(Boolean staticIndex) {
		this.staticIndex = staticIndex;
	}

	public String getLocaleAdmin() {
		return localeAdmin;
	}

	public void setLocaleAdmin(String localeAdmin) {
		this.localeAdmin = localeAdmin;
	}

	public String getLocaleFront() {
		return localeFront;
	}

	public void setLocaleFront(String localeFront) {
		this.localeFront = localeFront;
	}

	public String getTplSolution() {
		return tplSolution;
	}

	public void setTplSolution(String tplSolution) {
		this.tplSolution = tplSolution;
	}

	public String getTplMobileSolution() {
		return tplMobileSolution;
	}

	public void setTplMobileSolution(String tplMobileSolution) {
		this.tplMobileSolution = tplMobileSolution;
	}

	public Byte getFinalStep() {
		return finalStep;
	}

	public void setFinalStep(Byte finalStep) {
		this.finalStep = finalStep;
	}

	public Byte getAfterCheck() {
		return afterCheck;
	}

	public void setAfterCheck(Byte afterCheck) {
		this.afterCheck = afterCheck;
	}

	public Boolean getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(Boolean relativePath) {
		this.relativePath = relativePath;
	}

	public Boolean getResycleOn() {
		return resycleOn;
	}

	public void setResycleOn(Boolean resycleOn) {
		this.resycleOn = resycleOn;
	}

	public String getDomainAlias() {
		return domainAlias;
	}

	public void setDomainAlias(String domainAlias) {
		this.domainAlias = domainAlias;
	}

	public String getDomainRedirect() {
		return domainRedirect;
	}

	public void setDomainRedirect(String domainRedirect) {
		this.domainRedirect = domainRedirect;
	}

	public String getCorsUrl() {
		return corsUrl;
	}

	public void setCorsUrl(String corsUrl) {
		this.corsUrl = corsUrl;
	}

	public Ftp getUploadFtp() {
		return uploadFtp;
	}

	public void setUploadFtp(Ftp uploadFtp) {
		this.uploadFtp = uploadFtp;
	}

	public CmsConfig getConfig() {
		return config;
	}

	public void setConfig(CmsConfig config) {
		this.config = config;
	}

	public BbsCreditExchange getCreditExchange() {
		return creditExchange;
	}

	public void setCreditExchange(BbsCreditExchange creditExchange) {
		this.creditExchange = creditExchange;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getName())) {
			json.put("name", getName());
		} else {
			json.put("name", "");
		}
		if (StringUtils.isNotBlank(getShortName())) {
			json.put("shortName", getShortName());
		} else {
			json.put("shortName", "");
		}
		if (StringUtils.isNotBlank(getDomain())) {
			json.put("domain", getDomain());
		} else {
			json.put("domain", "");
		}
		if (StringUtils.isNotBlank(getPath())) {
			json.put("path", getPath());
		} else {
			json.put("path", "");
		}
		if (StringUtils.isNotBlank(getDomainAlias())) {
			json.put("domainAlias", getDomainAlias());
		} else {
			json.put("domainAlias", "");
		}
		if (StringUtils.isNotBlank(getDomainRedirect())) {
			json.put("domainRedirect", getDomainRedirect());
		} else {
			json.put("domainRedirect", "");
		}
		if (StringUtils.isNotBlank(getName())) {
			json.put("corsUrl", getName());
		} else {
			json.put("corsUrl", "");
		}
		if (getRelativePath() != null) {
			json.put("relativePath", getRelativePath());
		} else {
			json.put("relativePath", "");
		}
		if (StringUtils.isNotBlank(getProtocol())) {
			json.put("protocol", getProtocol());
		} else {
			json.put("protocol", "");
		}

		if (StringUtils.isNotBlank(getDynamicSuffix())) {
			json.put("dynamicSuffix", getDynamicSuffix());
		} else {
			json.put("dynamicSuffix", "");
		}

		if (StringUtils.isNotBlank(getStaticSuffix())) {
			json.put("staticSuffix", getStaticSuffix());
		} else {
			json.put("staticSuffix", "");
		}

		if (StringUtils.isNotBlank(getLocaleAdmin())) {
			json.put("localeAdmin", getLocaleAdmin());
		} else {
			json.put("localeAdmin", "");
		}

		if (StringUtils.isNotBlank(getLocaleFront())) {
			json.put("localeFront", getLocaleFront());
		} else {
			json.put("localeFront", "");
		}

		return json;
	}

	/**
	 * 获得站点url
	 * 
	 * @return
	 */
	public String getUrl() {
		if (getStaticIndex()) {
			return getUrlStatic();
		} else {
			return getUrlDynamic();
		}
	}

	public String getAppUrl() {
		String path = "http://";
		path = path + getDomain();
		if (getPort() != null) {
			path = path + ":" + getPort().toString();
		}
		if (getContextPath() != null) {
			path = path + getContextPath();
		}
		path = path + "/";
		return path;
	}

	/**
	 * 获得完整路径。在给其他网站提供客户端包含时有可以使用。
	 * 
	 * @return
	 */
	public String getUrlWhole() {
		if (getStaticIndex()) {
			return getUrlBuffer(false, true, false).append("/").toString();
		} else {
			return getUrlBuffer(true, true, false).append("/").toString();
		}
	}

	public String getUrlDynamic() {
		return getUrlBuffer(true, null, false).append("/").toString();
	}

	public String getUrlStatic() {
		return getUrlBuffer(false, null, true).append("/").toString();
	}

	public String getMagicShopUrl() {
		StringBuffer buff = new StringBuffer();
		buff.append(getProtocol()).append(getDomain());
		CmsConfig config = getConfig();
		if (config.getPort() != null && config.getPort() != 80) {
			buff.append(config.getPort());
		}
		if (StringUtils.isNotBlank(config.getContextPath())) {
			buff.append(config.getContextPath());
		}
		buff.append("/magic/mybox.html");
		return buff.toString();
	}

	public String getGiftIndexUrl() {
		StringBuffer buff = new StringBuffer();
		buff.append(getProtocol()).append(getDomain());
		CmsConfig config = getConfig();
		if (config.getPort() != null && config.getPort() != 80) {
			buff.append(config.getPort());
		}
		if (StringUtils.isNotBlank(config.getContextPath())) {
			buff.append(config.getContextPath());
		}
		buff.append("/gift/index.html");
		return buff.toString();
	}

	public String getMemberAdIndexUrl() {
		StringBuffer buff = new StringBuffer();
		buff.append(getProtocol()).append(getDomain());
		CmsConfig config = getConfig();
		if (config.getPort() != null && config.getPort() != 80) {
			buff.append(config.getPort());
		}
		if (StringUtils.isNotBlank(config.getContextPath())) {
			buff.append(config.getContextPath());
		}
		buff.append("/member/myAdvertises.html");
		return buff.toString();
	}

	public StringBuilder getUrlBuffer(boolean dynamic, Boolean whole, boolean forIndex) {
		boolean relative = whole != null ? !whole : getRelativePath();
		String ctx = getContextPath();
		StringBuilder url = new StringBuilder();
		if (!relative) {
			url.append(getProtocol()).append(getDomain());
			if (getPort() != null) {
				url.append(":").append(getPort());
			}
		}
		if (!StringUtils.isBlank(ctx)) {
			url.append(ctx);
		}
		if (dynamic) {
			String servlet = getServletPoint();
			if (!StringUtils.isBlank(servlet)) {
				url.append(servlet);
			}
		} else {
			if (!forIndex) {
				String staticDir = getStaticDir();
				if (!StringUtils.isBlank(staticDir)) {
					url.append(staticDir);
				}
			}
		}
		return url;
	}

	public StringBuilder getHttpsUrlBuffer(boolean dynamic, Boolean whole, boolean forIndex) {
		boolean relative = whole != null ? !whole : getRelativePath();
		String ctx = getContextPath();
		StringBuilder url = new StringBuilder();
		if (!relative) {
			url.append("https://").append(getDomain());
			if (getPort() != null && getPort() != 80) {
				url.append(":").append(getPort());
			}
		}
		if (!StringUtils.isBlank(ctx)) {
			url.append(ctx);
		}
		if (dynamic) {
			String servlet = getServletPoint();
			if (!StringUtils.isBlank(servlet)) {
				url.append(servlet);
			}
		} else {
			if (!forIndex) {
				String staticDir = getStaticDir();
				if (!StringUtils.isBlank(staticDir)) {
					url.append(staticDir);
				}
			}
		}
		return url;
	}

	public String getHttpsUrlDynamic() {
		return getHttpsUrlBuffer(true, null, false).append("/").toString();
	}

	public String getHttpsUrlStatic() {
		return getHttpsUrlBuffer(false, null, true).append("/").toString();
	}

	public String getUrlPrefix() {
		StringBuilder url = new StringBuilder();
		url.append(getProtocol()).append(getDomain());
		if (getPort() != null) {
			url.append(":").append(getPort());
		}
		return url.toString();
	}

	public String getUrlPrefixWithNoDefaultPort() {
		StringBuilder url = new StringBuilder();
		url.append(getProtocol()).append(getDomain());
		if (getPort() != null && getPort() != 80) {
			url.append(":").append(getPort());
		}
		return url.toString();
	}

	public String getSafeUrlPrefix() {
		StringBuilder url = new StringBuilder();
		url.append("https://").append(getDomain());
		if (getPort() != null && getPort() != 80 && getPort() != 443) {
			url.append(":").append(getPort());
		}
		return url.toString();
	}

	/**
	 * 获得模板路径。如：/WEB-INF/t/cms/www
	 * 
	 * @return
	 */
	public String getTplPath() {
		return TPL_BASE + "/" + getPath();
	}

	/**
	 * 获得模板方案路径。如：/WEB-INF/t/cms/www/default
	 * 
	 * @return
	 */
	public String getSolutionPath() {
		return TPL_BASE + "/" + getPath() + "/" + getTplSolution();
	}

	public String getMobileSolutionPath() {
		return TPL_BASE + "/" + getPath() + "/" + getTplMobileSolution();
	}

	/**
	 * 获得模板资源路径。如：/r/cms/www
	 * 
	 * @return
	 */
	public String getResPath() {
		return RES_PATH + "/" + getPath();
	}

	/**
	 * 获得上传路径。如：/u/jeecms/path
	 * 
	 * @return
	 */
	public String getUploadPath() {
		return UPLOAD_PATH + getPath();
	}

	/**
	 * 获得上传访问前缀。
	 * 
	 * 根据配置识别上传至数据、FTP和本地
	 * 
	 * @return
	 */
	public String getUploadBase() {
		CmsConfig config = getConfig();
		String ctx = config.getContextPath();
		if (config.getUploadToDb()) {
			if (!StringUtils.isBlank(ctx)) {
				return ctx + config.getDbFileUri();
			} else {
				return config.getDbFileUri();
			}
		} else if (getUploadFtp() != null) {
			return getUploadFtp().getUrl();
		} else {
			if (!StringUtils.isBlank(ctx)) {
				return ctx;
			} else {
				return "";
			}
		}
	}

	public String getServletPoint() {
		CmsConfig config = getConfig();
		if (config != null) {
			return config.getServletPoint();
		} else {
			return null;
		}
	}

	public String getContextPath() {
		CmsConfig config = getConfig();
		if (config != null) {
			return config.getContextPath();
		} else {
			return null;
		}
	}

	public Integer getPort() {
		CmsConfig config = getConfig();
		if (config != null) {
			return config.getPort();
		} else {
			return null;
		}
	}

	public String getDefImg() {
		CmsConfig config = getConfig();
		if (config != null) {
			return config.getDefImg();
		} else {
			return null;
		}
	}

	public String getLoginUrl() {
		CmsConfig config = getConfig();
		if (config != null) {
			return config.getLoginUrl();
		} else {
			return null;
		}
	}

	public String getProcessUrl() {
		CmsConfig config = getConfig();
		if (config != null) {
			return config.getProcessUrl();
		} else {
			return null;
		}
	}

	public static Integer[] fetchIds(Collection<CmsSite> sites) {
		if (sites == null) {
			return null;
		}
		Integer[] ids = new Integer[sites.size()];
		int i = 0;
		for (CmsSite s : sites) {
			ids[i++] = s.getId();
		}
		return ids;
	}

	public String getBaseDomain() {
		String domain = getDomain();
		if (domain.indexOf(".") > -1) {
			return domain.substring(domain.indexOf(".") + 1);
		}
		return domain;
	}

	public void init() {
		if (StringUtils.isBlank(getProtocol())) {
			setProtocol("http://");
		}
		if (StringUtils.isBlank(getTplSolution())) {
			setTplSolution(DEFAULT);
		}
		if (getFinalStep() == null) {
			byte step = 2;
			setFinalStep(step);
		}
	}

}