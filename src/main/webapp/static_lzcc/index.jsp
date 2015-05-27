<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 

<html>
<head>
<link href="${basePath}static_lzcc/style/simplefoucs_lrtk.css" rel="stylesheet" />
<link href="${basePath}static_lzcc/style/style.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}static_lzcc/style/wowsai.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}static_lzcc/js/jquery.js"></script>
<script src="${basePath}static_lzcc/js/simplefoucs.js" type="text/javascript"></script>
<meta charset="utf-8">
<title>思迪科技-首页</title>
<style>
/*透明背景解决*/
.submenu {
	behavior: url("${basePath}static_lzcc/pngbehavior.htc");
}

#slideBox .J_slide_trigger {
	behavior: url("${basePath}static_lzcc/pngbehavior.htc");
}

.J_slide_trigger li:hover {
	width: 240px;
	height: 28px;
	background-image: url(${basePath}static_lzcc/images/banner_b_bg_03.png);
	behavior: url("${basePath}static_lzcc/pngbehavior.htc");
}

#focus .pre {
	behavior: url("${basePath}static_lzcc/pngbehavior.htc");
}

#focus .next {
	behavior: url("${basePath}static_lzcc/pngbehavior.htc");
}
</style>

<!--产品图滚动-->


<!--导航 下拉菜单-->
<script>
	function dropMenu(obj) {
		$(obj).each(function() {
			var theSpan = $(this);
			var theMenu = theSpan.find(".submenu");
			var tarHeight = theMenu.height();
			theMenu.css({
				height : 0,
				opacity : 0
			});

			var t1;

			function expand() {
				clearTimeout(t1);
				theSpan.find('a').addClass("selected");
				theMenu.stop().show().animate({
					height : tarHeight,
					opacity : 1
				}, 200);
			}

			function collapse() {
				clearTimeout(t1);
				t1 = setTimeout(function() {
					theSpan.find('a').removeClass("selected");
					theMenu.stop().animate({
						height : 0,
						opacity : 0
					}, 200, function() {
						$(this).css({
							display : "none"
						});
					});
				}, 250);
			}

			theSpan.hover(expand, collapse);
			theMenu.hover(expand, collapse);
		});
	}

	$(document).ready(function() {

		dropMenu(".drop-menu-effect");

	});
