<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="/js/libs/jquery.js?${constantVersion}" type="text/javascript"></script>
<script src="/js/libs/jquery-ui-1.8.16.custom.min.js?${constantVersion}" type="text/javascript"></script>
<script src="/js/libs/highcharts.js?${constantVersion}" type="text/javascript"></script>
<script src="/js/libs/tmpl.min.js?${constantVersion}" type="text/javascript"></script>
<script src="/js/report/report${build}.js?${constantVersion}" type="text/javascript"></script>
<script type="text/x-jquery-tmpl" id="tmplData">
    <tr><td class="\${$item.getClass()}">\${date}</td>
    <td class="center">￥\${$item.round($data.avgPrice)}</td>
    <td class="center">\${impression}</td>
    <td class="center">\${click}</td>
    <td class="center">\${download}</td>
    <td class="center">￥\${$item.round($data.revenue)}</td></tr>
</script>
<script type="text/x-jquery-tmpl" id="tmplPromotionData">
    <tr><td class="\${$item.getClass()}">\${date}</td>
    <td class="center">\${impression}</td>
    <td class="center">\${promoterImpression}</td>
    <td class="center">\${click}</td>
    <td class="center">\${promoterClick}</td>
    <td class="center">\${download}</td>
    <td class="center">\${promoterDownload}</td>
</script>
<script type="text/x-jquery-tmpl" id="tmplList">
    <li><a href="#" id="\${id}" title="\${name}">\${name}</a></li>
</script>

