package com.sdkj.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tresource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tresource", catalog = "")
public class Tresource implements java.io.Serializable {

	// Fields

	private String id;
	private Tresource tresource;
	private String iconCls;
	private BigDecimal seq;
	private String text;
	private Integer type;
	private String url;
	private Set<Tresource> tresources = new HashSet<Tresource>(0);
	private Set<TroleTresource> troleTresources = new HashSet<TroleTresource>(0);

	// Constructors

	/** default constructor */
	public Tresource() {
	}

	/** minimal constructor */
	public Tresource(String id, String text) {
		this.id = id;
		this.text = text;
	}

	/** full constructor */
	public Tresource(String id, Tresource tresource, String iconCls,
			BigDecimal seq, String text, Integer type, String url,
			Set<Tresource> tresources, Set<TroleTresource> troleTresources) {
		this.id = id;
		this.tresource = tresource;
		this.iconCls = iconCls;
		this.seq = seq;
		this.text = text;
		this.type = type;
		this.url = url;
		this.tresources = tresources;
		this.troleTresources = troleTresources;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Tresource getTresource() {
		return this.tresource;
	}

	public void setTresource(Tresource tresource) {
		this.tresource = tresource;
	}

	@Column(name = "iconCls")
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "seq", precision = 22, scale = 0)
	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	@Column(name = "text", nullable = false, length = 100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tresource")
	public Set<Tresource> getTresources() {
		return this.tresources;
	}

	public void setTresources(Set<Tresource> tresources) {
		this.tresources = tresources;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tresource")
	public Set<TroleTresource> getTroleTresources() {
		return this.troleTresources;
	}

	public void setTroleTresources(Set<TroleTresource> troleTresources) {
		this.troleTresources = troleTresources;
	}

}