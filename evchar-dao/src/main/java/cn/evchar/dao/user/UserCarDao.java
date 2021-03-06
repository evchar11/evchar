package cn.evchar.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.user.UserCar;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class UserCarDao extends AbstractBaseDao<UserCar, Long> {
	private static final String FIND_BY_USER_ID_HQL = "from UserCar where userId = ? ";

	public List<UserCar> findUserCarListByUserId(Long userId) {
		return (List<UserCar>) findByHql(FIND_BY_USER_ID_HQL, userId);
	}

}
