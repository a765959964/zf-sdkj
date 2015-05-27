package com.sdkj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.dao.BaseDaoI;
import com.sdkj.model.Trole;
import com.sdkj.model.TroleTresource;
import com.sdkj.model.Tuser;
import com.sdkj.model.TuserTrole;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.UserModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.UserService;
import com.sdkj.util.ZJ_EncryptUtils;
import com.sdkj.util.ZJ_GeneratorUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseDaoI<Trole> roleDao;
	@Autowired
	private BaseDaoI<TuserTrole> adminRoleDao;
	@Autowired
	private BaseDaoI<Tuser> userDao;

	@Override
	public UserModel save(UserModel user) {
		Tuser t = new Tuser();
		BeanUtils.copyProperties(user, t, new String[] { "pwd" });
		t.setId(ZJ_GeneratorUtils.idGenerator().toString());
		t.setPwd(ZJ_EncryptUtils.md5(user.getPwd()));
		t.setCreatetime(new Date());
		userDao.save(t);
		if (user.getRoleIds() != null) {
			String roleIds = "";
			String roleNames = "";
			for (String id : user.getRoleIds().split(",")) {
				Trole r = roleDao.get(Trole.class, id);
				if (r != null) {
					TuserTrole ur = new TuserTrole();
					ur.setId(ZJ_GeneratorUtils.idGenerator().toString());
					ur.setTrole(r);
					ur.setTuser(t);
					adminRoleDao.save(ur);

					if (roleIds.length() > 0) {
						roleIds += ",";
						roleNames += ",";
					}
					roleIds += r.getId();
					roleNames += r.getText();
				}
			}
			user.setRoleNames(roleNames);
			user.setRoleIds(roleIds);
		}
		BeanUtils.copyProperties(t, user);
		return user;
	}

	@Override
	public UserModel find(UserModel user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", user.getName());
		params.put("pwd", ZJ_EncryptUtils.md5(user.getPwd()));
		Tuser t = userDao.get("from Tuser t where t.name=:name and t.pwd=:pwd",
				params);
		if (t != null) {
			BeanUtils.copyProperties(t, user);
			String roleIds = "";
			String roleNames = "";
			String resourceIds = "";
			String resourceNames = "";
			List<String> resourceUrls = new ArrayList<String>();
			Set<TuserTrole> ur = t.getTuserTroles();
			if (ur != null && !ur.isEmpty()) {
				for (TuserTrole tur : ur) {
					if (roleIds.length() > 0) {
						roleIds += ",";
						roleNames += ",";
					}
					roleIds += tur.getTrole().getId();
					roleNames += tur.getTrole().getText();

					Set<TroleTresource> rr = tur.getTrole()
							.getTroleTresources();
					if (rr != null && !rr.isEmpty()) {
						for (TroleTresource trr : rr) {
							if (resourceIds.length() > 0) {
								resourceIds += ",";
								resourceNames += ",";
							}
							resourceIds += trr.getTresource().getId();
							resourceNames += trr.getTresource().getText();
							resourceUrls.add(trr.getTresource().getUrl());
						}
					}
				}
			}
			user.setRoleIds(roleIds);
			user.setRoleNames(roleNames);
			user.setResourceIds(resourceIds);
			user.setResourceNames(resourceNames);
			user.setResourceUrls(resourceUrls);

			return user;
		}
		return null;
	}

	@Override
	public EasyuiDatagrid datagrid(PageModel page) {
		EasyuiDatagrid dg = new EasyuiDatagrid();
		String hql = "from Tuser t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(page, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(page, hql);
		List<Tuser> l = userDao.find(hql, params, page.getPage(),
				page.getRows());
		List<UserModel> nl = new ArrayList<UserModel>();
		changeModel(l, nl);
		dg.setTotal(userDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Tuser> l, List<UserModel> nl) {
		if (l != null && l.size() > 0) {
			for (Tuser t : l) {
				UserModel u = new UserModel();
				BeanUtils.copyProperties(t, u);
				Set<TuserTrole> s = t.getTuserTroles();
				if (s != null && !s.isEmpty()) {
					String roleIds = "";
					String roleNames = "";

					for (TuserTrole tt : s) {
						if (roleIds.length() > 0) {
							roleIds += ",";
							roleNames += ",";
						}
						roleIds += tt.getTrole().getId();
						roleNames += tt.getTrole().getText();
					}

					u.setRoleIds(roleIds);
					u.setRoleNames(roleNames);
				}

				nl.add(u);
			}
		}
	}

	private String addOrder(PageModel page, String hql) {
		if (page.getSort() != null) {
			hql += " order by " + page.getSort() + " " + page.getOrder();
		}
		return hql;
	}

	private String addWhere(PageModel page, String hql,
			Map<String, Object> params) {
		hql += " where 1=1 ";

		if (page.getQ() != null && !page.getQ().trim().equals("")) {
			hql += " and t.name like :name ";
			params.put("name", "%%" + page.getQ().trim() + "%%");
		}
		return hql;
	}

	@Override
	public List<UserModel> combobox(UserModel user) {
		String hql = "from Tuser t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql += " order by name";
		List<Tuser> l = userDao.find(hql, params, 1, 10);
		List<UserModel> nl = new ArrayList<UserModel>();
		changeModel(l, nl);
		return nl;
	}

	@Override
	public void remove(String ids) {
		for (String id : ids.split(",")) {
			Tuser t = userDao.get(Tuser.class, id);
			if (t != null) {
				userDao.delete(t);
			}
		}
	}

	@Override
	public UserModel edit(UserModel user) {
		Tuser t = userDao.get(Tuser.class, user.getId());
		if (t != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tuser", t);
			adminRoleDao.executeHql("delete TuserTrole t where t.tuser=:tuser",
					params);
			BeanUtils.copyProperties(user, t, new String[] { "pwd", "id" });
			BeanUtils.copyProperties(t, user);
			if (user.getRoleIds() != null) {
				String roleIds = "";
				String roleNames = "";
				for (String id : user.getRoleIds().split(",")) {
					Trole r = roleDao.get(Trole.class, id);
					if (r != null) {
						TuserTrole ur = new TuserTrole();
						ur.setId(ZJ_GeneratorUtils.idGenerator().toString());
						ur.setTrole(r);
						ur.setTuser(t);
						adminRoleDao.save(ur);

						if (roleIds.length() > 0) {
							roleIds += ",";
							roleNames += ",";
						}
						roleIds += r.getId();
						roleNames += r.getText();
					}
				}
				user.setRoleNames(roleNames);
				user.setRoleIds(roleIds);
			}
			return user;
		}
		return null;
	}

	@Override
	public void modifyRole(UserModel user) {
		for (String uid : user.getIds().split(",")) {
			Tuser u = userDao.get(Tuser.class, uid);
			if (u != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("Tuser", u);
				adminRoleDao.executeHql(
						"delete TuserTrole t where t.tuser=:Tuser", params);
				if (user.getRoleIds() != null) {
					for (String id : user.getRoleIds().split(",")) {
						Trole r = roleDao.get(Trole.class, id);
						if (r != null) {
							TuserTrole ur = new TuserTrole();
							ur.setId(ZJ_GeneratorUtils.idGenerator().toString());
							ur.setTrole(r);
							ur.setTuser(u);
							adminRoleDao.save(ur);
						}
					}
				}
			}
		}
	}

	@Override
	public void modifyPwd(UserModel user) {
		Tuser u = userDao.get(Tuser.class, user.getId());
		if (u != null) {
			u.setPwd(ZJ_EncryptUtils.md5(user.getPwd()));
		}
	}

	@Override
	public UserModel getModelById(String id) {
		Tuser u = userDao.get(Tuser.class, id);
		UserModel userModel = new UserModel();
		if (null != u) {
			BeanUtils.copyProperties(u, userModel);
		}
		return userModel;
	}

}
