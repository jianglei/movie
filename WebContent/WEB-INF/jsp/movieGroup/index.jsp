<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script src="/js/main.js" async="" mod_name="inf-main"></script>

<title>迅播影院-Gvod Player-Gvod电影-迅雷电影</title>
<meta name="keywords" content="迅播影院,3D电影,3D电视片源,高清电影下载,迅播播放器,迅雷看看,免费Gvod电影,kankan,百度影音"/>
<meta name="description" content="迅播影院提供最新最快电影资讯，迅雷看看电影点播以及迅雷电影下载，同时提供百度影音高速观看！"/>
<meta name="robots" content="index,follow"/>
<meta name="googlebot" content="index,follow"/>
<link href="/css/index.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<script>var sitePath=''</script>
<script src="/js/common.js"></script>
<script src="/js/function.js"></script>
<script src="/js/tab.js" type="text/javascript"></script>

<base href="." target="_blank"/>
</head>
<body>
<div class="top">
  <div class="divauto c9c9c9c">
    <p class="left">欢迎来到迅播影院，我们为大家免费提供好看的电影</p>
    <p class="right">
    <a href="javascript:void(0);" onclick="setHome(this,&#39;http://www.xhzxgc.com&#39;)">设为首页</a> - 
    <a href="javascript:void(0)" onclick="addFavorite(&#39;http://www.xhzxgc.com&#39;,&#39;迅播影院&#39;);">加入收藏</a> - 
    <!--
    <a href="http://www.2tu.cc/TvHot.html">电视热播</a> - <a href="http://www.2tu.cc/gbook.asp">留言求片</a> - 
    <a href="http://www.2tu.cc/google.xml">GOOGLE地图</a> - <a href="http://www.2tu.cc/baidu.xml">百度地图</a> - 
    <a href="http://www.2tu.cc/rss.xml">RSS订阅</a> - <a href="http://www.2tu.cc/shorturl.asp">
    <font color="red">将迅播放到桌面</font></a> </p>
      -->
  </div>
</div>
<div id="header">
  <div class="divauto">
    <div><h2 class="logo"><a href="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/迅播影院-Gvod Player-Gvod电影-迅雷电影下载.htm" title="迅雷电影下载" target="_self"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/logo.png" width="230" height="60" class="png"></a></h2></div>
    <div class="search">
      <div class="search_box">
        <form name="formsearch" id="formsearch" action="http://www.2tu.cc/search.asp" method="post" target="_self">
          <input type="text" class="search_text" name="searchword" value="请输入您要搜索的电影/电视剧" onblur="if(this.value==&#39;&#39;)this.value=&#39;请输入您要搜索的电影/电视剧&#39;" onfocus="if(this.value==&#39;请输入您要搜索的电影/电视剧&#39;)this.value=&#39;&#39;">
          <input type="submit" class="search_btn" value="">
        </form>
      </div>
      <p class="c555"><em>热门：</em><script type="text/javascript" language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/hot-all.js"></script><a href="http://www.2tu.cc/search.asp?searchword=%C9%FA%BB%AF%CE%A3%BB%FA">生化危机5</a><a href="http://www.2tu.cc/search.asp?searchword=%B5%FD%D3%B0%D6%D8%D6%D8">谍影重重4</a><a href="http://www.2tu.cc/search.asp?searchword=%D0%D0%CA%AC%D7%DF%C8%E2">行尸走肉</a><a href="http://www.2tu.cc/search.asp?searchword=%B4%F3%CC%AB%BC%E0">大太监</a><a href="http://www.2tu.cc/search.asp?searchword=%C6%C6%B8%AA%B3%C1%D6%DB">破釜沉舟</a>
</p>
    </div>
  </div>
  <div class="nav"> <span class="nav_l"></span> <span class="nav_m">
    <div class="menu">
      <ul>
        <li><a href="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/迅播影院-Gvod Player-Gvod电影-迅雷电影下载.htm" target="_self">首页</a></li>
        
        <li><a href="http://www.2tu.cc/GvodHtml/15.html" target="_self">电影</a></li>
        
        <li><a href="http://www.2tu.cc/GvodHtml/16.html" target="_self">电视</a></li>
        
        <li><a href="http://www.2tu.cc/GvodHtml/7.html" target="_self">动画片</a></li>
        
        <li><a href="http://www.2tu.cc/GvodHtml/8.html" target="_self">综艺片</a></li>
        
        <li><a href="http://www.2tu.cc/GvodHtml/21.html" target="_self">3D电影</a></li>
        
        <li><a href="http://www.2tu.cc/GvodHtml/18.html" target="_self">影视预告</a></li>
        
      </ul>
      <div class="nav_db" id="play_history"><a target="_self" href="javascript:;" id="skf" onmouseover="showTop();" class="blink">点播记录</a></div>
    </div>
    <p><b>热门分类：</b><a href="http://www.2tu.cc/GvodHtml/1.html" target="_self">动作片</a><a href="http://www.2tu.cc/GvodHtml/2.html" target="_self">喜剧片</a><a href="http://www.2tu.cc/GvodHtml/3.html" target="_self">爱情片</a><a href="http://www.2tu.cc/GvodHtml/4.html" target="_self">科幻片</a><a href="http://www.2tu.cc/GvodHtml/5.html" target="_self">恐怖片</a><a href="http://www.2tu.cc/GvodHtml/6.html" target="_self">剧情片</a><a href="http://www.2tu.cc/GvodHtml/13.html" target="_self">战争片</a><a href="http://www.2tu.cc/GvodHtml/14.html" target="_self">其它片</a><a href="http://www.2tu.cc/GvodHtml/9.html" target="_self">国产剧</a><a href="http://www.2tu.cc/GvodHtml/10.html" target="_self">港台剧</a><a href="http://www.2tu.cc/GvodHtml/11.html" target="_self">欧美剧</a><a href="http://www.2tu.cc/GvodHtml/12.html" target="_self">日韩剧</a><a href="http://www.2tu.cc/GvodHtml/17.html" target="_self">新马泰</a></p>
    </span> <span class="nav_r"></span> </div>
  <div onmouseout="hideTop();" onmouseover="showTop();" id="List_top_2" style="display: block;" class="histroydrop">
    <div class="histroydrop_tt"> <span onclick="setEmpty()">清空观看记录</span>|<span onclick="hideTop()" class="red">关闭</span> </div>
    <div class="histroydrop_con" id="histroydrop_con"><div id="view_history" class="view_history"><center>您的观看历史为空2。</center></div></div>
  </div><script type="text/javascript" language="javascript">ingetCookie();</script> 
