function set_date_param(dateType, startDate, endDate){
	$("#dateType").val(dateType);
	$("#startDate").val(startDate);
	$("#endDate").val(endDate);
}
function check_records(record_head_ch){
	var v=$(record_head_ch).attr("checked");
	if($(record_head_ch).attr("checked")){
		$("input[name='record_ch']").attr("checked", true);
	}else{
		$("input[name='record_ch']").removeAttr("checked");
	}
}
//列表页面点击新增
function modify_jump_click(ev, url, ok, message){
	if(ok==true){
		window.location.href=url;
	}else{
		alert(message);
		ev.preventDefault();
	}
}
//全局操作
function change(target,paramName,value, recordDesc){
	var ids ="";
	var hasAdminPause=false;
	$("input[name='record_ch']:checked").each(function(){
		ids+=(","+$(this).val());
		var status=$("#status_"+$(this).val()).val();
		if("2"==status){
			hasAdminPause=true;
		}
	});
	if(ids!=""){
		if(hasAdminPause){
			alert("您不能操作管理员暂停的数据对象，请和管理员联系！");
			return;
		}
		ids=ids.substr(1);
		if("is_del"==paramName){
            if(confirm("你确定需要删除这些记录吗?")){
                globalChangeSubmit(target, paramName,value,ids);
            }
        }else{
            globalChangeSubmit(target, paramName,value,ids);
        }
	}else{
		alert("请先选择需要修改的对象再做修改操作");
	}
}
function checkCanEdit(ev, id){
	var status=$("#status_"+id).val();
	if("2"==status){
		alert("您不能操作管理员暂停的数据对象，请和管理员联系！");
		ev.preventDefault();
		return false;
	}else{
		return true;
	}
}
function globalChangeSubmit(target, paramName,value,ids){
	$.ajax({
        type: "POST",
        url: "/"+target+"/changes?",
        data: "ids=" + ids + "&paramName=" + paramName + "&value=" + value,
        success: function(msg){
		refreshListAfterChange(paramName, value, ids);
        }
     });
}
function refreshListAfterChange(paramName, value, ids){
	if("is_del"==paramName){
//		$("input[name='status']").attr("checked","checked");
//		var form=$("#listQueryForm");
//		form.submit();
		window.location.href =window.location.href;
	}else{
		var idArray=ids.split(",");
		for(var index=0; index<idArray.length; index++){
			var id=idArray[index];
			if("status"==paramName && "0"==value){
				$("#td_status_"+id).html("<img src=\"/images/img_content/icon_state_ok.gif\" width=\"16\" height=\"16\" alt=\"有效\" />有效");
			}else if("status"==paramName && "1"==value){
				$("#td_status_"+id).html("<img src=\"/images/img_content/icon_state_invalid.gif\" width=\"16\" height=\"16\" alt=\"暂停\" />暂停");
			}
			
		}
	}
}
/**
 * 日期类型变更处理
 * @param report_name
 * @return
 */
function list_datetype_change(load_date, clear_data){
	if($("#dateType").val() != "Custom"){
		var today=new Date();
		if($("#dateType").val() == "Yesterday"){
			var yesterday=new Date();
			yesterday.setDate(today.getDate()-1);
			$("#startDate").val(formatdate(yesterday, "yyyy-MM-dd"));
			$("#endDate").val(formatdate(yesterday, "yyyy-MM-dd"));
		}else if($("#dateType").val() =="Today"){
			$("#startDate").val(formatdate(today, "yyyy-MM-dd"));
			$("#endDate").val(formatdate(today, "yyyy-MM-dd"));
		}else{
			var sixDayBefore=new Date();
			sixDayBefore.setDate(today.getDate()-6);
			$("#startDate").val(formatdate(sixDayBefore, "yyyy-MM-dd"));
			$("#endDate").val(formatdate(today, "yyyy-MM-dd"));
		}
		change_querylink();
		load_date();
	}else{
		clear_data();
	}
}
/**
 * 自定义时间段查询按钮单击处理
 * @param report_name
 * @return
 */
function list_updatebutton_click(load_date, clear_data){
	var start_time = $("#startDate").val();
	var end_time = $("#endDate").val();
	if(start_time=='' || end_time==''){
		alert("请输入日期范围！")
	}else{
		$("#dateType").val("Custom");
		change_querylink();
		load_date();
	}
}
/**
 * 日期查询条件变化时更新“分页”与“导出CSV”链接的URL
 * @return
 */
function change_querylink(){
	$(".queryLink").each(function() {
		var url=$(this).attr("href");
		if(url.indexOf("?")<0){
			url+="?";
		}
		url=url.replace(/&dateType=[^&]*/,'');
		url=url.replace(/&startDate=[^&]*/,'');
		url=url.replace(/&endDate=[^&]*/,'');
		url=url+"&dateType="+$("#dateType").val();
		url=url+"&startDate="+$("#startDate").val();
		url=url+"&endDate="+$("#endDate").val();
		$(this).attr("href", url);
	});

}