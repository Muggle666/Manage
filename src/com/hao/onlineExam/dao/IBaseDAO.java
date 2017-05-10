package com.hao.onlineExam.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;

public interface IBaseDAO<T> {

	public T get(Serializable id);

	public T getWithLock(Serializable id, LockOptions lockOptionsOptions);

	public T load(Serializable id);

	public T loadWithLock(Serializable id, LockOptions lockOptions);

	public List<T> loadAll();

	public void update(T entity);
	
	public void save(T entity);
	
	public void save(Collection<T> entity);
	
	public void saveOrUpdate(T entity);
	
	public void saveOrUpdate(Collection<T> entities);
	
	public void delete(T entity);
	
	public void deleteByKey(Serializable id);
	
	public void deteleAll(Collection<T> entities);
	
	/* -----------------------HSQL query and option ----------------------*/
	
	public List<T> find(String queryString);
	
	public List<T> find(String queryString, Object[] objects);
	
	public Iterator<T> iterate(String queryString);
	
	public Iterator<T> iterate(String queryString, Object[] values);
	
	public void closeIterator(Iterator<T> iterator);
	
	public T queryForObject(String hql, Object[] params);
	
	public T queryForTopObject(String hql, Object[] params);
	
	public List<T> queryForList(String hql, Object[] objects);
	
	public List<T> queryForList(final String hql, final Object[] params,final int recordNum);
	
	public DetachedCriteria createDetachedCriteria();
	
	public Criteria createCriteria();
	
	public List<T> findEqualByEntity(T entity, String[] propertyNames);
	
	/* -----------------------Others ----------------------*/
	public void flush();
	
	public Query createQuery(final String queryString);
	
	public Query createQuery(final String hql, final Object[] params);
	
	public SQLQuery createSqlQuery(String queryString);
	
	}

