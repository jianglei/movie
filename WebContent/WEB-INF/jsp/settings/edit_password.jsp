<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <title>密码设置  - 友盟广告管理平台</title>
            <link href="/css/base${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/layout${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
            <link href="/css/style${build}.css?${constantVersion}" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="container">
            <%@include file="../include/header.jsp"%>

            <form name="form" method="post"
                  action="/settings/update_user_password" id="form">
                <div id="main">
                    <div class="panel_table">
                        <div class="part_operation">
                            <div class="txt">修改密码</div>
                        </div>

                        <div id="center">

                            <div class="panel_main">
                                <div class="con">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0"
                                           class="tb_form">
                                        <tr>
                                            <th width="100"><span class="f_req">*</span>旧密码：</th>
                                            <td><input type="password" class="input_text not_null"
                                                       name="user_current_password" id="user_current_password" required="required"
                                                       style="width: 300px;" /></td>
                                        </tr>
                                        <tr>
                                            <th width="100"><span class="f_req">*</span>新密码：</th>
                                            <td><input type="password" class="input_text not_null"
                                                       name="user_password" id="user_password" required="required" style="width: 300px;" /></td>
                                        </tr>
                                        <tr>
                                            <th width="100"><span class="f_req">*</span>确认新密码：</th>
                                            <td><input type="password" class="input_text not_null"
                                                       name="user_password_confirmation"
                                                       id="user_password_confirmation"  required="required" style="width: 300px;" /></td>
                                        </tr>

                                    </table>
                                </div>

                                <div class="panel_btn">
                                    <span class="fr"> <input type="submit" class="btn_save"  value="保存" onclick="beforeSubmit();" />
                                    </span>
                                </div>
                            </div>

                        </div>
                    </div>

                    <%@include file="../include/footer.jsp"%>
            </form>
        </div>
        <script type="text/javascript" src="/js/libs/jquery-1.6.1.min.js"></script>
        <script type="text/javascript" src="/js/libs/base64.js"></script>
        <script type="text/javascript" src="/js/libs/jquery.md5.js"></script>
        <script type="text/javascript" src="/js/libs/util.js"></script>
        <script type="text/javascript">
            function beforeSubmit() {
                var user_current_password = $("#user_current_password").val();
                var user_password = $("#user_password").val();
                var user_password_confirmation = $("#user_password_confirmation")
                .val();
                if (user_password_confirmation == "") {
                    alert("请填写密码");
                    return false;
                }
                if (user_password == ""
                    || (user_password != user_password_confirmation)) {
                    alert("两次密码不一致");
                    return false;
                }
                $("#user_current_password").val($.md5(user_current_password));
                $("#user_password").val($.md5(user_password));
                $("#user_password_confirmation").val($.md5(user_password_confirmation));
                return true;
            }
            $(document).keydown(function(event) {
                if (event.keyCode == 13) {
                    $("#btn_save").click();
                }
            });
        </script>
    </body>
</html>
<%@include file="../include/bottom.jsp"%>