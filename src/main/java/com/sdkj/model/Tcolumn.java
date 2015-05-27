package com.sdkj.model;

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
 * Tcolumn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tcolumn", catalog = "")
public class Tcolumn implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	private String cname;
	private String tip;
	private String pic;
	private Integer type;
	private Integer isMenu;
	private Tcolumn tcolumn;
	private Integer sort;
	private Set<Tarticle> tarticles = new HashSet<Tarticle>(0);
	private Set<Tcolumn> tcolumns = new HashSet<Tcolumn>(0);

	// Constructors

	/** default constructor */
	public Tcolumn() {
	}

	/** minimal constructor */
	public Tcolumn(String id) {
		this.id = id;
	}

	/** full constructor */
	

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 20)
	public String getId() {
		return this.id;
	}

	public Tcolumn(String id, String cname, String tip, String pic,
			Integer type, Integer isMenu, Tcolumn tcolumn, Integer sort,
			Set<Tarticle> tarticles, Set<Tcolumn> tcolumns) {
		super();
		this.id = id;
		this.cname = cname;
		this.tip = tip;
		this.pic = pic;
		this.type = type;
		this.isMenu = isMenu;
		this.tcolumn = tcolumn;
		this.sort = sort;
		this.tarticles = tarticles;
		this.tcolumns = tcolumns;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Tcolumn getTcolumn() {
		return this.tcolumn;
	}

	public void setTcolumn(Tcolumn tcolumn) {
		this.tcolumn = tcolumn;
	}

	@Column(name = "cname")
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "tip")
	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	@Column(name = "pic")
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "isMenu")
	public Integer getIsMenu() {
		return this.isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}
	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tcolumn")
	public Set<Tarticle> getTarticles() {
		return this.tarticles;
	}

	public void setTarticles(Set<Tarticle> tarticles) {
		this.tarticles = tarticles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tcolumn")
	public Set<Tcolumn> getTcolumns() {
		return this.tcolumns;
	}

	public void setTcolumns(Set<Tcolumn> tcolumns) {
		this.tcolumns = tcolumns;
	}

}