package com.wuxianmeihao.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umeng.core.utils.Page;
import com.wuxianmeihao.core.domain.Movie;
import com.wuxianmeihao.core.domain.MovieGroup;
import com.wuxianmeihao.dao.EntityDaoImpl;
import com.wuxianmeihao.service.MovieGroupService;
import com.wuxianmeihao.service.MovieService;

@Service
public class MovieGroupServiceImpl extends EntityDaoImpl<MovieGroup, Integer> implements
	MovieGroupService {
	@Resource
	protected MovieService movieService;


	@Override
	public MovieGroup getById(int id) {
		return super.get(id);
	}
	
	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"})
	public void saveOrUpdate(MovieGroup movieGroup) {	
		super.saveOrUpdate(movieGroup);
	}
	
	@Override
	public Object getByName(String name) {
		return super.findUnique("name", name);
	}
	
	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"})
	public void saveOrUpdate(MovieGroup movieGroup,List<Movie> movieList) {	
		Object exsitGroup = getByName(movieGroup.getName());
		System.out.println(exsitGroup.getClass().getName());
		if("com.wuxianmeihao.core.domain.MovieGroup".equals(exsitGroup.getClass().getName())){
			movieGroup.setId(((MovieGroup)exsitGroup).getId());
			movieService.deleteByGroupId(movieGroup.getId());
		}
		saveOrUpdate(movieGroup);
		for(Movie movie:movieList){
			movie.setMovieGroupId(movieGroup.getId());
			movieService.saveOrUpdate(movie);
		}

	}
	
	@Override
	public Page<Map<String, Object>> getMovieGroupListByPage(
			Page<Map<String, Object>> page) {
		Page<Map<String, Object>> movieGroupPage = super.findPageMap(
				"listQueryPage", page);
		return movieGroupPage;
	}
	
//	@Override
	public List<Map<String, Object>> getMovieListe(Map<String, Object> params) {
		List<Map<String, Object>> movieList = super.query("getList", params);
		return movieList;
	}

}
