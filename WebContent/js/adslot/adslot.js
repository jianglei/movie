 var apps,adSlotDialogId = '' ,adList;

$(function() {
    //time choose
    $('.timeChoose').timeslot({value:(new Array(169)).join(0)});
    loadList(1);
    //最大化窗口

    adList = new window.AdList({listUrl:'adslot/showAd/',baseUrl:'adslot/showAd/',special:'adslot'});
    adList.initUI();

    $('#adslot_code .maxwin').click(function(){
        
       var widthW = $(window).width(),
        heightW = $(window).height();
        $('#adslot_code .code_body').css({'max-height':(heightW - 38)+'px','width':widthW+'px'});
        $('#adslot_code').appendTo('body').animate({
            'z-index':'100000',
            'top':'0',
            'right':'0',
            'width' :widthW+'px',
            'height' : heightW+'px'
            
        },300);
    });
    //最小化窗口
    $('#adslot_code .minwin').click(function(){
        $('#adslot_code').appendTo('#get_code').animate({
            'z-index':'999',
            'top':'40px',
            'right':'20px',
            'width' :'400px',
            'height' : '340px'
            
        },300,function(){
             $('#adslot_code .code_body').css({'max-height':'302px','width':'400px'});
        });
        
    });
    
    //删除入口图片
    $('a.delete_pic').click(function(){

        $('#pic'+ ($(this).parent().index()+1) +'_show').attr('src', '/images/img_upload.gif').siblings('.err').remove();
        updateImagesPath();
    });
    //上传入口图片
    $('a.choosefile input').change(function(){
        upload(this,'pic',$(this).attr('feedback'), 'updateImagesPath','#adslot_form',$('#landingSize').val());
    });
    //添加广告
    var addAd = new window.AddAd({addButtonLisner:'#tb_list'});
    addAd.initUI();
    addAd.region.initUI();
    var region = new window.Region({type:'adslot'});
    region.initUI();
    SyntaxHighlighter.highlight();
    //解决firefox下 file button大小和chrome不同的问题
    if($.browser.mozilla){
        $('#file1,#file2,#file3,#file4').css('left','-28px');
    }
    //高级设置 隐藏切换按钮
    $(".show_pro").unbind("click").click(function() {
        var pnlPro = $(this).closest('tr').next('tr').find(".pnl_pro").closest('td');
        if (pnlPro.is(":visible")) {
            $(this).removeClass("hide");
            pnlPro.hide();
        }
        else {
            $(this).addClass("hide");
            pnlPro.show();
        }
        adjustMsg();
    });
    //广告网络交换
    $('.ui_radio_netaccess').click(function(){
        $('.ui_radio_netaccess').not(this).removeClass('ui_radio_checked');
        $(this).toggleClass('ui_radio_checked');
        
        if($(this).hasClass('ui_radio_checked')){
            $('#accesstype').val($(this).attr('accesstype'));
            $('#ui_radio_jiaohuan,#ui_radio_uads').prop('checked',true);
            $('#xppercent,#appkey,#uadsPercent,#uadsKey').prop('disabled',false);
            $(this).nextAll('.accesstype_list').show();
        }else{
            $('#accesstype').val('none');
            $('#ui_radio_jiaohuan,#ui_radio_uads').prop('checked',false);
            $('#xppercent,#appkey,#uadsPercent,#uadsKey').prop('disabled',true);
            $(this).nextAll('.accesstype_list').hide();
        }
            
    });
    //设备切换
    $('.ui_radio_device').click(function(){
        
        if(!$(this).hasClass('ui_radio_disabled')){
            $(this).toggleClass('ui_radio_checked');
            if($('.ui_radio_device').filter('.ui_radio_checked[platform=iOS]').length>0){
                $('.ui_radio_device[platform=android]').addClass('ui_radio_disabled');
            }else if($('.ui_radio_device').filter('.ui_radio_checked[platform=android]').length>0){
                $('.ui_radio_device[platform=iOS]').addClass('ui_radio_disabled');
            }else{
                $('.ui_radio_device').removeClass('ui_radio_disabled');
            }
            var platform,device = $('.ui_radio_device').map(function(){
                if($(this).hasClass('ui_radio_checked')){
                    platform = $(this).attr('platform');
                    return $(this).attr('device');
                }
            }).get().join(',');
            $('input[name=device]').val(device);
             $('input[name=platform]').val(platform);
            landingTypeChange();
            
        }
        
    });
    // wap 模板
    $('a.ui_radio_wapTemplate').click(function(){
        $(this).addClass('ui_radio_checked').siblings('.ui_radio').removeClass('ui_radio_checked');
        $('#template').val($(this).attr('tpType'));
        landingTypeChange();
    });
    //radio 切换
    $('.ui_radio_entrance').click(function(){
        $('.ui_radio_entrance').removeClass('ui_radio_checked');
        $(this).addClass('ui_radio_checked');
        
        $('input[name=landingType]').val($(this).find('span').attr('adSlotLandingType'));
        landingTypeChange();
    });
    //字体大小初始化
    $('#textSizeAdSlot .pop_menu ul').fontSizePicker({valueLink:'textSizeAdSlot'});
    $('#ui_radio_jiaohuan').nextAll('input').prop('disabled',false);
    $('#ui_radio_jiaohuan,#ui_radio_uads').unbind('click').click(function(){
        $(this).nextAll('input').prop('disabled',!$(this).prop('checked'));
    });
    $(".icon_modify").click(function() {
        //alert($(this).parent().parent().find("td:eq(0)").css("height"));
        $(this).parent().parent().find("td:eq(0)").append("<div class='loading_row'><div class='txt'>正在刷新数据<img src='images/loading.gif' /></div></div>");
    });

    $(".btn_add_pos").click(function() {
        adSlotDialogId = getToken();
        $("input[name='id']").val("");
        $.get('/adslot/add1',function(data){
            if(data.status == 'ok'){
                apps = data.appPage.result;
            }
            showMsg({timeslots:'newAdslot'}, function() {
                $.unblockUI({
                    onUnblock:function(){
                        adSlotDialogId = '';
                    }
                });
                loadList(1);
                $(".btn_save_adslot").unbind("click");
            });
        });
    });
    //绑定获取代码
    $('.btn_add_code').bind('click',function(e){
        e.stopPropagation();
        //                    var data = getCode($(this).prev('.pos_rel').find('input').val());
        initCodeList();
        var height = $(window).height(),
        width = $(document).width(),
        codeWidth= $('#get_code').width(),
        codeHeight= $('#get_code').height();
        
        $.blockUI({
            css: {color: '#cccccc',border:'0',width:codeWidth+'px',height:codeHeight+'px','left' : width/2 - (codeWidth / 2),'top' : height/2 - ( codeHeight/ 2),background:'none',padding:'0px'},
            message: $('#get_code')
            //onBlock:function(){$('.blockMsg').draggable()}
        });
        return false;
        
    });
    //获取具体代码
    $('#choose_adslot_item').delegate('input.get_code_btn',"click",function(){
        $('.c_adslot_body .code_body').show();
        var that = this;
        var hostName = location.hostname;
        $('#code_review,#code_review2').each(function(){
            var url = (hostName == 'ufp.umeng.com'?'http://ex.mobmore.com':'')+$(this).attr('base_url')+$(that).attr('adslot_id');
            $(this).attr('href',url).text(url);
        });
        
        $('span.adslot_id').text($(this).attr('adslot_id'));
        $('.c_adslot_body div.landing_type').hide().filter('.'+$(this).attr('landingType')).show();
    });
    $("#get_code .close").unbind('click').click(function() {
        setTimeout($.unblockUI, 50);
    });

    $(window).resize(function(){
        adjustMsg();
    });
})
/*
 * 获取代码部分的广告位列表
 * 
 */
