<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<title>${topcolumn.cname }-郑州斯迪科技有限公司</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="${basePath}qt/css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}qt/css/jquery.js"></script>
<script type="text/javascript" src="${basePath}static_lzcc/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}qt/css/droppy.js"></script>
<script type="text/javascript" src="${basePath}qt/css/png.js"></script>
<script type="text/javascript" src="${basePath}qt/css/LoadImage.js" ></script>
<script type="text/javascript" src="${basePath}static/zj_pager/js/pager.js"></script>

<link href="${basePath}qt/css/sub.css" rel="stylesheet" type="text/css" />
<link href="${basePath}static/zj_pager/css/pager.css" type="text/css" rel="Stylesheet" />


<script type="text/javascript">
	$(document).ready(
			function() {
				$(".auto_img_cc").LoadImage(true, 150, 150,
						"<img src=\"${basePath}qt/images/loading.gif\"/>");
			});
</script>

<script type="text/javascript">
	window.onload = function() {

		initPage(${page.page}, ${page.pageNum});
	}

	function goPage(p, pn) {
		location.href = "${basePath}do/sd/page?cid=${cid}&page=" + p + "&rows=5";
	}
</script>

</head>

<body>

	<div class="header">
		<jsp:include page="header.jsp"></jsp:include>
	</div>


	<div class="contenter">
		<div class="contenter_l">

			<div class="contenter_l_t">
				当前位置：${topcolumn.cname}&gt;&gt;${column.cname }
				<div class="contenter_l_con">
					<div class="list_box">
						<div class="list_box_list">
							<ul>
								<c:forEach items="${articleList }" var="article">
									<li><span class="f_r">${fn:substring(article.createTime,0,10) }</span><a href="${basePath}do/list?cid=${article.cid}">${article.title }</a></li>
								</c:forEach>
							</ul>
						</div>
						<div class="clear"></div>
							<div id="zj_pager" style="clear:both">
								<div id="pageNum" class="pageNum" style="display: none"></div>
								<div id="pageNav" class="sabrosus"></div>
							</div>
					</div>
				</div>
			</div>



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
