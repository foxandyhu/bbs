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

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.util.DateUtils;

@Entity
@Table(name = "bbs_report_ext")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsReportExt implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:55:53
	 */
	private static final long serialVersionUID = -3915929370588402346L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "report_time")
	private Date reportTime;

	@Column(name = "report_reason")
	private String reportReason;

	@ManyToOne
	@JoinColumn(name = "report_user")
	private BbsUser reportUser;

	@ManyToOne
	@JoinColumn(name = "report_id")
	private BbsReport report;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	public BbsUser getReportUser() {
		return reportUser;
	}

	public void setReportUser(BbsUser reportUser) {
		this.reportUser = reportUser;
	}

	public BbsReport getReport() {
		return report;
	}

	public void setReport(BbsReport report) {
		this.report = report;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getReportUser() != null && StringUtils.isNotBlank(getReportUser().getUsername())) {
			json.put("username", getReportUser().getUsername());
		} else {
			json.put("username", "");
		}
		if (StringUtils.isNotBlank(getReportReason())) {
			json.put("reportReason", getReportReason());
		} else {
			json.put("reportReason", "");
		}
		if (getReportTime() != null) {
			json.put("reportTime", DateUtils.parseDateToDateStr(getReportTime()));
		} else {
			json.put("reportTime", "");
		}
		if (getReport() != null && getReport().getId() != null) {
			json.put("reportId", getReport().getId());
		} else {
			json.put("reportId", "");
		}
		return json;
	}
}