function initCodeList(){
    $.ajax({
        type: "get",
        url:"adslot/list?pageSize=100000",
        dataType: "json",
        data:"rnd="+Math.random(),
        beforeSend: function(XMLHttpRequest){
            $('#get_code #choose_adslot_item tr').remove();
            $('#get_code #choose_adslot_item').append('<tr id="loading_row_init"><td colspan="4">加载中请稍等!</td></tr>');
        },
        success: function(data, textStatus){
            if(!data.adSlotPage){$('#loading_row_init td').text('没有记录！');return;}
            var list = data.adSlotPage.result,elem="";
            for(var i = 0,len = list.length;i<len;i++){
                elem += '<tr class="border_bottom"><td width="140" title="'+ list[i].name +'">'+ list[i].name +'</td><td width="85" title="'+
                    (list[i].app_name?list[i].app_name:'-') +'">'+ (list[i].app_name?list[i].app_name:'-') +'</td><td width="85">' +
                    (list[i].landing_size?list[i].landing_size:'-') +'</td>';
                elem += '<td width="120"><input landingType='+ list[i].landing_type +' adslot_id="'+list[i].id+'" class="get_code_btn green_btn" type="button" value="获取代码>>"/></td></tr>';
            }
            $('#get_code #choose_adslot_item tr').remove();
            $('#get_code #choose_adslot_item').append(elem);
            //                                 $('#choose_adslot .c_ad_page_detail span').eq(0).text('1-'+ data.adSlotPage.totalItems +'条').end().eq(1).text('(共'+ data.adSlotPage.totalItems +'条)');
        },
        error: function(){
            $('#loading_row_init td').text('加载失败！');
        }
    });
}
/*
 * 变换入口尺寸
 *
 */
