<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Insert title here</title>
</head>
<body>
  <form name="upload" method="post" action="/upload/uploadFile" target="test" onsubmit="return check(this);"  enctype="multipart/form-data" >
   <input type="file" name="file" />
   <input type="text" name="type" value=""/>
   <input type="hidden" name="width" value=""/>
   <input type="hidden" name="height" value=""/>
   <input type="submit" value="提交"/>
   <input type="text" name="url" value=""/>
   <div id="imageDiv"></div>
  </form>
  <iframe name="test" id="test" width="0" height="0">
  	
  </iframe>
    <script type="text/javascript">
        document.upload.type.value=parent.document.getElementById('type').value;
        document.upload.width.value=parent.document.activeform.width.value;
        document.upload.height.value=parent.document.activeform.height.value;
        // console.log(parent.document.activeform.height.value);
        function check(form){
                if(form.file.value==""){
                    alert("请选择一个文件");
                    return false;
                }
                parent.document.getElementById('uploadDiv').style.display='none';
                return true;
            }
    </script>
 </body>

	
</html>