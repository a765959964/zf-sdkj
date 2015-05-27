package com.sdkj.pmodel;

import java.util.LinkedList;
import java.util.List;

import com.sdkj.util.ZJ_UrlUtils;


public class ColumnModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String cname;
	private String pid;
	private String pname;
	private String tip;
	private String pic;
	private Integer type;
	private Integer isMenu;
	private Integer sort;
	private List<ColumnModel> list = new LinkedList<ColumnModel>();
	

	public ColumnModel() {
		super();
	}
	public ColumnModel(String id, String cname, String pid, String pname,
			String tip, String pic, Integer type, Integer isMenu, Integer sort,
			List<ColumnModel> list) {
		super();
		this.id = id;
		this.cname = cname;
		this.pid = pid;
		this.pname = pname;
		this.tip = tip;
		this.pic = pic;
		this.type = type;
		this.isMenu = isMenu;
		this.sort = sort;
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ColumnModel> getList() {
		return list;
	}

	public void setList(List<ColumnModel> list) {
		this.list = list;
	}
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getPic() {
		pic = ZJ_UrlUtils.changeURL(pic);
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}
	@Override
	public String toString() {
		return "ColumnModel [id=" + id + ", cname=" + cname + ", pid=" + pid
				+ ", pname=" + pname + ", tip=" + tip + ", pic=" + pic
				+ ", type=" + type + ", isMenu=" + isMenu + ", sort=" + sort
				+ ", list=" + list + "]";
	}
	
}