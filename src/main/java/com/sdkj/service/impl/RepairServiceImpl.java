package com.sdkj.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.dao.BaseDaoI;
import com.sdkj.model.Tresource;
import com.sdkj.model.Trole;
import com.sdkj.model.TroleTresource;
import com.sdkj.model.Tuser;
import com.sdkj.model.TuserTrole;
import com.sdkj.service.RepairService;
import com.sdkj.util.ZJ_EncryptUtils;
import com.sdkj.util.ZJ_GeneratorUtils;

@Service("repairService")
public class RepairServiceImpl implements RepairService {
	@Autowired
	private BaseDaoI<Tuser> userDao;
	@Autowired
	private BaseDaoI<Tresource> resourceDao;
	@Autowired
	private BaseDaoI<Trole> roleDao;
	@Autowired
	private BaseDaoI<TroleTresource> roleResourceDao;
	@Autowired
	private BaseDaoI<TuserTrole> adminRoleDao;

	@Override
	synchronized public void delAndRepair() {
		roleResourceDao.executeHql("delete TroleTresource");
		adminRoleDao.executeHql("delete TuserTrole");
		resourceDao.executeHql("update Tresource t set t.tresource = null");
		resourceDao.executeHql("delete Tresource");
		roleDao.executeHql("delete Trole");
		userDao.executeHql("delete Tuser");
		repair();
	}

	@Override
	synchronized public void repair() {

		repairResource();

		repairRole();

		repairAdmin();

		repairRoleResource();

		repairAdminRole();
	}

	private void repairAdminRole() {
		adminRoleDao.executeHql("delete TuserTrole t where t.tuser.id in ( '0' )");

		TuserTrole adminrole = new TuserTrole();
		adminrole.setId(ZJ_GeneratorUtils.idGenerator());
		adminrole.setTrole(roleDao.get(Trole.class, "0"));
		adminrole.setTuser(userDao.get(Tuser.class, "0"));
		adminRoleDao.save(adminrole);
	}

	private void repairRoleResource() {
		roleResourceDao.executeHql("delete TroleTresource t where t.trole.id = '0'");
		List<Tresource> l = resourceDao.find("from Tresource");
		Trole r = roleDao.get(Trole.class, "0");
		for (Tresource t : l) {
			TroleTresource rr = new TroleTresource();
			rr.setId(ZJ_GeneratorUtils.idGenerator());
			rr.setTrole(r);
			rr.setTresource(t);
			roleResourceDao.save(rr);
		}
	}

	private void repairRole() {
		Trole admin = new Trole();
		admin.setId("0");
		admin.setText("超管角色");
		roleDao.saveOrUpdate(admin);
	}

