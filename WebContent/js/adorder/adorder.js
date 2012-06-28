 var adOrderDialogId = '',adList = {};
$(function() {
    $(".local_date"). each(function (i) {
        var date = new Date($(this).text());			
        $(this).text(date.getFullYear()+ "-"+ date.getMonth() + "-" + date.getDay());
    })
    loadList(1);
    adList = new window.AdList();
    adList.initUI();
    var addAd = new window.AddAd({addButtonLisner:'#tb_list'});
    addAd.initUI();
    addAd.region.initUI();
    $('#datepicker_start_adorder').datetimepicker({
        dateFormat: 'yy-mm-dd',
        changeYear:true,
        changeMonth:true,
        monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        dayNames:['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        dayNamesMin:['日','一','二','三','四','五','六'],
        timeText:'时间',
        hourText:'时',
        minuteText:'分',
        currentText:'现在',
        closeText:'确定',
        onClose: function(dateText, inst) {
            var endDateTextBox = $('#datepicker_end_adorder');
            if (endDateTextBox.val() !== '') {
                var testStartDate = new Date(dateText);
                var testEndDate = new Date(endDateTextBox.val());
                if (testStartDate > testEndDate)
                    endDateTextBox.val(dateText);
            }
            else {
                endDateTextBox.val(dateText);
            }
            $(this).blur();
        },
        onSelect: function (selectedDateTime){
            var start = $(this).datetimepicker('getDate');
            $('#datepicker_end_adorder').datetimepicker('option', 'minDate', new Date(start.getTime()));
        }
    });

    $('#datepicker_end_adorder').datetimepicker({
        dateFormat: 'yy-mm-dd',
        changeYear:true,
        changeMonth:true,
        monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        dayNames:['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        dayNamesMin:['日','一','二','三','四','五','六'],
        timeText:'时间',
        hourText:'时',
        minuteText:'分',
        currentText:'现在',
        closeText:'确定',
        onClose: function(dateText, inst) {
            var startDateTextBox = $('#datepicker_start_adorder');
            if (startDateTextBox.val() !== '') {
                var testStartDate = new Date(startDateTextBox.val());
                var testEndDate = new Date(dateText);
                if (testStartDate > testEndDate)
                    startDateTextBox.val(dateText);
            }
            else {
                startDateTextBox.val(dateText);
            }
            $(this).blur();
        },
        onSelect: function (selectedDateTime){
            var end = $(this).datetimepicker('getDate');
            $('#datepicker_start_adorder').datetimepicker('option', 'maxDate', new Date(end.getTime()) );
        }
    });

    $('.btn_add_order').click(function() {
        adOrderDialogId = getToken();
        $("input[name='id']").val("");
        showMsg(new ADOrder(), function() {
            $.unblockUI({
                onUnblock:function(){
                     adOrderDialogId = '';
                }
            });
            loadList(1);
        });
    });

    $(window).resize(function(){
        adjustMsg();
    });
});

/*
 * 加载列表
 * page 页码
 */
function loadList(page, status) {
    $.ajax({
        type: "get",
        url:"adorder/list?pageNo=" + page + (status  ? status : ""),
        dataType: "json",
        data:"rnd="+Math.random(),
        beforeSend: function(XMLHttpRequest){
        },
        success: function(data, textStatus){
            var list = data.adOrderPage;
            var adOrders = list.result;
            var totalPages = list.totalPages;
            var pageNo = list.pageNo;
            var len = adOrders.length;
            var odd = true;
            clearList();
            pageAnchorsGenerate(totalPages,pageNo,'',loadList);
            if(!list|| list.result.length === 0){
                $('#tb_list tr').not(':first').remove();
                $('#tb_list').append('<tr id="loading_row_init"><td colspan="4">没有记录!</td></tr>');
                return;
            }
            for (i = 0; i < len; i++) {
                var elem = "";
                elem += '<tr class="' + (odd === true ? "" : "transbg") + '"  id="tr_' + adOrders[i].id + '">';
                odd = !odd;
                elem += '<td class="chk pos_rel" style="display:block"><input type="checkbox" name="record_ch" value="' + adOrders[i].id + '" /></td>';
                elem += '<td><a href="javascript:void(0);" onclick="editAdOrder(' + adOrders[i].id + ',event)" class="tb_name">' + adOrders[i].name + '</a></td>';
                elem += '<td id="td_status_' + adOrders[i].id + '" style="text-align: left; padding: 0 10px;">';
                if (adOrders[i].status==='normal')
                    elem += '<img src="images/icon_state_normal.gif" width="16" height="16" alt="正常" />正常';
                else if(adOrders[i].status==='finish')
                    elem += '<img src="images/icon_state_complete.gif" width="16" height="16" alt="订单完成" />订单完成';
                else if(adOrders[i].status==='ready')
                    elem += '<img src="images/icon_state_abled.gif" width="16" height="16" alt="尚未投放" />尚未投放';
                else if(adOrders[i].status==='pause')
                    elem += '<img src="images/icon_state_pause.gif" width="16" height="16" alt="暂停" />暂停';
                else
                    elem += '<img src="images/icon_state_pause.gif" width="16" height="16" alt="未知" />未知';
                elem += '</td>';
                elem += '<td class="tb_customer">' + (adOrders[i].customer&&adOrders[i].customer!==''?adOrders[i].customer:'-') + '</td>';
                elem += '<td class="local_date tb_startTime">' + formatdate(new Date(adOrders[i].start_time), "yyyy-MM-dd HH:mm") + '</td>';
                elem += '<td class="local_date tb_endTime">' + (adOrders[i].end_time?formatdate(new Date(adOrders[i].end_time), "yyyy-MM-dd HH:mm"):'-') + '</td>';
                //					elem += '<td class="use center">';
                //					elem += '<div class="price">￥1000</div><div class="progress"><span style="width: 80px;"></span></div><div class="percent">80%</div>';
                //					elem += '</td>';
                elem += '<td><a href="javascript:void(0);" class="icon_modify" onclick="editAdOrder(' + adOrders[i].id + ',event)">编辑</a>';
                elem += '<a class="icon_expend_rev extend" href="javascript:void(0)">展开广告</a></td></tr>';
		
                $("#tb_list").append(elem);
            }
	
            
        },
        complete: function(XMLHttpRequest, textStatus){
        },
        error: function(){
        }
    });
}

/* 清除列表项 */
function clearList() {
    $("#tb_list tr").each(function (i) {
        if ($(this).attr("class") != "tit") {
            $(this).remove();
        }
    });
}

function showMask() {
    var height = $(".panel_table").css("height").replace("px", "");
    var padTop = height / 2 - 58;
    $('.loading').css('display', 'block').css('padding-top',
    padTop + "px").css("height", (height - padTop) + "px");
}

function hideMask() {
    $('.loading').css('display', 'none');
}

/*
 *自适应浮动层高度
 */
function adjustMsg() {
    var body = $('.msg_order').is(':visible')?$('.msg_order'):$('.msg_ad');
    $(".blockMsg").stop(true).animate({top:$(window).height()/2 - (body.height() / 2) + "px"},300);
}

/*
 * ADOrder对象
 * args 参数
 */
function ADOrder(id, name, customer, startTime, endTime,comments) {
    this.id = id;
    this.name = name;
    this.customer = customer;
    this.comments = comments;
    this.startTime = startTime;
    try{
        if( endTime.toString().trim()!="00:00:00")
            this.endTime= endTime;
    }catch (e){

    }
}

/*
 * 获得所填数据
 */
function getADOrder() {
    var id = $("input[name='id']").val();
    var name = $("input[name='name']").val();
    var customer = $("input[name='customer']").val();
    var startTime = ($('#datepicker_start_adorder').val().trim()+' 00:00').replace(/(\d{2}:\d{2})\s?(00:00)?\s?/,'$1:00'); //formatdate(new Date($('#datepicker_start_adorder').val()), "yyyy-MM-dd HH:mm:ss");
    var endTime = ($('#datepicker_end_adorder').val().trim()+' 00:00').replace(/(\d{2}:\d{2})\s?(00:00)?\s?/,'$1:00');//formatdate(new Date($('#datepicker_end_adorder').val()),"yyyy-MM-dd HH:mm:ss");
    var comments = $('textarea[name="comments"]').val();
    return new ADOrder(id, name, customer, startTime, endTime,comments);
}

/*
 * 初始化浮出层数据
 * adslot ADSlot数据对象
 */
function initMsg(adorder) {
    $(".err").remove();
    $("input[name='name']").val("");
    $("input[name='customer']").val("");
    $(".input_textarea").val("");
    $('#datepicker_start_adorder').val("");
    $('#datepicker_end_adorder').val("");
//                $('#datepicker_end_adorder').val("");
    //订单名称
    if (adorder.name ) $("input[name='name']").val(adorder.name);

    //外部ID
    if (adorder.customer ) $("input[name='customer']").val(adorder.customer);

    //开始日期
    if (adorder.startTime ) $('#datepicker_start_adorder').val(formatdate(new Date(adorder.startTime), "yyyy-MM-dd HH:mm"));

    //结束日期
    if (adorder.endTime ) $('#datepicker_end_adorder').val(formatdate(new Date(adorder.endTime), "yyyy-MM-dd HH:mm"));
    //说明
    if (adorder.comments ) $('textarea[name="comments"]').val(adorder.comments);
    
}

/*
 * 显示浮出层
 * adslot ADSlot数据对象
 * callback 回调方法
 */
function showMsg(adorder, callback) {
    initMsg(adorder);
    var height = $(window).height();
    var width = $(document).width();
    //obj = $(this).parent().parent().find("td:eq(0)");

    //弹出浮出层
    var msg = $('.msg_order');
    var height = $(window).height();
    var width = $(document).width();
    $.blockUI({
        css: {color: '#cccccc',border:'0',width:'818px','left' : width/2 - (msg.width() / 2),'top' : height/2 - (msg.height() / 2),background:'none',padding:'0px'},
        message: $('.msg_order')
        //onBlock:function(){$('.blockMsg').draggable()}
    });

    /* 事件处理部分 */
    $(".close").unbind('click').click(function() {
        $.unblockUI({
            onUnblock:function(){
                adOrderDialogId = '';
            }
        });
    });
    function saveOrder(){
        if (verify_null($("input[name='name']"), "订单名称不能为空！")&&verify_null($('#datepicker_start_adorder'), "开始时间不能为空",false,$('#datepicker_end_adorder'))) {
            var _self = this;
            $(_self).unbind('click');
            var model = getADOrder();
            var url = "/adorder/save";
            model.rnd = Math.random(),
            $.ajax({
                type: "get",
                url: url,
                data:model,
                dataType: "json",
                beforeSend: function(XMLHttpRequest){
                },
                success: function(data, textStatus){
                    if (data["status"] == "ok") {
                        //没有返回ok？？暂时写到complete处理
                    }
                },
                complete: function(XMLHttpRequest, textStatus){
                    callback();
                },
                error: function(){
                    alert('保存出错!');
                    $(_self).unbind("click").click(function() {
                        saveOrder.apply(_self);
                    });
                }
            });
        }
    }
    //保存
    $(".btn_save_order").unbind("click").click(function() {
        saveOrder.apply(this);
    });
}

/* 编辑广告位
 * id 编号
 */
function editAdOrder(id,event) {
    $("input[name='id']").val(id);
    var evenObj = $(event.target);
    var currentDialogId = adOrderDialogId = getToken();
    $.ajax({
        type: "get",
        url: "/adorder/edit/" + id,
        dataType: "json",
        data:"rnd="+Math.random(),
        beforeSend: function(XMLHttpRequest){
	
        },
        success: function(data, textStatus){
            if(currentDialogId != adOrderDialogId) return;
            var adorder = new ADOrder(data.adOrder.id, data.adOrder.name, data.adOrder.customer, data.adOrder.startTime, data.adOrder.endTime,data.adOrder.comments);
            showMsg(adorder, function() {
                var obj = evenObj.parent().parent().find("td:eq(0)");
                obj.append("<div class='loading_row'><div class='txt'>正在刷新数据&nbsp;&nbsp;&nbsp;<img src='images/loading_m.gif' /></div></div>");
                updateItem(evenObj.parent().parent(), $("input[name='name']").val(), $('#datepicker_start_adorder').val(), $('#datepicker_end_adorder').val(),$("input[name='customer']").val());
                $.unblockUI({
                    onUnblock:function(){
                        adOrderDialogId='';
                    }
                });
            });
        },
        complete: function(XMLHttpRequest, textStatus){
	
        },
        error: function(){
            alert('编辑失败!');
        }
    });
}

/* 更新单独一项
 * obj 需要刷新的行
 * adslot ADSlot数据对象
 */
function updateItem(obj, name, startTime, endTime,customer) {
    setTimeout(function() {
        $(".loading_row").remove();
        obj.find(".tb_name").html(name);
        obj.find(".tb_startTime").html(startTime);
        obj.find(".tb_endTime").html(endTime);
        obj.find(".tb_customer").html(customer==""?"-":customer);
    }, 1000);
}
