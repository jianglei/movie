<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="msg_wrap_form" class="msg_ad msgForm" style="display: none;">
    <input type="hidden" name="adid" />
    <div class="step1">
        <div class="msg_content_top"
             style="padding-left: 10px; padding-top: 10px;">
            <div class="flow step1" style="width: 680px;">
                <ul>
                    <li><a href="#">1.&nbsp;选择类型和设备</a></li>
                    <li><a href="#">2.&nbsp;内容编辑</a></li>
                </ul>
                <span class="fr close"></span>
            </div>
 
        </div>

        <div class="msg_content_center">
            <table width="100%" border="0" cellspacing="0" cellpadding="0"
                   class="tb_form">
                <tr>
                    <th width="100"><span class="f_req">*</span>广告名称：</th>
                    <td><div class="list_order "><strong tabindex="2">所属订单：</strong><select id="list_order"></select></div>
                        <input type="text" class="input_text" name="adname"  placeholder="不能多于16个字符" title="不能多于16个字符"   style="width: 300px;" tabindex="1"/></td>

                </tr>
                <tr>
                    <th><span class="f_req">*</span>所属广告位：</th>
                    <td>
                        <div class="tb_inner_wrap">
                            <table class="tb_inner">
                                <tr>
                                    <th width="1"><input type="checkbox"
                                                         onclick="checkAllAdSlot(this, 'record_adslot');" /></th>
                                    <th>广告位名称</th>
                                    <th>入口类型</th>
                                    <th>所属应用</th>
                                    <th>适应平台</th>
                                    <th>内容尺寸</th>
                                </tr>
                                <!--
                                        <tr>
                                                <td colspan="9" class="op
                                                r">
                                                        <span class="pager">1-3条(共3条)&nbsp;&nbsp;&nbsp;&nbsp;第<select><option>1</option><option>2</option></select>页</span>
                                                </td>
                                        </tr>
                                -->
                            </table>
                            <div style="height: 90px; overflow-y: auto;">
                                <table class="tb_inner" id="list_adslot">
                                    <tr>
                                        <td>
                                         loading...   
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <input class="" type="hidden" id="li_adslot" name="li_adslot" value=""/>
                    </td>
                </tr>
                <tr>
                    <th>投放时间：</th>
                    <td><span class="f_req">*</span>开始：<input class="input_text" type="text"  id="datepicker_ad_start" name="startTime" />
                        &nbsp;&nbsp;&nbsp;结束：<input class="input_text" type="text" placeholder="不设置" id="datepicker_ad_end" name="endTime" /><br /> 
                    </td>
                </tr>
                <!--					<tr>
                                                <th></th>
                                                <td>
                                                    <span style="padding-left: 10px; color: gray;">如果投放时间为不连续的多个时段，<a href="#">请点击这里选择</a></span>
                                                </td>
                                        </tr>-->
                <tr>
                    <th><span class="f_req">*</span>每日预算：</th>
                    <td><label><input type="radio" name="budget" value="none" />不限定预算&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <label><input   type="radio" name="budget" value="limit" />限定预算&nbsp;</label>￥<input class="input_text" type="text" name="budgetLimit" disabled /></td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <div style="float: left;">
                            <a class="ui_radio ui_radio_pre"  pricetype="impression"><span>按展示计费</span></a>
                        </div>
                        <div style="float: left; padding-left: 20px; ">
                            <span class="blue">千次</span>展现价格： 
                            <input type="text" disabled  class="input_text" value="" id="price_input_impression"  style="width: 50px;"/>&nbsp;元
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <div style="float: left;">
                            <a class="ui_radio ui_radio_pre"  pricetype="click"><span>按点击计费</span></a>
                        </div>
                        <div style="float: left; padding-left: 20px; ">
                            <span class="blue">单次</span>点击价格： <input type="text" disabled class="input_text" value="" id="price_input_click" style="width: 50px;"/>&nbsp;元
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <div style="float: left;">
                            <a class="ui_radio ui_radio_pre"  pricetype="download"><span>按下载计费</span></a>
                        </div>
                        <div style="float: left; padding-left: 20px;">
                            <span class="blue">单次</span>下载价格： <input type="text" disabled  class="input_text" value="" id="price_input_download" style="width: 50px;"/>&nbsp;元
                        </div><br />

                    </td>
                </tr>

                <tr><th></th><td>
                        <input type="hidden" name="priceType" value="" />
                        <input type="hidden" name="costPrice" value="" />
                    </td></tr>
                <tr>
                    <th><span class="f_req">*</span>投放地域：</th>
                    <td style="display: block;position:relative;">
                        <a class="ui_radio ui_radio_regionad" data="1"  ><span>所有地理位置</span></a>
                        <a class="ui_radio ui_radio_regionad" data="2"  ><span>特定地理位置</span></a>
                        <span class="comment ad_special_comment"></span>
                        <div class="ad_special special" style="display:none;">
                            <%@include file="region.jsp"%>
                        </div>
                        <input type="hidden" name="adareas" value=""/>
                    </td>
                </tr>
                <tr>
                    <th><span class="f_req">*</span>网络状况：</th>
                    <td>
                        <a class="ui_radio ui_radio_net" net="all" ><span>所有网络</span></a>
                        <a class="ui_radio ui_radio_net" net="2G" ><span>2G</span></a>
                    </td>
                </tr>
                <tr class="tip">
                    <th></th>
                    <td>
                        <a class="ui_radio ui_radio_net" net="3G" ><span>3G</span></a>
                        <a class="ui_radio ui_radio_net" net="WIFI" ><span>WIFI</span></a>
                        <input type="hidden" name="networks" value="" />
                    </td>
                </tr>
                <tr>
                    <th>出现渠道：</th>
                    <td>
                        <input type="text" class="input_text" name="channels"  placeholder="填写广告想出现的渠道号，以逗号区分" style="width: 260px;" /> 
                        <a href="support/docs/channel" target="_blank">渠道输入方法</a>
                           <!-- <span style="padding-left: 10px; color: gray;"></span> -->
                    </td>
                </tr>
                <tr>
                    <th>固定排序：</th>
                    <td>
                        <input type="text" class="input_text" name="fixedRank" id="fixedRank" placeholder="请输入正整数" style="width: 260px;" /> 
                    </td>
                </tr>
            </table>
        </div>
        <div class="msg_content_bottom">
            <span class="fr"> 
                <input type="button" class="btn_use_pastad" style="float: left;" value="使用已有广告内容" /> 
                <input type="button" class="btn_continue_ad" style="float: left; margin-left: 10px;" value="继续创建广告" />
            </span>
            <!--
    <a href="#" class="icon_return">返回订单管理页面</a>
            -->
        </div>
    </div>
    <div class="step2" style="display: none">
        <div class="msg_content_top"
             style="padding-left: 10px; padding-top: 10px;">
            <div class="flow step2" style="width: 680px;">
                <ul>
                    <li><a href="#">1.&nbsp;选择类型和设备</a></li>
                    <li><a href="#">2.&nbsp;内容编辑</a></li>
                </ul>
                <span class="fr close"></span>
            </div>

        </div>

        <div class="msg_content_center">
            <table width="100%" border="0" cellspacing="0" cellpadding="0"
                   class="tb_form">
                <tr>
                    <th width="100"><span class="f_req">*</span>选择广告类型：</th>
                    <td>
                        <table>
                            <tr>
                                <td
                                    style="background: url('images/icon_tip.png') no-repeat; height: 64px; width: 64px; padding: 0;"></td>
                                <td style="padding: 0 10px 0 10px;"><strong>网站</strong><br />
                                    <span  style="color: gray; display: inline-block; padding: 0 0 5px 0;">增加网站手机的流量</span><br />
                                    <a class="ui_radio ui_radio_type"  title="web" >
                                        <span>网站推广</span>
                                    </a>
                                </td>
                                <td
                                    style="background: url('images/icon_tip.png') -64px 0 no-repeat; height: 64px; width: 64px; padding: 0;"></td>
                                <td style="padding: 0 0 0 10px;"><strong>应用程序</strong><br />
                                    <span
                                        style="color: gray; display: inline-block; padding: 0 0 5px 0;">推动应用程序下载</span><br />
                                    <a class="ui_radio ui_radio_type"  title="app" >
                                        <span>应用推广</span>
                                    </a>

                                </td>                            
                            </tr>

                            <tr><td colspan="4"><input type="hidden" name="contentType" value="" /></td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <th><span class="f_req">*</span>打开方式：</th>
                    <td>
                        <div class="ui_select ui_select_landingType" style="z-index: 2;">
                            <a  class="btn_pulldown"></a> 
                            <span class="text"></span>
                            <div class="pop_menu">
                                <ul>
                                    <li class="android app"><a  pointto="adLandingType" content="detail" >详细界面</a></li>
                                    <li class="android app"><a   pointto="adLandingType" content="download" >直接下载</a></li>
                                    <li class="android web iOS"><a   pointto="adLandingType" content="webview" >内嵌网页</a></li>
                                    <li class="android web iOS"><a   pointto="adLandingType" content="browser" >浏览器</a></li>
                                    <li class="iOS app"><a   pointto="adLandingType" content="gotoAppStore" >GotoAppStore</a></li>
                                </ul>
                            </div>
                        </div> 
                        <input type="hidden" name="adLandingType" id="adLandingType" value="detail" />
                    </td>
                </tr>
                <tr>
                    <th><span class="f_req">*</span>广告形式：</th>
                    <td>
                        <a class="ui_radio ui_radio_form" style_type="standard"  >
                            <span>标准</span>
                        </a>
                        <a class="ui_radio ui_radio_form" style_type="image"  >
                            <span>图片</span>
                        </a>
                        <a class="ui_radio ui_radio_form" style_type="text"  >
                            <span>文字</span>
                        </a>
                        <input type="hidden" name="displayType" value="" />
                    </td>
                </tr>
                <tr class="10100 10010 01010 01100 type_switch">
                    <th><span class="f_req">*</span>内容尺寸：</th>
                    <td>
                        <div class="ui_select ui_select_landingSize" style="z-index: 2;">
                            <a  class="btn_pulldown"></a> 
                            <span class="text"></span>
                            <div class="pop_menu">
                                <ul>
                                    <li class="android standard banner wap custom embed push"><a pointto="adLandingSize" content="202x55" >202x55</a></li>
                                    <li class="iOS standard banner wap custom embed push"><a pointto="adLandingSize" content="320x50" >320x50</a></li>
                                    <li class="iOS image wap push"><a pointto="adLandingSize" content="480x320" >480x320</a></li>
                                    <li class="iOS image wap push"><a pointto="adLandingSize" content="320x480" >320x480</a></li>
                                    <li class="android image wap push"><a pointto="adLandingSize" content="424x380" >424x380</a></li>
                                    <li class="android image wap push"><a pointto="adLandingSize" content="380x424" >380x424</a></li>
                                    <li class="iOS  image bigimage"><a pointto="adLandingSize" content="640x320" >640x320</a></li>
                                    <li class=" android image bigimage"><a pointto="adLandingSize" content="800x250" >800x250</a></li>
                                    <li class="text iOS android"><a pointto="adLandingSize" content="-" >-</a></li>
                                    
                                </ul>
                            </div>
                        </div> 
                        <input type="hidden" name="adLandingSize" id="adLandingSize" value="202x55" />
                    </td>
                </tr>
                <tr class="tip">
                    <th></th>
                    <!--						<td><a href="#">形式预览&nbsp;&gt;&gt;</a></td>-->
                </tr>

                <tr class="10100 10010 10001 type_switch ios">
                    <th><span class="f_req">*</span><span class="iosOrAndroidName">网址：</span></th>
                    <td>
                        <input type="text" class="input_text" name="weburl" verify="url" title="网址不能为空!" class="sub" style="width: 300px;" value="http://"/>
                        <!-- <input type="button" id="inputiTunes" class="green_btn hidden" value="导入"/> -->
                    </td>
                </tr>

                <form name="form" method="post" action="/ad/save"  onsubmit="javascript: return true;" id="ad_form"   enctype="multipart/form-data">
                    <tr class="01010 01100 type_switch android">
                        <th><span class="f_req">*</span>上传apk：</th>
                        <td style="display:block">
                            <input title="不能为空!类型(.apk)"  rule="apk" type="file" feedback="url" name="apkFile" id="apkFile" filesize="50000"  filetype="apk"   /> 
                            <a  class="changeIcon">( 更改 )</a>
                            <span class="comment"></span>
                            <input title="不能为空!类型(.apk)"  rule="apk" class="sub"  name="url" id="url" value="" type="hidden" verify="file"/>
                        </td>

                    </tr>
                    <tr class="01010 01100 type_switch">
                        <th><span class="f_req">*</span>上传logo：</th>
                        <td style="display:block">
                            <input title="不能为空!类型(.ico|.jpg|.gif|.png)"  rule="ico|jpg|gif|png" type="file" filetype="icon_app"  feedback="icon" name="iconFile" id="iconFile"  filesize="200"  /> 
                            <a  class="changeIcon">( 更改 )</a>
                            <span class="comment"></span>
                            <input title="不能为空!类型(.ico|.jpg|.gif|.png)"  rule="ico|jpg|gif|png" class="sub previewLogo"  forshow="icon_show"  name="icon" id="icon" value="" type="hidden" verify="file" />
                        </td>
                    </tr>
                    <tr  class="10100 type_switch">
                        <th><span class="f_req">*</span>网站logo：</th>
                        <td style="display:block">
                            <input title="不能为空!类型(.ico|.jpg|.gif|.png)"  rule="ico|jpg|gif|png" type="file" filetype="icon_wap"  feedback="weblogo" name="logoFile" id="logoFile" filesize="200"  />
                            <a  class="changeIcon">( 更改 )</a>
                            <span class="comment"></span>
                            <input  title="不能为空!类型(.ico|.jpg|.gif|.png)"  rule="ico|jpg|gif|png" class="sub previewLogo" name="weblogo" id="weblogo" value="" type="hidden" verify="file"  forshow="icon_show"/>
                        </td>
                    </tr>
                    <tr class="10010 01010 type_switch">
                        <th><span class="f_req">*</span><span id="bannerOrBigImg">横幅图片</span>：</th>
                        <td style="display:block">
                            <input title="不能为空!类型(.ico|.jpg|.gif|.png)"   rule="ico|jpg|gif|png" type="file" filetype="pic"  feedback="bannerimg" name="bannerImage" id="bannerImage"  filesize="400"  /> 
                            <a  class="changeIcon">( 更改 )</a>
                            <span class="comment"></span>
                            <input   title="不能为空!类型(.ico|.jpg|.gif|.png)"   rule="ico|jpg|gif|png" class="sub previewBannerImg"  name="bannerimg" id="bannerimg" value="" type="hidden" verify="file"  forshow="bannerimg_show"/>
                        </td>
                    </tr>
                    <input type="hidden" name="fileObject" id="ad_form_fileObject" value="" />
                    <input type="hidden" id="ad_form_uploadType" name="uploadType" value="" />
                    <input type="hidden" id="ad_form_feedback" name="feedback" value="" /> 
                    <input type="hidden" id="ad_form_callback" name="callback" value="" /> 
                    <input name="width" id="ad_form_width" type="hidden" value="480" /> 
                    <input name="height" id="ad_form_height" type="hidden" value="75" />
                </form>
                <tr class="10100 10010 type_switch">
                    <th><span class="f_req">*</span>网站名称：</th>
                    <td>
                        <input type="text" class="input_text sub previewName" name="webname"  placeholder="不能多于20字" title="不能多于20字" verify="adWords"    forshow="name_show"  style="width: 300px;" />
                    </td>
                </tr>


               <!--  <tr class="10100 10010 type_switch">
                    <th><span class="f_req">*</span>网站简介：</th> 
                    <td class="f_req"><textarea placeholder="不能多于20字" title="不能多于20字" style="width: 310px; height: 50px;" rows=" " verify="adWords"  forshow="des_show"  cols=" " name="webdes" id="webdes" class="input_textarea previewDes"></textarea></td>
                </tr> -->
                <tr class="01100 01010 10001 type_switch">
                    <th><span class="f_req">*</span>软件名称：</th>
                    <td><input type="text" class="input_text sub previewName" name="appname"  placeholder="不能多于20字" title="不能多于20字" verify="adWords"  forshow="name_show" style="width: 300px;" /></td>
                </tr>
