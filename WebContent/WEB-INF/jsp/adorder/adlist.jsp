<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<script src="/js/libs/tmpl.min.js?${constantVersion}" type="text/javascript"></script>
<script src="/js/modules/adlist${build}.js?${constantVersion}" type="text/javascript"></script>
<script type="text/x-jquery-tmpl" id="adList">
<tr>
        <td class="pos_rel" style=" display: block;">
            <input type="checkbox" value="\${$item.getId()}" name="adStatusChange">
        </td>
        <td class="tb_adname" style="">\${name||ad_name}</td>
<!--    <td class="tb_adorder">\${name }</td>-->
        {{html $item.getAdorders()}}
        <td class="tb_adtype">\${$item.getType()}</td>
<!--	<td><a >${page.landing_type == 'download' ? '直接下载' : '详细界面' }</a></td>-->
        <td id="td_level_\${$item.getId()}" style="text-align:center;display: block;" class = "level">
            <div class="pnl">
                <div style="text-align:center;"><span class="lvl i\${prior} prior_img_\${$item.getId()}"></span></div>
                <ul style="padding:10px;">
                        <li>
                            <input type="radio" name="prior_\${$item.getId()}" \${$item.checked(1)}  onclick="$('#prior_input_\${$item.getId()}').val(1);$(this).closest('.pnl').find('.lvl').removeClass('i1 i2 i3').addClass('i1')" id="prior1_\${$item.getId()}"/>
                            <label for="prior1_\${$item.getId()}" >&nbsp;低级</label>
                        </li>
                        <li>
                            <input type="radio" name="prior_\${$item.getId()}" \${$item.checked(2)}  onclick="$('#prior_input_\${$item.getId()}').val(2);$(this).closest('.pnl').find('.lvl').removeClass('i1 i2 i3').addClass('i2')" id="prior2_\${$item.getId()}"/>
                            <label for="prior2_\${$item.getId()}" >&nbsp;中级</label>
                        </li>
                        <li>
                            <input type="radio" name="prior_\${$item.getId()}"  \${$item.checked(3)} onclick="$('#prior_input_\${$item.getId()}').val(3);$(this).closest('.pnl').find('.lvl').removeClass('i1 i2 i3').addClass('i3')" id="prior3_\${$item.getId()}"/>
                            <label for="prior3_\${$item.getId()}" >&nbsp;高级</label>
                        </li>
                </ul>
                <input type="hidden" id="prior_input_\${$item.getId()}" value="\${prior}"/>
                <div class="opr">
                        <span class="btn_save"><a  >保存</a></span>
                        <span class="btn_cancel"><a >取消</a></span>
                </div>
            </div>
            <span class="lvl i\${prior} prior_img_\${$item.getId()}"><a   adid="\${$item.getId()}"></a></span>
        </td>
        <td id="td_status_\${$item.getId()}">
            {{html $item.getStatus()}}
        </td>
        <td class="edit_btn"><a href="javascript:void(0)" class="icon_modify " adpage_id="\${$item.getId()}" >编辑</a></td>
</tr>
</script>

				