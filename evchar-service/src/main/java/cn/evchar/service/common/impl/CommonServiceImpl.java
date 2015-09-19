package cn.evchar.service.common.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.evchar.service.common.CommonService;

//TODO:待实现通用service
@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {


	@Override
	public <T> void save(T entity) {
	}

	@Override
	public <T> void saveOrUpdate(T entity) {

	}

	@Override
	public <T> void delete(T entity) {

	}

	@Override
	public <T> void deleteEntityById(Class<T> entityName, Serializable id) {

	}

	@Override
	public <T> T get(Class<T> class1, Serializable id) {
		return null;
	}

	@Override
	public <T> List<T> loadAll(Class<T> entityClass) {
		return null;
	}

	@Override
	public Session getSession() {
		return null;
	}

	@Override
	public <T> List<T> findByExample(Class<T> entityClass, Object exampleEntity) {
		return null;
	}

}