function landingTypeChange(){
    var platform =$('input[name=platform]').val(),
    template = $('#template').val()!==''? $('#template').val():'applist',
    landingType = $('input[name=landingType]').val() ;
    if(landingType!==''&&platform!==''){
        var landingTypeCol = {
            android:{
                banner:{size:'202x55'},
                wap:{size:{applist:'202x55',horizon_bigimage:'424x380'}},
                embed:{size:'202x55'},
                bigimage:{size:'640x340'},
                custom:{size:'202x55'},
                text:{size:'-'}
            },
            iOS:{
                banner:{size:'320x50'},
                wap:{size:{applist:'320x50',horizon_bigimage:'480x320'}},
                embed:{size:'320x50'},
                bigimage:{size:'640x340'},
                custom:{size:'320x50'},
                text:{size:'-'}
            }
            
        }
        if(landingType!='wap'){
            $('.ui_noselect span').text(landingTypeCol[platform][landingType].size);
            $('#landingSize').val(landingTypeCol[platform][landingType].size);

        }else{

            $('.ui_noselect span').text(landingTypeCol[platform][landingType].size[template]);
            $('#landingSize').val(landingTypeCol[platform][landingType].size[template]);
        }
    }
}

/*
 * 获得所填数据
 */
function getADSlot() {
    $('.timeChoose').updateTime();
    var adslot={
        id : $("input[name='id']").val(),
        name : $("input[name='name']").val(),
        landingType : $("input[name='landingType']").val(),
        device : $("input[name='device']").val(),
        platform : $("input[name='platform']").val(),
        landingSize : $("input[name='landingSize']").val(),
        displayStrategy:$("input[name='displayStrategy']").val(),
        // textSize:$("input[name='textSizeAdSlot']").val(),
        appName : $("input[name='appName']").val()!='不设置'?$("input[name='appName']").val():'',
        timeslots : $("input[name='timeslots']").val()};

    for (i = 0; i < apps.length; i++) {
        if (adslot.appName == apps[i].name) {
            adslot.appId = apps[i].id;
            break;
        }
    }
    if(adslot.landingType =='wap'){
        adslot.template = $('#template').val();
    }else if (adslot.landingType =='bigimage'){
        adslot.template = 'vertical_bigimage';
    } else if(adslot.landingType =='text'){
        adslot.textSize = $("input[name='textSizeAdSlot']").val();
    }
    adslot.areas = $("input[name='adslotareas']").val();
    if ($("input[name='enablePreload']:checked").index() == 0) adslot.enablePreload = "yes";
    else adslot.enablePreload = "no";
    if ($("input[name='enablePage']:checked").index() == 0) adslot.enablePage = "yes";
    else adslot.enablePage = "no";
    adslot.channels = $("input[name='adslotChannels']").val();
    adslot.landingImages = $('#landing_images').val();
    adslot.adNetworkStrategy = $('#accesstype').val();
    if($('.accesstype_list').is(':visible')){
        if($('#ui_radio_jiaohuan').prop('checked')){
            adslot.xpEnable = 1;
            adslot.xpPercent = $('#xppercent').val();
            adslot.appkey = $('#appkey').val();
        }else{
            adslot.xpEnable = 0;
        }
        if($('#ui_radio_uads').prop('checked')){
            adslot.uadsEnable = 1;
            adslot.uadsPercent = $('#uadsPercent').val();
            adslot.uadsKey = $('#uadsKey').val();
        }else{
           adslot.uadsEnable = 0;
        }
    }else{
        adslot.uadsEnable = 0;
        adslot.xpEnable = 0;
    }
    
    
    
    return adslot;
}