	private void repairResource() {
		Tresource source = new Tresource();
		source.setId("0");
		source.setText("首页");
		source.setIconCls("icon-home");
		source.setSeq(BigDecimal.valueOf(1));
		source.setType(1);
		resourceDao.saveOrUpdate(source);

		Tresource xtgl = new Tresource();
		xtgl.setTresource(source);
		xtgl.setId("xtgl");
		xtgl.setText("系统管理");
		xtgl.setSeq(BigDecimal.valueOf(1));
		xtgl.setIconCls("icon-advanced");
		xtgl.setType(1);
		resourceDao.saveOrUpdate(xtgl);

		Tresource yhgl = new Tresource();
		yhgl.setTresource(xtgl);
		yhgl.setId("yhgl");
		yhgl.setText("用户管理");
		yhgl.setSeq(BigDecimal.valueOf(1));
		yhgl.setType(1);
		yhgl.setIconCls("icon-user");
		yhgl.setUrl("/ht/admin/yhgl.jsp");
		resourceDao.saveOrUpdate(yhgl);

		Tresource yhglAdd = new Tresource();
		yhglAdd.setTresource(yhgl);
		yhglAdd.setId("yhglAdd");
		yhglAdd.setText("用户添加");
		yhglAdd.setSeq(BigDecimal.valueOf(1));
		yhglAdd.setUrl("/do/user/add");
		yhglAdd.setType(2);
		resourceDao.saveOrUpdate(yhglAdd);

		Tresource yhglDel = new Tresource();
		yhglDel.setTresource(yhgl);
		yhglDel.setId("yhglDel");
		yhglDel.setText("用户删除");
		yhglDel.setSeq(BigDecimal.valueOf(2));
		yhglDel.setUrl("/do/user/remove");
		yhglDel.setType(2);
		resourceDao.saveOrUpdate(yhglDel);

		Tresource yhglEdit = new Tresource();
		yhglEdit.setTresource(yhgl);
		yhglEdit.setId("yhglEdit");
		yhglEdit.setText("用户修改");
		yhglEdit.setSeq(BigDecimal.valueOf(3));
		yhglEdit.setUrl("/do/user/edit");
		yhglEdit.setType(2);
		resourceDao.saveOrUpdate(yhglEdit);

		Tresource yhglDatagrid = new Tresource();
		yhglDatagrid.setTresource(yhgl);
		yhglDatagrid.setId("yhglDatagrid");
		yhglDatagrid.setText("用户列表");
		yhglDatagrid.setSeq(BigDecimal.valueOf(4));
		yhglDatagrid.setUrl("/do/user/datagrid");
		yhglDatagrid.setType(2);
		resourceDao.saveOrUpdate(yhglDatagrid);

		Tresource yhglModifyPwd = new Tresource();
		yhglModifyPwd.setTresource(yhgl);
		yhglModifyPwd.setId("yhglModifyPwd");
		yhglModifyPwd.setText("用户修改密码");
		yhglModifyPwd.setSeq(BigDecimal.valueOf(5));
		yhglModifyPwd.setUrl("/do/user/modifyPwd");
		yhglModifyPwd.setType(2);
		resourceDao.saveOrUpdate(yhglModifyPwd);

		Tresource yhglModifyRole = new Tresource();
		yhglModifyRole.setTresource(yhgl);
		yhglModifyRole.setId("yhglModifyRole");
		yhglModifyRole.setText("用户批量修改角色");
		yhglModifyRole.setSeq(BigDecimal.valueOf(6));
		yhglModifyRole.setUrl("/do/user/modifyRole");
		yhglModifyRole.setType(2);
		resourceDao.saveOrUpdate(yhglModifyRole);

		Tresource jsgl = new Tresource();
		jsgl.setTresource(xtgl);
		jsgl.setId("jsgl");
		jsgl.setText("角色管理");
		jsgl.setIconCls("icon-customers");
		jsgl.setSeq(BigDecimal.valueOf(2));
		jsgl.setUrl("/ht/admin/jsgl.jsp");
		jsgl.setType(1);
		resourceDao.saveOrUpdate(jsgl);

		Tresource jsglAdd = new Tresource();
		jsglAdd.setTresource(jsgl);
		jsglAdd.setId("jsglAdd");
		jsglAdd.setText("角色添加");
		jsglAdd.setSeq(BigDecimal.valueOf(1));
		jsglAdd.setUrl("/do/role/add");
		jsglAdd.setType(2);
		resourceDao.saveOrUpdate(jsglAdd);

		Tresource jsglDel = new Tresource();
		jsglDel.setTresource(jsgl);
		jsglDel.setId("jsglDel");
		jsglDel.setText("角色删除");
		jsglDel.setSeq(BigDecimal.valueOf(2));
		jsglDel.setUrl("/do/role/remove");
		jsglDel.setType(2);
		resourceDao.saveOrUpdate(jsglDel);

		Tresource jsglEdit = new Tresource();
		jsglEdit.setTresource(jsgl);
		jsglEdit.setId("jsglEdit");
		jsglEdit.setText("角色修改");
		jsglEdit.setSeq(BigDecimal.valueOf(3));
		jsglEdit.setUrl("/do/role/edit");
		jsglEdit.setType(2);
		resourceDao.saveOrUpdate(jsglEdit);

		Tresource jsglDatagrid = new Tresource();
		jsglDatagrid.setTresource(jsgl);
		jsglDatagrid.setId("jsglDatagrid");
		jsglDatagrid.setText("角色列表");
		jsglDatagrid.setSeq(BigDecimal.valueOf(4));
		jsglDatagrid.setUrl("/do/role/datagrid");
		jsglDatagrid.setType(2);
		resourceDao.saveOrUpdate(jsglDatagrid);

		Tresource zygl = new Tresource();
		zygl.setTresource(xtgl);
		zygl.setId("zygl");
		zygl.setText("资源管理");
		zygl.setSeq(BigDecimal.valueOf(3));
		zygl.setUrl("/ht/admin/zygl.jsp");
		zygl.setType(1);
		zygl.setIconCls("icon-suppliers");
		resourceDao.saveOrUpdate(zygl);

		Tresource zyglAdd = new Tresource();
		zyglAdd.setTresource(zygl);
		zyglAdd.setId("zyglAdd");
		zyglAdd.setText("资源添加");
		zyglAdd.setSeq(BigDecimal.valueOf(1));
		zyglAdd.setUrl("/do/resource/add");
		zyglAdd.setType(2);
		resourceDao.saveOrUpdate(zyglAdd);

		Tresource zyglDel = new Tresource();
		zyglDel.setTresource(zygl);
		zyglDel.setId("zyglDel");
		zyglDel.setText("资源删除");
		zyglDel.setSeq(BigDecimal.valueOf(2));
		zyglDel.setUrl("/do/resource/remove");
		zyglDel.setType(2);
		resourceDao.saveOrUpdate(zyglDel);

		Tresource zyglEdit = new Tresource();
		zyglEdit.setTresource(zygl);
		zyglEdit.setId("zyglEdit");
		zyglEdit.setText("资源修改");
		zyglEdit.setSeq(BigDecimal.valueOf(3));
		zyglEdit.setUrl("/do/resource/edit");
		zyglEdit.setType(2);
		resourceDao.saveOrUpdate(zyglEdit);

		Tresource zyglTreegrid = new Tresource();
		zyglTreegrid.setTresource(zygl);
		zyglTreegrid.setId("zyglTreegrid");
		zyglTreegrid.setText("资源列表");
		zyglTreegrid.setSeq(BigDecimal.valueOf(4));
		zyglTreegrid.setUrl("/do/resource/treegrid");
		zyglTreegrid.setType(2);
		resourceDao.saveOrUpdate(zyglTreegrid);

		Tresource icon = new Tresource();
		icon.setTresource(xtgl);
		icon.setId("icon");
		icon.setText("图标管理");
		icon.setSeq(BigDecimal.valueOf(4));
		icon.setUrl("/ht/icon/icon.jsp");
		icon.setType(1);
		icon.setIconCls("icon-suppliers");
		resourceDao.saveOrUpdate(icon);

		Tresource icondatagrid = new Tresource();
		icondatagrid.setTresource(icon);
		icondatagrid.setId("icondatagrid");
		icondatagrid.setText("图标列表");
		icondatagrid.setSeq(BigDecimal.valueOf(4));
		icondatagrid.setUrl("/do/icon/datagrid");
		icondatagrid.setType(2);
		resourceDao.saveOrUpdate(icondatagrid);

		Tresource iconadd = new Tresource();
		iconadd.setTresource(icon);
		iconadd.setId("iconadd");
		iconadd.setText("图标添加");
		iconadd.setSeq(BigDecimal.valueOf(4));
		iconadd.setUrl("/do/icon/add");
		iconadd.setType(2);
		resourceDao.saveOrUpdate(iconadd);

		Tresource iconedit = new Tresource();
		iconedit.setTresource(icon);
		iconedit.setId("iconedit");
		iconedit.setText("图标修改");
		iconedit.setSeq(BigDecimal.valueOf(4));
		iconedit.setUrl("/do/icon/edit");
		iconedit.setType(2);
		resourceDao.saveOrUpdate(iconedit);

		Tresource iconremove = new Tresource();
		iconremove.setTresource(icon);
		iconremove.setId("iconremove");
		iconremove.setText("图标删除");
		iconremove.setSeq(BigDecimal.valueOf(4));
		iconremove.setUrl("/do/icon/remove");
		iconremove.setType(2);
		resourceDao.saveOrUpdate(iconremove);

		Tresource log = new Tresource();
		log.setTresource(xtgl);
		log.setId("log");
		log.setText("日志管理");
		log.setSeq(BigDecimal.valueOf(5));
		log.setUrl("/ht/log/log.jsp");
		log.setType(1);
		log.setIconCls("icon-suppliers");
		resourceDao.saveOrUpdate(log);

		Tresource logdatagrid = new Tresource();
		logdatagrid.setTresource(log);
		logdatagrid.setId("logdatagrid");
		logdatagrid.setText("日志列表");
		logdatagrid.setSeq(BigDecimal.valueOf(4));
		logdatagrid.setUrl("/do/log/datagrid");
		logdatagrid.setType(2);
		resourceDao.saveOrUpdate(logdatagrid);
	}

	private void repairAdmin() {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "admin");
		List<Tuser> tl = userDao.find("from Tuser t where t.name = :name and t.id != '0'", m);
		if (tl != null && tl.size() > 0) {
			for (Tuser u : tl) {
				u.setName(u.getName() + ZJ_GeneratorUtils.idGenerator());
			}
		}

		Tuser admin = new Tuser();
		admin.setId("0");
		admin.setName("admin");
		admin.setPwd(ZJ_EncryptUtils.md5("admin"));
		admin.setCreatetime(new Date());
		userDao.saveOrUpdate(admin);
	}

}
