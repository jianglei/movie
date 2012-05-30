/* 
 * ����Ƿ�Ϊ��
 */
function verify_null(obj, msg, newline,errPrevObj) {
    var prevObj = errPrevObj!=undefined?errPrevObj:obj;
    var val = obj.val().replace(/(^\s*)|(\s*$)/g,'')
    if (val.indexOf('<')>-1||val.indexOf('>')>-1||val.indexOf('script')>-1||val.indexOf('eval')>-1){
        val = '';
        msg = '字符不符合规则';
    }
    if ( val== "") {
        if (obj.parent().find(".err").html() == null) {
            if (newline) {
                prevObj.after("<div class='err'>" + msg + "</div>");
            }
            else {
                prevObj.after("<span class='err'>" + msg + "</span>");
            }
        }
        obj.focus();
        return false;
    } else {
        obj.parent().find(".err").remove();
        return true;
    }
}
function verify_file(obj,msg,type,newline){
    var patt=new RegExp(type,"g");
       
    if (!patt.test(obj.val())) {
            
        if (obj.parent().find(".err").html() == null) {
            if (newline) obj.after("<div class='err'>" + msg + "</div>");
            else obj.after("<span class='err'>" + msg + "</span>");
        }
        obj.focus();
        return false;
    } else {
        obj.parent().find(".err").remove();
        return true;
                
    }
}
/* 
 * ��ʽ��ʱ��
 * date unixʱ���
 * split �ָ��
 */
function formatDate(date, split){
	var d = new Date(date);
	return d.getFullYear() + split + (d.getMonth() + 1) + split + d.getDate();
}