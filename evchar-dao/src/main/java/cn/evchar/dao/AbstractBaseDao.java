package cn.evchar.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractBaseDao<M extends Serializable, PK extends Serializable> extends HibernateDaoSupport {  
	private Class<M> entityClass;  
    private String HQL_LIST_ALL;  
    private String HQL_COUNT_ALL;  
    private String entityName;
    
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
     super.setSessionFactory(sessionFactory);
    }
    @SuppressWarnings("unchecked")  
    public void init() {//通过初始化方法在依赖注入完毕时生成HQL  
        //1、通过反射获取注解“M”（即模型对象）的类类型  
    	this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];  
        //2、得到模型对象的实体名  
        entityName = getSessionFactory().getClassMetadata(this.entityClass).getEntityName();  
        //3、根据实体名生成HQL  
        HQL_LIST_ALL = "from " + entityName;  
        HQL_COUNT_ALL = " select count(*) from " + entityName;  
    } 
    
    @SuppressWarnings("unchecked")
	protected List<M> findByHql(String queryString, Object... value){
    	return (List<M>)getHibernateTemplate().find(queryString, value);
    }
    protected String getListAllHql() {//获取查询所有记录的HQL  
        return HQL_LIST_ALL;  
    }  
    protected String getCountAllHql() {//获取统计所有记录的HQL  
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

   
   /**  根据查询条件返回唯一一条记录 
     * @param <T> 返回类型 
     * @param hql Hibernate查询语句 
     * @param paramlist 参数列表 
     * @return 返回唯一记录 */  
    @SuppressWarnings("unchecked")  
    protected <T> T unique(final String hql, final Object... paramlist) {  
        return getHibernateTemplate().execute(new HibernateCallback<T>() {  
            public T doInHibernate(Session session) throws HibernateException, SQLException {  
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
   //省略部分可选的便利方法，想了解更多请参考源代码  
} 