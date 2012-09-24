window.Report = function(param){
    this.param = {type:"get",data:{pageSize:100000},dataUrl:"",listUrl:"",dataContainer:".part_table table",listContainer:".sel_wrap1 .pop_menu ul",dataTmpl:"#tmplData",dataPromotionTmpl:'#tmplPromotionData',listTmpl:"#tmplList"};
    $.extend(this.param,param);
    this.period = "day_-7";
    this.promotionOpen = false;
    this.currentToken = '';
     
};
window.Report.prototype = {
    initUI : function(){
        var that = this;
        var selTitle = {list:'',adorder:'选择订单:',adslot:'选择广告位:',ad:'选择广告:',app:'选择应用:'};
        
        if(!!~$.inArray(that.param.name,['list','platform'])){$('div.sel_wrap,div.sel_title').hide();}
        if(that.param.name=='ad'){$('.output_csv').show();}

        if(selTitle[that.param.name]){$('div.sel_title1').text(selTitle[that.param.name]);}
        $('.sel_wrap').click(function(){
            $(this).find('.pop_menu').slideToggle(300,function(){
              $(".con",this).next().toggleClass("arrow_up");
            });
        });
        //选择广告位,广告,应用,订单
        $(".sel_wrap1").delegate("li","click",function() {
            $(".sel_wrap1 .con").text($(this).text()).attr('title',$(this).text()).attr('a_id',$(this).find("a").attr('id'));
            that.currentToken = that.getToken();
            that.getPostData();
            that.loadData($(this).find("a").attr('id'),that.param.data);
        });
        if(that.param.name == 'platform'){
          $('div.sel_wrap3,div.sel_title3').show();
          $(".sel_wrap3").delegate("li","click",function() {
            $(".sel_wrap3 .con").text($(this).text()).attr('title',$(this).text()).attr('platform',$(this).find("a").attr('platform'));
            that.param.data.platform = $(".sel_wrap3 .con").attr('platform');
            that.currentToken = that.getToken();
            that.getPostData();
            that.loadData(null,that.param.data);
                
          });
        }
        if(!!~$.inArray(that.param.name,['app','adslot','list'])){

          $('div.sel_wrap2,div.sel_title2').show();
          $(".sel_wrap2").delegate("li","click",function() {
            $(".sel_wrap2 .con").text($(this).text()).attr('title',$(this).text()).attr('category',$(this).find("a").attr('category'));
            that.currentToken = that.getToken();
            that.getPostData();
            that.loadData($(".sel_wrap1 .con").attr('a_id'),that.param.data);
                
          });
        }
        
        //今日 昨日 ...链接
        $('div.panel_choicelnk a').not('.btn_choice_time').bind("click",function(){
            if($(this).hasClass('current')){return false};
            that.param.data.period = that.period = $(this).attr('period');
            $(".panel_choice_time").hide();
            // delete that.param.data.startDate;
            // delete that.param.data.endDate;
            $(this).addClass('current').siblings().removeClass('current');
            that.getPostData();
            if( that.period!='today'&& that.period!='yesterday'){
                $('.panel_chart').show();
                
            }else{
                $('.panel_dashboard .item span').text('-');
                $('.panel_chart').hide();
                
            }
            that.currentToken = that.getToken();
            
            that.loadData(that.param.dataUrl=="/report/total"?"":$(".sel_wrap1 .con").attr('a_id'),that.param.data);

            return false;
                
        });
        //自定义时间
        $(".btn_choice_time").click(function() {
            $(this).addClass('current').siblings().removeClass('current');
            $(this).parent().toggleClass('toggle_choice_time');
            $(".panel_choice_time").toggle();
            $('.output_csv').toggleClass('cl');
          
        });
        $('#cus_start_time').datepicker({
            dateFormat: 'yy-mm-dd',
            changeYear:true,
            changeMonth:true,
            monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
            dayNames:['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
            dayNamesMin:['日','一','二','三','四','五','六']
        });
        $('#cus_end_time').datepicker({
            dateFormat: 'yy-mm-dd',
            changeYear:true,
            changeMonth:true,
            monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
            dayNames:['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
            dayNamesMin:['日','一','二','三','四','五','六']
        });
        $('.panel_choice_time button').click(function(){
          that.currentToken = that.getToken();
          delete that.period;
          that.getPostData();
          $('.panel_chart').show();
            that.loadData(that.param.dataUrl=="/report/total"?"":$(".sel_wrap1 .con").attr('a_id'),that.param.data);
        });

    },
    dataFilter:{
        round: function(data){
          var dataDec=data.toString().split('.')[1];
          if(dataDec!==undefined && dataDec.length > 2){
            return data.toFixed(2);
          }else{
            return data;
          }
        },
        getClass : function(){
          var day = new Date(this.data.date.replace(/-/g,'/')).getDay();
          if(!!~$.inArray(day,[0,6])){
            return 'weekday';
          }else{
            return '';
          }
        }
    },
    getToken:function(){
      return Math.random().toString(16).substring(2);
    },
    getPostData :function(){
      var that = this;
      delete that.param.data.period;
      delete that.param.data.startDate;
      delete that.param.data.endDate;
      delete that.param.data.category;
      that.promotionOpen = false;
      that.param.data.category = $(".sel_wrap2 .con").attr('category');
      if($('div.panel_choicelnk a.current').index() < 6){
          that.param.data.period = that.period;
      }else{
      if($('#cus_start_time').val()!=='' && $('#cus_end_time').val()!==''){
           
           that.param.data.startDate = $('#cus_start_time').val();
           that.param.data.endDate = $('#cus_end_time').val();
        }
      }
      if($(".sel_wrap2 .con").attr('category') == '0'){
        that.promotionOpen = true;
      }
        
    },
    tmplList : function(data){
        var that = this;
         if(this.param.listTmpl.length>0){

              $(this.param.listTmpl).tmpl(data).appendTo(this.param.listContainer);
              var name = $(that.param.listContainer).find("li a").eq(0).text(),a_id=$(that.param.listContainer).find("li a").eq(0).attr('id');
              
              $(".sel_wrap1 span.con").text(name).attr('title',name).attr('a_id',a_id);
              this.loadData($(that.param.listContainer).find("li a").eq(0).attr("id"));
        }
//        this.drawGraph(data);
    },
    tmplData : function(data){
       
        if(this.param.dataTmpl.length>0){
            $(this.param.dataContainer).find("tr:gt(1)").remove();
            if(this.promotionOpen){
            $('tr.normalReportTitle').hide();
            $('tr.promotionReportTitle').show().parent().addClass('promotionTable');
            $(this.param.dataPromotionTmpl).tmpl(data.reportList).appendTo(this.param.dataContainer);
            if(this.period!='today'&&this.period!='yesterday'){
                this.drawGraph(data.reportList);
	          }
	           this.dashPromotionBoard(data.summary);
             }else{
              $('tr.normalReportTitle').show();
              $('tr.promotionReportTitle').hide();
              $(this.param.dataTmpl).tmpl(data.reportList,this.dataFilter).appendTo(this.param.dataContainer);
             	if(this.period!='today'&&this.period!='yesterday'){
                  this.drawGraph(data.reportList);
	            }
	            this.dashBoard(data.summary);
             }
              
              
              
        }
       
    },
    loadList :function(){
        var that = this;

        that.param.data.rnd = Math.random();
            $.ajax({
                type: that.param.type,
                url: that.param.listUrl,
//                dataType: that.param.dataType || "",
                data:that.param.data ,
                beforeSend : function(){
                    $(that.param.listContainer).find("li").remove().parents("div.sel_wrap").find("span.con").text("加载中！");
                    
                },
                success: function(data, textStatus){
                  loginValidate(data);
                    if(data[that.param.resultInterface]!==undefined&&data[that.param.resultInterface].result.length > 0){
                        that.tmplList(data[that.param.resultInterface].result);
                    }else{
                        $(".sel_wrap1 span.con").text("无记录!");
                        
                        $(that.param.dataContainer).find("tr:gt(1)").remove().end().append('<tr><td class="wait">没有记录！</td></tr>');
                    }
                        

                },
                complete: function(XMLHttpRequest, textStatus){

                },
                error: function(){
                    $(".sel_wrap1 span.con").text("加载失败！");
                    
                }
        });
    },
    loadData : function(id,data){
        var that = this;
        var nowToken = that.currentToken;
        if(data===undefined) data ={};
        data.rnd = Math.random();
            $.ajax({
                type: that.param.type,
                url: that.param.dataUrl+(id!=undefined?('/'+id):""),
                
//                dataType: that.param.dataType || "",
                data:data,
                beforeSend : function(){
                  $(that.param.dataContainer).find("tr:gt(1)").remove().end().append('<tr><td class="wait">加载中请稍等！</td></tr>');
                  $('#chart1').text('加载中！');
                },
                success: function(data, textStatus){
                  loginValidate(data);
                  if(nowToken!=that.currentToken) return;
                  if(data.status == 'failed'){
                    $(that.param.dataContainer).find("td.wait").html("加载失败！");
                    $('#chart1').text('加载失败！');
                    return false;
                  }
                  if (data&&data.reportList!==null&&data.reportList.length>0){
                      that.tmplData(data);
                  }else{
                      $(that.param.dataContainer).find("td.wait").html("没有记录！");
                      $('#chart1').text('无记录!');
                  }
                },
                complete: function(XMLHttpRequest, textStatus){
                },
                error: function(){
                   $(that.param.dataContainer).find("td.wait").html("加载失败！");
                   $('#chart1').text('加载失败！');
                }
        });
    

  },
  dashBoard : function(data){
        $('.panel_dashboard2').hide();
        $('.panel_dashboard1').show();

        var items = $('.panel_dashboard1 .item span');
        try{
            var avgPriceDeci =data.avgPrice.toString().split('.')[1],
             clickRateDeci =(data.clickRate*100).toString().split('.')[1],
             revenueDeci =data.revenue.toString().split('.')[1];
            items.eq(0).text( '￥'+ ((avgPriceDeci!=undefined&&avgPriceDeci.length>2)?data.avgPrice.toFixed(2):data.avgPrice));
            items.eq(1).text( data.impression);
            items.eq(2).text( data.click);
            items.eq(3).text( (clickRateDeci!=undefined&&clickRateDeci.length>2)?(data.clickRate*100).toFixed(2)+'%':(data.clickRate*100)+'%');
            items.eq(4) .text( '￥'+ ((revenueDeci!=undefined&&revenueDeci.length>2)?data.revenue.toFixed(2):data.revenue));
        }catch(e){
            items.text('-');
        }
  },
  dashPromotionBoard : function(data){
        $('.panel_dashboard1').hide();
        $('.panel_dashboard2').show();
        var items = $('.panel_dashboard2 .item span');
        try{
            var  clickRateDeci =(data.clickRate*100).toString().split('.')[1];
             
            items.eq(0).text( (clickRateDeci!=undefined&&clickRateDeci.length>2)?(data.clickRate*100).toFixed(2)+'%':(data.clickRate*100)+'%');
            items.eq(1).text( data.click);
            items.eq(2).text( data.download);
            items.eq(3).text( data.promoterClick);
            items.eq(4) .text(data.promoterDownload);
        }catch(e){
            items.text('-');
        }
  },
  drawGraph : function(data){
        var that = this ;
        that.chart={
         Chart:{},
         chart_id:"chart1",
         categories:[],
         step:2,
         series:[
           {name:'广告展示次数',data:[],"type":null,"visible":true},
           {name:'点击次数',data:[],"type":null,"visible":true},
           {name:'下载数',data:[],"type":null,"visible":true},
           {name:('收益'),data:[],"type":null,"visible":true}
           ]
        };
        $(that.chart.chart_id).text('');
        that.chart.step = parseInt(data.length/7);
        if(that.promotionOpen){
          that.chart.series = [
           {name:'贡献展示数',data:[],"type":null,"visible":true},
           {name:'获得展示数',data:[],"type":null,"visible":true},
           {name:'贡献点击量',data:[],"type":null,"visible":true},
           {name:'获得点击量',data:[],"type":null,"visible":true},
           {name:'贡献下载数',data:[],"type":null,"visible":true},
           {name:'获得下载数',data:[],"type":null,"visible":true},
           {name:'贡献点击率',data:[],"type":null,"visible":true},
           {name:'获得点击率',data:[],"type":null,"visible":true},
          ];
          for (var i=data.length-1;i>=0;i--){
              that.chart.categories.push(data[i].date);
              that.chart.series[0].data.push(data[i].impression);
              that.chart.series[1].data.push(data[i].promoterImpression);
              that.chart.series[2].data.push(data[i].click);
              that.chart.series[3].data.push(data[i].promoterClick);
              that.chart.series[4].data.push(data[i].download);
              that.chart.series[5].data.push(data[i].promoterDownload);
              that.chart.series[6].data.push(data[i].clickRate);
              that.chart.series[7].data.push(data[i].promoterClickRate);
            }
             
        }else{
          for (var i=data.length-1;i>=0;i--){
              that.chart.categories.push(data[i].date);
              that.chart.series[0].data.push(data[i].impression);
              that.chart.series[1].data.push(data[i].click);
              that.chart.series[2].data.push(data[i].download);
              that.chart.series[3].data.push(data[i].revenue);  
          }

        }
        
          
	 
     // 1. Define JSON options
    
     var options = {
                   chart: {"defaultSeriesType":"spline","animation":false,"renderTo": that.chart.chart_id },
                           title: {"text":$(".sel_wrap2 .con").text()},
                           legend: {"margin":15,"enabled":true},
                           xAxis: {"categories":that.chart.categories,  "labels":{"step":that.chart.step,"align":"center","rotation":0}},
                           yAxis: {"title":"","min":0,"tickPixelInterval":50},
                   		     tooltip:  {"enabled":true},
                           credits: {"enabled":false},
                           plotOptions: {"area":{"stacking":null}},
                           series: that.chart.series,
                           subtitle: {}
                   };
    // 2. Add callbacks (non-JSON compliant)
    	options.tooltip.formatter = function() {
          var tipStr = '';
          tipStr = this.series.name + '@' + this.x +': ';
          if(this.series.name=='收益'){
            var yValueDec = this.y.toString().split('.')[1];
            tipStr += '￥'+ ((yValueDec!=undefined&&yValueDec.length>2)?this.y.toFixed(2):this.y);
          }else if(this.series.name.indexOf("点击率") > -1){
            var yValueDec = (this.y*100).toString().split('.')[1];
            tipStr += ((yValueDec!=undefined&&yValueDec.length>2)?this.y.toFixed(2):this.y) + '%';
           
          }else{
            tipStr += this.y;
          }
             
            return tipStr;
      }
    // 3. Build the chart
    that.chart.Chart = new Highcharts.Chart(options);
  }
}