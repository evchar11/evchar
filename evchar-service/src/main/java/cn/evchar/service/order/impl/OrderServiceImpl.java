package cn.evchar.service.order.impl;

import javax.annotation.Resource;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.user.User;
import cn.evchar.common.entity.user.UserAccount;
import cn.evchar.common.exception.EvcharException;
import cn.evchar.common.util.Result;
import cn.evchar.dao.order.OrderDao;
import cn.evchar.service.order.IOrderService;
import cn.evchar.service.user.IUserAccountService;
import cn.evchar.service.user.IUserService;

public class OrderServiceImpl implements IOrderService {


	@Resource
	private OrderDao orderDao;
	@Resource
	private IUserService userService;
	@Resource
	private IUserAccountService userAccountService;
	
	@Override
	public void appoint(String wechatId, Long deviceId, Long carId, Long money) {
		User user = userService.findUserByWechatId(wechatId);
		if(user == null){
			throw new EvcharException(ApiCode.ERR_USER_NOT_FOUND, "用户未注册");
		}
		Long userId = user.getId();
		//校验余额是否可以支持这次预约
		userAccountService.checkAccount(userId, money);
		Result result = new Result();
		//result = deviceService.appointDevice(userId, deviceId);
		if(!result.isSuccess()){
			throw new EvcharException(ApiCode.ERR_DEVICE_APPOINT, result.getMessage());
		}
		
		generateOrder(userId, deviceId, carId, price);
		
	}
}
