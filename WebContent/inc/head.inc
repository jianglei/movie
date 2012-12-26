<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int wb_h=799;
%>
<nav class="show-on-phones"> <a href="/movieGroup/index" style=""
		class="button black nice">返回首页</a></nav>
	<div id="krBar" class="container hide-on-phones">
		<div class="row" id="kb_inner">
			<div id="topmenu" class="eight columns">
				
			</div>
			<div class="four columns">
				<div class="right" style="margin-top: 4px">
				<a data-title="@wyl_leon" class="small button radius" rel="weibo" href="http://weibo.com/528880906" target="_blank">+ 关注</a>
					<a data-title="/rss"
						href="/rss" class="small red button radius"
						rel="shareit" target="_blank">+ 订阅</a>
				</div>
			</div>
		</div>
	</div>
	<div id="header" role="main" class="container">
		<header class="row"> <hgroup class="two columns">
		<h1>
			<a rel="home" href="/movieGroup/index"><span
				style="color: #ff0000;">Moive</span><span style="color: #ffa500;">R</span><span
				style="color: #0000ff;">SS</span></a>
		</h1>
		<h5 class="subheader">高清电影订阅中心</h5>
		</hgroup>
		<div class="three columns fr">
			<form class="nice hide-on-phones" action="/movieGroup/index"
				id="searchform" method="get" role="search">
				<input type="text" placeholder="搜索" autocomplete="off"
					x-webkit-speech="" class="small nice fr input-text oversize"
					style="margin-left: 40px; margin-top: 30px;width: 280px;" value="${name}" name="name" id="name" />
			</form>
		</div>
		<div class="seven columns show-on-desktops fr">
			这里放广告
		</div>
		</header>
	</div>