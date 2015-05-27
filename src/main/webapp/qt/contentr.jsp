<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 右1 最新公告 -->
	<div class="contenter_r_1">
		<div class="contenter_r_t">最新公告</div>
		<div class="contenter_r_1_con">
			<div class="contenter_l_2_con5" style="padding:0px; border:none;">
				<ul>
					<li><a href="news_info.html">斯迪科技一家专业从事电信增值……</a></li>
					<li><a href="news_info.html">运营商短信深度运营平台上线……</a></li>
					<li><a href="news_info.html">2012年度十佳增值业务合作伙伴……</a></li>
					<li><a href="news_info.html">空中充值业务（移动、联通、电信）……</a></li>
				</ul>
				<div class="clear"></div>
			</div>
		</div>
	</div>


	<!-- 右2   产品系列 -->
	<div class="contenter_r_2">
		<div class="contenter_r_t">业务体系</div>
		<div class="contenter_r_2_con">
			<div class="prod_type">
				<ul>
				
					<c:forEach items="${yewutixi }" var="tixi">
						<li><a href="${basePath}do/sd/list?cid=${tixi.id}">${tixi.cname }</a></li>
					</c:forEach>
					
				</ul>
			</div>
			<script language="javascript" type="text/javascript">
				$(".prod_type>ul").droppy();
				$(".prod_type ul ul li:last-child").css("border", "0px");
			</script>
		</div>
	</div>



	<!--右3  联系我们 -->
	<div class="contenter_r_3">
		<c:forEach items="${lianxiwomen }" var="lxwn">
		<div class="contenter_r_t">${lxwn.cname }</div>
		<div class="contenter_r_3_con">
			<img src="${lxwn.pic }" width="243" height="70" />
			${lxwn.content }
		</div>
		</c:forEach>
	</div>
	<div class="contenter_r_4"></div>

<div class="clear"></div>