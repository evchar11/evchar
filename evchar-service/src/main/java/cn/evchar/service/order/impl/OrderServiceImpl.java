package cn.evchar.service.order.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.order.Order;
import cn.evchar.common.entity.order.Order.OrderStatus;
import cn.evchar.common.entity.user.User;
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
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	public Long  appoint(String wechatId, Long deviceId, Long carId, Long money, String macId) {
		User user = userService.findUserByWechatId(wechatId);
		if(user == null){
			throw new EvcharException(ApiCode.ERR_USER_NOT_FOUND, "用户未注册");
		}
		Long userId = user.getId();
		//校验用户是否有已经预约的订单
		if(findAppointedOrder(userId) != null){
			throw new EvcharException(ApiCode.ERR_USER_HAS_ORDER_APPOINTED, "已存在预约订单，请先取消");
		}
		//校验余额是否可以支持这次预约
		if(!userAccountService.checkAccount(userId, money)){
			throw new EvcharException(ApiCode.ERR_USER_HAS_ENOUGH_MONEY, "用户余额不足");
		}
		Result<Object> result = new Result<Object>();
		//TODO 预约设备，返回对应
		//result = deviceService.appointDevice(userId, deviceId);
		if(!result.isSuccess()){
			throw new EvcharException(ApiCode.ERR_DEVICE_APPOINT, result.getMessage());
		}
		
		return generateOrder(userId, deviceId, carId, money, macId);
		
	}

	/**
	 * 查找用户已预约的订单
	 * @param userId
	 * @return 订单
	 */
	public Order findAppointedOrder(Long userId) {
		return orderDao.findAppointedOrder(userId);
		
	}

	/**
	 * 创建订单
	 * @param userId
	 * @param deviceId
	 * @param carId
	 * @param money
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public Long generateOrder(Long userId, Long deviceId, Long carId,
			Long money, String macId) {
		Date now = new Date();
		Order order = new Order();
		order.setCarId(carId);
		order.setCreateTime(now);
		order.setUpdateTime(now);
		order.setDeviceId(deviceId);
		order.setUserId(userId);
		order.setTotalPrice(money);
		order.setStatus(OrderStatus.APPOINT.code());
		order.setMacId(macId);
		Long orderId = orderDao.save(order);
		return orderId;
	}
}
