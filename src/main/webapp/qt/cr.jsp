<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="contenter_r_2">
	<div class="contenter_r_t">业务体系</div>
	<div class="contenter_r_2_con">
		<div class="prod_type">
			<ul>
				<c:forEach items="${yewutixi }" var="tixi">
					<li><a href="${basePath}do/sd/list?cid=${tixi.id}">${tixi.cname }</a></li>
				</c:forEach>
				<div class=" clear"></div>
			</ul>
		</div>
		<script language="javascript" type="text/javascript">
			$(".prod_type>ul").droppy();
			$(".prod_type ul ul li:last-child").css("border", "0px");
		</script>
	</div>
</div>

<div class="contenter_r_3">
	<div class="contenter_r_t">${lianxiwomen.cname }</div>
	<div class="contenter_r_3_con">
		<img src="${lianxiwomen.pic }" width="243" height="68" />
		${lianxiwomen.content }
	</div>
</div>


<div class="contenter_r_4"></div>

<div class="clear"></div>
