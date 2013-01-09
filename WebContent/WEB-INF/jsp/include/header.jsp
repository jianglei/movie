<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script src="/js/main.js"></script>

<title>迅播影院-Gvod Player-Gvod电影-迅雷电影</title>
<meta name="keywords" content="迅播影院,3D电影,3D电视片源,高清电影下载,迅播播放器,迅雷看看,免费Gvod电影,kankan,百度影音"/>
<meta name="description" content="迅播影院提供最新最快电影资讯，迅雷看看电影点播以及迅雷电影下载，同时提供百度影音高速观看！"/>
<meta name="robots" content="index,follow"/>
<meta name="googlebot" content="index,follow"/>
<link href="/css/index.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<script>var sitePath='';</script>
<script src="/js/common.js"></script>
<script src="/js/function.js" ></script>
<script src="/js/tab.js" type="text/javascript"></script>

<base href="." target="_blank"/>
</head>
<body>
<div class="top">
  <div class="divauto c9c9c9c">
    <p class="left">欢迎来到迅播影院，我们为大家免费提供好看的电影</p>
    <p class="right">
    <a href="javascript:void(0);" onclick="setHome(this,'http://www.xhzxgc.com')">设为首页</a> - 
    <a href="javascript:void(0)" onclick="addFavorite('http://www.xhzxgc.com','迅播影院');">加入收藏</a> - 
    <!--
    <a href="http://www.2tu.cc/TvHot.html">电视热播</a> - <a href="http://www.2tu.cc/gbook.asp">留言求片</a> - 
    <a href="http://www.2tu.cc/google.xml">GOOGLE地图</a> - <a href="http://www.2tu.cc/baidu.xml">百度地图</a> - 
    <a href="http://www.2tu.cc/rss.xml">RSS订阅</a> - <a href="http://www.2tu.cc/shorturl.asp">
    <font color="red">将迅播放到桌面</font></a> 
      -->
      </p>
  </div>
</div>
<div id="header">
  <div class="divauto">
    <div><h2 class="logo"><a href="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/迅播影院-Gvod Player-Gvod电影-迅雷电影下载.htm" title="迅雷电影下载" target="_self"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/logo.png" width="230" height="60" class="png"/></a></h2></div>
    <div class="search">
      <div class="search_box">
        <form name="formsearch" id="formsearch" action="/movieGroup/index" method="post" target="_self">
          <input type="text" class="search_text" name="name" value="请输入您要搜索的电影/电视剧" onblur="if(this.value=='')this.value='请输入您要搜索的电影/电视剧'" onfocus="if(this.value=='请输入您要搜索的电影/电视剧')this.value=''"/>
          <input type="submit" class="search_btn" value=""/>
        </form>
      </div>
    </div>
  </div>
  <div class="nav"> <span class="nav_l"></span> 
  <span class="nav_m">
    <div class="menu">
      <ul>
        <li><a href="/movieGroup/main" target="_self">首页</a></li>
        
        <li><a href="/movieGroup/category" target="_self">电影</a></li>
        
        <li><a href="/movieGroup/category" target="_self">电视</a></li>
        
        <li><a href="/movieGroup/category" target="_self">动画片</a></li>
        
        <li><a href="/movieGroup/category" target="_self">综艺片</a></li>
        
        
      </ul>
      <!-- <div class="nav_db" id="play_history"><a target="_self" href="javascript:;" id="skf" onmouseover="showTop();" class="blink">点播记录</a></div> -->
    </div>
    <p>
    	<b>热门分类:</b>
    	<a href="/movieGroup/category?category=8" target="_self">恐怖片</a>
    	<a href="/movieGroup/category?category=9" target="_self">剧情片</a>
    	<a href="/movieGroup/category?category=10" target="_self">动作片</a>
    	<a href="/movieGroup/category?category=11" target="_self">喜剧片</a>
    	<a href="/movieGroup/category?category=12" target="_self">纪录片</a>
    	<a href="/movieGroup/category?category=13" target="_self">爱情片</a>
    	<a href="/movieGroup/category?category=14" target="_self">科幻片</a>
    	<a href="/movieGroup/category?category=15" target="_self">战争片</a>
    	<a href="/movieGroup/category?category=0" target="_self">综艺节目</a>
    	<a href="/movieGroup/category?category=7" target="_self">卡通动漫</a>
    	<a href="/movieGroup/category?category=1" target="_self">香港剧</a>
    	<a href="/movieGroup/category?category=2" target="_self">欧美剧</a>
    	<a href="/movieGroup/category?category=3" target="_self">国产剧</a>
    	<a href="/movieGroup/category?category=4" target="_self">韩国剧</a>
    	<a href="/movieGroup/category?category=5" target="_self">日本剧</a>
    	<a href="/movieGroup/category?category=6" target="_self">海外剧</a>
    </p>
    </span> 
    <span class="nav_r"></span> 
  </div>
  <!-- 
  <div onmouseout="hideTop();" onmouseover="showTop();" id="List_top_2" style="display: block;" class="histroydrop">
    <div class="histroydrop_tt"> <span onclick="setEmpty()">清空观看记录</span>|<span onclick="hideTop()" class="red">关闭</span> </div>
    <div class="histroydrop_con" id="histroydrop_con"><div id="view_history" class="view_history"><center>您的观看历史为空。</center></div></div>
  </div> 
   -->
</div>