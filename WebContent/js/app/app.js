var status,appDialogId ='';
    
$(function() {
    //加载列表
    loadList(1);
    //添加应用
    $('.btn_add_app').click(function() {
        appDialogId = getToken();
        initMsg({}, function(){
            $.unblockUI({
                onUnblock:function(){
                    appDialogId = '';
                    $('body').css({'overflow-y':'auto'});
                }
            });
            loadList(1);
        });
        // $(".btn_save").unbind("click").click(function() {
        //     if (verify_null($("input[name='name']"), "应用名称不能为空！") &&
        //         verify_null($("input[name='platform']"), "平台不能为空！", true)) {
        //         var name = $("input[name='name']").val();
        //         var platform = $("input[name='platform']").val();
        //         var description = $("textarea[name='description']").val();
        //         $.ajax({
        //             type: "get",
        //             url: "/app/save?name=" + name + "&platform=" + platform + "&description=" + description,
        //             data:"rnd="+Math.random(),
        //             dataType: "json",
        //             beforeSend: function(XMLHttpRequest){
        //             },
        //             success: function(data, textStatus){
        //                 if (data["status"] == "ok") {
                            
        //                 }
        //             },
        //             complete: function(XMLHttpRequest, textStatus){
        //             },
        //             error: function(){
        //             }
        //         });
        //     }
        // });
    });
    
    // $(window).resize(function(){
    //     adjustMsg();
    // });
});
    
/* 清除列表项 */
function clearList() {
    $("#tb_list tr").each(function (i) {
        if ($(this).attr("class") != "tit") {
            $(this).remove();
        }
    });
    //$("#tb_list").append('<tr id="loading_row_init"><td colspan="7">加载中...</td></tr>');
}

/* 加载列表
 * page 页码	
 */
function loadList(page, status) {
    $.ajax({
        type: "get",
        url:"app/list?pageNo=" + page + (status ? status : ""),
        dataType: "json",
        data:"rnd="+Math.random(),
        beforeSend: function(XMLHttpRequest){
        },
        success: function(data, textStatus){
            var list = data;
           
            var apps = list.appPage.result;
            var totalPages = list.appPage.totalPages;
            var pageNo = list.appPage.pageNo;
            var len = apps.length;
            var odd = true;
            clearList();
            pageAnchorsGenerate(totalPages,pageNo,'',loadList);
            if(!list.appPage || list.appPage.result.length === 0){
                $('#tb_list tr').not(':first').remove();
                $('#tb_list').append('<tr id="loading_row_init"><td colspan="4">没有记录!</td></tr>');
                return;
            }
            for (i = 0; i < len; i++) {
                var elem = "";
                elem += '<tr class=' + (odd === true ? "" : "transbg") + ' id="tr_' + apps[i].id + '">';
                odd = !odd;
                elem += '<td class="pos_rel chk" style="display:block"><input type="checkbox" name="record_ch" value="' + apps[i].id + '"></td>';
                elem += '<td><a href="javascript:void(0)" onclick="editApp(' + apps[i].id + ',event)" class="tb_name">' + apps[i].name + '</a></td>';
                elem += '<td id="td_status_' + apps[i].id + '" style="text-align:left; padding:0 30px;">';
                if (apps[i].status.indexOf("normal")>-1)
                    elem += '<img src="images/icon_state_normal.gif" width="16" height="16" alt="正常" />正常';
                else
                    elem += '<img src="images/icon_state_pause.gif" width="16" height="16" alt="暂停" />暂停';
                elem += '</td>';
                elem += '<td class="tb_platform">' + apps[i].platform + '</td>';
                elem += '<td>' + apps[i].ad_slot_count + '</td>';
                //					elem += '<td><span class="green">28</span>&nbsp;元</td>';
                elem += '<td><a href="javascript:void(0)" class="icon_modify" onclick="editApp(' + apps[i].id + ',event)">编辑</a></td>';
                elem += '</tr>';
                $("#tb_list").append(elem);
            }
            
        },
        complete: function(XMLHttpRequest, textStatus){
        },
        error: function(){
        }
    });
}

/* 更新单独一项
 * obj 需要刷新的行
 * name 应用名称
 * platform 平台
 */
function updateItem(obj, name, platform) {
    setTimeout(function() {
        $(".loading_row").remove();
        obj.find(".tb_name").html(name);
        obj.find(".tb_platform").html(platform);
    }, 1000);
}

