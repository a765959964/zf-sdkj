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
<link href="${basePath}static_lzcc/style/stylechild.css" rel="stylesheet" type="text/css" />
<link href="${basePath}static_lzcc/style/tea_center.css" rel="stylesheet" type="text/css" />
<link href="${basePath}static_lzcc/style/base.css" rel="stylesheet" type="text/css" />
<link href="${basePath}static_lzcc/style/themes.css" rel="stylesheet" type="text/css" />
<link href="${basePath}static_lzcc/style/style.css" rel="stylesheet" type="text/css" />
<link href="${basePath}static_lzcc/style/tea_center.css" rel="stylesheet" type="text/css" />
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

<script type="text/javascript" src="${basePath}static_lzcc/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}static_lzcc/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/static/zj_pager/js/pager.js"></script>
<link href="${basePath}/static/zj_pager/css/pager.css" type="text/css" rel="Stylesheet" />
<style>
.job_name {
	width: 730px;
	height: 35px;
	line-height: 35px;
	margin-top: 20px;
	margin-bottom: 19px;
	background: #F7F7F7 no-repeat 15px center;
	padding-left: 25px;
}

.job_name a {
	cursor: pointer;
}

.job_name a:hover {
	color: #666;
}

.job_name h3 {
	margin-left: 30px;
	height: 35px;
	line-height: 35px;
	background: url(${basePath}static_lzcc/images/button_job.png) no-repeat
		right center;
	cursor: default;
	margin-right: 30px;
}

.job_name h3 {
	margin-left: 30px;
	height: 35px;
	line-height: 35px;
	background: url(${basePath}static_lzcc/images/button_job.png) no-repeat
		right center;
	cursor: pointer;
}

job_name job_name1 {
	padding-left: 40px;
	margin-left: 30px;
}

.job_details td {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #333;
	padding-left: 40px;
}
</style>
<meta charset="utf-8">
<title>${column.cname}-${topcolumn.cname }-龙渚春茶</title>
<!--分类菜单-->

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
<!--fenleicaidan-->
<!--分页初始化 -->
<script type="text/javascript">
	window.onload = function() {
		initPage(${page.page}, ${page.pageNum});
	}

	function goPage(p, pn) {
		location.href = "${basePath}do/lzcc/list?cid=${cid}&page=" + p + "&rows=4";
	}
</script>

