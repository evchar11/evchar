package cn.evchar.service.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public interface CommonService {

	public <T> void save(T entity);

	public <T> void saveOrUpdate(T entity);

	public <T> void delete(T entity);

	public <T> void deleteEntityById(Class<T> entityName, Serializable id);

	public <T> T get(Class<T> clazz, Serializable id);

	public <T> List<T> loadAll(final Class<T> entityClass);

	public Session getSession();

	public <T> List<T> findByExample(final Class<T> entityClass,
			final Object exampleEntity);
}
