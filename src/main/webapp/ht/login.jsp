<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("basePath", request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业后台登陆系统</title>
<script type="text/javascript"
	src="${basePath}/static/jquery-easyui-1.3.1/jquery-1.8.0.min.js"
	charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/js/zjJsExt.js"
	charset="utf-8"></script>
<link href="${basePath}ht/static/login/style/index.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	$(function() {
		$("#formButton").click(
				function() {
					$.post("${basePath}do/user/login", $("#form").serialize(),
							function(data) {
								if (data.success) {
									location.href = "${basePath}ht/index.jsp";
								} else {
									alert("账户或密码有误，请重新输入");
								}
							});
				});
	});
</script>
<script type="text/JavaScript">
	if (window != top) {
		top.location.href = location.href;
	}
</script>
</head>
<body
	style="background:url(${basePath}ht/static/login/images/bg.jpg); width:100%; height:100%">
	<div class="logo">
		<img src="${basePath}ht/static/login/images/dl_03.png" />
	</div>
	<div class="title">
		<img src="${basePath}ht/static/login/images/title.png" />
	</div>
	<div class="form">
		<form action="" method="get" id="form">
			<ul>
				<li><span>用户名：</span> <input name="name" type="text" class="a1" />
				</li>
				<li><span>密码:</span> <input name="pwd" type="password"
					class="a1" /></li>
				<li><span></span> <input name="" type="button" value="登 陆"
					class="a2" id="formButton" /></li>
			</ul>
		</form>

	</div>
</body>
</html>