/*
 * 初始化浮出层数据
 * adslot ADSlot数据对象
 */
function initMsg(adslot) {
    $(".step2").hide();
    $(".step1").show();
    //		$(".special").hide();
    if ($("table.pnl_pro").is(':visible')) {
        $("table.pnl_pro").closest('td').hide();
        $(".advance_set_show_pro").removeClass("hide");
    }
    $(".err").remove();
    $("img.landingImagesimg").attr('src','/images/img_upload.gif');
    //广告位名称
    if (adslot.name ) $("input[name='name']").val(adslot.name);
    else $("input[name='name']").val("");

    //入口类型
    if (adslot.landingType ) {
        $("input[name='landingType']").val(adslot.landingType);
        $(".ui_radio_entrance").removeClass("ui_radio_checked")
        .has("."+adslot.landingType).addClass('ui_radio_checked');
    } else {
        $(".ui_radio_entrance").removeClass("ui_radio_checked");
        $("input[name='landingType']").val("");
    }

    //入口尺寸
    if (adslot.landingSize ) {
        $(".landingSize .text").html(adslot.landingSize);
        $('input[name="landingSize"]').val(adslot.landingSize);
    }else{
        $(".landingSize .text").html('');
        $('input[name="landingSize"]').val('');
    }
    //wap 模板
    $('#template').val('');
    $('.ui_radio_wapTemplate').removeClass('ui_radio_checked');
    if(adslot.template){
        $('#template').val(adslot.template);
        $('.ui_radio_wapTemplate').filter('[tpType="'+adslot.template +'"]').addClass('ui_radio_checked');
    }
    //所属应用
    if(apps){

        var len = apps.length;
        var appsHtml = "<li><a href='javascript:void(0)' content='不设置' pointto='appName'>不设置</a></li>";
        var cur = "";
        for(i = 0; i < len; i++) {
            if (apps[i].id == adslot.appId) cur = apps[i].name;
            appsHtml += "<li><a href='javascript:void(0)' content='"+apps[i].name+"' pointto='appName'>" + apps[i].name + "</a></li>";
        }
        $("#apps .pop_menu ul").html(appsHtml);
    }
    if (adslot.appId ) {
        $("#apps .text").html(cur);
        $("input[name='appName']").val(cur);
    } else {
        $("#apps .text").html("不设置");
        $("input[name='appName']").val("");
    }
    initUISelect();

    //适合机型
    (function(){
         $(".ui_radio_device").removeClass("ui_radio_checked ui_radio_disabled");
         $('input[name="platform"],input[name="device"]').val('');
        if (adslot.device) {
            var device = adslot.device.split(',');
            //$("input[name='device']").val(adslot.device);
            for(var i =0 ;i< device.length;i++){
                $('.'+device[i]).parent().trigger("click");
            }
        }
    })();
    
    var region = new window.Region({type:'adslot'});
    //投放地域
    region.initAreas(adslot.areas);
    //是否缓存广告
    if (adslot.enablePreload == "no") {
        $("input[name='enablePreload']:eq(1)").attr("checked", "checked");
    } else {
        $("input[name='enablePreload']:eq(0)").attr("checked", "checked");
    }
    //广告可否翻页
    if (adslot.enablePage == "no") {
        $("input[name='enablePage']:eq(1)").attr("checked", "checked");
    } else {
        $("input[name='enablePage']:eq(0)").attr("checked", "checked");
    }

    //更新渠道
    if (adslot.channels) $("input[name='adslotChannels']").val(adslot.channels);
    else $("input[name='adslotChannels']").val("");
   
    //弹出浮出层
    var msg = $('.msg_pos');
    var height = $(window).height();
    var width = $(document).width();
    $.blockUI({
        css: {color: '#cccccc',border:'0',width:'818px','left' : width/2 - (msg.width() / 2),'top' : height/2 - (msg.height() / 2),background:'none',padding:'0px'},
        message: $('.msg_pos')
        //onBlock:function(){$('.blockMsg').draggable()}
    });

    /* 事件处理部分 */
    $(".close").unbind('click').click(function() {
        $.unblockUI({
            onUnblock:function(){
                adSlotDialogId = '';
            }
        });
    });
     //投放方式
     $("#displayStrategy span").text("请选择投放方式");
     $('input[name="displayStrategy"]').val('');
    if (adslot.displayStrategy ) {
        var displayStrategyMap = {
            prior:'仅显示优先级最高广告',
            rotate:'多条广告轮播'
        }
        $("#displayStrategy span").text(displayStrategyMap[adslot.displayStrategy]);
        $('input[name="displayStrategy"]').val(adslot.displayStrategy);
    }
     //文字大小
    $("#textSizeAdSlot span").text("请选择文字大小");
    $('input[name="textSizeAdSlot"]').val('');
    if (adslot.textSize ) {
        $("#textSizeAdSlot span").text(adslot.textSize);
        $('input[name="textSizeAdSlot"]').val(adslot.textSize);
    }
    
    //入口图片
    var deleteBtns = $('a.delete_pic');
    var images = $("img.landingImagesimg");
    deleteBtns.css('visibility','hidden');
    if (adslot.landingImages && adslot.landingImages != "") {
        var ss = adslot.landingImages.split(",");
        $('#landing_images').val(adslot.landingImages);
        for(var index=0; index< ss.length; index++){
            var s = ss[index];
            if (s == ''||s=='/images/img_upload.gif'){
                continue;
            }
            deleteBtns.eq(index).css('visibility','visible'); 
            $(images[index]).attr('src', s);
        }
    }else{
        images.attr('src','/images/img_upload.gif');
        $('#landing_images').val('');
    }

    //投放时段
    $('.timeChoose .weekday input').prop('checked',true);
    if(adslot.timeslots && adslot.timeslots!="newAdslot"){
        $('input[name="timeslots"]').val(adslot.timeslots);
        $('.timeChoose').setTimeSlotValue(adslot.timeslots);
    }else if(!adslot.timeslots){
        var zeroSeries = (new Array(169)).join(0);
        $('input[name="timeslots"]').val(zeroSeries);
        initTimeSlots(zeroSeries);
    }else if(adslot.timeslots=="newAdslot"){
        var oneSeries = (new Array(169)).join(1);
        $('input[name="timeslots"]').val(oneSeries);
        $('.timeChoose').setTimeSlotValue(oneSeries);
    }
   
    //接入网络
    $('a.ui_radio_netaccess').removeClass('ui_radio_checked').nextAll('.accesstype_list').hide();
    $('#ui_radio_jiaohuan,#ui_radio_uads').prop('checked',false);
    $('#ui_radio_jiaohuan,#ui_radio_uads').nextAll('input').prop('disabled',true).val('');
    if(adslot.adNetworkStrategy&&adslot.adNetworkStrategy!='none'){
        (adslot.adNetworkStrategy=='fill'&&$('a.ui_radio_netaccess').eq(0).addClass('ui_radio_checked'))||(adslot.adNetworkStrategy=='percent'&&$('a.ui_radio_netaccess').eq(1).addClass('ui_radio_checked'));
        $('#accesstype').val(adslot.adNetworkStrategy);
        $('a.ui_radio_netaccess').nextAll('.accesstype_list').show();
    }else{
        $('#accesstype').val('none');
    }
    // if(adslot.xpEnable!=undefined&&adslot.xpEnable==1){
    if(adslot.xpEnable==1){
        $('#ui_radio_jiaohuan').prop('checked',true);
        $('#ui_radio_jiaohuan').nextAll('input').prop('disabled',false);
    }
    
    $('#xppercent').val(adslot.xpPercent);
    $('#appkey').val(adslot.appkey);
// }
    $('#uadsKey').val(adslot.uadsKey);
    $('#uadsPercent').val(adslot.uadsPercent);
    if(adslot.uadsEnable==1){
        $('#ui_radio_uads').prop('checked',true);
        $('#ui_radio_uads').nextAll('input').prop('disabled',false);
    }
    
}   


