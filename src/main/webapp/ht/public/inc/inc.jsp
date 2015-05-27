<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	request.setAttribute("basePath", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
java.util.Map<String, Cookie> cookieMap = new java.util.HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "gray";//指定如果用户未选择样式，那么初始化一个默认样式
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>
<!-- jquery -->
<script type="text/javascript" src="${basePath}/static/jquery-easyui-1.3.1/jquery-1.8.0.min.js" charset="utf-8"></script>
<!-- my97日期控件 -->
<script type="text/javascript" src="${basePath}/static/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<!-- easyui控件 -->
<link id="easyuiTheme" rel="stylesheet" href="${basePath}/static/jquery-easyui-1.3.1/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css" type="text/css"><
<!-- <link id="easyuiTheme" ref="stylesheet" href="${basePath}/static/jquery-easyui-1.3.1/themes/<%=easyuiTheme%>/easyui.css" type="text/css"> -->
<link rel="stylesheet" href="${basePath}/static/jquery-easyui-1.3.1/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="${basePath}/static/jquery-easyui-1.3.1/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- cookie插件 -->
<script type="text/javascript" src="${basePath}/static/js/jquery.cookie.js" charset="utf-8"></script>
<!-- kindeditor相关 -->
<link rel="stylesheet" href="${basePath}static/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="${basePath}static/kindeditor-4.1.10/plugins/code/prettify.css" />
<script charset="utf-8" src="${basePath}static/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}static/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8" src="${basePath}static/kindeditor-4.1.10/plugins/code/prettify.js"></script>
<!-- 自己定义的样式和JS扩展 -->
<link rel="stylesheet" href="${basePath}/static/css/iconCls.css" type="text/css"></link>
<script type="text/javascript" src="${basePath}/static/js/zj_jsExt.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/js/zj_jqueryExt.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/js/zj_easyuiExt.js" charset="utf-8"></script>
<!-- 日历控件 -->
<link href='${basePath}ht/static/fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='${basePath}ht/static/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='${basePath}ht/static/fullcalendar/fullcalendar.min.js'></script>
