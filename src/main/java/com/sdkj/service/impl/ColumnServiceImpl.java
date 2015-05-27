package com.sdkj.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.dao.BaseDaoI;
import com.sdkj.model.Tarticle;
import com.sdkj.model.Tcolumn;
import com.sdkj.pmodel.ColumnModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.ComboTree;
import com.sdkj.pmodel.ui.DataGird;
import com.sdkj.service.ColumnService;
import com.sdkj.util.ZJ_ClazzUtils;
import com.sdkj.util.ZJ_GeneratorUtils;



@Service("columnService")
public class ColumnServiceImpl implements ColumnService {
	@Autowired
	private BaseDaoI<Tarticle> articleDao;
	@Autowired
	private BaseDaoI<Tcolumn> columnDao;

	private String getTotalHql(PageModel page, String hql) {
		hql += " where 1=1 ";
		if (page.getName() != null && !page.getName().trim().equals("")) {
			hql += " and t.cName like '%%" + page.getName().trim() + "%%'";
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

	private void changeModel(List<Tcolumn> l, List<ColumnModel> nl) {
		if (l != null && l.size() > 0) {
			for (Tcolumn t : l) {
				ColumnModel u = new ColumnModel();
				BeanUtils.copyProperties(t, u);
				Tcolumn t2 = t.getTcolumn();
				if (null != t2) {
					u.setPname(t2.getCname());
					u.setPid(t2.getId());
				}
				nl.add(u);
			}
		}
	}

	@Override
	public DataGird datagrid(PageModel page) {
		String hql = "from Tcolumn t";
		String hql2 = "select count(*) from Tcolumn t";
		String listHql = getListHql(page, hql);
		String totalHql = getTotalHql(page, hql2);
		List<Tcolumn> tcolumuList = columnDao.find(listHql, null,
				page.getPage(), page.getRows());
		List<ColumnModel> columnModels = new LinkedList<ColumnModel>();
		changeModel(tcolumuList, columnModels);
		Long total = columnDao.count(totalHql);
		DataGird dataGrid = new DataGird();
		dataGrid.setRows(columnModels);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@Override
	public ColumnModel save(ColumnModel columnModel) {
		columnModel.setId(ZJ_GeneratorUtils.idGenerator());
		Tcolumn t = new Tcolumn();
		BeanUtils.copyProperties(columnModel, t);
		if (ZJ_ClazzUtils.notNullOrEmpty(columnModel.getPid())) {
			Tcolumn tcolumn = new Tcolumn();
			tcolumn.setId(columnModel.getPid());
			t.setTcolumn(tcolumn);
		}
		columnDao.save(t);
		return columnModel;
	}

	@Override
	public List<ComboTree> allTreeNode() {
		List<ComboTree> nl = new ArrayList<ComboTree>();
		String hql = "from Tcolumn";
		List<Tcolumn> l = columnDao.find(hql);
		if (l != null && l.size() > 0) {
			for (Tcolumn t : l) {
				ComboTree m = new ComboTree();
				BeanUtils.copyProperties(t, m);
				m.setId(t.getId());
				m.setText(t.getCname());
				Tcolumn tm = t.getTcolumn();// 获得当前节点的父节点对象
				if (tm != null) {
					m.setPid(tm.getId());
					m.setPname(tm.getCname());
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public void remove(String ids) {
		for (String id : ids.split(",")) {
			Tcolumn t = columnDao.get(Tcolumn.class, id);
			if (t != null) {
				columnDao.delete(t);
			}
		}
	}

	@Override
	public ColumnModel edit(ColumnModel columnModel) {
		if (!columnModel.getId().equals(columnModel.getPid())) {
			Tcolumn t = columnDao.get(Tcolumn.class, columnModel.getId());
			Tcolumn p = columnDao.get(Tcolumn.class, columnModel.getPid());
			t.setTcolumn(p);
			BeanUtils.copyProperties(columnModel, t);
			columnDao.update(t);
			columnModel.setPname(p.getCname());
		}
		return columnModel;
	}

	@Override
	public ColumnModel getModelById(String id) {
		Tcolumn tcolumn = columnDao.get(Tcolumn.class, id);
		ColumnModel columnModel = new ColumnModel();
		if (null != tcolumn) {
			BeanUtils.copyProperties(tcolumn, columnModel);
		}
		return columnModel;
	}

	@Override
	public List<ColumnModel> columnList() {
		String hql = "from Tcolumn t";
		List<Tcolumn> tcolumuList = columnDao.find(hql);
		List<ColumnModel> columnModels = new LinkedList<ColumnModel>();
		changeModel(tcolumuList, columnModels);
		return columnModels;
	}

	@Override
	public List<ColumnModel> columnListFirst() {
		String hql = "from Tcolumn t where t.tcolumn.id ='0'";
		List<Tcolumn> tcolumuList = columnDao.find(hql);
		List<ColumnModel> columnModels = new LinkedList<ColumnModel>();
		changeModel(tcolumuList, columnModels);
		Collections.sort(columnModels, new Comparator<ColumnModel>() {
			@Override
			public int compare(ColumnModel o2, ColumnModel o1) {
				return o2.getSort().compareTo(o1.getSort());
			}
		});
		return columnModels;
	}

	@Override
	public List<ColumnModel> columnListSecond() {
		String hql = "from Tcolumn t where t.tcolumn.id!='0' and t.tcolumn.id!=null";
		List<Tcolumn> tcolumuList = columnDao.find(hql);
		List<ColumnModel> columnModels = new LinkedList<ColumnModel>();
		changeModel(tcolumuList, columnModels);
		return columnModels;
	}
	
	
	
	@Override
	public List<ColumnModel> sonColumnListByCid(String pid) {
		String hql = String.format("from Tcolumn t where t.tcolumn.id='%s'",
				pid);
		List<Tcolumn> tcolumuList = columnDao.find(hql);
		List<ColumnModel> columnModels = new LinkedList<ColumnModel>();
		changeModel(tcolumuList, columnModels);
		// list排序
		Collections.sort(columnModels, new Comparator<ColumnModel>() {
			@Override
			public int compare(ColumnModel o2, ColumnModel o1) {
				return o2.getSort().compareTo(o1.getSort());
			}
		});
		return columnModels;
	}

	@Override
	public List<ColumnModel> columnListByCid(String cid) {
		Tcolumn tcolumn = columnDao.get(Tcolumn.class, cid);
		String pid = tcolumn.getTcolumn().getId();
		List<Tcolumn> tcolumuList = new LinkedList<Tcolumn>();
		if (pid.equals("0")) {
			Set<Tcolumn> tcolumunSet = tcolumn.getTcolumns();
			for (Tcolumn tcolumn2 : tcolumunSet) {
				tcolumuList.add(tcolumn2);
			}
		} else {
			String hql = "from Tcolumn t where t.tcolumn.id='" + pid + "'";
			tcolumuList = columnDao.find(hql);
		}
		List<ColumnModel> columnModels = new LinkedList<ColumnModel>();
		changeModel(tcolumuList, columnModels);
		// list排序
		Collections.sort(columnModels, new Comparator<ColumnModel>() {
			@Override
			public int compare(ColumnModel o2, ColumnModel o1) {
				return o2.getSort().compareTo(o1.getSort());
			}
		});
		return columnModels;
	}
	
	
	

	

	@Override
	public ColumnModel topColumnByCid(String cid) {
		Tcolumn tcolumn = columnDao.get(Tcolumn.class, cid);
		if (!tcolumn.getTcolumn().getId().equals("0")) {
			tcolumn = tcolumn.getTcolumn();
		}
		ColumnModel columnModel = new ColumnModel();
		if (null != tcolumn) {
			BeanUtils.copyProperties(tcolumn, columnModel);
		}
		return columnModel;
	}

	@Override
	public ColumnModel topColumnByArticleId(String articleId) {
		Tcolumn tcolumn = new Tcolumn();
		ColumnModel columnModel = new ColumnModel();
		Tarticle tarticle = articleDao.get(Tarticle.class, articleId); //获得文章
		if (null != tarticle) {
			String cid = tarticle.getTcolumn().getId();	//获得栏目id
			tcolumn = columnDao.get(Tcolumn.class, cid);	//获得 栏目信息
			if (!columnDao.get(Tcolumn.class, tcolumn.getTcolumn().getId())
					.getId().equals("0")) {
				tcolumn = columnDao.get(Tcolumn.class, tcolumn.getTcolumn()
						.getId());
			}
		}
		if (null != tcolumn) {
			BeanUtils.copyProperties(tcolumn, columnModel);
		}
		return columnModel;
	}

	@Override
	public ColumnModel columnByCid(String cid) {
		Tcolumn tcolumn = columnDao.get(Tcolumn.class, cid);
		ColumnModel columnModel = new ColumnModel();
		if (null != tcolumn) {
			BeanUtils.copyProperties(tcolumn, columnModel);
		}
		return columnModel;
	}

	@Override
	public ColumnModel columnByArticleId(String articleId) {
		Tarticle tarticle = articleDao.get(Tarticle.class, articleId);
		ColumnModel columnModel = new ColumnModel();
		if (null != tarticle) {
			
			String cid = tarticle.getTcolumn().getId();	
			if(!tarticle.getTcolumn().getId().equals("0")){
				Tcolumn column = columnDao.get(Tcolumn.class, cid);
				String pid = column.getTcolumn().getId();
				String hql ="from Tcolumn t where t.tcolumn.id = '"+pid+"'";
				List<Tcolumn> list = this.columnDao.find(hql);
				if(null != list){
					for (Tcolumn t : list) {
						BeanUtils.copyProperties(t, columnModel);
						columnModel.setPid(pid);
						columnModel.setPname(column.getTcolumn().getCname());
						
					}
					
				}
				
			}
			
		}
		return columnModel;
	}


	@Override
	public List<ColumnModel> columnListByArticleId(String articleId) {
		List<ColumnModel> columnModels = new LinkedList<ColumnModel>();
		Tarticle tarticle = articleDao.get(Tarticle.class, articleId);
		List<Tcolumn> tcolumuList = null;
		if (null != tarticle) {
			Tcolumn tcolumn = tarticle.getTcolumn();
			String cid = tarticle.getTcolumn().getId();
			if (!tcolumn.getTcolumn().getId().equals("0")) {
				tcolumn = columnDao.get(Tcolumn.class, cid);
				String pid = tcolumn.getTcolumn().getId();
				String hql = "from Tcolumn t where t.tcolumn.id='" + pid + "'";
				tcolumuList = columnDao.find(hql);
				changeModel(tcolumuList, columnModels);
			}

		}
		return columnModels;
	}
}
