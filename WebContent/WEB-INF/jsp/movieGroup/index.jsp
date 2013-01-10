<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script src="/js/main.js"></script>

<%@include file="../include/header.jsp"%>

<div class="divauto" id="main">
	<!-- 
	<div class="section mt10">
    	<div class="news_top">
        	<div class="box list1 left">
            	<div class="tit">
                	<h1>最近更新</h1>
                </div>
                <ul> 
			        <c:forEach items="${new.result}" var="movie" varStatus="status">
			        	<c:if test="${status.index < 14 }">
			        	<li><p><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a>${movie.category }</p>${fn:substring(movie.last_update_time, 5, 9)}</li>
			        	</c:if>
			        </c:forEach>
                </ul>
            </div>
            
            <div class="tab tabw1">
            	<div class="tit">
                	<h1>热播推荐</h1>
                    <ul>
                        <li id="ph1" onmouseover="setTab('ph',1,4)" class=""><a href="javascript:void(0);">电影</a></li>
                        <li id="ph2" onmouseover="setTab('ph',2,4)" class=""><a href="javascript:void(0);">电视剧</a></li>
                        <li id="ph3" onmouseover="setTab('ph',3,4)" class="active"><a href="javascript:void(0);">综艺</a></li>
                        <li id="ph4" onmouseover="setTab('ph',4,4)" class=""><a href="javascript:void(0);">动漫</a></li>
                    </ul>
                </div>
                
                <ul class="pic plist1" id="con_ph_1" style="display: none;">
                <c:forEach items="${dianying.result}" var="movie" varStatus="status">
                  <li>
                      <a href="/movie/movieDetail?id=${movie.id }" class="i"><img src="${movie.pic_url }" alt="${movie.name }"><!--  <em class="v">1280高清</em></a>
                      <p><b class="c0071bc"><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></b></p>
                      <em>${fn:substring(movie.last_update_time, 0, 4)}-${movie.category}</em>
                  </li>
          		</c:forEach>
                  
          
                </ul>
                
                <ul class="pic plist1" id="con_ph_2" style="display: none;"> 
          		<c:forEach items="${dianshiju.result}" var="movie" varStatus="status">
                  <li>
                      <a href="/movie/movieDetail?id=${movie.id }" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
                      <p><b class="c0071bc"><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></b></p>
                      <em>${fn:substring(movie.last_update_time, 0, 4)}-${movie.category}</em>
                  </li>
          		</c:forEach>
          
                </ul>
                
                <ul class="pic plist1" id="con_ph_3" style="display: block;">
          			<c:forEach items="${zongyi.result}" var="movie" varStatus="status">
                  <li>
                      <a href="/movie/movieDetail?id=${movie.id }" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
                      <p><b class="c0071bc"><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></b></p>
                      <em>${fn:substring(movie.last_update_time, 0, 4)}-${movie.category}</em>
                  </li>
          		</c:forEach>
          
                </ul>
                
                <ul class="pic plist1" id="con_ph_4" style="display: none;">
          			<c:forEach items="${dongman.result}" var="movie" varStatus="status">
                  <li>
                      <a href="/movie/movieDetail?id=${movie.id }" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
                      <p><b class="c0071bc"><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></b></p>
                      <em>${fn:substring(movie.last_update_time, 0, 4)}-${movie.category}</em>
                  </li>
          		</c:forEach>
          
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
    
    <div class="ad960">
		<script type="text/javascript">
		alimama_pid="mm_34345258_3449822_11208811";
		alimama_width=950;
		alimama_height=90;
		</script>
		<script src="http://a.alimama.cn/inf.js" type="text/javascript"></script>
<!--
<script src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/inf.js" type="text/javascript"></script>
<iframe style="width: 950px; height: 90px; border: 0px;" id="tanx-a-mm_16021781_2735106_9750910" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/alimama(1).htm"></iframe>
 
