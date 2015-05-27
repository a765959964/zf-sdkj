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

import com.sdkj.pmodel.ArticleModel;
import com.sdkj.pmodel.Json;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.ComboTree;
import com.sdkj.pmodel.ui.DataGird;
import com.sdkj.pmodel.PageModel;
import com.sdkj.service.ArticleService;



/**
 * 文章
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired	
	private ArticleService articleService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ResponseBody
	@RequestMapping("/datagrid")
	public DataGird datagrid(PageModel page) {
		return articleService.datagrid(page);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(ArticleModel articleModel) {
		Json j = new Json();
		try {
			ArticleModel r = articleService.save(articleModel);
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
		return articleService.allTreeNode();
	}

	@RequestMapping("/showView")
	public String showView(String id, HttpServletRequest request) {
		ArticleModel articleModel = articleService.getModelById(id);
		request.setAttribute("article", articleModel);
		return "/ht/article/articleShow";
	}

	@RequestMapping("/editView")
	public String editView(String id, HttpServletRequest request) {
		ArticleModel articleModel = articleService.getModelById(id);
		request.setAttribute("article", articleModel);
		return "/ht/article/articleEdit";
	}

	@ResponseBody
	@RequestMapping("/edit")
	public Json edit(ArticleModel articleModel) {
		Json j = new Json();
		try {
			ArticleModel r = articleService.edit(articleModel);
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
		articleService.remove(pageModel.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		return j;
	}

	@RequestMapping("/show")
	public String showNote(String id, HttpServletRequest request) {
		ArticleModel articleModel = articleService.getModelById(id);
		request.setAttribute("articleModel", articleModel);
		return "ht/article/articleShow";
	}
}
