/*
 * font & fontsize picker
 * luxueyan
 * Date: 2012-02-18
 * jquery 扩展 生成font piker 和font size picker
 */
var fontSizes = {
            初号:'40pt',小初:'36pt',一号:'26pt',小一:'24pt',二号:'22pt',小二:'18pt',三号:'16pt'
            ,小三:'15pt',四号:'14pt',小四:'12pt',五号:'10.5pt',小五:'9pt',六号:'7.5pt',小六:'6.5pt',七号:'5.5pt',八号:'5pt'
            ,5:'5pt',5.5:'5.5pt',6.5:'6.5pt',7.5:'7.5pt',8:'8pt',9:'9pt',10:'10pt'
            ,10.5:'10.5pt',11:'11pt',12:'12pt',14:'14pt',16:'16pt',18:'18pt',20:'20pt'
            ,22:'22pt',24:'24pt',26:'26pt',28:'28pt',36:'36pt',48:'48pt',72:'72pt'
        };
(function($){
    $.fn.fontPicker = function(paramNew){
        var param = {
            valueLink :null
        }
        $.extend(param,paramNew);
        var fonts = new Array(
            '微软雅黑','宋体','黑体',
            'Arial,Arial,Helvetica,sans-serif',
            'Arial Black,Arial Black,Gadget,sans-serif',
            'Comic Sans MS,Comic Sans MS,cursive',
            'Courier New,Courier New,Courier,monospace',
            'Georgia,Georgia,serif',
            'Impact,Charcoal,sans-serif',
            'Lucida Console,Monaco,monospace',
            'Lucida Sans Unicode,Lucida Grande,sans-serif',
            'Palatino Linotype,Book Antiqua,Palatino,serif',
            'Tahoma,Geneva,sans-serif',
            'Times New Roman,Times,serif',
            'Trebuchet MS,Helvetica,sans-serif',
            'Verdana,Geneva,sans-serif' );
        return this.each(function(){
            var sel = this;
            var fontList = '';
            $.each(fonts, function(i, item) {
                fontList += '<li><a href="#" title="'+ item +'" content="'+ item +'" pointto="'+ param.valueLink +'">'+ item +'</a></li>'
            })

            $(sel).html('').append(fontList);
        });
    }
    $.fn.fontSizePicker = function(paramNew){
        var param = {
            valueLink :null
        }
        $.extend(param,paramNew);
        
        return this.each(function(){
            var sel = this;
            var fontList = '';
            $.each(fontSizes, function(i, item) {
                fontList += '<li><a href="#" title="'+ i +'" content="'+ i +'" pointto="'+ param.valueLink +'">'+ i +'</a></li>'
            })

            $(sel).html('').append(fontList);
        });
    }
})(jQuery)
