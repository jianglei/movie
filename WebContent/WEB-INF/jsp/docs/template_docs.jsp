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
                <h4>模板一：</h4>
                <p>为list样式，可支持推应用和推网页。</p>
                <img src="/imgs/doc/template1.jpg" alt="" />
            </li>
            <li>
                <h4>模板二：</h4>
                <p>为左侧一个大图，右侧介绍的形式，较为适合品牌的推广，同样支持推应用和推网页。</p>
                <img src="/imgs/doc/template2.jpg" alt="" />
                
            </li>
        </ul>
    </div>
    <%@include file="../include/footer.jsp"%>
    </div>
</body>
</html>




