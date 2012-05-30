<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld"%>
<%@ taglib prefix="w" uri="/WEB-INF/tlds/umeng-ufp.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <%@include file="../include/constants.jsp"%>
            <title>订单页面 - 友盟广告管理平台</title>
            <link href="css/jquery-ui-1.8.16.custom${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="container">
            <%@include file="../include/header.jsp"%>
            <div id="main">
                <div class="panel_btn_top">
                    <input type="button" class="btn_add_order" value="添加订单" />
                </div>
                <div class="panel_table" style="position: relative;">
                    <div class="loading" style="display: none;">
                        <div>
                            <div class="txt">正在刷新数据</div>
                            <div class="pic">
                                <img src="images/loading.gif" />
                            </div>
                        </div>
                    </div>

                    <div class="part_operation">
                        <div class="state">
                            <a href="#" class="state_filter">状态筛选</a><span class="state_info"><span
                                    class="none">未筛选</span></span>
                            <div class="state_panel" style="display: none;">
                                <ul>
                                    <li><input type="checkbox" value="0" id="status_normal"/><label for="status_normal" class="filter normal">正常</label></li>
     								<!-- <li><input type="checkbox" value="1" id="status_top"/><label for="status_top" class="filter top">到达预算</label></li>  -->
                                    <li><input type="checkbox" value="2" id="status_pause" /><label for="status_pause" class="filter pause">暂停</label></li>
                                    <li><input type="checkbox" value="5" id="status_complete"/><label for="status_complete" class="filter complete">投放完成</label></li>
                                    <li><input type="checkbox" value="7" id="status_ready"/><label for="status_ready" class="filter ready">尚未投放</label></li>
                                    <li><input type="checkbox" value="all" id="status_all" /><label for="status_all" class="filter nofilter">&nbsp;&nbsp;不筛选</label></li>
                                </ul>
                                <div class="opr">
                                    <span class="btn_change_state"><a href="#">确定</a></span>
                                </div>
                            </div>
                        </div>
                        <dl>
                            <dd class="play"><a status="normal" action="/adorder">启用</a></dd>
                            <dd class="pause"><a status="pause" action="/adorder">暂停</a></dd>
                            <dd class="delete"><a status="delete" action="/adorder">删除</a></dd>
                        </dl>
                    </div>
                    <div class="part_table">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb_list">
                            <tr class="tit">
                                <th width="10"><input type="checkbox" name=" " onclick="checkAll(this, 'record_ch');"/></th>
                                <th>订单名称</th>
                                <th width="100">状态</th>
                                <th>广告客户</th>
                                <th width="180">开始日期</th>
                                <th width="180">结束日期</th>
                                <!--							<th width="250">预算/使用率</th>-->
                                <th class="last" width="190">操作</th>
                            </tr>
                            <tr id="loading_row_init">
                                <td colspan="8">加载中...</td>
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
        <div id="msg_wrap_form" class="msg_order" style="display: none;">
            <input type="hidden" name="id" />
            <div class="msg_content_top">
                <h3 class="title">
                    <span class="fl label">新订单</span> <span class="fr close"></span>
                    <div class="clear"></div>
                </h3>
            </div>
            <div class="msg_content_center">
                <h4>基本信息</h4>
                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                       class="tb_form">
                    <tr>
                        <th width="120"><span class="f_req">*</span>订单名称：</th>
                        <td><input type="text" class="input_text" name="name" style="width: 364px;" /></td>
                    </tr>
                    <tr>
                        <th>广告客户：</th>
                        <td><input type="text" class="input_text" style="width: 364px;" name="customer" /></td>
                    </tr>
                    <tr>
                        <th><span class="f_req">*</span>开始日期：</th>
                        <td>
                            <input type="text" class="input_text" name="startTime" id="datepicker_start_adorder" />
                            <span style="font-weight: bold; padding-left: 20px;">
                                结束日期：</span>
                            <input type="text" class="input_text" name="endTime" id="datepicker_end_adorder" />
                            <!--<input type="text" class="input_text" style="width: 320px;" value="日期不限" name="endTime" />-->
                        </td>
                    </tr>
                    <tr>
                        <th>说明：</th>
                        <td><textarea style="width: 374px; height: 50px;" rows=" " cols=" " class="input_textarea" name="comments"></textarea></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td></td>
                    </tr>
                </table>
                <!--				<h4>公司信息</h4>
                                                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                                                        class="tb_form">
                                                        <tr>
                                                                <th width="120"><span class="f_req">*</span>广告客户：</th>
                                                                <td>
                                                                        <div class="ui_select" style="z-index: 5; float: left;">
                                                                                <a href="javascript:void(0);" class="btn_pulldown"></a> <span
                                                                                        class="text">请选择</span>
                                                                                <div class="pop_menu">
                                                                                        <ul>
                                                                                                <li><a href="#">广告客户1</a></li>
                                                                                                <li><a href="#">广告客户2</a></li>
                                                                                        </ul>
                                                                                </div>
                                                                        </div> <span
                                                                        style="float: left; padding-left: 20px; font-weight: bold; padding-right: 15px;">代理机构：</span>
                                                                        <div class="ui_select" style="z-index: 4; float: left;">
                                                                                <a href="javascript:void(0);" class="btn_pulldown"></a> <span
                                                                                        class="text">请选择</span>
                                                                                <div class="pop_menu">
                                                                                        <ul>
                                                                                                <li><a href="#">代理机构1</a></li>
                                                                                                <li><a href="#">代理机构2</a></li>
                                                                                        </ul>
                                                                                </div>
                                                                        </div>
                                                                </td>
                                                        </tr>
                                                        <tr>
                                                                <th></th>
                                                                <td></td>
                                                        </tr>
                                                </table>-->
                <!--				<h4>人员信息</h4>
                                                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                                                        class="tb_form">
                                                        <tr>
                                                                <th width="120"><span class="f_req">*</span>广告客户联系人：</th>
                                                                <td>
                                                                        <div class="ui_select" style="z-index: 3;">
                                                                                <a href="javascript:void(0);" class="btn_pulldown"></a> <span
                                                                                        class="text">请选择</span>
                                                                                <div class="pop_menu">
                                                                                        <ul>
                                                                                                <li><a href="#">人员信息1</a></li>
                                                                                                <li><a href="#">人员信息2</a></li>
                                                                                        </ul>
                                                                                </div>
                                                                        </div>
                                                                </td>
                                                        </tr>
                                                        <tr>
                                                                <th><span class="f_req">*</span>代理机构联系人：</th>
                                                                <td>
                                                                        <div class="ui_select" style="z-index: 2;">
                                                                                <a href="javascript:void(0);" class="btn_pulldown"></a> <span
                                                                                        class="text">请选择</span>
                                                                                <div class="pop_menu">
                                                                                        <ul>
                                                                                                <li><a href="#">代理机构联系人1</a></li>
                                                                                                <li><a href="#">代理机构联系人2</a></li>
                                                                                        </ul>
                                                                                </div>
                                                                        </div>
                                                                </td>
                                                        </tr>
                                                        <tr>
                                                                <th><span class="f_req">*</span>其他联系人：</th>
                                                                <td>
                                                                        <div class="ui_select" style="z-index: 1;">
                                                                                <a href="javascript:void(0);" class="btn_pulldown"></a> <span
                                                                                        class="text">请选择</span>
                                                                                <div class="pop_menu">
                                                                                        <ul>
                                                                                                <li><a href="#">其他联系人1</a></li>
                                                                                                <li><a href="#">其他联系人2</a></li>
                                                                                        </ul>
                                                                                </div>
                                                                        </div>
                                                                </td>
                                                        </tr>
                                                        <tr>
                                                                <th></th>
                                                                <td></td>
                                                        </tr>
                                                </table>
                                                <h4>订单属性</h4>
                                                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                                                        class="tb_form">
                                                        <tr>
                                                                <th width="120"><span class="f_req">*</span>销售人员：</th>
                                                                <td>
                                                                        <div class="ui_select" style="z-index: 1;">
                                                                                <a href="javascript:void(0);" class="btn_pulldown"></a> <span
                                                                                        class="text">请选择</span>
                                                                                <div class="pop_menu">
                                                                                        <ul>
                                                                                                <li><a href="#">销售人员1</a></li>
                                                                                                <li><a href="#">销售人员2</a></li>
                                                                                        </ul>
                                                                                </div>
                                                                        </div>
                                                                </td>
                                                        </tr>
                                                </table>-->
            </div>
            <div class="msg_content_bottom">
                <span class="fr"> <input type="button" class="btn_save_order btn_save" value="保存" />
                </span>
            </div>
        </div>
        <%@include file="../include/add_ad.jsp"%>
        <script src="/js/libs/jquery.js?${constantVersion}" type="text/javascript"></script>
        <!--<script src="/js/ui.js?${constantVersion}" type="text/javascript"></script>-->
        <script src="/js/libs/jquery.blockUI${build}.js?${constantVersion}" type="text/javascript"></script>
        <!--<script src="/js/tools.js?${constantVersion}" type="text/javascript"></script>-->
        <script src="/js/libs/jquery-ui-1.8.16.custom.min.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/iColorPicker${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/fontPicker${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/jquery-ui-timepicker-addon${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/list_util${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/util${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/region${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/add_ad${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/adorder/adorder${build}.js?${constantVersion}" type="text/javascript"></script>
        <%@include file="adlist.jsp"%>
        <script type="text/javascript">
       
        </script>
    </body>
</html>


<%@include file="../include/bottom.jsp"%>
