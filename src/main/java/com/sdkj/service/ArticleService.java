package com.sdkj.service;

import java.util.List;

import com.sdkj.pmodel.ArticleModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.ComboTree;
import com.sdkj.pmodel.ui.DataGird;




public interface ArticleService {
	// 增
	public ArticleModel save(ArticleModel articleModel);

	// 删
	public void remove(String ids);

	// 改
	public ArticleModel edit(ArticleModel articleModel);

	// 查
	public ArticleModel getModelById(String articleId);

	// 查
	public DataGird datagrid(PageModel page);
	
	
	public List<ComboTree> allTreeNode();

	
	public PageModel getArticleListByColumn(PageModel page, String cId);
	
	public ArticleModel getModelByCid(String cid);

}