package com.umeng.core.dao.impl;

import static com.umeng.core.utils.BaseUtils.createQueryParam;
import static com.umeng.core.utils.BaseUtils.notEmpty;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_COUNT;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_DELETE;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_FIND;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_FINDALL;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_FINDPAGE;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_FINDUNIQUE;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_GET;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_INSERT;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_REMOVE;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_UPDATE;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_UPDATEBYKEY;
import static com.umeng.core.utils.MyBatisPostfixs.POSTFIX_UPDATEBYMAP;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.umeng.core.dao.EntityDao;
import com.umeng.core.utils.BaseUtils;
import com.umeng.core.utils.GenericsUtils;
import com.umeng.core.utils.Page;
import com.umeng.core.utils.PageUtil;

@Component
public abstract class AbstractEntityDaoImpl<E, PK extends Serializable> implements EntityDao<E, PK> {

    private Map<String, Object> emptyParam = new HashMap<String, Object>();

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract SqlSessionTemplate getSqlSessionTemplate();

    public Class<E> entityClass;

    public String primaryKeyName;

    @SuppressWarnings("unchecked")
    public AbstractEntityDaoImpl() {
        entityClass = GenericsUtils.getSuperClassGenricType(getClass());
    }

    @Override
    public Number count() {
        return count(emptyParam);
    }

    @Override
    public Number count(final Map<String, Object> param) {
        return count(POSTFIX_COUNT, param);
    }

    @Override
    public Number count(final String postfix, final Map<String, Object> param) {
        Assert.notNull(postfix, "Postfix");
        return (Number) getSqlSessionTemplate().selectOne(getFullPostfix(postfix), param);
    }

    @Override
    public void delete(Map<String, Object> param) {
    	getSqlSessionTemplate().delete(getFullPostfix(POSTFIX_DELETE), param);
    }

    @Override
    public void delete(PK id) {
    	getSqlSessionTemplate().delete(getFullPostfix(POSTFIX_DELETE), id);
    }

