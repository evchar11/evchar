package cn.evchar.dao.user;

import cn.evchar.common.entity.user.UserAccount;
import cn.evchar.dao.AbstractBaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDao extends AbstractBaseDao<UserAccount, Long>{
	private static final String FIND_BY_USER_ID_HQL = "from UserAccount where userId = ? ";

	public UserAccount findByUserId(Long userId) {
		return unique(FIND_BY_USER_ID_HQL, userId);
	}

}
