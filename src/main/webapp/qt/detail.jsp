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

<title>${topcolumn.cname}-郑州斯迪科技有限公司</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="${basePath}qt/css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}qt/css/jquery.js"></script>
<script type="text/javascript" src="${basePath}qt/css/droppy.js"></script>
<script type="text/javascript" src="${basePath}qt/css/png.js"></script>
<link href="${basePath}qt/css/sub.css" rel="stylesheet" type="text/css" />

</head>

<body>

	<!-- 头部 -->
	<div class="header">

		<jsp:include page="header.jsp"></jsp:include>

	</div>

	<!-- contenter -->
	<div class="contenter">
		<div class="contenter_l">

			<jsp:include page="cl.jsp"></jsp:include>

		</div>
		<div class="contenter_r">

			<jsp:include page="cr.jsp"></jsp:include>

		</div>
	</div>

	<!-- footer -->
	<div class="footer">
	
		<jsp:include page="footer.jsp"></jsp:include>
	</div>

</body>
</html>
