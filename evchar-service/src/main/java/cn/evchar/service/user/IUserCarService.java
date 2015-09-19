package cn.evchar.service.user;

import java.util.List;

import cn.evchar.common.entity.user.UserCar;

public interface IUserCarService {
	/**
	 * 保存用户车辆信息
	 * @param userCar
	 * @return
	 */
	boolean saveUserCar(UserCar userCar);

	/**
	 * 获取用户车辆信息 by userId
	 * @param userId
	 * @return
	 */
	List<UserCar> findUserCarListByUserId(Long userId);

	UserCar getById(Long carId);
}
