package com.jeecms.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 上传附件
 * 
 * @author andy_hulibo@163.com 2018年11月1日下午1:55:15
 */
@Entity
@Table(name="jo_upload")
public class DbFile implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:11:40
	 */
	private static final long serialVersionUID = -7253069360404145359L;

	@Id
	@Column(name="filename")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="length")
	private Integer length;
	
	@Column(name="last_modified")
	private Long lastModified;
	
	@Column(name="content")
	private byte[] content;

	
	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public Integer getLength() {
		return length;
	}

	
	public void setLength(Integer length) {
		this.length = length;
	}

	
	public Long getLastModified() {
		return lastModified;
	}

	
	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

	
	public byte[] getContent() {
		return content;
	}

	
	public void setContent(byte[] content) {
		this.content = content;
	}

	
	public DbFile(String id) {
		this.setId(id);
	}

	
	public DbFile(String id, Integer length, Long lastModified, byte[] content) {

		this.setId(id);
		this.setLength(length);
		this.setLastModified(lastModified);
		this.setContent(content);
	}
}