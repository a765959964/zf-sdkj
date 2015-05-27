package com.sdkj.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sdkj.pmodel.SessionInfo;
import com.sdkj.util.ZJ_ResourceUtil;

public class SecurityInterceptor implements HandlerInterceptor {
	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// 让不用验证的url通过
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		for (String str : excludeUrls) {
			if (null != url && url.contains(str)) {
				return true;
			}
		}
		// 如果session到期跳转
		Object obj = request.getSession().getAttribute(ZJ_ResourceUtil.getSessionInfoName());
		if (null == obj) {
			request.setAttribute("msg", "您没有访问此资源的权限！<br/>请联系超管赋予您<br/>[" + url + "]<br/>的资源访问权限！");
			request.getRequestDispatcher("/ht/public/error/noSession.jsp").forward(request, response);
			return false;
		}
		// 验证登录用户权限
		SessionInfo sessionInfo = (SessionInfo) obj;
		if (sessionInfo.getAdminId().equals("0")) {// 超管不需要验证权限
			return true;
		} else {
			List<String> urls = sessionInfo.getResourceUrls();
			logger.info("urls:" + urls);
			for (String string : urls) {
				if (null != string && !"".equals(string) && url.contains(string)) {
					return true;
				}
			}
			request.setAttribute("msg", "您没有访问此资源的权限！<br/>请联系超管赋予您<br/>[" + url + "]<br/>的资源访问权限！");
			request.getRequestDispatcher("/ht/public/error/noSecurity.jsp").forward(request, response);
			return false;

		}
	}
}
