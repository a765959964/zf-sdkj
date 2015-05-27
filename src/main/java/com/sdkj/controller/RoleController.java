package com.sdkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdkj.pmodel.Json;
import com.sdkj.pmodel.Role;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@RequestMapping("/datagrid")
	@ResponseBody
	public EasyuiDatagrid datagrid(Role role) {
		return roleService.datagrid(role);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Role role) {
		Json j = new Json();
		try {
			Role r = roleService.save(role);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Json remove(Role role) {
		roleService.remove(role.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		return j;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Role role) {
		Json j = new Json();
		try {
			Role u = roleService.edit(role);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/combogrid")
	@ResponseBody
	public EasyuiDatagrid combogrid(Role role) {
		return roleService.datagrid(role);
	}

}
