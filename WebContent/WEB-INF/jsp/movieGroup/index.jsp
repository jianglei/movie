<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="/js/function.js"></script>
<script src="/js/tab.js" type="text/javascript"></script>

<base href="." target="_blank"/>
</head>
<body>
<div class="top">
  <div class="divauto c9c9c9c">
    <p class="left">欢迎来到迅播影院，我们为大家免费提供好看的电影</p>
    <p class="right">
    <a href="javascript:void(0);" onclick="setHome(this,'http://www.xhzxgc.com')">设为首页</a> - 
    <a href="javascript:void(0)" onclick="addFavorite('http://www.xhzxgc.com','迅播影院');">加入收藏</a>
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
			<div>
				<h2 class="logo">
					<a href="http://www.xhzxgc.com" title="迅播电影观看" target="_self"><img
						src="/images/logo.png" width="230" height="60" class="png" />
					</a>
				</h2>
			</div>
			<div class="search">
				<div class="search_box">
					<form name="formsearch" id="formsearch" action="/movieGroup/index"
						method="post" target="_self">
						<input type="text" class="search_text" name="name"
							value="请输入您要搜索的电影/电视剧"
							onblur="if(this.value=='')this.value='请输入您要搜索的电影/电视剧'"
							onfocus="if(this.value=='请输入您要搜索的电影/电视剧')this.value=''" /> <input
							type="submit" class="search_btn" value="" />
					</form>
				</div>
			</div>
		</div>
		<div class="nav">
			<span class="nav_l"></span> <span class="nav_m">
				<div class="menu">
					<ul>
						<li><a
							href="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/迅播影院-Gvod Player-Gvod电影-迅雷电影下载.htm"
							target="_self">首页</a>
						</li>

						<li><a href="http://www.2tu.cc/GvodHtml/15.html"
							target="_self">电影</a>
						</li>

						<li><a href="http://www.2tu.cc/GvodHtml/16.html"
							target="_self">电视</a>
						</li>

						<li><a href="http://www.2tu.cc/GvodHtml/7.html"
							target="_self">动画片</a>
						</li>

						<li><a href="http://www.2tu.cc/GvodHtml/8.html"
							target="_self">综艺片</a>
						</li>


					</ul>
					<!-- <div class="nav_db" id="play_history"><a target="_self" href="javascript:;" id="skf" onmouseover="showTop();" class="blink">点播记录</a></div> -->
				</div>
				<p>
					<b>热门分类：</b><a href="http://www.2tu.cc/GvodHtml/1.html"
						target="_self">动作片</a><a href="http://www.2tu.cc/GvodHtml/2.html"
						target="_self">喜剧片</a><a href="http://www.2tu.cc/GvodHtml/3.html"
						target="_self">爱情片</a><a href="http://www.2tu.cc/GvodHtml/4.html"
						target="_self">科幻片</a><a href="http://www.2tu.cc/GvodHtml/5.html"
						target="_self">恐怖片</a><a href="http://www.2tu.cc/GvodHtml/6.html"
						target="_self">剧情片</a><a href="http://www.2tu.cc/GvodHtml/13.html"
						target="_self">战争片</a><a href="http://www.2tu.cc/GvodHtml/14.html"
						target="_self">其它片</a><a href="http://www.2tu.cc/GvodHtml/9.html"
						target="_self">国产剧</a><a href="http://www.2tu.cc/GvodHtml/10.html"
						target="_self">港台剧</a><a href="http://www.2tu.cc/GvodHtml/11.html"
						target="_self">欧美剧</a><a href="http://www.2tu.cc/GvodHtml/12.html"
						target="_self">日韩剧</a><a href="http://www.2tu.cc/GvodHtml/17.html"
						target="_self">新马泰</a>
				</p> </span> <span class="nav_r"></span>
		</div>
		<!-- 
		  <div onmouseout="hideTop();" onmouseover="showTop();" id="List_top_2" style="display: block;" class="histroydrop">
		    <div class="histroydrop_tt"> <span onclick="setEmpty()">清空观看记录</span>|<span onclick="hideTop()" class="red">关闭</span> </div>
		    <div class="histroydrop_con" id="histroydrop_con"><div id="view_history" class="view_history"><center>您的观看历史为空。</center></div></div>
		  </div> 
   		-->
	</div>
	<div class="ad960">
