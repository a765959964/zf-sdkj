<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="contenter_l_1">
	<div class="contenter_l_t">公司简介</div>
	<div class="contenter_l_1_con">
		<dl>
			<c:forEach items="${gsjj}" var="jianjie" begin="0" end="0">
			<dt>
				<img src="${jianjie.pic }" width="220"
					height="200" />
			</dt>
			<dd>
				
						${jianjie.content }
					</c:forEach>
				<div class="more">
					<a href="javascript:void(0);"><img
						src="${basePath}qt/images/more.gif" width="35" height="9" /></a>
				</div>
			</dd>
		</dl>
		<div class="clear"></div>
	</div>
</div>
<div class="contenter_l_2">
	<div class="contenter_l_t">公司新闻</div>
	<div class="contenter_l_2_con">
		<ul>
			<c:forEach items="${gongsixinwen }" var="xw" begin="0" end="9">
				<li><i>${fn:substring(xw.createTime,0,10) }</i><a href="${basePath}do/sd/detail?articleId=${xw.articleId}">${fn:substring(xw.title,0,11)}……</a></li>
			</c:forEach>
		</ul>
		<div class="clear"></div>
		<div class="more">
			<a href="javascript:void(0)"><img src="${basePath}qt/images/more.gif"
				width="35" height="9" /></a>
		</div>
	</div>
</div>


<!-- 左三 -->
<div class="contenter_l_3">
	<div class="contenter_l_t">业务平台</div>
	<div class="contenter_l_3_con">
		<ul class="pro_list">
				
			<c:forEach items="${tlinks }" var="tlink" begin="0" end="3">
					
				<li>
					<div class="pic_img">
						<a target="_blank" href="${tlink.siteurl }"><img
							src="${tlink.imgsrc }" width="130" height="130" /></a>
					</div>
					<div class="pic_name">
						<a target="_blank" href="${tlink.siteurl}">${tlink.sitename}</a>
					</div>
				</li>
			</c:forEach>
			
			<div class="clear"></div>
		</ul>

	</div>
</div>

<!-- 左4 业务合作公司  -->
<div class="contenter_l_4">
	<div class="contenter_l_4_con">
		<span>业务合作公司：</span>
		<ul>
			<li><a href="#">郑州明旭泰科贸有限公司</a></li>
			<li><a href="#">广州动感在线通信科技有限公司</a></li>
			<li><a href="#">湖北凡威科技有限公司</a></li>
			<div class="clear"></div>
		</ul>
	</div>
	<div class="clear"></div>
</div>