package com.wuxianmeihao.service;

import java.util.List;
import java.util.Map;

import com.umeng.core.utils.Page;
import com.wuxianmeihao.core.domain.Movie;
import com.wuxianmeihao.core.domain.MovieGroup;

/**
 * @author ke
 */
public interface MovieGroupService {
	public MovieGroup getById(int id);

    public void saveOrUpdate(MovieGroup movieGroup);
    
    public void saveOrUpdate(MovieGroup movieGroup,List<Movie> movieList);
    
    public Object getByName(String name);
	
    public Page<Map<String, Object>> getMovieGroupListByPage(Page<Map<String, Object>> page);
    
    public List<Map<String, Object>> getMovieListe(Map<String, Object> params);
}