<!--                <tr class="10001 type_switch" >
                    <th><span class="f_req">*</span>字体：</th>
                    <td>
                        <div class="ui_select ui_select_textFont" style="z-index: 3;">
                            <a  class="btn_pulldown"></a> 
                            <span class="text">选择字体</span>
                            <div class="pop_menu">
                                <ul>
                                    
                                </ul>
                            </div>
                        </div> 
                        <input type="hidden" name="textFont" value="" />
                    </td>
                </tr>-->
                <tr class="10001 type_switch">
                    <th><span class="f_req">*</span>文字大小：</th>
                    <td>
                        <div class="ui_select ui_select_textSize" style="z-index: 2;">
                            <a  class="btn_pulldown"></a> 
                            <span class="text">选择文字大小</span>
                            <div class="pop_menu">
                                <ul>
                                    
                                </ul>
                            </div>
                        </div> 
                        <input type="hidden" name="textSize" value="" />
                    </td>
                </tr>
                <tr class="10001 type_switch">
                    <th><span class="f_req">*</span>颜色：</th>
                    <td>
                        <div class="ui_select_textColor" style="z-index: 1;">
<!--                            <a  class="btn_pulldown"></a> -->
<!--                            <span class="text">选择颜色</span>-->
                            <input type="text" class="input_text iColorPicker" disabled name="textColor" width="10" value="#FFFFFF" id="textColorPicker"/>
