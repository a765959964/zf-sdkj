package com.sdkj.service;

import com.sdkj.pmodel.IconModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;

public interface IconService {
	// 增
	public IconModel save(IconModel iconModel);

	// 删
	public void remove(String ids);

	// 改
	public IconModel edit(IconModel iconModel);

	// 查
	public IconModel getModelById(String id);

	// 查
	public EasyuiDatagrid datagrid(PageModel page);

}
