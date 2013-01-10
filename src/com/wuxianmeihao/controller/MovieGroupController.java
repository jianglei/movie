package com.wuxianmeihao.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.umeng.core.utils.BaseUtils;
import com.umeng.core.utils.Page;
import com.umeng.core.utils.StringUtil;
import com.wuxianmeihao.core.domain.Movie;
import com.wuxianmeihao.core.domain.MovieGroup;
import com.wuxianmeihao.service.MovieGroupService;
import com.wuxianmeihao.service.MovieService;
import com.wuxianmeihao.utils.Constants;
import com.wuxianmeihao.utils.categoryUtil;

@Controller
@RequestMapping(value = "/movie")
public class MovieGroupController extends MultiActionController {

	@Resource
	private MovieGroupService movieGroupService;

	@Resource
	private MovieService movieService;

	private List<String> list = new ArrayList<String>();

	@RequestMapping
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map<String, Object> queryParma = new HashMap<String, Object>();

		Iterator<Entry<String, Set<String>>> it = Constants.CategoryTypeMap
				.entrySet().iterator();
		while (it.hasNext()) {
			Page<Map<String, Object>> page = new Page<Map<String, Object>>();
			page.setPageSize(12);
			page.setPageNo(1);
			Entry<String, Set<String>> e = it.next();
			String value = e.getValue().toString();
			queryParma.put("categorys", BaseUtils.str2strArray(value.substring(
					1, value.length() - 1)));
			page.setParam(queryParma);
			movieGroupService.getMovieGroupListByPage(page);
			model.addAttribute(e.getKey(), page);
			
			Page<Map<String, Object>> page_each_new = new Page<Map<String, Object>>();
			page_each_new.setPageSize(14);
			page_each_new.setPageNo(1);
			page_each_new.setOrderBy("last_update_time");
			page_each_new.setOrder("asc");
			page_each_new.setParam(queryParma);
			movieGroupService.getMovieGroupListByPage(page_each_new);
			model.addAttribute(e.getKey()+"_new", page_each_new);
		}

		Page<Map<String, Object>> page_new = new Page<Map<String, Object>>();
		page_new.setPageSize(14);
		page_new.setPageNo(1);
		page_new.setOrderBy("last_update_time");
		page_new.setOrder("asc");
		movieGroupService.getMovieGroupListByPage(page_new);
		model.addAttribute("new", page_new);

