package com.sdkj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TuserTrole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tuser_trole", catalog = "")
public class TuserTrole implements java.io.Serializable {

	// Fields

	private String id;
	private Trole trole;
	private Tuser tuser;

	// Constructors

	/** default constructor */
	public TuserTrole() {
	}

	/** minimal constructor */
	public TuserTrole(String id, Trole trole) {
		this.id = id;
		this.trole = trole;
	}

	/** full constructor */
	public TuserTrole(String id, Trole trole, Tuser tuser) {
		this.id = id;
		this.trole = trole;
		this.tuser = tuser;
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
	@JoinColumn(name = "roleId", nullable = false)
	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

}