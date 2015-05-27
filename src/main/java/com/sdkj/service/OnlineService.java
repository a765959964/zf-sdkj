package com.sdkj.service;

import com.sdkj.pmodel.Online;
import com.sdkj.pmodel.ui.EasyuiDatagrid;

public interface OnlineService {

	public void saveOrUpdateTonlineByLoginNameAndIp(String loginName, String ip);

	public void deleteTonlineByLoginNameAndIp(String loginName, String ip);

	public EasyuiDatagrid datagrid(Online online);

}
