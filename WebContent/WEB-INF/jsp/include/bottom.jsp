<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
(function(){
    var pathName = location.pathname,
    title,subtitle1,subtitle2='${logoName}广告管理平台',
    //菜单列表
    menus = {
                adslot:["/adslot","/app"], //广告位
                ad:["/ad","/adorder"],//广告
                report:["/report","/report/adslot","/report/adorder","/report/ad","/report/app","/report/platform"],//报表
                settings:["/settings", '/settings/edit_logo','/settings/edit_black_list']//设置
            };
    out:
    for (var key1 in menus){
        for(var key2 in menus[key1]){
            if(menus[key1][key2] == pathName){
                title=$('#submenu_'+key1).show().parent().find("#menu ul li").removeClass("current").filter('#'+key1+'_tab').addClass('current').find('a').text();
                subtitle1=$('#submenu_'+key1).find("ul li").removeClass("current").eq(key2).addClass("current").find('a').text();
                break out;
            }
        }
     };
     if(subtitle1){
        document.title = subtitle1 + ' - ' + subtitle2;
     }
     
})()
</script>

<!-- baidu or google analytics -->