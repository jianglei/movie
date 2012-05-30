<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="../include/constants.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <title>账户设置  - 友盟广告管理平台</title>
           <link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/setting${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="container">
            <%@include file="../include/header.jsp"%>
            <div id="main">
                <div class="panel_table">
                    <div class="part_operation">
                        <div class="txt">个人信息 </div>
                    </div>					
                    <div class="profile_info" style="padding:10px;">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                               class="tb_form_info">
                            <tr>
                                <th width="80" height="25">昵称：</th>
                                <td>${user.nickname }</td>
                            </tr>
                            <tr>
                                <th width="80" height="25">Email：</th>
                                <td>${user.email }</td>
                            </tr>
                            <tr>
                                <th width="80" height="25">姓名：</th>
                                <td>${user.name }</td>
                            </tr>
                            <tr>
                                <th width="80" height="25">手机：</th>
                                <td>${user.phone }</td>
                            </tr>

                        </table>
                        <div class="alter_btn" style="margin:10px 0 5px 30px;">
                            <a href="javascript:void(0)" id="alter_pro">修改资料</a>
                            <a href="javascript:void(0)" id="alter_pw" style="margin-left:20px;">修改密码</a>
                        </div>
                    </div>
                    
                </div>
            </div>

            <%@include file="../include/footer.jsp"%>
        </div>


        <div id="msg_wrap_form" style="display: none;">
            <div class="msg_content_top">
                <h3 class="title">
                    <span class="fl label" style="padding-left: 8px;">修改账号</span> <span
                        class="fr close"></span>
                    <div class="clear"></div>
                </h3>
            </div>

            <div class="panel_main">
                <form name="form" method="post" action="/settings/update_user_info" id="form_pro">
                    <div class="con_profile msg_content_center">

                        <input type="hidden" name="id" value="${user.id }" />
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                               class="tb_form">
                            <tr>
                                <th width="100"><span class="f_req">*</span>昵称：</th>
                                <td><input type="text" class="input_text not_null"
                                           name="nickname" id="nickname" value="${user.nickname }"
                                           style="width: 300px;" /></td>
                            </tr>
                            <tr>
                                <th width="100"><span class="f_req">*</span>Email：</th>
                                <td><input type="text" class="input_text not_null"
                                           name="email" id="email" value="${user.email }"
                                           style="width: 300px;" /></td>
                            </tr>

                            <tr>
                                <th width="100"><span class="f_req">*</span>姓名：</th>
                                <td><input type="text" class="input_text not_null"
                                           name="name" id="name" value="${user.name }"
                                           style="width: 300px;" /></td>
                            </tr>
                            <tr>
                                <th width="100"><span class="f_req">*</span>手机：</th>
                                <td><input type="text" class="input_text not_null"
                                           name="phone" id="phone" value="${user.phone }"
                                           style="width: 300px;" /></td>
                            </tr>

                        </table>


                    </div>
                    <div class="panel_btn_pro panel_btn msg_content_bottom">
                        <span class="fr"> <input type="submit" class="btn_save"
                                                 value="保存" />
                        </span>
                    </div>
                </form>
                <form name="form" method="post" action="/settings/update_user_password" id="form_pw">
                    <div class="con_password msg_content_center">

                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                               class="tb_form">
                            <tr>
                                <th width="100"><span class="f_req">*</span>旧密码：</th>
                                <td><input type="password" class="input_text not_null"
                                           name="user_current_passwordv" id="user_current_passwordv" required="true"
                                           style="width: 300px;" />
                                    <input type="hidden" name="user_current_password" id="user_current_password"/>
                                </td>
                            </tr>
                            <tr>
                                <th width="100"><span class="f_req">*</span>新密码：</th>
                                <td><input type="password" class="input_text not_null"
                                           name="user_passwordv" id="user_passwordv" required="true" style="width: 300px;" />
                                    <input type="hidden" name="user_password" id="user_password"/>
                                </td>
                            </tr>
                            <tr>
                                <th width="100"><span class="f_req">*</span>确认新密码：</th>
                                <td><input type="password" class="input_text not_null"
                                           name="user_password_confirmationv"
                                           id="user_password_confirmationv"  required="true" style="width: 300px;" />
                                    <input type="hidden" name="user_password_confirmation" id="user_password_confirmation"/>
                                </td>
                            </tr>

                        </table>

                    </div>
                    <div class="panel_btn_pw panel_btn msg_content_bottom">
                        <span class="fr"> <input type="submit" class="btn_save"
                                                 value="保存" />
                        </span>
                    </div>
                </form>
            </div>
        </div>	
    </body>
</html>


<script type="text/javascript" src="/js/libs/jquery-1.6.1.min.js?${constantVersion}"></script>
<script type="text/javascript" src="/js/libs/jquery.blockUI.js?${constantVersion}"></script>
<script type="text/javascript" src="/js/libs/jquery.validate.min.js?${constantVersion}"></script>
<script type="text/javascript" src="/js/libs/jquery.form.js?${constantVersion}"></script>
<script type="text/javascript" src="/js/libs/jquery.md5.js?${constantVersion}"></script>
<script type="text/javascript">
    $(function(){
        var options;
        $('div.alter_btn a').click(function(){
            var msg = $('#msg_wrap_form'),
            height = $(window).height(),
            width = $(document).width();
            if($(this).is('#alter_pro')){
                $('div.msg_content_top .label').text('修改资料');
                $('#form_pro').show();
                $('#form_pw').hide();
                
            }else{
                $('div.msg_content_top .label').text('修改密码');
                $('#form_pro').hide();
                $('#form_pw').show();
            }
            $.blockUI({
                css:{color:'#cccccc',border:'none',width:'722px',left:width/2-msg.width()/2,top:height/2-msg.height()/2,background:'none',padding:'0px'},
                message:msg
            });
        });
        $('.close').click(function(){
            setTimeout($.unblockUI,100);
        });
        $('input.btn_save').click(function(e){
            e.stopPropagation();
            if($('#form_pro').is(':visible')){
                options = {
                    success:function(data){
                        if(data.status == 'ok'){
                            $('table.tb_form_info tr').each(function(){
                                var index = $(this).index();
                                $(this).find('td').text($('#form_pro tr').eq(index).find('input').val());
                            });
                            setTimeout($.unblockUI,100);
                        }else{
                            alert('修改信息失败!');
                        }
                        
                    }
                }
            }else{
                if($("#user_current_passwordv")!=''&&$("#user_passwordv").val()!=''&&$("#user_password_confirmationv").val()!=''){
                    $("#user_current_password").val($.md5($("#user_current_passwordv").val()));
                    $("#user_password").val($.md5($("#user_passwordv").val()));
                    $("#user_password_confirmation").val($.md5($("#user_password_confirmationv").val()));

                }
                options = {
                    success:function(data){
                        if(data.status =='ok'){
                            alert('修改密码成功!');
                            setTimeout($.unblockUI,100);
                        }else{
                            alert('修改密码失败!');
                        }
                             
                        
                    }
                };
            }
                  
        });
        //            jQuery.validator.addMethod("isTel", function(value, element) {
        //                var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678
        //                return this.optional(element) || (tel.test(value));
        //                }, "请正确填写您的电话号码"); 
        jQuery.validator.addMethod("isMobile", function(value, element){
            var length = value.length;
            return this.optional(element) || length == 11 && (/^1[3584]\d{9}$/.test(value));
        }, "请填写正确的手机号码");
        $("#form_pro").validate({
            rules: {
                nickname: "required",
                email: {
                    required: true,
                    email:true  
                },
                name: 'required',
                phone:{
                    required:true,
                    isMobile:true
                }
            },
            messages: {
                nickname: "必填字段",
                email: {
                    required: "必填字段",
                    email: "请输入正确的邮箱地址"
                },
                name:"必填字段",
                phone: {
                    required: "必填字段",
                    isMobile: "请输入正确的手机号码"
                }
            },
            submitHandler:function(form){
                $("#form_pro").ajaxSubmit(options); 
                return false; 
            } 
        });
        $("#form_pw").validate({
            rules: {
                user_current_passwordv: "required",
                user_passwordv: {
                    required: true,
                    //minlength: 5
                },
                user_password_confirmationv: {
                    required: true,
                    //minlength: 5,
                    equalTo: "#user_passwordv"
                }
            },
            messages: {
                user_current_passwordv: "请输入原来的密码",
                user_passwordv: {
                    required: "请输入新密码",
                    //minlength: jQuery.format("密码不能小于{0}个字符")
                },
                user_password_confirmationv: {
                    required: "请再次输入新密码",
                    //minlength: "密码不能小于5个字符",
                    equalTo: "两次输入密码不一致"
                }
            },
            submitHandler:function(form){
                $("#form_pw").ajaxSubmit(options); 
                return false; 
            } 
        });
        
    });
</script>
<%@include file="../include/bottom.jsp"%>