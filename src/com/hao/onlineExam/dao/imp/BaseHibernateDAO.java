package com.hao.onlineExam.dao.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import com.hao.onlineExam.dao.IBaseDAO;

public abstract class BaseHibernateDAO<T> implements IBaseDAO<T> {
	// Auto get the Class Type of "T"
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseHibernateDAO() {
		this.entityClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	private Class<T> entityClass;
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) this.getSession().get(entityClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getWithLock(Serializable id, LockOptions lockOptions) {
		T t = (T) this.getSession().get(entityClass, id, lockOptions);
		if (t != null)
			this.flush();
		return t;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T) this.getSession().load(entityClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T loadWithLock(Serializable id, LockOptions lockOptions) {
		T t = (T) this.getSession().load(entityClass, id, lockOptions);
		if (t != null)
			this.flush();
		return t;
	}

	@Override
	public List<T> loadAll() {
		String hql = " FROM " + entityClass.getName();
		return queryForList(hql, null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T queryForObject(final String hql, final Object[] params) {
		return (T) ((Criteria) createQuery(hql, params)).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T queryForTopObject(final String hql, final Object[] params) {
		return (T) ((Criteria) createQuery(hql, params).setFirstResult(0).setMaxResults(1)).uniqueResult();
	}
	
	@Override@SuppressWarnings("unchecked")
	public List<T> queryForList(final String hql, final Object[] params) {
		return createQuery(hql, params).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> queryForList(final String hql, final Object[] params, final int recordNum) {
		return ((Criteria) createQuery(hql, params).setFirstResult(0).setMaxResults(recordNum)).list();
	}

	public void setQueryParams(Query query, Object[] params) {
		if (null == params) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
	}

	@Override
	public void update(T entity) {
		if (null == entity)
			return;
		this.getSession().update(entity);
	}

	@Override
	public void save(Collection<T> entity) {
		if (null == entity)
			return;
		for(T t : entity)
		this.getSession().save(t);
	}
	
	@Override
	public void save(T entity) {
		if (null == entity)
			return;
		this.getSession().save(entity);
	}


	@Override
	public void saveOrUpdate(T entity) {
		if (null == entity)
			return;
		this.getSession().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdate(Collection<T> entities) {
		if (null == entities)
			return;
		for (T t : entities) {
			this.getSession().save(t);
		}
	}

	@Override
	public void delete(T entity) {
		this.getSession().delete(entity);
	}

	@Override
	public void deleteByKey(Serializable id) {
		this.getSession().delete(this.load(id));
	}

	@Override
	public void deteleAll(Collection<T> entities) {
		if (null == entities)
			return;
		for (T t : entities) {
			this.getSession().delete(t);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(String queryString) {
		Query query = (Query) this.getSession().createQuery(queryString);
		return query.list();
	}

	@Override
	public List<T> find(String queryString, Object[] objects) {
		return this.queryForList(queryString, objects);
	}

	@Override
	public Iterator<T> iterate(String queryString) {
		return this.find(queryString).iterator();
	}

	@Override
	public Iterator<T> iterate(String queryString, Object[] values) {
		return this.queryForList(queryString, values).iterator();
	}

	@Override
	public void closeIterator(Iterator<T> iterators) {
		this.closeIterator(iterators);
	}

	@Override
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.entityClass);
	}

	@Override
	public Criteria createCriteria() {
		return this.createDetachedCriteria().getExecutableCriteria(getSession());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findEqualByEntity(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		Example example = Example.create(entity);
		example.excludeZeroes();
		String[] defProperties = this.getSessionFactory().getClassMetadata(entityClass).getPropertyNames();
		for (String defProperty : defProperties) {
			int ii = 0;
			for (ii = 0; ii < propertyNames.length; ii++) {
				if (defProperty.equals(propertyNames[ii])) {
					criteria.addOrder(Order.asc(defProperty));
					break;
				}
			}
			if (ii == propertyNames.length) {
				example.excludeProperty(defProperty);
			}
		}
		criteria.add(example);
		return (List<T>) criteria.list();
	}

	@Override
	public Query createQuery(final String queryString) {
		return (Query) this.getSession().createQuery(queryString);
	}

	@Override
	public Query createQuery(final String hql, final Object[] params) {
		Query query = this.createQuery(hql);
		setQueryParams(query, params);
		return query;
	}

	@Override
	public SQLQuery createSqlQuery(String queryString) {
		return this.getSession().createSQLQuery(queryString);
	}

	@Override
	public void flush() {
		this.getSession().flush();
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
