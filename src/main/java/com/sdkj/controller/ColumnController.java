package com.sdkj.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdkj.pmodel.ColumnModel;
import com.sdkj.pmodel.Json;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.ComboTree;
import com.sdkj.pmodel.ui.DataGird;
import com.sdkj.service.ColumnService;

@Controller
@RequestMapping("/column")
public class ColumnController {
	@Autowired
	private ColumnService columnService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ResponseBody
	@RequestMapping("/treegrid")
	public List<ColumnModel> columnList() {
		return columnService.columnList();
	}

	@ResponseBody
	@RequestMapping("/datagrid")
	public DataGird datagrid(PageModel pageModel) {
		return columnService.datagrid(pageModel);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(ColumnModel columnModel) {
		Json j = new Json();
		try {
			ColumnModel r = columnService.save(columnModel);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/allTreeNode")
	@ResponseBody
	public List<ComboTree> allTreeNode() {
		return columnService.allTreeNode();
	}

	@RequestMapping("/editView")
	public String editView(String id, HttpServletRequest request) {
		ColumnModel columnModel = columnService.getModelById(id);
		request.setAttribute("column", columnModel);
		return "/ht/column/lmglEdit";
	}

	@ResponseBody
	@RequestMapping("/edit")
	public Json edit(ColumnModel columnModel) {
		Json j = new Json();
		try {
			ColumnModel r = columnService.edit(columnModel);
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
		columnService.remove(pageModel.getId());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		return j;
	}

}
