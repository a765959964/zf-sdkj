<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="contenter_l">

	<div class="contenter_l_t">
		当前位置：<c:choose>
					
					<c:when test="${column.cname eq '联系我们' and topcolumn.cname eq '联系我们'}">
							${column.cname }
					</c:when>
					
					<c:when test="${column.cname eq '招纳贤士' and topcolumn.cname eq '招纳贤士' }">
							${column.cname }
					</c:when>
					
					<c:otherwise>
							${topcolumn.cname }&gt;&gt;${column.cname }
					</c:otherwise>
					
			   </c:choose>
		
	
	</div>
	<div class="contenter_l_con">
			${article.content}
	</div>



</div>