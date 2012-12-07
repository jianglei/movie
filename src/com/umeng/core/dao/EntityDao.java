package com.umeng.core.dao;

import java.io.*;
import java.util.*;

import com.umeng.core.utils.*;

public interface EntityDao<E, PK extends Serializable> {

    public Number count();

    public Number count(final Map<String, Object> param);

    public Number count(final String postfix, final Map<String, Object> param);

    public void delete(final Map<String, Object> id);

    public void delete(final PK id);

    public List<E> find(final Map<String, Object> map);

    public List<E> find(final String postfix, final Map<String, Object> map);

    public List<E> findAll();

    public List<E> findAll(final String postfix);

    public List<E> findBy(final String propertyName, final Object value);
    
    public List<Integer> findIds(final String propertyName, final Object value);

    public List<E> findBy(final String postfix, final String propertyName, final Object value);

    public Page<E> findPage(final Page<E> page);

    public Page<E> findPage(final String postfix, final Page<E> page);
    
    public Page<Map<String, Object>> findPageMap(final String postfix, final Page<Map<String, Object>> page);

    public E findUnique(final String propertyName, final Object value);

    public E get(final PK id);
    
    public Map<String, Object> getMapById(final String postfix, final PK id);
    
    public List<Map<String, Object>> getMapListByMap(final String postfix, final Map<String, Object> param);

    public int insert(final E entity);

    public List<Map<String, Object>> query(final String postfix, final Map<String, Object> map);

    public void remove(final Map<String, Object> map);

    public void remove(final PK id);

    public void remove(final String postfix, final Map<String, Object> map);

    public void saveOrUpdate(final E entity);

    public void update(final E entity);

    public void update(final String postfix, final Map<String, Object> param);

    public void updateByMap(final Map<String, Object> param);
    
    public void updateByKey(final Map<String, Object> param);
    
    public void updateStatusByIds(String ids, String status);

}
