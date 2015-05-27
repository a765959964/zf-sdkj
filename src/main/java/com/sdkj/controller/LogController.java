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
import com.sdkj.pmodel.LogModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.LogService;

@Controller
@RequestMapping("/log")
public class LogController {
	@Autowired
	private LogService logService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ResponseBody
	@RequestMapping("/datagrid")
	public EasyuiDatagrid datagrid(PageModel pageModel) {
		return logService.datagrid(pageModel);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(LogModel logModel) {
		Json j = new Json();
		try {
			LogModel r = logService.save(logModel);
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
		LogModel logModel = logService.getModelById(id);
		request.setAttribute("log", logModel);
		return "/ht/log/logShow";
	}

	@RequestMapping("/editView")
	public String editView(String id, HttpServletRequest request) {
		LogModel logModel = logService.getModelById(id);
		request.setAttribute("log", logModel);
		return "/ht/log/logEdit";
	}

	@ResponseBody
	@RequestMapping("/edit")
	public Json edit(LogModel logModel) {
		Json j = new Json();
		try {
			LogModel r = logService.edit(logModel);
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
		logService.remove(pageModel.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		return j;
	}

	@RequestMapping("/show")
	public String showNote(String id, HttpServletRequest request) {
		LogModel logModel = logService.getModelById(id);
		request.setAttribute("logModel", logModel);
		return "ht/log/logShow";
	}
}
