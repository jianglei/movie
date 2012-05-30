<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<link rel="shortcut icon" href="http://www.umeng.com/images/favicon.ico" />

<div id="header">
	<div class="con"
		stype="width: 950px;
margin: 0 auto;
margin-top: 0px;
margin-right: auto;
margin-bottom: 0px;
margin-left: auto;">

<c:set var="logoUrl" value="http://www.umeng.com/images/image_updated/logo.png" scope="session" />
<c:set var="logoName" value="广告管理平台" scope="session" />
<c:if test="${user != null and user.logoUrl !=null and user.logoUrl!=''}" >
	<c:set var="logoUrl" value="${user.logoUrl} " scope="session" />
</c:if>
<c:if test="${user != null and user.logoName !=null and user.logoName!=''}" >
	<c:set var="logoName" value="${user.logoName} " scope="session" />
</c:if>
                <div class="logo" style=" position: relative;padding:0;line-height:50px;">
			<img class="logoUrl_show" src='<c:out value="${logoUrl}"/>' style="width:120px; height:45px;"/>
			<a href="http://ufp.umeng.com"
				style="height: 32px;  display: inline-block; margin-left:10px;">
			${logoName}广告管理平台
				</a>
		</div>

		<c:if test="${user != null}">
			<div class="opr">
				欢迎您, ${user.nickname }  | <a href="/login/logout">退出</a>
			</div>
		</c:if>

	</div>
</div>
<div id="menu">
	<div class="con">
		<ul>
			<li class="position" id="adslot_tab"><a href="/adslot">广告位</a><span></span></li>
			<li class="ad" id="ad_tab"><a href="/ad">广告</a><span></span></li>
			<li class="report" id="report_tab"><a href="/report">报表</a><span></span></li>
			<li class="settings" id="settings_tab"><a href="/settings">设置</a><span></span></li>
		</ul>
	</div>
</div>
<div class="submenu" id="submenu_adslot" style="display: none">
	<div class="con">
		<ul>
<!--             ***********不要删除span标签****************-->
			<li id="adslot_tab_adslot" class=""><a href="/adslot">广告位管理</a><span></span></li>
			<li id="adslot_tab_app" class=""><a href="/app" style="background-image: none">应用</a><span></span></li>
			<!-- <li id="adslot_tab_overview" class=""><a href="#" style="background-image:none">广告位使用情况</a></li> -->
		</ul>
	</div>
</div>
<div class="submenu" id="submenu_ad" style="display: none">
	<div class="con">
		<ul>
			<li id="ad_tab_ad" class=""><a href="/ad">广告库</a><span></span></li>
			<li id="ad_tab_adorder" class=""><a href="/adorder" style="background-image: none">订单</a><span></span></li>
		</ul>
	</div>
</div>
<div class="submenu" id="submenu_report" style="display: none">
	<div class="con">
		<ul>
			<li><a href="/report">整体效果</a><span></span></li>
			<li><a href="/report/adslot">广告位报表</a><span></span></li>
			<li><a href="/report/adorder">订单报表</a><span></span></li>
			<li><a href="/report/ad">广告报表</a><span></span></li>
			<li><a href="/report/app" style="background-image: none">应用报表</a><span></span></li>
		</ul>
	</div>
</div>
<div class="submenu" id="submenu_settings" style="display: none">
	<div class="con">
		<ul>
			<!-- 			<li><a href="#">管理员设置</a></li> -->
			<li><a href="/settings">账户信息</a><span></span></li>
			<li><a href="/settings/edit_logo" >设置Logo</a><span></span></li>
			<li><a href="/settings/edit_black_list" style="background-image: none">修改黑名单</a><span></span></li> 
			<!-- 			<li><a href="#">历史操作</a><span></span></li> -->
			<!-- 			<li><a href="#" style="background-image:none">广告主效果查询账户</a></li> -->
		</ul>
	</div>
</div>

