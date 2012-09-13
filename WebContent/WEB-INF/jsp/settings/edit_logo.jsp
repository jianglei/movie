<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="../include/constants.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <title>账户设置 - 友盟广告管理平台</title>
            <link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="container">
            <%@include file="../include/header.jsp"%>

            <form name="form" method="post" action="/settings/update_user_info"
                  onsubmit="javascript: return true;" id="logo_form"
                  enctype="multipart/form-data">
                <input type="hidden" name="id" value="${user.id }" id="userid" />
                <div id="main">
                    <div class="panel_table">
                        <div class="part_operation">
                            <div class="txt">自定义Logo</div>
                        </div>
                        <div class="con" style="padding-top:20px;">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0"
                                   class="tb_form">
                                <tr>
                                    <th width="100" style="vertical-align:bottom;"><span class="f_req">*</span>名称：</th>
                                    <td><input type="text" class="input_text not_null"
                                               name="logoName" id="logoName" value="${user.logoName }" onkeyup="$('.logoUrl_show').next('a').text($(this).val()+'广告管理平台')"
                                               style="width: 300px;" /></td>
                                </tr>
                                <tr>
                                    <th style="vertical-align:bottom;"><span class="f_req">*</span>公司logo：</th>
                                    <td>
<!--                                         <a href="javascript:void(0);" class="changeIcon">( 更改 )</a>
                                         <span class="comment"><a class="view_icon" href="${user.logoUrl}" target="_blank">/public/upload/...</a></span>-->
                                        <input type="file" class="not_null"
                                               name="logo" id="logo" value="${user.logoUrl}" onchange="upload(this,'pic','logoUrl', 'changeUploadStyle','#logo_form');"
                                               style="width: 300px;" />
                                         <a href="javascript:void(0);" class="changeIcon">( 更改 )</a>
                                        <span class="comment"></span>
                                        <input type="hidden" class="not_null"
                                               name="logoUrl" id="logoUrl" value="${user.logoUrl}" 
                                               style="width: 300px;" verify="file"/>

                                    </td>
                                </tr>

                            </table>
                        </div>
                        <div class="panel_btn">
                            <span class="fr"> <input type="submit" class="btn_save"   value="保存" />
                            </span>
                        </div>

                    </div>
                </div>

                <input type="hidden" name="fileObject" id="logo_form_fileObject" value="" />
                <input type="hidden" id="logo_form_uploadType" name="uploadType" value="" />
                <input type="hidden" id="logo_form_feedback" name="feedback" value="" />
                <input type="hidden" id="logo_form_callback" name="callback" value="" />
                <input name="width"  id="logo_form_width" type="hidden" value="480" />
                <input name="height" id="logo_form_height" type="hidden" value="75" />
            </form>
            <%@include file="../include/footer.jsp"%>
        </div>
        <div id="uploadDiv" style="display: none">
            <iframe id="uploadFrame" name="uploadFrame"></iframe>
        </div>
    </body>
</html>
<script src="/js/libs/jquery-1.6.1.min.js?${constantVersion}" type="text/javascript"></script>
<script src="/js/libs/jquery.blockUI.js?${constantVersion}" type="text/javascript"></script>
<script src="/js/libs/list_util.js?${constantVersion}" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
       changeUploadStyle();
        
        $('.btn_save').click(function(){
            $.post(
                '/settings/update_user_info',
               {
                   logoName:$('#logoName').val(),
                   logoUrl:$('#logoUrl').val(),
                   id:$('#userid').val()
               },
               function(data){
                loginValidate(data);
                 if(data.status =='ok'){
                     alert('自定义成功!');
                 }else{
                     alert('自定义失败!');
                 }
               }
            );
          return false;
        });
    })
</script>
<%@include file="../include/bottom.jsp"%>