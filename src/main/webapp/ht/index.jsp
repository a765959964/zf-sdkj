<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>思迪后台管理系统</title>
<jsp:include page="public/inc/inc.jsp"></jsp:include>
<jsp:include page="public/inc/meta.jsp"></jsp:include>
<jsp:include page="public/inc/islogin.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false,href:'${pageContext.request.contextPath}/ht/public/layout/north.jsp'" style="height: 50px;overflow: hidden;" class="app-header"></div>
	<div data-options="region:'west',border:false,title:'功能导航',split:true,href:'${pageContext.request.contextPath}/ht/public/layout/west.jsp'" style="width: 200px;overflow: hidden;"></div>
	<div data-options="region:'center',border:false,split:true,title:'',href:'${pageContext.request.contextPath}/ht/public/layout/center.jsp'" style="overflow: hidden;"></div>
	<div data-options="region:'east',title:'日历',border:false,split:true" style="width: 200px;overflow: hidden;">
		<jsp:include page="public/layout/east.jsp"></jsp:include>
	</div>
	<div data-options="region:'south',border:false,href:'${pageContext.request.contextPath}/ht/public/layout/south.jsp'" style="height: 27px;overflow: hidden;"></div>
</body>
</html>