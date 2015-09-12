package cn.evchar.dao.user;

import java.util.List;

import cn.evchar.common.entity.user.UserCar;
import cn.evchar.dao.AbstractBaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class UserCarDao extends AbstractBaseDao<UserCar, Long>{
	private static final String FIND_BY_USER_ID_HQL = "from UserCar where userId = ? ";

	public List<UserCar> findUserCarListByUserId(Long userId) {
		return getListByHQL(FIND_BY_USER_ID_HQL, userId);
	}

}