    @Override
    public List<E> find(final Map<String, Object> param) {
        return find(POSTFIX_FIND, param);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> find(final String postfix, final Map<String, Object> map) {
        Assert.notNull(postfix, "Postfix");

        List<E> result = (List<E>) getSqlSessionTemplate().selectList(getFullPostfix(postfix), map);
        return result != null ? result : new ArrayList<E>();
    }

    @Override
    public List<E> findAll() {
        return findAll(POSTFIX_FINDALL);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll(String postfix) {
        Assert.notNull(postfix, "postfix must be not null");
        List<E> result = (List<E>) getSqlSessionTemplate().selectList(getFullPostfix(postfix));
        return result != null ? result : new ArrayList<E>();
    }

    @Override
    public List<E> findBy(final String propertyName, final Object value) {
        Map<String, Object> param = createQueryParam(propertyName, value);
        return find(param);
    }
    @Override
    public List<Integer> findIds(final String propertyName, final Object value) {
    	Map<String, Object> param = createQueryParam(propertyName, value);
        List<Integer> result = (List<Integer>) getSqlSessionTemplate().selectList(getFullPostfix("findIds"), param);
        return result != null ? result : new ArrayList<Integer>();
    }

    @Override
    public List<E> findBy(final String postfix, String propertyName, Object value) {
        Map<String, Object> param = createQueryParam(propertyName, value);
        return find(postfix, param);
    }

    @Override
    public Page<E> findPage(Page<E> page) {
        return findPage(POSTFIX_FINDPAGE, page);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<E> findPage(final String postfix, Page<E> page) {
        Number total = pageCount(postfix, page.getParam());
        if (total != null && total.longValue() > 0) {
            page.setTotalItems(total.longValue());
            
            List<E> result = (List<E>) getSqlSessionTemplate().selectList(getFullPostfix(postfix),
                    PageUtil.toParameterMap(page));
            page.setResult(result);
        }
        return page;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Page<Map<String, Object>> findPageMap(final String postfix, final Page<Map<String, Object>> page){
        Number total = pageCount(postfix, page.getParam());
        if (total != null && total.longValue() > 0) {
            page.setTotalItems(total.longValue());
            List<Map<String, Object>> result = (List<Map<String, Object>>) getSqlSessionTemplate().selectList(getFullPostfix(postfix),
                    PageUtil.toParameterMap(page));
            page.setResult(result);
        }
        return page;
    }
    @SuppressWarnings("unchecked")
    @Override
    public E findUnique(final String propertyName, final Object value) {
        Map<String, Object> queryParam = createQueryParam(propertyName, value);
        List<E> results = (List<E>) getSqlSessionTemplate().selectList(
                getFullPostfix(POSTFIX_FINDUNIQUE), queryParam);
        return (E) (notEmpty(results) ? results.get(0) : new Object());
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(final PK id) {
        return (E) getSqlSessionTemplate().selectOne(getFullPostfix(POSTFIX_GET), id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getMapById(final String postfix, final PK id) {
        return (Map<String, Object>) getSqlSessionTemplate().selectOne(getFullPostfix(postfix), id);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getMapListByMap(final String postfix, final Map<String, Object> param) {
    	return (List<Map<String, Object>>) getSqlSessionTemplate().selectList(getFullPostfix(postfix), param);
    }
    
    @Override
    public int insert(E entity) {
        Assert.notNull(entity, "entity");
        int id = getSqlSessionTemplate().insert(getFullPostfix(POSTFIX_INSERT), entity);
        logger.debug("insert entity: {}", entity);
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> query(String postfix, Map<String, Object> paramMap) {
        List<Map<String, Object>> result = (List<Map<String, Object>>) getSqlSessionTemplate()
                .selectList(getFullPostfix(postfix), paramMap);
        return result != null ? result : new ArrayList<Map<String, Object>>();
    }
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryBySimpleSql(String postfix, Map<String, Object> paramMap) {
        List<Map<String, Object>> result = (List<Map<String, Object>>) getSqlSessionTemplate()
                .selectList(postfix, paramMap);
        return result != null ? result : new ArrayList<Map<String, Object>>();
    }

    @Override
    public void remove(final Map<String, Object> map) {
        remove(POSTFIX_REMOVE, map);
    }

    @Override
    public void remove(final PK id) {
        Assert.notNull(id, "id is null");
        getSqlSessionTemplate().update(getFullPostfix(POSTFIX_REMOVE), id);
        logger.debug("remove entity id: {}", id);
    }

    @Override
    public void remove(final String postfix, final Map<String, Object> map) {
        Assert.notNull(postfix, "Postfix is null");
        update(postfix, map);
        logger.debug("remove entity param: {}", map);
    }

    @Override
    public void saveOrUpdate(final E entity) {
        Object primaryKey = getPrimaryKeyValue(entity);
        if (primaryKey == null || (Integer) primaryKey == 0) {
            insert(entity);
        } else {
            update(entity);
        }
    }
    
    public void saveOrUpdateObject(Object obj){
    	Object primaryKey = getPrimaryKeyValue(obj);
        if (primaryKey == null || (Integer) primaryKey == 0) {
        	String prefix = getFullPostfix(POSTFIX_INSERT,obj);
        	getSqlSessionTemplate().insert(prefix, obj);
        } else {
        	getSqlSessionTemplate().update(getFullPostfix(POSTFIX_UPDATE,obj), obj);
        }
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    @Override
    public void update(final E entity) {
    	getSqlSessionTemplate().update(getFullPostfix(POSTFIX_UPDATE), entity);
        logger.debug("update entity: {}", entity);
    }

    @Override
    public void update(final String postfix, Map<String, Object> param) {
    	getSqlSessionTemplate().update(getFullPostfix(postfix), param);
        logger.debug("update entity: {}", param);
    }

    @Override
    public void updateByMap(Map<String, Object> param) {
        update(POSTFIX_UPDATEBYMAP, param);
    }

    @Override
    public void updateByKey(Map<String, Object> param) {
        update(POSTFIX_UPDATEBYKEY, param);
    }

    protected String getFullPostfix(final String postfix) {
        Assert.notNull(postfix);
        StringBuilder fullPostfix = new StringBuilder();
        fullPostfix.append(entityClass.getSimpleName());
        fullPostfix.append(".");
        fullPostfix.append(postfix);
        return fullPostfix.toString();
    }
    
    private String getFullPostfix(final String postfix,Object obj) {
        Assert.notNull(postfix);
        StringBuilder fullPostfix = new StringBuilder();
        fullPostfix.append(obj.getClass().getSimpleName());
        fullPostfix.append(".");
        fullPostfix.append(postfix);
        return fullPostfix.toString();
    }

    private Number pageCount(final String postfix, final Map<String, Object> param) {
        Assert.notNull(postfix, "Postfix is null");
        return count(postfix + POSTFIX_COUNT, param);
    }

    protected Object getPrimaryKeyValue(Object o) {
        try {
            return PropertyUtils.getProperty(o, "id");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
   /* 
    public void updateStatusByIds(String ids, String name,
			int value) {
		List<Integer> listids = BaseUtils.str2list(ids);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("listids", listids);
        map.put(name, value);
        updateByMap(map);
		
	}*/
    public void updateStatusByIds(String ids, String status) {
		List<Integer> listids = BaseUtils.str2list(ids);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("listids", listids);
        map.put("status", status);
        updateByMap(map);
	}
    public void updateStatusByKey(String idField, Integer id, String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(idField, id);
        map.put("status", status);
        updateByKey(map);
	}

}