/* 初始化浮出层 */
function initMsg(app,callback,id) {
    //初始化数据
    $('.err').remove();
    if(app.name&&app.name!==""){
        $('.msg_content_top .title span').eq(0).text('编辑应用');
    }else{
        $('.msg_content_top .title span').eq(0).text('新建应用');
    }
    $("input[name='name']").val(app.name);
    $(".ui_radio").removeClass("ui_radio_checked");
    if (app.platform&&app.platform == "android") {
        $(".android").parent().addClass("ui_radio_checked");
        $("input[name='platform']").val(app.platform);
    } else if (app.platform&&app.platform == "iOS"){
        $(".ios").parent().addClass("ui_radio_checked");
        $("input[name='platform']").val(app.platform);
    }
    $("textarea[name='description']").val(app.description?app.description:"");
    
    //弹出浮出层
    var msg = $('.msg_app');
    popBox(msg);
    console.log(id);
    // var height = $(window).height();
    // var width = $(document).width();
    // $.blockUI({
    //     css: {color: '#cccccc',border:'0',width:'818px','left' : width/2 - (msg.width() / 2),'top' : height/2 - (msg.height() / 2),background:'none',padding:'0px'},
    //     message: $('.msg_app')
    //     //onBlock:function(){$('.blockMsg').draggable()}
    // });
    
    //事件处理
    $(".close").unbind('click').click(function() {
        $.unblockUI({
            onUnblock:function(){
                 appDialogId = '';
                 $('body').css({'overflow-y':'auto'});
            }
        });
    });
    $('.ui_radio_os').click(function(){
        $('.ui_radio_os').removeClass('ui_radio_checked');
        $(this).addClass('ui_radio_checked');
    });
    function saveApp(){
        if (verify_null($("input[name='name']"), "应用名称不能为空！") &&
            verify_null($("input[name='platform']"), "平台不能为空！", true)) {
            var _self = this;
            $(_self).unbind('click');
            var name = $("input[name='name']").val();
            var platform = $("input[name='platform']").val();
            var description = $("textarea[name='description']").val();
            // console.log(id);
            // if (description === "") description = " ";
            $.ajax({
                type: "get",
                url: "/app/save?name=" + name + "&platform=" + platform + "&description=" + description +(id?("&id=" + id):""),
                dataType: "json",
                data:"rnd="+Math.random(),
                beforeSend: function(XMLHttpRequest){
                },
                success: function(data, textStatus){
                    if (data["status"] == "ok") {
                        callback();
                    }
                },
                complete: function(XMLHttpRequest, textStatus){
                },
                error: function(){
                    alert('保存失败!');
                    $(".btn_save").unbind('click').click(function() {
                        saveApp.apply(this);
                    });
                }
            });
        }
    }
    $(".btn_save").unbind('click').click(function() {
        saveApp.apply(this);
    });
}

/* 编辑应用
 * id 应用编号
 */
function editApp(id,event) {
    var evenObj = null;
    evenObj = $(event.target);
    var appDialogId;
    var currentDialogId = getToken();
    appDialogId = currentDialogId;
    $.ajax({
        type: "get",
        url: "/app/edit/" + id,
        dataType: "json",
        data:"rnd="+Math.random(),
        beforeSend: function(XMLHttpRequest){
            
        },
        success: function(data, textStatus){
            if(currentDialogId != appDialogId) return;
            

            initMsg(data.app,function(){
                var name = $("input[name='name']").val();
                var platform = $("input[name='platform']").val();
                var obj = evenObj.parent().parent().find("td:eq(0)");
                obj.append("<div class='loading_row'><div class='txt'>正在刷新数据&nbsp;&nbsp;&nbsp;<img src='images/loading_m.gif' /></div></div>");
                updateItem(evenObj.parent().parent(), name, platform);
                $.unblockUI({
                    onUnblock:function(){
                         appDialogId ='';
                         $('body').css({'overflow-y':'auto'});
                    }
                });
            },id);
            
        },
        complete: function(XMLHttpRequest, textStatus){
            
        },
        error: function(){
            
        }
    });
}

function showMask() {
    var height = $(".panel_table").css("height").replace("px", "");
    var padTop = height / 2 - 58;
    $('.loading').css('display', 'block').css('padding-top', padTop + "px").css("height", (height - padTop) + "px");
}

function hideMask() {
    $('.loading').css('display', 'none');
}
//  function adjustMsg() {
//     var body = $('.msg_app').is(':visible')?$('.msg_app'):$('.msg_ad');
//     $("body>.blockMsg").css({top:$(window).height()/2 - (body.height() / 2) + "px"});
//     // $("body>.blockMsg").stop(true).animate({top:$(window).height()/2 - (body.height() / 2) + "px"},300);
// }