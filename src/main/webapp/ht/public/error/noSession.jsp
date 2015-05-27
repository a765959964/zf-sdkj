<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	request.setAttribute("basePath", request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/");
%>

<script type="text/javascript" charset="utf-8">
	location.href = "${basePath}ht/login.jsp";
</script>