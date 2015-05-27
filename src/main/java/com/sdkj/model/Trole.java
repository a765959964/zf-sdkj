package com.sdkj.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Trole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trole", catalog = "")
public class Trole implements java.io.Serializable {

	// Fields

	private String id;
	private String text;
	private Set<TroleTresource> troleTresources = new HashSet<TroleTresource>(0);
	private Set<TuserTrole> tuserTroles = new HashSet<TuserTrole>(0);

	// Constructors

	/** default constructor */
	public Trole() {
	}

	/** minimal constructor */
	public Trole(String id, String text) {
		this.id = id;
		this.text = text;
	}

	/** full constructor */
	public Trole(String id, String text, Set<TroleTresource> troleTresources,
			Set<TuserTrole> tuserTroles) {
		this.id = id;
		this.text = text;
		this.troleTresources = troleTresources;
		this.tuserTroles = tuserTroles;
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

	@Column(name = "text", nullable = false, length = 100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trole")
	public Set<TroleTresource> getTroleTresources() {
		return this.troleTresources;
	}

	public void setTroleTresources(Set<TroleTresource> troleTresources) {
		this.troleTresources = troleTresources;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trole")
	public Set<TuserTrole> getTuserTroles() {
		return this.tuserTroles;
	}

	public void setTuserTroles(Set<TuserTrole> tuserTroles) {
		this.tuserTroles = tuserTroles;
	}

}