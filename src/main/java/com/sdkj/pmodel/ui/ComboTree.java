package com.sdkj.pmodel.ui;

import java.io.Serializable;
import java.math.BigDecimal;

public class ComboTree implements Serializable {
	
	/**
	 * easyui ComboTree 格式化
	 */
	private static final long serialVersionUID = -725143575012938440L;
	private String id;
	private String text;
	private String url;
	private String iconCls;
	private BigDecimal seq;
	private String pid;
	private String pname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public BigDecimal getSeq() {
		return seq;
	}
	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
