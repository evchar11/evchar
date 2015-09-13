package cn.evchar.dao.user;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import cn.evchar.common.entity.user.User;
import cn.evchar.common.util.StringUtils;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class UserDao extends AbstractBaseDao<User, Long> {
	public static final String GET_BY_WECHATID_HQL = "from User where wechatId = ?";

	/**
	 * 
	 * */
	public User getByWechatId(String wechatId) {
		Assert.isTrue(StringUtils.isNotBlank(wechatId));
		return unique(GET_BY_WECHATID_HQL, wechatId);
	}

}
