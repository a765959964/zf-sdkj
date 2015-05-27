package com.sdkj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdkj.pmodel.Json;
import com.sdkj.pmodel.SessionInfo;
import com.sdkj.pmodel.ui.EasyuiMenu;
import com.sdkj.pmodel.ui.EasyuiTreegrid;
import com.sdkj.service.ResourceService;
import com.sdkj.util.ZJ_ResourceUtil;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;

	@RequestMapping("/treegrid")
	@ResponseBody
	public List<EasyuiTreegrid> treegrid() {
		return resourceService.treegrid();
	}

	@RequestMapping("/allTreeNode")
	@ResponseBody
	public List<EasyuiTreegrid> allTreeNode(HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ZJ_ResourceUtil.getSessionInfoName());
		String userId = sessionInfo.getAdminId();
		String resourceIds = sessionInfo.getResourceIds();
		if (userId.equals("0")) {
			return resourceService.allTreeNode();
		} else {
			return resourceService.allTreeNodeByResIds(resourceIds);
		}

	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(EasyuiTreegrid resource) {
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(resourceService.add(resource));
		j.setMsg("添加成功!");
		return j;
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Json remove(String id) {
		Json j = new Json();
		resourceService.remove(id);
		j.setSuccess(true);
		j.setObj(id);
		j.setMsg("删除成功!");
		return j;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(EasyuiTreegrid resource) {
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(resourceService.edit(resource));
		j.setMsg("编辑成功!");
		return j;
	}

	@RequestMapping("/allMenuNode")
	@ResponseBody
	public List<EasyuiMenu> allMenuNode(HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ZJ_ResourceUtil.getSessionInfoName());
		String userId = sessionInfo.getAdminId();
		String resourceIds = sessionInfo.getResourceIds();
		if ("0".equals(userId)) {
			return resourceService.allMenuNode();
		} else {
			return resourceService.allMenuNodeByResIds(resourceIds);
		}
	}

}
