<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../include/constants.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单报表 - 广告管理平台</title>
<link href="/css/jquery-ui-1.8.16.custom${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
</head>
<body>
<div id="container">
<%@include file="../include/header.jsp"%>
<%@include file="html.jsp"%>
<%@include file="../include/footer.jsp"%>	
</div>
<%@include file="report_tmpl.jsp"%>
<script type="text/javascript">
	$(function() {
            var param = {resultInterface:"page",dataUrl:"/report/adorder",listUrl:"/adorder/list",name:'adorder'},
            report = new Report(param);
            report.initUI();
            report.loadList();
//            report.loadData(1);
	})
</script>
</body>
</html>

<%@include file="../include/bottom.jsp"%>