package com.jeecms.core.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.SocketException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecms.common.upload.UploadUtils;

@Entity
@Table(name = "jo_ftp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class Ftp implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月31日下午1:17:07
	 */
	private static final long serialVersionUID = -969862217533585655L;

	private static final Logger log = LoggerFactory.getLogger(Ftp.class);

	@Id
	@Column(name = "ftp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ftp_name")
	private String name;

	@Column(name = "ip")
	private String ip;

	@Column(name = "port")
	private Integer port;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "encoding")
	private String encoding;

	@Column(name = "timeout")
	private Integer timeout;

	@Column(name = "ftp_path")
	private String path;

	@Column(name = "url")
	private String url;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String storeByExt(String path, String ext, InputStream in) throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		store(filename, in);
		return filename;
	}

	public String storeByFilename(String filename, InputStream in) throws IOException {
		store(filename, in);
		return filename;
	}

	public File retrieve(String name) throws IOException {
		String path = System.getProperty("java.io.tmpdir");
		File file = new File(path, name);
		file = UploadUtils.getUniqueFile(file);

		FTPClient ftp = getClient();
		OutputStream output = new FileOutputStream(file);
		ftp.retrieveFile(getPath() + name, output);
		output.close();
		ftp.logout();
		ftp.disconnect();
		return file;
	}

	public boolean restore(String name, File file) throws IOException {
		store(name, FileUtils.openInputStream(file));
		file.deleteOnExit();
		return true;
	}

	private int store(String remote, InputStream in) {
		try {
			FTPClient ftp = getClient();
			if (ftp != null) {
				String filename = getPath() + remote;
				String name = FilenameUtils.getName(filename);
				String path = FilenameUtils.getFullPath(filename);
				if (!ftp.changeWorkingDirectory(path)) {
					String[] ps = StringUtils.split(path, '/');
					String p = "/";
					ftp.changeWorkingDirectory(p);
					for (String s : ps) {
						p += s + "/";
						if (!ftp.changeWorkingDirectory(p)) {
							ftp.makeDirectory(s);
							ftp.changeWorkingDirectory(p);
						}
					}
				}
				ftp.storeFile(name, in);
				ftp.logout();
				ftp.disconnect();
			}
			in.close();
			return 0;
		} catch (SocketException e) {
			log.error("ftp store error", e);
			return 3;
		} catch (IOException e) {
			log.error("ftp store error", e);
			return 4;
		}
	}

	private FTPClient getClient() throws SocketException, IOException {
		FTPClient ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		ftp.setDefaultPort(getPort());
		ftp.connect(getIp());
		int reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			log.warn("FTP server refused connection: {}", getIp());
			ftp.disconnect();
			return null;
		}
		if (!ftp.login(getUsername(), getPassword())) {
			log.warn("FTP server refused login: {}, user: {}", getIp(), getUsername());
			ftp.logout();
			ftp.disconnect();
			return null;
		}
		ftp.setControlEncoding(getEncoding());
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
		return ftp;
	}
}