package com.sdkj.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tlog", catalog = "")
public class Tlog implements java.io.Serializable {

	// Fields

	private String id;
	private Date createtime;
	private String event;
	private Integer type;

	// Constructors

	/** default constructor */
	public Tlog() {
	}

	/** full constructor */
	public Tlog(String id, Date createtime, String event, Integer type) {
		this.id = id;
		this.createtime = createtime;
		this.event = event;
		this.type = type;
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

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "event", nullable = false)
	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}