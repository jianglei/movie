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
            <title>广告管理页面 - 友盟广告管理平台</title>
            <link href="/css/jquery-ui-1.8.16.custom${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="container">
            <%@include file="../include/header.jsp"%>

            <div id="main">
                <div class="panel_btn_top">
                    <input type="button" class="btn_add_ad" value="添加广告" />
                </div>
                <div class="panel_table">
                    <div class="part_operation">
                        <div class="state">
                            <a href="#" class="state_filter">状态筛选</a>
                            <span class="state_info"><span	class="none">未筛选</span></span>
                            <div class="state_panel" style="display: none;">
                                <ul>
                                    <li><input type="checkbox" value="0" id="status_normal"/><label for="status_normal" class="filter normal">正常</label></li>
                                    <li><input type="checkbox" value="3" id="status_top" /><label for="status_top" class="filter top">到达预算</label></li>
                                    <li><input type="checkbox" value="2" id="status_pause" /><label for="status_pause" class="filter pause">暂停</label></li>
                                    <li><input type="checkbox" value="4" id="adorder_pause" /><label for="adorder_pause" class="filter pause">订单暂停</label></li>
                                    <li><input type="checkbox" value="5" id="status_complete"/><label for="status_complete" class="filter complete">投放完成</label></li>
                                    <li><input type="checkbox" value="6" id="status_adorder_complete" /><label for="status_adorder_complete" class="filter complete">订单投放完成</label></li>
                                    <li><input type="checkbox" value="7" id="status_ready"/><label for="status_ready" class="filter ready">尚未投放</label></li>
                                    <li><input type="checkbox" value="8" id="status_adorder_ready" /><label for="status_adorder_ready" class="filter ready">订单尚未投放</label></li>
                                     <li><input type="checkbox" value="9" id="platform_ios" /><label for="platform_ios" class="filter platform_ios platform">iOS</label></li>
                                    <li><input type="checkbox" value="10" id="platform_android" /><label for="platform_android" class="filter platform_android platform">Android</label></li>
                                    <li><input type="checkbox" value="all" id="status_all" /><label for="status_all" class="filter nofilter">&nbsp;&nbsp;不筛选</label></li>
                                </ul>
                                <div class="opr">
                                    <span class="btn_change_state"><a href="#">确定</a></span>
                                </div>
                            </div>
                        </div>
                        <dl>
                            <dd class="play"><a status="normal" action="/ad">启用</a></dd>
                            <dd class="pause"><a status="pause" action="/ad">暂停</a></dd>
                            <dd class="delete"><a status="delete" action="/ad">删除</a></dd>
                        </dl>
                    </div>
                    <div class="part_table">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb_list" class="adList">
                            <tr class="tit">
                                <th width="40"><input type="checkbox" name=" "  onclick="checkAll(this, 'record_ch');" /></th>
                                <th>广告名</th>
                                <th width="50">平台</th>
                                <th width="130">所属广告位</th>
                                <th width="155">开始日期</th>
                                <th width="155">结束日期</th>
                                <th width="100">优先级</th>
                                <th width="125">状态</th>
                                <th class="last" width="100">操作</th>
                            </tr>
                            <tr id="loading_row_init">
                                <td colspan="9">加载中...</td>
                            </tr>
                        </table>
                    </div>
                    <div class="part_operation part_operation_footer">
                        <div id="pager" class="fr"></div>
                        <div class="fl f_csv">
                            <!--						<a href="${exportUrl}">导出CSV</a>-->
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../include/footer.jsp"%>
        </div>
        <%@include file="../include/add_ad.jsp"%>
        <script src="/js/libs/jquery.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/jquery-ui-1.8.16.custom.min.js?${constantVersion}" type="text/javascript"></script>
        <!--<script src="/js/jquery.validate.min.js" type="text/javascript"></script>-->
        <script src="/js/libs/jquery.blockUI${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/iColorPicker${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/fontPicker${build}.js?${constantVersion}" type="text/javascript"></script>
        <!--<script src="/js/tools${build}.js?${constantVersion}" type="text/javascript"></script>-->
        <script src="/js/libs/jquery-ui-timepicker-addon${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/list_util${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/util${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/region${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/add_ad${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/ad/ad${build}.js?${constantVersion}" type="text/javascript"></script>
    </body>
</html>

<%@include file="../include/bottom.jsp"%>