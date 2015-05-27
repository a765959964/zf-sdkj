package com.sdkj.service;

import java.util.List;

import com.sdkj.pmodel.ColumnModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.ComboTree;
import com.sdkj.pmodel.ui.DataGird;



public interface ColumnService {

	public ColumnModel save(ColumnModel columnModel);

	public List<ColumnModel> columnList();

	public List<ColumnModel> columnListFirst();

	public List<ColumnModel> columnListSecond();

	public List<ColumnModel> columnListByCid(String cid);
	public List<ColumnModel> sonColumnListByCid(String pid);

	public List<ColumnModel> columnListByArticleId(String articleId);

	public ColumnModel columnByCid(String cid);

	public ColumnModel columnByArticleId(String articleId);

	public ColumnModel topColumnByCid(String cid);

	public ColumnModel topColumnByArticleId(String articleId);

	public void remove(String ids);

	public ColumnModel edit(ColumnModel columnModel);

	public DataGird datagrid(PageModel page);

	public ColumnModel getModelById(String id);

	public List<ComboTree> allTreeNode();
	

}
