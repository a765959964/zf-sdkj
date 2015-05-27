package com.sdkj.controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sdkj.pmodel.ArticleModel;
import com.sdkj.pmodel.ColumnModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.TlinksModel;
import com.sdkj.service.ArticleService;
import com.sdkj.service.ColumnService;
import com.sdkj.service.TlinksService;

@Controller
@RequestMapping("/lzcc")
public class LzccController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ColumnService columnService;
	@Autowired
	private TlinksService tlinksService;
	
	

	
	@RequestMapping("/index")
	public String index(PageModel page, HttpServletRequest request) {
		//详情
		List<TlinksModel> tlinks = tlinksService.tlinksList();
		// 一级菜单
		List<ColumnModel> list1 = columnService.columnListFirst();
		// 子栏目
		List<ColumnModel> list2 = columnService.columnListSecond();
		for (ColumnModel columnModel : list1) {
			for (ColumnModel column2 : list2) {
				if (column2.getPid().equals(columnModel.getId())) {
					columnModel.getList().add(column2);
				}
			}
		}
		// 产品中心
		@SuppressWarnings("unchecked")
		List<ArticleModel> productList = articleService.getArticleListByColumn(
				page, "5IBAAG3K08X12").getList();

		// 产品中心 栏目列表
		List<ColumnModel> columnList = columnService
				.columnListByCid("5IBAAG3K08X12");
		// 茶叶分类
		@SuppressWarnings("unchecked")
		List<ArticleModel> chayefl = articleService.getArticleListByColumn(
				page, "5IBAAG3V1HZ1B").getList();

		LinkedHashMap<ColumnModel, List<ColumnModel>> columnMap = new LinkedHashMap<ColumnModel, List<ColumnModel>>();
		for (ColumnModel cm : columnList) {
			List<ColumnModel> columnList2 = columnService.sonColumnListByCid(cm
					.getId());
			if (columnList2.size() == 0) {
				columnMap.put(cm, new LinkedList<ColumnModel>());
			} else
				columnMap.put(cm, columnList2);
		}
		// 公司简介
		@SuppressWarnings("unchecked")
		List<ArticleModel> jianjieList = articleService.getArticleListByColumn(
				page, "5IBAAG3KO4F13").getList();

		// 企业简介
		@SuppressWarnings("unchecked")
		List<ArticleModel> qiye = articleService.getArticleListByColumn(
				page, "5IBAAG3QQ5H18").getList();

		// 茶园动态
		@SuppressWarnings("unchecked")
		List<ArticleModel> chayuanList = articleService.getArticleListByColumn(
				page, "5IBAAG3NSZH15").getList();

		// 茶具介绍
		@SuppressWarnings("unchecked")
		List<ArticleModel> chajuList = articleService.getArticleListByColumn(
				page, "5IBAAG3VFUG1C").getList();

		// 行业资讯
		@SuppressWarnings("unchecked")
		List<ArticleModel> hyzx = articleService.getArticleListByColumn(page,
				"5IBAAG3SB1M19").getList();

		List<ColumnModel> gsList = columnService
				.columnListByCid("5IBAAG3K08X12");
		// 信阳毛尖
		@SuppressWarnings("unchecked")
		List<ArticleModel> maojian = articleService.getArticleListByColumn(
				page, "5IBAE63A60D00").getList();

		// 信阳红
		@SuppressWarnings("unchecked")
		List<ArticleModel> xinyanghong = articleService.getArticleListByColumn(
				page, "5IBAE63BR7601").getList();

		// 普洱茶
		@SuppressWarnings("unchecked")
		List<ArticleModel> puercha = articleService.getArticleListByColumn(
				page, "5IBAE63EK6O02").getList();

		// 台湾乌龙
		@SuppressWarnings("unchecked")
		List<ArticleModel> wulong = articleService.getArticleListByColumn(page,
				"5IBAE63G12J03").getList();

		// 亚山小种
		@SuppressWarnings("unchecked")
		List<ArticleModel> xiaozhong = articleService.getArticleListByColumn(
				page, "5IBAE63GLEQ04").getList();

		// 福鼎白茶
		@SuppressWarnings("unchecked")
		List<ArticleModel> baicha = articleService.getArticleListByColumn(
				page, "5IBAE63H4VP05").getList();
		
		request.setAttribute("baicha", baicha);
		request.setAttribute("qiye", qiye);
		request.setAttribute("xinyanghong", xinyanghong);
		request.setAttribute("puercha", puercha);
		request.setAttribute("wulong", wulong);
		request.setAttribute("xiaozhong", xiaozhong);
		request.setAttribute("maojian", maojian);
		request.setAttribute("hyzx", hyzx);
		request.setAttribute("chayefl", chayefl);
		request.setAttribute("chajuList", chajuList);
		request.setAttribute("chayuanList", chayuanList);
		request.setAttribute("jianjieList", jianjieList);
		request.setAttribute("gsList", gsList);
		request.setAttribute("menuList", list1);
		request.setAttribute("productList", productList);
		request.setAttribute("columnMap", columnMap);
		request.setAttribute("tlinks", tlinks);
		
		return "/qt/index";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	public String list(PageModel page, String cid, HttpServletRequest request) {
		//详情
		List<TlinksModel> tlinks = tlinksService.tlinksList();
		
		// 导航
		List<ColumnModel> list1 = columnService.columnListFirst();
		List<ColumnModel> list2 = columnService.columnListSecond();
		for (ColumnModel columnModel : list1) {
			for (ColumnModel column2 : list2) {
				if (column2.getPid().equals(columnModel.getId())) {
					columnModel.getList().add(column2);
				}
			}
		}
		// 顶层栏目
		ColumnModel topcolumn = columnService.topColumnByCid(cid);
		// 当前栏目
		ColumnModel column = columnService.columnByCid(cid);
		// 若是产品中心第三层
		List<ColumnModel> columnList = new LinkedList<ColumnModel>();
		// if(topcolumn.getId()!="5IBAAG3V1HZ1B"){
		// columnList = columnService.columnListByCid("5IBAAG3V1HZ1B");
		// topcolumn= columnService.topColumnByCid("5IBAAG3V1HZ1B");
		// }else{
		columnList = columnService.columnListByCid(topcolumn.getId());
		// }
		// 栏目列表
		LinkedHashMap<ColumnModel, List<ColumnModel>> columnMap = new LinkedHashMap<ColumnModel, List<ColumnModel>>();
		for (ColumnModel cm : columnList) {
			List<ColumnModel> columnList2 = columnService.sonColumnListByCid(cm
					.getId());
			if (columnList2.size() == 0) {
				columnMap.put(cm, new LinkedList<ColumnModel>());
			} else
				columnMap.put(cm, columnList2);
		}
		// 文章列表
		page.setRows(4);
		// 判定is新品上市
		page = articleService.getArticleListByColumn(page, cid);

		List<ArticleModel> articleList = page.getList();
		request.setAttribute("menuList", list1);
		request.setAttribute("list2", list2);
		request.setAttribute("columnList", columnList);
		request.setAttribute("articleList", articleList);
		request.setAttribute("topcolumn", topcolumn);
		request.setAttribute("columnMap", columnMap);
		request.setAttribute("column", column);
		request.setAttribute("page", page);
		request.setAttribute("cid", cid);
		
		
		request.setAttribute("tlinks", tlinks);
		return "/static_lzcc/list";
	}

	/**
	 * 详情
	 * 
	 * @param articleId
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(String articleId, HttpServletRequest request) {
		// 导航
		List<ColumnModel> list1 = columnService.columnListFirst();
		List<ColumnModel> list2 = columnService.columnListSecond();
		for (ColumnModel columnModel : list1) {
			for (ColumnModel column2 : list2) {
				if (column2.getPid().equals(columnModel.getId())) {
					columnModel.getList().add(column2);
				}
			}
		}
		ColumnModel topcolumn = columnService.topColumnByArticleId(articleId);
		// 文章
		ArticleModel article = articleService.getModelById(articleId);
		// 列表
		List<ColumnModel> columnList = columnService.columnListByCid(article
				.getCid());
		LinkedHashMap<ColumnModel, List<ColumnModel>> columnMap = new LinkedHashMap<ColumnModel, List<ColumnModel>>();
		for (ColumnModel cm : columnList) {
			List<ColumnModel> columnList2 = columnService.sonColumnListByCid(cm
					.getId());
			if (columnList2.size() == 0) {
				columnMap.put(cm, new LinkedList<ColumnModel>());
			} else
				columnMap.put(cm, columnList2);
		}
		request.setAttribute("menuList", list1);
		request.setAttribute("columnList", columnList);
		request.setAttribute("article", article);
		request.setAttribute("topcolumn", topcolumn);
		request.setAttribute("columnMap", columnMap);
		List<TlinksModel> tlinks = tlinksService.tlinksList();
		request.setAttribute("tlinks", tlinks);
		return "/static_lzcc/lzcc_center_details";
	}

}
