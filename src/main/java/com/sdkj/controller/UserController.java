package com.sdkj.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdkj.pmodel.Json;
import com.sdkj.pmodel.PageModel;
import com.sdkj.pmodel.SessionInfo;
import com.sdkj.pmodel.UserModel;
import com.sdkj.pmodel.ui.EasyuiDatagrid;
import com.sdkj.service.UserService;
import com.sdkj.util.IpUtil;
import com.sdkj.util.ZJ_Date2StrUtils;
import com.sdkj.util.ZJ_JacksonUtils;
import com.sdkj.util.ZJ_ResourceUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = Logger
			.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() {
				return ZJ_Date2StrUtils.date2str((Date) getValue());
			}

			@Override
			public void setAsText(String text) {
				setValue(ZJ_Date2StrUtils.str2date(text));
			}
		});
	}

	@ResponseBody
	@RequestMapping("/datagrid")
	public EasyuiDatagrid datagrid(PageModel pageModel) {
		return userService.datagrid(pageModel);
	}

	@RequestMapping("/showView")
	public String showView(String id, HttpServletRequest request) {
		UserModel userModel = userService.getModelById(id);
		request.setAttribute("user", userModel);
		return "/ht/user/userShow";
	}

	@RequestMapping("/editView")
	public String editView(String id, HttpServletRequest request) {
		UserModel userModel = userService.getModelById(id);
		request.setAttribute("user", userModel);
		return "/ht/user/userEdit";
	}

	@ResponseBody
	@RequestMapping("/edit")
	public Json edit(UserModel userModel) {
		Json j = new Json();
		try {
			UserModel r = userService.edit(userModel);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
			j.setObj(r);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@ResponseBody
	@RequestMapping("/remove")
	public Json remove(PageModel pageModel) {
		userService.remove(pageModel.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		return j;
	}

	@RequestMapping("/show")
	public String showNote(String id, HttpServletRequest request) {
		UserModel userModel = userService.getModelById(id);
		request.setAttribute("userModel", userModel);
		return "ht/user/userShow";
	}

	@RequestMapping("/login")
	@ResponseBody
	public Json login(UserModel user, HttpServletRequest request,
			HttpSession session) {
		logger.info("user:"+ZJ_JacksonUtils.toJson(user));
		Json j = new Json();
		UserModel u = userService.find(user);
		if (u != null) {
			j.setSuccess(true);
			j.setMsg("登录成功！");
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setAdminId(u.getId());
			sessionInfo.setLoginName(u.getName());
			sessionInfo.setIp(IpUtil.getIpAddr(request));
			sessionInfo.setRoleIds(u.getRoleIds());
			sessionInfo.setRoleNames(u.getRoleNames());
			sessionInfo.setResourceIds(u.getResourceIds());
			sessionInfo.setResourceNames(u.getResourceNames());
			sessionInfo.setResourceUrls(u.getResourceUrls());
			session.setAttribute(ZJ_ResourceUtil.getSessionInfoName(), sessionInfo);
			request.getSession().setAttribute("user", u);
			j.setObj(sessionInfo);
		} else {
			j.setMsg("登录失败！");
		}
		return j;
	}

	@RequestMapping("/logout")
	@ResponseBody
	public Json logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		Json j = new Json();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/combogrid")
	@ResponseBody
	public EasyuiDatagrid combogrid(PageModel page) {
		return userService.datagrid(page);
	}

	@RequestMapping("/combobox")
	@ResponseBody
	public List<UserModel> combobox(UserModel user) {
		return userService.combobox(user);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(UserModel user) {
		Json j = new Json();
		try {
			UserModel u = userService.save(user);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg("添加失败！管理员名已存在！");
		}
		return j;
	}

	@RequestMapping("/modifyRole")
	@ResponseBody
	public Json modifyRole(UserModel user) {
		Json j = new Json();
		userService.modifyRole(user);
		j.setSuccess(true);
		j.setMsg("编辑成功！");
		return j;
	}

	@RequestMapping("/modifyPwd")
	@ResponseBody
	public Json modifyPwd(UserModel user) {
		Json j = new Json();
		userService.modifyPwd(user);
		j.setSuccess(true);
		j.setMsg("编辑成功！");
		return j;
	}

	@RequestMapping("/modifyCurrentAdminPwd")
	@ResponseBody
	public Json modifyCurrentAdminPwd(UserModel user) {
		Json j = new Json();
		userService.modifyPwd(user);
		j.setSuccess(true);
		j.setMsg("修改成功！");
		return j;
	}

	@RequestMapping("/adminInfo")
	public String adminInfo() {
		return "ht/admin/adminInfo";
	}
}
