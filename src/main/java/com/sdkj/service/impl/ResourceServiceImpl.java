package com.sdkj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.dao.BaseDaoI;
import com.sdkj.model.Tresource;
import com.sdkj.model.TroleTresource;
import com.sdkj.model.Tuser;
import com.sdkj.pmodel.ui.EasyuiMenu;
import com.sdkj.pmodel.ui.EasyuiTreegrid;
import com.sdkj.service.ResourceService;
import com.sdkj.util.ZJ_GeneratorUtils;
import com.sdkj.util.ZJ_StringUtils;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	private static final Logger logger = Logger.getLogger(ResourceServiceImpl.class);
	@Autowired
	private BaseDaoI<Tresource> resourceDao;
	@Autowired
	private BaseDaoI<TroleTresource> roleResourceDao;

	@Autowired
	private BaseDaoI<Tuser> userDao;

	@Override
	public List<EasyuiTreegrid> treegrid() {
		List<Tresource> l = resourceDao.find("from Tresource t order by t.seq");
		List<EasyuiTreegrid> nl = new ArrayList<EasyuiTreegrid>();
		if (l != null && l.size() > 0) {
			for (Tresource t : l) {
				EasyuiTreegrid r = new EasyuiTreegrid();
				BeanUtils.copyProperties(t, r);
				if (t.getTresource() != null) {
					r.setPid(t.getTresource().getId());
					r.setPname(t.getTresource().getText());
				}
				nl.add(r);
			}
		}
		return nl;
	}

	@Override
	public List<EasyuiTreegrid> allTreeNode() {
		List<EasyuiTreegrid> nl = new ArrayList<EasyuiTreegrid>();
		String hql = "from Tresource t order by t.seq";
		List<Tresource> l = resourceDao.find(hql);
		if (l != null && l.size() > 0) {
			for (Tresource t : l) {
				EasyuiTreegrid m = new EasyuiTreegrid();
				BeanUtils.copyProperties(t, m);
				Tresource tm = t.getTresource();// 获得当前节点的父节点对象
				if (tm != null) {
					m.setPid(tm.getId());
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public List<EasyuiTreegrid> allTreeNodeByResIds(String resIds) {
		List<EasyuiTreegrid> nl = new ArrayList<EasyuiTreegrid>();
		if (null == resIds || "".equals(resIds.trim())) {
			return nl;
		} else {
			resIds = ZJ_StringUtils.changeStr(resIds);
			String hql = String.format("from Tresource t where t.id in (%s) order by t.seq", resIds);
			logger.info(hql);
			List<Tresource> l = resourceDao.find(hql);
			if (l != null && l.size() > 0) {
				for (Tresource t : l) {
					EasyuiTreegrid m = new EasyuiTreegrid();
					BeanUtils.copyProperties(t, m);
					Tresource tm = t.getTresource();// 获得当前节点的父节点对象
					if (tm != null) {
						m.setPid(tm.getId());
					}
					nl.add(m);
				}
			}
			return nl;
		}

	}

	@Override
	public List<EasyuiMenu> allMenuNode() {
		List<EasyuiMenu> nl = new ArrayList<EasyuiMenu>();

		String hql = "from Tresource t where t.type='1' order by t.seq";
		List<Tresource> l = resourceDao.find(hql);
		if (l != null && l.size() > 0) {
			for (Tresource t : l) {
				EasyuiMenu m = new EasyuiMenu();
				Map<String, Object> attr = new HashMap<String, Object>();
				BeanUtils.copyProperties(t, m);
				attr.put("url", t.getUrl());
				m.setAttributes(attr);
				Tresource tm = t.getTresource();// 获得当前节点的父节点对象
				if (tm != null) {
					m.setPid(tm.getId());
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public List<EasyuiMenu> allMenuNodeByResIds(String resIds) {
		List<EasyuiMenu> nl = new ArrayList<EasyuiMenu>();
		if (null == resIds || "".equals(resIds.trim())) {
			return nl;
		} else {
			resIds = ZJ_StringUtils.changeStr(resIds);
			String hql = String.format("from Tresource t where t.id in (%s) and t.type='1' order by t.seq", resIds);
			logger.info("hql:" + hql);
			List<Tresource> l = resourceDao.find(hql);
			if (l != null && l.size() > 0) {
				for (Tresource t : l) {
					EasyuiMenu m = new EasyuiMenu();
					Map<String, Object> attr = new HashMap<String, Object>();
					BeanUtils.copyProperties(t, m);
					attr.put("url", t.getUrl());
					m.setAttributes(attr);
					Tresource tm = t.getTresource();// 获得当前节点的父节点对象
					if (tm != null) {
						m.setPid(tm.getId());
					}
					nl.add(m);
				}
			}
			return nl;
		}
	}

	@Override
	public EasyuiTreegrid add(EasyuiTreegrid resource) {
		Tresource t = new Tresource();
		BeanUtils.copyProperties(resource, t);
		t.setId(ZJ_GeneratorUtils.idGenerator());
		if (resource.getPid() != null) {
			Tresource p = resourceDao.get(Tresource.class, resource.getPid());
			if (p != null) {
				t.setTresource(p);
			}
		}
		resourceDao.save(t);
		BeanUtils.copyProperties(t, resource);
		return resource;
	}

	@Override
	public void remove(String id) {
		Tresource t = resourceDao.get(Tresource.class, id);
		this.del(t);
	}

	private void del(Tresource r) {
		Set<Tresource> s = r.getTresources();
		if (s != null && !s.isEmpty()) {
			for (Tresource t : s) {
				del(t);
			}
		}
		resourceDao.delete(r);
	}

	@Override
	public EasyuiTreegrid edit(EasyuiTreegrid resource) {
		Tresource t = resourceDao.get(Tresource.class, resource.getId());// 要修改的权限
		if (t != null) {
			BeanUtils.copyProperties(resource, t);
			t.setTresource(null);// 现将当前节点的父节点置空
			if (resource.getPid() != null && !resource.getPid().trim().equals("") && !resource.getPid().equals(resource.getId())) {
				// 如果pid不为空，并且pid不跟自己的id相同，说明要修改当前节点的父节点
				Tresource presource = resourceDao.get(Tresource.class, resource.getPid());// 要设置的上级权限
				if (presource != null) {
					if (isDown(t, presource)) {// 要将当前节点修改到当前节点的子节点中
						Set<Tresource> tresources = t.getTresources();// 当前要修改的权限的所有下级权限
						if (tresources != null && tresources.size() > 0) {
							for (Tresource tresource : tresources) {
								if (tresource != null) {
									tresource.setTresource(null);
								}
							}
						}
					}
					t.setTresource(presource);
				}
			}
		}
		return resource;
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点
	 * 
	 * @param t
	 * @param pt
	 * @return
	 */
	private boolean isDown(Tresource t, Tresource pt) {
		if (pt.getTresource() != null) {
			if (pt.getTresource().getId().equals(t.getId())) {
				return true;
			} else {
				return isDown(t, pt.getTresource());
			}
		}
		return false;
	}

}