</div>
<div class="ad960" id="index01"><script type="text/javascript" language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/index01.js"></script><iframe src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/8200_1426.htm" width="960" height="90" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>
</div>
<div class="divauto" id="main">
	<div class="section mt10">
    	<div class="news_top">
        	<div class="box list1 left">
            	<div class="tit">
                	<h1>今日更新47部</h1>
                    <a href="http://www.2tu.cc/newlist.html">最近更新&gt;&gt;</a>
                </div>
                <ul> 
        <li><p><a href="http://www.2tu.cc/Html/GP13777.html">12-13赛季NB..</a>12-26</p>12-26</li>
      
        <li><p><a href="http://www.2tu.cc/Html/GP12101.html">军火女王[24]</a>军火贩卖</p>12-26</li>
      
        <li><p><a href="http://www.2tu.cc/Html/GP14072.html">内线前传[30]</a>谍战悬疑</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13879.html">天下女人心[25]</a>爱情励志</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13392.html">飓风营救2</a>1280高清</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14109.html">周恩来万隆之行</a>1280高清</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14032.html">战役行动：使命召唤/战..</a>1280高清</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14090.html">超时空战警3D/新特警..</a>1024高清</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13842.html">偶滴神啊</a>1280超清</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14103.html">迅电流光/命中雷霆</a>1280高清</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP289.html">红场特警</a>1280高清</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14108.html">给你一千万</a>1280高清</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14034.html">法网狙击[6]</a>时装法律</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14033.html">幸福摩天轮[6]</a>时装温情</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP2835.html">娱乐百分百</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP3068.html">型男大主厨</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP2836.html">完全娱乐</a>12-25</p>12-26</li>
      
                </ul>
            </div>
            
            <div class="tab tabw1">
            	<div class="tit">
                	<h1>热播推荐</h1>
                    <ul>
                        <li id="ph1" onmouseover="setTab(&#39;ph&#39;,1,4)" class=""><a href="javascript:void(0);">电影</a></li>
                        <li id="ph2" onmouseover="setTab(&#39;ph&#39;,2,4)" class=""><a href="javascript:void(0);">电视剧</a></li>
                        <li id="ph3" onmouseover="setTab(&#39;ph&#39;,3,4)" class="active"><a href="javascript:void(0);">综艺</a></li>
                        <li id="ph4" onmouseover="setTab(&#39;ph&#39;,4,4)" class=""><a href="javascript:void(0);">动漫</a></li>
                    </ul>
                </div>
                
                <ul class="pic plist1" id="con_ph_1" style="display: none;">
                  <li>
                      <a href="http://www.2tu.cc/Html/GP13319.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120923155837206.jpg" alt="诈欺游戏：再生/诈欺游戏剧场版2/欺诈游戏"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13319.html">诈欺游戏：再..</a></b></p>
                      <em>2012-剧情片</em>
                  </li>
          
                  <li>
                      <a href="http://www.2tu.cc/Html/GP13934.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121129171031026.jpg" alt="人在囧途之泰囧/人再囧途之泰囧"><em class="v">贺岁喜剧</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13934.html">人在囧途之泰..</a></b></p>
                      <em>2012-喜剧片</em>
                  </li>
          
                  <li>
                      <a href="http://www.2tu.cc/Html/GP13399.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121221125140939.jpg" alt="寒战"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13399.html">寒战</a></b></p>
                      <em>2012-动作片</em>
                  </li>
          
                  <li>
                      <a href="http://www.2tu.cc/Html/GP13442.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121222223942519.jpg" alt="一路向西"><em class="v">1280超清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13442.html">一路向西</a></b></p>
                      <em>2012-爱情片</em>
                  </li>
          
                  <li>
                      <a href="http://www.2tu.cc/Html/GP10219.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120716232446726.jpg" alt="Z-108弃城"><em class="v">1280超清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP10219.html">Z-108弃城</a></b></p>
                      <em>2011-恐怖片</em>
                  </li>
          
                  <li>
                      <a href="http://www.2tu.cc/Html/GP13392.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121014152849954.jpg" alt="飓风营救2"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13392.html">飓风营救2</a></b></p>
                      <em>2012-动作片</em>
                  </li>
          
                  <li>
                      <a href="http://www.2tu.cc/Html/GP13249.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120913112124962.jpg" alt="谍影重重4：伯恩的遗产"><em class="v">1280超清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13249.html">谍影重重4：..</a></b></p>
                      <em>2012-动作片</em>
                  </li>
          
                  <li>
                      <a href="http://www.2tu.cc/Html/GP13702.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121030170505750.jpg" alt="太极2：英雄崛起"><em class="v">1280超清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13702.html">太极2：英雄..</a></b></p>
                      <em>2012-动作片</em>
                  </li>
          
                </ul>
                
                <ul class="pic plist1" id="con_ph_2" style="display: none;"> 
          		<li>
                      <a href="http://www.2tu.cc/Html/GP13541.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121014114808346.jpg" alt="勇士闯魔城2[11]"><em class="v">冒险喜剧</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13541.html">勇士闯魔..[11]</a></b></p>
                      <em>2012-日韩剧</em>
                  </li>
          
          		<li>
                      <a href="http://www.2tu.cc/Html/GP12370.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121221105106358.jpg" alt="爱回家[158]"><em class="v">家族情感</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12370.html">爱回家[158]</a></b></p>
                      <em>2012-港台剧</em>
                  </li>
          
          		<li>
                      <a href="http://www.2tu.cc/Html/GP13861.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121119213046099.jpg" alt="隋唐英雄(张卫健版)[60]"><em class="v">年度巨制</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13861.html">隋唐英雄..[60]</a></b></p>
                      <em>2012-国产剧</em>
                  </li>
          
          		<li>
                      <a href="http://www.2tu.cc/Html/GP12453.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120527091636069.jpg" alt="绅士的品格[19]"><em class="v">国语版</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12453.html">绅士的品格[19]</a></b></p>
                      <em>2012-日韩剧</em>
                  </li>
          
          		<li>
                      <a href="http://www.2tu.cc/Html/GP13111.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120819201604043.jpg" alt="麻辣女兵"><em class="v">青春励志</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13111.html">麻辣女兵</a></b></p>
                      <em>2012-国产剧</em>
                  </li>
          
          		<li>
                      <a href="http://www.2tu.cc/Html/GP13767.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121108094205141.jpg" alt="想你[13]"><em class="v">传统爱情</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13767.html">想你[13]</a></b></p>
                      <em>2012-日韩剧</em>
                  </li>
          
          		<li>
                      <a href="http://www.2tu.cc/Html/GP12181.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120517213317330.jpg" alt="仁显王后的男人[16]"><em class="v">浪漫笑剧</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12181.html">仁显王后..[16]</a></b></p>
                      <em>2012-日韩剧</em>
                  </li>
          
          		<li>
                      <a href="http://www.2tu.cc/Html/GP10458.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20110918150526496.jpg" alt="廉政英雄[65]"><em class="v">警匪偶像</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP10458.html">廉政英雄[65]</a></b></p>
                      <em>2011-港台剧</em>
                  </li>
          
                </ul>
                
                <ul class="pic plist1" id="con_ph_3" style="display: block;">
          			<li>
                      <a href="http://www.2tu.cc/Html/GP6628.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/6628.jpg" alt="非诚勿扰[综艺]"><em class="v">12-23</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP6628.html">非诚勿扰[综..</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP3386.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/3386.jpg" alt="天天向上"><em class="v">12-21</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP3386.html">天天向上</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP2785.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2785.jpg" alt="快乐大本营"><em class="v">12-22</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2785.html">快乐大本营</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP2828.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2828.jpg" alt="康熙来了"><em class="v">12-25</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2828.html">康熙来了</a></b></p>
                      <em>2010-综艺片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP5187.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/5187.jpg" alt="全能住宅改造王"><em class="v">12-25</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP5187.html">全能住宅改造王</a></b></p>
                      <em>2010-综艺片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP2835.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2835.jpg" alt="娱乐百分百"><em class="v">12-25</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2835.html">娱乐百分百</a></b></p>
                      <em>2010-综艺片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP13777.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121108135814807.jpg" alt="12-13赛季NBA常规赛"><em class="v">12-26</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13777.html">12-13赛..</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP13243.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120912095014719.jpg" alt="美国之声第三季[32]"><em class="v">真人选秀</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13243.html">美国之声第三季</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
          
                </ul>
                
                <ul class="pic plist1" id="con_ph_4" style="display: none;">
          			<li>
                      <a href="http://www.2tu.cc/Html/GP2770.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2770.jpg" alt="火影忍者[514]"><em class="v">日语TV</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2770.html">火影忍者[514]</a></b></p>
                      <em>2004-动画片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP2760.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2760.jpg" alt="海贼王[578]"><em class="v">日语TV</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2760.html">海贼王[578]</a></b></p>
                      <em>2008-动画片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP1660.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/1660.jpg" alt="名侦探柯南[690]"><em class="v">长篇连载</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP1660.html">名侦探柯南[690]</a></b></p>
                      <em>2010-动画片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP5175.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/5175.jpg" alt="妖精的尾巴[162]"><em class="v">日语TV</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP5175.html">妖精的尾巴[162]</a></b></p>
                      <em>2009-动画片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP13381.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120930224956258.jpg" alt="秦时明月4之万里长城[32]"><em class="v">武侠3D</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13381.html">秦时明月..[32]</a></b></p>
                      <em>2012-动画片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP10616.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20111002131734808.jpg" alt="全职猎人2011[60]"><em class="v">十月新番</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP10616.html">全职猎人..[60]</a></b></p>
                      <em>2011-动画片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP12814.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120708091537872.jpg" alt="刀剑神域"><em class="v">小说改编</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12814.html">刀剑神域</a></b></p>
                      <em>2012-动画片</em>
                  </li>
          
          			<li>
                      <a href="http://www.2tu.cc/Html/GP9196.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/9196.jpg" alt="美食的俘虏[87]"><em class="v">四月新番</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP9196.html">美食的俘虏[87]</a></b></p>
                      <em>2011-动画片</em>
                  </li>
          
                </ul>
            </div>
            
            <div class="box list2 right">
            	<div class="tit">
                	<h1>影片预告</h1>
                    <a href="http://www.2tu.cc/GvodHtml/18.html">更多预告&gt;&gt;</a>
                </div>
                <ul><li><p><a href="http://www.2tu.cc/Html/GP14106.html">楚汉传奇</a>传奇大剧</p>12-25</li><li><p><a href="http://www.2tu.cc/Html/GP11576.html">特种部队2：复..</a>动作科幻</p>12-19</li><li><p><a href="http://www.2tu.cc/Html/GP1056.html">星际迷航12暗..</a>科幻冒险</p>12-07</li><li><p><a href="http://www.2tu.cc/Html/GP13937.html">凶间雪山</a>惊悚悬疑</p>11-29</li><li><p><a href="http://www.2tu.cc/Html/GP13936.html">南泥湾</a>战争历史</p>11-29</li><li><p><a href="http://www.2tu.cc/Html/GP13935.html">异度迷局/七夜..</a>惊悚悬疑</p>11-29</li><li><p><a href="http://www.2tu.cc/Html/GP13933.html">笑过2012</a>灾难剧情</p>11-29</li><li><p><a href="http://www.2tu.cc/Html/GP13932.html">我老公不靠谱</a>爱情喜剧</p>11-29</li><li><p><a href="http://www.2tu.cc/Html/GP13897.html">大漠枪神</a>枪战片</p>11-23</li><li><p><a href="http://www.2tu.cc/Html/GP13856.html">宿主</a>科幻惊悚</p>11-19</li><li><p><a href="http://www.2tu.cc/Html/GP13855.html">致命契约</a>科幻悬疑</p>11-19</li><li><p><a href="http://www.2tu.cc/Html/GP13854.html">惊天魔盗团</a>魔幻侠盗</p>11-19</li><li><p><a href="http://www.2tu.cc/Html/GP13853.html">魔境仙踪/奥兹..</a>魔幻冒险</p>11-19</li><li><p><a href="http://www.2tu.cc/Html/GP13793.html">僵尸世界大战</a>惊悚血腥</p>11-10</li>
                </ul>
            </div>
        </div>
        <div class="bot"></div>
    </div>
    <div class="ad960"><script type="text/javascript" language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/index02.js"></script><script type="text/javascript">
