jQuery(function(){
    $('body').delegate('.pnl_pro a.accBtn','click',function(){
        timeFunction.target = $(this).closest('.pnl_pro');
        timeFunction['select'+$(this).attr('selType')]();
    });
    $('body').delegate('.pnl_pro .weekday input','click',function(){
        timeFunction.target = $(this).closest('.pnl_pro');
        timeFunction.selectAllRow($(this).attr('selIndex'),$(this));
    });
    $('body').delegate('.pnl_pro .time_arrow span','click',function(){
        timeFunction.target = $(this).closest('.pnl_pro');
        timeFunction.selectAllCol($(this).index(),$(this));
    });
    $('body').delegate('.pnl_pro .time_picker span','click',function(){
        timeFunction.target = $(this).closest('.pnl_pro');
        var pos = timeFunction.getPos($(this));
        (pos[1] == "0px"||pos[1] == "0%"||pos[1] == "0")?timeFunction.item_unsel($(this)):timeFunction.item_sel($(this));
    });
});
var timeFunction = {
    //兼容ie8
    target : $(),
   
    getPos:function (item){
         if($.browser.msie){
            var pos =[];
            pos.push(item.css("background-position-x"));
            pos.push(item.css("background-position-y"));
        }else{
            var pos = item.css("background-position").split(" ");
        }
        return pos;
    },
    //选择所有列
    selectAllCol:function (i) {
       var item_clik = "span.a" + i;
       var item_change = "span.t" + i;
       var pos = this.getPos($(item_clik));
        if (pos[1] == "0px"||pos[1] == "0%"||pos[1] == "0") {

            this.item_unsel(item_clik);
            this.item_unsel(item_change);
        }
        else {
            this.item_sel(item_clik);
            this.item_sel(item_change);
        }

    },

    //选择所有行
    selectAllRow:function (i, obj) {
        var item_change = ".w" + i + " span";
        if (obj.prop('checked')) {
            this.item_sel(item_change);
        } else {
            this.item_unsel(item_change);
        }
    },

    //选择所有
    selectAll:function () {
        this.item_sel(".time_picker span");
        $(".weekday input[type='checkbox']",this.target).prop("checked", true);
    },

    //选择工作日
    selectWorkday:function () {
        this.item_sel(".w1 span, .w2 span, .w3 span, .w4 span, .w5 span");
        $(".w1 input[type='checkbox'], .w2 input[type='checkbox'], .w3 input[type='checkbox'], .w4 input[type='checkbox'], .w5 input[type='checkbox']",this.target).prop("checked", true);

        this.item_unsel(".w6 span, .w7 span");
        $(".w6 input[type='checkbox'], .w7 input[type='checkbox']",this.target).prop("checked", false);
    },

    //选择周末
    selectWeekend:function () {
        this.item_unsel(".w1 span, .w2 span, .w3 span, .w4 span, .w5 span");
        $(".w1 input[type='checkbox'], .w2 input[type='checkbox'], .w3 input[type='checkbox'], .w4 input[type='checkbox'], .w5 input[type='checkbox']",this.target).prop("checked", false);

        this.item_sel(".w6 span, .w7 span");
        $(".w6 input[type='checkbox'], .w7 input[type='checkbox']",this.target).prop("checked", true);
    },

    item_sel:function (selector) {
        var _self = this;
        _self.target.find(selector).each(function(i,e){
            
            var pos = _self.getPos($(e));
            $(e).css("background-position", (pos[0] + " 0px"));
        });
       
    },

    item_unsel:function (selector) {
        var _self = this;
        _self.target.find(selector).each(function(i,e){
            
            var pos = _self.getPos($(e));
            $(e).css("background-position", (pos[0] + " 11px"));
        });
       

    }

    
};
(function($){
    $.fn.timeslot = function(param){
        var options = {
            value:(new Array(169)).join(1)
        };
        options = $.extend(true,{},options,param);
        var _self = this
        return this.each(function(){
            var timeSlotStr = '';
            timeSlotStr += '<span class="pnl_pro">';
                timeSlotStr += '<a class="accBtn" selType="All" >全部时间</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                timeSlotStr += '<a class="accBtn" selType="Workday" >工作日</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                timeSlotStr += '<a class="accBtn" selType="Weekend" >周末</a>';
                timeSlotStr += '<span style="padding-left:200px; background:url(images/timer_picker_tip.jpg) no-repeat; margin-left:140px; "></span>';
               timeSlotStr += ' <div class="show_time">';
                    timeSlotStr += '<div class="weekday"></div>';
                    timeSlotStr += '<div class="time_arrow">';
                        timeSlotStr += '<span class="a0">1</span><span class="a1">1</span><span class="a2">2</span><span class="a3"></span><span class="a4"></span><span class="a5"></span>';
                        timeSlotStr += '<span class="a6"></span><span class="a7"></span><span class="a8"></span><span class="a9"></span><span class="a10""></span><span class="a11""></span>';
                        timeSlotStr += '<span class="a12"></span><span class="a13"></span><span class="a14"></span><span class="a15"></span><span class="a16"></span><span class="a17"></span>';
                        timeSlotStr += '<span class="a18"></span><span class="a19"></span><span class="a20"></span><span class="a21"></span><span class="a22"></span><span class="a23"></span>';
                    timeSlotStr += '</div>';
                timeSlotStr += '</div>';
                timeSlotStr += '<div class="show_time w1">';
                    timeSlotStr += '<div class="weekday"><input type="checkbox" checked="true" selIndex="1"/>&nbsp;星期一</div>';
                    timeSlotStr += '<div class="time_picker">';
                        timeSlotStr += '<span class="t0">0</span><span class="t1">1</span><span class="t2">2</span><span class="t3">3</span><span class="t4">4</span><span class="t5">5</span>';
                        timeSlotStr += '<span class="t6">6</span><span class="t7">7</span><span class="t8">8</span><span class="t9">9</span><span class="t10">10</span><span class="t11">11</span>';
                        timeSlotStr += '<span class="t12">12</span><span class="t13">13</span><span class="t14">14</span><span class="t15">15</span><span class="t16">16</span><span class="t17">17</span>';
                        timeSlotStr += '<span class="t18">18</span><span class="t19">19</span><span class="t20">20</span><span class="t21">21</span><span class="t22">22</span><span class="t23">23</span>';
                    timeSlotStr += '</div>';
                timeSlotStr += '</div>';
                timeSlotStr += '<div class="show_time w2">';
                    timeSlotStr += '<div class="weekday"><input type="checkbox" checked="true" selIndex="2"/>&nbsp;星期二</div>';
                    timeSlotStr += '<div class="time_picker">';
                        timeSlotStr += '<span class="t0">0</span><span class="t1">1</span><span class="t2">2</span><span class="t3">3</span><span class="t4">4</span><span class="t5">5</span>';
                        timeSlotStr += '<span class="t6">6</span><span class="t7">7</span><span class="t8">8</span><span class="t9">9</span><span class="t10">10</span><span class="t11">11</span>';
                        timeSlotStr += '<span class="t12">12</span><span class="t13">13</span><span class="t14">14</span><span class="t15">15</span><span class="t16">16</span><span class="t17">17</span>';
                        timeSlotStr += '<span class="t18">18</span><span class="t19">19</span><span class="t20">20</span><span class="t21">21</span><span class="t22">22</span><span class="t23">23</span>';
                    timeSlotStr += '</div>';
                timeSlotStr += '</div>';
                timeSlotStr += '<div class="show_time w3">';
                    timeSlotStr += '<div class="weekday"><input type="checkbox" checked="true" selIndex="3"/>&nbsp;星期三</div>';
                    timeSlotStr += '<div class="time_picker">';
                        timeSlotStr += '<span class="t0">0</span><span class="t1">1</span><span class="t2">2</span><span class="t3">3</span><span class="t4">4</span><span class="t5">5</span>';
                        timeSlotStr += '<span class="t6">6</span><span class="t7">7</span><span class="t8">8</span><span class="t9">9</span><span class="t10">10</span><span class="t11">11</span>';
                        timeSlotStr += '<span class="t12">12</span><span class="t13">13</span><span class="t14">14</span><span class="t15">15</span><span class="t16">16</span><span class="t17">17</span>';
                        timeSlotStr += '<span class="t18">18</span><span class="t19">19</span><span class="t20">20</span><span class="t21">21</span><span class="t22">22</span><span class="t23">23</span>';
                    timeSlotStr += '</div>';
                timeSlotStr += '</div>';
                timeSlotStr += '<div class="show_time w4">';
                    timeSlotStr += '<div class="weekday"><input type="checkbox" checked="true" selIndex="4"/>&nbsp;星期四</div>';
                    timeSlotStr += '<div class="time_picker">';
                        timeSlotStr += '<span class="t0">0</span><span class="t1">1</span><span class="t2">2</span><span class="t3">3</span><span class="t4">4</span><span class="t5">5</span>';
                        timeSlotStr += '<span class="t6">6</span><span class="t7">7</span><span class="t8">8</span><span class="t9">9</span><span class="t10">10</span><span class="t11">11</span>';
                        timeSlotStr += '<span class="t12">12</span><span class="t13">13</span><span class="t14">14</span><span class="t15">15</span><span class="t16">16</span><span class="t17">17</span>';
                        timeSlotStr += '<span class="t18">18</span><span class="t19">19</span><span class="t20">20</span><span class="t21">21</span><span class="t22">22</span><span class="t23">23</span>';
                    timeSlotStr += '</div>';
                timeSlotStr += '</div>';
                timeSlotStr += '<div class="show_time w5">';
                    timeSlotStr += '<div class="weekday"><input type="checkbox" checked="true" selIndex="5"/>&nbsp;星期五</div>';
                    timeSlotStr += '<div class="time_picker">';
                        timeSlotStr += '<span class="t0">0</span><span class="t1">1</span><span class="t2">2</span><span class="t3">3</span><span class="t4">4</span><span class="t5">5</span>';
                        timeSlotStr += '<span class="t6">6</span><span class="t7">7</span><span class="t8">8</span><span class="t9">9</span><span class="t10">10</span><span class="t11">11</span>';
                        timeSlotStr += '<span class="t12">12</span><span class="t13">13</span><span class="t14">14</span><span class="t15">15</span><span class="t16">16</span><span class="t17">17</span>';
                        timeSlotStr += '<span class="t18">18</span><span class="t19">19</span><span class="t20">20</span><span class="t21">21</span><span class="t22">22</span><span class="t23">23</span>';
                    timeSlotStr += '</div>';
                timeSlotStr += '</div>';
                timeSlotStr += '<div class="show_time w6">';
                    timeSlotStr += '<div class="weekday"><input type="checkbox" checked="true" selIndex="6"/>&nbsp;星期六</div>';
                    timeSlotStr += '<div class="time_picker">';
                        timeSlotStr += '<span class="t0">0</span><span class="t1">1</span><span class="t2">2</span><span class="t3">3</span><span class="t4">4</span><span class="t5">5</span>';
                        timeSlotStr += '<span class="t6">6</span><span class="t7">7</span><span class="t8">8</span><span class="t9">9</span><span class="t10">10</span><span class="t11">11</span>';
                        timeSlotStr += '<span class="t12">12</span><span class="t13">13</span><span class="t14">14</span><span class="t15">15</span><span class="t16">16</span><span class="t17">17</span>';
                        timeSlotStr += '<span class="t18">18</span><span class="t19">19</span><span class="t20">20</span><span class="t21">21</span><span class="t22">22</span><span class="t23">23</span>';
                    timeSlotStr += '</div>';
                timeSlotStr += '</div>';
                timeSlotStr += '<div class="show_time w7">';
                    timeSlotStr += '<div class="weekday"><input type="checkbox" checked="true" selIndex="7"/>&nbsp;星期日</div>';
                   timeSlotStr += ' <div class="time_picker" style="border-bottom:0;">';
                        timeSlotStr += '<span class="t0">0</span><span class="t1">1</span><span class="t2">2</span><span class="t3">3</span><span class="t4">4</span><span class="t5">5</span>';
                        timeSlotStr += '<span class="t6">6</span><span class="t7">7</span><span class="t8">8</span><span class="t9">9</span><span class="t10">10</span><span class="t11">11</span>';
                        timeSlotStr += '<span class="t12">12</span><span class="t13">13</span><span class="t14">14</span><span class="t15">15</span><span class="t16">16</span><span class="t17">17</span>';
                        timeSlotStr += '<span class="t18">18</span><span class="t19">19</span><span class="t20">20</span><span class="t21">21</span><span class="t22">22</span><span class="t23">23</span>';
                    timeSlotStr += '</div>';
                timeSlotStr += '</div>';
                timeSlotStr += '<div class="clear"></div>';
               
                timeSlotStr += '<input type="hidden" name="timeslots" class="timeslots_input" value=""/>';
            timeSlotStr += '</span>';
            $(this).append(timeSlotStr);
             _self.setTimeSlotValue(options.value);
        });
    };
    
    $.fn.setTimeSlotValue = function(value){
         return this.each(function(){
            var pos;
            for (var i = 0 ;i < value.length ; i++){
                var span = $(this).find(".time_picker span").eq(i);
               
                 pos = timeFunction.getPos(span);
                if(value.charAt(i) == "1"){
                    span.css("background-position",pos[0]+" 0px");
                }else{
                    span.css("background-position",pos[0]+" 11px");
                }
            }
         });
    }; 
    $.fn.getTimeSlotValue = function(value){
        var timeArray = [];
         this.each(function(){
            var timeStr;
            timeStr = $(this).find('input[name="timeslots"]').val( $(this).find('.time_picker span').map(function(i,e){
                var pos = timeFunction.getPos($(e));
                if (pos[1]=="0"||pos[1]=="0px"||pos[1]=="0%"){
                    return "1";
                }else{
                    return "0";
                }
            }).get().join(""));
            timeArray.push(timeStr);
         });
         return timeArray;
    };
    $.fn.updateTime=function (){
        return this.each(function(){
            $(this).find('input[name="timeslots"]').val( $(this).find('.time_picker span').map(function(i,e){
                
                var pos = timeFunction.getPos($(e));
                if (pos[1]=="0"||pos[1]=="0px"||pos[1]=="0%"){
                    return "1";
                }else{
                    return "0";
                }
            }).get().join(""));
        });
    };

})(jQuery);