<!--                            <div class="pop_menu">
                                <ul>
                                    <li><a href="#"  onclick="$(&quot;input[name='textColor']&quot;).val($(this).text());">红</a></li>
                                    <li><a href="#"  onclick="$(&quot;input[name='textColor']&quot;).val($(this).text());">黄</a></li>
                                    <li><a href="#"  onclick="$(&quot;input[name='textColor']&quot;).val($(this).text());">蓝</a></li>
                                </ul>
                            </div>-->
                        </div> 
                        <input type="hidden" name="textColor" value="" />
                    </td>
                </tr>
                <tr class="01100 01010 10100 10010 type_switch">
                    <th><span class="f_req">*</span><span class="adwords_toggle_title">推广文字：</span></th>
                    <td><input type="text" class="input_text sub previewDes" name="adWords" title="不多于20字" placeholder="不多于20字" verify="adWords"  forshow="des_show" style="width: 300px;" /></td>
                </tr>
                <tr class="01100 01010 type_switch">
                    <th><span class="f_req">*</span>开发商：</th>
                    <td><input type="text" class="input_text sub" name="provider" title="请填写开发商!"  placeholder="不能多于20字" title="不能多于20字" verify="adWords" style="width: 300px;" /></td>
                </tr>
                <tr class="01100 01010 type_switch">
                    <th><span class="f_req">*</span>详细描述：</th>
                    <td class="f_req"><textarea placeholder="不能多于500字" title="不能多于500字" style="width: 310px; height: 50px;" rows=" " verify="area" cols=" " name="description" class="input_textarea"></textarea></td>
                </tr>
                <tr class="">
                    <th>样式预览：</th>
                    <td>
                        <span class="preview" title="类型为WAP的模板2的预览为原图的50%;其他类型预览是原比例">  
                            <img src="/images/no_img.gif" id="icon_show" class="icon_show weblogo_show" width="48" height="48" style="margin:2px;"/>
                            <span id="name_show" class="name_show"></span>
                            <span id="des_show"></span>
                            <img src="/images/no_img.gif" id="bannerimg_show" class="bannerimg_show" width="100%" height="100%" style="display: none;"/>
                        </span> 
                        <!--                                                        <span class="preview_del"><a href="#">清除</a></span>-->
                    </td>
                </tr>
            </table>
        </div>
        <div class="msg_content_bottom">
            <span class="fr"> <input type="button" class="btn_save_ad btn_save"
                                     value="保存" />
            </span>
            <a class="ad_icon_return icon_return" onclick="" href="javascript:void(0)">返回订单管理页面</a>
            <!--
            <a href="#" class="icon_return">返回订单管理页面</a>
            -->
        </div>
    </div>
    
</div>
<div id="adListForUseContainer" class="box_shadow10px hidden">
    <span class="fr close_adlist"><img src="/images/get_code_close.png" alt=""></span>
        <table id="choose_ad">
            <tbody>
                <tr><td class="thead" colspan="5">选择广告位</td></tr>
                <tr class="border_bottom">
                    <th width="150">广告名称</th>
                    <th width="100" class="border_left">类型</th>
                    <th width="130" class="border_left">开始时间</th>
                    <th width="130" class="border_left">结束时间</th>
                    <th width="120" class="border_left">状态</th>
                </tr>
                <tr>
                    <td colspan="5" height="270" class="c_adslot_body">
                        <div style="height:270px;overflow-y:auto;overflow-x:hidden;">
                            <table id="choose_ad_item">
                                <tr class="border_bottom">
                                    <td class="loadingStatus">loading...</td>
                                </tr>
                            </table>
                        </div>
                    </td>   
                </tr>
            </tbody>
        </table>
    <span class="btns">
        <input class="btn_save_ad2 btn_input" type="button" value="保存">
    </span>
</div>
<div id="uploadDiv" style="display: none">
    <iframe id="uploadFrame" name="uploadFrame"></iframe>
</div>
