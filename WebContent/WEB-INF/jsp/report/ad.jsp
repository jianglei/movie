<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../include/constants.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>应用报表 - 广告管理平台</title>
<link href="/css/jquery-ui-1.8.16.custom${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
</head>
<body>
<div id="container">
<%@include file="../include/header.jsp"%>
<%@include file="html.jsp"%>
<div id="msg_wrap_form" style="" class="down_csv_ad hd">
    <div class="msg_content_top">
        <h3 class="title">
            <span class="fl label" style="padding-left: 8px;">导出数据</span> <span
                class="fr close"></span>
            <div class="clear"></div>
        </h3>
    </div>

    <div class="msg_content_center">
        <ul class="csv_choose">
        	<li>
        		<label ><input url="ad_export/impression" type="checkbox" checked/>展示</label>
        	</li>
    		<li>
    			<label ><input url="ad_export/click" type="checkbox" checked/>点击</label>
    		</li>
    		<li>
    			<label ><input url="ad_export/download" type="checkbox" checked/>下载</label>
    		</li>
    		<li>
    			<label ><input url="ad_export/clickRate" type="checkbox" checked/>点击率</label>
    		</li>
    		<li>
    			<label ><input url="ad_export/conversionRate" type="checkbox" checked/>转化率</label>
    		</li>
    		<li>
    			<label ><input url="ad_export/revenue"  type="checkbox" checked/>收益</label>
    		</li>
        </ul>
    </div>
    <div class="msg_content_bottom">
        <span class="fr">
            <input type="button" class="btn_download_csv btn_save" value="保存" />
        </span>
    </div>
</div>		
<%@include file="../include/footer.jsp"%>
</div>
<%@include file="report_tmpl.jsp"%>
<script type="text/javascript" src="/js/libs/jquery.blockUI.min.js?${constantVersion}"></script>
<script type="text/javascript">
function popBox(popFrame){
	var height = $(window).height(),
    width = $(document).width(),
    codeWidth= popFrame.width(),
    codeHeight= popFrame.height();
    
    $.blockUI({
        css: {color: '#cccccc',border:'0',width:codeWidth+'px',height:codeHeight+'px','left' : width/2 - (codeWidth / 2),'top' : height/2 - ( codeHeight/ 2),background:'none',padding:'0px'},
        message: popFrame
        //onBlock:function(){$('.blockMsg').draggable()}
    });
}
	$(function() {
            var param = {resultInterface:"adPage",dataUrl:"/report/ad",listUrl:"/ad/list",name:'ad'},
            report = new Report(param);
            report.initUI();
            report.loadList();
//            report.loadData(1);
         $('.output_csv').click(function(){
         	var popFrame = $('#msg_wrap_form');
         	popBox(popFrame);
         });
         $(".close").unbind('click').click(function() {
	        $.unblockUI();
		});	
         $('.btn_download_csv').click(function(){
         	var param = "?";
         	var preriodBody = $('.panel_choicelnk a');
         	var currentPeriod = preriodBody.filter('.current');
         	if(currentPeriod.is('.btn_choice_time')){
         		param += "startDate="+$('#cus_start_time').val();
         		param += "&endDate="+$('#cus_end_time').val();
         	}else{
         		param += "period="+currentPeriod.attr('period');
         	}
         	$('.down_csv_ad .csv_choose li input:checked').each(function(){
         		window.open($(this).attr('url')+param,'_blank');
         	});
         });
	});
</script>
</body>
</html>

<%@include file="../include/bottom.jsp"%>