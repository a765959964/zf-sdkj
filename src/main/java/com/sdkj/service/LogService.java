package com.sdkj.service;

import com.sdkj.pmodel.LogModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;

public interface LogService {
	// 增
	public LogModel save(LogModel logModel);

	// 删
	public void remove(String ids);

	// 改
	public LogModel edit(LogModel logModel);

	// 查
	public LogModel getModelById(String id);

	// 查
	public EasyuiDatagrid datagrid(PageModel page);

}