/* 
 * 显示浮出层
 * adslot ADSlot数据对象
 * callback 回调方法
 */
function showMsg(adslot, callback) {
    initMsg(adslot);

    //保存并继续
    $(".btn_save_continue").unbind("click").click(function() {
        var landingType = $('input[name="landingType"]').val();
        if (validateStepOne()) {
            $(".step1").hide();
            $(".step2").show();
            $('.ui_radio_wapTemplate').closest('tr').hide();
            if (landingType!='custom'&&landingType!='wap'){
                $('#upload_rukou_pic').hide();
            }else{
                $('#upload_rukou_pic').show();

                if(landingType=='wap'){
                    $('.ui_radio_wapTemplate').closest('tr').show();
                }
            }
            if(landingType=='text'){
                $('.landingSize').closest('tr').hide();
                $('#displayStrategy,#textSizeAdSlot').closest('tr').show();
            }else{
                $('.landingSize').closest('tr').show();
                $('#displayStrategy,#textSizeAdSlot').closest('tr').hide();
            }
            updateImagesPath();
            adjustMsg();
	
        }
    });

    //保存
    $(".btn_save_adslot").unbind("click").click(function() {
        if(validateStepTwo()){
            var model = getADSlot();
            var url = "/adslot/save";
            model.rnd = Math.random();
            if (!model.appId) {
                model.appId ="";
            }
            $.ajax({
                type: "get",
                url: url,
                data:model,
                dataType: "json",
                beforeSend: function(XMLHttpRequest){
                },
                success: function(data, textStatus,xhr){
                    if (data["status"] == "ok") {
                        callback();
                    }
                },
                complete: function(XMLHttpRequest, textStatus){
                },
                error: function(){
                }
            });
        }
    });
}
function validateStepOne(){
    return verify_null($("input[name='name']"))&&
            verify_null($("input[name='landingType']"), "",true) &&
           verify_null($("input[name='platform']"), "",true);
            
}
function validateStepTwo(){
   // var landingType = $('input[name="landingType"]').val();
     //((landingType!="custom"&&landingType!="wap")||((landingType=="custom"||landingType=="wap")&&
            //verify_null($("input[name='landingImages']"), "",true)))&&
     return verify_null($("input[name='timeslots']"), "",true)&&
            //($("input[name='landingType']").val()!='wap'||($("input[name='landingType']").val()=='wap'&&verify_null($('#template'),'',true)))&&
            verify_null($("input[name='adslotareas']"), "",true)&&
            ($('#ui_radio_jiaohuan').prop('checked')==false||(verify_null($("#xppercent"), "",false,$("#appkey"),{digits:true,max:100,min:0})&&verify_null($("#appkey"), "",false,$("#appkey"),{maxLength:30})))&&
            ($('#ui_radio_uads').prop('checked')==false||(verify_null($("#uadsPercent"), "",false,$("#uadsKey"),{digits:true,max:100,min:0})&&verify_null($("#uadsKey"), "",false,$("#uadsKey"),{maxLength:30})))&&
            ($("input[name='landingType']").val()!='text'||(verify_null($("input[name='displayStrategy']"))&&verify_null($("input[name='textSizeAdSlot']"))))
}
/* 编辑广告位
 * id 编号
 */
