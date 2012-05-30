<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../include/constants.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>UFP - 广告样式说明文档</title>
<link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/doc${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
</head>
<body>
    <div id="container">
    <%@include file="../include/header.jsp"%>
    <div id="main">
        <ul class="listDoc">
            <li>
                <h4>渠道输入方法：</h4>
                <p>若您只想在某些渠道出现，则直接输入这些渠道的渠道号即可，用逗号区分。</p>
                <p>若你不想在某些渠道出现，则输入{渠道号}即可。</p>
            </li>
            <li>
                <h4>例如：</h4>
                <p>若该广告只想在安智出现，假设在sdk里输入的安智渠道号是anzhi，则在后台输入anzhi。</p>
                <p>若该广告不想在应用汇出现，假设在sdk里输入的应用汇的渠道号是appchina，则输入{appchina}。</p>
            </li>
    </div>
    <%@include file="../include/footer.jsp"%>
    </div>
</body>
</html>




