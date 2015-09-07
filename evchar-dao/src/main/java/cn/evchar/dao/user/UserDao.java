package cn.evchar.dao.user;

import cn.evchar.common.util.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.user.User;
import cn.evchar.dao.AbstractBaseDao;
import org.springframework.util.Assert;

@Repository
public class UserDao extends AbstractBaseDao<User, Long>{
    public static final String GET_BY_NICKNAME_HQL = "from User where nickName = ?" ;
    public static final String GET_BY_WECHATID_HQL = "from User where wechatId = ?" ;
	
	@Autowired
    private SessionFactory sessionFactory;

    public User getByNickName(String nickName){
        Assert.isTrue(StringUtils.isNotBlank(nickName));
        return getByHQL(GET_BY_NICKNAME_HQL, nickName);
    }
    
    public User getByWechatId(String wechatId){
        Assert.isTrue(StringUtils.isNotBlank(wechatId));
        return getByHQL(GET_BY_WECHATID_HQL, wechatId);
    }

}
