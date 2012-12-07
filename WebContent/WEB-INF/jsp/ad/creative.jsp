<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建广告</title>
<link rel="stylesheet" href="/css/default.css" />
<link rel="stylesheet" href="/css/ui-lightness/jquery-ui-1.8.16.custom.css" />
<script type="text/javascript" src="/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/upload.js"></script>
<script type="text/javascript" src="/js/util.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.8.16.custom.min.js"></script>

<script type="text/javascript" src="/js/jquery.blockUI-2.39.js"></script>
<script type="text/javascript" src="/js/jquery-ui-timepicker-addon.js"></script>
</head>

<body>

<form method="post" action="/ad/save" id="form" name="form" enctype="multipart/form-data">
<div class="umengADsystem_container">
    
    <!-- 步骤end -->
    <!-- 主体start -->
    <div class="umengADsystem_two_col umengADsystem_clearfix">
        <!-- 左侧start -->
        <div class="col_l">
            <!-- 面板start -->
            <div class="umengADsystem_panel_main">
                
                <div class="content">
                    <!-- 标题 start -->
                    <h2 class="umengADsystem_title_sub"> 广告信息 </h2>
                    <!-- 标题end -->
                    <!-- ad table start  -->
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="umengADsystem_table_form">
                        <tr class="third">
                            <th width="120"><span class="umengADsystem_font_blue">* </span>广告名称：</th>
                            <td><input type="text" class="input_text not_null" name="name" id="name" value="${result.name }"  style="width:300px;" />
                                <span class="" id="name_warning"></span></td>
                        </tr>
                        <tr class="second">
                            <th></th>
                            <td><span class="umengADsystem_font_gray">广告的名称，不能为空！</span></td>
                        </tr>
                        <tr class="third">
                            <th width="120"><span class="umengADsystem_font_blue">* </span>着陆地址：</th>
                            <td><input type="text" class="input_text not_null" name="clickUrl" id="clickUrl" value="${result.clickUrl }"  style="width:300px;" />
                                <span class="" id="clickUrl_warning"></span></td>
                        </tr>
                        <tr class="second">
                            <th></th>
                            <td><span class="umengADsystem_font_gray">着陆地址，不能为空！</span></td>
                        </tr>
                        
                       
                    </table>
                    
                   
                        
	                    
	                    
	                    <!-- banner_ad table start  -->
	                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="umengADsystem_table_form banner_ad">
	                        <tr class="third">
	                            <th width="120"><span class="umengADsystem_font_blue">* </span>图片：</th>
	                            <td>
	                                <span class="wrap_input_file">
	                                        <span class="input_file" id="banner_file_span">
	                                            <input name="banner_File" id="banner_File" class="" value="" type="file"  onchange="upload(this,'pic','pic');"/>
	                                        </span>
	                                            <input type="hidden" id="pic" name="pic" value="${result.pic }"/>
	                                        <span class="input_btn"><input type="button" value="上传..." class="btn_small" /></span>
	                                </span><span class="warning" id="banner_File_warning"></span>
	                            </td>
	                        </tr>
	                        <tr class="second">
	                            <th></th>
	                            <td><!-- 预览 start -->
	                                <div class="umengADsystem_panel_preview umengADsystem_clearfix" >
	                                    <div class="part_preview box_shadow">
	                                    <div class="size_img_big">
	                                    <c:if test="${result.pic != null}">
	                                    <img id="banner_show" src="${result.pic }" />
	                                    </c:if>
	                                    <c:if test="${result.pic == null}">
	                                    <img id="banner_show" src="${result.pic }" style="display:none;"/>
	                                    </c:if>
	                                    </div></div>
	                                    <p class="panel_btn"><a href="javascript:void(0);" onclick="clear_pic();" class="icon_delete" title="清除">清除</a></p>
	                                    <div class="part_notice">
	                                        <p>注意：</p>
	                                        <p>1.图标格式为gif、jpg、jpeg、png格式</p>
	                                        <p>2.图标为正方型</p>
	                                    </div>
	                                </div>
	                                <!-- 预览 end --></td>
	                        </tr>
	                    </table>
                        
                    <!-- ad  end  -->
                </div>
                <div class="panel_btn"> <span class="part_right">
               
                
                   <input type="button" class="btn_ok" onclick="beforeSubmit();" value="保存" />
               
                </span> 
                    <!-- 在本次版本中暂不实现
                    <span class="part_right"> <a href="#" class="icon_return">返回上一步</a></span> 
                    -->
                    <a href="#" class="icon_return">返回广告列表</a> </div>
            </div>
            
            <!-- 面板end -->
        </div>
        <!-- 左侧end -->
    </div>
    <!-- 主体end -->
</div>



</form>
<div id="uploadDiv" style="display: none">
    <iframe id="uploadFrame" name="uploadFrame"></iframe>
</div>
<script type="text/javascript" >
function upload(obj,uploadType,feedback){
    document.form.target = "uploadFrame";
    document.form.action = "/upload/uploadFile";
    document.form.submit();
    document.form.target = "_self";
    document.form.action = "/advertiser/creative/${adgroup.id }/save";
}
function clear_pic(){
    $('#pic').val('');
    $('#banner_File').addClass('file_not_null');;
    $('#banner_show').attr("src",'');
    $('#banner_show').hide();
}

function beforeSubmit(){
	if(checkSubmit()){
		document.form.action = "/ad/save";
		document.form.submit();
	}
}
</script>
</body>

</html>
