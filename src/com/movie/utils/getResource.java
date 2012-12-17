package com.movie.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.wuxianmeihao.core.domain.Movie;
import com.wuxianmeihao.core.domain.MovieGroup;



public class getResource {
	
	public static void main(String[] args){
		try {
			URL uri = new URL("http://www.bdzy.cc/list/?0-2.html");
	        BufferedReader x = new BufferedReader(new InputStreamReader(uri.openStream(),"gbk"));
	        String s = "";
	        StringBuffer sb = new StringBuffer();
	        while((s = x.readLine()) != null){
	        	sb.append(s);
	        }
	        System.out.println(sb.toString());
	        System.out.println(sb.toString().split("页次:")[1]);
	        String[] movieUrlArray = sb.toString().split("<a href=\"/");
	        List<String> movieUrlList = new ArrayList<String>();
	        int m = 0;
	        for(String movieUrl : movieUrlArray){
	        	if(movieUrl.startsWith("detail") && movieUrl.contains("点击进入")){
	        		movieUrlList.add(movieUrl.substring(0, movieUrl.indexOf("\"")));
	        		m++;
	        	}
	        }
	        
	        URL url = new URL("http://www.bdzy.cc/detail/?16546.html");
	        x = new BufferedReader(new InputStreamReader(url.openStream(),"gbk"));
	        StringBuffer sbDetail = new StringBuffer();
	        while((s = x.readLine()) != null){
	        	sbDetail.append(s);
	        }
	        String fullHtml = sbDetail.toString();
	        System.out.println(fullHtml);
	        
	        MovieGroup mg = new MovieGroup();
	        
	        mg.setPicUrl(getLastUrlValue(fullHtml,"<!--影片图片开始代码-->","<!--影片图片结束代码-->"));
	        
	        mg.setName(getLastValueWithMarks(fullHtml,"<!--影片名称开始代码-->","<!--影片名称结束代码-->"));
	        mg.setActorList(getLastValueWithMarks(fullHtml,"<!--影片演员开始代码-->","<!--影片演员结束代码-->"));
	        mg.setDirector(getLastValueWithMarks(fullHtml,"<!--影片导演开始-->","<!--影片导演结束-->"));
	        mg.setRemark(getLastValueWithMarks(fullHtml,"<!--影片备注开始代码-->","<!--影片备注结束代码-->"));
	        mg.setCategory(getLastValueWithMarks(fullHtml,"<!--影片类型开始代码-->","<!--影片类型结束代码-->"));
	        mg.setArea(getLastValueWithMarks(fullHtml,"<!--影片地区开始代码-->","<!--影片地区结束代码-->"));
	        mg.setLastUpdateTime(getLastValueWithMarks(fullHtml,"<!--影片更新时间开始代码-->","<!--影片更新时间结束代码-->"));
	        mg.setStatus(getLastValueWithMarks(fullHtml,"<!--影片状态开始代码-->","<!--影片状态结束代码-->"));
	        mg.setLanguage(getLastValueWithMarks(fullHtml,"<!--影片语言开始代码-->","<!--影片语言结束代码-->"));
	        mg.setReleaseTime(getLastValueWithMarks(fullHtml,"<!--上映日期开始代码-->","<!--上映日期结束代码-->"));
	        
	        mg.setDescription(getLastValueWithMarks(fullHtml,"<!--影片介绍开始代码-->","<!--影片介绍结束代码-->"));
	        
	        String moviesHtml = getLastValueWithOutMarks(fullHtml,"<!--播放列表开始代码-->","<!--播放列表结束代码-->");
//	        System.out.println(moviesHtml);
	        String[] moviesArray = moviesHtml.split("value=");
	        Movie movie;
	        
	        for(String strMovie : moviesArray){
	        	if(strMovie.contains("bdhd:")){
	        		
	        		strMovie = strMovie.replaceAll("'", "").replaceAll("\"", "").replaceAll("\\[", "|");
		        	movie = new Movie();
		        	movie.setUrl(strMovie.substring(0, strMovie.indexOf("checked")).trim());
		        	movie.setName(movie.getUrl().substring(movie.getUrl().lastIndexOf("|"), movie.getUrl().lastIndexOf(".")).replace("|", ""));
		        	System.out.println(movie.getName());
		        	System.out.println(movie.getUrl());
	        	}
	        }
	        
	        System.out.println(mg.getPicUrl());
	        System.out.println(mg.getName());
	        System.out.println(mg.getActorList());
	        System.out.println(mg.getDirector());
	        System.out.println(mg.getRemark());
	        System.out.println(mg.getCategory());
	        System.out.println(mg.getArea());
	        System.out.println(mg.getLastUpdateTime());
	        System.out.println(mg.getStatus());
	        System.out.println(mg.getLanguage());
	        System.out.println(mg.getReleaseTime());
	        System.out.println(mg.getDescription());
	        
	        
	        
		} catch (Exception e) {
			System.out.print("-----------error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         
	}
	
	public static String getLastUrlValue(String fullHtml,String start,String end){
		return fullHtml.substring(fullHtml.indexOf(start), fullHtml.indexOf(end)).replaceAll(start, "");
//		return with_prifex.substring(with_prifex.indexOf("http"));
	}
	
	public static String getLastValueWithMarks(String fullHtml,String start,String end){
		return fullHtml.substring(fullHtml.indexOf(start), fullHtml.indexOf(end))
		.replaceAll(start, "")
		.replaceAll("\"", "")
		.replaceAll("<div class=intro>", "")
		.replaceAll("</div>", "");
		
	}
	
	public static String getLastValueWithOutMarks(String fullHtml,String start,String end){
		return fullHtml.substring(fullHtml.indexOf(start), fullHtml.indexOf(end))
		.replaceAll(start, "");
		
		
	}
}