</script>
</head>
<body>
	<div class="header">
		<img src="${basePath}static_lzcc/images/logo_05.png"/>
		<div class="nav_top">
			<ul>
				<c:forEach items="${menuList }" var="menu">

					<li class="drop-menu-effect">
						<p class="about" >
							<span>
								<a href="${basePath }do/lzcc/index">${menu.cname }</a>
							</span>
							<br />${menu.tip }</p>
						<c:if test="${not empty menu.list}">
							<ul class="submenu">

								<c:forEach items="${menu.list}" var="m2">
									<li>
										<a href="${basePath}do/lzcc/list?cid=${m2.id}">${m2.cname}</a>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div style="clear:both;"></div>
	<div class="banner_box">
		<div id="wrap">
			<script type="text/javascript" src="${basePath}static_lzcc/js/lunfan.js" charset="utf-8"></script>
			<div id="mainBox">
				<div class="zhuanti_box">
					<div id="slideBox" style="float:left;">
						<div class="J_slide" name="__DTD2">
							<div class="J_slide_clip">
								<ul class="J_slide_list">
									<li class="J_slide_item">
										<a href="#" onclick="return false">
											<img src="${basePath}static_lzcc/images/z12.jpg" height="389" width="1000"/>
										</a>
									</li>
									<li class="J_slide_item">
										<a href="#">
											<img src="${basePath}static_lzcc/images/6.jpg" height="389" width="1000"/>
										</a>
										<div class="J_slide_advance"></div>
									</li>
									<li class="J_slide_item">
										<a href="#">
											<img src="${basePath}static_lzcc/images/z2.jpg" height="389" width="1000"/>
										</a>
									</li>
									<li class="J_slide_item">
										<a href="#">
											<img src="${basePath}static_lzcc/images/zz2.jpg" height="389" width="1000"/>
										</a>
									</li>
								</ul>
								<div style="clear:both;"></div>
							</div>
							<ul class="J_slide_trigger">
								<li class="">
									<a href="javascript:;" title="龙渚春茶 好茶不贵"> 龙渚春茶 好茶不贵</a>
								</li>
								<li class="">
									<a href="javascript:;"  title="龙渚春茶 好茶不贵"> 天赐名茶</a>
								</li>
								<li class="">
									<a href="javascript:void(0)" title="龙渚春茶 好茶不贵"> 茶园风光 </a>
								</li>
								<li class="">
									<a href="javascript:void(0)"  title="龙渚春茶 好茶不贵"> 招商热线</a>
								</li>
							</ul>
						</div>
					</div>
					<script type="text/javascript">
						new Tab('.J_tab', {
							auto : true
						});
						new Slide('#slideBox', {
							index : 1,
							effect : 'slide',
							firstDelay : 8
						});
					</script>
				</div>
			</div>

		</div>

	</div>
	<div style="clear:both"></div>
	<div class="container_box">
		<div class="con_01">
			<div class="about_lzc">
				<div class="cloumnA">
					<div class="cloumn_bg"></div>
					<div class="cloumn_n">
						<p>
							<span class="span_01">关于龙渚春</span>
							<br>
							<span class="span_02">ABOUT LONGZHUCHUN</span>
						</p>
						<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</div>
				</div>
				<div class="video_com">
				 <embed src="http://player.youku.com/player.php/sid/XNjQ5MDI1MDUy/v.swf" allowFullScreen="true" quality="high" width="298" height="208" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash"></embed>
				</div>
			</div>
			<!--选项卡-->
			<div class="menubox">
				<ul class="menulist">
					<li class="current">
						<span>公司简介</span>
					</li>
					<li class="others">
						<span>企业动态</span>
					</li>
					<li class="others">
						<span>优秀茶品</span>
					</li>
				</ul>
				<div class="content">
					<div class="content_p01">
						<c:forEach items="${jianjieList}" var="jianjie" begin="0" end="0">
						${fn:substring(jianjie.content,0,270)}
					</c:forEach>
						<a href="${basePath }do/lzcc/detail?articleId=${jianjie.articleId}">【详情】</a>
					</div>
				</div>
				<div class="content" style="display:none;padding-top:12px;height: 202px;">
					<c:forEach items="${qiye }" var="qiye" begin="0" end="8">
						<ol id="tea_news_01">
							<li>
								<span>${fn:substring(qiye.createTime,0,10)}</span>
								<a href="${basePath }do/lzcc/detail?articleId=${qiye.articleId}">${fn:substring(qiye.title,0,15)}</a>
								
							</li>
						</ol>
					</c:forEach>
				</div>
				<div class="content" style="display:none">
					<c:forEach items="${xinyanghong }" var="xyh" begin="0" end="0">
						<img src="${xyh.pic }" >
					</c:forEach>
					<div class="pro_intro">
						<c:forEach items="${xinyanghong }" var="xyh" begin="0" end="0">
							<h4 class="content_p01">${xyh.title}</h4>
							<p class="content_p02"></p>
							<a href="${basePath }do/lzcc/detail?articleId=${xyh.articleId}">详情</a>
							</p>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="tea_news">
				<div class="cloumnA">
					<div class="cloumn_bg"></div>
					<div class="cloumn_n">
						<p>
							<span class="span_01">行业资讯&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<br>
							<span class="span_02">INFORMATION</span>
						</p>
						
						<c:forEach items="${hyzx}" var="hy" begin="0" end="0">
						<a href="${basePath}do/lzcc/list?cid=${hy.cid}">更多>></a>
						</c:forEach>
					</div>

				</div>
				<div class="new_list">
					<ol id="roll">
						<c:forEach items="${hyzx}" var="hy">
							<li>
								<a href="${basePath}do/lzcc/detail?articleId=${hy.articleId}">
									<span>${fn:substring(hy.createTime,0,10)}</span>
									${fn:substring(hy.title,0,13) }
									
								</a>
							</li>
						</c:forEach>
					</ol>
				</div>
			</div>

		</div>
		<div class="pro_list">
			<div class="cloumnA">
				<div class="cloumn_bg"></div>
				<div class="cloumn_n">
					<p>
						<span class="span_01">茶品推荐</span>
						<br>
						<span class="span_03">RECOMMENDED &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</p>
				<c:forEach items="${chayefl }" var="fl" begin="0" end="0">
					<a href="${basePath}do/lzcc/list?cid=${fl.cid}">更多>></a>
				</c:forEach>
				</div>
			</div>
			<div class="pro_list_pic">
				<div class="bannerbox">
					<div id="focus">
						<ul>