function editAdSlot(id,event) {
    $("input[name='id']").val(id);
    var currentDialogId = adSlotDialogId = getToken();
    var evenObj = null;
    evenObj = $(event.target);
    $.ajax({
        type: "get",
        url: "/adslot/edit1/" + id,
        dataType: "json",
        data:"rnd="+Math.random(),
        beforeSend: function(XMLHttpRequest){
	
        },
        success: function(data, textStatus){
            if(currentDialogId != adSlotDialogId ) return;
            var adslot = data.adSlot;
            apps = data.appPage.result;
            showMsg(adslot, function() {
                var obj = evenObj.parent().parent().find("td:eq(0)");
                obj.append("<div class='loading_row'><div class='txt'>正在刷新数据&nbsp;&nbsp;&nbsp;<img src='images/loading_m.gif' /></div></div>");
                updateItem(evenObj.parent().parent(), $("input[name='name']").val(), $("input[name='appName']").val(), $("input[name='landingType']").val(), $("input[name='landingSize']").val());
                $.unblockUI({
                    onUnblock:function(){
                        adSlotDialogId ='';
                    }
                });
            });
        },
        complete: function(XMLHttpRequest, textStatus){
	
        },
        error: function(){
	
        }
    });
}

/* 更新单独一项
 * obj 需要刷新的行
 * adslot ADSlot数据对象
 */
