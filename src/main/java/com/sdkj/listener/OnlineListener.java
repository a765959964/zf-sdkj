package com.sdkj.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sdkj.pmodel.SessionInfo;
import com.sdkj.service.OnlineService;
import com.sdkj.util.ZJ_ResourceUtil;

/**
 * 监听在线管理员上线下线
 * 
 * @author 张剑
 * 
 */
public class OnlineListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener, ServletRequestListener, ServletRequestAttributeListener {

	private static final Logger logger = Logger.getLogger(OnlineListener.class);

	private static ApplicationContext ctx = null;

	public OnlineListener() {
	}

	public void requestDestroyed(ServletRequestEvent arg0) {
	}

	/**
	 * 向session里增加属性时调用(管理员成功登陆后会调用)
	 */
	public void attributeAdded(HttpSessionBindingEvent evt) {
		String name = evt.getName();
		logger.debug("向session存入属性：" + name);
		if (ZJ_ResourceUtil.getSessionInfoName().equals(name)) {// 如果存入的属性是sessionInfo的话
			HttpSession session = evt.getSession();
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(name);
			if (sessionInfo != null) {
				OnlineService onlineService = (OnlineService) ctx.getBean("onlineService");
				onlineService.saveOrUpdateTonlineByLoginNameAndIp(sessionInfo.getLoginName(), sessionInfo.getIp());
			}
		}
	}

	/**
	 * 服务器初始化时调用
	 */
	public void contextInitialized(ServletContextEvent evt) {
		logger.debug("服务器启动");
		ctx = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
	}

	public void sessionDidActivate(HttpSessionEvent arg0) {
	}

	public void valueBound(HttpSessionBindingEvent arg0) {
	}

	public void attributeAdded(ServletContextAttributeEvent arg0) {
	}

	public void attributeRemoved(ServletContextAttributeEvent arg0) {
	}

	/**
	 * session销毁(管理员退出系统时会调用)
	 */
	public void sessionDestroyed(HttpSessionEvent evt) {
		HttpSession session = evt.getSession();
		if (session != null) {
			logger.debug("session销毁：" + session.getId());
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ZJ_ResourceUtil.getSessionInfoName());
			if (sessionInfo != null) {
				OnlineService onlineService = (OnlineService) ctx.getBean("onlineService");
				onlineService.deleteTonlineByLoginNameAndIp(sessionInfo.getLoginName(), sessionInfo.getIp());
			}
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	public void attributeAdded(ServletRequestAttributeEvent evt) {
	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {
	}

	public void sessionWillPassivate(HttpSessionEvent arg0) {
	}

	public void sessionCreated(HttpSessionEvent arg0) {
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
	}

	public void attributeReplaced(ServletContextAttributeEvent arg0) {
	}

	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
	}

	public void contextDestroyed(ServletContextEvent evt) {
		logger.debug("服务器关闭");
	}

	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
	}

	public void requestInitialized(ServletRequestEvent arg0) {
	}

}
