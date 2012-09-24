<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tlds/umeng-ufp.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="../include/constants.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <title>广告位管理 - 友盟广告管理平台</title>
            <link href="/css/shCoreDefault${build}.css?${constantVersion}" type="text/css" rel="stylesheet"/> 
            <link href="/css/jquery-ui-1.8.16.custom${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/adslot${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/time_choose${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="container">
            <%@include file="../include/header.jsp"%>
            <div id="main" style="padding-top:7px;">
                <div class="panel_choicelnk" style="float:left; padding-top:17px;display:none;">
                    今日目前效果数据：
                </div>
                <div class="clear"></div>
                <div style="height:23px; margin:17px 0  10px 0;">
                    <input type="button" class="btn_add_pos" value="添加广告位" style="float:left;" />
                    <input type="button" class="btn_add_code" value="获取代码" style="float:left; margin-left:10px;" />
                    <!--&nbsp;&nbsp;<a onclick="javascript:showMask()">打开遮罩</a>&nbsp;&nbsp;<a onclick="hideMask()">关闭遮罩</a>-->
                </div>

                <div class="clear"></div>
                <div class="panel_table" style="position:relative;">
                    <div class="loading" style="display:none;">
                        <div>
                            <div class="txt">正在刷新数据</div>
                            <div class="pic"><img src="/images/loading.gif" /></div>
                        </div>
                    </div>
                    <div class="part_operation" >
                        <div class="state">
                            <a  class="state_filter">状态筛选</a><span class="state_info"><span class="none">未筛选</span></span>
                            <div class="state_panel" style="display:none;">
                                <ul>
                                    <li><input type="checkbox" value="0" id="status_normal"/><label for="status_normal" class="filter normal">正常</label></li>
                                    <li><input type="checkbox" value="2" id="status_pause" /><label for="status_pause" class="filter pause">暂停</label></li>
    								<li><input type="checkbox" value="1" id="app_pause" /><label for="app_pause" class="filter pause">应用暂停</label></li>
                                    <li><input type="checkbox" value="9" id="platform_ios" /><label for="platform_ios" class="filter platform_ios">iOS</label></li>
                                    <li><input type="checkbox" value="10" id="platform_android" /><label for="platform_android" class="filter platform_android">Android</label></li>
                                    <!--								<li><input type="checkbox" value="3" id="status_complete"/><label for="status_complete" class="filter complete">订单完成</label></li>-->
                                    <li><input type="checkbox" value="all" id="status_all" /><label for="status_all" class="filter nofilter">&nbsp;&nbsp;不筛选</label></li>
                                </ul>
                                <div class="opr"><span class="btn_change_state"><a >确定</a></span></div>
                            </div>
                        </div>
                        <dl>
                            <dd class="play"><a status="normal" action="/adslot">启用</a></dd>
                            <dd class="pause"><a status="pause" action="/adslot">暂停</a></dd>
                            <dd class="delete"><a status="delete" action="/adslot">删除</a></dd>
                        </dl>
                    </div>
                    <div class="part_table">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb_list">
                            <tr class="tit">
                                <th width="35"><input type="checkbox" name=" " onclick="checkAll(this, 'record_ch');"/></th>
                                <th width="200">广告位名称</th>
                                <th width="50">平台</th>
                                <th width="100">状态</th>
                                <th>所属应用</th>
                                <th width="120">入口类型</th>
                                <th width="120">内容尺寸</th>
                                <th width="180" class="last">操作</th>
                            </tr>
                            <tr id="loading_row_init">
                                <td colspan="8">加载中...</td>
                            </tr>
                        </table>
                    </div>

                    <div class="part_operation part_operation_footer">
                        <div style="" id="pager" class="fr"></div>
                        <div class="fl f_csv">
