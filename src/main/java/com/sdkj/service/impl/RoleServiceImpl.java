package com.sdkj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.dao.BaseDaoI;
import com.sdkj.model.Tresource;
import com.sdkj.model.Trole;
import com.sdkj.model.TroleTresource;
import com.sdkj.pmodel.Role;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.RoleService;
import com.sdkj.util.ZJ_GeneratorUtils;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private BaseDaoI<Trole> roleDao;
	@Autowired
	private BaseDaoI<TroleTresource> roleResourceDao;
	@Autowired
	private BaseDaoI<Tresource> resourceDao;

	@Override
	public EasyuiDatagrid datagrid(Role role) {
		EasyuiDatagrid dg = new EasyuiDatagrid();
		String hql = "from Trole t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(role, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(role, hql);
		List<Trole> l = roleDao.find(hql, params, role.getPage(), role.getRows());
		List<Role> nl = new ArrayList<Role>();
		changeModel(l, nl);
		dg.setTotal(roleDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Trole> l, List<Role> nl) {
		if (l != null && l.size() > 0) {
			for (Trole t : l) {
				Role u = new Role();
				BeanUtils.copyProperties(t, u);

				Set<TroleTresource> s = t.getTroleTresources();
				if (s != null && !s.isEmpty()) {
					String resourceIds = "";
					String resourceNames = "";
					for (TroleTresource rr : s) {
						Tresource r = rr.getTresource();
						if (resourceIds.length() > 0) {
							resourceIds += ",";
							resourceNames += ",";
						}
						resourceIds += r.getId();
						resourceNames += r.getText();
					}
					u.setResourceIds(resourceIds);
					u.setResourceNames(resourceNames);
				}

				nl.add(u);
			}
		}
	}

	private String addOrder(Role role, String hql) {
		if (role.getSort() != null) {
			hql += " order by " + role.getSort() + " " + role.getOrder();
		}
		return hql;
	}

	private String addWhere(Role role, String hql, Map<String, Object> params) {
		return hql;
	}

	@Override
	public Role save(Role role) {
		Trole t = new Trole();
		BeanUtils.copyProperties(role, t);
		t.setId(ZJ_GeneratorUtils.idGenerator());
		roleDao.save(t);
		if (role.getResourceIds() != null) {
			String resourceNames = "";
			for (String id : role.getResourceIds().split(",")) {
				Tresource r = resourceDao.get(Tresource.class, id);
				if (r != null) {
					TroleTresource rr = new TroleTresource();
					rr.setId(ZJ_GeneratorUtils.idGenerator());
					rr.setTresource(r);
					rr.setTrole(t);
					roleResourceDao.save(rr);

					if (resourceNames.length() > 0) {
						resourceNames += ",";
					}
					resourceNames += r.getText();
				}
			}
			role.setResourceNames(resourceNames);
		}
		role.setId(t.getId());
		return role;
	}

	@Override
	public void remove(String ids) {
		for (String id : ids.split(",")) {
			Trole t = roleDao.get(Trole.class, id);
			if (t != null) {
				roleDao.delete(t);
			}
		}
	}

	@Override
	public Role edit(Role role) {
		Trole t = roleDao.get(Trole.class, role.getId());
		if (t != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("trole", t);
			roleResourceDao.executeHql("delete TroleTresource t where t.trole=:trole", params);

			BeanUtils.copyProperties(role, t);
			if (role.getResourceIds() != null) {
				String resourceNames = "";
				for (String id : role.getResourceIds().split(",")) {
					Tresource r = resourceDao.get(Tresource.class, id);
					if (r != null) {
						TroleTresource rr = new TroleTresource();
						rr.setId(ZJ_GeneratorUtils.idGenerator());
						rr.setTresource(r);
						rr.setTrole(t);
						roleResourceDao.save(rr);

						if (resourceNames.length() > 0) {
							resourceNames += ",";
						}
						resourceNames += r.getText();
					}
				}
				role.setResourceNames(resourceNames);
			}
		}
		return role;
	}
}
