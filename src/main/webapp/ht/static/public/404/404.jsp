<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setAttribute("basePath", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>你所访问的页面不存在 (404)</title>
<link href="${basePath}public/404/404/404.css" rel="stylesheet" type="text/css">
<style type="text/css"></style>
</head>

<body>
	<h1>404</h1>
	<h3>你所访问的页面不存在.</h3>
	<hr>
	<p>
		你可能输入了不存在的URL地址，或者该资源已经被删除， <a href="${basePath}">点击这里</a> 回到首页.
	</p>
</body>
</html>