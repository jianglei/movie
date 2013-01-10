<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script src="/js/main.js"></script>
<script src="/js/function.js" ></script>
<%@include file="../include/header.jsp"%>
<div class="ad960" id="index01"><script type="text/javascript" language="javascript" src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/index01.js"></script><iframe src="./迅播影院-Gvod Player-Gvod电影-迅雷电影下载_files/8200_1426.htm" width="960" height="90" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>
</div>
<div class="divauto" id="main">
	<div class="sections">
		<div class="sl">
			<div class="tit1 c5b5c5c">
				<div class="titleft">
							当前位置:
					
					<a href="/GvodHtml/1.html">${name}</a>
				</div>
				<!-- 
				<div class="titright">
					<a target="_self" href="/mscoref/1.html">按评分</a>
					<a target="_self" href="/click/1.html">按热度</a>
					<a class="red" target="_self" href="/GvodHtml/1.html">按更新</a>
				</div>
				 -->
			</div>
			<div class="con">
				<ul class="piclist">
				  <c:forEach items="${category.result}" var="movie" varStatus="status">
		                  <li>
							<a class="i" href="/movie/movieDetail?id=${movie.id}">
							<img alt="${movie.name }" src="${movie.pic_url }">
							<!--
							<em class="green_score">
								<em class="fenshu">
								6
								<sup>.5</sup>
								</em>
							</em>
							<em class="v">${movie.category}</em>  -->
							</a>
							<div class="info">
								<h1 class="c0071bc">
								<a href="/movie/movieDetail?id=${movie.id}">${fn:substring(movie.name, 0, 10)}</a>
								<em>${fn:substring(movie.release_time, 0, 4)}</em>
								</h1>
								<!-- <div class="star">
								 <p class="sar sar3"></p>
								</div>-->
								<p>主演：${movie.actor_list }</p>
								<p>
								<b>状态：${movie.status}</b>
								<b>地区：${movie.area }</b>
								</p>
								<p>
								<b>类型：${movie.category }</b>
								<b>更新：${movie.last_update_time}</b>
								</p>
								<span class="btn">
								<a href="/movie/movieDetail?id=${movie.id}">观看</a>
								</span>
								<!-- 
								<span class="btn1">
								<a href="/Html/GP14213.html#down">下载</a>
								</span> -->
							</div>
					   </li>
                  </c:forEach>
				</ul>
				<div id="pager">
					<span>共${category.totalItems}条数据 页次:${category.pageNo}/${category.totalPages}页</span>
					<a href="javascript:gotoPage(1)" target="_self">首页</a>
					<a href="javascript:gotoPage(${category.pageNo-1})" target="_self">上一页</a>
					<c:forEach  var="count"   begin="${category.pageNo}" end="${category.pageNo+4}" step="1">
						<c:if test="${count == category.pageNo}">  
			                <em>${count}</em>
			            </c:if>  
			            <c:if test="${!(count == category.pageNo)}">  
			                <a href="javascript:gotoPage(${count})" target="_self">${count}</a>
			            </c:if> 
					</c:forEach>
					<a href="javascript:gotoPage(${category.pageNo+1}" target="_self">下一页</a>
					<a href="javascript:gotoPage(${category.totalPages})" target="_self">尾页</a>
					<span>
						<input type="input" size="4" name="page">
						<input class="btn" type="button" onclick="getPageGoUrl(222,'page','/movie/category?pageNo=<page>')" value="跳转">
					</span>
				</div>
			</div>
		</div>
		<div class="sr">
			<div class="newr">
				<h1>搜索索引</h1>
				<dl class="c0071bc">
				<dt>按年份：</dt>
					<dd class="pl1">
					<c:forEach  var="year"   begin="2002" end="2012" step="1">
						<a target="_self" href="javascript:gotoYear(${year})">${year}</a>
					</c:forEach>
					</dd>
				<!--  
				<dt>按地区：</dt>
					<dd class="pl2">
						<a target="_self" href="javascript:gotoArea('大陆')">大陆</a>
						<a target="_self" href="javascript:gotoArea('香港')">香港</a>
						<a target="_self" href="javascript:gotoArea('台湾')">台湾</a>
						<a target="_self" href="javascript:gotoArea('日本')">日本</a>
						<a target="_self" href="javascript:gotoArea('韩国')">韩国</a>
						<a target="_self" href="javascript:gotoArea('欧美')">欧美</a>
						<a target="_self" href="javascript:gotoArea('其它')">其它</a>
					</dd>
				-->
				</dl>
			</div>
			<div class="box mt10 newr1">
				<h1>热门${name}</h1>
				<ul style="height:403px;">
				 <c:forEach items="${category.result}" var="movie" varStatus="status" begin="1" end="14" step="1" >
					<li>
						<p>
						<a href="/movie/movieDetail?id=${movie.id}">${movie.name}</a>
						</p>
						<c:if test="${movie.release_time == 0 }">
						-
						</c:if>
						<c:if test="${movie.release_time != 0 }">
						${movie.release_time}
						</c:if>
					</li>
				 </c:forEach>
				</ul>
			</div>
	    </div>
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




<div style="display:none;" id="bindex01"></div>

<script language="javascript" type="text/javascript">
<!--
	document.getElementById("index01").innerHTML = document.getElementById("bindex01").innerHTML;
	document.getElementById("bindex01").innerHTML = "";
-->

function gotoPage(pageNo){   
	var url = trimUrl(window.location.href).replace(/pageNo=[^&]*/, "")+ "&pageNo="+pageNo;    
	window.location.href = url;  
}
function gotoYear(year){   
	var url = trimUrl(window.location.href).replace(/year=[^&]*/, "")+ "&year="+year;    
	window.location.href = url;  
}
function gotoArea(area){   
	var url = trimUrl(window.location.href).replace(/area=[^&]*/, "")+ "&area="+area;    
	window.location.href = url;  
}
function trimUrl(url){   
	if(url != null && url.length > 1){      
		var temp = url[url.length - 1] == '#' ? url.substring(0,url.length - 1) : url;      
		var pos = temp.indexOf("?",0);      
		if (pos==-1){ 
			temp += "?"      
		}
		return temp[temp.length - 1] == '/' ? temp.substring(0,temp.length - 1) : temp;    
	}    
	else   return url; 
}
</script>

</body></html>