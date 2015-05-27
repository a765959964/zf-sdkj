package com.sdkj.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdkj.pmodel.IconModel;
import com.sdkj.pmodel.Json;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.IconService;

@Controller
@RequestMapping("/icon")
public class IconController {
	@Autowired
	private IconService iconService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ResponseBody
	@RequestMapping("/datagrid")
	public EasyuiDatagrid datagrid(PageModel pageModel) {
		return iconService.datagrid(pageModel);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(IconModel iconModel) {
		Json j = new Json();
		try {
			IconModel r = iconService.save(iconModel);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/showView")
	public String showView(String id, HttpServletRequest request) {
		IconModel iconModel = iconService.getModelById(id);
		request.setAttribute("icon", iconModel);
		return "/ht/icon/iconShow";
	}

	@RequestMapping("/editView")
	public String editView(String id, HttpServletRequest request) {
		IconModel iconModel = iconService.getModelById(id);
		request.setAttribute("icon", iconModel);
		return "/ht/icon/iconEdit";
	}

	@ResponseBody
	@RequestMapping("/edit")
	public Json edit(IconModel iconModel) {
		Json j = new Json();
		try {
			IconModel r = iconService.edit(iconModel);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
			j.setObj(r);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@ResponseBody
	@RequestMapping("/remove")
	public Json remove(PageModel pageModel) {
		iconService.remove(pageModel.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		return j;
	}

	@RequestMapping("/show")
	public String showNote(String id, HttpServletRequest request) {
		IconModel iconModel = iconService.getModelById(id);
		request.setAttribute("iconModel", iconModel);
		return "ht/icon/iconShow";
	}
}
