window.AdList = function(param){
    this.param={resultBody:'adPage',type:'get',listUrl:'adorder/showAd/',baseUrl:'adorder/showAd/',data:{},listTmpl:'#adList',listContainer:'tr.expend_panel div.tb_inner_wrap',special:'none'};
    $.extend(this.param,param);

}
window.AdList.prototype = {
    initUI : function(){
        var that = this;
        $('div.part_table').delegate('a.extend','click',function(){
            $(".extend").text("展开广告").removeClass('icon_expend').addClass('icon_expend_rev');
            if ($(this).parent().parent().next().attr("class") != "expend_panel") {
                
                    $(this).text("收起广告").removeClass('icon_expend_rev').addClass('icon_expend');
                    that.param.listUrl = that.param.baseUrl+ $(this).closest('tr').attr('id').replace('tr_','');
                    that.loadList(1,$(this).closest('tr'));
            } else { 
                    $(this).text("展开广告").removeClass('icon_expend').addClass('icon_expend_rev');
                    $(this).parent().parent().next().remove();
            }
        });
        $('div.part_table').delegate('span.lvl','click',function(){
            var adId = $(this).find('a').attr('adid');
            that.showPrior('td_level_'+adId,adId);
        });
    },
    tmplList : function(data){
        var that = this;
        var totalPages = data.totalPages;
        var pageNo = data.pageNo;
         if(that.param.listTmpl.length>0){
              var str = '<table class="tb_inner"><tbody><tr>'+
			'<th width="10"><input type="checkbox"  onclick="checkAll(this,&quot;adStatusChange&quot;)"></th>'+
			'<th width="160">广告名</th>';
            if(that.param.special=='adslot'){
                str += '<th>订单</th>';
            }

			str += '<th width="110">类型</th>'+
                       // '<th>打开方式</th>'+
			'<th width="110">优先级</th>'+
			'<th width="130">状态</th>'+
			'<th class="last" width="120">操作</th></tr>'+
                        '<tr><td colspan="9" class="opr">'+
                        '<span class="btn_inner_table_new fl btn_add_ad"></span>'+
                        '<div class=""><div id="ad_pager" class="fr pager"></div><div class="fl f_csv"></div></div>'+
                        //'<span class="btn_inner_table_has fl" style="margin-left: 10px;"><a href="#">添加已有广告</a></span>'+
                        //'<span class="pager" style="display:;">第<select><option>1</option><option>2</option></select>页'+
                        //'<span class="expend_all"><a href="#">展开所有页面</a></span></span>'+
			'</td></tr></tbody></table>';
              $(that.param.listTmpl).tmpl(data.result,{
                 checked:function(pr){
                     return (this.data.prior == pr?'checked':'');
                     
                 },
                 getId:function(){
                    if(this.data.id){
                        return this.data.id;
                    }else{
                        return this.data.ad_id;
                    }
                 },
                 getAdorders:function(){
                    if(that.param.special=='adslot'){
                        return '<td class="tb_adorder">'+ (this.data.ad_order_name?this.data.ad_order_name:'-')+'</td>';
                    }else{
                        return '';
                    }
                 },
                 getType:function(){
                     return (this.data.content_type == 'app'?'应用推广':'网站推广');
                 },
                 getStatus:function(){
                     var str ='';
                     if(that.param.baseUrl=='adorder/showAd/'){
                         if(this.data.status == 'normal'){
                             // if(this.data.system_status!='limit'){
                                str='<img src="images/icon_state_normal.gif" width="16" height="16" alt="正常" />正常';
                             // }
                             // else{
                             //    str ='<img src="images/icon_state_top.gif" width="16" height="16" alt="到达预算" />到达预算';
                             // }
                         }else if(this.data.status == 'pause'){
                            str = '<img src="images/icon_state_pause.gif" width="16" height="16" alt="暂停" />暂停';
                         }else if(this.data.status == 'limit'){
                            str = '<img src="images/icon_state_top.gif" width="16" height="16" alt="到达预算" />到达预算';
                         }else if(this.data.status == 'adorder_pause'){
                            str = '<img src="images/icon_state_pause.gif" width="16" height="16" alt="订单暂停" />订单暂停'; 
                         }else if(this.data.status == 'adorder_finish'){
                            str = '<img src="images/icon_state_complete.gif" width="16" height="16" alt="订单投放完成" />订单投放完成';
                         }else if(this.data.status == 'finish'){
                            str = '<img src="images/icon_state_complete.gif" width="16" height="16" alt="投放完成" />投放完成';
                         }else if(this.data.status == 'adorder_ready'){
                            str = '<img src="images/icon_state_abled.gif" width="16" height="16" alt="订单尚未投放" />订单尚未投放';
                         }else if(this.data.status == 'ready'){
                            str = '<img src="images/icon_state_abled.gif" width="16" height="16" alt="尚未投放" />尚未投放';
                         }else {
                            str = '<img src="images/icon_state_disabled.gif" width="16" height="16" alt="未知状态" />未知';
                         }
                     }else{
                         if(this.data.ad_status=='normal' && this.data.adslot_status=='normal' ){
                            str = '<img src="images/icon_state_normal.gif" width="16" height="16" alt="正常" />正常';
                          }else if(this.data.ad_status=='limit' && this.data.adslot_status=='normal' ){
                            str = '<img src="images/icon_state_top.gif" width="16" height="16" alt="到达预算" />到达预算';
                          }else if(this.data.ad_status=='normal' && this.data.adslot_status=='pause'){
                            str = '<img src="images/icon_state_pause.gif" width="16" height="16" alt="广告位暂停" />广告位暂停';
                          }else if(this.data.ad_status=='pause'){
                            str = '<img src="images/icon_state_pause.gif" width="16" height="16" alt="广告暂停" />暂停';
                          }else if(this.data.ad_status=="adorder_pause"){
                            str = '<img src="images/icon_state_pause.gif" width="16" height="16" alt="订单暂停" />订单暂停';
                          }else if(this.data.adslot_status=='app_pause'){
                            str = '<img src="images/icon_state_pause.gif" width="16" height="16" alt="应用暂停" />应用暂停';
                          }else if(this.data.ad_status=="adorder_finish"){
                            str = '<img src="images/icon_state_complete.gif" width="16" height="16" alt="订单投放完成" />订单投放完成';
                          }else if(this.data.ad_status=="finish"){
                            str = '<img src="images/icon_state_complete.gif" width="16" height="16" alt="投放完成" />投放完成';
                          }else if(this.data.ad_status=="adorder_ready"){
                            str = '<img src="images/icon_state_abled.gif" width="16" height="16" alt="订单尚未投放" />订单尚未投放';
                          }else if(this.data.ad_status=="ready"){
                            str = '<img src="images/icon_state_abled.gif" width="16" height="16" alt="尚未投放" />尚未投放';
                          }else{
                            str = '<img src="images/icon_state_disabled.gif" width="16" height="16" alt="未知状态" />未知';
                          }
                     }
                    
                     return str;
                 }
              }).insertAfter($(that.param.listContainer).html(str).find('table tr').first());
                pageAnchorsGenerate(totalPages,pageNo,'#ad_pager',function(page){
                    that.loadList(page,$('tr.expend_panel').prev(),true);
                });
        }
    },
    
    loadList :function(pageNo,parent,reload){
        var that = this;
        var nowExpanel = $(".expend_panel");
        that.param.data.rnd = Math.random();
        that.param.data.pageNo = pageNo;

            $.ajax({
                type: that.param.type,
                url: that.param.listUrl,
                data:that.param.data ,
                beforeSend : function(){
                    if(!reload){
                        nowExpanel.remove();
                        var str = '<tr class="expend_panel"><td class="tb_inner" colspan="8" ><div class="tb_inner_wrap">加载中！</div></td></tr>';
                        parent.after(str);
                    }
                    // $(that.param.listContainer).text("");
                },
                success: function(data, textStatus){
                    loginValidate(data);
                    if(data[that.param.resultBody]){
                        that.tmplList(data[that.param.resultBody]);
                    }
                },
                complete: function(XMLHttpRequest, textStatus){
                },
                error: function(){
                     $(that.param.listContainer).text("加载失败！");
                    
                }
        });
    },
   showPrior: function (input,adId) {
        var that = this;
        $(".level .pnl").hide();
        var nowPanel = $("#" + input + ".level .pnl");
        if(nowPanel.next('span').hasClass('i1')){
            nowPanel.find('li').eq(0).find('input:radio').trigger('click');
        }else if(nowPanel.next('span').hasClass('i2')){
            nowPanel.find('li').eq(1).find('input:radio').trigger('click');
        }else{
            nowPanel.find('li').eq(2).find('input:radio').trigger('click');
        }
        nowPanel.show();
    	nowPanel.find(".btn_cancel").unbind('click').click(function() {
    		$(".level .pnl").hide();
    	});
        nowPanel.find(".btn_save").unbind('click').click(function() {
    		that.updatePrior('',adId,'/ad/prior?prior='+$("#prior_input_"+adId).val()+'&id='+adId);
        });
    },

    updatePrior:function (ad_slot_id, ad_id, url){
            //url = "/"+target+"/changes?";
            //url = "/changes?";
            $.ajax({
            type: "GET",
            url: url,
            data: "",
            success: function(data){
                    loginValidate(data);
                    $("#td_level_" + ad_id + ".level .pnl").hide();
                    $(".prior_img_" + ad_id).removeClass('i1 i2 i3');
                    $(".prior_img_" + ad_id).addClass('i' + $('#prior_input_' + ad_id).val());
            }
         });
    }
    
  
}