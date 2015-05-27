package com.sdkj.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.dao.BaseDaoI;
import com.sdkj.model.Ticon;
import com.sdkj.pmodel.IconModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.IconService;
import com.sdkj.util.ZJ_GeneratorUtils;

@Service("iconService")
public class IconServiceImpl implements IconService {
	@Autowired
	private BaseDaoI<Ticon> iconDao;

	private String getTotalHql(PageModel page, String hql) {
		hql += " where 1=1 ";
		if (page.getName() != null && !page.getName().trim().equals("")) {
			hql += " and t.name like '%" + page.getName().trim() + "%'";
		}
		return hql;
	}

	private String getListHql(PageModel page, String hql) {
		hql = getTotalHql(page, hql);
		if (page.getSort() != null) {
			hql += " order by " + page.getSort() + " " + page.getOrder();
		}
		return hql;
	}

	private void changeModel(List<Ticon> l, List<IconModel> nl) {
		if (l != null && l.size() > 0) {
			for (Ticon t : l) {
				IconModel u = new IconModel();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	@Override
	public EasyuiDatagrid datagrid(PageModel page) {
		String hql = "from Ticon t";
		String hql2 = "select count(*) from Ticon t";
		String listHql = getListHql(page, hql);
		String totalHql = getTotalHql(page, hql2);
		List<Ticon> tcolumuList = iconDao.find(listHql, null, page.getPage(), page.getRows());
		List<IconModel> iconModels = new LinkedList<IconModel>();
		changeModel(tcolumuList, iconModels);
		Long total = iconDao.count(totalHql);
		EasyuiDatagrid dataGrid = new EasyuiDatagrid();
		dataGrid.setRows(iconModels);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@Override
	public IconModel save(IconModel iconModel) {
		iconModel.setId(ZJ_GeneratorUtils.idGenerator());
		Ticon t = new Ticon();
		BeanUtils.copyProperties(iconModel, t);
		iconDao.save(t);
		return iconModel;
	}

	@Override
	public void remove(String ids) {
		for (String id : ids.split(",")) {
			Ticon t = iconDao.get(Ticon.class, id);
			if (t != null) {
				iconDao.delete(t);
			}
		}
	}

	@Override
	public IconModel edit(IconModel iconModel) {
		Ticon t = iconDao.get(Ticon.class, iconModel.getId());
		BeanUtils.copyProperties(iconModel, t);
		iconDao.update(t);
		return iconModel;
	}

	@Override
	public IconModel getModelById(String id) {
		Ticon ticon = iconDao.get(Ticon.class, id);
		IconModel iconModel = new IconModel();
		if (null != ticon) {
			BeanUtils.copyProperties(ticon, iconModel);
		}
		return iconModel;
	}

}