alimama_pid="mm_16021781_2735106_9750910";
alimama_width=950;
alimama_height=90;
</script>
<script src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/inf.js" type="text/javascript"></script><iframe style="width: 950px; height: 90px; border: 0px;" id="tanx-a-mm_16021781_2735106_9750910" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/alimama(1).htm"></iframe>
</div>
	<div class="section1 mt10">
    	<div class="news_top">
            <div class="tab tabw2">
            	<div class="tit">
                	<h2 class="ico1 png">推荐电影</h2>
                    <span class="c0071bc"><a href="http://www.2tu.cc/GvodHtml/1.html">动作片</a>|<a href="http://www.2tu.cc/GvodHtml/2.html">喜剧片</a>|<a href="http://www.2tu.cc/GvodHtml/3.html">爱情片</a>|<a href="http://www.2tu.cc/GvodHtml/4.html">科幻片</a>|<a href="http://www.2tu.cc/GvodHtml/5.html">恐怖片</a>|<a href="http://www.2tu.cc/GvodHtml/6.html">剧情片</a>|<a href="http://www.2tu.cc/GvodHtml/13.html">战争片</a>|<a href="http://www.2tu.cc/GvodHtml/14.html">其它片</a>|<a href="http://www.2tu.cc/GvodHtml/15.html" target="_self">全部</a>
                    </span>
                </div> 
                <ul class="pic plist1">
      			<li>
                      <a href="http://www.2tu.cc/Html/GP13392.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121014152849954.jpg" alt="飓风营救2"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13392.html">飓风营救2</a></b></p>
                      <em>2012-动作片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP14032.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121216225347955.jpg" alt="战役行动：使命召唤/战争力量"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14032.html">战役行动：使..</a></b></p>
                      <em>2012-战争片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP14090.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121223011836500.jpg" alt="超时空战警3D/新特警判官"><em class="v">1024高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14090.html">超时空战警3..</a></b></p>
                      <em>2012-科幻片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP14103.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121225122500165.jpg" alt="迅电流光/命中雷霆"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14103.html">迅电流光/命..</a></b></p>
                      <em>2012-喜剧片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP14096.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121224114712629.jpg" alt="法国猫王"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14096.html">法国猫王</a></b></p>
                      <em>2012-剧情片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP13652.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121027164537987.jpg" alt="R2B.返回基地/R2B: 回到基地/壮志冲天"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13652.html">R2B.返回..</a></b></p>
                      <em>2012-动作片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP13289.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/201209191236371000.jpg" alt="大海啸之鲨口逃生/大海啸之鲨口逃生/诱饵/鲨口逃生"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13289.html">大海啸之鲨口..</a></b></p>
                      <em>2012-动作片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP14093.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121223172537767.jpg" alt="潘多拉的宝剑"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14093.html">潘多拉的宝剑</a></b></p>
                      <em>2012-喜剧片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP14040.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121218112324878.jpg" alt="爱情必修学/文科恋曲/校缘心曲"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14040.html">爱情必修学/..</a></b></p>
                      <em>2011-喜剧片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP13702.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121030170505750.jpg" alt="太极2：英雄崛起"><em class="v">1280超清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13702.html">太极2：英雄..</a></b></p>
                      <em>2012-动作片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP14041.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121218112641753.jpg" alt="伴我梦游"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14041.html">伴我梦游</a></b></p>
                      <em>2012-喜剧片</em>
                  </li>
              
      			<li>
                      <a href="http://www.2tu.cc/Html/GP13442.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121222223942519.jpg" alt="一路向西"><em class="v">1280超清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13442.html">一路向西</a></b></p>
                      <em>2012-爱情片</em>
                  </li>
              
                </ul>
            </div>
            
            <div class="box list2 right">
            	<div class="tit">
                	<h1>电影更新</h1>
                </div>
                <ul>
                <li><p><a href="http://www.2tu.cc/Html/GP13392.html">飓风营救2</a>1280高清</p>12-26</li>
        
                <li><p><a href="http://www.2tu.cc/Html/GP14109.html">周恩来万隆之行</a>1280高清</p>12-26</li>
        
                <li><p><a href="http://www.2tu.cc/Html/GP14032.html">战役行动：使命召唤/战..</a>1280高清</p>12-26</li>
        
        
        	<li><p><a href="http://www.2tu.cc/Html/GP14090.html">超时空战警3D/新特警..</a>1024高清</p>12-26</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP13842.html">偶滴神啊</a>1280超清</p>12-26</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP14103.html">迅电流光/命中雷霆</a>1280高清</p>12-26</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP289.html">红场特警</a>1280高清</p>12-26</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP14108.html">给你一千万</a>1280高清</p>12-26</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP1015.html">卫斯理传奇</a>1280高清</p>12-25</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP14102.html">逃脱/飚客找麻烦</a>1280高清</p>12-25</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP81.html">龙行天下/92黄飞鸿之..</a>1280高清</p>12-25</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP1475.html">僵尸家族</a>1280高清</p>12-25</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP687.html">黄昏/神秘群岛/山穷水尽</a>1024超清</p>12-25</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP14096.html">法国猫王</a>1280高清</p>12-25</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP14101.html">芭比</a>1024超清</p>12-25</li>
        
        	<li><p><a href="http://www.2tu.cc/Html/GP13652.html">R2B.返回基地/R2..</a>1280高清</p>12-25</li>
        
                </ul>
            </div>
        </div>
        <div class="bot"></div>
    </div>
    <div class="ad960"><script type="text/javascript" language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/index03.js"></script><script type="text/javascript">
