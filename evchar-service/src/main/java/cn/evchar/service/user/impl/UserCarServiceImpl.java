package cn.evchar.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.evchar.common.entity.user.UserCar;
import cn.evchar.dao.user.UserCarDao;
import cn.evchar.service.user.IUserCarService;

@Transactional
@Service
public class UserCarServiceImpl implements IUserCarService {
	@Resource
	private UserCarDao userCarDao;


	@Override
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public boolean saveUserCar(UserCar userCar) {
		Long userCarId = userCarDao.save(userCar);
		Assert.state(userCarId != null && userCarId > 0, "保存用户车信息失败");
		return true;
	}


	@Override
	public List<UserCar> findUserCarListByUserId(Long userId) {
		return userCarDao.findUserCarListByUserId(userId);
	}

}