<li>
							<c:forEach items="${maojian}" var="mj" varStatus="num" begin="0" end="7">
							<c:if test="${num.index%4==0&&num.index!=0}">
							</li>
							<li>
							</c:if>
								<a href="${basePath}do/lzcc/detail?articleId=${mj.articleId}" target="_blank">
									<img src="${mj.pic }" alt="">
									<span class="focus_pro_intro">${mj.title }</span></a>
							</c:forEach>
							</li>
						</ul>
					</div>
				</div>
				<!--<p><a class="button_left"></a><a class="button_right"></a></p> -->
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="footer">

			<p class="blogroll">
				<span>友情链接：</span>
				<c:forEach items="${tlinks}" var="tl" begin="0" end="5">
					<a href="${tl.siteurl }" target="_blank">${tl.sitename }</a>
				</c:forEach>
			</p>
			<p class="scenic_spots">
				<span>信阳旅游景点：</span>
				<a href="http://www.bytravel.cn/Landscape/30/siwangshan.html" target="_blank">四望山红色旅游景区</a>
				<a href="http://www.nanwanhu.gov.cn/lvyoufuwu/zn/" target="_blank">南湾湖风景区</a>
				<a href="http://www.hnly.gov.cn/xinyang/zybh/zrbhq/webinfo/2009/12/1259720231292676.htm" target="_blank">天目山省级森林保护区</a>
				<a href="http://luoshan.xyskjj.gov.cn/system/custom_info/showinfo.asp?infoid=6789" target="_blank">九里落雁湖</a>
				<a href="http://www.luoshan.gov.cn/html/2009325533422053697.html" target="_blank">天下第一险石</a>
			</p>
			<p class="copyright">
				Copyright (c) 2014-2015 龙渚春茶. All Right Reserved
				<br>
				技术支持：
				<a href="http://www.yesteches.com" target="_blank">雅思科技</a>
				豫ICP备11016542号
			</p>

		
		</div>
	</div>
	<!--选项卡-->
	<script>
		$(".menulist li").mouseover(function() {
			$(".menulist li").addClass("others");
			$(this).removeClass("others").addClass("current");
			var index = $(".menulist li").index(this);
			$(".content").hide();
			$(".content").eq(index).fadeIn(0);
		});
	</script>
	<script type="text/javascript">
		(function(A) {
			function _ROLL(obj) {
				this.ele = document.getElementById(obj);
				this.interval = false;
				this.currentNode = 0;
				this.passNode = 0;
				this.speed = 100;
				this.childs = _childs(this.ele);
				this.childHeight = parseInt(_style(this.childs[0])['height']);
				addEvent(this.ele, 'mouseover', function() {
					window._loveYR.pause();
				});
				addEvent(this.ele, 'mouseout', function() {
					window._loveYR.start(_loveYR.speed);
				});
			}
			function _style(obj) {
				return obj.currentStyle || document.defaultView.getComputedStyle(obj, null);
			}
			function _childs(obj) {
				var childs = [];
				for (var i = 0; i < obj.childNodes.length; i++) {
					var _this = obj.childNodes[i];
					if (_this.nodeType === 1) {
						childs.push(_this);
					}
				}
				return childs;
			}
			function addEvent(elem, evt, func) {
				if (-[ 1, ]) {
					elem.addEventListener(evt, func, false);
				} else {
					elem.attachEvent('on' + evt, func);
				}
				;
			}
			function innerest(elem) {
				var c = elem;
				while (c.childNodes.item(0).nodeType == 1) {
					c = c.childNodes.item(0);
				}
				return c;
			}
			_ROLL.prototype = {
				start : function(s) {
					var _this = this;
					_this.speed = s || 100;
					_this.interval = setInterval(function() {
						_this.ele.scrollTop += 1;
						_this.passNode++;
						if (_this.passNode % _this.childHeight == 0) {
							var o = _this.childs[_this.currentNode] || _this.childs[0];
							_this.currentNode < (_this.childs.length - 1) ? _this.currentNode++ : _this.currentNode = 0;
							_this.passNode = 0;
							_this.ele.scrollTop = 0;
							_this.ele.appendChild(o);
						}
					}, _this.speed);
				},
				pause : function() {
					var _this = this;
					clearInterval(_this.interval);
				}
			}
			A.marqueen = function(obj) {
				A._loveYR = new _ROLL(obj);
				return A._loveYR;
			}
		})(window);
		marqueen('roll').start(100/*速度默认100*/);
	</script>
</body>
</html>