<script type="text/javascript">
alimama_pid="mm_34345258_3449822_11198405";
alimama_width=950;
alimama_height=90;
</script>
<script src="http://a.alimama.cn/inf.js" type="text/javascript"></script>
</div>
<div class="divauto" id="main">

	
	<div class="section1 mt10">
    	<div class="news_top">
            <div class="tab tabw2">
            	<div class="tit">
                	<h2 class="ico1 png">推荐电影</h2>
                    <span class="c0071bc"><a href="http://www.2tu.cc/GvodHtml/1.html">动作片</a>|<a href="http://www.2tu.cc/GvodHtml/2.html">喜剧片</a>|<a href="http://www.2tu.cc/GvodHtml/3.html">爱情片</a>|<a href="http://www.2tu.cc/GvodHtml/4.html">科幻片</a>|<a href="http://www.2tu.cc/GvodHtml/5.html">恐怖片</a>|<a href="http://www.2tu.cc/GvodHtml/6.html">剧情片</a>|<a href="http://www.2tu.cc/GvodHtml/13.html">战争片</a>|<a href="http://www.2tu.cc/GvodHtml/14.html">其它片</a>|<a href="http://www.2tu.cc/GvodHtml/15.html" target="_self">全部</a>
                    </span>
                </div> 
                <ul class="pic plist1">
                <c:forEach items="${dianying.result}" var="movie" varStatus="status">
      			<li>
                      <a href="http://www.2tu.cc/Html/GP13392.html" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13392.html">${fn:substring(movie.name, 0, 7)}</a></b></p>
                      <em>${fn:substring(movie.last_update_time, 0, 4)}-${movie.category}</em>
                  </li>
              </c:forEach>
              
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
    <div class="ad960">

<script type="text/javascript"> 
alimama_pid="mm_34345258_3449822_11208808"; 
alimama_width=950; 
alimama_height=90; 
</script> 
<script src="http://a.alimama.cn/inf.js" type="text/javascript"> 
</script>
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
      				<c:forEach items="${dianshiju.result}" var="movie" varStatus="status">
      			<li>
                      <a href="http://www.2tu.cc/Html/GP13392.html" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13392.html">${fn:substring(movie.name, 0, 7)}</a></b></p>
                      <em>${fn:substring(movie.last_update_time, 0, 4)}-${movie.category}</em>
                  </li>
              </c:forEach>
      
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
    <div class="ad960">
<script type="text/javascript"> 
alimama_pid="mm_34345258_3449822_11208810"; 
alimama_width=950; 
alimama_height=90; 
</script> 
<script src="http://a.alimama.cn/inf.js" type="text/javascript"> 
</script>
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
      				<c:forEach items="${dongman.result}" var="movie" varStatus="status">
		      			<li>
		                      <a href="http://www.2tu.cc/Html/GP13392.html" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
		                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13392.html">${fn:substring(movie.name, 0, 7)}</a></b></p>
		                      <em>${fn:substring(movie.last_update_time, 0, 4)}-${movie.category}</em>
		                  </li>
		             </c:forEach>
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
    <div class="ad960">
<script type="text/javascript"> 
alimama_pid="mm_34345258_3449822_11208811"; 
alimama_width=950; 
alimama_height=90; 
</script> 
<script src="http://a.alimama.cn/inf.js" type="text/javascript"> 
</script>

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
      				<c:forEach items="${zongyi.result}" var="movie" varStatus="status">
		      			<li>
		                      <a href="http://www.2tu.cc/Html/GP13392.html" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
		                      <p><b class="c0071bc"><a href="http://www.2tu.cc/Html/GP13392.html">${fn:substring(movie.name, 0, 7)}</a></b></p>
		                      <em>${fn:substring(movie.last_update_time, 0, 4)}-${movie.category}</em>
		                  </li>
		             </c:forEach>
      
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
    
</div>

 <div id="footer">
    <div class="copyright divauto">
        <p>免责声明:本站所有视频均来自互联网收集而来，版权归原创者所有，如果侵犯了你的权益，请通知我们，我们会及时删除侵权内容，谢谢合作！<br>联系信箱：jlrunning@163.com&nbsp;<a href="http://www.cnzz.com/stat/website.php?web_id=1908774" target="_blank" title="站长统计">站长统计</a>


<p></p>
    </div>
</div>



</body></html>