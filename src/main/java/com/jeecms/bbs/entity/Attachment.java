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

@Entity
@Table(name="attachment")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Attachment implements Serializable{
	
	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午4:00:01
	 */
	private static final long serialVersionUID = -3955659015127659881L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="file_path")
	private String filePath;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_size")
	private Integer fileSize;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="is_pictrue")
	private Boolean picture;
	
	@Column(name="zoom_pic_path")
	private String zoomPicPath;

	@ManyToOne
	@JoinColumn(name="post_id")
	private BbsPost post;

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public String getFilePath () {
		return filePath;
	}

	public void setFilePath (String filePath) {
		this.filePath = filePath;
	}

	public String getFileName () {
		return fileName;
	}

	public void setFileName (String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileSize () {
		return fileSize;
	}

	public void setFileSize (Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getPicture () {
		return picture;
	}

	public void setPicture (Boolean picture) {
		this.picture = picture;
	}

	public String getZoomPicPath() {
		return zoomPicPath;
	}

	public void setZoomPicPath(String zoomPicPath) {
		this.zoomPicPath = zoomPicPath;
	}

	public BbsPost getPost () {
		return post;
	}

	public void setPost (BbsPost post) {
		this.post = post;
	}

}