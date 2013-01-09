package com.wuxianmeihao.service;

import java.util.List;
import java.util.Map;

import com.umeng.core.utils.Page;
import com.wuxianmeihao.core.domain.Movie;

/**
 * @author ray
 */
public interface MovieService {
	public Movie getById(int id);

	public void saveOrUpdate(Movie movie);

	public void deleteByGroupId(int GroupId);

	public List<Movie> getByMovieGroupId(int GroupId);
	
	public Page<Map<String, Object>> getMovieListByPage(Page<Map<String, Object>> page);

}
