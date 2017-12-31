package com.zslin.basic.dao;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.zslin.basic.common.PlatformCommonKit;
import com.zslin.basic.idao.IBaseDao;
import com.zslin.basic.model.Pager;
import com.zslin.basic.model.SystemRequest;
import com.zslin.basic.threadlocal.FilterQueryHolder;
import com.zslin.basic.threadlocal.SystemRequestHolder;

@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {

	private SessionFactory sessionFactory;
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;
	
	protected SystemRequest getSystemRequest() {
		SystemRequest sr = SystemRequestHolder.getSystemRequest();
		if(sr==null) sr = new SystemRequest();
		return sr;
	}
	
	public Class<?> getClz() {
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Inject
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	public void update(T t) {
		getSession().update(t);
	}

	public void delete(Integer id) {
		getSession().delete(this.load(id));
	}

	public T load(Integer id) {
		return (T) getSession().load(getClz(), id);
	}
	
	public <N extends Object>List<N> listBySql(String sql,Class<?> clz, boolean hasEntity,Object ...args) {
		return this.listBySql(sql,null, clz, hasEntity, args);
	}
	
	public <N extends Object>Pager<N> findBySql(String sql,Class<?> clz, boolean hasEntity,Object ...args) {
		return this.findBySql(sql, null, clz, hasEntity, args);
	}
	
	public Pager<T> findNoCount(String hql,Object ...args) {
		return this.findNoCount(hql, null, args);
	}
	
	public Pager<T> find(String hql,Object ...args) {
		return this.find(hql, null,args);
	}
	
	public List<T> list(String hql,Object ...args) {
		return this.list(hql, null, args);
	}
	
	public List<?> listObj(String hql,Object ...args) {
		return this.listObj(hql,null, args);
	}
	
	public Object queryObject(String hql,Object ...args) {
		return queryObject(hql, null, args);
	}
	
	/** 通过hql获取对象 */
	public Object queryObject(String hql,
			Map<String, Object> alias,Object ...args) {
		try {
			Query query = getSession().createQuery(hql);
			setAliasParameter(query, alias);
			setParameter(query, args);
			return query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过Hql修改数据
	 * @param hql
	 * @param args
	 */
	public void updateByHql(String hql, Object ...args) {
		try {
			Query query = getSession().createQuery(hql);
			setParameter(query, args);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	public Pager<T> findNoCount(String hql, Map<String, Object> alias,Object ...args) {
		try {
			hql = initQuery(hql);
			hql = initSort(hql);
			//String cq = getCountHql(hql,true);
			//Query cquery = getSession().createQuery(cq);
			Query query = getSession().createQuery(hql);
			//设置别名参数
			setAliasParameter(query, alias);
			//setAliasParameter(cquery, alias);
			//设置参数
			setParameter(query, args);
			//setParameter(cquery, args);
			Pager<T> pages = new Pager<T>();
			setPagers(query,pages);
			List<T> datas = query.list();
			pages.setDatas(datas);
			return pages;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<?> listObj(String hql,Map<String,Object> alias,Object ...args) {
		try {
			hql = initQuery(hql);
			hql = initSort(hql);
			Query query = getSession().createQuery(hql);
			setAliasParameter(query, alias);
			setParameter(query, args);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Pager<T> find(String hql, Map<String, Object> alias,Object ...args) {
		try {
			hql = initQuery(hql);
			hql = initSort(hql);
			String cq = getCountHql(hql,true);
			Query cquery = getSession().createQuery(cq);
			Query query = getSession().createQuery(hql);
			//设置别名参数
			setAliasParameter(query, alias);
			setAliasParameter(cquery, alias);
			//设置参数
			setParameter(query, args);
			setParameter(cquery, args);
			Pager<T> pages = new Pager<T>();
			setPagers(query,pages);
			List<T> datas = query.list();
			pages.setDatas(datas);
			long total = (Long)cquery.uniqueResult();
			pages.setTotal(total);
			return pages;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<T> list(String hql, Map<String, Object> alias,Object ...args) {
		try {
			hql = initQuery(hql);
			hql = initSort(hql);
			Query query = getSession().createQuery(hql);
			setAliasParameter(query, alias);
			setParameter(query, args);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public <N extends Object>Pager<N> findBySql(String sql,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity,Object ...args) {
		try {
			sql = initQuery(sql);
			sql = initSort(sql);
			String cq = getCountHql(sql,false);
			SQLQuery sq = getSession().createSQLQuery(sql);
			SQLQuery cquery = getSession().createSQLQuery(cq);
			setAliasParameter(sq, alias);
			setAliasParameter(cquery, alias);
			setParameter(sq, args);
			setParameter(cquery, args);
			Pager<N> pages = new Pager<N>();
			setPagers(sq, pages);
			if(hasEntity) {
				sq.addEntity(clz);
			} else {
				sq.setResultTransformer(Transformers.aliasToBean(clz));
			}
			List<N> datas = sq.list();
			pages.setDatas(datas);
			long total = ((BigInteger)cquery.uniqueResult()).longValue();
			pages.setTotal(total);
			return pages;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过sql修改数据
	 * @param sql
	 * @param args
	 */
	public void updateBySql(String sql,Object ...args) {
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			setParameter(sqlQuery, args);
			sqlQuery.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	/** 通过Sql语句查询数据 */
	public <N extends Object>List<N> listBySql(String sql,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity,Object ...args) {
		try {
			sql = initQuery(sql);
			sql = initSort(sql);
			SQLQuery sq = getSession().createSQLQuery(sql);
			setAliasParameter(sq, alias);
			setParameter(sq, args);
			if(hasEntity) {
				sq.addEntity(clz);
			} else 
				sq.setResultTransformer(Transformers.aliasToBean(clz));
			return sq.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String initSort(String hql) {
		String order = getSystemRequest().getOrder();
		String sort = getSystemRequest().getSort();
		if(sort!=null&&!"".equals(sort.trim())) {
			hql+=" order by "+sort;
			if(!"desc".equals(order)) hql+=" asc";
			else hql+=" desc";
		}
		return hql;
	}
	
	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query,Map<String,Object> alias) {
		if(alias!=null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					//查询条件是列表
					query.setParameterList(key, (Collection)val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}
	
	/** 设置参数 */
	private void setParameter(Query query,Object[] args) {
		if(args!=null&&args.length>0) {
			int index = 0;
			for(Object arg:args) {
				query.setParameter(index++, arg);
			}
		}
	}
	
	/**
	 * 初始化查询语句，并把从页面传过来的查询条件加上
	 * @param hql
	 * @return
	 */
	private String initQuery(String hql) {
		String query = FilterQueryHolder.getQuery();
		if(!PlatformCommonKit.isEmpty(query)) {
			if(hql.toLowerCase().indexOf("where")<0) {
				query=" where 1=1 "+query;
			}
			int index_flag = hql.toLowerCase().indexOf("group");
			int index = hql.toLowerCase().indexOf("order");
			if(index_flag>=0) {
				StringBuffer sb = new StringBuffer(hql);
				hql = sb.insert(index_flag, " "+query+" ").toString();
			}
			if(index>=0) {
				StringBuffer sb = new StringBuffer(hql);
				hql = sb.insert(index, " "+query+" ").toString();
			} else {
				hql = hql+" "+query;
			}
		}
//		logger.debug(hql);
		return hql;
	}
	
	/** 设置分页 */
	@SuppressWarnings("rawtypes")
	protected void setPagers(Query query,Pager pages) {
		Integer pageSize = getSystemRequest().getPageSize();
		Integer pageOffset = getSystemRequest().getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset = 0;
		if(pageSize==null||pageSize<0) pageSize = 15;
		pages.setOffset(pageOffset);
		pages.setSize(pageSize);
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}
	
	/**
	 * 生成查询数据条数的查询语句，主要用于分页使用
	 * @param hql 查询语句
	 * @param isHql Hql和Sql标记
	 * @return
	 */
	protected String getCountHql(String hql, boolean isHql) {
		String tempHql = hql.toLowerCase();
		
		String e = hql.substring(tempHql.toLowerCase().indexOf("from"));
		
		String c = "select count(*) AS amount "+e;
		if(isHql)
			c = c.replaceAll("fetch", "");
		if(tempHql.toLowerCase().indexOf(" group")>=0 && !isHql) {
			c = "SELECT COUNT(tmp_table.amount) FROM ("+c+") as tmp_table";
		}
		return c;
	}
	
	/**
	 * 排序
	 * @param ids 对象主键列表
	 * @param clz 类名
	 * @param field 序号字段名
	 */
	public void updateSort(Integer[] ids,String clz, String field) {
		int index = 1;
		String hql = "update "+clz+" m set m."+field+"=? where m.id=?";
		for(Integer id:ids) {
			this.updateByHql(hql, new Object[]{index++,id});
		}
	}
}
