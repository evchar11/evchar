package cn.evchar.dao.user;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.user.User;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class UserDao extends AbstractBaseDao<User, Long>{
	
	@Autowired
    private SessionFactory sessionFactory;

}
