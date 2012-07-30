<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="include/constants.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>友盟广告管理平台</title>
<link rel="shortcut icon" href="http://www.umeng.com/images/favicon.ico" />
<link  href="/css/default${build}.css?${constantVersion}" type="text/css" rel="stylesheet"/>
<link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
</head>
<body class="login" style="overflow:auto;"  onload="document.getElementById('name').focus();">
<!-- 独立头部 start -->
<div class="umengadsystem_header">

<div id="header">
<div class="con" stype="width: 950px;margin: 0 auto;">

<c:set var="logoUrl" value="http://www.umeng.com/images/image_updated/logo.png" scope="session" />
<c:set var="logoName" value="广告管理平台" scope="session" />
<c:if test="${user != null and user.logoUrl !=null and user.logoUrl!=''}" >
	<c:set var="logoUrl" value="${user.logoUrl} " scope="session" />
</c:if>
<c:if test="${user != null and user.logoName !=null and user.logoName!=''}" >
	<c:set var="logoName" value="${user.logoName} " scope="session" />
</c:if>
		<div class="logo" style="background-image: url('<c:out value="${logoUrl}"/>'); background-repeat: no-repeat; width: 135px; height: 32px; position: relative;">
			<a href="/"	style="width: 170px; height: 32px; position: absolute; display: block; right: -150px; top: 10px;">
			 ${logoName}
			</a>
		</div>
		<c:if test="${user != null}">
			<div class="opr">
				欢迎您, ${user.nickname }  | <a href="/login/logout">退出</a>
			</div>
		</c:if>

	</div>
</div>

</div>
    </div>
</div>
<!-- 独立头部 end -->
<!-- 正文 start -->
<div class="umengadsystem_container">
    <!-- login start -->
    <div id="message_div" class="umengadsystem_notice notice_wrong" <c:if test="${error==null}">style="display:none;"</c:if>/> <a href="#" class="btn_close" title="关闭" onclick="closeNotice(this);"></a> <span id="message_span">${error}</span> </div>
    <div class="login_banner">
        <div class="umengadsystem_login">
            <form action="/login/doLogin" method="post" name="form1" id="form1">
            <input type="hidden" name="returl" id="returl" value="${returl }"/>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" >
                <tr class="title">
                    <td>用户名</td>
                </tr>
                <tr class="content">
                    <td><input type="text" name="loginName" id="name" class="input_text" style="width:265px;" autofocus /></td>
                </tr>
                <tr class="title">
                    <td>密码</td>
                </tr>
                <tr class="content2">
                    <td>
                        <input type="password" id="passwd_in" name="passwd_in" class="input_text" style="width:265px;" />
                    </td>
                </tr>
                <tr class="content2">
                    <td>
                        <input type="checkbox" name="rmb_passwd" checked id="rmb_passwd"/><label for="remember_pwd">记住密码</label>
                        <a href="mailto:appnetwork@umeng.com" class="right">&gt;&gt;忘记密码？  </a>
                    </td>
                </tr>
            </table>
            <div class="btn_panel">
                <span class="middle">
                    <input id="btn_login" type="button" value="登   录"  class="btn_login"  onclick="beforeSubmit();return false;"/>
                </span>
                
            </div>
            </form>
        </div>
    </div>     
    <!-- login end -->
</div>
<!-- 正文end -->
<%@include file="include/footer.jsp"%>
<script type="text/javascript" src="/js/libs/jquery-1.6.1.min.js?${constantVersion}"></script>
<script type="text/javascript" src="/js/libs/base64${build}.js?${constantVersion}"></script>
<script type="text/javascript" src="/js/libs/jquery.md5${build}.js?${constantVersion}"></script>
<script type="text/javascript" src="/js/libs/util${build}.js?${constantVersion}"></script> 
<script type="text/javascript">
function beforeSubmit(){
    var name = $("#name").val();
    var pwd = $("#passwd_in").val();
    if(name!="" && pwd!=""){
        var md5pwd = $.md5(pwd);
        if ($('#rmb_passwd:checked').size() > 0){
     	   SetCookie('cas_login_name',name);
     	   SetCookie('cas_login_pwd',pwd);
        }else{
     	   SetCookie('cas_login_pwd','');
           SetCookie('cas_login_pwd','');
        }
        var url=window.location.href.substring(0,window.location.href.indexOf("/", 8))+"/login/doLogin";
        url=url+"?loginName="+name+"&passwd="+md5pwd+"&returl="+$("#returl").val();;
        window.location.href=url;
    }else{
        $("#message_div").show();
        $("#message_span").html("请填写用户名和密码!");
        return ;
    }
}
$(document).ready(function(){
	var logincookie = GetCookie('cas_login_name');
	var pwdcookie = GetCookie('cas_login_pwd');
	if(logincookie!=null){
	    document.getElementById('name').value=logincookie;
	    document.getElementById('passwd_in').value=pwdcookie;
	}  
    $(document).keydown(function (event){
    	if(event.keyCode==13){ 
    	  $("#btn_login").click(); 
    	}
    });
});

</script>
</body>
</html>
