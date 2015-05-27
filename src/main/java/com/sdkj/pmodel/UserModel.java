package com.sdkj.pmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @文件名：UserModel.java
 * @作用：
 * @作者：张剑
 * @创建时间：2014-05-29 09:55:38
 */

//
public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;
	// 属性
	private String id;// id
	private String ids;// id
	private String jobNum;// 工号
	private String name;// 姓名
	private String pwd;// 密码
	private Date birthday;// 生日
	private String nativePlace;// 籍贯
	private String sex;// 性别
	private String pic;// 头像url
	private String email;// 邮箱
	private String phone;// 手机号
	private String address;// 住址
	private String education;// 学历
	private String positionId;// 职位ID
	private String positionName;// 职位名
	private String deptId;;// 部门ID
	private String deptName;// 部门名
	private Date createtime;
	private String resourceIds;
	private String resourceNames;
	private String roleIds;
	private String roleNames;
	private List<String> resourceUrls = new ArrayList<String>();

	// 构造方法
	public UserModel() {
	}

	public UserModel(String id, String jobNum, String name, String pwd, Date birthday, String nativePlace, String sex, String pic, String email, String phone, String address, String education, String positionId) {
		this.id = id;
		this.jobNum = jobNum;
		this.name = name;
		this.pwd = pwd;
		this.birthday = birthday;
		this.nativePlace = nativePlace;
		this.sex = sex;
		this.pic = pic;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.education = education;
		this.positionId = positionId;
	}

	// get-set方法
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getResourceNames() {
		return resourceNames;
	}

	public void setResourceNames(String resourceNames) {
		this.resourceNames = resourceNames;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public List<String> getResourceUrls() {
		return resourceUrls;
	}

	public void setResourceUrls(List<String> resourceUrls) {
		this.resourceUrls = resourceUrls;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
