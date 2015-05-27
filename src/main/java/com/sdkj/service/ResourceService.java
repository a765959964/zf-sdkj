package com.sdkj.service;

import java.util.List;

import com.sdkj.pmodel.ui.EasyuiMenu;
import com.sdkj.pmodel.ui.EasyuiTreegrid;

public interface ResourceService {

	public List<EasyuiTreegrid> treegrid();

	public List<EasyuiTreegrid> allTreeNode();

	public List<EasyuiTreegrid> allTreeNodeByResIds(String resIds);

	public List<EasyuiMenu> allMenuNode();

	public List<EasyuiMenu> allMenuNodeByResIds(String resIds);

	public EasyuiTreegrid add(EasyuiTreegrid resource);

	public void remove(String id);

	public EasyuiTreegrid edit(EasyuiTreegrid resource);

}
