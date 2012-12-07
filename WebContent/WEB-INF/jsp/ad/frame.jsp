<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>
<script type="text/javascript">
	if('${message}'!=''){
		alert('${message}');
	}
	if('${url}' != ''){
		if('${uploadType}' == 'pic'){
			
            	parent.$('#pic').val('${url}');
                parent.$('#banner_show').attr("src",'${url}');
                parent.$('#banner_show').show();
                if(parent.$('#banner_File').val()!=""){
                    var bannerFile = parent.$('#banner_File').clone();
                    parent.$('#banner_File').remove();
                    bannerFile.appendTo(parent.$('#banner_file_span'));
                    parent.$("#banner_File_warning").attr("class","warning");
                    parent.$("#banner_File_warning").html("");
                    parent.$('#banner_File').removeClass('file_not_null');
                }
            
		}
	}
	
</script>

</body>

</html>
