package com.sdkj.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.dao.BaseDaoI;
import com.sdkj.model.Tlog;
import com.sdkj.pmodel.LogModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.LogService;
import com.sdkj.util.ZJ_GeneratorUtils;

@Service("logService")
public class LogServiceImpl implements LogService {
	@Autowired
	private BaseDaoI<Tlog> logDao;

	private String getTotalHql(PageModel page, String hql) {
		hql += " where 1=1 ";
		if (page.getName() != null && !page.getName().trim().equals("")) {
			hql += " and t.event like '%%" + page.getName().trim() + "%%'";
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

	private void changeModel(List<Tlog> l, List<LogModel> nl) {
		if (l != null && l.size() > 0) {
			for (Tlog t : l) {
				LogModel u = new LogModel();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	@Override
	public EasyuiDatagrid datagrid(PageModel page) {
		String hql = "from Tlog t";
		String hql2 = "select count(*) from Tlog t";
		String listHql = getListHql(page, hql);
		String totalHql = getTotalHql(page, hql2);
		List<Tlog> tcolumuList = logDao.find(listHql, null, page.getPage(), page.getRows());
		List<LogModel> logModels = new LinkedList<LogModel>();
		changeModel(tcolumuList, logModels);
		Long total = logDao.count(totalHql);
		EasyuiDatagrid dataGrid = new EasyuiDatagrid();
		dataGrid.setRows(logModels);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@Override
	public LogModel save(LogModel logModel) {
		logModel.setId(ZJ_GeneratorUtils.idGenerator());
		Tlog t = new Tlog();
		BeanUtils.copyProperties(logModel, t);
		t.setCreatetime(new Date());
		logDao.save(t);
		return logModel;
	}

	@Override
	public void remove(String ids) {
		for (String id : ids.split(",")) {
			Tlog t = logDao.get(Tlog.class, id);
			if (t != null) {
				logDao.delete(t);
			}
		}
	}

	@Override
	public LogModel edit(LogModel logModel) {
		Tlog t = logDao.get(Tlog.class, logModel.getId());
		logModel.setCreatetime(t.getCreatetime());
		BeanUtils.copyProperties(logModel, t);
		logDao.update(t);
		return logModel;
	}

	@Override
	public LogModel getModelById(String id) {
		Tlog tlog = logDao.get(Tlog.class, id);
		LogModel logModel = new LogModel();
		if (null != tlog) {
			BeanUtils.copyProperties(tlog, logModel);
		}
		return logModel;
	}

}