</head>
<body>
	<div class="header">
		<img src="${basePath}static_lzcc/images/logo_05.png" />
		<div class="nav_top">
			<ul>
				<c:forEach items="${menuList }" var="menu">
					<li class="drop-menu-effect">
						<p class="about">
							<span>
								<a href="${basePath }do/lzcc/index">${menu.cname }</a>
							</span>
							<br />${menu.tip }</p>
						<c:if test="${not empty menu.list}">
							<ul class="submenu">
								<c:forEach items="${menu.list}" var="menu2">
									<li>
										<a href="${basePath}do/lzcc/list?cid=${menu2.id}">${menu2.cname}</a>
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
		<div class="banner_img_box">
			<c:if test="${topcolumn.id eq '5IBAAG3IDOM10' }">
				<img src="${basePath}static_lzcc/images/6.jpg" />
			</c:if>
			<c:if test="${topcolumn.id eq '5IBAAG3JL2811' }">
				<img src="${basePath}static_lzcc/images/z2.jpg" />
			</c:if>
			<c:if test="${topcolumn.id eq '5IBAAG3K08X12' }">
				<img src="${basePath}static_lzcc/images/zz2.jpg" />
			</c:if>


			<div class="banner"></div>
		</div>

	</div>
	<div class="container_box">
		<div class="tea_center_pro_list">
			<div class="cloumnB">
				<div class="cloumn_bg"></div>
				<div class="cloumn_B_n">
					<p class="tea_garden">
						<span class="span_01">${topcolumn.cname }</span>
						<br>
						<span class="span_02">${topcolumn.tip }</span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</div>
			</div>
			<div class="tea_center_list">
				<!--chapinfenlei-->
				<ul>	
					<c:choose>	
						<c:when test="${column.id=='5IBAAG3V1HZ1B' or column.pid eq '5IBAAG3V1HZ1B' }">
							<c:forEach items="${columnMap}" var="map" varStatus="st">
								<li class="pro_class_B">
									<ul>
										<c:forEach items="${map.value}" var="list">
											<c:if test="${not empty list}">
												<li>
													<a onclick="location.href='${basePath}do/lzcc/list?cid=${list.id}'">${list.cname}</a>
												</li>
											</c:if>
										</c:forEach>
									
									</ul>
								</li>
							</c:forEach>
								
						</c:when>
						<c:otherwise>
							<c:forEach items="${columnList}" var="column">
								<li class="pro_class_B">
									<a onclick="location.href='${basePath}do/lzcc/list?cid=${column.id}'">${column.cname}</a>
									<c:if test="${not empty column.list}">
										<ul class="submenu">
											<c:forEach items="${column.list}" var="column2">
												<li>
													<a href="${basePath}do/lzcc/list?cid=${column2.id}">${column.cname}</a>
												</li>
											</c:forEach>
										</ul>
									</c:if>
								</li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
				</ul>
			</div>
			<div>
				<div>
					<img class="tell_pic" src="${basePath }static_lzcc/images/tea_center_tell.png" />
				</div>
			</div>
			<script>
				$(".pro_class_B").toggle(function() {
					$(this).next(".pro_class_B_list").slideUp(300);
				}, function() {
					$(this).next(".pro_class_B_list").slideDown(300);
				})
			</script>
		</div>




		<div class="tea_pro">
			<p class="link_nav">
				<span>当前位置：</span>
				<a href="${basePath}do/lzcc/index">首页&nbsp;</a>
				&gt;
				<a href="javascript:void(0);">${topcolumn.cname }</a>
				&gt;
				<a href="${basePath}do/lzcc/list?cid=${column.id}">${column.cname }</a>


			</p>
			<div class="tea_center_pro">
				<c:choose>
					<c:when test="${column.type==1}">
						<c:forEach items="${articleList }" var="article">
							<div class="job_name">
								<h3 class="job_list">${article.title }</h3>
							</div>
							<div class="job_details" style="display:none;">${article.content }</div>
						</c:forEach>
						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
					</c:when>
					<c:when test="${column.id eq '5IBAAG3OPSO16'}">
						<div class="tea_center_pro">
							<div class="job_box">
								<c:forEach items="${articleList }" var="article" >
									<div class="job_name">
										<h3 class="job_list">${article.title}</h3>
									</div>
									<div class="job_details" style="display:none;">
										<h3>${article.content }</h3>
									</div>
								</c:forEach>
							</div>
							<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
						</div>
					</c:when>
					<c:when test="${column.id eq '5IBAAG3KO4F13'}">
						<div class="tea_center_pro">
							<div class="job_box">
								<c:forEach items="${articleList }" var="article" begin="0" end="0">

									<br />
									<div>${article.content }</h3>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:when>
					<c:when test="${column.id eq '5IBAAG3MCPK14'}">
						<div class="tea_center_pro">
							<div class="job_box">
								<c:forEach items="${articleList }" var="article" begin="0" end="0">
									<br />
									<div>${article.content }</h3>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:when>
					<c:when test="${column.id eq '5IBAAG3NSZH15'}">
						<div class="tea_center_pro">
							<div class="job_box">
								<c:forEach items="${articleList }" var="article" begin="0" end="0">

									<br />
									<img src="${article.pic}" alt="茶园风光" />
									<div>${article.content }</h3>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:when>
					<c:when test="${column.id eq '5IBAAG3P14417'}">
						<div class="tea_center_pro">
							<div class="job_box">
								<c:forEach items="${articleList }" var="article">
									<br />
									<%-- <img src="${article.pic}" alt="联系我们" /> --%>
									<div>${article.content }</h3>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:when>
					
					
						<c:when test="${column.id eq '5IBAAG3VFUG1C'}">
						<c:forEach items="${articleList }" var="fenlei">
							<div class="tea_center_pro01">
								<a href="${basePath }do/lzcc/detail?articleId=${fenlei.articleId}">
									<img src="${fenlei.pic }" style="width=50px,height=40px">
								</a>
								<div class="tea_center_pro_intro">${fenlei.content }</div>
							</div>
						</c:forEach>

						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
					</c:when>
					
					
					<c:when test="${column.id eq '5IBAAG3V1HZ1B' or column.pid eq '5IBAAG3V1HZ1B'}">
						<c:forEach items="${articleList }" var="fenlei">
							<div class="tea_center_pro01">
								<a href="${basePath }do/lzcc/detail?articleId=${fenlei.articleId}">
									<img src="${fenlei.pic }" style="width=50px,height=40px">
								</a>
								<div class="tea_center_pro_intro">${fenlei.content }</div>
							</div>
						</c:forEach>

						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
					</c:when>
					<c:when test="${column.id eq '5IBAE63A60D00'}">
						<c:forEach items="${maojian }" var="mj">
							<div class="tea_center_pro01">
								<a href="${basePath }do/lzcc/detail?articleId=${mj.articleId}">
									<img src="${mj.pic }" style="width=50px,height=40px"/>
								</a>
								<div class="tea_center_pro_intro">${mj.content }</div>
							</div>
						</c:forEach>
						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
					</c:when>
						<c:when test="${column.id eq '5IBAE63BR7601'}">
						<c:forEach items="${xinyanghong }" var="xyh">
							<div class="tea_center_pro01">
								<a href="${basePath }do/lzcc/detail?articleId=${xyh.articleId}">
									<img src="${xyh.pic }" style="width=50px,height=40px"/>
								</a>
								<div class="tea_center_pro_intro">${xyh.content }</div>
							</div>
						</c:forEach>
						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
					</c:when>
						<c:when test="${column.id eq '5IBAE63EK6O02'}">
						<c:forEach items="${puercha }" var="prc">
							<div class="tea_center_pro01">
								<a href="${basePath }do/lzcc/detail?articleId=${prc.articleId}">
									<img src="${prc.pic }" style="width=50px,height=40px"/>
								</a>
								<div class="tea_center_pro_intro">${prc.content }</div>
							</div>
						</c:forEach>
						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
					</c:when>
					
						<c:when test="${column.id eq '5IBAE63G12J03'}">
						<c:forEach items="${wulomg }" var="wl">
							<div class="tea_center_pro01">
								<a href="${basePath }do/lzcc/detail?articleId=${wl.articleId}">
									<img src="${wl.pic }" style="width=50px,height=40px"/>
								</a>
								<div class="tea_center_pro_intro">${wl.content }</div>
							</div>
						</c:forEach>
						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
					</c:when>
					
						<c:when test="${column.id eq '5IBAE63GLEQ04'}">
						<c:forEach items="${xiaozhong }" var="xz">
							<div class="tea_center_pro01">
								<a href="${basePath }do/lzcc/detail?articleId=${xz.articleId}">
									<img src="${xz.pic }" style="width=50px,height=40px"/>
								</a>
								<div class="tea_center_pro_intro">${xz.content }</div>
							</div>
						</c:forEach>
						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
					</c:when>
						<c:when test="${column.id eq '5IBAE63H4VP05'}">
						<c:forEach items="${baicha }" var="bc">
							<div class="tea_center_pro01">
								<a href="${basePath }do/lzcc/detail?articleId=${bc.articleId}">
									<img src="${bc.pic }" style="width=50px,height=40px"/>
								</a>
								<div class="tea_center_pro_intro">${bc.content }</div>
							</div>
						</c:forEach>
						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>
					</c:when>
					
					
					<c:when test="${article.articleId eq '5IBACBMFHRP17'}">

						<div class="tea_center_pro01">
							<a href="${basePath }do/lzcc/detail?articleId=${fenlei.articleId}">
								<img src="${fenlei.pic }" style="width=50px,height=40px">
							</a>
							<div class="tea_center_pro_intro"></div>
						</div>
					</c:when>

					<c:otherwise>
						<li>
							<c:forEach items="${articleList }" var="article">
								<div class="job_name job_name1">

									<a onclick="location.href='${basePath }do/lzcc/detail?articleId=${article.articleId}'">
										<p class="job_list">${article.title}<span style="padding-right:30px;
	float: right;">发布时间：${fn:substring(article.createTime,0,10)}</span>
										</p>
									</a>
								</div>
							</c:forEach>
						</li>
						<div id="zj_pager" style="clear:both">
							<div id="pageNum" class="pageNum" style="display: none"></div>
							<div id="pageNav" class="sabrosus"></div>
						</div>

					</c:otherwise>
				</c:choose>

			</div>

			<!--/*上下收展*/-->
			<script>
				$(".job_name").toggle(function() {
					$(this).next(".job_details").slideDown(300);
				}, function() {
					$(this).next(".job_details").slideUp(300);
				})
			</script>
		</div>
	</div>
	</div>
	<div id="footer">
		<div class="footer">
			<div class="footer">

				<p class="blogroll">
					<span>友情链接：</span>
					<a href="http://tea.zjol.com.cn/cycd/" target="_blank">中国茶网</a>
					<a href="http://news.t0001.com/" target="_blank">第一茶叶网</a>
					<a href="http://www.woitea.net/" target="_blank">爱茶网</a>
					<a href="http://www.fjtea.cn/" target="_blank">福建茶叶网</a>
					<a href="http://www.sanzui.com/bbs/forum.php" target="_blank">三醉斋</a>
					<a href="http://www.teaer.cn/show/" target="_blank">心鑫茶艺</a>
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
			marqueen('roll').start(50/*速度默认100*/);
		</script>
</body>
</html>
