package com.wuxianmeihao.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wuxianmeihao.core.domain.Creatives;
import com.wuxianmeihao.service.CreativesService;

@Controller
@RequestMapping(value = "/ad")
public class CreativesController extends MultiActionController {

    public static final String ALLOWED_LOGIN_ADDR = "adpm";
    
    @Resource
    private CreativesService creativeService;

    
    @RequestMapping
    @ResponseBody
    public List<Map<String, Object>> index(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
    	Map<String, Object> param = new HashMap<String,Object>();
    	param.put("status", "0");
    	List<Map<String, Object>> resultParams = creativeService.getAdListByMap(param);
        return resultParams;
    }
    
    @RequestMapping(value = "/list")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
    		
    	List<Map<String, Object>> resultParams = creativeService.getAdListByMap(null);
        
    	model.addAttribute("result",resultParams);
    	return "/ad/creative_list";
    }
    
    @RequestMapping(value = "/changes")
    public String updateStatus(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
    	
    	String ids = request.getParameter("ids");
    	String[] idArray = ids.split(",");
    	String paramName = request.getParameter("paramName");
    	for(String id : idArray){
	    	Creatives c = creativeService.getById(Integer.parseInt(id));
	    	if(paramName != null && paramName.equals("status")){
	    		c.setStatus(Integer.parseInt(request.getParameter("value")));
	    	}else{
	    		c.setIsDel(Integer.parseInt(request.getParameter("value")));
	    	}
	    	creativeService.saveOrUpdate(c);
    	}
    	List<Map<String, Object>> resultParams = creativeService.getAdListByMap(null);
        
    	model.addAttribute("result",resultParams);
    	return "/ad/creative_list";
    }
    
    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Model model) {
           	
    	Creatives c = new Creatives();
    	c.setName(request.getParameter("name"));
    	c.setClickUrl(request.getParameter("clickUrl"));
    	c.setIsDel(0);
    	c.setStatus(1);
    	c.setPic(request.getParameter("pic"));
    	c.setAdDesc(request.getParameter("adDesc"));
    	creativeService.saveOrUpdate(c);
    	return "redirect:/ad/list";
    }
    
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response, Model model) {
       
    	String id = request.getParameter("id");
    	if(id != null){
	    	Creatives c = creativeService.getById(Integer.parseInt(id));
	    	model.addAttribute("result",c);
    	}
    	return "/ad/creative";
    }
        
}
