<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="main" style="padding-top:7px;">
    <div class="panel_choicelnk" style="float:left; padding-top:8px;min-height:25px;">
        <a href="###" period="today" class="">今天</a>
        <a href="###" period="yesterday" class="">昨天</a>
        <a href="###" period="day_-7" class="current">过去7天</a>
        <a href="###" period="lastWeek">上周</a>
        <a href="###" period="lastMonth">上月</a>
        <a href="###" period="lastQuarter">上季度</a>
        <a href="###" class="btn_choice_time" style="cursor:pointer;">自定义时间</a>
    </div>
    <div class="choice_time">
         <div class="panel_choice_time" style="display:none;">  
            <label for='cus_start_time'>&nbsp;&nbsp;开始:&nbsp;&nbsp;</label><input id="cus_start_time" class="input_text" placeholder="选择开始时间"/>
            <label for='cus_end_time'>&nbsp;&nbsp;结束:&nbsp;&nbsp;</label><input id="cus_end_time" class="input_text" placeholder="选择结束时间"/>
            &nbsp;&nbsp;<button>查询</button>
        </div>
    </div>

    
    <div class="sel_wrap sel_wrap1">
        <span class="left"></span>
        <span class="con">加载中</span>
        <span class="arrow_down" ></span>
        <div class="pop_menu">
            <ul>
            </ul>
        </div>
    </div>
    <div class="sel_title sel_title1">选择应用：</div>
    <div class="sel_wrap sel_wrap2" style="display:none">
        <span class="left"></span>
        <span class="con" category="">汇总</span>
        <span class="arrow_down" ></span>
        <div class="pop_menu">
            <ul>
                <li><a title="汇总" category="" href="###">汇总</a></li>
                <li><a title="自销广告" category="1" href="###">自销广告</a></li>
                <li><a title="友盟广告" category="2" href="###">友盟广告</a></li>
                <li><a title="友盟交换" category="0" href="###">友盟交换</a></li>
            </ul>
        </div>
    </div>
    <div class="sel_title sel_title2" style="display:none">选择接入网络：</div>
    <div class="sel_wrap sel_wrap3" style="display:none">
        <span class="left"></span>
        <span class="con" category="">Android</span>
        <span class="arrow_down" ></span>
        <div class="pop_menu">
            <ul>
                <li><a title="Android平台" platform="android" href="###">Android</a></li>
                <li><a title="iOS广告" platform="iOS" href="###">iOS</a></li>
            </ul>
        </div>
    </div>
    <div class="sel_title sel_title3" style="display:none">选择平台：</div>
    <div class="output_csv fr hd"><a>导出所有广告数据</a></div>
    <div class="clear"></div>
    <div class="panel_dashboard panel_dashboard1" style="">
        <div class="item first">
            <h3>平均点击价格</h3>
            <span>￥0</span>
        </div>
        <div class="item">
            <h3>广告展示次数</h3>
            <span>0</span>
        </div>
        <div class="item">
            <h3>点击量</h3>
            <span>0</span>
        </div>
        <div class="item">
            <h3>点击率</h3>
            <span>0</span>
        </div>
        <div class="item last">
            <h3>收益</h3>
            <span>￥0</span>
        </div>
    </div>
    <div class="panel_dashboard panel_dashboard2" style="display:none;">
        <div class="item first">
            <h3>点击率</h3>
            <span>￥0</span>
        </div>
        <div class="item">
            <h3>贡献点击</h3>
            <span>0</span>
        </div>
        <div class="item">
            <h3>贡献下载</h3>
            <span>0</span>
        </div>
        <div class="item">
            <h3>获得点击</h3>
            <span>0</span>
        </div>
        <div class="item last">
            <h3>获得下载</h3>
            <span>￥0</span>
        </div>
    </div>

    <div class="panel_chart">
        <div id="chart1" ></div>
    </div>

    <div class="clear"></div>
    <div class="panel_table">
        <div class="part_operation">
            <div class="txt">
                报表列表
            </div>
        </div>
        <div class="part_table">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="">
                <tr class="normalReportTitle">
                    <th width="180">日期</th>
                    <th width="100">平均点击价格</th>
                    <th>广告展示次数</th>
                    <th>点击次数</th>
                    <th>下载数</th>
                    <th width="120" class="last">收益</th>
                </tr>
                <tr class="promotionReportTitle" style="display:none">
                    <th width="180">日期</th>
                    <th width="100">贡献展示数</th>
                    <th>获得展示数</th>
                    <th>贡献点击量</th>
                    <th>获得点击量</th>
                    <th>贡献下载量</th>
                    <th class="last">获得下载量</th>
                </tr>
            </table>
        </div>
        <div class="part_operation part_operation_footer">
<!--				<div class="fl f_csv"><a href="#">导出CSV</a></div>-->
        </div>
    </div>
</div>
