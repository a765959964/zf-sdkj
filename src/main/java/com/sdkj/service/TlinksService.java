package com.sdkj.service;

import java.util.LinkedList;
import java.util.List;

import com.sdkj.model.Tcolumn;
import com.sdkj.model.Tlinks;
import com.sdkj.pmodel.ColumnModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.TlinksModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;

public interface TlinksService {
	// 增
	public TlinksModel save(TlinksModel tlinksModel);

	// 删
	public void remove(String ids);

	// 改
	public TlinksModel edit(TlinksModel tlinksModel);

	// 查
	public TlinksModel getModelById(String id);

	// 查
	public EasyuiDatagrid datagrid(PageModel page);
	
	//查旬全部
	public List<TlinksModel> tlinksList();
	

}
