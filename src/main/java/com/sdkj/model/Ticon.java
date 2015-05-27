package com.sdkj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ticon entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ticon", catalog = "")
public class Ticon implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String url;

	// Constructors

	/** default constructor */
	public Ticon() {
	}

	/** minimal constructor */
	public Ticon(String id) {
		this.id = id;
	}

	/** full constructor */
	public Ticon(String id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}