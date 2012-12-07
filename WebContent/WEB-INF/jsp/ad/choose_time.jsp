<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>            
            <p class="time"><span id="date_show">今天</span></p>
            <!-- pop_contont start -->
            <div class="pop_contont">
                <div class="col_2 umengADsystem_clearfix">
                    <div class="col_first">
                        <div id="datepicker1"></div>
                    </div>
                    <div class="col_to">至</div>
                    <div class="col_first">
                        <div id="datepicker2"></div>
                    </div>
                    <div class="col_list">
                        <ul>
                            <li><a href="javascript:setDateChoose('Today');">今天</a></li>
                            <li><a href="javascript:setDateChoose('Yesterday');">昨天</a></li>
                            <li><a href="javascript:setDateChoose('7days');">过去7天</a></li>
                            <li><a href="javascript:setDateChoose('30days');">过去30天</a></li>
                            <li><a href="javascript:setDateChoose('90days');">过去90天</a></li>
                            <li><a href="javascript:setDateChoose('LastMonth');">上一个月</a></li>
                            <li><a href="javascript:setDateChoose('ThisMonth');">本月</a></li>
                        </ul>
                    </div>
                </div>
                <p class="panel_btn">
                    <a href="javascript:void(0);" class="btn_cancel">取消</a>
                    <a href="javascript:submitDateQuery();" class="btn_ok">确定</a>
                </p>
                <input type="hidden" id="dateType" name="dateType" value=""/>
                <input type="hidden" id="startDate" name="startDate" value=""/>
                <input type="hidden" id="endDate" name="endDate" value=""/>
            </div>
            <!-- pop_contont end -->
<script type="text/javascript">
function setDateChoose(dateType, startDate, endDate){
	var now=new Date();
	if('Today'==dateType){
        startDate=$.datepicker.formatDate( "yy-mm-dd", now);
        endDate=$.datepicker.formatDate( "yy-mm-dd", now);
    }else if('Yesterday'==dateType){
        var yesterDay=new Date();
        yesterDay.setDate(now.getDate()-1);
        startDate=$.datepicker.formatDate("yy-mm-dd", yesterDay);
        endDate=$.datepicker.formatDate("yy-mm-dd", yesterDay);
    }else if('7days'==dateType){
        var date7=new Date();
        date7.setDate(now.getDate()-6);
        startDate=$.datepicker.formatDate("yy-mm-dd", date7);
        endDate=$.datepicker.formatDate("yy-mm-dd", now);
    }else if('30days'==dateType){
        var date30=new Date();
        date30.setDate(now.getDate()-29);
        startDate=$.datepicker.formatDate("yy-mm-dd", date30);
        endDate=$.datepicker.formatDate("yy-mm-dd", now);
    }else if('90days'==dateType){
        var date90=new Date();
        date90.setDate(now.getDate()-89);
        startDate=$.datepicker.formatDate("yy-mm-dd", date90);
        endDate=$.datepicker.formatDate("yy-mm-dd", now);
    }else if('LastMonth'==dateType){  
        var dateLastMonthStart=new Date();
        dateLastMonthStart.setDate(0);
        dateLastMonthStart.setDate(1);
        startDate=$.datepicker.formatDate("yy-mm-dd", dateLastMonthStart);
        var dateLastMonthEnd=new Date();
        dateLastMonthEnd.setDate(0);
        endDate=$.datepicker.formatDate("yy-mm-dd", dateLastMonthEnd);
    }else if('ThisMonth'==dateType){ 
        var dateThisMonth=new Date();
        dateThisMonth.setDate(1);
        startDate=$.datepicker.formatDate("yy-mm-dd", dateThisMonth);
        endDate=$.datepicker.formatDate("yy-mm-dd", now);
    }
    $("#datepicker1").datepicker("setDate",startDate);
    $("#datepicker2").datepicker("setDate",endDate);  
    $("#date_show").empty();
    $("#date_show").append(startDate+" 至 "+endDate);
    $("input[name='startDate']").val(startDate);
    $("input[name='endDate']").val(endDate);
    $("input[name='dateType']").val('Custom');  

}
function submitDateQuery(){
    var startDate=$.datepicker.formatDate("yy-mm-dd", $("#datepicker1").datepicker("getDate"));
    var endDate=$.datepicker.formatDate("yy-mm-dd", $("#datepicker2").datepicker("getDate"));
    $("input[name='startDate']").val(startDate);
    $("input[name='endDate']").val(endDate);
    $("input[name='dateType']").val('Custom');  
    $("#listQueryForm").submit();
}
</script>