package com.sdkj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tlinks entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tlinks", catalog = "")
public class Tlinks implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String sitename;
	private String siteurl;
	private String imgsrc;

	// Constructors

	/** default constructor */
	public Tlinks() {
	}

	/** minimal constructor */
	public Tlinks(String id, String sitename, String siteurl) {
		this.id = id;
		this.sitename = sitename;
		this.siteurl = siteurl;
	}

	/** full constructor */
	public Tlinks(String id, String sitename, String siteurl, String imgsrc) {
		this.id = id;
		this.sitename = sitename;
		this.siteurl = siteurl;
		this.imgsrc = imgsrc;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "sitename", nullable = false, length = 50)
	public String getSitename() {
		return this.sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	@Column(name = "siteurl", nullable = false)
	public String getSiteurl() {
		return this.siteurl;
	}

	public void setSiteurl(String siteurl) {
		this.siteurl = siteurl;
	}

	@Column(name = "imgsrc")
	public String getImgsrc() {
		return this.imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

}