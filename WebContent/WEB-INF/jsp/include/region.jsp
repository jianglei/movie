<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

    <table class="country_list">
        <tbody>
            <tr class="tb_title"><td><input id="china" type="checkbox"/><label for="china">中国</label></td></tr>
            <tr class="area_list">
                <td>
                    <span class="sub_title"><input id ="huabei" type="checkbox"/><label for="huabei">华北地区：</label></span>
                    <label for='bj'>北京</label><input type='checkbox' id='bj'/>
                    <label for='tj'>天津</label><input type='checkbox' id='tj'/>
                    <label for='hb'>河北</label><input type='checkbox' id='hb'/>
                    <label for='sx'>山西</label><input type='checkbox' id='sx'/>
                    <label for='nmg'>内蒙古</label><input type='checkbox' id='nmg'/>
                </td>
            </tr>
            <tr class="area_list">
                <td>
                    <span class="sub_title"><input id ="huadong" type="checkbox"/><label for="huadong">华东地区：</label></span>
                    <label for='sh'>上海</label><input type='checkbox' id='sh'/>
                    <label for='js'>江苏</label><input type='checkbox' id='js'/>
                    <label for='zj'>浙江</label><input type='checkbox' id='zj'/>
                    <label for='sd'>山东</label><input type='checkbox' id='sd'/>
                    <label for='ah'>安徽</label><input type='checkbox' id='ah'/>

                </td>
            </tr>
            <tr class="area_list">
                <td>
                    <span class="sub_title"><input id ="dongbei" type="checkbox"/><label for="dongbei">东北地区：</label></span>
                    <label for='ln'>辽宁</label><input type='checkbox' id='ln'/>
                    <label for='jl'>吉林</label><input type='checkbox' id='jl'/>
                    <label for='hlj'>黑龙江</label><input type='checkbox' id='hlj'/>

                </td>
            </tr>
            <tr class="area_list">
                <td>
                    <span class="sub_title"><input id ="huazhong" type="checkbox"/><label for="huazhong">华中地区：</label></span>
                    <label for='hub'>湖北</label><input type='checkbox' id='hub'/>
                    <label for='hun'>湖南</label><input type='checkbox' id='hun'/>
                    <label for='hen'>河南</label><input type='checkbox' id='hen'/>
                    <label for='jx'>江西</label><input type='checkbox' id='jx'/>

                </td>
            </tr>
            <tr class="area_list">
                <td>
                    <span class="sub_title"><input id ="huanan" type="checkbox"/><label for="huanan">华南地区：</label></span>
                    <label for='gd'>广东</label><input type='checkbox' id='gd'/>
                    <label for='gx'>广西</label><input type='checkbox' id='gx'/>
                    <label for='hn'>海南</label><input type='checkbox' id='hn'/>
                    <label for='fj'>福建</label><input type='checkbox' id='fj'/>

                </td>
            </tr>
            <tr class="area_list">
                <td>
                    <span class="sub_title"><input id ="xinan" type="checkbox"/><label for="xinan">西南地区：</label></span>
                    <label for='sc'>四川</label><input type='checkbox' id='sc'/>
                    <label for='cq'>重庆</label><input type='checkbox' id='cq'/>
                    <label for='gz'>贵州</label><input type='checkbox' id='gz'/>
                    <label for='yn'>云南</label><input type='checkbox' id='yn'/>
                    <label for='xz'>西藏</label><input type='checkbox' id='xz'/>

                </td>
            </tr>
            <tr class="area_list">
                <td>
                    <span class="sub_title"><input id ="xibei" type="checkbox"/><label for="xibei">西北地区：</label></span>
                    <label for='ssx'>陕西</label><input type='checkbox' id='ssx'/>
                    <label for='gs'>甘肃</label><input type='checkbox' id='gs'/>
                    <label for='xj'>新疆</label><input type='checkbox' id='xj'/>
                    <label for='qh'>青海</label><input type='checkbox' id='qh'/>
                    <label for='nx'>宁夏</label><input type='checkbox' id='nx'/>

                </td>
            </tr>
            <tr class="area_list">
                <td>
                    <span class="sub_title"><input id ="gangaotai" type="checkbox"/><label for="gangaotai">港澳台地区：</label></span>
                    <label for='hk'>香港</label><input type='checkbox' id='hk'/>
                    <label for='am'>澳门</label><input type='checkbox' id='am'/>
                    <label for='tw'>台湾</label><input type='checkbox' id='tw'/>

                </td>
            </tr>

            <tr class="tb_title">
                <td>
                    <input id="guowai" type="checkbox"/><label for="guowai">国外</label>
                </td>
            </tr>
            <tr class="area_list">
                <td>
                    <label for="us">美国</label><input id ="us" type="checkbox"/>
                    <label for="jp">日本</label><input id ="jp" type="checkbox"/>
                    <label for="other">其他</label><input id ="other" type="checkbox"/>
                </td>
            </tr>
        </tbody>
    </table>
<button class="ui_radio_region_btn ui_radio_region_cancel">取消</button>
<button class="ui_radio_region_btn ui_radio_region_ok">确定</button>
