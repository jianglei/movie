$(function(){
	 //initUIRadio();
	 initUISelect();
         umengRadioImitate();//radio_imitate
	umengSelectIitate_withoutValue();//select_imitate
})
function initUISelect(){
	$('.ui_select').unbind("click").click(function(){
		//ie6 for overflow auto
		if($.browser.msie&&($.browser.version < "7.0")){
			if($(this).children('.pop_menu').height()>150){
				$(this).children('.pop_menu').css('height','144px');
			}
		}
		//click slide
		$(this).children('.pop_menu').slideToggle(300);
	})
	$('.ui_select .pop_menu').delegate('ul li a','click',function(){
		$(this).parent().parent().parent().prev('.text').html($(this).html());
                $('input[name="'+$(this).attr('pointto')+'"]').val($(this).attr('content'));
	})
	$(document).unbind("click").click(function(){
		$('.ui_select .pop_menu').hide();
	})
	$('.ui_select').click(function(e){
		if (e.stopPropagation) e.stopPropagation();
		else e.cancelBubble = true;
	})
}
//==radio_imitate start==
function umengRadioImitate(){
	$('.radio_imitate').click(function(){
		$('.radio_imitate').removeClass('radio_imitate_checked');
		$(this).addClass('radio_imitate_checked');
	})
}

//==select_imitate start==
function umengSelectIitate_withoutValue(){
	$('.select_imitate').click(function(){
		//ie6 for overflow auto
		if($.browser.msie&&($.browser.version < "7.0")){
			if($(this).children('.pop_menu').height()>150){
				$(this).children('.pop_menu').css('height','144px');
			}
		}
		//click slide
		if(!$(this).hasClass('disableShow')){
			$(this).children('.pop_menu').slideToggle(300);
		}
	})
	$(document).click(function(){
		$('.select_imitate .pop_menu').hide();
	})
	$('.select_imitate').click(function(e){
		if (e.stopPropagation) e.stopPropagation();
		else e.cancelBubble = true;
	})
}

