window.Region=function(data){
    this.data={type:''}
    $.extend(this.data,data);
    this.regionRadio =$('.ui_radio_region'+this.data.type);
    this.special = $('div.'+ this.data.type +'_special');
    this.table = this.special.find('table.country_list');
    this.areas = $('input[name="'+this.data.type+'areas"]');
}
window.Region.prototype={
    initUI: function(){
        //地域设置 
        var that =this;
         that.table.find("tr.tb_title").unbind('click').click(function(){
            $(this).nextUntil("tr.tb_title").find("input").prop("checked",$(this).find("input").prop("checked"));

        });
        that.table.find("tr.area_list span.sub_title").unbind('click').click(function(){
            $(this).nextAll("input").prop("checked",$(this).find("input").prop("checked"));

        });
        that.regionRadio.click(function(){
                that.regionRadio.removeClass('ui_radio_checked');
                $(this).addClass('ui_radio_checked');
                if ($(this).attr("data") == 2) {
                       that.initAreas(that.special.prev('.comment').text(),true);
                       that.areas.val(that.special.prev('.comment').text());
                       that.special.show(200).prev('.comment').show();
                       
                } else {
                       that.special.hide(200).prev('.comment').hide();
                       that.areas.val('all');
                       that.special.prev('.comment').text('all');
                }
        });
        that.table.nextAll('.ui_radio_region_btn').click(function(){
             that.updateAreas($(this));
             that.special.hide(200);
        });
//        that.table.nextAll('.ui_radio_region_cancel').click(function(){
//             that.updateAreas();
//             that.special.hide(200);
//        });
    },
    initAreas:function(areas,forceInit){
        var that = this;
        that.regionRadio.removeClass('ui_radio_checked');
        that.special.hide().prev('.comment').hide();
        that.table.find('input').prop('checked',true);
        if(areas != undefined){
            that.areas.val(areas);
            if (areas == "all"&&!forceInit) {
                that.regionRadio.eq(0).addClass('ui_radio_checked');
                that.special.hide();
            }else if(areas == "all"||areas == "") {
                //初始化为all或空时选中所有checkbox
                that.regionRadio.eq(1).addClass('ui_radio_checked');
                return;
            }else {
                $('.tb_title input,.sub_title input').prop('checked',false);
                that.special.prev('.comment').show();
                that.regionRadio.eq(1).addClass('ui_radio_checked').next('.comment').text(areas).attr('title',areas);
                 that.table.find('tr.area_list td>label').each(function(i,e){
                    if (areas.indexOf($(e).text())<0){
                        $(e).next('input').prop('checked',false);
                    }
                });
            }
        }else{
//            that.regionRadio.eq(0).addClass('ui_radio_checked');
            that.regionRadio.first().trigger('click');
        }
    },
    updateAreas:function (target){
        var that =this;
        if(target.hasClass('ui_radio_region_cancel')&&(that.areas.val()==''||that.areas.val()=='all')){
            that.regionRadio.eq(0).trigger('click');
            return;
        }else{
           var areas= that.table.find('tr.area_list td>input').map(function(i,e){
                if ($(e).prop('checked')){
                    return $(e).prev('label').text();
                }
            }).get().join(",");
            //为全选状态或者都不选时 默认为所有网络
            if(areas.split(',').length == 37||areas.length==0){
                that.regionRadio.eq(0).trigger('click');
                return;
            }else{
                that.areas.val(areas);
                that.special.prev('.comment').text(areas);
            }
        }
    }
}