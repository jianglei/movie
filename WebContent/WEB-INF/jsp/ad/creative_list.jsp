<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>广告创意列表</title>
<link rel="stylesheet" href="/css/default.css?t=258963" />
<link href="/css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.6.1.min.js"></script> 
<script type="text/javascript" src="/js/ui.js"></script> 
<script type="text/javascript" src="/js/util.js?rand=147852369"></script>
<script type="text/javascript" src="/js/list_util.js?rand=147852369"></script> 
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="/js/jquery-ui-1.8.16.custom.min.js"></script>
</head>
<body>

<!-- 正文 start -->
<div class="umengADsystem_container">
    
  
    <!-- 表格start -->
    <div class="umengADsystem_tabel_data">
        <div class="part_operation">
    
            <dl>
                <dd class="addnew"><input type="button" value="新建广告创意" class="btn_add" onclick="window.location.href='/ad/edit';"/></dd>
                 
                <dd class="play"><a href="javascript:void(0);" onclick="change('ad','status',0);return false;">启用</a></dd>
                <dd class="pause"><a href="javascript:void(0);" onclick="change('ad','status',1);return false;" >暂停</a></dd>
                <dd class="delete"><a href="javascript:void(0);"  onclick="change('ad','is_del',1);return false;">删除</a></dd>
                <!-- 
                <dd class="return"><a class="icon_return" href="/advertiser/campaign?dateType=${page.param.dateType}&startDate=${page.param.startDate}&endDate=${page.param.endDate}">返回推广计划列表</a></dd>
                <dd class="return"><a class="icon_return" href="/advertiser/adgroup/${campaign.id }/index?dateType=${page.param.dateType}&startDate=${page.param.startDate}&endDate=${page.param.endDate}">返回广告组列表</a></dd>
           		-->
            </dl>
        </div>
        <div class="part_table">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th width="35"><input type="checkbox" name="record_head_ch" id="record_head_ch" onclick="check_records(this);"/></th>
                    <th>创意名称</th>
                    <th>状态</th>
                    <th width="100">图片</th>
                    <th>出价</th>
                    <th>平均点击价格</th>
                    <th>展示次数</th>
                    <th>点击次数</th>
                    <th>点击率</th>
                    <th>下载次数</th>
                    <th>消费</th>
                    <th class="last">操作</th>
                </tr>
                <c:forEach items="${result}" var="creative" varStatus="status">
                <tr <c:if test="${status.index%2==0 }">class="transbg tr_pv"</c:if><c:if test="${status.index%2!=0 }">class="tr_pv"</c:if> id="tr_${creative.original_id }">
                    <td>
                    <input type="checkbox" name="record_ch" value="${creative.id }"/>
                    <input type="hidden" name="object_status" id="status_${creative.id }" value="${creative.status}"/>
                    </td>
                    <td class="umengADsystem_align_left">
	                     <a href="/advertiser/creative/edit/${creative.id }" onclick="checkCanEdit(event, ${creative.id});">${creative.name }</a>
                    </td>
                    <td id="td_status_${creative.id }">
                      <c:choose>
                          <c:when test="${creative.status==1}">
                              <img src="/images/img_content/icon_state_invalid.gif" width="16" height="16" alt="暂停" />暂停
                          </c:when>
                          <c:otherwise>
                              <img src="/images/img_content/icon_state_ok.gif" width="16" height="16" alt="有效" />有效
                          </c:otherwise>                          
                       </c:choose>   
                    </td>
                    <td><img src="${creative.pic }" width="100" height="100"/></td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                    <td><a class="icon_modify"  href="/ad/edit?id=${creative.id }">编辑</a></td>
                </tr>
                </c:forEach>
            </table>
        </div>
        
    </div>
</div>

</body>
</html>
