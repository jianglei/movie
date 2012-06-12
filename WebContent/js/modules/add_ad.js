window.AddAd = function(data){
    this.adSlotPage='';
    this.adOrderPage='';
    this.curId='';
    this.ad_type='';
    this.platform = '';
    this.template = '';
    this.entryType = '';
    this.langdingSize = '';
    this.isTextLandingType =false;
    this.stepTwoRequest = true;
    this.adDialogId = '';
    this.adSlotExistDialogId = '';
    this.data={
        addButtonLisner:'div.panel_btn_top',
        editButtonLisner:'#tb_list',
        adslotStrict:false
    };
    this.textSizeAdSlot = '';
    this.region= new window.Region({type:'ad'});
    $.extend(this.data,data);
};
window.AddAd.prototype={
    initUI : function(){
        var that = this;
        /* STEP1 */
        $('.ui_radio_pre').click(function(){
            $(this).toggleClass('ui_radio_checked');
            $('.ui_radio_pre').not(this).removeClass('ui_radio_checked');
            $('.blue').next('input').each(function(){
                $(this).prop('disabled',!$(this).closest('div').prev('div').find('a.ui_radio_pre').hasClass('ui_radio_checked'));
            });
            $("input[name='priceType']").val($(this).hasClass('ui_radio_checked')?$(this).attr('pricetype'):'none');
            $("input[name='costPrice']").val($(this).hasClass('ui_radio_checked')?$(this).closest('div').next('div').find('input').val():'');
                    
        });
        //关闭已有广告弹出层
        $('.close_adlist').click(function(){
            //$('#adListForUseContainer').fadeOut(200); 
            $('.msg_ad').unblock({onUnblock:function(){
                that.adSlotExistDialogId = '';
            }});
        });
        
        //选择已有广告
        $('#choose_ad_item').delegate('tr input[name="record_ch"]','click',function(){
            $('#choose_ad_item input[name="record_ch"]').not($(this)).prop('checked',false);
           
        });
        $('#adListForUseContainer .btns .btn_save_ad2').click(function(){
            var checkedAd = $('#choose_ad_item input[name="record_ch"]:checked');
             if(checkedAd.length>0){
                 $.ajax({
                        type: "get",
                        url: "/ad/edit2/" + checkedAd.val(), 
                        data:{rnd:Math.random()},
                        beforeSend: function(XMLHttpRequest){
                        },
                        success: function(data, textStatus){
                            that.initMsgAd2(data);
                        },
                        complete: function(XMLHttpRequest, textStatus){

                        },
                        error: function(){
                            alert('请求失败');
                        }
                });
             }else{
                 alert('请先选中一个已有广告!')
             }
        });
    	$('.ui_radio_net').click(function(){
    		$(this).toggleClass('ui_radio_checked');
    		if($.trim($(this).text())=='所有网络'){
                $('.ui_radio_net')[$(this).hasClass('ui_radio_checked')?'addClass':'removeClass']('ui_radio_checked');
            }else {
                if($('.ui_radio_net').not(':first').filter('.ui_radio_checked').length<3){
                    
                    $('.ui_radio_net').first().removeClass('ui_radio_checked');
                }else{
                    $('.ui_radio_net').first().addClass('ui_radio_checked');
                }
            }
            $('input[name="networks"]').val($('.ui_radio_net').not(':first').filter('.ui_radio_checked').map(function(i,e){
                return $(e).attr('net');
            }).get().join(','));
    	});
    	$('.blue').next('input').keyup(function(){
            $("input[name='costPrice']").val($(this).val());
        });
        //限定预算
        $("input[name='budget']").unbind('click').click(function(){
            $("input[name='budgetLimit']").prop('disabled',$("input[name='budget']:eq(0)").prop('checked'));
        });
	   /* STEP2 */
        $('a.ad_icon_return').click(function(){
            $('.step2').hide();
            $('.step1').show();
            that.adjustMsg();
            that.stepTwoRequest =false;
        });
        //尺寸更改
        $('#adLandingSize').bind('change',function(){
            that.changeAdPreviewSize($(this).val());
        });
        //上传文件
        $('.type_switch input[type=file]').change(function(){
            upload(this,$(this).attr('filetype'),$(this).attr('feedback'), 'changeUploadStyle','#ad_form',that.landingSize);
            
        });
        //key up 预览
        $('.previewName,.previewDes').keyup(function(){
            $('#'+$(this).attr('forshow')).text($(this).val());
        });
        //颜色选择
        $('#textColorPicker').change(function(){
            that.textPreviewChange();
        });
        $('input[name="textFont"],input[name="textSize"]').change(function(){
           that.textPreviewChange();
        });
        //字体初始化
        $('.ui_select_textFont .pop_menu ul').fontPicker({valueLink:'textFont'});
        $('.ui_select_textSize .pop_menu ul').fontSizePicker({valueLink:'textSize'});
        //导入ios itunes 链接
        $('#inputiTunes').click(function(){
            that.getDataFromiTunes($(this).prev().val());
        });
        //选择广告类型
	$('.ui_radio_type').click(function(){
        if(!$(this).hasClass('ui_radio_disabled')){
            $('.ui_radio_type').removeClass('ui_radio_checked');
            $(this).addClass('ui_radio_checked');
            $("input[name='contentType']").val($(this).attr('title'));
            if($(this).attr('title')=='web'){
                $(".ui_select_landingType .pop_menu ul li").eq(2).find('a').trigger('click');
            }else{
                if(that.platform != 'iOS'){
                    $(".ui_select_landingType .pop_menu ul li").eq(0).find('a').trigger('click');
                }else{
                    $(".ui_select_landingType .pop_menu ul li").eq(4).find('a').trigger('click');
                }
            }

            that.changeFormStyle();
        }
	});
        //选择广告形式
	$('.ui_radio_form').click(function(){
        if(!$(this).hasClass('ui_radio_disabled')){
            $('.ui_radio_form').removeClass('ui_radio_checked');
            $(this).addClass('ui_radio_checked');
            $("input[name='displayType']").val($(this).attr('style_type'));
            that.isTextLandingType = $(this).attr('style_type')=='text';
            that.changeFormStyle();
        }
	});
	//选择开始时间
	$('#datepicker_ad_start').datetimepicker({
		dateFormat: 'yy-mm-dd',
                timeFormat:'hh:mm',
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
			var endDateTextBox = $('#datepicker_ad_end');
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
			$('#datepicker_ad_end').datetimepicker('option', 'minDate', new Date(start.getTime()));
		}
	});
	
	//选择结束日期
	$('#datepicker_ad_end').datetimepicker({
	            dateFormat: 'yy-mm-dd',
                timeFormat:'hh:mm',
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
                beforeShow: function( input ) {
                    //添加不设置button
                    setTimeout(function() {
                        var buttonPane = $( input )
                            .datepicker( "widget" )
                            .find( ".ui-datepicker-buttonpane" );
                            buttonPane.find('.ui-datepicker-clear').remove();
                        $( "<button>", {
                            text: "不设置",
                            click: function() {
                               $('#datepicker_ad_end').val('').datepicker( "hide" ).blur();
                            }
                        }).appendTo( buttonPane ).addClass("ui-datepicker-clear ui-state-default ui-priority-primary ui-corner-all");
                    }, 1 );
                },
                onChangeMonthYear: function( input ) {
                    //添加不设置button
                    setTimeout(function() {
                        var buttonPane = $( input )
                            .datepicker( "widget" )
                            .find( ".ui-datepicker-buttonpane" );
                            buttonPane.find('.ui-datepicker-clear').remove();
                        $( "<button>", {
                            text: "不设置",
                            click: function() {
                                $('#datepicker_ad_end').val('').datepicker( "hide" ).blur();
                            }
                        }).appendTo( buttonPane ).addClass("ui-datepicker-clear ui-state-default ui-priority-primary ui-corner-all");
                    }, 1 );
                },
		onClose: function(dateText, inst) {
			var startDateTextBox = $('#datepicker_ad_start');
			if (startDateTextBox.val() != '') {
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
		onSelect: function (input){
                        var end = $(this).datetimepicker('getDate');
			$('#datepicker_ad_start').datetimepicker('option', 'maxDate', new Date(end.getTime()) );
			
		}
	});

        //选择广告位
        $(".tb_inner_wrap").delegate("#list_adslot tr td input", "click", function(){
            that.changeAdslotCheck();
        });
        //添加广告
        $(that.data.addButtonLisner).delegate(".btn_add_ad","click",function(event) {
            var currentDialogId = that.adDialogId =getToken();
		$("input[name='adid']").val("");
		$.ajax({
			type: "get",
			url: "/ad/add1",
			dataType: "json",
            data:"rnd="+Math.random(),
			success: function(data, textStatus){
                if(currentDialogId != that.adDialogId) return;
				that.adSlotPage = data.adSlotPage.result;
				that.adOrderPage = data.adOrderPage.result;
				var hasAdSlot = -1;
                                if($('.btn_add_ad').parents('tr.expend_panel').length>0){
                                    hasAdSlot=$('.btn_add_ad').parents('tr.expend_panel').prev('tr').find('td').eq(0).find('input').val();
                                    var adOrderId = $('.btn_add_ad').closest('tr.expend_panel').prev('tr').attr('id').replace('tr_','');
                                }
				//var len = that.adOrderPage.length;
				that.showMsg(new that.AD1(), function() {
					$.unblockUI({
                        onUnblock:function(){
                            that.adDialogId = '';
                            that.updateItem($(event.target));
                        }
                    });
                   
					
				},hasAdSlot,adOrderId,event);
				
			}
			
		});
	});
	$(that.data.editButtonLisner).delegate('td.edit_btn a','click',function(event){
                that.editAD($(this).attr('adpage_id'),event);
        });
        $(window).resize(function(){
		that.adjustMsg();
	});
        
    },
    AD1:function (adOrderId, name, budget, budgetLimit, priceType, costPrice, areas, networks, channels, adSlotId, startTime, endTime, landingSize, platform,fixedRank) {
            this.adOrderId = adOrderId;
            this.name = name;
            this.budget = budget;
            this.budgetLimit = budgetLimit;
            if(priceType!=''){
                this.priceType = priceType;
                this.costPrice = costPrice;
            }
            this.fixedRank = fixedRank;
            this.areas = areas;
            this.networks = networks;
            this.channels = channels;
            this.adSlotId = adSlotId;
            this.startTime = startTime;
            this.landingSize = landingSize;
            this.platform = platform;
            // this.template = template;
            // this.entryType = entryType;
            try{
                if( $.trim(endTime.toString())!="00:00:00")
                this.endTime= endTime;
            }catch (e){
                var time = formatdate(new Date(endTime), "yyyy-MM-dd HH:mm");
                if ( $.trim(time)!="00:00:00")
                this.endTime= endTime;
            }

    },
    verifyAD1 : function(){
        $('.err').remove();
      return   verify_null($("input[name='adname']"),'名称不能为空',true)
          &&verify_null($("input[name='li_adslot']"),'所属广告位不能为空',true)
           &&verify_null($("input[name='startTime']"), "时间不能为空")
           &&($("input[name='budget']").eq(0).prop('checked')||$("input[name='budget']").eq(1).prop('checked')&&verify_null($("input[name='budgetLimit']"),'不能为空',false))
           &&($('a.ui_radio_pre').filter('.ui_radio_checked').length ==0||verify_null($("input[name='costPrice']"), "计价不能为空",false,$('a.ui_radio_pre').filter('.ui_radio_checked').parent().next(),{num:true}))
           &&verify_null($("input[name='adareas']"), "投放区域不能为空")
           &&verify_null($("input[name='networks']"), "网络不能为空")
           &&($('#fixedRank').val()===""||$('#fixedRank').val()!==""&&verify_null($('#fixedRank'),"",false,null,{digits:true}))
    },
    getAD1:function () {
        var adSlotId = [],that = this;
        $("input[name='li_adslot']").val('');
        $("input[name='record_adslot']:checked").each(function() {
//                var hasSlot = true;
            adSlotId.push($(this).val());
            $("input[name='li_adslot']").val(adSlotId.join(''));
//              console.log(adSlotId.join(''));
        });
          
        if(that.verifyAD1()){
            var adOrderId = ($("#list_order option:selected").text()!='请选择订单'?$("#list_order option:selected").val():""),
             name = $("input[name='adname']").val(),
             budget = $("input[name='budget']:checked").val(),
             budgetLimit = $("input[name='budgetLimit']").val(),
             priceType = $("input[name='priceType']").val(),
             costPrice = $("input[name='costPrice']").val(),
             areas = $("input[name='adareas']").val(),
             networks = $("input[name='networks']").val(),
             channels = $("input[name='channels']").val(),
             platform = that.platform,
             landingSize = that.landingSize,
             fixedRank = $('#fixedRank').val(),
             // template = that.template,
             // entryType = that.entryType,
             startTime =($.trim($("input[name='startTime']").val())+' 00:00').replace(/(\d{2}:\d{2})\s?(00:00)?\s?/,'$1:00'), //formatdate(new Date($("input[name='startTime']").val().replace('-','/')), "yyyy-MM-dd HH:mm:ss");
             endTime = ($.trim($("input[name='endTime']").val())+' 00:00').replace(/(\d{2}:\d{2})\s?(00:00)?\s?/,'$1:00');//formatdate(new Date($("input[name='endTime']").val().replace('-','/')),"yyyy-MM-dd HH:mm:ss");
            return new that.AD1(adOrderId, name, budget, budgetLimit, priceType, costPrice, areas, networks, channels, adSlotId, startTime, endTime,landingSize,platform,fixedRank);
        }else{
            return;
        }
    },

    AD2:function (contentType, landingType, landingSize, displayType, title, adWords, provider, url, icon, description,img,textFont,textSize,textColor,platform) {
        this.contentType = contentType;
        this.landingType = landingType;
        this.landingSize = landingSize;
        this.displayType = displayType;
        this.title = title;
        this.adWords = adWords;
        this.provider = provider;
        this.url = url;
        this.icon = icon;
        this.description = description;
        this.img = img;
        if(this.displayType=='text'){
            this.textFont = textFont;
            this.textSize = textSize;
            this.textColor = textColor;
        }
        this.platform = platform;
    },
    verifyAD2 :function(){
        var that = this;
        return $('tr.type_switch:has(.f_req)').map(function(){
            if(that.platform != 'iOS'){
              if ($(this).hasClass(that.ad_type)){
                 var input =$(this).find('input[type="hidden"],input[type="text"],textarea');
                 if(input.attr('verify')=='file'){
                     return verify_file(input,input.attr('title'),input.attr('rule'))?1:0;
                 }else if(input.attr('verify')=='area'){
                     return verify_null(input,'',false,null,{maxLength:500})?1:0;
                 }else if(input.attr('verify')=='adWords'){
                     return verify_null(input,'',false,null,{maxLength:20})?1:0;
                 }else if(input.attr('verify')=='url'){
                     return verify_null(input,'',false,null,{url:'yes'})?1:0;
                 }else{
                     return verify_null(input,input.attr('title'))?1:0;
                 }
              }
            }else{
                if (($(this).hasClass(that.ad_type)&&!$(this).hasClass('android'))||$(this).hasClass('ios')){
                 
                var input = $(this).find('input[type="hidden"],input[type="text"],textarea');
                 
                 if(input.attr('verify')=='file'){
                     return verify_file(input,input.attr('title'),input.attr('rule'))?1:0;
                 }else if(input.attr('verify')=='area'){
                     return verify_null(input,'',false,null,{maxLength:500})?1:0;
                 }else if(input.attr('verify')=='adWords'){
                     return verify_null(input,'',false,null,{maxLength:20})?1:0;
                 }else{
                     return verify_null(input,input.attr('title'))?1:0;
                 }
              }
            }
             
        }).get().join('');
    },
	
    getAD2:function () {
        var that = this;
        var  apkFile=$('#url'),
             iconFile=  $('#icon'),
             contentType = $("input[name='contentType']"),
             landingType = $("input[name='adLandingType']"),
             displayType = $("input[name='displayType']"),
             appname = $("input[name='appname']").parents('tr.type_switch').hasClass(that.ad_type)?$("input[name='appname']"):$("input[name='webname']"),
             adWords = $("input[name='adWords']"),
             provider = $("input[name='provider']"),
             url = $("input[name='url']").parents('tr.type_switch').hasClass(that.ad_type)?apkFile:$("input[name='weburl']"),
             icon = $("input[name='icon']").parents('tr.type_switch').hasClass(that.ad_type)?iconFile:$("input[name='weblogo']"),
             bannerimg = $('#bannerimg'),
             textFont = '',//$('input[name="textFont"]'),
             textSize= $('input[name="textSize"]'),
             textColor = $('input[name="textColor"]'),
             description = $("textarea[name='description']");
            if(that.platform == 'iOS'){
                url = $("input[name='weburl']");
            }
            that.landingSize = $('#adLandingSize').val();
            //验证表单
        var verify = that.verifyAD2();
        if(verify.split('1').length==(parseInt(verify.length)+1)){
            return new that.AD2(contentType.val(), landingType.val(), that.landingSize, displayType.val(), appname.val(), adWords.val(), provider.val(), url.val(), icon.val(), description.val(),bannerimg.val(),textFont,textSize.val(),textColor.val(),that.platform);
        }else{
            return;
        }

},

//选择所属广告位
changeAdslotCheck : function(){
        var checkBox = $("#list_adslot tr td input"), that =this,
           checkedBox = checkBox.filter(':checked').eq(0);
           checkBox.prop('disabled',false).closest('tr').css({color:'black',cursor:'auto'});
           that.stepTwoRequest = true;
        if(checkedBox.length > 0){
            (function(){
                that.platform =checkedBox.closest('tr').find('td.platform').text();
                that.template = checkedBox.closest('tr').find('td.platform').attr('template');
                that.entryType = checkedBox.closest('tr').find('td.entryType').attr('entryType');
                that.landingSize =checkedBox.closest('tr').find('td.landing_size').text();
                that.textSizeAdSlot = checkedBox.closest('tr').find('td.textSizeAdSlot').attr('textSizeAdSlot');
                checkBox.each(function(){
                    if($(this).closest('tr').find('td.platform').text()!=that.platform ||
                    $(this).closest('tr').find('td.landing_size').text()!=that.landingSize){
                    // $(this).closest('tr').find('td.textSizeAdSlot').attr('textSizeAdSlot')!=that.textSizeAdSlot){
                        $(this).prop('disabled',true);
                        $(this).closest('tr').css({color:'grey',cursor:'not-allowed'});
                    }
                });
            })();
        }
},
/*
 * 初始化浮出层数据
 * ad1 AD1数据对象
 */
initMsgAd1:function (ad,hasAdSlot,adOrderId,event) {
        $(".step1").show();
        $(".step2").hide();
        $(".err").remove();
        //订单名称
        var that = this;
        //that.curId = ad.id;
        if (ad.name != undefined) $("input[name='adname']").val(ad.name);
        else $("input[name='adname']").val("");
        //adOrderId, name, budget, budgetLimit, priceType, price, areas, networks, channels, adSlotId
        //所属订单
        
        (function(){
            var len = that.adOrderPage.length;
            var orders = "";
            for (var i = 0; i < len; i++) {
                orders += '<option value="' + that.adOrderPage[i].id + '" '+
                ((adOrderId==that.adOrderPage[i].id)?' selected ':'')+' >' +
                that.adOrderPage[i].name + '</option>';
            }
            $("#list_order").html("").append('<option value="0">请选择订单</option>'+orders);
            
        })();
        
        if (ad.adOrderId) $("#list_order option[value='" + ad.adOrderId + "']").attr("selected", true);
        //所属广告位
        (function(){
            var len = that.adSlotPage.length;
            if(len >0 ){
                var  adSlotLandingTypeMap = {
                    bigimage:'大图',
                    embed:'内嵌入口',
                    banner:'横幅',
                    custom:'自定义入口',
                    wap:'WAP',
                    text:'文字链'
                };
                $(".tb_inner_wrap th input").prop('checked',false);
                $("input[name='li_adslot']").val('');
                var slots = "";
                for (var i = 0; i < len; i++) {
                        slots += '<tr><td width="1"><input type="checkbox"  name="record_adslot" value="' + that.adSlotPage[i].id + '" ';
                        slots += '/></td><td width="95">' + that.adSlotPage[i].name + '</td><td width="60" class="entryType" entryType="'+ that.adSlotPage[i].landing_type +'">' +
                         adSlotLandingTypeMap[that.adSlotPage[i].landing_type] + '</td><td width="80">' + 
                            (that.adSlotPage[i].app_name!=undefined?that.adSlotPage[i].app_name:'-') + '</td><td width="60" class="platform" template="'+ that.adSlotPage[i].template +'">' + 
                            that.adSlotPage[i].platform + '</td><td width="60" class="landing_size textSizeAdSlot" textSizeAdSlot="'+ 
                            (that.adSlotPage[i].text_size!=undefined?that.adSlotPage[i].text_size:'') +'" >' +(that.adSlotPage[i].landing_type!='text'? that.adSlotPage[i].landing_size:'-') + '</td></tr>';
                }
                $("#list_adslot").html("").append(slots);
                //和下面的if有重复 记得合并!!!!
                if(hasAdSlot>0){
                    var nowCheckedBox = $("#list_adslot input[type='checkbox'][value=" + hasAdSlot + "]");
                    nowCheckedBox.prop("checked", true);

                }
                if (ad.adSlotId) {
                    for (var ii = 0; ii < ad.adSlotId.length; ii++) {
                        $("#list_adslot input[type='checkbox'][value=" + ad.adSlotId[ii] + "]").prop("checked", true);
                    }
                }
            }else{
                $("#list_adslot").html('<tr><td>请先创建广告位...</td></tr>');
            }
            that.changeAdslotCheck();
        })();
        // 开始时间
        if (ad.startTime ) $("input[name='startTime']").val(formatdate(new Date(ad.startTime), "yyyy-MM-dd HH:mm"));
        else $("input[name='startTime']").val(formatdate(new Date(), "yyyy-MM-dd HH:mm"));
        if (ad.endTime) $("input[name='endTime']").val(formatdate(new Date(ad.endTime), "yyyy-MM-dd HH:mm"));
        else $("input[name='endTime']").val('');

        //每日预算
      
        
        (function(){
            

            $("input[name='budget']:eq(0)").prop("checked", true);
            $("input[name='budgetLimit']").val('');
            if (ad.budget == "limit") {
                $("input[name='budget']:eq(1)").trigger('click');
                if (ad.budgetLimit) {
                    $("input[name='budgetLimit']").val(ad.budgetLimit).prop('disabled',false);
                }
            } else {
                $("input[name='budget']:eq(0)").trigger('click');
            }
            $(".ui_radio_pre").removeClass('ui_radio_checked');
            $("input[name='priceType']").val("");
            $("#price_input_impression,#price_input_click,#price_input_download").val("").prop('disabled',true);
            var adPriceTypeIndex = {
                impression:0,
                click:1,
                download:2
            };
            if (ad.priceType&&ad.priceType != 'none'){
                $("input[name='priceType']").val(ad.priceType);
                $(".ui_radio_pre:eq("+adPriceTypeIndex[ad.priceType]+")").addClass('ui_radio_checked');
                $("#price_input_"+ad.priceType+",input[name='costPrice']").val(ad.costPrice).prop('disabled',false);
            }else{
                $("input[name='costPrice']").val('');
            }
        })();
        
      
        //投放地域
        that.region.initAreas(ad.areas);
        //网络状况
        var radioNet =$(".ui_radio_net");
        radioNet.removeClass('ui_radio_checked');
        $("input[name='networks']").val("");
        if(ad.networks){
            $("input[name='networks']").val(ad.networks);
                radioNet.each(function(i,e){
                    if(ad.networks.indexOf($(e).text())>-1)
                    $(e).addClass('ui_radio_checked');
                });
                if(radioNet.filter('.ui_radio_checked').length>2){
                    $('.ui_radio_net').first().addClass('ui_radio_checked');
                }
        }else{
            $('.ui_radio_net').first().trigger('click');

        }
        //渠道
        if (ad.channels) $("input[name='channels']").val(ad.channels);
        else $("input[name='channels']").val("");

        if (ad.fixedRank) $("#fixedRank").val(ad.fixedRank);
        else $("#fixedRank").val("");

},

/*
 * 初始化浮出层数据
 * ad2 AD2数据对象
 */
initMsgAd2:function (data) {
         var that = this;
        $('.msg_ad').unblock({onUnblock:function(){
            that.adSlotExistDialogId = '';
        }});
        $(".step1").hide();
        $(".step2").show();
       
        that.adjustMsg();
        
        $(".err").remove();
        $(".ui_radio_type").removeClass('ui_radio_checked');
        $(".ui_radio_form").removeClass('ui_radio_checked');
        // $(".tb_form").find(".ui_radio_type:eq(1)").addClass('ui_radio_checked');
        // $(".tb_form").find(".ui_radio_form:eq(0)").addClass('ui_radio_checked');
        $("input[name='appname']").val('');
        $("input[name='adWords']").val('');
        $("input[name='provider']").val('');
        $("input[name='weburl']").val('http://');
        // $("textarea[name='webdes']").val('');
        $("input[name='webname']").val('');
        $("input[name='icon']").val('');
        $("input[name='url']").val('');
        $("input[name='weblogo']").val('');
        $("input[name='bannerimg']").val('');
        $("textarea[name='description']").val('');
        $("input[type='file']").val('');
        //文字链
        $('input[name="textFont"]').val('');
        $('.ui_select_textFont span.text').text('请选择字体');
        $('input[name="textSize"]').val(that.textSizeAdSlot!==''?that.textSizeAdSlot:'');
        $('.ui_select_textSize span.text').text(that.textSizeAdSlot!==''?that.textSizeAdSlot:'请选择字体大小');
        $('input[name="textColor"]').val('#000000').css('background','#000000');
        
        $('#icon_show').attr('src','');
        $('#name_show').text('');
        $('#des_show').text('');
        $('#bannerimg_show').attr('src','');
        $(".ui_radio_form,.ui_radio_type").removeClass('ui_radio_checked ui_radio_disabled');
        $('#bannerOrBigImg').text('横幅图片');
        
        //类型过率
        if(data.adContent){
            // that.template = data.adContent.template || that.template;
            that.landingSize = data.adContent.landingSize || that.landingSize;
            that.platform = data.adContent.platform || that.platform;
            // that.entryType = data.adContent.entryType || that.entryType;
        }
        // console.log(that.landingSize);
        $('#adLandingSize').val(that.landingSize);
        $('.ui_select_landingSize span.text').text(that.landingSize).attr('title',that.landingSize);
        //that.changeAdPreviewSize();
       
         var landingTypeAdStyleList ={
             'banner':{adType:[true,true],adStyle:[true,true,false]},
             'custom':{adType:[true,true],adStyle:[true,false,false]},
             'bigimage':{adType:[true,true],adStyle:[false,true,false]},
             'embed':{adType:[true,true],adStyle:[true,false,false]},
             'wap':{adType:[true,true],adStyle:[true,true,false]},
             'text':{adType:[true,false],adStyle:[false,false,true]}
         };
         that.isTextLandingType = false;
         var adType = [true,true],adStyle=[true,true,true];
            // nowEntryType = $("input[name='record_adslot']:checked").eq(0).closest('tr').find('td.entryType').attr('entryType');
            adType = landingTypeAdStyleList[that.entryType].adType;
            adStyle = landingTypeAdStyleList[that.entryType].adStyle;
          
        
            $('.ui_radio_form').each(function(i,e){
             if(!adStyle[i]){
                $(e).addClass('ui_radio_disabled');
                }
            });
            $('.ui_radio_type').each(function(i,e){
                if(!adType[i]){
                    $(e).addClass('ui_radio_disabled');
                }
            });
        if(data.adContent){
                $("#adLandingType").val(data.adContent.landingType);
              (function(){
                  var contentType = {
                      web:0,
                      app:1
                  },
                  displayType={
                      standard:0,
                      image:1,
                      text:2
                  };
                  var radioType = $(".tb_form").find(".ui_radio_type").eq(contentType[data.adContent.contentType]);
                  var radioStyle = $(".tb_form").find(".ui_radio_form").eq(displayType[data.adContent.displayType]);
                  that.isTextLandingType = data.adContent.displayType=='text';
                  radioType.addClass('ui_radio_checked');
                  $("input[name='contentType']").val(data.adContent.contentType);
                   radioStyle.addClass('ui_radio_checked');
                  $("input[name='displayType']").val(data.adContent.displayType);
                 
              })();
              
             // }
             that.initStep2();
             

            //网址 同下面共用一个字段 为ios平台的时候一直是weburl 否则android的应用和网站不同 应用时apk的url 网站是网址的url
            if(data.adContent.url&&($("input[name='weburl']").parents('tr.type_switch').hasClass(that.ad_type)||that.platform == 'iOS'))
                $("input[name='weburl']").val(data.adContent.url);
            //apk文件
            if(that.platform == 'android'&&data.adContent.url&&$("input[name='url']").parents('tr.type_switch').hasClass(that.ad_type))
            {$("input[name='url']").val(data.adContent.url);}
            //网站logo 同下面共用一个字段
            if(data.adContent.icon&&$("input[name='weblogo']").parents('tr.type_switch').hasClass(that.ad_type))
                {
                    $("input[name='weblogo']").val(data.adContent.icon);
                    $('#icon_show').attr('src',data.adContent.icon);
                }
            //应用图标
            if(data.adContent.icon&&$("input[name='icon']").parents('tr.type_switch').hasClass(that.ad_type))
                {
                    $("input[name='icon']").val(data.adContent.icon);
                    $('#icon_show').attr('src',data.adContent.icon);
                }

            //横幅图片
            if(data.adContent.img )
                {
                    $("input[name='bannerimg']").val(data.adContent.img);
                    $('#bannerimg_show').attr('src',data.adContent.img);
                }
            //名称 同下面共用一个字段
            if(data.adContent.title ){
                //软件名称
                if($("input[name='appname']").parents('tr.type_switch').hasClass(that.ad_type)){
                    $("input[name='appname']").val(data.adContent.title);$('#name_show').text(data.adContent.title);
                //网站名称
                }else if($("input[name='webname']").parents('tr.type_switch').hasClass(that.ad_type)){
                    $("input[name='webname']").val(data.adContent.title);$('#name_show').text(data.adContent.title);
                }
            }
            //推广文字
            if(data.adContent.adWords) {
                $("input[name='adWords']").val(data.adContent.adWords);
                $('#des_show').text(data.adContent.adWords);
            }
            //开发商
            if(data.adContent.provider ) $("input[name='provider']").val(data.adContent.provider);

            //详细描述 同下面共用一个字段
            if(data.adContent.description &&$("textarea[name='description']").parents('tr.type_switch').hasClass(that.ad_type))
                {$("textarea[name='description']").val(data.adContent.description);}
            //网站介绍
            // if(data.adContent.description &&$("#webdes").parents('tr.type_switch').hasClass(that.ad_type))
            //     {$("#webdes").val(data.adContent.description);$('#des_show').text(data.adContent.description);}
             //文字链样式初始化
             if(data.adContent.textFont){
                 $('input[name="textFont"]').val(data.adContent.textFont);
                 $('.ui_select_textFont span.text').text(data.adContent.textFont);
             }
             if(data.adContent.textSize ){
                 $('input[name="textSize"]').val(data.adContent.textSize);
                 $('.ui_select_textSize span.text').text(data.adContent.textSize);
             }
             if(data.adContent.textColor){
                 $('input[name="textColor"]').val(data.adContent.textColor).css('background',data.adContent.textColor);
             }
        }else{//默认 初始化对象
            var contentTypeList = $('.ui_radio_type').not('.ui_radio_disabled');
            var firstEnabledContentType = contentTypeList.length==1?contentTypeList.eq(0):contentTypeList.eq(1);
            var firstEnabledDisplayType = $('.ui_radio_form').not('.ui_radio_disabled').eq(0);
            $("input[name='contentType']").val(firstEnabledContentType.attr('title'));
            $("input[name='displayType']").val(firstEnabledDisplayType.attr('style_type'));

            firstEnabledContentType.trigger('click');
            firstEnabledDisplayType.trigger('click');

        }
        if(that.isTextLandingType){
            that.textPreviewChange();
        }
         //that.changeFormStyle();
         changeUploadStyle();
},
/*
*step2 页面的一些初始化工作
**/
initStep2:function(){
     var that = this,
        adLandingTypeMap = {
             android:{
                  app:{download:'直接下载',detail:'详细界面',iosOrAndroidName:'网址:',inputOpen:false},
                  web:{browser:'浏览器',webview:'内嵌网页',iosOrAndroidName:'网址:',inputOpen:false}
                  //iosOrAndroidName:'网址:'
             },
             iOS:{
                  app:{gotoAppStore:'GotoAppStore',download:'GotoAppStore',detail:'GotoAppStore',iosOrAndroidName:'苹果应用链接:',inputOpen:true},
                  web:{gotoAppStore:'GotoAppStore',browser:'浏览器',webview:'内嵌网页',iosOrAndroidName:'网址:',inputOpen:false}
                  //iosOrAndroidName:'苹果应用链接:'
             }
         },
         contentType = $("input[name='contentType']").val(),
         landingType = $("input[name='adLandingType']").val(),
         displayType = $("input[name='displayType']").val(),
         // entryType = $("input[name='record_adslot']:checked").eq(0).closest('tr').find('td.entryType').attr('entryType'),
         // template = $("input[name='record_adslot']:checked").eq(0).closest('tr').find('td.platform').attr('template'),
         swithList = $('.msg_content_center tr.type_switch');
         //改变值
         $('span.iosOrAndroidName').text(adLandingTypeMap[that.platform][contentType]['iosOrAndroidName']);
         $("div.ui_select_landingType span.text").html(adLandingTypeMap[that.platform][contentType][landingType]);
         if(adLandingTypeMap[that.platform][contentType]['inputOpen']){
            $('#inputiTunes').removeClass('hidden');
         }else{
            $('#inputiTunes').addClass('hidden');
         }
         //显示推广文字或网站简介
         if(contentType==="app"){
            $('.adwords_toggle_title').text('推广文字：');
         }else{
            $('.adwords_toggle_title').text('网站简介：');
         }
        //元素切换
        that.ad_type =  $(".tb_form .ui_radio_type,.tb_form .ui_radio_form").map(function(){
           return $(this).hasClass('ui_radio_checked')?1:0 ;
        }).get().join('');
        swithList.hide().filter('.'+that.ad_type).show();
         if(that.platform == 'iOS'){
            swithList.filter('.android').hide().end().filter('.ios').show();
        }
        //landingSize 列表切换
     
        $('.ui_select_landingSize .pop_menu ul li').hide().filter(function(){
            return $(this).hasClass(that.platform)&&$(this).hasClass(displayType)&&$(this).hasClass(that.entryType);
        }).show();
        //landingType 列表切换
        $(".ui_select_landingType .pop_menu ul li").show().not('.'+that.platform).hide().end().not('.'+contentType).hide();
       
          //预览条幅的切换
         if($('#bannerimg').closest('tr.type_switch').hasClass(that.ad_type)){
             $('#icon_show,#name_show,#des_show').hide();
             $('#bannerimg_show').show();
         }else{
             $('#name_show').show();
             $('#bannerimg_show').hide();
             if(that.isTextLandingType){
                $('#icon_show,#des_show').hide();
                $('#name_show').addClass('textFont_show');
             }else{
                $('#icon_show,#des_show').show();
                $('#name_show').removeClass('textFont_show').removeAttr('style');//css({'font-family':'微软雅黑','font-size':'14px','color':'black'});
             }
         }
         
        that.adjustMsg();
        that.previewChange();
        changeUploadStyle();
},
/*
 * 更改推广类型的表单样式
 */
changeFormStyle:function (){
        var that = this,
       
        adLandingSizeMap = {
            android:{
                banner:{size:'202x55'},
                wap:{size:{standard:'202x55',image:'424x380'}},
                embed:{size:'202x55'},
                bigimage:{size:'800x250'},
                custom:{size:'202x55'},
                text:{size:'-'}
            },
            iOS:{
                banner:{size:'320x50'},
                wap:{size:{standard:'320x50',image:'480x320'}},
                embed:{size:'320x50'},
                bigimage:{size:'640x320'},
                custom:{size:'320x50'},
                text:{size:'-'}
            }
            // android:{

            //     standard:{size:'202x55'},
            //     image:{size:'202x55',wap:{applist:'202x55',horizon_bigimage:'480x320',vertial_bigimage:'640x340'}},
            //     text:{size:'-'}
            // },
            // iOS:{
            //     standard:{size:'320x50'},
            //     image:{size:'320x50',wap:{applist:'320x50',horizon_bigimage:'480x320',vertial_bigimage:'640x340'}},
            //     text:{size:'-'}
            // }
            
        } ,
         displayType = $("input[name='displayType']").val();
         // entryType = $("input[name='record_adslot']:checked").eq(0).closest('tr').find('td.entryType').attr('entryType'),
         // template = $("input[name='record_adslot']:checked").eq(0).closest('tr').find('td.platform').attr('template');
        if(that.entryType=='wap'){
            var nowSize = adLandingSizeMap[that.platform][that.entryType].size[displayType];
            $('.ui_select_landingSize span.text').text(nowSize).attr('title',nowSize);
            $('#adLandingSize').val(nowSize);
        }else{
            var nowSize =adLandingSizeMap[that.platform][that.entryType].size;
            $('.ui_select_landingSize span.text').text(nowSize).attr('title',nowSize);
            $('#adLandingSize').val(nowSize);
        }
        that.initStep2();
        that.adjustMsg();
        that.previewChange();
        changeUploadStyle();
    },
    //初始化已有广告浮出层
    initExistAdList:function(data){
        var result = data.adPage.result,elem='',odd = true;
        if(result.length > 0){
            for(var i = 0;i < result.length ;i++){
                elem += '<tr class=' + (odd == true ? "" : "transbg") + ' id="tr_' + result[i].id + '">';
                odd = !odd;
                elem += '<td width="140"  class="chk pos_rel" style="display:block"><input id="input_'+ result[i].id +'" type="checkbox" name="record_ch" value="' + result[i].id + '"/>';
                elem += '<a class="edit_btn" href="javascript:void(0)" adpage_id="'+ result[i].id +'" class="tb_adname"><label title="'+ result[i].name +'" for="input_' + result[i].id+ '">'+ result[i].name + '</label></a></td>';
                if (result[i].content_type == "app") 
                    elem += '<td width="100" class="tb_adtype">应用推广</td>';
                else
                    elem += '<td width="100" class="tb_adtype">网站推广</td>';
                elem += '<td width="130">' + (result[i].start_time!=undefined? formatdate(new Date(result[i].start_time), "yyyy-MM-dd HH:mm"):"-") + '</td>';
                elem += '<td width="130">' + (result[i].end_time!=undefined? formatdate(new Date(result[i].end_time), "yyyy-MM-dd HH:mm"):"-") + '</td>';
                elem += '<td width="" id="td_status_' + result[i].id + '" style="text-align:left; " class="status">';
                if (result[i].status=="normal")
                    elem += '<img style="vertical-align:text-bottom;margin-right:3px;" src="images/icon_state_normal.gif" width="16" height="16" alt="正常" />正常';
                else if(result[i].status=="limit")
                    elem += '<img style="vertical-align:text-bottom;margin-right:3px;" src="images/icon_state_top.gif" width="16" height="16" alt="到达预算" />到达预算';
                else if(result[i].status=="adorder_pause")
                    elem += '<img style="vertical-align:text-bottom;margin-right:3px;" src="images/icon_state_pause.gif" width="16" height="16" alt="订单暂停" />订单暂停';
                else if(result[i].status == 'adorder_finish')
                    elem += '<img style="vertical-align:text-bottom;margin-right:3px;" src="images/icon_state_complete.gif" width="16" height="16" alt="订单投放完成" />订单投放完成';
                else if(result[i].status == 'finish')
                    elem += '<img style="vertical-align:text-bottom;margin-right:3px;" src="images/icon_state_complete.gif" width="16" height="16" alt="投放完成" />投放完成';
                else if(result[i].status == 'adorder_ready')
                    elem += '<img style="vertical-align:text-bottom;margin-right:3px;" src="images/icon_state_abled.gif" width="16" height="16" alt="订单尚未投放" />订单尚未投放';
                else if(result[i].status == 'ready')
                    elem += '<img style="vertical-align:text-bottom;margin-right:3px;" src="images/icon_state_abled.gif" width="16" height="16" alt="尚未投放" />尚未投放';
                else if (result[i].status == 'pause')
                    elem += '<img style="vertical-align:text-bottom;margin-right:3px;" src="images/icon_state_pause.gif" width="16" height="16" alt="暂停" />暂停';
                else
                    elem += '<img style="vertical-align:text-bottom;margin-right:3px;" src="images/icon_state_disabled.gif" width="16" height="16" alt="未知" />未知';
                elem += '</td></tr>';
            }
         //$('#adListForUseContainer').fadeIn(200);
            $('#choose_ad_item').html(elem);
        } else{
            $('#choose_ad_item .loadingStatus').text('没有匹配广告位!');
        }    
    },
/* 
 * 显示浮出层
 * ad AD数据对象
 * callback 回调方法
 */
showMsg:function (ad1, callback,hasAdSlot,adOrderId,event) {
        var that = this;
        that.initMsgAd1(ad1,hasAdSlot,adOrderId,event);
        //弹出浮出层
        var msg = $('.msg_ad'),
        height = $(window).height(),
         width = $(document).width(),model1 = null,
         model2 = null;
        $.blockUI({
                css: {color: '#cccccc',border:'0',width:'818px','left' : width/2 - (msg.width() / 2),'top' : height/2 - (msg.height() / 2),background:'none',padding:'0px'},
                message: msg
                // onBlock:function(){
                //     that.adDialogId = getToken();
                //     console.log(that.adDialogId);
                // }
        });
        $("input[name='adname']").blur().focus();
        /* 事件处理部分 */
        $(".close").unbind("click").click(function() {
                $.unblockUI({

                    onUnblock:function(){
                        that.adDialogId = '';
                    }
                });
        });
        $('.btn_use_pastad').unbind('click').click(function(){
                model1 = that.getAD1();
                var currentDialogId = '';
                if (model1 != null) {
                    $.ajax({
                            type: "get",
                            url: "/ad/add2" , 
                            data:{platform:that.platform,landingSize:that.landingSize},
                            dataType: "json",
                            beforeSend: function(XMLHttpRequest){
                                var msg=$('#adListForUseContainer');
                                $('.msg_ad').block({
                                    message:msg,
                                    css:{
                                        border:0,
                                        background:'none',
                                        
                                        height:'430px',
                                        width:'680px',
                                       
                                    },
                                    onBlock:function(){
                                        that.adSlotExistDialogId = getToken();
                                        currentDialogId = that.adSlotExistDialogId;
                                    }
                                });
                            },
                            success: function(data, textStatus){
                                if( currentDialogId != that.adSlotExistDialogId) return;
                                that.initExistAdList(data);
                            },
                            complete: function(XMLHttpRequest, textStatus){

                            },
                            error: function(){
                                $('.loadingStatus').text('请求失败!');
                            }
                    });
                }
        });
        //保存并继续添加
        $(".btn_continue_ad").unbind("click").click(function() {
                 model1 = that.getAD1();
                 //console.log(that.template);
               var currentDialogId = that.adDialogId;
                if (model1 ) {
                    //model1.rnd = Math.random();
                        //if (model.areas == undefined)model.areas = "";
                        if(!that.stepTwoRequest){
                            $(".step1").hide();
                            $(".step2").show();
                             that.adjustMsg();
                            return ;
                         }
                        if ($("input[name='adid']").val() == "") {
                            that.initMsgAd2({});
                        } else {
                                //编辑
                                $.ajax({
                                        type: "get",
                                        url: "/ad/edit2/" + $("input[name='adid']").val(),
                                        data:{rnd:Math.random()},
                                        beforeSend: function(XMLHttpRequest){
                                        },
                                        success: function(data, textStatus){
                                            if( currentDialogId != that.adDialogId) return;
                                                that.initMsgAd2(data);
                                        },
                                        complete: function(XMLHttpRequest, textStatus){

                                        },
                                        error: function(){
                                            alert('请求失败');
                                        }
                                });
                        }
                         that.adjustMsg();
                }
        });
        //保存
        $(".btn_save_ad").unbind("click").click(function() {
                // that.entryType = $("input[name='record_adslot']:checked").eq(0).closest('tr').find('td.entryType').attr('entryType');
                model2 = that.getAD2();
                if(model2 ){
                    if ($("input[name='adid']").val() != "") {
                        model2.id = that.curId;
                    }
                    model2.rnd = Math.random();
                    var model = $.extend(model1,model2);
                    var url = "/ad/save";//?type=" + model.type + "&landingType=" + model.landingType + "&style=" + model.style + "&name=" + model.name + "&adWords=" + model.adWords + "&provider=" + model.provider + "&url=" + model.url + "&icon=" + model.icon + "&description=" + model.description + "&id=" + curId;
                    $.ajax({
                            type: "get",
                            url: url,
                            dataType: "json",
                            data:model,
                            beforeSend: function(XMLHttpRequest){
                            },
                            success: function(data, textStatus){
                                    if (data["status"] == "ok") {
                                        callback();
                                    }else{
                                        alert('保存失败!');
                                    }
                            },
                            complete: function(XMLHttpRequest, textStatus){
                            },
                            error: function(){
                                alert('保存失败!');
                            }
                    });
                }
        });
},
/* 
 *自适应浮动层高度
 */
adjustMsg:function() {
    $("body>.blockMsg").stop(true).animate({top:$(window).height()/2 - ($('.msg_ad').height() / 2) + "px"},300);
},
/*
*尺寸更改时候的预览
**/
changeAdPreviewSize:function(value){
    var that = this,
     standardSize = {
        android:{width:'202',height:'55'},
        iOS:{width:'320',height:'50'}
    };
    // nowEntryType = $("input[name='record_adslot']:checked").eq(0).closest('tr').find('td.entryType').attr('entryType');
    that.landingSize = value||that.landingSize;
    if(that.landingSize!='-'){
        var widthP= that.landingSize.split('x')[0],
        heightP= that.landingSize.split('x')[1];
        $('.preview').width(widthP/(1+(parseInt(widthP)>400)));
        $('.preview').height(heightP/(1+(parseInt(heightP)>200)));
        // if(nowEntryType == 'wap'){
            if(that.landingSize == '480x320'||that.landingSize=='640x320'||that.landingSize=='800x250'){
                $('#bannerOrBigImg').text('大图图片');
            }else{
                $('#bannerOrBigImg').text('横幅图片');
            }
        // }
        $('#name_show,#des_show').css('max-width',(parseInt(widthP)-60)+'px');
    }else{
        $('.preview').width(standardSize[that.platform].width);
        $('.preview').height(standardSize[that.platform].height);
        $('#name_show,#des_show').css('max-width',standardSize[that.platform].width+'px');
       
    }
    that.adjustMsg();
},
/*
 *入口类型为'文字链'的时候的样式实时预览
 **/
textPreviewChange:function(){
   var cssStyle = {
       'font-family':$('input[name="textFont"]').val(),
       'font-size':fontSizes[$('input[name="textSize"]').val()],
       'color':$('input[name="textColor"]').val()
   }
   $('#name_show').css(cssStyle);
},
previewChange : function(){
    $('.previewBannerImg,.previewLogo').each(function(){
        if($(this).closest('.type_switch').is(':visible')){
            if($(this).val()!=''){
                $('#'+$(this).attr('forshow')).attr('src',$(this).val()+'?'+Math.random());
            }else{
                $('#'+$(this).attr('forshow')).attr('src','/images/no_img.gif'+'?'+Math.random());
            }
        }
    });
    $('.previewName,.previewDes').filter(':visible').each(function(){
        $('#'+$(this).attr('forshow')).text($(this).val());
    });
    this.changeAdPreviewSize($('#adLandingSize').val());
    if(this.isTextLandingType){
        this.textPreviewChange();
    }
},
/*
 *编辑
 **/
editAD:function (id,event) {
    var that = this;
        that.curId = id;
        that.stepTwoRequest = true;
        that.adDialogId = getToken();
        $("input[name='adid']").val(id);
        var evenObj = $(event.target),
        currentDialogId = that.adDialogId;
        $.ajax({
                type: "get",
                url: "/ad/edit1/" + id,
                dataType: "json",
                data:"rnd="+Math.random(),
                beforeSend: function(XMLHttpRequest){
                },
                success: function(data, textStatus){
                        if(currentDialogId != that.adDialogId) return;
                        that.adSlotPage = data.adSlotPage.result;
                        that.adOrderPage = data.adOrderPage.result;
                        //alert(data.ad.areas);
                        //alert(data.adHolderList);
                        var arr_adSlotId = new Array()
                        for (i = 0; i < data.adHolderList.length; i++) {
                            arr_adSlotId.push(data.adHolderList[i].adSlotId);
                        }
                        // var ad1 = new that.AD1(data.ad.adOrderId, data.ad.name, data.ad.budget, data.ad.budgetLimit, data.ad.priceType, data.ad.costPrice, data.ad.areas, data.ad.networks, data.ad.channels, arr_adSlotId, data.ad.startTime, data.ad.endTime,data.ad.fixedRank);
                        data.ad.adSlotId = arr_adSlotId;
                        that.showMsg(data.ad, function() {
                                //that.updateItem(evenObj.parent().parent(), $("input[name='adname']").val(), $("input[name='contentType']").val(),$('#list_order option:selected').text());
                                $.unblockUI({onUnblock:function(){
                                    that.adDialogId = '';
                                    that.updateItem(evenObj);
                                }});
                        },-1,'',event);
                },
                complete: function(XMLHttpRequest, textStatus){

                },
                error: function(){

                }
        });
    },
/*
*从appstore 链接地址抓取数据
*/ 
    getDataFromiTunes : function(appUrl){
        $.getJSON(
            'http://itunes.apple.com/lookup?jsoncallback=?',
            {
            id:appUrl.replace(/.*id(\d{9})/g,'$1'),
             // rnd:Math.random()
            },
            function(data){
                alert(data);
            }
        );
        // $.ajax({
        //     type : 'get',
        //     url : 'http://itunes.apple.com/lookup',//'/service/grabAppStoreInfo' ,
        //     data:{
        //         id:appUrl.replace(/.*id(\d{9})/g,'$1'),
        //          // rnd:Math.random()
        //     },
        //     dataType:"json",
        //     success: function(data){
        //         alert(data);
        //     }
        // });
    },
/* 更新单独一项
 * obj 需要刷新的行
 */
    updateItem:function (evenObj, name, type,adorder) {
        var obj = evenObj.closest('.tb_inner_wrap');
        obj.append("<div class='loading_row'><div class='txt'>正在刷新数据&nbsp;&nbsp;&nbsp;<img src='images/loading_m.gif' /></div></div>");
         if(location.pathname == '/ad'){
            loadList($('#pager span').text());
        }else{
            var id = $('tr.expend_panel').prev().attr('id').replace('tr_','');
                adList.param.listUrl = adList.param.baseUrl+ id;
                adList.loadList(adList.param.data.pageNo,$('tr.expend_panel').prev(),true);
        }
    //     setTimeout(function() {
    //         $(".loading_row").remove();
    //         obj.find(".tb_adname").text(name);
    //         obj.find(".tb_adorder").text(adorder);
    //         if (type == "web") {obj.find(".tb_adtype").text("网站推广");}
    //         else {obj.find(".tb_adtype").html("应用推广");}
    // //                    if($('.tb_adorder').size()>0){$('.tb_adorder').text(adorder);}
    //     }, 1000);
    }
}