		return "/movieGroup/index";
	}

	@RequestMapping(value = "/main2")
	@ResponseBody
	public Object indexForMain2(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map<String, Object> queryParma = new HashMap<String, Object>();

		Iterator<Entry<String, Set<String>>> it = Constants.CategoryTypeMap
				.entrySet().iterator();
		while (it.hasNext()) {
			Page<Map<String, Object>> page = new Page<Map<String, Object>>();
			page.setPageSize(8);
			page.setPageNo(1);
			Entry<String, Set<String>> e = it.next();
			String value = e.getValue().toString();
			queryParma.put("categorys", BaseUtils.str2strArray(value.substring(
					1, value.length() - 1)));
			page.setParam(queryParma);
			movieGroupService.getMovieGroupListByPage(page);
			model.addAttribute(e.getKey(), page);
		}

		Page<Map<String, Object>> page_new = new Page<Map<String, Object>>();
		page_new.setPageSize(14);
		page_new.setPageNo(1);
		page_new.setOrderBy("last_update_time");
		page_new.setOrder("asc");
		movieGroupService.getMovieGroupListByPage(page_new);
		model.addAttribute("new", page_new);

		return model;
	}

	@RequestMapping(value = "/main")
	public String indexForMain(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map<String, Object> queryParma = new HashMap<String, Object>();

		Iterator<Entry<String, Set<String>>> it = Constants.CategoryTypeMap
				.entrySet().iterator();
		while (it.hasNext()) {
			Page<Map<String, Object>> page = new Page<Map<String, Object>>();
			page.setPageSize(8);
			page.setPageNo(1);
			Entry<String, Set<String>> e = it.next();
			String value = e.getValue().toString();
			queryParma.put("categorys", BaseUtils.str2strArray(value.substring(
					1, value.length() - 1)));
			page.setParam(queryParma);
			movieGroupService.getMovieGroupListByPage(page);
			model.addAttribute(e.getKey(), page);
		}

		Page<Map<String, Object>> page_new = new Page<Map<String, Object>>();
		page_new.setPageSize(14);
		page_new.setPageNo(1);
		page_new.setOrderBy("last_update_time");
		page_new.setOrder("asc");
		movieGroupService.getMovieGroupListByPage(page_new);
		model.addAttribute("new", page_new);

		return "/movieGroup/index";
	}

	@RequestMapping(value = "/detail")
	@ResponseBody
	public Object getMovieList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			int movieGroupId = Integer.parseInt(request.getParameter("id"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", movieGroupId);
			List<Map<String, Object>> movieList = movieGroupService
					.getMovieListe(params);
			return movieList;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		getResource("http://www.bdzy.cc/list/?0.html", 1);
		for (int i = 2; i < 350; i++) {
			if (getResource("http://www.bdzy.cc/list/?0-" + i + ".html", i)) {
				break;
			}
		}
		for (String info : list) {
			System.out.println(info);
		}
		return "sucess";
	}

	private boolean getResource(String listUrl, int i) {
		int count = 0;
		String info = "";
		String detailInfo = "";
		try {
			// URL uri = new URL("http://www.bdzy.cc/");
			URL uri = new URL(listUrl);
			BufferedReader x = new BufferedReader(new InputStreamReader(uri
					.openStream(), "gbk"));
			String s = "";
			StringBuffer sb = new StringBuffer();
			while ((s = x.readLine()) != null) {
				sb.append(s);
			}
			if (!sb.toString().split("页次:")[1].startsWith("" + i + "/")) {
				return true;
			}
			String[] movieUrlArray = sb.toString().split("<a href=\"/");
			List<String> movieUrlList = new ArrayList<String>();
			for (String movieUrl : movieUrlArray) {
				if (movieUrl.startsWith("detail") && movieUrl.contains("点击进入")) {
					movieUrlList.add(movieUrl.substring(0, movieUrl
							.indexOf("\"")));
				}
			}

			for (String detailUrl : movieUrlList) {
				try {
					URL url = new URL("http://www.bdzy.cc/" + detailUrl);

					x = new BufferedReader(new InputStreamReader(url
							.openStream(), "gbk"));
					StringBuffer sbDetail = new StringBuffer();
					while ((s = x.readLine()) != null) {
						sbDetail.append(s);
					}
					String fullHtml = sbDetail.toString();

					MovieGroup mg = new MovieGroup();

					System.out.println(fullHtml);

					mg.setPicUrl(getLastUrlValue(fullHtml, "<!--影片图片开始代码-->",
							"<!--影片图片结束代码-->"));

					mg.setName(getLastValueWithMarks(fullHtml,
							"<!--影片名称开始代码-->", "<!--影片名称结束代码-->"));
					mg.setActorList(getLastValueWithMarks(fullHtml,
							"<!--影片演员开始代码-->", "<!--影片演员结束代码-->"));
					mg.setDirector(getLastValueWithMarks(fullHtml,
							"<!--影片导演开始-->", "<!--影片导演结束-->"));
					mg.setRemark(getLastValueWithMarks(fullHtml,
							"<!--影片备注开始代码-->", "<!--影片备注结束代码-->"));
					mg.setCategory(getLastValueWithMarks(fullHtml,
							"<!--影片类型开始代码-->", "<!--影片类型结束代码-->"));
					mg.setArea(getLastValueWithMarks(fullHtml,
							"<!--影片地区开始代码-->", "<!--影片地区结束代码-->"));
					mg.setLastUpdateTime(getLastValueWithMarks(fullHtml,
							"<!--影片更新时间开始代码-->", "<!--影片更新时间结束代码-->"));
					mg.setStatus(getLastValueWithMarks(fullHtml,
							"<!--影片状态开始代码-->", "<!--影片状态结束代码-->"));
					mg.setLanguage(getLastValueWithMarks(fullHtml,
							"<!--影片语言开始代码-->", "<!--影片语言结束代码-->"));
					mg.setReleaseTime(getLastValueWithMarks(fullHtml,
							"<!--上映日期开始代码-->", "<!--上映日期结束代码-->"));

					mg.setDescription(getLastValueWithMarks(fullHtml,
							"<!--影片介绍开始代码-->", "<!--影片介绍结束代码-->"));

					String moviesHtml = getLastValueWithOutMarks(fullHtml,
							"<!--播放列表开始代码-->", "<!--播放列表结束代码-->");
					String[] moviesArray = moviesHtml.split("value=");
					Movie movie;
					List<Movie> movieList = new ArrayList<Movie>();
					for (String strMovie : moviesArray) {
						if (strMovie.contains("bdhd:")) {
							strMovie = strMovie.replaceAll("'", "").replaceAll(
									"\"", "").replaceAll("\\[", "|");
							movie = new Movie();
							movie.setUrl(strMovie.substring(0,
									strMovie.indexOf("checked")).trim());
							movie.setName(movie.getUrl().substring(
									movie.getUrl().lastIndexOf("|"),
									movie.getUrl().lastIndexOf(".")).replace(
									"|", ""));
							movieList.add(movie);
						}
					}
					detailInfo = mg.getUrl();
					System.out.println("-----start--------" + mg.getName());
					movieGroupService.saveOrUpdate(mg, movieList);
					System.out.println("-----end--------" + mg.getName());
					count++;
					if (count % 100 == 0 && count / 100 > 0) {
						System.out.println("已经插入了" + count + "条电影");
					}
				} catch (Exception e) {
					list.add(detailInfo);
					continue;
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			list.add(info);
		}
		return false;
	}

	@RequestMapping(value = "category")
	public String category(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Integer pageNo = 1;
		Integer category = 1;
		if (null != request.getParameter("pageNo"))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		String year = request.getParameter("year");
		String area = request.getParameter("area");
		
		String name = null;
		if(null != request.getParameter("name")){
			name = request.getParameter("name");
			category = null;
		}else{
			if (null != request.getParameter("category"))
				category = Integer.parseInt(request.getParameter("category"));
		}

		Page<Map<String, Object>> page_category = new Page<Map<String, Object>>();
		page_category.setPageSize(18);
		page_category.setPageNo(pageNo);
		page_category.setOrderBy("last_update_time");
		page_category.setOrder("asc");

		Map<String, Object> param = new HashMap<String, Object>();
		if(category != null){
			param.put("category", categoryUtil.getCategory(category));
		}
		if(name != null){
			param.put("name", "%"+name+"%");
			System.out.println(name);
		}
		param.put("releaseTime", year);
		param.put("area", area);

		page_category.setParam(param);
		movieGroupService.getMovieGroupListByPage(page_category);
		model.addAttribute("category", page_category);
		model.addAttribute("name", categoryUtil.getCategory(category));
		model.addAttribute("search_name", name);
		return "/movieGroup/search";
	}

	@RequestMapping(value = "/movieDetail")
	public String getMovieDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			int movieGroupId = Integer.parseInt(request.getParameter("id"));
			MovieGroup movieGroup = movieGroupService.getById(movieGroupId);
			List<Movie> movies = movieService.getByMovieGroupId(movieGroupId);
			model.addAttribute("movieGroup", movieGroup);
			model.addAttribute("movies", movies);

			Page<Map<String, Object>> page_suggest = new Page<Map<String, Object>>();
			page_suggest.setPageSize(15);
			page_suggest.setPageNo(1);
			movieGroupService.getMovieGroupListByPage(page_suggest);
			model.addAttribute("pageSuggest", page_suggest);
			return "/movieGroup/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			int movieId = Integer.parseInt(request.getParameter("id"));
			Movie movie = movieService.getById(movieId);
			model.addAttribute("movie", movie);

			MovieGroup movieGroup = movieGroupService.getById(movie
					.getMovieGroupId());

			Page<Map<String, Object>> page_suggest = new Page<Map<String, Object>>();
			page_suggest.setPageSize(15);
			page_suggest.setPageNo(1);

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("category", movieGroup.getCategory());

			movieGroupService.getMovieGroupListByPage(page_suggest);
			model.addAttribute("pageSuggest", page_suggest);
			return "/movieGroup/view";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private String getLastUrlValue(String fullHtml, String start, String end) {
		return fullHtml.substring(fullHtml.indexOf(start),
				fullHtml.indexOf(end)).replaceAll(start, "");
	}

	private String getLastValueWithMarks(String fullHtml, String start,
			String end) {
		return fullHtml.substring(fullHtml.indexOf(start),
				fullHtml.indexOf(end)).replaceAll(start, "").replaceAll("\"",
				"").replaceAll("<div class=intro>", "")
				.replaceAll("</div>", "");

	}

	private String getLastValueWithOutMarks(String fullHtml, String start,
			String end) {
		return fullHtml.substring(fullHtml.indexOf(start),
				fullHtml.indexOf(end)).replaceAll(start, "");

	}
}
