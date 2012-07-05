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
                <img src="/imgs/doc/template1.jpg" alt="" width="250"/>
            </li>
            <li>
                <h4>模板二：</h4>
                <p>为左侧一个大图，右侧介绍的形式，较为适合品牌的推广，同样支持推应用和推网页。比较适合横屏的应用。</p>
                <img src="/imgs/doc/template2.jpg" alt=""  width="400"/>
                
            </li>
            <li>
                <h4>模板三：</h4>
                <p>做上方一个大图，下方的左侧是title和介绍，右侧为下载的按钮，支持应用和网页，比较适合竖屏的应用。</p>
                <img src="/imgs/doc/template3.jpg" alt="" />
                
            </li>
        </ul>
    </div>
    <%@include file="../include/footer.jsp"%>
    </div>
</body>
</html>




