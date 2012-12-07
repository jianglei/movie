package com.umeng.core.dao.impl;

import java.io.Serializable;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO: better use DaoImpl in your project

@Component
public class EntityDaoImplDemo<E, PK extends Serializable> extends AbstractEntityDaoImpl<E, PK> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

	@Override
	protected SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

}