/*960*60，创建于2012-11-8*/
var cpro_id = "u1117260";
</script>
<script src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/c.js" type="text/javascript"></script><script type="text/javascript" charset="utf-8" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/ecom"></script><div style="display:none">-</div> <iframe id="cproIframe1" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/uijs.htm" width="960" height="60" align="center,center" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" allowtransparency="true"></iframe>
</div>
    <div class="section1 mt10">
    	<div class="news_top">
            <div class="tab tabw2">
            	<div class="tit">
                	<h2 class="ico2 png">推荐电视剧</h2>
                    <span class="c0071bc"><a href="http://www.2tu.cc/GvodHtml/9.html" target="_self">国产剧</a>|<a href="http://www.2tu.cc/GvodHtml/10.html" target="_self">港台剧</a>|<a href="http://www.2tu.cc/GvodHtml/11.html" target="_self">欧美剧</a>|<a href="http://www.2tu.cc/GvodHtml/12.html" target="_self">日韩剧</a>|<a href="http://www.2tu.cc/GvodHtml/17.html" target="_self">新马泰</a>|<a href="http://www.2tu.cc/GvodHtml/16.html" target="_self">全部</a>
                    </span>
                </div> 
                <ul class="pic plist1">
      				<li>
                      <a href="http://www.2tu.cc/Html/GP14034.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121221104757068.jpg" alt="法网狙击[6]"><em class="v">时装法律</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14034.html">法网狙击</a></b></p>
                      <em>2012-港台剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP14033.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121221105255648.jpg" alt="幸福摩天轮[6]"><em class="v">时装温情</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14033.html">幸福摩天轮</a></b></p>
                      <em>2012-港台剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP12370.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121221105106358.jpg" alt="爱回家[158]"><em class="v">家族情感</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12370.html">爱回家</a></b></p>
                      <em>2012-港台剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP12453.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120527091636069.jpg" alt="绅士的品格[19]"><em class="v">国语版</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12453.html">绅士的品格</a></b></p>
                      <em>2012-日韩剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP14055.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121219223542879.jpg" alt="嬉皮侠心/皮五传奇[42]"><em class="v">古装搞笑</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14055.html">嬉皮侠心/皮..</a></b></p>
                      <em>2012-国产剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP13861.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121119213046099.jpg" alt="隋唐英雄(张卫健版)[60]"><em class="v">年度巨制</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13861.html">隋唐英雄(张..</a></b></p>
                      <em>2012-国产剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP14023.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121216120614220.jpg" alt="山河恋之美人无泪[24]"><em class="v">古装历史</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14023.html">山河恋之美人..</a></b></p>
                      <em>2012-国产剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP12643.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20110625110158228.jpg" alt="火线警告/非常突然/黑名单第六季[18]"><em class="v">警匪罪案</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12643.html">火线警告/非..</a></b></p>
                      <em>2012-欧美剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP12893.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120717203048658.jpg" alt="都市侠盗第五季[14]"><em class="v">剧情罪案</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12893.html">都市侠盗第五季</a></b></p>
                      <em>2012-欧美剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP13670.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121028000705661.jpg" alt="刷新3+7[9]"><em class="v">单元剧</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13670.html">刷新3+7</a></b></p>
                      <em>2012-国产剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP14094.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121223205839587.jpg" alt="刑名师爷[30]"><em class="v">悬疑侦探</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14094.html">刑名师爷</a></b></p>
                      <em>2012-国产剧</em>
                  </li>
      
      				<li>
                      <a href="http://www.2tu.cc/Html/GP13740.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121103232327947.jpg" alt="艾米加油"><em class="v">都市职场</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13740.html">艾米加油</a></b></p>
                      <em>2012-国产剧</em>
                  </li>
      
                </ul>
            </div>
            
            <div class="box list2 right">
            	<div class="tit">
                	<h1>电视剧更新</h1>
                </div>
                <ul> 
      <li><p><a href="http://www.2tu.cc/Html/GP14072.html">内线前传[30]</a>谍战悬疑</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13879.html">天下女人心[25]</a>爱情励志</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14034.html">法网狙击[6]</a>时装法律</p>12-26</li>
      
      
      <li><p><a href="http://www.2tu.cc/Html/GP14033.html">幸福摩天轮[6]</a>时装温情</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13480.html">妈妈是什么[26]</a>家庭喜剧</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13870.html">吴子龙走吧[25]</a>家庭情感</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13556.html">可能爱过你[51]</a>家庭情感</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13765.html">加油金先生[36]</a>励志奋斗</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP12337.html">爱情啊爱情啊[166]</a>韩语TV</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP12370.html">爱回家[158]</a>家族情感</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13993.html">约会规则第四季[6]</a>生活喜剧</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13720.html">真爱趁现在[33]</a>爱情偶像</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13695.html">我们可以结婚吗[17]</a>生活爱情</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13755.html">电视剧之王[16]</a>生活喜剧</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13401.html">马医[26]</a>人物改编</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13956.html">学校2013[8]</a>校园时代</p>12-26</li>
      
                </ul>
            </div>
        </div>
        <div class="bot"></div>
    </div>
    <div class="ad960"><script type="text/javascript" language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/index04.js"></script><script type="text/javascript">
/*960*60-2，创建于2012-11-8*/
var cpro_id = "u1117263";
</script>
<script src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/c.js" type="text/javascript"></script><script type="text/javascript" charset="utf-8" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/ecom(1)"></script><div style="display:none">-</div> <iframe id="cproIframe2" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/uijs(1).htm" width="960" height="60" align="center,center" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" allowtransparency="true"></iframe>
</div>
    <div class="section1 mt10">
    	<div class="news_top">
            <div class="tab tabw2">
            	<div class="tit">
                	<h2 class="ico3 png">推荐动漫</h2>
                    <span class="c0071bc"><a href="http://www.2tu.cc/GvodHtml/7.html" target="_self">全部</a>
                    </span>
                </div> 
                <ul class="pic plist1"> 
      <li>
                      <a href="http://www.2tu.cc/Html/GP12098.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120410155717519.jpg" alt="终极蜘蛛侠[26]"><em class="v">英语TV</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12098.html">终极蜘蛛侠</a></b></p>
                      <em>2012-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP12021.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120403203233209.jpg" alt="火影忍者小李外传[39]"><em class="v">水影忍者</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP12021.html">火影忍者小李..</a></b></p>
                      <em>2012-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP6823.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/6823.jpg" alt="魔术快斗[11]"><em class="v">新番连载</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP6823.html">魔术快斗</a></b></p>
                      <em>2010-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP13782.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121109111745880.jpg" alt="头文字D第五季[4]"><em class="v">山路飙车</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13782.html">头文字D第五季</a></b></p>
                      <em>2012-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP13454.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121007204139428.jpg" alt="魔笛MAGI[12]"><em class="v">同名漫画</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13454.html">魔笛MAGI</a></b></p>
                      <em>2012-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP2760.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2760.jpg" alt="海贼王[578]"><em class="v">日语TV</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2760.html">海贼王</a></b></p>
                      <em>2008-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP1660.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/1660.jpg" alt="名侦探柯南[690]"><em class="v">长篇连载</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP1660.html">名侦探柯南</a></b></p>
                      <em>2010-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP13828.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121116232555221.jpg" alt="超凡的诺曼/通灵男孩诺曼"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13828.html">超凡的诺曼/..</a></b></p>
                      <em>2012-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP5175.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/5175.jpg" alt="妖精的尾巴[162]"><em class="v">日语TV</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP5175.html">妖精的尾巴</a></b></p>
                      <em>2009-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP13734.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121103104430510.jpg" alt="功夫熊猫：盖世传奇(电视版)第二季[10]"><em class="v">动漫巨作</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13734.html">功夫熊猫：盖..</a></b></p>
                      <em>2012-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP14070.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121221124300125.jpg" alt="赞比西亚大冒险"><em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP14070.html">赞比西亚大冒险</a></b></p>
                      <em>2012-动画片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP13735.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121103105003935.jpg" alt="忍者神龟2012第一季[12]"><em class="v">翻新重制</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13735.html">忍者神龟20..</a></b></p>
                      <em>2012-动画片</em>
                  </li>
      
                </ul>
            </div>
            
            <div class="box list2 right">
            	<div class="tit">
                	<h1>动漫更新</h1>
                </div>
                <ul>
      <li><p><a href="http://www.2tu.cc/Html/GP12101.html">军火女王[24]</a>军火贩卖</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP12098.html">终极蜘蛛侠[26]</a>英语TV</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13847.html">凶鬼恶灵之动画篇/邪恶..[6]</a>魔幻惊悚</p>12-26</li>
      
      
      <li><p><a href="http://www.2tu.cc/Html/GP12021.html">火影忍者小李外传[39]</a>水影忍者</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13982.html">百变机兽之元气星魂[21]</a>热血科幻</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13878.html">雷速登闪电冲线第三部[25]</a>赛车世界</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14053.html">幻变精灵之蛋糕甜心[11]</a>科幻搞笑</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP12810.html">王者天下[30]</a>热血少儿</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13462.html">樱花庄的宠物女孩[12]</a>小说改编</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13403.html">元气少女缘结神</a>日语TV</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13020.html">今天的明日香[18]</a>TV动画</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13558.html">泡芙小姐第四季[11]</a>都市情感</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13215.html">邻座的怪同学</a>青春故事</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14100.html">岁月的童话/回忆点点滴..</a>1280高清</p>12-24</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP6823.html">魔术快斗[11]</a>新番连载</p>12-24</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP14097.html">AKB0048 Nex..[1]</a>日语动漫</p>12-24</li>
      
                </ul>
            </div>
        </div>
        <div class="bot"></div>
    </div>
    <div class="ad960"><script type="text/javascript" language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/index05.js"></script><script type="text/javascript">
/*960*60-3，创建于2012-11-8*/
var cpro_id = "u1117267";
</script>
<script src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/c.js" type="text/javascript"></script><script type="text/javascript" charset="utf-8" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/ecom(2)"></script><div style="display:none">-</div> <iframe id="cproIframe3" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/uijs(2).htm" width="960" height="60" align="center,center" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" allowtransparency="true"></iframe>
</div>
    <div class="section1 mt10">
    	<div class="news_top">
            <div class="tab tabw2">
            	<div class="tit">
                	<h2 class="ico4 png">推荐综艺</h2>
                    <span class="c0071bc"><a href="http://www.2tu.cc/GvodHtml/8.html" target="_self">全部</a>
                    </span>
                </div> 
                <ul class="pic plist1"> 
      <li>
                      <a href="http://www.2tu.cc/Html/GP13777.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121108135814807.jpg" alt="12-13赛季NBA常规赛"><em class="v">12-26</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13777.html">12-13赛..</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP2835.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2835.jpg" alt="娱乐百分百"><em class="v">12-25</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2835.html">娱乐百分百</a></b></p>
                      <em>2010-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP2781.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2781.jpg" alt="女人我最大"><em class="v">12-24</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2781.html">女人我最大</a></b></p>
                      <em>2010-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP2828.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2828.jpg" alt="康熙来了"><em class="v">12-25</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2828.html">康熙来了</a></b></p>
                      <em>2010-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP2829.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2829.jpg" alt="国光帮帮忙"><em class="v">12-25</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2829.html">国光帮帮忙</a></b></p>
                      <em>2010-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP6113.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120704001552093.jpg" alt="我们约会吧+"><em class="v">12-25</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP6113.html">我们约会吧+</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP13188.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20120903203340790.jpg" alt="谁与争锋[综艺]"><em class="v">12-24</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13188.html">谁与争锋[综..</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP2870.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2870.jpg" alt="天才冲冲冲"><em class="v">12-22</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2870.html">天才冲冲冲</a></b></p>
                      <em>2010-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP6628.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/6628.jpg" alt="非诚勿扰[综艺]"><em class="v">12-23</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP6628.html">非诚勿扰[综..</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP13846.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121118233906042.jpg" alt="中国达人秀第4季"><em class="v">12-23</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13846.html">中国达人秀第..</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP2785.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/2785.jpg" alt="快乐大本营"><em class="v">12-22</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP2785.html">快乐大本营</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
      
      <li>
                      <a href="http://www.2tu.cc/Html/GP13732.html" class="i"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/20121102231028008.jpg" alt="女人如歌"><em class="v">12-21</em></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13732.html">女人如歌</a></b></p>
                      <em>2012-综艺片</em>
                  </li>
      
                </ul>
            </div>
            
            <div class="box list2 right">
            	<div class="tit">
                	<h1>综艺更新</h1>
                </div>
                <ul>
      <li><p><a href="http://www.2tu.cc/Html/GP13777.html">12-13赛季NBA常..</a>12-26</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP2835.html">娱乐百分百</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP3068.html">型男大主厨</a>12-25</p>12-26</li>
      
      
      <li><p><a href="http://www.2tu.cc/Html/GP2836.html">完全娱乐</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP5180.html">食尚玩家</a>12-24</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP2781.html">女人我最大</a>12-24</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP2828.html">康熙来了</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP2829.html">国光帮帮忙</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP2831.html">大学生了没</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP5187.html">全能住宅改造王</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP7489.html">SS小燕之夜</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP5778.html">TVBS哈新闻</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP6113.html">我们约会吧+</a>12-25</p>12-26</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP13188.html">谁与争锋[综艺]</a>12-24</p>12-25</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP4478.html">猪哥会社</a>12-22</p>12-24</li>
      
      <li><p><a href="http://www.2tu.cc/Html/GP2870.html">天才冲冲冲</a>12-22</p>12-24</li>
      
                  
                </ul>
            </div>
        </div>
        <div class="bot"></div>
    </div>
    
    <div class="section1 mt10">
    	<div class="news_top c555">
            <div class="tab1 ">
                <h2 class="ico5 png">最新资讯</h2>
                <ul>
                <li class="png"><a href="http://www.2tu.cc/html/article/index3.html" class="i">网站搜索、评论等功能再次升级</a></li>
                <li class="png"><a href="http://www.2tu.cc/html/article/index2.html" class="i">迅雷看看点播功能升级完成</a></li>
                <li class="png"><a href="http://www.2tu.cc/html/article/index1.html" class="i">迅播影院正式改版完成</a></li>
                </ul>
            </div>
            
            <div class="box2 right">
                <h1>推荐资讯</h1>
                <ul>
                <li><a href="http://www.2tu.cc/html/article/index3.html" class="i">网站搜索、评论等功能再次升级</a></li>
                <li><a href="http://www.2tu.cc/html/article/index2.html" class="i">迅雷看看点播功能升级完成</a></li>
                <li><a href="http://www.2tu.cc/html/article/index1.html" class="i">迅播影院正式改版完成</a></li>
                </ul>
            </div>
        </div>
        <div class="bot"></div>
    </div>
    <div class="ad960"><script type="text/javascript" language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/index06.js"></script><script type="text/javascript"> 
alimama_pid="mm_16021781_2735106_11029810"; 
alimama_width=950; 
alimama_height=90; 
</script> 
<script src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/inf.js" type="text/javascript"> 
</script><iframe style="width: 950px; height: 90px; border: 0px;" id="tanx-a-mm_16021781_2735106_11029810" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/alimama.htm"></iframe>
</div>
    <div class="section1 mt10">
    	<div class="news_top1 c555">
        	<div class="tit">
            	<h1>友情链接</h1>
                <em>欢迎高质量网站与本站友情链接，联系www#xunbo.cc(#改@)</em>
            </div>
            <div class="link"><a href="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/迅播影院-Gvod Player-Gvod电影-迅雷电影下载.htm">迅雷下载</a><a href="http://www.77vcd.com/">最新电视剧</a><a href="http://dy.hao123.cn/">电影天堂</a><a href="http://www.gvod.tv/">迅播资源中心</a>
            </div>
        </div>
        <div class="bot"></div>
    </div>
</div>

 <div id="footer">
    <div class="copyright divauto">
        <p>免责声明:本站所有视频均来自互联网收集而来，版权归原创者所有，如果侵犯了你的权益，请通知我们，我们会及时删除侵权内容，谢谢合作！<br>联系信箱：www@xunbo.cc&nbsp;<script language="javascript" type="text/javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/tongji.js"></script><script src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/stat.php" language="JavaScript" charset="gb2312"></script><script src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/cnzz_core.php" charset="utf-8" type="text/javascript"></script><a href="http://www.cnzz.com/stat/website.php?web_id=1908774" target="_blank" title="站长统计">站长统计</a>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F56d36efc1d26755102490a81574f120d' type='text/javascript'%3E%3C/script%3E"));
</script><script src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/h.js" type="text/javascript"></script><a href="http://tongji.baidu.com/hm-web/welcome/ico?s=56d36efc1d26755102490a81574f120d" target="_blank">网站统计</a>
<script type="text/javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/ad.js"></script><iframe src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/item.htm" width="1" height="1" scrolling="auto" frameborder="0"></iframe>
</p><div style="display:none;">
<script language="javascript" type="text/javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/15156812.js"></script><a href="http://www.51.la/?15156812" target="_blank" title="51.la 专业、免费、强健的访问统计">网站统计</a>
<img style="width:0px;height:0px" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/go.asp">
<noscript>&lt;a href="http://www.51.la/?15156812" target="_blank"&gt;&lt;img alt="&amp;#x6211;&amp;#x8981;&amp;#x5566;&amp;#x514D;&amp;#x8D39;&amp;#x7EDF;&amp;#x8BA1;" src="http://img.users.51.la/15156812.asp" style="border:none" /&gt;&lt;/a&gt;</noscript>
</div>

<p></p>
    </div>
</div>

<script type="text/javascript" language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/index-pf.js"></script><script language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/fl.php"></script><div id="ShowDIV" style="position:fixed; z-index: 2147483646;bottom:0;right:0;overflow:hidden;"><div id="fCDOT" style="z-index: 1999999; top: 3px; left: 234px; width: 25px; height: 13px; position: absolute;" onclick="ED_CloseIt()"><img style="width:25px; height:13px;" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/close.gif"></div><div id="fdiv" style="margin:0;padding:0;width:270px;height:200px;"><div style="position:absolute;z-index:1"><a href="http://p.144gg.com/s/2/989/0.html?uid=308200&ext=NWEgICAgICAgICAgNTVTUFhSUFBMUVFSWVJMUlZTVExQTFNUVVJUTFFSVE5RUldOUVhXTlVQTFJQUVJRUlJWUVRUVFBW" target="_blank" onclick="ED_ClickIt(&quot;NWEgICAgICAgICAgNTVTUFhSUFBMUVFSWVJMUlZTVExQTFNUVVJUTFFSVE5RUldOUVhXTlVQTFJQUVJRUlJWUVRUVFBW&quot;)"><img src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/dot.gif" width="270" height="200" border="0"></a></div><script>WRITEFF('http://p.144gg.com/b/2/989/270X200.swf?uid=308200','270','200');</script><object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,24,0" width="270" height="200" align="middle"><param name="movie" value="http://p.144gg.com/b/2/989/270X200.swf?uid=308200"><param name="quality" value="high"><param name="wmode" value="transparent"><embed pluginspage="http://www.macromedia.com/go/getflashplayer" width="270" height="200" align="middle" type="application/x-shockwave-flash" src="http://p.144gg.com/b/2/989/270X200.swf?uid=308200" quality="high" wmode="transparent"></object></div></div>


<div style="display:none;" id="bindex01"></div>

<script language="javascript" type="text/javascript">
<!--
	document.getElementById("index01").innerHTML = document.getElementById("bindex01").innerHTML;
	document.getElementById("bindex01").innerHTML = "";
-->
</script>

</body></html>