package com.sdkj.service;

import java.util.List;

import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.UserModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;

public interface UserService {

	public UserModel save(UserModel userModel);

	public UserModel find(UserModel userModel);

	public EasyuiDatagrid datagrid(PageModel page);

	public List<UserModel> combobox(UserModel userModel);

	public void remove(String ids);

	public UserModel edit(UserModel userModel);

	public void modifyRole(UserModel userModel);

	public void modifyPwd(UserModel userModel);

	public UserModel getModelById(String id);

}
