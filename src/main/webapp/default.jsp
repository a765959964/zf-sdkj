<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setAttribute("basePath", request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="${basePath}">

<title></title>
<link rel="icon" href="favicon.png" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.png" type="image/x-icon" />
<script type="text/javascript">
	location.href = "${basePath}admin.jsp";
</script>
</head>
</html>
