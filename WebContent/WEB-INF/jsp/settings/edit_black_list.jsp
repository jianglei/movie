<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="../include/constants.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <title>黑名单设置 - 友盟广告管理平台</title>
            <link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="container">
            <%@include file="../include/header.jsp"%>
                <div id="main">
                    <div class="panel_table">
                        <div class="part_operation">
                            <div class="txt">黑名单设置</div>
                        </div>
                        <div class="con" style="padding:20px;">
                            <table>
                                <tr>
                                    <td><textarea name="blacklist" id="blacklist" class="box_inset_shadow border_radius5px" cols="30" rows="5" placeholder="输入不想推的内容的关键词,系统会自动屏蔽名称或者说明带有该关键词的广告,关键词以逗号分隔">${ user.blacklist }</textarea></td>
                                
                                    <td style="color:#545454;padding:10px;">输入不想推的内容的关键词,系统会自动屏蔽名称或者说明带有该关键词的广告,关键词以逗号分隔</td>
                                </tr>
                            </table>
                          
                        </div>
                        <div class="panel_btn">
                            <span class="fr"> <input type="submit" class="btn_save"   value="保存" />
                            </span>
                        </div>

                    </div>
                </div>
            <%@include file="../include/footer.jsp"%>
        </div>
    </body>
</html>
<script type="text/javascript" src="/js/libs/jquery-1.6.1.min.js?${constantVersion}"></script>
<script type="text/javascript" src="/js/libs/list_util.js?${constantVersion}"></script>
<script type="text/javascript">
    $(function(){
        $('.btn_save').click(function(){
            $.post(
                '/settings/update_user_info',
               {
                   blacklist:$('#blacklist').val(),
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