function updateItem(obj, name, appName, landingType, landingSize) {
    var landingArray = {
        bigimage:'大图',
        embed:'内嵌入口',
        banner:'横幅',
        custom:'自定义入口',
        wap:'WAP',
        text:'文字链'
    }
    setTimeout(function() {

        obj.find(".tb_name").text(name);
        obj.find(".tb_appName").text((appName!=''&&appName!='不设置')?appName:'-');
        obj.find(".tb_landingType").text(landingArray[landingType]);
        obj.find(".tb_landingSize").text(landingSize);
        $(".loading_row").remove();
    }, 1000);
}


/* 
 *自适应浮动层高度
 */
function adjustMsg() {
    var body = $('.msg_pos').is(':visible')?$('.msg_pos'):($('.msg_ad').is(':visible')?$('.msg_ad'):$('#get_code'));
    $("body>.blockMsg").stop(true).animate({top:$(window).height()/2 - (body.height() / 2) + "px"},300);
}
/* 加载列表
 * page 页码	
 */
function loadList(page, status) {
    $.ajax({
        type: "get",
        url:"adslot/list?pageNo=" + page + (status  ? status : ""),
        dataType: "json",
        data:"rnd="+Math.random(),
        beforeSend: function(XMLHttpRequest){
            //clearList();  //发送请求前，清理列表项
            //showMask();
        },
        success: function(data, textStatus){
            var list = data;
                  
            
            var adSlots = list.adSlotPage.result;
            //apps = list.appPage.result;
            var totalPages = list.adSlotPage.totalPages;
            var pageNo = list.adSlotPage.pageNo;
            var len = adSlots.length;
            var odd = true;
            pageAnchorsGenerate(totalPages,pageNo,'',loadList);
            if(!list.adSlotPage || list.adSlotPage.result.length === 0){
                $('#tb_list tr').not(':first').remove();
                $('#tb_list').append('<tr id="loading_row_init"><td colspan="4">没有记录!</td></tr>');
                return;
            }
            clearList();
            for (i = 0; i < len; i++) {
                var elem = "";
                elem += '<tr class="' + (odd === true ? "" : "transbg") + '" id="tr_' + adSlots[i].id + '">';
                odd = !odd;
                elem += '<td class="chk pos_rel" style="display:block;"><input type="checkbox" name="record_ch" value="' + adSlots[i].id + '"/></td>';
                elem += '<td><a href="javascript:void(0)" onclick="editAdSlot(' + adSlots[i].id + ',event)" class="tb_name">' + adSlots[i].name + '</a></td>';
                elem += '<td id="td_status_' + adSlots[i].id + '" class=" status" status="'+ adSlots[i].status +'">';
                if (adSlots[i].status=="normal")
                    elem += '<img src="images/icon_state_normal.gif" width="16" height="16" alt="正常" />正常';
                else if(adSlots[i].status=="app_pause")
                    elem += '<img src="images/icon_state_pause.gif" width="16" height="16" alt="应用暂停" />应用暂停';
                else
                    elem += '<img src="images/icon_state_pause.gif" width="16" height="16" alt="暂停" />暂停';
                elem += '</td>';
                elem += '<td class="center tb_appName">' + ((adSlots[i].app_name&&adSlots[i].app_name!=='')?adSlots[i].app_name:'-') + '</td>';
                switch(adSlots[i].landing_type){
                    case "embed":elem += '<td class="center tb_landingType">内嵌入口</td>';break;
                    case "custom":elem += '<td class="center tb_landingType">自定义入口</td>';break;
                    case "banner":elem += '<td class="center tb_landingType">横幅</td>';break;
                    case "wap":elem += '<td class="center tb_landingType">WAP</td>';break;
                    case "bigimage":elem += '<td class="center tb_landingType">大图</td>';break;
                    case "text":elem += '<td class="center tb_landingType">文字链</td>';break;
                                
                }
		
                elem += '<td class="center tb_landingSize">' + adSlots[i].landing_size + '</td>';
                elem += '<td class="center">';
                elem += '<a href="javascript:void(0)" onclick="editAdSlot(' + adSlots[i].id + ',event)" class="icon_modify">编辑</a>';
                //elem += '<a class="icon_report" href="#">报告</a>';
                elem += '<a class="icon_expend_rev extend" href="javascript:void(0)" >展开广告</a>';
                //					elem += '<a class="get_code" href="/api/wap?slot_id='+adSlots[i].id+'" target="_blank">获取代码</a>';
                elem += '</td></tr>';
	
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
    $("#tb_list tr").not('.tit').remove();
}
function showMask() {
    var height = $(".panel_table").css("height").replace("px", "");
    var padTop = height / 2 - 58;
    $('.loading').css('display', 'block').css('padding-top', padTop + "px").css("height", (height - padTop) + "px");
}
function hideMask() {
    $('.loading').css('display', 'none');
}
function updateImagesPath(){
    $('#landing_images').val($("img.landingImagesimg").map(function(){
        var index =  $("img.landingImagesimg").index($(this));
        if($(this).attr('src')!=='/images/img_upload.gif'&&$(this).attr('src')!==""&&$(this).attr('src')!==null){
            $('a.delete_pic').closest('tr').show();
            $('a.delete_pic').eq(index).css('visibility','visible');
                
            return $(this).attr('src');
        }else{
            $('a.delete_pic').eq(index).css('visibility','hidden');
        }
    }).get().join(","));
    if($('#landing_images').val()===''){
        $('a.delete_pic').closest('tr').hide();
    }
}