</div> -->
	<div class="section1 mt10">
    	<div class="news_top">
            <div class="tab tabw2">
            	<div class="tit">
                	<h2 class="ico1 png">推荐电影</h2>
                    <span class="c0071bc">
                    <a href="/movie/category?category=8" target="_self">恐怖片</a>|
			    	<a href="/movie/category?category=9" target="_self">剧情片</a>|
			    	<a href="/movie/category?category=10" target="_self">动作片</a>|
			    	<a href="/movie/category?category=11" target="_self">喜剧片</a>|
			    	<a href="/movie/category?category=12" target="_self">纪录片</a>|
			    	<a href="/movie/category?category=13" target="_self">爱情片</a>|
			    	<a href="/movie/category?category=14" target="_self">科幻片</a>|
			    	<a href="/movie/category?category=15" target="_self">战争片</a>|
                    
                    </span>
                </div> 
                <ul class="pic plist1">
      			<c:forEach items="${dianying.result}" var="movie" varStatus="status">
      			  <li>
                      <a href="/movie/movieDetail?id=${movie.id }" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
                      
                      <p><b class="c0071bc"><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></b></p>
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
                <c:forEach items="${dianying_new.result}" var="movie" varStatus="status">
	                <c:if test="${status.index < 14 }">
	      			  <li>
	                      <p><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></p>${fn:substring(movie.last_update_time, 0, 4)}
	                  </li>
	                  </c:if>
              	</c:forEach>
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
		<script src="http://a.alimama.cn/inf.js" type="text/javascript"></script>
	</div>
    <div class="section1 mt10">
    	<div class="news_top">
            <div class="tab tabw2">
            	<div class="tit">
                	<h2 class="ico2 png">推荐电视剧</h2>
                    <span class="c0071bc">
                    	<a href="/movie/category?category=1" target="_self">香港剧</a>|
				    	<a href="/movie/category?category=2" target="_self">欧美剧</a>|
				    	<a href="/movie/category?category=3" target="_self">国产剧</a>|
				    	<a href="/movie/category?category=4" target="_self">韩国剧</a>|
				    	<a href="/movie/category?category=5" target="_self">日本剧</a>|
				    	<a href="/movie/category?category=6" target="_self">海外剧</a>|
                    </span>
                </div> 
                <ul class="pic plist1">
                <c:forEach items="${dianshiju.result}" var="movie" varStatus="status">
      			  <li>
                      <a href="/movie/movieDetail?id=${movie.id }" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
                      <p><b class="c0071bc"><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></b></p>
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
     			<c:forEach items="${dianshiju_new.result}" var="movie" varStatus="status">
	                <c:if test="${status.index < 14 }">
	      			  <li>
	                      <p><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></p>${fn:substring(movie.last_update_time, 0, 4)}
	                  </li>
	                  </c:if>
              	</c:forEach>
      
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
		<script src="http://a.alimama.cn/inf.js" type="text/javascript"></script>
	</div>
    <div class="section1 mt10">
    	<div class="news_top">
            <div class="tab tabw2">
            	<div class="tit">
                	<h2 class="ico3 png">推荐动漫</h2>
                    <span class="c0071bc"><a href="/movie/category?category=7" target="_self">全部</a>
                    </span>
                </div> 
                <ul class="pic plist1"> 
     <c:forEach items="${dongman.result}" var="movie" varStatus="status">
      			  <li>
                      <a href="/movie/movieDetail?id=${movie.id }" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
                      <p><b class="c0071bc"><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></b></p>
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
      			<c:forEach items="${dongman_new.result}" var="movie" varStatus="status">
	                <c:if test="${status.index < 14 }">
	      			  <li>
	                      <p><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></p>${fn:substring(movie.last_update_time, 0, 4)}
	                  </li>
	                  </c:if>
              	</c:forEach>
      
                </ul>
            </div>
        </div>
        <div class="bot"></div>
    </div>
    <div class="ad960">
    	<script type="text/javascript">
		alimama_pid="mm_34345258_3449822_11198405";
		alimama_width=950;
		alimama_height=90;
		</script>
		<script src="http://a.alimama.cn/inf.js" type="text/javascript"></script>
	</div>
    <div class="section1 mt10">
    	<div class="news_top">
            <div class="tab tabw2">
            	<div class="tit">
                	<h2 class="ico4 png">推荐综艺</h2>
                    <span class="c0071bc"><a href="/movie/category?category=0" target="_self">全部</a>
                    </span>
                </div> 
                <ul class="pic plist1"> 
     			<c:forEach items="${zongyi.result}" var="movie" varStatus="status">
      			  <li>
                      <a href="/movie/movieDetail?id=${movie.id }" class="i"><img src="${movie.pic_url }" alt="${movie.name }"></a>
                      <p><b class="c0071bc"><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></b></p>
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
      			<c:forEach items="${zongyi_new.result}" var="movie" varStatus="status">
	                <c:if test="${status.index < 14 }">
	      			  <li>
	                      <p><a href="/movie/movieDetail?id=${movie.id }">${fn:substring(movie.name, 0, 7)}</a></p>${fn:substring(movie.last_update_time, 0, 4)}
	                  </li>
	                  </c:if>
              	</c:forEach>
      
                  
                </ul>
            </div>
        </div>
        <div class="bot"></div>
    </div>
    
   
</div>

 <div id="footer">
    <div class="copyright divauto">
        <p>免责声明:本站所有视频均来自互联网收集而来，版权归原创者所有，如果侵犯了你的权益，请通知我们，我们会及时删除侵权内容，谢谢合作！<br>联系信箱：jlrunning@163.com&nbsp;
        <a href="http://www.cnzz.com/stat/website.php?web_id=1908774" target="_blank" title="站长统计">站长统计</a>


</body></html>