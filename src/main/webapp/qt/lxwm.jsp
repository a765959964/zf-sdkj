<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>联系我们-郑州斯迪科技有限公司 </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  		<div class="header">
  			<jsp:include page="header.jsp"></jsp:include>
  		</div>
  		
  		
  		<div class="contenter">
  			
  			<div class="contenter_l">
  				<jsp:include page="cl.jsp"></jsp:include>
  			</div>
  			
  			<div class="contenter_r">
				<jsp:include page="cr.jsp"></jsp:include>  				
  			</div>
  		</div>
  
  	
  		<div class="footer">
			<jsp:include page="footer.jsp"></jsp:include>  		
  		</div>
  	
  </body>
</html>
