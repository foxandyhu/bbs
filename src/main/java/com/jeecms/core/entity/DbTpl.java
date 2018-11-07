package com.jeecms.core.entity;

import static com.jeecms.common.web.Constants.SPT;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.Assert;

import com.jeecms.common.web.Constants;
import com.jeecms.core.tpl.Tpl;

/**
 * 模板
 * @author andy_hulibo@163.com
 * 2018年11月1日下午2:04:39
 */
@Entity
@Table(name="jo_template")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class DbTpl implements Tpl, Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:11:31
	 */
	private static final long serialVersionUID = 2379708156824718888L;

	@Id
	@Column(name="tpl_name")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="tpl_source")
	private String source;
	
	@Column(name="last_modified")
	private long lastModified;
	
	@Column(name="is_directory")
	private boolean directory;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	/**
	 * 获得文件夹或文件的所有父文件夹
	 * 
	 * @param path
	 * @return
	 */
	public static String[] getParentDir(String path) {
		Assert.notNull(path, "路径为空!");
		if (!path.startsWith(SPT)) {
			throw new IllegalArgumentException("path must start with /");
		}
		List<String> list = new ArrayList<String>();
		int index = path.indexOf(SPT, 1);
		while (index >= 0) {
			list.add(path.substring(0, index));
			index = path.indexOf(SPT, index + 1);
		}
		String[] arr = new String[list.size()];
		return list.toArray(arr);
	}

	public String getName() {
		return getId();
	}

	public String getPath() {
		String name = getId();
		return getId().substring(0, name.lastIndexOf("/"));
	}

	public String getFilename() {
		String name = getId();
		if (!StringUtils.isBlank(name)) {
			int index = name.lastIndexOf(Constants.SPT);
			if (index != -1) {
				return name.substring(index + 1, name.length());
			}
		}
		return name;
	}

	public long getLength() {
		if (isDirectory() || getSource() == null) {
			return 128;
		} else {
			// 一个英文字符占1个字节，而一个中文则占3-4字节，这里取折中一个字符1.5个字节
			return (long) (getSource().length() * 1.5);
		}
	}

	public int getSize() {
		return (int) (getLength() / 1024) + 1;
	}

	public Date getLastModifiedDate() {
		return new Timestamp(getLastModified());
	}

}