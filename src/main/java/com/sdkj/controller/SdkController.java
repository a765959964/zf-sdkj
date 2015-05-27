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
@RequestMapping("/sd")
public class SdkController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ColumnService columnService;
	@Autowired
	private TlinksService tlinksService;	//合作伙伴
	
	

	
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

		// 产品中心 栏目列表
		List<ColumnModel> columnList = columnService
				.columnListByCid("5IBAAG3K08X12");

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
		List<ArticleModel> gongsijianjie = articleService.getArticleListByColumn(
				page, "5IBAAG3KO4F13").getList();

		//业务体系
		List<ColumnModel> yewutixi = columnService.sonColumnListByCid("5IBAAG3JL2811");
		
		//联系我们
		@SuppressWarnings("unchecked")
		List<ArticleModel> lianxiwomen = articleService.getArticleListByColumn(page, "5IERHKAWDVT01").getList();
		
		//公司新闻
		@SuppressWarnings("unchecked")
		List<ArticleModel> gongsixinwen = articleService.getArticleListByColumn(page, "5IERJW4FVJI01").getList();
		
		
		request.setAttribute("menuList", list1);
		request.setAttribute("columnMap", columnMap);
		request.setAttribute("tlinks", tlinks);		//合作伙伴
		
		request.setAttribute("yewutixi",yewutixi );		//业务体系
		request.setAttribute("lianxiwomen", lianxiwomen);	//联系我们
		request.setAttribute("gsjj", gongsijianjie);	//公司简介
		request.setAttribute("gongsixinwen", gongsixinwen); 	//公司新闻
		
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
		
		columnList = columnService.columnListByCid(topcolumn.getId());
		
		ArticleModel article = articleService.getModelByCid(cid);
		
		//业务体系
		List<ColumnModel> yewutixi = columnService.sonColumnListByCid("5IBAAG3JL2811");
		
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
		page = articleService.getArticleListByColumn(page, cid);

		ArticleModel lianxiwomen = articleService.getModelByCid("5IERHKAWDVT01");	//联系我们
		
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
		request.setAttribute("lianxiwomen", lianxiwomen);  	//联系我们
		request.setAttribute("article", article);
		request.setAttribute("yewutixi", yewutixi);	
		return "/qt/detail";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/page")
	public String page(PageModel page,String cid,HttpServletRequest request){
		
		List<TlinksModel> tlinks = tlinksService.tlinksList();	//详情
		List<ColumnModel> yewutixi = columnService.sonColumnListByCid("5IBAAG3JL2811");	//业务体系
		ArticleModel lianxiwomen = articleService.getModelByCid("5IERHKAWDVT01");	//联系我们
		
		List<ColumnModel> list1 = columnService.columnListFirst();	
		List<ColumnModel> list2 = columnService.columnListSecond();
		
		for (ColumnModel m1 : list1) {
			for (ColumnModel m2  : list2) {
				if(m2.getPid().equals(m1.getId())){
					m1.getList().add(m2);
					
				}
			}
			
		}
		
		ColumnModel topcolumn = columnService.topColumnByCid(cid);	//获得上级 栏目
		
		ColumnModel column = columnService.columnByCid(cid);	// 获得当前栏目
		
		List<ColumnModel> columnList = new LinkedList<ColumnModel>();
		
		columnList = columnService.columnListByCid(topcolumn.getId());
		
		ArticleModel article = articleService.getModelByCid(cid);	//获得内容
		
		page.setRows(5);
		page = articleService.getArticleListByColumn(page, cid);
		
		List<ArticleModel> articleList = page.getList();
		request.setAttribute("tlinks", tlinks);
		request.setAttribute("yewutixi", yewutixi); 
		request.setAttribute("menuList",list1 );
		request.setAttribute("columnList", columnList);
		request.setAttribute("articleList", articleList);
		request.setAttribute("page", page);
		request.setAttribute("article",article );
		request.setAttribute("topcolumn", topcolumn);
		request.setAttribute("column",column);
		request.setAttribute("cid", cid);
		request.setAttribute("lianxiwomen", lianxiwomen);
		return "/qt/page";
	}
	
	
	
	/**
	 * 详细页面
	 * @param articleId
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/detail")
	public String details(String articleId,HttpServletRequest request){
		
		ColumnModel column = columnService.columnByArticleId(articleId);
		
		
		ArticleModel article =	articleService.getModelById(articleId);	//文章信息
		List<ColumnModel> columnList = columnService.columnListByCid(article.getCid());	//获得栏目信息
		
		List<TlinksModel> tlinks = tlinksService.tlinksList();	//详情
		List<ColumnModel> yewutixi = columnService.sonColumnListByCid("5IBAAG3JL2811");	//业务体系
		ArticleModel lianxiwomen = articleService.getModelByCid("5IERHKAWDVT01");	//联系我们
		
		List<ColumnModel> list1 = columnService.columnListFirst();	
		List<ColumnModel> list2 = columnService.columnListSecond();
		
		for (ColumnModel m1 : list1) {
			for (ColumnModel m2  : list2) {
				if(m2.getPid().equals(m1.getId())){
					m1.getList().add(m2);
				}
			}
		}
		
		
		request.setAttribute("menuList",list1 );
		request.setAttribute("columnList", columnList);
		request.setAttribute("article", article);
		request.setAttribute("column",column);
		request.setAttribute("tlinks", tlinks);
		request.setAttribute("yewutixi", yewutixi);
		request.setAttribute("lianxiwomen", lianxiwomen);
		return "/qt/pagedetail";
	}
	
	
}
