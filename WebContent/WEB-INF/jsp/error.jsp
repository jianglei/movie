<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isErrorPage="true"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>友盟广告管理平台</title>
<link rel="shortcut icon" href="http://www.umeng.com/images/favicon.ico" />
<link rel="stylesheet" href="/css/default.css" />
<link href="/css/base.css" type="text/css" rel="stylesheet" />
<link href="/css/layout.css" type="text/css" rel="stylesheet" />
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body class="login"  onload="document.getElementById('name').focus();">
<!-- 独立头部 start -->
<div class="umengADsystem_header">

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
		<div class="logo"
			style="background-image: url('<c:out value="${logoUrl}"/>'); background-repeat: no-repeat; width: 135px; height: 32px; position: relative;">
			<a href="/"
				style="width: 170px; height: 32px; position: absolute; display: block; right: -150px; top: 10px;">
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
<div class="umengADsystem_container">
    <!-- login start -->
    
    <div class="umengADsystem_login">
    	出错了，请与管理员联系！
          
  <!--
  
错误码： <%=request.getAttribute("javax.servlet.error.status_code")%> <br>
信息： <%=request.getAttribute("javax.servlet.error.message")%> <br>
 异常： <%=request.getAttribute("javax.servlet.error.exception_type")%>  <br>
详细错误: 
<% exception.printStackTrace(response.getWriter());%>
 -->
 
        
    </div>
    <!-- login end -->
</div>
<!-- 正文end -->

<%@include file="include/footer.jsp"%>

</body>
</html>
