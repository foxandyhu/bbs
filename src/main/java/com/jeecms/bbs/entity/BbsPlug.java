package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.util.DateUtils;

/**
 * 插件
 * @author andy_hulibo@163.com
 * 2018年10月31日下午5:08:44
 */
@Entity
@Table(name="jb_plug")
public class BbsPlug implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:57:09
	 */
	private static final long serialVersionUID = 6111166701021696866L;

	@Id
	@Column(name="plug_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;
	
	@Column(name="path")
	private String path;
	
	@Column(name="description")
	private String description;
	
	@Column(name="author")
	private String author;
	
	@Column(name="upload_time")
	private Date uploadTime;
	
	@Column(name="install_time")
	private Date installTime;
	
	@Column(name="uninstall_time")
	private Date uninstallTime;
	
	@Column(name="file_conflict")
	private Boolean fileConflict;
	
	@Column(name="is_used")
	private Boolean used;
	
	@Column(name="plug_perms")
	private String plugPerms;
	
	@Column(name="plug_repair")
	private boolean plugRepair;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getInstallTime() {
		return installTime;
	}

	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}

	public Date getUninstallTime() {
		return uninstallTime;
	}

	public void setUninstallTime(Date uninstallTime) {
		this.uninstallTime = uninstallTime;
	}

	public Boolean isFileConflict() {
		return fileConflict;
	}

	public void setFileConflict(boolean fileConflict) {
		this.fileConflict = fileConflict;
	}

	public Boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getPlugPerms() {
		return plugPerms;
	}

	public void setPlugPerms(String plugPerms) {
		this.plugPerms = plugPerms;
	}

	public boolean getPlugRepair() {
		return plugRepair;
	}

	public void setPlugRepair(boolean plugRepair) {
		this.plugRepair = plugRepair;
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
		if (StringUtils.isNotBlank(getAuthor())) {
			json.put("author", getAuthor());
		} else {
			json.put("author", "");
		}
		if (getUsed() != null) {
			json.put("used", getUsed());
		} else {
			json.put("used", "");
		}
		if (getUploadTime() != null) {
			json.put("uploadTime", DateUtils.parseDateToDateStr(getUploadTime()));
		} else {
			json.put("uploadTime", "");
		}
		if (getInstallTime() != null) {
			json.put("installTime", DateUtils.parseDateToDateStr(getInstallTime()));
		} else {
			json.put("installTime", "");
		}
		if (getUninstallTime() != null) {
			json.put("uninstallTime", DateUtils.parseDateToDateStr(getUninstallTime()));
		} else {
			json.put("uninstallTime", "");
		}
		if (StringUtils.isNotBlank(getDescription())) {
			json.put("description", getDescription());
		} else {
			json.put("description", "");
		}
		if (getFileConflict() != null) {
			json.put("fileConflict", getFileConflict());
		} else {
			json.put("fileConflict", "");
		}
		if (StringUtils.isNotBlank(getPath())) {
			json.put("path", getPath());
		} else {
			json.put("path", "");
		}
		return json;
	}

	public Boolean getUsed() {
		return isUsed();
	}

	public Boolean getFileConflict() {
		return isFileConflict();
	}

	public boolean getCanInstall() {
		// 未使用 且(文件未冲突或者是修复类)
		if (!getUsed() && (!getFileConflict() || getPlugRepair())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getCanUnInstall() {
		// 使用中的修复类插件和未使用的插件 不能卸载
		if ((getUsed() && getPlugRepair()) || !getUsed()) {
			return false;
		} else {
			return true;
		}
	}
}