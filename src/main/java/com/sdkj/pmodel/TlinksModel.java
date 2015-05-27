package com.sdkj.pmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tlinks entity. @author MyEclipse Persistence Tools
 */
public class TlinksModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String sitename;
	private String siteurl;
	private String imgsrc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getSiteurl() {
		return siteurl;
	}
	public void setSiteurl(String siteurl) {
		this.siteurl = siteurl;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	@Override
	public String toString() {
		return "TlinksModel [id=" + id + ", sitename=" + sitename
				+ ", siteurl=" + siteurl + ", imgsrc=" + imgsrc + "]";
	}
	
	
}