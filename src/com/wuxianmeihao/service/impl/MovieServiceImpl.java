package com.wuxianmeihao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umeng.core.utils.Page;
import com.wuxianmeihao.core.domain.Creatives;
import com.wuxianmeihao.core.domain.Movie;
import com.wuxianmeihao.dao.EntityDaoImpl;
import com.wuxianmeihao.service.MovieService;
import com.wuxianmeihao.utils.StatusUtil;

@Service
public class MovieServiceImpl extends EntityDaoImpl<Movie, Integer> implements
		MovieService {
//	@Resource
//	protected AdHolderService adHolderService;


	@Override
	public Movie getById(int id) {
		return super.get(id);
	}

	
	
	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"})
	public void saveOrUpdate(Movie movie) {	
	
		super.saveOrUpdate(movie);

	}
	
	@Override
	public void deleteByGroupId(int GroupId){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("movieGroupId",GroupId);
		super.delete(param);
	}

	

}
