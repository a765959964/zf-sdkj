<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="top_img">
	<img src="${basePath}qt/images/top_img.jpg" width="950" height="110" />
</div>
<div class="menu">
	<ul>
		<c:forEach items="${menuList }" var="menu">
			<li>
			
			<c:choose>
				<c:when test="${menu.cname eq '首页' }">
					<a href="${basePath}do/sd/index">${menu.cname }</a>
				</c:when>
				
				<c:when test="${menu.cname eq '招纳贤士' }">
					<a href="${basePath}do/sd/list?cid=${menu.id}">${menu.cname }</a>
				</c:when>
				
				<c:when test="${menu.cname eq '联系我们' }">
					<a href="${basePath}do/sd/list?cid=${menu.id}">${menu.cname }</a>
				</c:when>
				<c:otherwise>
           				<a href="javascript:(0);">${menu.cname }</a>
     			</c:otherwise>
			</c:choose>
			
				<c:if test="${not empty menu.list }">
					<ul>
						<c:forEach items="${menu.list }" var="m2">
							
							<li>
								
								<c:if test="${m2.cname eq '公司新闻' }">
									<a href="${basePath}do/sd/page?cid=${m2.id}">${m2.cname }</a>
								</c:if>
								
								<a href="${basePath}do/sd/list?cid=${m2.id}">${m2.cname }</a>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:forEach>
	</ul>
	<div class="clear"></div>
	<script language="javascript">
		$(".menu>ul>li").hover(function() {
			$(this).children("ul").show();
		}, function() {
			$(this).children("ul").hide();
		});
	</script>
</div>
<div class="banner">
	<div style=" z-index:50;">
		<script language='javascript'>
			linkarr = new Array();
			picarr = new Array();
			textarr = new Array();
			var swf_width = 940;
			var swf_height = 250;
			var files = "";
			var links = "";
			var texts = "";

			//这里设置调用标记
			linkarr[1] = "";
			picarr[1] = "${basePath}qt/images/banner1.jpg";
			textarr[1] = "";
			linkarr[2] = "";
			picarr[2] = "${basePath}qt/images/banner2.jpg";
			textarr[2] = "";
			linkarr[3] = "";
			picarr[3] = "${basePath}qt/images/banner3.jpg";
			textarr[3] = "";

			for (i = 1; i < picarr.length; i++) {
				if (files == "")
					files = picarr[i];
				else
					files += "|" + picarr[i];
			}
			for (i = 1; i < linkarr.length; i++) {
				if (links == "")
					links = linkarr[i];
				else
					links += "|" + linkarr[i];
			}
			for (i = 1; i < textarr.length; i++) {
				if (texts == "")
					texts = textarr[i];
				else
					texts += "|" + textarr[i];
			}
			document
					.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ swf_width +'" height="'+ swf_height +'">');
			document
					.write('<param name="movie" value="qt/images/bcastr3.swf"><param name="quality" value="high">');
			document
					.write('<param name="menu" value="false"><param name=wmode value="opaque">');
			document
					.write('<param name="FlashVars" value="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'">');
			document
					.write('<embed src="${basePath}qt/images/bcastr3.swf" wmode="opaque" FlashVars="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'& menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');
			document.write('</object>');
		</script>
	</div>
</div>