<!--                            <a href="${exportUrl}">导出CSV</a>-->
                        </div>
                    </div>		
                </div>
            </div>
            <%@include file="../include/footer.jsp"%>
        </div>
        <!-- 广告位管理弹出层 begin-->  
        <div id="msg_wrap_form" class="msg_pos msgForm" style="display:none;">
            <div class="step1">
                <div class="msg_content_top" style="padding-left:10px; padding-top:10px;">
                    <div class="flow step1" style="width:680px;">
                        <ul>
                            <li><a >1.&nbsp;选择类型和设备</a></li>
                            <li><a >2.&nbsp;选择策略</a></li>
                        </ul>
                        <span class="fr close"></span>
                    </div>

                </div>

                <div class="msg_content_center">
					<input type="hidden" name="id" value="" />
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb_form" style="border-collapse:separate;">
                        <tr>
                            <th width="100"><span class="f_req">*</span>广告位名称：</th>
                            <td><input type="text" class="input_text" name="name" style="width:300px;" /></td>
                        </tr>
                        <tr>
                            <th><span class="f_req">*</span>入口类型：</th>
                            <td>
                                <a class="ui_radio ui_radio_entrance"  ><span adSlotLandingType="banner" class='banner'>横幅</span></a> 
                                <a class="ui_radio ui_radio_entrance"  ><span adSlotLandingType="custom" class='custom'>自定义入口</span></a>
                                <a class="ui_radio ui_radio_entrance"  ><span adSlotLandingType="bigimage" class='bigimage'>轮播大图</span></a><br /> 
                                <a class="ui_radio ui_radio_entrance"  ><span adSlotLandingType="embed" class='embed'>内嵌入口</span></a>
                                <a class="ui_radio ui_radio_entrance"  ><span adSlotLandingType="wap" class='wap'>网页</span></a>
                                <a class="ui_radio ui_radio_entrance"  ><span adSlotLandingType="text" class='text'>文字链</span></a><br />
                                <a class="ui_radio ui_radio_entrance"  ><span adSlotLandingType="push" class='push'>全屏广告</span></a>
                                <input type="hidden" name="landingType" value="" /><br />
                                <a href="support/docs/landingtype" target="_blank">点击查看类型介绍&nbsp;&gt;&gt;</a>
                            </td>
                        </tr>

                        <tr>
                            <th>所属应用：</th>
                            <td>
                                <div class="ui_select" id="apps" style="z-index:1;"> <a  class="btn_pulldown"></a> <span class="text">请选择应用</span>
                                    <div class="pop_menu">
                                        <ul>
                                        </ul>
                                    </div>
                                </div>
                                <input name="appName" type="hidden" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="f_req">*</span>适合机型：</th>
                            <td>
                                <a class="ui_radio ui_radio_device"  platform="iOS" device="iPhone"><span class="ios iPhone">iPhone</span></a> 
                                <a class="ui_radio ui_radio_device"  platform="android" device="Android" ><span class="android Android">Android</span></a>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <a class="ui_radio ui_radio_device"  platform="iOS" device="iPad" ><span class="ios iPad">iPad</span></a> 
                                <a class="ui_radio ui_radio_device"  platform="android" device="AndroidPad" ><span class="android AndroidPad">Android pad</span></a>
                                <input type="hidden" name="device" value=""/>
                                <input type="hidden" name="platform" value=""/>
                            </td>
                        </tr>
                        <tr class="push_options hd">
                            <th><span class="f_req">*</span>弹窗大小：</th>
                            <td>
                                <a class="ui_radio ui_radio_push" ><span val="100">全屏</span></a> 
                                <a class="ui_radio ui_radio_push" ><span val="80">80%屏幕</span></a><br/>
                                <a class="ui_radio ui_radio_push" ><span val="60">60%屏幕</span></a>
                                <a class="ui_radio ui_radio_push" ><span val="4">信息提示框</span></a>
                                <!-- <a href="">点击查看介绍&nbsp;&gt;&gt;</a> -->
                                <input type="hidden" name="opensize" id="opensize" value=""/>
                            </td>
                        </tr>
                    </table>


                </div>
                <div class="msg_content_bottom">
                    <span class="fr">
                        <input type="button" class="btn_save_continue" value="保存并继续" />
                    </span>
                    <!--
                    <a  class="icon_return">返回订单管理页面</a>
                    -->
                </div>
            </div>
            <div class="step2" style="display:none">
                <div class="msg_content_top" style="padding-left:10px; padding-top:10px;">
                    <div class="flow step2" style="width:680px;">
                        <ul>
                            <li><a >1.&nbsp;选择类型和设备</a></li>
                            <li><a >2.&nbsp;选择策略</a></li>
                        </ul>
                        <span class="fr close"></span>
                    </div>

                </div>

                <div class="msg_content_center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb_form">
                        <tr id="upload_rukou_pic">
                            <th width="100"><span class=""></span>上传入口图片：</th>
                            <td>
                                <form name="form" method="post" action="/ad/save" onsubmit="javascript: return true;" id="adslot_form" enctype="multipart/form-data">
                                    <table id="uploadpic">
                                        <tr>
                                            <td style="padding:0 15px;">
                                                <a class="choosefile"  >
                                                    <img src="/images/img_upload.gif" id="pic1_show" class="landingImagesimg pic1_show"  width=64 height=64/>
                                                    <input size="1" class="file_not_null" rule="ico|jpg|gif|png" feedback="pic1" name="file1" id="file1" value="" type="file" filesize="200" />
                                                </a>
                                            </td>
                                            <td style="padding:0 15px;">
                                                <a class="choosefile"  >
                                                    <img src="/images/img_upload.gif" id="pic2_show" class="landingImagesimg pic2_show" width=64 height=64/>
                                                    <input size="1" class="file_not_null" rule="ico|jpg|gif|png" feedback="pic2" name="file2" id="file2" value="" type="file" filesize="200" />
                                                </a>
                                            </td>
                                            <td style="padding:0 15px;">
                                                <a class="choosefile" >
                                                    <img src="/images/img_upload.gif" id="pic3_show" class="landingImagesimg pic3_show" width=64 height=64/>
                                                    <input size="1" class="file_not_null" rule="ico|jpg|gif|png" feedback="pic3" name="file3" id="file3" value="" type="file" filesize="200" />
                                                </a>
                                            </td>
                                            <td style="padding:0 15px;">
                                                <a class="choosefile"  >
                                                    <img src="/images/img_upload.gif" id="pic4_show" class="landingImagesimg pic4_show" width=64 height=64/>
                                                    <input size="1" class="file_not_null" rule="ico|jpg|gif|png" feedback="pic4" name="file4" id="file4" value="" type="file" filesize="200" />
                                                </a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="text-align:center"><a class="delete_pic"  >删除</a></td>
                                            <td style="text-align:center"><a class="delete_pic"  >删除</a></td>
                                            <td style="text-align:center"><a class="delete_pic"  >删除</a></td>
                                            <td style="text-align:center"><a class="delete_pic"  >删除</a></td>
                                        </tr>
                                        <input name="landingImages" id="landing_images" value="" type="hidden" />


                                        <input type="hidden" name="fileObject" id="adslot_form_fileObject" value="" />
                                        <input type="hidden" id="adslot_form_uploadType" name="uploadType" value="" />
                                        <input type="hidden" id="adslot_form_feedback" name="feedback" value="" />
                                        <input type="hidden" id="adslot_form_callback" name="callback" value="" />
                                        <input name="width"  id="adslot_form_width" type="hidden" value="480" />
                                        <input name="height" id="adslot_form_height" type="hidden" value="75" />

                                    </table>
                                </form>
                            </td>
                        </tr>
                        <tr class="template_options">
                            <th><span class="f_req">*</span>选择模板：</th>
                            <td>
                                <a class="ui_radio  ui_radio_wapTemplate"  fortab=".v_tmpl_list" ><span class="">竖屏模板</span></a> 
                                <a class="ui_radio custom_hide ui_radio_wapTemplate"  fortab=".h_tmpl_list" ><span class="">横屏模板</span></a>  
                                <a class="ui_radio  ui_radio_wapTemplate custom_show"  fortab=".vh_tmpl_list" ><span class="">横竖适配</span></a>   
                                <!-- <a class="ui_radio ui_radio_short ui_radio_wapTemplate"  ><span class="">模板三</span></a>   -->
                                <!-- <a class="ui_radio ui_radio_short ui_radio_wapTemplate" tpType="allimage"  ><span class="">模板四</span></a> -->  
                                <!-- <a class="ui_radio ui_radio_wapTemplate" tpType="vertical_bigimage"  ><span class="">模板三</span></a>  -->
                                <a href="support/docs/template" target="_blank">查看模版介绍&gt;&gt;</a>
                                <input type="hidden" name="template"  id="template" value=""/>
                                <div class="v_tmpl_list tmpl_list ">
                                    <ul>
                                        <li>
                                            <label for="t1"><input id="t1" type="radio" name="tmpl_list" tpType="applist"/>竖屏列表</label>
                                        </li>
                                        <li class="custom_hide hd">
                                            <label for="t2"><input id="t2" type="radio" name="tmpl_list" tpType="vertical_bigimage" />竖屏大图</label>
                                        </li>
                                    </ul>
                                </div>
                                <div class="h_tmpl_list tmpl_list hd">
                                    <ul>
                                        <li><label for="t3"><input id="t3" type="radio" name="tmpl_list" tpType="horizon_bigimage"/>横屏大图</label></li>
                                    </ul>
                                </div>
                                <div class="vh_tmpl_list tmpl_list hd">
                                    <ul>
                                        <li><label for="t4"><input id="t4" type="radio" name="tmpl_list" tpType="iconlist"/>全Icon列表</label></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="f_req">*</span>内容尺寸：</th>
                            <td>
                                <div class="ui_noselect landingSize" style="z-index:5;"> <span class="text">请选择尺寸</span>
                                    <!--                                                            <a  class="btn_pulldown"></a> -->
                                    <!--								<div class="pop_menu">
                                                                                                            <ul>
                                                                                                                    <li><a  onclick="$(&quot;input[name='landingSize']&quot;).val('202 x 55');">202 x 55</a></li>
                                                                                                                    <li><a  onclick="$(&quot;input[name='landingSize']&quot;).val('100 x 20');">100 x 20</a></li>
                                                                                                                    <li><a  onclick="$(&quot;input[name='landingSize']&quot;).val('200 x 30');">200 x 30</a></li>
                                                                                                                    
                                                                                                            </ul>
                                                                                                    </div>-->
                                </div>
                                <input type="hidden" name="landingSize" id="landingSize" value=""/>
                            </td>
                        </tr>
                        <tr class="banner_options">
                            <th><span class="f_req">*</span>展示间隔：</th>
                            <td>
                                <input type="text" placeholder="输入1到100之间的整数" class="input_text" id="interval" name="interval"  style="width:190px;"/>
                                <span class="tips ios_tips">(iOS用户请选择3.5及以上SDK版本)</span>
                                <!-- <input type="hidden" name="interval" id="interval" value="15"/> -->
                            </td>
                        </tr>
                        <tr class="banner_options">
                            <th><span class="f_req">*</span>动画方式：</th>
                            <td>
                                <div class="ui_select" id="anim_in" style="z-index:4;"> 
                                    <a  class="btn_pulldown"></a> <span class="text">请选择动画方式</span>
                                    <div class="pop_menu">
                                        <ul>
                                            <li><a  pointto="anim_in" content="0" >随机</a></li>
                                            <li><a  pointto="anim_in" content="1" >从左侧切入</a></li>
                                            <li><a  pointto="anim_in" content="2" >从右侧切入</a></li>
                                            <li><a  pointto="anim_in" content="3" >从上切入</a></li>
                                            <li><a  pointto="anim_in" content="4" >从下切入</a></li>
                                            <li><a  pointto="anim_in" content="6" >缩放</a></li>
                                            <li><a  pointto="anim_in" content="7" >旋转</a></li>
                                            <li class="ios_only"><a  pointto="anim_in" content="9" >翻书</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <span class="tips ios_tips">(iOS用户请选择3.5及以上SDK版本)</span>
                                <input name="anim_in" type="hidden" value=""/>
                            </td>
                        </tr>
                        <tr class="push_options pushStrategyOptions">
                            <th><span class="f_req">*</span>推送策略：</th>
                            <td>
                                <div class="ui_select" id="pushStrategy" style="z-index:3;"> 
                                    <a  class="btn_pulldown"></a> <span class="text">请选择推送策略</span>
                                    <div class="pop_menu">
                                        <ul>
                                            <li><a  pointto="pushStrategy" content="onceperday" >一天仅推送一次</a></li>
                                            <li><a  pointto="pushStrategy" content="whenadchange" >广告替换时推送</a></li>
                                            <li><a  pointto="pushStrategy" content="nolimit" >无限制</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <input name="pushStrategy" type="hidden" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="f_req">*</span>投放方式：</th>
                            <td>
                                <div class="ui_select" id="displayStrategy" style="z-index:2;"> 
                                    <a  class="btn_pulldown"></a> <span class="text">请选择投放方式</span>
                                    <div class="pop_menu">
                                        <ul>
                                            <li><a  pointto="displayStrategy" content="rotate" >多条广告轮播</a></li>
                                            <li><a  pointto="displayStrategy" content="prior" >仅显示优先级最高广告</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <input name="displayStrategy" type="hidden" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="f_req">*</span>文字大小：</th>
                            <td>
                                <div class="ui_select" id="textSizeAdSlot" style="z-index:1;"> 
                                    <a  class="btn_pulldown"></a> <span class="text">请选择字体大小</span>
                                    <div class="pop_menu">
                                        <ul>
                                            
                                        </ul>
                                    </div>
                                </div>
                                <input name="textSizeAdSlot" type="hidden" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="f_req">*</span>投放时段：</th>
                            <td >
                                <div class="show_pro"></div>

                            </td>
                        </tr>
                        <tr><td class="tit"></td><td style="display:none;" class="timeChoose">
                                
                            </td>

                        </tr>

                        <tr class="tip">
                            <th><span class="f_req">*</span>投放地域：</th>
                            <td style="position: relative;display:block;">
                                <a class="ui_radio ui_radio_regionadslot" data="1" ><span>所有地理位置</span></a> 
                                <a class="ui_radio ui_radio_regionadslot" data="2" ><span>特定地理位置</span></a>
                                <span class="comment adslot_special_comment"></span>
                                <div class="adslot_special special" style="display:none;">
                                    <%@include file="../include/region.jsp"%>
                                </div>
                            </td>
                        </tr>
                        <!--					<tr class="tip">
                                                                        <th></th>
                                                                        <td style="position: relative;display:block;">
                                                                                
                                                                        </td>
                                                                </tr>-->
                        <input type="hidden" name="adslotareas" id="adslot_areas" value="all"/>
                        <tr>
                            <th>高级设置：</th>
                            <td><div class="show_pro advance_set_show_pro"></div></td>
                        </tr>
                        <tr>
                            <!--						<th></th>-->
                            <td colspan="2" style="border: 1px dashed #5A9EE6;display:none;padding-bottom:10px; border-collapse: separate;">
                                <table class="pnl_pro" style="border-collapse: separate;">
    								<tr class="preload_ad">
                                        <th>是否缓存广告：</th>
                                        <td>
                                            <input type="radio" name="enablePreload"  val="yes" id="adcache_yes"/><label for="adcache_yes">是</label>
                                            <input type="radio" name="enablePreload" checked="checked" id="adcache_no" val="no"/><label for="adcache_no">否</label>
                                        </td>
                                    </tr> 
                                    <tr class="hd">
                                        <th>广告可否翻页：</th>
                                        <td>
                                            <input type="radio" name="enablePage" checked="checked" val="yes" id="adpage_yes"/><label for="adpage_yes">是</label>
                                            <input type="radio" name="enablePage" id="adpage_no" val="no"/><label for="adpage_no">否</label>
                                        </td>
                                    </tr>
                                    <tr class="new_ad_tips">
                                        <th>新广告提示：</th>
                                        <td>
                                            <input type="radio" name="enableNew"  id="adNew_yes" val="yes"/><label for="adNew_yes">是</label>
                                            <input type="radio" name="enableNew" checked="checked" id="adNew_no" val="no"/><label for="adNew_no">否</label>
                                        </td>
                                    </tr>
                                    <tr class="filter_app">
                                        <th>是否过滤已安装的app：</th>
                                        <td>
                                            <input type="radio" name="filter" checked="checked" id="filter_yes" val="1"/><label for="filter_yes">是</label>
                                            <input type="radio" name="filter"  id="filter_no" val="0"/><label for="filter_no">否</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>更新渠道：</th>
                                        <td>
                                            <input type="text" class="input_text" name="adslotChannels" style="width:300px;" placeholder="填写广告位想出现的渠道号，以逗号区分"/>
                                            <a href="support/docs/channel" target="_blank">渠道输入方法</a>
                                            <!--                                                                                <div style="color:#656565; margin-top:5px;">填写广告位想出现的渠道号，以逗号区分</div>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>广告网络接入：</th>
                                        <td>    
                                            <input type="hidden" id="accesstype" value="none"/>
                                            <a class="ui_radio ui_radio_netaccess"    accesstype="fill"><span>广告不足时补充</span></a>
                                            <a class="ui_radio ui_radio_netaccess"    accesstype="percent"><span>任何情况均补充</span></a><br />
                                            <!--										<a class="ui_radio ui_radio_jiaohuan"  ><span>友盟交换网络</span></a>-->
                                            <div class="accesstype_list">
                                                <div >
                                                    <input type="checkbox" id="ui_radio_jiaohuan" style="vertical-align:middle;"/><label for="ui_radio_jiaohuan">友盟交换网络</label>
                                                    <label>流量占比:&nbsp;&nbsp;</label><input type="text" class="input_text" id="xppercent" style="width:30px;"/>
                                                    <label>%&nbsp;&nbsp;appkey:&nbsp;&nbsp;</label><input style="width:180px;" class="input_text" id="appkey" type="text" placeholder="请输入24位正确key"/>
                                                </div>
                                                <div>
                                                    <input type="checkbox" id="ui_radio_uads" style="vertical-align:middle;"/><label for="ui_radio_uads">友盟广告网络</label>
                                                    <label>流量占比:&nbsp;&nbsp;</label><input type="text" class="input_text" id="uadsPercent" style="width:30px;"/>
                                                    <label>%&nbsp;&nbsp;appkey:&nbsp;&nbsp;</label><input style="width:180px;" class="input_text" id="uadsKey" type="text" placeholder="请输入24位正确key"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>

                </div>
                <div class="msg_content_bottom">
                    <span class="fr">
                        <input type="button" class="btn_save_adslot btn_save" value="保存" />
                    </span>
                    <a href="javascript:void(0)" onclick="$('.step2').hide();$('.step1').show();adjustMsg();" class="icon_return">返回选择类型和设备页面</a>
                </div>
            </div>
        </div>
        <!-- 广告位管理弹出层 end-->  
        <!-- 获取代码弹出层 begin-->   
        <div id="get_code" class="box_shadow10px msgForm" style="display:none;">
            <span class="fr close"><img src="/images/get_code_close.png" alt=""></span>
            <table id="choose_adslot">
                <tbody>
                    <tr><td class="thead" colspan="4">选择广告位</td></tr>
                    <tr class="border_bottom">
                        <th width="140">广告位名称</th>
                        <th class="border_left">应用</th>
                        <th class="border_left">尺寸</th>
                        <th width="130" class="border_left"></th>
                    </tr>
                    <tr>
                        <td colspan="4" height="270" class="c_adslot_body">
                            <div style="height:270px;overflow-y:auto;overflow-x:hidden;">
                                <table id="choose_adslot_item">

                                    <tr class="border_bottom">
                                        <td>123</td>
                                        <td>默认频道</td>
                                        <td>960x90</td>
                                        <td><input class="get_code_btn green_btn" type="button" value="获取代码>>"/></td>
                                    </tr>
                                </table>
                            </div>
                        </td>   
                    </tr>

                    <!--            <tr class="border_top">
                                    <td colspan="4" class="tfoot">
                                      <ul class="c_ad_page">
                                        <li><a><上一页</a></li>
                                        <li><a>1</a></li>
                                        <li><a>下一页></a></li>
                                      </ul>  
                                        <div class="c_ad_page_detail"><span>1-1条</span><span>(共1条)</span></div>
                                    </td>
                                </tr>-->


                </tbody>
            </table>
            <div class="middle_ico">>></div>
            <table id="adslot_code">
                <tbody>
                    <tr>
                        <td class="thead" colspan="4">
                            广告位代码
                            <div class="maxwin" title="最大化窗口" >
                                <div class="frame"></div>
                                <div class="frame" style="right:3px;top:3px;"></div>
                            </div>
                            <div class="minwin" title="还原窗口">
                                <hr style="width: 13px;border: 1.5px solid white;"></hr>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class='c_adslot_body' height="302">
                           <%@include file="getcode.jsp"%>
                        </td>
                    </tr>
                    <!-- <tr class="border_top">
                                        <td class="tfoot">
                                          共0条  
                                        </td>
                                    </tr>-->
                </tbody>
            </table>
        </div>
        <!-- 获取代码弹出层 end--> 
        <%@include file="../include/add_ad.jsp"%>
        <script src="/js/libs/jquery.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/jquery-ui-1.8.16.custom.min.js?${constantVersion}" type="text/javascript" ></script>
        <script src="/js/libs/jquery-ui-timepicker-addon.min.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/iColorPicker${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/fontPicker${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/brush/shCore${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/brush/shBrushJava${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/brush/shBrushXml${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/jquery.blockUI${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/list_util${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/libs/util${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/region${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/add_ad${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/adlist${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/modules/timeChoose${build}.js?${constantVersion}" type="text/javascript"></script>
        <script src="/js/adslot/adslot${build}.js?${constantVersion}" type="text/javascript"></script>
        <%@include file="../adorder/adlist.jsp"%>
        <div id="uploadDiv" style="display: none">
            <iframe id="uploadFrame" name="uploadFrame"></iframe>
        </div>
        <%@include file="../include/bottom.jsp"%>
    </body>
</html>


