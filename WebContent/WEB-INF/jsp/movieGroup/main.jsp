<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link rel="stylesheet" type="text/css" href="/css/main.css" />
<script type="text/javascript" src="/js/jquery.pager.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<title>免费高清电影订阅中心</title>
<meta name="keywords" content="moiverss,dyrss,高清电影,高清电影订阅中心,高清快播" />
<meta name="description" content="免费高清电影订阅网站，致力于提供优质的高清电影快播资源播放地址。" />
</head>
<body>

	<div class="container singlerow">
		<div class="row mg15">
			<div class="two columns show-on-desktops" id="main_nav">
				<dl class="nice tabs vertical" id="MainMenu">
					<dd class="home">
						<a href="/simple" id="browser" title="精简方式浏览">精简 →</a><a
							class="active" href="/main">首页</a>
					</dd>
					<dd class="news">
						<a href="/category">分类浏览</a>
					</dd>
					<dd class="news">
						<a href="/search">搜索</a>
					</dd>
					<dd class="news">
						<a href="/detail?id=240047">蓝光高清合辑</a>
					</dd>
					<dd class="news">
						<a href="/about">关于我们</a>
					</dd>
					<dd class="news">
						<a href="http://weibo.com/528880906" target="_blank">关注我们</a>
					</dd>
				</dl>
			</div>
			<div class="seven columns maincol" id="posts">
		
				
				<c:if test="${page.totalItems == 0 }">
				<h3>呀！怎么还木有影片！~</h3>
				</c:if>
				<c:if test="${page.totalItems != 0 }">
					 <div style="width: 95px; margin-top: -20px; position: absolute; background: none repeat scroll 0pt 0pt rgb(0, 166, 252); border-top: medium none; border-right: medium none; font-weight: lighter; border-radius: 0pt 0pt 3px 3px; right: 30px;" class="alert-box">
					<a style="color:#fff" class="" href="/movieGroup/index?o=year">按发行时间排序</a>
					</div>
					<c:forEach items="${page.result}" var="movie" varStatus="status">
						<article class="posts">
						<h3>
							<a target="_blank" href="/movieGroup/detail?id=${movie.id}">${movie.name}</a>
						</h3>
						<div class="postmeta">
							更新于 <strong>${fn:substring(movie.last_update_time, 0, 10)}</strong>
		
						</div>
						<div></div>
						<div class="row">
							<div class="three columns">
								<div class="thumbnail hide-on-phones">
									<a href="/movieGroup/detail?id=${movie.id}"
										title="${movie.name}"><img height="200"
										width="150"
										src="${movie.pic_url}"
										alt="${movie.name}"/></a>
								</div>
							</div>
							<div class="nine columns">
								<p>
									<strong>影片名称: </strong>${movie.name}
								</p>
								<p>
									<strong>影片栏目: </strong>${movie.category}
								</p>
								<p>
									<strong>影片地区: </strong>${movie.area}
								</p>
								<p>
									<strong>影片主演: </strong>${movie.actor_list}
								</p>
								<p>
									<strong>影片导演: </strong>${movie.director}
								</p>
								<p>
									<strong>发行日期: </strong>
									<c:if test="${movie.release_time != 0 }">
										${movie.release_time}
									</c:if>
								</p>
								<div class="download">
									<div class="readmore">
										<a class="button" href="/play?id=${movie.id}" target="play">在线播放</a>
									</div>
								</div>
							</div>
						</div>
						<hr>
						</article>
					</c:forEach>
				</c:if>
				${pageNo}
				${totalPages}
				<div class="">
					<script>
					  $(document).ready(function() {
				            $("#pager").pager({ pagenumber: ${page.pageNo}, pagecount: ${page.totalPages}, buttonClickCallback: PageClick });
				        });
					  PageClick = function(pageclickednumber) {
							location="/movieGroup/index?pageNo="+pageclickednumber;
						}
					</script>
					<div id="pager" ></div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>