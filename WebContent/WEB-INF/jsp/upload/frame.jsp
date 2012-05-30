<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>
<script type="text/javascript">
//if('${message}'!=''){	alert('${message}');	}
(function(){
    if('${url}' != ''){
	//if('${uploadType}' == 'pic'){
		//alert('${url}');
		//alert(parent.$('#${feedback}_show').attr("src"));
        if (parent.$('.${feedback}_show').length > 0)	parent.$('.${feedback}_show').attr("src",'${url}');
		if (parent.$('#${feedback}').length > 0)				parent.$('#${feedback}').val('${url}');
		if (parent.$('#${feedback}_text').length > 0)		parent.$('#${feedback}_text').val('${url}');
		
		if (parent.${callback})		parent.${callback}(parent.$('#${fileObject}').siblings('input[verify="file"]'));
		parent.$('#${fileObject}').parent().find('#loadingStatus').remove();
		// console.log(parent.$('#${fileObject}'));
		parent.uploadFileWait = false;
		// console.log(parent.uploadFileWait);
    }
})()

</script>


</body>

</html>
