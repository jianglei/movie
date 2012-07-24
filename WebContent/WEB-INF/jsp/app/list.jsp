<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld"%>
<%@ taglib prefix="w" uri="/WEB-INF/tlds/umeng-ufp.tld"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../include/constants.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>广告位应用 - 友盟广告管理平台</title>
<link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
<link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
</head>
<body>
    <div id="container">
        <%@include file="../include/header.jsp"%>
    
        <div id="main">
            <div class="panel_btn_top"><input type="button" class="btn_add_app" value="添加应用" /></div>
            <div class="panel_table"  style="position:relative;">
                <div class="loading" style="display:none;">
                    <div>
                        <div class="txt">正在刷新数据</div>
                        <div class="pic"><img src="images/loading.gif" /></div>
                    </div>
                </div>
                <div class="part_operation" >
                    <div class="state">
                        <a href="#" class="state_filter">状态筛选</a><span class="state_info">
                            <span class="none">未筛选</span></span>
                        <div class="state_panel" style="display:none;">
                            <ul>
                                <li><input type="checkbox" value="0" id="status_normal"/><label for="status_normal" class="filter normal">正常</label></li>
                                <!--								<li><input type="checkbox" value="1" id="status_top"/><label for="status_top" class="filter top">到达预算</label></li>-->
                                <li><input type="checkbox" value="2" id="status_pause" /><label for="status_pause" class="filter pause">暂停</label></li>
                                <!--                                                                <li><input type="checkbox" value="3" id="status_complete"/><label for="status_complete" class="filter complete">订单完成</label></li>-->
                                <li><input type="checkbox" value="all" id="status_all" /><label for="status_all" class="filter nofilter">&nbsp;&nbsp;不筛选</label></li>
                            </ul>
                            <div class="opr"><span class="btn_change_state"><a href="#">确定</a></span></div>
                        </div>
                    </div>
                    <dl>
                       <dd class="play"><a status="normal" action="/app">启用</a></dd>
                        <dd class="pause"><a status="pause" action="/app">暂停</a></dd>
                        <dd class="delete"><a status="delete" action="/app">删除</a></dd>
                    </dl>
                </div>
                <div class="part_table">
                    <table id="tb_list" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr class="tit">
                            <th width="35"><input type="checkbox" name=""  onclick="checkAll(this, 'record_ch');"/></th>
                            <th>应用名</th>
                            <th width="150">状态</th>
                            <th width="100">平台</th>
                            <th width="100">广告位数</th>
                            <!--						<th>最近7天日均收益</th>-->
                            <th class="last" width="150">操作</th>
                        </tr>
                        <tr id="loading_row_init">
                            <td colspan="7">加载中...</td>
                        </tr>
                    </table>
                </div>
                <div class="part_operation part_operation_footer">
                    <div id="pager" class="fr"></div>
                    <div class="fl f_csv">
<!--                        <a href="${exportUrl}">导出CSV</a>-->
                    </div>
                </div>
            </div>
        </div>
            
        <%@include file="../include/footer.jsp"%>	
    
    </div>
    
    <div id="msg_wrap_form" class="msg_app msgForm" style="display:none;">
        <div class="msg_content_top">
            <h3 class="title">
                <span class="fl label" style="padding-left:8px;">新建应用</span>
                <span class="fr close"></span>
                <div class="clear"></div>
            </h3>
        </div>
        <div class="msg_content_center">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb_form">
                <tr>
                    <th width="100"><span class="f_req">*</span>应用名：</th>
                    <td><input type="text" class="input_text" name="name" style="width:300px;" /></td>
                </tr>
                <tr>
                    <th><span class="f_req">*</span>平台：</th>
                    <td>
                        <a class="ui_radio ui_radio_os" href="javascript:void(0)" onclick="$('input[name=platform]').val('iOS');"><span class="ios">iOS</span></a> 
                        <a class="ui_radio ui_radio_os" href="javascript:void(0)" onclick="$('input[name=platform]').val('android');"><span class="android">Android</span></a>
                        <input type="hidden" name="platform" />
                    </td>
                </tr>
                <tr>
                    <th width="100">备注：</th>
                    <td><textarea style="width:320px; height:50px;" rows=" " cols=" " name="description" class="input_textarea"></textarea></td>
                </tr>
                <tr>
                    <th></th>
                    <td></td>
                </tr>
            </table>
        </div>
        <div class="msg_content_bottom">
            <span class="fr">
                <input type="button" class="btn_save" value="保存" />
            </span> 
        </div>
    </div>
    <script src="/js/libs/jquery.js?${constantVersion}" type="text/javascript"></script>
    <script src="/js/libs/jquery-ui-1.8.16.custom.min.js?${constantVersion}" type="text/javascript"></script>
    <script src="/js/libs/jquery.blockUI${build}.js?${constantVersion}" type="text/javascript"></script>
    <script src="/js/libs/list_util${build}.js?${constantVersion}" type="text/javascript"></script>
    <script src="/js/libs/jquery.validate.min.js?${constantVersion}" type="text/javascript"></script>
    <script src="/js/app/app.js?${constantVersion}" type="text/javascript"></script>
</body>
</html>

<%@include file="../include/bottom.jsp"%>
