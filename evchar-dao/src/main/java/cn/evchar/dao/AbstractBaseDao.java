package cn.evchar.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import cn.evchar.common.entity.AbstractEntity;

import com.google.common.collect.Lists;

@SuppressWarnings("unchecked")
public abstract class AbstractBaseDao<T extends AbstractEntity> extends HibernateDaoSupport {


	/**
	 * 按主键查询
	 * @param id
	 * @return
	 */
	public T findById(Serializable id) {
		Object obj = this.getHibernateTemplate().get(this.getEntityClass(), id);
		return (obj != null) ? (T) obj : null;
	}


	/**
	 * 按主键查询
	 * @param id
	 * @param upgrade lock级别
	 * @return
	 */
	public T findById(Serializable id, LockMode upgrade) {
		Object obj = null;
		if (upgrade != null) {
			obj = this.getHibernateTemplate().get(this.getEntityClass(), id, upgrade);
		} else {
			obj = this.getHibernateTemplate().get(this.getEntityClass(), id);
		}
		return (obj != null) ? (T) obj : null;
	}


	/**
	 * 保存对象
	 * @param obj
	 * @return 主键
	 */
	public Serializable save(T obj) {
		checkEntity(obj);
		return getHibernateTemplate().save(obj);
	}


	/**
	 * 保存或更新
	 * @param obj
	 */
	public void saveOrUpdate(T obj) {
		checkEntity(obj);
		getHibernateTemplate().saveOrUpdate(obj);
	}
	
	/**
	 * 更新
	 * @param obj
	 */
	public void update(T obj) {
		checkEntity(obj);
		getHibernateTemplate().update(obj);
	}

	
	/**
	 * 删除
	 * @param obj
	 */
	public void remove(T obj) {
		checkEntity(obj);
		this.getHibernateTemplate().delete(obj);
	}

	
	private Class<?> getGenericClass(Class<?> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (genType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
			if ((params != null) && (params.length >= (index - 1))) {
				return (Class<?>) params[index];
			}
		}// end if.
		return null;
	}

	protected Class<?> getEntityClass() {
		return this.getGenericClass(this.getClass(), 0);
	}


	
	/**
	 * 批量保存
	 * @param cols
	 * @return id列表
	 */
	public List<Serializable> batchSave(Collection<T> cols) {
		List<Serializable> idList = Lists.newArrayList();
		for (T obj : cols){
			checkEntity(obj);
			idList.add(save(obj));
		}
		return idList;
	}

	/**
	 * 批量更新保存
	 * @param cols
	 */
	public void batchSaveOrUpdate(Collection<T> cols) {
		for (T obj : cols){
			checkEntity(obj);
			saveOrUpdate(obj);
		}
	}
	
	/**
	 * 校验Object
	 * @param obj
	 */
	private void checkEntity(T obj) {
		Assert.notNull(obj);
		Assert.isTrue(obj instanceof AbstractEntity, "type not match");
	}
}
