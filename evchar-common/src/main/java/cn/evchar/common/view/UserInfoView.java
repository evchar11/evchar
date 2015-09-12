package cn.evchar.common.view;

import java.util.List;

import cn.evchar.common.entity.user.User;
import cn.evchar.common.entity.user.UserCar;


/**
 * 用户详情信息
 * @author wangfeng@evchar.cn
 *
 */
public class UserInfoView {
	private User user;
	private List<UserCar> carList;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserCar> getCarList() {
		return carList;
	}
	public void setCarList(List<UserCar> carList) {
		this.carList = carList;
	}
	
}
