<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>思迪科技</title>
<link href="${basePath}qt/css/default.css" rel="stylesheet"
	type="text/css" />
<link href="${basePath}qt/css/index.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${basePath}qt/css/jquery.js"></script>
<script type="text/javascript" src="${basePath}qt/css/droppy.js"></script>
<script type="text/javascript" src="${basePath}qt/css/png.js"></script>
</head>

<body>

	<!-- 头部 -->
	<div class="header">

		<jsp:include page="header.jsp"></jsp:include>

	</div>


	<!-- 内容 -->
	<div class="contenter">

		<jsp:include page="contenter.jsp"></jsp:include>

	</div>


	<!-- 尾部 -->
	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>

</body>
</html>
