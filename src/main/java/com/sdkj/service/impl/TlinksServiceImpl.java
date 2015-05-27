package com.sdkj.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.dao.BaseDaoI;
import com.sdkj.model.Tcolumn;
import com.sdkj.model.Tlinks;
import com.sdkj.pmodel.ColumnModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.TlinksModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.TlinksService;
import com.sdkj.util.ZJ_GeneratorUtils;

@Service("tlinksService")
public class TlinksServiceImpl implements TlinksService {
	@Autowired
	private BaseDaoI<Tlinks> tlinksDao;

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
	
	
	private void changeModel(List<Tlinks> l, List<TlinksModel> nl) {
		if (l != null && l.size() > 0) {
			for (Tlinks t : l) {
				TlinksModel u = new TlinksModel();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	@Override
	public EasyuiDatagrid datagrid(PageModel page) {
		String hql = "from Tlinks t";
		String hql2 = "select count(*) from Tlinks t";
		String listHql = getListHql(page, hql);
		String totalHql = getTotalHql(page, hql2);
		List<Tlinks> tcolumuList = tlinksDao.find(listHql, null, page.getPage(), page.getRows());
		List<TlinksModel> tlinksModel = new LinkedList<TlinksModel>();
		changeModel(tcolumuList, tlinksModel);
		Long total = tlinksDao.count(totalHql);
		EasyuiDatagrid dataGrid = new EasyuiDatagrid();
		dataGrid.setRows(tlinksModel);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@Override
	public TlinksModel save(TlinksModel tlinksModel) {
		tlinksModel.setId(ZJ_GeneratorUtils.idGenerator());
		Tlinks t = new Tlinks();
		BeanUtils.copyProperties(tlinksModel, t);
		tlinksDao.save(t);
		return tlinksModel;
	}

	@Override
	public void remove(String ids) {
		for (String id : ids.split(",")) {
			Tlinks t = tlinksDao.get(Tlinks.class, id);
			if (t != null) {
				tlinksDao.delete(t);
			}
		}
	}

	@Override
	public TlinksModel edit(TlinksModel tlinksModel) {
		Tlinks t = tlinksDao.get(Tlinks.class, tlinksModel.getId());
		BeanUtils.copyProperties(tlinksModel, t);
		tlinksDao.update(t);
		return tlinksModel;
	}

	@Override
	public TlinksModel getModelById(String id) {
		Tlinks tlinks = tlinksDao.get(Tlinks.class, id);
		TlinksModel tlinksModel = new TlinksModel();
		if (null != tlinks) {
			BeanUtils.copyProperties(tlinks, tlinksModel);
		}
		return tlinksModel;
	}
	
	
	@Override
	public List<TlinksModel> tlinksList() {
		// TODO Auto-generated method stub
		String hql = "from Tlinks t";
		List<Tlinks> tlinksList = tlinksDao.find(hql);
		List<TlinksModel> tlinksModel = new LinkedList<TlinksModel>();
		changeModel(tlinksList, tlinksModel);
		return tlinksModel;
	}
	
	
	
}
