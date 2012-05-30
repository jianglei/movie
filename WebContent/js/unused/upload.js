//上传图片

function uploadPic(){
	$.ajaxFileUpload({
			url:'/creative/uploadPic',
			secureuri:false,
			fileElementId:'creativePic',
			dataType: 'json',
			success: function (data, status){
				if (data.result == 1) {
					//alert(data.mediaUrl);
                    //修改预览图片的url
                    $("#previewPic1").attr("src", baseUrl + data.mediaUrl);
                    $("#previewPic2").attr("src", baseUrl + data.mediaUrl);
                    //设置隐藏的picurl为上传图片链接
                    $("#picUrl").val(data.mediaUrl);
				} else {
					alert(data.error);
					$("#picUrl").val("");
				}
			},
			error: function (data, status, e){
				alert("上传失败！请确认文件大小没有超过限制");
				$("#picUrl").val("");
			}
		}
	)
}
