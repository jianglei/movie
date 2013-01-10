<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script src="/js/main.js"></script>

<%@include file="../include/header.jsp"%>
<div id="main" class="divauto">
	<div class="sections">
		<div class="sl">
			<div class="tit c5b5c5c">
				当前位置:
				<a href="/">首页</a>
				&nbsp;&nbsp;»&nbsp;&nbsp;
				<a href="/GvodHtml/6.html">${movieGroup.category}</a>
				&nbsp;&nbsp;»&nbsp;&nbsp;${movieGroup.name}
			</div>
			<div class="con">
				<div id="playinfo">
					<div class="img">
						<img alt="${movieGroup.name}" src="${movieGroup.picUrl}">
					</div>
					<div class="info">
					<div class="pfen">
						<!--  <h1>评分</h1>-->
						<div class="starscore">
							<input id="MARK_B1" type="hidden" value="30" name="MARK_B1">
							<input id="MARK_B2" type="hidden" value="7.6" name="MARK_B2">
							<input id="MARK_B3" type="hidden" value="228" name="MARK_B3">
							<div id="filmStar" >
							<!--  -->
								<div id="start" >
								</div>
								<div id="starTC" class="starC">
								<a onmousemove="startm(1)" onmouseout="kaifach()" onclick="OnStar(14225,1)" href="javascript:;"></a>
								<a onmousemove="startm(2)" onmouseout="kaifach()" onclick="OnStar(14225,2)" href="javascript:;"></a>
								<a onmousemove="startm(3)" onmouseout="kaifach()" onclick="OnStar(14225,3)" href="javascript:;"></a>
								<a onmousemove="startm(4)" onmouseout="kaifach()" onclick="OnStar(14225,4)" href="javascript:;"></a>
								<a onmousemove="startm(5)" onmouseout="kaifach()" onclick="OnStar(14225,5)" href="javascript:;"></a>
								<a onmousemove="startm(6)" onmouseout="kaifach()" onclick="OnStar(14225,6)" href="javascript:;"></a>
								<a onmousemove="startm(7)" onmouseout="kaifach()" onclick="OnStar(14225,7)" href="javascript:;"></a>
								<a onmousemove="startm(8)" onmouseout="kaifach()" onclick="OnStar(14225,8)" href="javascript:;"></a>
								<a onmousemove="startm(9)" onmouseout="kaifach()" onclick="OnStar(14225,9)" href="javascript:;"></a>
								<a onmousemove="startm(10)" onmouseout="kaifach()" onclick="OnStar(14225,10)" href="javascript:;"></a>
								</div>
							</div>
							<!--
							<span id="filmStarScore" class="no c1"/>
							<em>分</em>
							-->
						</div>
						<!--<p id="filmStarScoreTip">比较精彩</p>-->
					</div>
					<script type="text/javascript">
						markVideo(14225,30,0,228,5,0);
				    </script>
				    <div class="text c0071bc">
						<p>
						主演：${movieGroup.actorList}
						</p>
						<p>
						<b>状态：${movieGroup.status}</b>
						<b>更新时间：${movieGroup.lastUpdateTime}</b>
						</p>
						<p>
						<b>地区：${movieGroup.area} </b>
						<b>上映年代：${movieGroup.releaseTime} </b>
						</p>
					</div>
					<div class="share">
					<div class="shares">
						<script src="/js/ads/share.js" language="javascript" type="text/javascript">
						document.writeln("<!-- Baidu Button BEGIN -->");
						document.writeln("<div id=\"bdshare\" class=\"bdshare_t bds_tools get-codes-bdshare\">");
						document.writeln("<span class=\"bds_more\">分享到：<\/span>");
						document.writeln("<a class=\"bds_qzone\"><\/a>");
						document.writeln("<a class=\"bds_tsina\"><\/a>");
						document.writeln("<a class=\"bds_tqq\"><\/a>");
						document.writeln("<a class=\"bds_renren\"><\/a>");
						document.writeln("<a class=\"bds_t163\"><\/a>");
						document.writeln("<a class=\"shareCount\"><\/a>");
						document.writeln("<\/div>");
						document.writeln("<script type=\"text\/javascript\" id=\"bdshare_js\" data=\"type=tools&amp;uid=0\" ><\/script>");
						document.writeln("<script type=\"text\/javascript\" id=\"bdshell_js\"><\/script>");
						document.writeln("<script type=\"text\/javascript\">");
						document.writeln("document.getElementById(\"bdshell_js\").src = \"http:\/\/bdimg.share.baidu.com\/static\/js\/shell_v2.js?cdnversion=\" + Math.ceil(new Date()\/3600000)");
						document.writeln("<\/script>");
						document.writeln("<!-- Baidu Button END -->")
						</script>
						<div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
						<span class="bds_more">分享到：</span>
						<a class="bds_qzone" title="分享到QQ空间" href="#"></a>
						<a class="bds_tsina" title="分享到新浪微博" href="#"></a>
						<a class="bds_tqq" title="分享到腾讯微博" href="#"></a>
						<a class="bds_renren" title="分享到人人网" href="#"></a>
						<a class="bds_t163" title="分享到网易微博" href="#"></a>
						<a class="shareCount" href="#" title="累计分享0次">0</a>
						</div>
						<script id="bdshare_js" data="type=tools&uid=0" type="text/javascript" src="http://bdimg.share.baidu.com/static/js/bds_s_v2.js?cdnversion=377120">
						var bdShare=bdShare||{};(function(){var n1=new Date().getTime();var n2=new Date().getTime();var n3=new Date().getTime();var W=window,D=document,B=D.body,E=D.documentElement,location={},U="",T=D.title,params=[],iframe="",custom={},s=D.getElementById("bdshare_js").attributes.data,ie6=/MSIE 6.0/.test(navigator.userAgent),bt=D.compatMode!="CSS1Compat",s=s.nodeValue.replace(/&amp;/g,"&").split("&")||"";try{var l=W.location||D.location;location.href=l.href;location.search=l.search;location.hash=l.hash;location.protocol=l.protocol;location.hostname=l.hostname;location.pathname=l.pathname;U=encodeURIComponent(location.href)}catch(e){setTimeout(function(){var l=W.location||D.location;location.href=l.href;location.search=l.search;location.hash=l.hash;location.protocol=l.protocol;location.hostname=l.hostname;location.pathname=l.pathname;U=encodeURIComponent(location.href)},0)}for(var i=s.length;i--;){var j=s[i].split("=");params[j[0]]=j[1]}var k={bdPref:"bds_",bdNums:params.mini?7:15,bdMini:params.mini||false,bdType:params.type||"",bdLeft:params.pos||"right",bdTitle:"\u5206\u4eab\u5230",bdText:T,weiboText:"",bdMore:"\u66f4\u591a...",bdPopTitle:"\u5206\u4eab\u5230",bdImg:params.img||0,bdImgW:24,bdTop:0,bdIds:"bdshare",bdIdsl:params.mini?"bdshare_m":"bdshare_l",bdUid:params.uid||"",bdPic:"",searchPic:params.searchPic||0,bdComment:"",bdDesc:"",wbUid:"",tongji:"on",review:"on",render:true,snsKey:{},bdHost:"http://bdimg.share.baidu.com/static/",bdJump:"http://share.baidu.com/",bdApi:"http://api.share.baidu.com/",bdStatistics:"http://share.baidu.com/commit",bdCommit:"http://s.share.baidu.com/",bdMshare:"http://s.share.baidu.com/mshare",bdCout:"http://nsclick.baidu.com/v.gif?pid=307",bdWList:"([http|https]://[a-zA-Z0-9_.]+.baidu.com)",bdList:["mshare,\u4e00\u952e\u5206\u4eab,mshare","qzone,QQ\u7a7a\u95f4,qqkj","tsina,\u65b0\u6d6a\u5fae\u535a,xlwb","baidu,\u767e\u5ea6\u641c\u85cf,bdsc","renren,\u4eba\u4eba\u7f51,rrw","tqq,\u817e\u8baf\u5fae\u535a,txwb","bdxc,\u767e\u5ea6\u76f8\u518c,bdxc","kaixin001,\u5f00\u5fc3\u7f51,kxw","tqf,\u817e\u8baf\u670b\u53cb,txpy","tieba,\u767e\u5ea6\u8d34\u5427,bdtb","douban,\u8c46\u74e3\u7f51,db","tsohu,\u641c\u72d0\u5fae\u535a,shwb","bdhome,\u767e\u5ea6\u65b0\u9996\u9875,bdhome","sqq,QQ,sqq","thx,\u548c\u8baf\u5fae\u535a,thx","qq,QQ\u6536\u85cf,qqsc","taobao,\u6211\u7684\u6dd8\u5b9d,tjh","hi,\u767e\u5ea6\u7a7a\u95f4,bdkj","msn,MSN,msn","sohu,\u641c\u72d0\u767d\u793e\u4f1a,shbsh","t163,\u7f51\u6613\u5fae\u535a,wywb","qy,\u5947\u827a\u5947\u8c08,qyqt","meilishuo,\u7f8e\u4e3d\u8bf4,mls","mogujie,\u8611\u83c7\u8857,mgj","diandian,\u70b9\u70b9\u7f51,diandian","huaban,\u82b1\u74e3,huaban","leho,\u7231\u4e50\u6d3b,leho","share189,\u624B\u673A\u5FEB\u4F20,share189","duitang,\u5806\u7cd6,duitang","hx,\u548c\u8baf,hexun","tfh,\u51e4\u51f0\u5fae\u535a,fhwb","fx,\u98de\u4fe1,feixin","youdao,\u6709\u9053\u4e91\u7b14\u8bb0,youdao","sdo,\u9EA6\u5E93\u8BB0\u4E8B,sdo","qingbiji,\u8F7B\u7B14\u8BB0,qingbiji","ifeng,\u51E4\u51F0\u5FEB\u535A,ifeng","people,\u4EBA\u6C11\u5FAE\u535A,people","xinhua,\u65B0\u534E\u5FAE\u535A,xinhua","ff,\u996d\u5426,fanfou","mail,\u90ae\u4ef6\u5206\u4eab,mail","kanshou,\u641C\u72D0\u968F\u8EAB\u770B,kanshou","yaolan,\u6447\u7BEE\u7A7A\u95F4,yaolan","wealink,\u82e5\u90bb\u7f51,wealink","tuita,\u63A8\u4ED6,tuita","xg,\u9c9c\u679c,xianguo","ty,\u5929\u6daf\u793e\u533a,tianya","fbook,Facebook,facebook","twi,Twitter,twitter","ms,Myspace,myspace","deli,delicious,delicious","s51,51\u6E38\u620F\u793e\u533a,51shequ","s139,139\u8bf4\u5ba2,shuoke","linkedin,linkedin,LinkedIn","copy,\u590d\u5236\u7f51\u5740,kaobei","print,\u6253\u5370,print"]}||{},base={_createScriptTag:function(a,b,c){a.setAttribute("type","text/javascript");c&&a.setAttribute("charset",c);a.setAttribute("src",b);document.getElementsByTagName("head")[0].appendChild(a)},_removeScriptTag:function(a){if(a.clearAttributes){a.clearAttributes()}else{for(var b in a){if(a.hasOwnProperty(b)&&"parentNode"!=b){delete a[b]}}}if(a&&a.parentNode){a.parentNode.removeChild(a)}a=null},callByBrowser:function(b,c,d){var e=document.createElement("SCRIPT"),scriptLoaded=0,options=d||{},charset=options.charset,callback=c||function(){},timeOut=options.timeOut||0,timer;e.onload=e.onreadystatechange=function(){if(scriptLoaded){return }var a=e.readyState;if("undefined"==typeof a||a=="loaded"||a=="complete"){scriptLoaded=1;try{callback();clearTimeout(timer)}finally{e.onload=e.onreadystatechange=null;base._removeScriptTag(e)}}};if(timeOut){timer=setTimeout(function(){e.onload=e.onreadystatechange=null;base._removeScriptTag(e);options.onfailure&&options.onfailure()},timeOut)}base._createScriptTag(e,b,charset)},on:function(ele,type,func){if(ele.addEventListener){ele.addEventListener(type,func,false)}else{if(ele.attachEvent){ele.attachEvent("on"+type,func)}}},unon:function(ele,type,func){if(ele.removeEventListener){ele.removeEventListener(type,func,false)}else{if(ele.detachEvent){ele.detachEvent("on"+type,func)}}},html:function(a,b){var c=D.createElement(b||"div");for(var p in a){p=="style"?c[p].cssText=a[p]:c[p]=a[p]}if(b=="link"){D.getElementsByTagName("head")[0].appendChild(c)}else{return B.insertBefore(c,(b=="iframe"?B.firstChild:iframe.nextSibling))}},list:function(s){var o=k.bdPref,m=s.split(","),n=m[1],u="#",c=o+m[0]+" "+m[2];return{name:n,url:u,cls:c}},list_s:function(s){var a=[],h=inner="",conf=k,leng=conf.bdList,n=conf.bdNums,more=(conf.bdNums<leng.length)?'<li><a href="#" class="bds_more">'+conf.bdMore+"</a></li>":"";while(n--){h=base.list(leng[n]),a.push('<a href="'+h.url+'" class="'+h.cls+'">'+h.name+"</a>")}inner='<iframe id="bdsIfr" style="position:absolute;display:none;z-index:9999;" frameborder="0"></iframe>';inner+='<div id="'+conf.bdIdsl+'"><div id="'+conf.bdIdsl+'_c"><h6>'+conf.bdTitle+"</h6>";inner+="<ul><li>"+a.reverse().join("</li><li>")+"</li>"+more+'</ul><p><a href="#" class="goWebsite">\u767e\u5ea6\u5206\u4eab</a></p></div></div>';if(s){var popShare=base.html({id:"bdshare_s",innerHTML:inner});var child=base.children(popShare);popShare=child[child.length-1];base.on(popShare,"mouseover",function(e){var e=e||window.event;var target=e.target||e.srcElement;var targetPostion=base.getPosition(target);var relatedElement=e.relatedTarget||e.fromElement;var mousePos=base.getMousePos(e);var x=mousePos.x-targetPostion.l;var y=mousePos.y-targetPostion.t;target.buttontype=1;target.x=x;target.y=y;if(base.contains(popShare,relatedElement)){return }popShare.time=+new Date})}else{return inner}},scroll:function(b,c){base.on(W,"scroll",function(e){var a=base.getSize(),conf=k,top=conf.bdTop;if(conf.bdType=="slide"){if(ie6||bt){base.setAttr(D.getElementById(conf.bdIdsl),"display:block");_bdS.style.top=(a.t+(top==0?(a.h/2-(D.getElementById(conf.bdIdsl).offsetHeight-11)/2):parseInt(top)))+"px"}else{if(conf.bdTop!=0){_bdS.style.top=parseInt(conf.bdTop)+"px"}}}if(c=="pop"&&(ie6||bt)){b.style.top=(a.t+a.h/2-b.offsetHeight/2)+"px";base.setAttr(iframe,"top:"+b.style.top)}})},getSize:function(){return{t:(B.scrollTop||E.scrollTop),l:(B.scrollLeft||E.scrollLeft),w:(E.clientWidth||B.clientWidth),h:(W.innerHeight||E.clientHeight||B.clientHeight)}},getPosition:function(a){var s=base.getSize(),o=a,pos={t:0,l:0},gecko=/gecko/.test(navigator.userAgent),posAdd=function(t,l){pos.t+=t,pos.l+=l};if(o&&o!=B){if(o.getBoundingClientRect){var b=o.getBoundingClientRect(),doc=a.ownerDocument,body=doc.body,html=doc.documentElement,clientTop=html.clientTop||body.clientTop||0,clientLeft=html.clientLeft||body.clientLeft||0;if(b.top==b.bottom){var g=o.style.display;o.style.display="block";o.style.display=g}posAdd(b.top+s.t-clientTop,b.left+s.l-clientLeft)}else{var c=D.defaultView;while(o){posAdd(o.offsetTop,o.offsetLeft);var e=c.getComputedStyle(o,null);if(gecko){var f=parseInt(e.getPropertyValue("border-left-width"),10)||0,bs=parseInt(e.getPropertyValue("border-top-width"),10)||0;posAdd(bs,f);if(o!=a&&e.getPropertyValue("overflow")!="visible"){posAdd(bs,f)}}o=o.offsetParent}o=a.parentNode;while(o&&o!=B){posAdd(-o.scrollTop,-o.scrollLeft);o=o.parentNode}}}return pos},setAttr:function(a,b){var s=b.split(";"),sl=s.length;while(sl--){if(s[sl]){var p=s[sl].split(":");if(p){a.style[p[0]]=p[1]}}}},children:function(ele){for(var children=[],tmpEl=ele.firstChild;tmpEl;tmpEl=tmpEl.nextSibling){if(tmpEl.nodeType==1){children.push(tmpEl)}}return children},generateRandom:function(str,n){var m=str.length;var s="";for(var i=1;i<=n;i++){var r=Math.floor(m*Math.random());s=s+str.charAt(r)}return s},generateLinkid:function(){var time=(+new Date).toString(36);var random=base.generateRandom("0123456789abcdefghijklmnopqrstuvwxyz",3);return time+random},contains:function(ele,child){if(!child||!ele){return false}if(ele==child){return true}var parent=child.parentNode;while(parent!=null&&typeof (parent.tagName)!="undefind"){if(parent==ele){return true}parent=parent.parentNode}return false},getMousePos:function(ev){if(ev.pageX||ev.pageY){return{x:ev.pageX,y:ev.pageY}}if(document.documentElement&&document.documentElement.scrollTop){return{x:ev.clientX+document.documentElement.scrollLeft-document.documentElement.clientLeft,y:ev.clientY+document.documentElement.scrollTop-document.documentElement.clientTop}}else{if(document.body){return{x:ev.clientX+document.body.scrollLeft-document.body.clientLeft,y:ev.clientY+document.body.scrollTop-document.body.clientTop}}}},_isCookieValidKey:function(key){return(new RegExp('^[^\\x00-\\x20\\x7f\\(\\)<>@,;:\\\\\\"\\[\\]\\?=\\{\\}\\/\\u0080-\\uffff]+\x24')).test(key)},getCookieRaw:function(key){if(base._isCookieValidKey(key)){var reg=new RegExp("(^| )"+key+"=([^;]*)(;|\x24)"),result=reg.exec(document.cookie);if(result){return result[2]||null}}return null},setCookieRaw:function(key,value,options){if(!base._isCookieValidKey(key)){return }options=options||{};var expires=options.expires;if("number"==typeof options.expires){expires=new Date();expires.setTime(expires.getTime()+options.expires)}document.cookie=key+"="+value+(options.path?"; path="+options.path:"")+(expires?"; expires="+expires.toGMTString():"")+(options.domain?"; domain="+options.domain:"")+(options.secure?"; secure":"")},getCookie:function(key){var value=base.getCookieRaw(key);if("string"==typeof value){value=decodeURIComponent(value);return value}return null},setCookie:function(key,value,options){base.setCookieRaw(key,encodeURIComponent(value),options)}},ifr=function(a){base.html({href:k.bdHost+"css/bdsstyle.css?cdnversion=20121228",rel:"stylesheet",type:"text/css"},"link");iframe=iframe||base.html({style:"display:none;"+(a||""),frameBorder:0},"iframe")},loader=function(type,to,title,url){var b=/(#\d+\-[a-zA-Z\d]+\-\d+\-\d+\-[a-f\d]{32}$)/g,urlWithSign=(location.search+location.hash).replace(/&/g,"#"),sign=urlWithSign.match(b)||"",re=new RegExp(k.bdWList,"ig"),getUrl=location.protocol+"//"+location.hostname+location.pathname+location.search;if(re.test(location.href)&&sign==""&&typeof type=="undefined"){return }var buttonTypeArr=[];if(bdShare.fn.b.length>0){buttonTypeArr.push("button")}if(bdShare.fn.t.length>0){buttonTypeArr.push("tools")}if(bdShare.fn.conf.bdType){buttonTypeArr.push(bdShare.fn.conf.bdType)}var buttonType=(function(){for(var i=0,len=buttonTypeArr.length,obj={},a=[];i<len;i++){!(buttonTypeArr[i] in obj)&&a.push(buttonTypeArr[i])&&(obj[buttonTypeArr[i]]="")}return a.join(",")})();if(k.tongji=="on"||sign!=""||typeof type!="undefined"){if(sign!=""&&type!="3072"){sign=sign.toString().replace("#","");(new Image()).src=k.bdCommit+"back?url="+encodeURIComponent(getUrl)+"&sign="+encodeURIComponent(sign)+"&title="+encodeURIComponent(T.substr(0,300))}var loadTime=bdShare.loadTime?(+new Date-bdShare.loadTime):"";if(typeof type!="undefined"){(new Image()).src=k.bdCout+"&type="+type+"&sign="+sign+"&uid="+k.bdUid+"&desturl="+encodeURIComponent(D.referrer)+"&linkid="+linkid+"&button="+buttonType+"&loadTime="+loadTime}else{var shareType=[0,0,0,0];for(var i=0,len=buttonTypeArr.length;i<len;i++){shareType[{tools:0,button:1,slide:2}[buttonTypeArr[i]]]=1}bdShare._LogPool=bdShare._LogPool||[];bdShare._LogPool.push({key:"cite",api:{uid:k.bdUid||"",linkid:linkid,type:parseInt(shareType.reverse().join(""),2),mini:k.bdMini?1:0,loadTime:loadTime}})}}},dialog={create:function(){var b=k,s=b.bdList,sl=s.length,list=[],h="",size=base.getSize(),o=D.getElementById(b.bdIds+"_pop"),pos=((ie6||bt)?"absolute":"fixed"),t=((ie6||bt)?size.t:0);if(!o){while(sl--){h=base.list(s[sl]),list.push('<a href="'+h.url+'" class="'+h.cls+'">'+h.name+"</a>")}var c=base.html({id:b.bdIds+"_pop",style:"position:"+pos,innerHTML:"<div><h5></h5><ul><li>"+list.reverse().join("</li><li>")+'</li></ul><p><a href="#" class="goWebsite">\u767e\u5ea6\u5206\u4eab</a></p></div>'});event.bind(c,"mouseover","a");event.bind(c,"click","b",function(){c.style.display="none";iframe.style.display="none"});base.on(c,"mouseover",function(e){var e=e||window.event;var target=e.target||e.srcElement;var targetPostion=base.getPosition(target);var mousePos=base.getMousePos(e);var x=mousePos.x-targetPostion.l;var y=mousePos.y-targetPostion.t;target.buttontype=2;target.x=x;target.y=y;var relatedElement=e.relatedTarget||e.fromElement;if(base.contains(c,relatedElement)){return }c.time=+new Date})}var d=c||o;d.getElementsByTagName("h5")[0].innerHTML=(custom.title||b.bdPopTitle)+"<b>&nbsp;</b>";base.setAttr(d,"display:block;");base.setAttr(d,"left:"+(size.w/2-d.offsetWidth/2)+"px;top:"+(t+size.h/2-d.offsetHeight/2)+"px");base.setAttr(iframe,"position:"+pos+";display:block;z-index:10000;width:"+d.offsetWidth+"px;height:"+d.offsetHeight+"px;left:"+d.style.left+";top:"+d.style.top);base.scroll(d,"pop");base.on(W,"resize",function(e){var a=base.getSize(),post="left:"+(a.w/2-d.offsetWidth/2)+"px;top:"+(t+a.h/2-d.offsetHeight/2)+"px";base.setAttr(d,post);base.setAttr(iframe,post)});if(b.bdType!="slide"){D.getElementById(k.bdIdsl).style.display="none";D.getElementById("bdsIfr").style.display="none"}window.bdShareActivity&&bdShareActivity.check()}},event={bind:function(a,b,c,d){var f=this;a.addEventListener?a.addEventListener(b,function(e){return f._action(e,e.target,c,a,d)},false):a.attachEvent("on"+b,function(e){e=W.event;return f._action(e,e.srcElement,c,a,d)})},_action:function(e,b,d,f,g){if(b.nodeName==d.toUpperCase()){if(d=="a"||d=="span"){var c=b.className,t=c=="goWebsite"?c:c.split(" ")[0].split("_")[1],me=this;if(t){(t=="more"&&d=="span")?me._tools():b.onclick=function(i,a){return function(e){var e=e||window.event;var target=e.target||e.srcElement;var targetPostion=base.getPosition(target);var mousePos=base.getMousePos(e);var x=mousePos.x-targetPostion.l;var y=mousePos.y-targetPostion.t;if(i=="more"){me._tools()}else{if(i=="copy"){me._copy(me)}else{if(i=="print"){me._print()}else{me._jump(i,a,b,x,y)}}}return false}}(t,f)}return false}if(typeof g==="function"){g()}}if(typeof e.preventDefault==="function"){e.preventDefault();e.stopPropagation()}else{e.returnValue=false;e.cancelBubble=true}},_tools:function(){loader(3072);dialog.create()},_copy:function(a){_this=a;var b=new _this._browsers();if(b.name=="IE"){window.clipboardData.setData("text",document.title+" "+location.href);alert("\u6807\u9898\u548c\u94fe\u63a5\u590d\u5236\u6210\u529f\uff0c\u60a8\u53ef\u4ee5\u63a8\u8350\u7ed9QQ/MSN\u4e0a\u7684\u597d\u53cb\u4e86\uff01")}else{window.prompt("\u60a8\u4f7f\u7528\u7684\u662f\u975eIE\u6838\u5fc3\u6d4f\u89c8\u5668\uff0c\u8bf7\u6309\u4e0b Ctrl+C \u590d\u5236\u4ee3\u7801\u5230\u526a\u8d34\u677f",document.title+" "+location.href)}},_mail:function(desc){},_print:function(){var popE=document.getElementById("bdshare_pop");popE&&(popE.style.display="none");window.print();popE&&(popE.style.display="block");return },_jump:function(a,b,target,x,y){var c=k,pic=c.bdPic,txt=c.bdText,weibotxt=c.weiboText,wbuid=c.wbUid,urls=U,desc=c.bdDesc,comment=c.bdComment,miniWindow=c.bdMiniWindow||0;custom=(b.getAttribute("data")&&b.getAttribute("data")!="")?eval("("+b.getAttribute("data")+")"):custom;if(custom!=""){pic=encodeURIComponent(custom.pic||"")||pic;urls=encodeURIComponent(custom.url||"")||urls;comment=encodeURIComponent(custom.comment||"")||comment;desc=encodeURIComponent(custom.desc||"")||desc;wbuid=custom.wbuid||wbuid;if(a=="tsina"){if(custom.text&&custom.weibotext){txt=encodeURIComponent((custom.text+custom.weibotext).substr(0,300))}else{if(custom.weibotext){txt=encodeURIComponent((txt+custom.weibotext).substr(0,300))}else{if(custom.text){txt=encodeURIComponent((custom.text+weibotxt).substr(0,300))}else{txt=encodeURIComponent((txt+weibotxt).substr(0,300))}}}}else{txt=encodeURIComponent((custom.text||"").substr(0,300))||encodeURIComponent(txt.substr(0,300))}}else{if(a=="tsina"){txt=encodeURIComponent((txt+weibotxt).substr(0,300))}else{txt=encodeURIComponent(txt.substr(0,300))}}txt=encodeURIComponent(txt);n3=new Date().getTime()+3000;var l=n1.toString(32)+n2.toString(32)+n3.toString(32);var type=target.buttontype;var ax=Math.floor(bdShare.XY.loadedX);var ay=Math.floor(bdShare.XY.loadedY);var type1=type>0?1:0;var bx=Math.floor(target.x);var by=Math.floor(target.y);var cx=Math.floor(x);var cy=Math.floor(y);var w=Math.floor(target.offsetWidth);var h=Math.floor(target.offsetHeight);var type2=type;var t=+new Date;t=t-b.time;var sloc=[ax,ay,type1,bx,by,cx,cy,w,h,type2,t].join(".");var btntype;switch(k.bdType){case"button":btntype=1;break;case"slide":btntype=2;break;default:btntype=0;break}var to=a=="mail"?"_mail":a;var urlParams="?click=1&url="+urls+"&uid="+c.bdUid+"&to="+to+"&type=text&relateUid="+wbuid+"&pic="+pic+"&title="+txt+"&key="+(c.snsKey[a]||"")+"&sign="+c.review+"&desc="+desc+"&comment="+comment+"&searchPic="+c.searchPic+"&l="+l+"&linkid="+linkid+"&sloc="+sloc+"&apiType=0&buttonType="+btntype+"&firstime="+base.getCookie("bdshare_firstime");if(a=="mshare"){var d=c.bdMshare+urlParams}else{var d=c.bdCommit+urlParams}var bdStatistics=c.bdCommit+"commit"+urlParams+"&t="+Math.random();if(a!="goWebsite"){bdShare.fn._getSc("share");var sendRequest=function(){var list=[];return function(src){var index=list.push(new Image)-1;list[index].onload=function(){list[index]=list[index].onload=null};list[index].src=src}}();setTimeout(function(){sendRequest(bdStatistics)},1500);if(a=="bdxc"){var g=window,d=document,m="_bdXC",s;if(g[m]){if(window._bdXC_loaded){g[m].reInit()}}else{s=d.createElement("script");s.setAttribute("charset","utf-8");s.src="http://xiangce.baidu.com/zt/collect/mark.js?"+(new Date()).getTime();d.getElementsByTagName("head")[0].appendChild(s)}}else{if(a=="mail"){var baseURL=k.bdCommit+"sendmail";window.open(baseURL+urlParams,"_blank")}else{if(1==miniWindow){W.open(d,"bdShare","height=400, width=600")}else{W.open(d)}}}}else{W.open(c.bdJump)}},_browsers:function(){var a={};var b=navigator.userAgent.toLowerCase();var c,s;if(b.match(/msie ([\d.]+)/)){s=b.match(/msie ([\d.]+)/);a.name="IE";a.version=s[1]}else{if(b.match(/firefox\/([\d.]+)/)){s=b.match(/firefox\/([\d.]+)/);a.name="firefox";a.version=s[1]}else{if(b.match(/chrome\/([\d.]+)/)){s=b.match(/chrome\/([\d.]+)/);a.name="chrome";a.version=s[1]}else{if(b.match(/opera.([\d.]+)/)){s=b.match(/opera.([\d.]+)/);a.name="opera";a.version=s[1]}else{if(b.match(/safari.([\d.]+)/)){s=b.match(/safari.([\d.]+)/);a.name="safari";a.version=s[1]}else{a.name="\u672a\u77e5\u7684\u6d4f\u89c8\u5668";a.version="\u672a\u77e5\u7684\u7248\u672c\u53f7"}}}}}return a}};var linkid=base.generateLinkid();bdShare.fn=(function(){return{init:function(){n2=new Date().getTime()+1000;this.b=[];this.t=[];this.s=[];this.type="load";this._getShare();var self=this;if(typeof bds_config!="undefined"){for(var p in bds_config){if(p=="bdPopTitle"||p=="bdText"||p=="weiboText"||p=="bdPic"||p=="wbUid"||p=="tongji"||p=="render"||p=="snsKey"||p=="review"||p=="bdComment"||p=="bdDesc"||p=="bdTop"||p=="searchPic"||p=="bdUrl"||p=="bdMiniWindow"){k[p]=(p=="render"||p=="snsKey"||p=="bdPopTitle"||p=="bdText"||p=="weiboText")?bds_config[p]:encodeURIComponent(bds_config[p])}}}this.conf=k;this.imgW=k.bdImgW;if(this.conf.render){if(this.b.length>0||this.t.length>0){this._share()}if(this.conf.bdType=="slide"){this._slide()}}this._getSc(this.type);var shareButtons=this.t;for(var i=0;i<shareButtons.length;i++){var shareButton_a=shareButtons[i].getElementsByTagName("a");for(var j=0;j<shareButton_a.length;j++){!(shareButton_a[j].getAttribute("href"))&&shareButton_a[j].setAttribute("href","#")}}bdShare.XY=bdShare.XY||{};function getLoadedXY(e){var e=e||window.event;var mousePos=base.getMousePos(e);bdShare.XY.loadedX=mousePos.x;bdShare.XY.loadedY=mousePos.y;if(bdShare.XY.loadedX>0&&bdShare.XY.loadedY>0){base.unon(document,"mousemove",getLoadedXY)}}base.on(document,"mousemove",getLoadedXY);for(var i=0,len=this.t.length;i<len;i++){(function(x){var xy=x;base.on(self.t[x],"mouseover",function(e){var e=e||window.event;var target=e.target||e.srcElement;var targetPostion=base.getPosition(target);var mousePos=base.getMousePos(e);var x=mousePos.x-targetPostion.l;var y=mousePos.y-targetPostion.t;target.buttontype=0;target.x=x;target.y=y;var relatedElement=e.relatedTarget||e.fromElement;if(base.contains(self.t[xy],relatedElement)){return }self.t[xy].time=+new Date})})(i)}loader();if(base.getCookie("bdshare_firstime")==null){base.setCookie("bdshare_firstime",new Date()*1,{path:"/",expires:new Date().setFullYear(2022)-new Date()})}},_getSc:function(t){this.type=t;var c=D.querySelectorAll?D.querySelectorAll(".shareCount"):D.getElementsByTagName("*"),cl=c.length;while(cl--){if(/\sshareCount|shareCount\s|shareCount$/.test(c[cl].className)){this.s.push(c[cl])}}if(this.s.length>0){base.callByBrowser(this.conf.bdApi+"getnum?url="+U+"&callback=bdShare.fn._getShare&type="+t+"&t="+new Date().getTime())}},_getShare:function(v){var a=v?true:false,c=[];if(D.querySelectorAll){this.b=D.querySelectorAll(".bdshare_b");this.t=D.querySelectorAll(".bdshare_t")}else{var e=D.getElementsByTagName("*"),l=e.length;while(l--){var s=e[l];if(/\sbdshare_t|bdshare_t\s/.test(s.className)){this.t.push(s)}if(/\sbdshare_b|bdshare_b\s|bdshare_b$/.test(s.className)){this.b.push(s)}}}if(a){var c=this.s,cl=c.length,count;while(cl--){if(/\sshareCount|shareCount\s|shareCount$/.test(c[cl].className)){count=this.type=="share"?(v.num[0]<10000?parseInt(c[cl].innerHTML)+1:v.num[1]):(v.errno*1==0?v.num[1]:0);c[cl].innerHTML=decodeURI(count);c[cl].setAttribute("title","\u7d2f\u8ba1\u5206\u4eab"+v.num[0]+"\u6b21");c[cl].onclick=function(){dialog.create()}}}this.s=[]}},_share:function(){base.list_s(true);var b=this.b,t=this.t,bl=b.length,tl=t.length,p=D.getElementById(this.conf.bdIdsl),m=D.getElementById("bdsIfr"),w=this.conf.bdList,wl=w.length;base.setAttr(p,"display:none;");event.bind(p,"mouseover","a");if(bl>0){while(bl--){if(b[bl].id=="bdshare"){event.bind(b[bl],"click","img",function(){dialog.create()});event.bind(b[bl],"mouseover","a",function(){dialog.create()});this._s(b[bl],p,m,b[bl],"b")}}}if(tl>0){while(tl--){if(t[tl].id=="bdshare"){event.bind(t[tl],"click","span",function(){dialog.create()});event.bind(t[tl],"mouseover","a");var a=base.children(t[tl]),sl=a.length,s="";while(sl--){var c=a[sl];if(/bds_more/.test(c.className)){s=a[sl];if(bt){s.style.height=(/bds_tools_32/.test(t[tl].className)?38:17)+"px"}this._s((s==""?t[tl]:s),p,m,t[tl],s)}else{for(var l=0;l<wl;l++){if(w[l]){var d=w[l].split(","),re=new RegExp(this.conf.bdPref+d[0]+"$","ig");if(w[l]){var d=w[l].split(","),re=new RegExp(this.conf.bdPref+d[0]+"$","ig");if(re.test(c.className)){if(d[1]=="鎷疯礉"){c.title="澶嶅埗缃戝潃"}else{if(!c.title){c.title="\u5206\u4eab\u5230"+d[1]}}}}}}}}}}}},_s:function(a,p,m,b,s){var c=this,pos=[],t,v=(s==""?"none":"block");this._popShow(a,{over:function(e){pos=base.getPosition(a);custom=(b.getAttribute("data")&&b.getAttribute("data")!="")?eval("("+b.getAttribute("data")+")"):"";base.setAttr(p,"display:"+v);base.setAttr(p,"left:"+(((base.getSize().w-e.clientX)<p.offsetWidth)?(pos.l-p.offsetWidth+a.offsetWidth):pos.l)+"px");base.setAttr(p,"top:"+(((base.getSize().h-e.clientY)<p.offsetHeight)?(pos.t-p.offsetHeight):pos.t+a.offsetHeight)+"px");base.setAttr(m,"display:"+v+";width:"+p.offsetWidth+"px;height:"+p.offsetHeight+"px;left:"+p.style.left+";top:"+p.style.top)},out:function(e){t=setTimeout(function(){p.style.display="none";m.style.display="none"},100);c._popShow(p,{over:function(){clearTimeout(t);p.style.display="block";m.style.display="block"},out:function(){p.style.display="none";m.style.display="none"}})}})},_slide:function(){if(!W._bdS){var a=this.conf.bdLeft=="right"?"left":"right",img=this.conf.bdHost+"images/"+this.conf.bdLeft.substring(0,1)+this.conf.bdImg+".gif";W._bdS=base.html({id:this.conf.bdIds,innerHTML:'<img src="'+img+'" alt="" style="float:'+a+';margin-top:58px;"/>'+base.list_s(false)});var child=base.children(_bdS);var slideShare=child[child.length-1];base.on(slideShare,"mouseover",function(e){var e=e||window.event;var target=e.target||e.srcElement;var targetPostion=base.getPosition(target);var relatedElement=e.relatedTarget||e.fromElement;var mousePos=base.getMousePos(e);var x=mousePos.x-targetPostion.l;var y=mousePos.y-targetPostion.t;target.buttontype=1;target.x=x;target.y=y;if(base.contains(slideShare,relatedElement)){return }slideShare.time=+new Date})}var b=this,o=_bdS,size=base.getSize(),t=D.getElementById(this.conf.bdIdsl),m=D.getElementById("bdsIfr"),fixed=(!ie6&&!bt)?"fixed":"absolute",top=(ie6?size.t:0)+(this.conf.bdTop==0?(size.h<t.offsetHeight?-40:(size.h/2-t.offsetHeight/2)):parseInt(this.conf.bdTop));base.setAttr(o,"overflow:hidden;height:330px;position:"+fixed+";top:"+top+"px;"+this.conf.bdLeft+":0;"+((a=="right")?a+":"+(size.w-this.imgW)+"px":""));base.setAttr(t,a+":"+this.imgW+"px;display:none");event.bind(o,"click","img",function(){loader(3072);dialog.create()});event.bind(o,"mouseover","img",sl);event.bind(t,"mouseover","a");function sl(){base.setAttr(t,"display:block;");if((t.offsetWidth+b.imgW)==o.offsetWidth){return }tm=setTimeout(function(){if((t.offsetWidth+b.imgW)!=o.offsetWidth){b._move(o,0,b.imgW,t.offsetWidth,m)}},100);b._popShow(t,{over:function(){base.setAttr(m,"height:"+t.offsetHeight+"px;"+b.conf.bdLeft+":0;top:0;display:block")},out:function(){}});b._popShow(o,{over:function(){},out:function(){if(o.offsetWidth==(t.offsetWidth+b.imgW)){clearTimeout(tm);b._move(o,0,t.offsetWidth+b.imgW,-t.offsetWidth,m)}m.style.display="none"}})}base.scroll(o,"slide");base.on(W,"resize",function(){base.setAttr(o,((a=="right")?a+":"+(base.getSize().w-b.imgW)+"px":""))})},_move:function(o,t,b,c,m){var a=o.style,objm=m.style,me=this,d=6;setInterval(function(){if(t<d){t++;a.width=Math.ceil(c*t/d+b)+"px";objm.width=Math.ceil(c*t/d+b)-me.imgW+"px"}else{return }},10)},_popShow:function(o,a){if(D.all){o.onmouseenter=function(e){over(W.event)};o.onmouseleave=out}else{o.onmouseover=function(e){e.relatedTarget==null?over(e):(!(this===e.relatedTarget||this.compareDocumentPosition(e.relatedTarget)==20)&&over(e))};o.onmouseout=function(e){e.relatedTarget==null?out(e):(!(this===e.relatedTarget||this.compareDocumentPosition(e.relatedTarget)==20)&&out(e))}}function over(e){a.over(e)}function out(e){a.out(e)}}}})();ifr();bdShare.fn.init()})();
						</script>
						<script id="bdshell_js" type="text/javascript" src="http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=377120">
						var bdShare=bdShare||{};bdShare.ready=bdShare.ready||function(b,c){c=c||document;if(/complete/.test(c.readyState)){b()}else{if(c.addEventListener){if("interactive"==c.readyState){b()}else{c.addEventListener("DOMContentLoaded",b,false)}}else{var a=function(){a=new Function;b()};void function(){try{c.body.doScroll("left")}catch(d){return setTimeout(arguments.callee,10)}a()}();c.attachEvent("onreadystatechange",function(){("complete"==c.readyState)&&a()})}}};bdShare.loadScript=bdShare.loadScript||function(b){var a=document.createElement("script");a.src=b;bdShare.ready(function(){document.getElementsByTagName("script")[0].parentNode.appendChild(a)})};bdShare.loadTime=+new Date;if(!bdShare.ApiPVLogger){bdShare.loadScript("http://bdimg.share.baidu.com/static/js/logger.js?cdnversion="+Math.ceil(new Date()/3600000))}document.getElementById("bdshare_js").src="http://bdimg.share.baidu.com/static/js/bds_s_v2.js?cdnversion="+Math.ceil(new Date()/3600000);
						</script>
						<script type="text/javascript">
						document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
						</script>
						</div>
					</div>
					<div class="pic">
						<script src="/js/ads/con468x60.js" language="javascript" type="text/javascript">
						document.writeln("<iframe src=\"http:\/\/www.37cs.com\/html\/click\/7395_1422.html\" width=\"468\" height=\"60\" marginheight=\"0\" marginwidth=\"0\" scrolling=\"no\" frameborder=\"0\"><\/iframe>")
						</script>
						<iframe width="468" scrolling="no" height="60" frameborder="0" marginwidth="0" marginheight="0" src="http://www.37cs.com/html/click/7395_1422.html">
						<html>
						</iframe>
					</div>
				</div>
			</div>
			 </div>
			 
			 <div class="tit3 mt10">
				<b>剧情介绍</b>
			 </div>
			 <div class="con4">
			     <div class="about_t">
			     ${movieGroup.description}
			    </div>
		    </div>
		    <div class="tit3 mt10">
				<b>百度影音</b>
				<a target="_blank" href="http://dl.client.baidu.com/BaiduPlayer/un/BaiduPlayer_86054008.exe">下载百度影音</a>
			</div>
			
			<div class="con4">
				<ul class="pdown">
				     <c:forEach items="${movies}" var="movie" varStatus="status">
						<li>
							<a target="_blank" href="/movie/view?id=${movie.id}" title="${movie.name}">${movie.name}</a>
						</li>
					</c:forEach>
				</ul>
		   </div>
		 </div>

		<div class="sr">
			<div class="ads250">
			</div>
			<div class="ads250 mt10">
				<script src="/js/ads/con250x250-2.js" language="javascript" type="text/javascript">
				document.writeln("<iframe src=\"http:\/\/www.37cs.com\/html\/click\/7395_1714.html\" width=\"250\" height=\"200\" marginheight=\"0\" marginwidth=\"0\" scrolling=\"no\" frameborder=\"0\"><\/iframe>")
				</script>
				<iframe width="250" scrolling="no" height="200" frameborder="0" marginwidth="0" marginheight="0" src="http://www.37cs.com/html/click/7395_1714.html">
				<html>
				<head>
				<title>37cs_v1.1</title>
				<style type="text/css">
				body {margin: 0;}
				</style>
				</head>
				<body>
				<script language="javascript">
				var ref=encodeURIComponent(document.referrer);
				var i,s=0,adlist=new Array(),m=(new Date().getTime())%100+1;
				adlist[0]={img:'http://p.49ko.com/b/1/97/250X200.swf?uid=507395',url:'http://p.ggmm777.com/s/1/97/0.html?uid=507395',p:50};
				adlist[1]={img:'http://p.144gg.com/b/2/104/250X200.swf?uid=507395',url:'http://p.000dn.com/s/2/104/0.html?uid=507395',p:50};
				for(i=0;i<2;i++){
				if (m>s && m<=s+adlist[i].p) break;
				s+=adlist[i].p;}
				if (i>=2) i=2-1;
				if (adlist[i].img.indexOf('swf')>0) {
				document.write("<div style='position:absolute;z-index:1'><a href='"+adlist[i].url+"&referer="+ref+"' target='_blank'><img src='http://www.37cs.com/html/click/dot.gif' width='250' height='200' border='0'></a></div>");
				document.write("<div style='position:absolute;z-index:2;top:182px;left:172px'><a href='http://www.37cs.com' target='_blank'><img src='http://www.37cs.com/html/click/logo.png' width='78' height='18' border='0'></a></div>");
				document.write("<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,28,0' width='250' height='200' style='cursor:hand;cursor:pointer;'>");
				document.write("<param name='movie' value='"+adlist[i].img+"'><param name='quality' value='high'><param name='wmode' value='Opaque'>");
				document.write("<embed wmode='Opaque' src='"+adlist[i].img+"' width='250' height='200' quality='high' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash'></embed>");
				document.write("</object>");
				} else {
				document.write("<a href='"+adlist[i].url+"&domain="+ref+"' target='_blank'>");
				document.write("<img src='"+adlist[i].img+"'width='250' height='200' border='0'/>");
				document.write("</a>");
				}
				</script>
				<div style="position:absolute;z-index:1">
				<a target="_blank" href="http://p.ggmm777.com/s/1/97/0.html?uid=507395&referer=http%3A%2F%2Fwww.2tu.cc%2FHtml%2FGP14225.html">
				<img width="250" height="200" border="0" src="http://www.37cs.com/html/click/dot.gif">
				</a>
				</div>
				<div style="position:absolute;z-index:2;top:182px;left:172px">
				<a target="_blank" href="http://www.37cs.com">
				<img width="78" height="18" border="0" src="http://www.37cs.com/html/click/logo.png">
				</a>
				</div>
				<object width="250" height="200" style="cursor:hand;cursor:pointer;" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,28,0" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000">
				<param value="http://p.49ko.com/b/1/97/250X200.swf?uid=507395" name="movie">
				<param value="high" name="quality">
				<param value="Opaque" name="wmode">
				<embed width="250" height="200" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" quality="high" src="http://p.49ko.com/b/1/97/250X200.swf?uid=507395" wmode="Opaque">
				</object>
				</body>
				</html>
				</iframe>
			</div>
			<div class="box mt10 newr1">
				<h1>热门推荐</h1>
				<ul style="height:403px;">
				<c:forEach items="${pageSuggest.result}" var="movie" varStatus="status" begin="1" end="14" step="1" >
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