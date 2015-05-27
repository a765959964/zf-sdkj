package com.sdkj.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.sdkj.dao.BaseDaoI;
import com.sdkj.model.Tarticle;
import com.sdkj.model.Tcolumn;
import com.sdkj.pmodel.ArticleModel;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.ui.ComboTree;
import com.sdkj.pmodel.ui.DataGird;
import com.sdkj.service.ArticleService;
import com.sdkj.util.ZJ_ClazzUtils;
import com.sdkj.util.ZJ_GeneratorUtils;


@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private BaseDaoI<Tarticle> articleDao;
	@Autowired
	private BaseDaoI<Tcolumn> columnDao;

	private String getTotalHql(PageModel page, String hql) {
		hql += " where 1=1 ";
		if (page.getName() != null && !page.getName().trim().equals("")) {
			hql += " and t.title like '%%" + page.getName().trim() + "%%'";
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

	private String addSortHql(PageModel page, String hql) {
		if (page.getSort() != null) {
			hql += " order by " + page.getSort() + " " + page.getOrder();
		}
		return hql;
	}

	private void changeModel(List<Tarticle> l, List<ArticleModel> nl) {
		if (l != null && l.size() > 0) {
			for (Tarticle t : l) {
				ArticleModel u = new ArticleModel();
				BeanUtils.copyProperties(t, u);
				Tcolumn t2 = t.getTcolumn();
				if (null != t2) {
					u.setCid(t2.getId());
					u.setCname(t2.getCname());
				}
				nl.add(u);
			}
		}
	}

	@Override
	public DataGird datagrid(PageModel page) {
		String hql = "from Tarticle t";
		String hql2 = "select count(*) from Tarticle t";
		String listHql = getListHql(page, hql);
		String totalHql = getTotalHql(page, hql2);
		List<Tarticle> tcolumuList = articleDao.find(listHql, null,
				page.getPage(), page.getRows());
		List<ArticleModel> articleModels = new LinkedList<ArticleModel>();
		changeModel(tcolumuList, articleModels);
		Long total = articleDao.count(totalHql);
		DataGird dataGrid = new DataGird();
		dataGrid.setRows(articleModels);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@Override
	public ArticleModel save(ArticleModel articleModel) {
		articleModel.setArticleId(ZJ_GeneratorUtils.idGenerator());
		articleModel.setCreateTime(new Timestamp(System.currentTimeMillis()));
		Tarticle t = new Tarticle();
		BeanUtils.copyProperties(articleModel, t);
		if (ZJ_ClazzUtils.notNullOrEmpty(articleModel.getCid())) {
			Tcolumn tcolumn = new Tcolumn();
			tcolumn.setId(articleModel.getCid());
			t.setTcolumn(tcolumn);
			articleModel.setCname(tcolumn.getCname());
		}
		articleDao.save(t);
		return articleModel;
	}

	@Override
	public void remove(String ids) {
		for (String articleId : ids.split(",")) {
			Tarticle t = articleDao.get(Tarticle.class, articleId);
			if (t != null) {
				articleDao.delete(t);
			}
		}
	}

	@Override
	public ArticleModel edit(ArticleModel articleModel) {
		Tarticle t = articleDao
				.get(Tarticle.class, articleModel.getArticleId());
		articleModel.setCreateTime(t.getCreateTime());
		Tcolumn column = columnDao.get(Tcolumn.class, articleModel.getCid());
		column.setId(articleModel.getCid());
		t.setTcolumn(column);
		BeanUtils.copyProperties(articleModel, t);
		articleDao.update(t);
		articleModel.setCname(column.getCname());
		return articleModel;
	}

	@Override
	public ArticleModel getModelById(String articleId) {
		Tarticle tarticle = articleDao.get(Tarticle.class, articleId);
		ArticleModel articleModel = new ArticleModel();
		if (null != tarticle) {
			BeanUtils.copyProperties(tarticle, articleModel);
			articleModel.setCid(tarticle.getTcolumn().getId());
			articleModel.setCname(tarticle.getTcolumn().getCname());
		}
		return articleModel;
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
	public PageModel getArticleListByColumn(PageModel page, String cid) {
		String chql = "from Tcolumn t where t.tcolumn.id =:cid";	//根据内容ｉｄ获得栏目ｉｄ
		String hql =null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", cid);
		List<Tcolumn> columnList = columnDao.find(chql, params);
		String cids = "'" + cid + "'";
		if (columnList.size() > 0) {
			for (Tcolumn tcolumn : columnList) {
				cids += ",'" + tcolumn.getId() + "'";
			}
		}
		String shql = "from Tcolumn t where t.tcolumn.id in (" + cids + ")";
		List<Tcolumn> secondList = columnDao.find(shql);
		if(secondList.size()>0){
			for(Tcolumn tc : secondList){
				cids += ",'" + tc.getId() + "'";
			}	
		}
		// 根据文章创建日期排序 
		hql = "from Tarticle t where t.tcolumn.id in (" + cids + ") order by t.createTime desc";
		
		String totalHql = "select count(*) from Tarticle t where t.tcolumn.id in ("
				+ cids + ")";
		String listHql = addSortHql(page, hql);
		List<Tarticle> tcolumuList = articleDao.find(listHql, null,
				page.getPage(), page.getRows());
		List<ArticleModel> articleModels = new LinkedList<ArticleModel>();
		changeModel(tcolumuList, articleModels);
		Long total = articleDao.count(totalHql, null);
		
		int num = (int) (total > 0 ? (total - 1) / page.getRows() + 1 : 0);
		page.setPageNum(num);
		page.setTotal(total);
		page.setList(articleModels);
		return page;
	}

	@Override
	public ArticleModel getModelByCid(String cid) {
		
		String hql = "from Tarticle t where t.tcolumn.id = '"+cid+"'";
		List<Tarticle> article = articleDao.find(hql);	//全部文章信息
		
		Tcolumn column = columnDao.get(Tcolumn.class, cid);
		
		ArticleModel articleModel = new ArticleModel();
		
		for (Tarticle t : article) {
			BeanUtils.copyProperties(t, articleModel);
			articleModel.setCname(column.getCname());
		}
		
		return articleModel;
		
		
	}
}
