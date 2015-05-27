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

import com.sdkj.pmodel.Json;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.TlinksModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.TlinksService;

@Controller
@RequestMapping("/tlinks")
public class TlinksController {
	@Autowired
	private TlinksService tlinksService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ResponseBody
	@RequestMapping("/datagrid")
	public EasyuiDatagrid datagrid(PageModel pageModel) {
		return tlinksService.datagrid(pageModel);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(TlinksModel tlinksModel) {
		Json j = new Json();
		try {
			TlinksModel r = tlinksService.save(tlinksModel);
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
		TlinksModel tlinksModel = tlinksService.getModelById(id);
		request.setAttribute("tlinks", tlinksModel);
		return "/ht/tlinks/tlinksShow";
	}

	@RequestMapping("/editView")
	public String editView(String id, HttpServletRequest request) {
		TlinksModel tlinksModel = tlinksService.getModelById(id);
		request.setAttribute("tlinks", tlinksModel);
		return "/ht/tlinks/tlinksEdit";
	}

	@ResponseBody
	@RequestMapping("/edit")
	public Json edit(TlinksModel tlinksModel) {
		Json j = new Json();
		try {
			TlinksModel r = tlinksService.edit(tlinksModel);
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
		tlinksService.remove(pageModel.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		return j;
	}

	@RequestMapping("/show")
	public String showNote(String id, HttpServletRequest request) {
		TlinksModel tlinksModel = tlinksService.getModelById(id);
		request.setAttribute("tlinksModel", tlinksModel);
		return "ht/tlinks/tlinksShow";
	}
}
