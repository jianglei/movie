// JavaScript Document
$(function(){
   adjustHeight();
})
//计算产品介绍和帮助栏目高度start
function adjustHeight(){
	$('.umengADportal_product .col_2 .col_l').removeAttr('style');
	var tempHeightleft= $('.umengADportal_product .col_2 .col_l').height();
	var tempHeightRight = $('.umengADportal_product .col_2 .col_r').height();
	if(tempHeightleft<=tempHeightRight){
		$('.umengADportal_product .col_2 .col_l').height(tempHeightRight);
	}
}
//计算产品介绍和帮助栏目高度end


//帮助弹出提示start
function iconHelpPop(helpObj , popContent){
	var tempContent  =popContent;
	var tempHtml = '<span class="pop_content">'+tempContent+'</span>';
	
	$(helpObj).append(tempHtml);
	$(helpObj).hover(function(){
		$(this).children('.pop_content').slideDown(100);
	},function(){
		$(this).children('.pop_content').slideUp(100);
	})
}
//帮助弹出提示end