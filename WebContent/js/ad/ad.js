 $(function() {
    loadList(1);
    var addAd = new window.AddAd({addButtonLisner:'.panel_btn_top'});
    addAd.initUI();
    addAd.region.initUI();
    $(':checkbox').prop('checked',false);
});
/*  
 * 加载列表
 * page 页码	
 */
function loadList(page, status) {
    $.ajax({
        type: "get",
        url:"ad/list?pageNo=" + page + (status ? status : ""),
        dataType: "json",
        data:"rnd="+Math.random(),
        beforeSend: function(XMLHttpRequest){
        },
        success: function(data, textStatus){
            var list = data;
            var adPage = list.adPage.result;
            var totalPages = list.adPage.totalPages;
            var pageNo = list.adPage.pageNo;
            var len = adPage.length;
            var odd = true;
            //生成分页
            pageAnchorsGenerate(totalPages,pageNo,'',loadList);
            if(!list.adPage || list.adPage.result.length == 0){
                $('#tb_list tr').not(':first').remove();
                $('#tb_list').append('<tr id="loading_row_init"><td colspan="4">没有记录!</td></tr>');
                return;
            }
            clearList();
            for (i = 0; i < len; i++) {
                var elem = "";

                elem += '<tr class=' + (odd == true ? "" : "transbg") + ' id="tr_' + adPage[i].id + '">';
                odd = !odd;
                elem += '<td class="chk pos_rel" style="display:block"><input type="checkbox" name="record_ch" value="' + adPage[i].id + '"/></td>';
                elem += '<td class="edit_btn" title="'+adPage[i].name+'"><a href="javascript:void(0)" adpage_id="'+ adPage[i].id +'" class="tb_adname">' + adPage[i].name + '</a></td>';
                
                elem += '<td class="tb_ad_slot_name" title="'+(adPage[i].ad_slot_name?adPage[i].ad_slot_name:'-')+'">'+(adPage[i].ad_slot_name?adPage[i].ad_slot_name:'-')+'</td>';
                
                elem += '<td>' + (adPage[i].start_time? formatdate(new Date(adPage[i].start_time), "yyyy-MM-dd HH:mm"):"-") + '</td>';
                elem += '<td>' + (adPage[i].end_time? formatdate(new Date(adPage[i].end_time), "yyyy-MM-dd HH:mm"):"-") + '</td>';
                elem += '<td style="position:relative;display:block;" id="td_level_' + adPage[i].id + '" style="text-align:left; padding:0 30px;" class="level">';
                elem += '<div class="pnl"><div style="text-align:center;">';
                elem += '<span class="lvl i' + adPage[i].prior + ' prior_img_' + adPage[i].id + '"></span></div>';
                elem += '<ul style="padding:10px;">';

                elem += '<li><input type="radio" name="prior_' + adPage[i].id + '" id="prior1_'+ adPage[i].id + '"';
                //alert(adPage[i].prior);
                if (adPage[i].prior == 1) elem += 'checked="checked" ';
                elem += 'onclick="$(&quot;#prior_input_' + adPage[i].id + '&quot;).val(1);$(this).closest(&quot;.pnl&quot;).find(&quot;.lvl&quot;).removeClass(&quot;i1 i2 i3&quot;).addClass(&quot;i1&quot;)"/>'+
                    '<label for="prior1_'+ adPage[i].id +'">&nbsp;低级</label></li>';

                elem += '<li><input type="radio" name="prior_' + adPage[i].id + '" id="prior2_'+ adPage[i].id +  '"';
                if (adPage[i].prior == 2) elem += 'checked="checked" ';
                elem += 'onclick="$(&quot;#prior_input_' + adPage[i].id + '&quot;).val(2);$(this).closest(&quot;.pnl&quot;).find(&quot;.lvl&quot;).removeClass(&quot;i1 i2 i3&quot;).addClass(&quot;i2&quot;)"/>'+
                    '<label for="prior2_'+ adPage[i].id +'">&nbsp;中级</label></li>';


                elem += '<li><input type="radio" name="prior_' + adPage[i].id + '" id="prior3_'+ adPage[i].id +  '"';
                if (adPage[i].prior == 3) elem += 'checked="checked" ';
                elem += 'onclick="$(&quot;#prior_input_' + adPage[i].id + '&quot;).val(3);$(this).closest(&quot;.pnl&quot;).find(&quot;.lvl&quot;).removeClass(&quot;i1 i2 i3&quot;).addClass(&quot;i3&quot;)"/>'+
                    '<label for="prior3_'+ adPage[i].id +'">&nbsp;高级</label></li>';

                elem += '</ul>';
                elem += '<input type="hidden" id="prior_input_' + adPage[i].id + '" value=""/>';
                elem += '<div class="opr">';
                elem += '<span class="btn_save"><a href="javascript:void(0)" onclick="';

                elem += "updatePrior(" + adPage[i].id + ", '/ad/prior?prior=' + $('#prior_input_" + adPage[i].id + "').val() + '&id=" + adPage[i].id + "');return false;";
                //                                        console.log(adPage[i].prior);
                elem += '">保存</a></span>';
                elem += '<span class="btn_cancel"><a href="javascript:void(0)">取消</a></span>';
                elem += '</div></div>';
                elem += '<span class="lvl i' + adPage[i].prior + ' prior_img_' + adPage[i].id + '"><a href="javascript:void(0)" onclick="showPrior(&quot;td_level_' + adPage[i].id + '&quot;)" ></a></span>';
                elem += '</td>';
                elem += '<td id="td_status_' + adPage[i].id + '" style="text-align:left;" class="status">';
                if (adPage[i].status=="normal")
                    elem += '<img src="images/icon_state_normal.gif" width="16" height="16" alt="正常" />正常';
                else if(adPage[i].status=="limit")
                    elem += '<img src="images/icon_state_top.gif" width="16" height="16" alt="到达预算" />到达预算';
                else if(adPage[i].status=="adorder_pause")
                    elem += '<img src="images/icon_state_pause.gif" width="16" height="16" alt="订单暂停" />订单暂停';
                else if(adPage[i].status == 'adorder_finish')
                    elem += '<img src="images/icon_state_complete.gif" width="16" height="16" alt="订单投放完成" />订单投放完成';
                else if(adPage[i].status == 'finish')
                    elem += '<img src="images/icon_state_complete.gif" width="16" height="16" alt="投放完成" />投放完成';
                else if(adPage[i].status == 'adorder_ready')
                    elem += '<img src="images/icon_state_abled.gif" width="16" height="16" alt="订单尚未投放" />订单尚未投放';
                else if(adPage[i].status == 'ready')
                    elem += '<img src="images/icon_state_abled.gif" width="16" height="16" alt="尚未投放" />尚未投放';
                else if (adPage[i].status == 'pause')
                    elem += '<img src="images/icon_state_pause.gif" width="16" height="16" alt="暂停" />暂停';
                else
                    elem += '<img src="images/icon_state_disabled.gif" width="16" height="16" alt="未知" />未知';
                elem += '</td></td>';
                elem += '<td class="edit_btn"><a href="javascript:void(0)" adpage_id="' + adPage[i].id + '" class="icon_modify">编辑</a></td>';
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

/* 清除列表项 */
function clearList() {
    $("#tb_list tr").not('.tit').remove();
}

function showPrior(input) {
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
}

function updatePrior(id, url){
    //url = "/"+target+"/changes?";
    //url = "/changes?";
    $.ajax({
        type: "GET",
        url: url,
        data:"rnd="+Math.random(),
        success: function(msg){
            $("#td_level_" + id + ".level .pnl").hide();
            $(".prior_img_" + id).removeClass('i1 i2 i3');
            $(".prior_img_" + id).addClass('i' + $('#prior_input_' + id).val());
        }
    });
}	
