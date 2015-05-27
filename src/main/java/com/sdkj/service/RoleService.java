package com.sdkj.service;

import com.sdkj.pmodel.Role;
import com.sdkj.pmodel.ui.EasyuiDatagrid;

public interface RoleService {

	public EasyuiDatagrid datagrid(Role role);

	public Role save(Role role);

	public void remove(String ids);

	public Role edit(Role role);

}
