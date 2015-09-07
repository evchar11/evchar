package cn.evchar.service.user;

import cn.evchar.common.entity.user.UserCar;

public interface IUserCarService {
	/**
	 * 保存用户车辆信息
	 * @param userCar
	 * @return
	 */
	boolean saveUserCar(UserCar userCar);
}
