package com.sdkj.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tonline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tonline", catalog = "")
public class Tonline implements java.io.Serializable {

	// Fields

	private String id;
	private String ip;
	private Date logindatetime;
	private String loginname;

	// Constructors

	/** default constructor */
	public Tonline() {
	}

	/** full constructor */
	public Tonline(String id, String ip, Date logindatetime, String loginname) {
		this.id = id;
		this.ip = ip;
		this.logindatetime = logindatetime;
		this.loginname = loginname;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 20)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ip", nullable = false, length = 50)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "logindatetime", nullable = false, length = 19)
	public Date getLogindatetime() {
		return this.logindatetime;
	}

	public void setLogindatetime(Date logindatetime) {
		this.logindatetime = logindatetime;
	}

	@Column(name = "loginname", nullable = false, length = 100)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

}