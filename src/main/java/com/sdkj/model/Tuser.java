package com.sdkj.model;

import java.util.Date;
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
 * Tuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tuser", catalog = "")
public class Tuser implements java.io.Serializable {

	// Fields

	private String id;
	private String email;
	private String phone;
	private String name;
	private String pwd;
	private String sex;
	private String pic;
	private Date createtime;
	private Set<TuserTrole> tuserTroles = new HashSet<TuserTrole>(0);

	// Constructors

	/** default constructor */
	public Tuser() {
	}

	/** minimal constructor */
	public Tuser(String id, String name, String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}

	/** full constructor */
	public Tuser(String id, String email, String phone, String name,
			String pwd, String sex, String pic, Date createtime,
			Set<TuserTrole> tuserTroles) {
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.pic = pic;
		this.createtime = createtime;
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

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 11)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pwd", nullable = false, length = 100)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "pic", length = 100)
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<TuserTrole> getTuserTroles() {
		return this.tuserTroles;
	}

	public void setTuserTroles(Set<TuserTrole> tuserTroles) {
		this.tuserTroles = tuserTroles;
	}

}