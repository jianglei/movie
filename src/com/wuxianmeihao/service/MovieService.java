package com.wuxianmeihao.service;

import com.wuxianmeihao.core.domain.Movie;

/**
 * @author ray
 */
public interface MovieService {
	public Movie getById(int id);

    public void saveOrUpdate(Movie movie);
    
    public void deleteByGroupId(int GroupId);
	
}
