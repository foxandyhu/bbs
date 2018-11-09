package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
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

import com.jeecms.core.entity.CmsSite;

/**
 * 举报记录
 * @author andy_hulibo@163.com 2018年11月2日下午3:51:57
 */
@Entity
@Table(name = "bbs_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsReport implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:56:01
	 */
	private static final long serialVersionUID = -7089081475906674298L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="report_url")
	private String reportUrl;
	
	@Column(name="process_time")
	private Date processTime;
	
	@Column(name="process_result")
	private String processResult;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="report_time")
	private Date reportTime;

	@ManyToOne
	@JoinColumn(name="process_user")
	private BbsUser processUser;

	@OneToMany(cascade=CascadeType.REMOVE,orphanRemoval=true,mappedBy="report")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
	private Set<BbsReportExt> bbsReportExtSet;

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getProcessResult() {
		return processResult;
	}


	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	
	public boolean isStatus() {
		return status;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	public Date getReportTime() {
		return reportTime;
	}

	
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	
	public BbsUser getProcessUser() {
		return processUser;
	}

	
	public void setProcessUser(BbsUser processUser) {
		this.processUser = processUser;
	}

	public Set<BbsReportExt> getBbsReportExtSet() {
		return bbsReportExtSet;
	}

	
	public void setBbsReportExtSet(Set<BbsReportExt> bbsReportExtSet) {
		this.bbsReportExtSet = bbsReportExtSet;
	}

	public JSONObject convertToJson(Integer https, CmsSite site) throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getReportUrl())) {
			json.put("reportUrl", getReportUrl());
		} else {
			json.put("reportUrl", "");
		}
		if (getProcessTime() != null) {
			json.put("processTime", getProcessTime());
		} else {
			json.put("processTime", "");
		}
		if (StringUtils.isNotBlank(getProcessResult())) {
			json.put("processResult", getProcessResult());
		} else {
			json.put("processResult", "");
		}
		json.put("status", getStatus());
		if (getReportTime() != null) {
			json.put("reportTime", getReportTime());
		} else {
			json.put("reportTime", "");
		}
		if (getReportExt() != null && getReportExt().getReportUser() != null
				&& StringUtils.isNotBlank(getReportExt().getReportUser().getUsername())) {
			json.put("reportUserName", getReportExt().getReportUser().getUsername());
		} else {
			json.put("reportUserName", "");
		}
		json.put("point", 0);
		return json;
	}

	public BbsReportExt getReportExt() {
		Set<BbsReportExt> sets = getBbsReportExtSet();
		Iterator<BbsReportExt> it = null;
		if (sets != null) {
			it = sets.iterator();
		}
		if (it != null) {
			return it.next();
		}
		return null;
	}
}