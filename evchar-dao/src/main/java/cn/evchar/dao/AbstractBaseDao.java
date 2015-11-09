package cn.evchar.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

public abstract class AbstractBaseDao<M extends Serializable, PK extends Serializable>
		extends HibernateDaoSupport {
	private Class<M> entityClass;
	private String HQL_LIST_ALL;
	private String HQL_COUNT_ALL;
	private String entityName;

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {// 通过初始化方法在依赖注入完毕时生成HQL
		// 1、通过反射获取注解“M”（即模型对象）的类类型
		this.entityClass = (Class<M>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		// 2、得到模型对象的实体名
		entityName = getSessionFactory().getClassMetadata(this.entityClass)
				.getEntityName();
		// 3、根据实体名生成HQL
		HQL_LIST_ALL = "from " + entityName;
		HQL_COUNT_ALL = " select count(*) from " + entityName;
	}

	protected List findByHql(String queryString, Object... value) {
		return getHibernateTemplate().find(queryString, value);

	}

	protected String getListAllHql() {// 获取查询所有记录的HQL
		return HQL_LIST_ALL;
	}

	protected String getCountAllHql() {// 获取统计所有记录的HQL
		return HQL_COUNT_ALL;
	}

	@SuppressWarnings("unchecked")
	public PK save(M model) {
		return (PK) getHibernateTemplate().save(model);
	}

	public void saveOrUpdate(M model) {
		getHibernateTemplate().saveOrUpdate(model);
	}

	public void update(M model) {
		getHibernateTemplate().update(model);
	}

	public void merge(M model) {
		getHibernateTemplate().merge(model);
	}

	public void delete(PK id) {
		getHibernateTemplate().delete(this.get(id));
	}

	public M get(PK id) {
		return getHibernateTemplate().get(this.entityClass, id);
	}

	public int countAll() {
		Number total = unique(getCountAllHql());
		return total.intValue();
	}

	/**
	 * 查找全部
	 */
	public <T> List<T> loadAll(final Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 按主键查找
	 */
	public <T> T get(Class<T> entityClass, final Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 按模版查找，只能用于简单属性，不适用于外键关联的属性
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByExample(final Class<T> entityClass,
			final Object exampleEntity) {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		return (List<T>) getHibernateTemplate().findByExample(
				entityClass.getName(), exampleEntity);

	}

	/** 按属性查找唯一实体 **/
	@SuppressWarnings("unchecked")
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		//TODO: 会引起session泄露的方法，待fix
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 根据查询条件返回唯一一条记录
	 * 
	 * @param <T>
	 *            返回类型
	 * @param hql
	 *            Hibernate查询语句
	 * @param paramlist
	 *            参数列表
	 * @return 返回唯一记录
	 */
	@SuppressWarnings("unchecked")
	protected <T> T unique(final String hql, final Object... paramlist) {
		return getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if (paramlist != null) {
					for (int i = 0; i < paramlist.length; i++) {
						query.setParameter(i, paramlist[i]);
					}
				}
				return (T) query.setMaxResults(1).uniqueResult();
			}
		});
	}

	private <T> Criteria createCriteria(Class<T> entityClass,
			Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	// 省略部分可选的便利方法，想了解更多请参考源代码
	/**
	 * 列表查询
	 * 
	 * @param <T>
	 *            模型类型
	 * @param hql
	 *            Hibernate查询语句
	 * @param pn
	 *            页码 从1开始
	 * @param pageSize
	 *            每页记录数
	 * @param map
	 *            命名参数Map
	 * @return 模型列表
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> list(final String hql, final int pn,
			final int pageSize, final Object... paramlist) {
		List<T> resultList = (List<T>) getHibernateTemplate().executeFind(
				new HibernateCallback<List<T>>() {

					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						// for (Entry<String, Collection<?>> e : map.entrySet())
						// {
						// query.setParameterList(e.getKey(), e.getValue());
						// }
						int i = 0;
						for (Object val : paramlist) {
							query.setParameter(i, val);
							i++;
						}
						if (pn > -1 && pageSize > -1) {
							query.setMaxResults(pageSize);
							int start = (pn - 1) * pageSize;
							if (start != 0) {
								query.setFirstResult(start);
							}
						}
						if (pn < 0) {
							query.setFirstResult(0);
						}
						List<T> results = query.list();
						return results;
					}
				});
		return resultList;
	}
}
