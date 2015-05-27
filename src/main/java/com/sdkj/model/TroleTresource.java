package com.sdkj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TroleTresource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trole_tresource", catalog = "")
public class TroleTresource implements java.io.Serializable {

	// Fields

	private String id;
	private Tresource tresource;
	private Trole trole;

	// Constructors

	/** default constructor */
	public TroleTresource() {
	}

	/** full constructor */
	public TroleTresource(String id, Tresource tresource, Trole trole) {
		this.id = id;
		this.tresource = tresource;
		this.trole = trole;
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
	@JoinColumn(name = "resourceId", nullable = false)
	public Tresource getTresource() {
		return this.tresource;
	}

	public void setTresource(Tresource tresource) {
		this.tresource = tresource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", nullable = false)
	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

}