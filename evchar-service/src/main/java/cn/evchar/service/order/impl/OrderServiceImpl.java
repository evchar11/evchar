package cn.evchar.service.order.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.order.Order;
import cn.evchar.common.entity.order.Order.OrderStatus;
import cn.evchar.common.entity.user.User;
import cn.evchar.common.exception.EvcharException;
import cn.evchar.common.util.Result;
import cn.evchar.dao.order.OrderDao;
import cn.evchar.service.device.IDeviceService;
import cn.evchar.service.hardware.DeviceManager;
import cn.evchar.service.order.ICalculateService;
import cn.evchar.service.order.IOrderService;
import cn.evchar.service.user.IUserAccountService;
import cn.evchar.service.user.IUserService;

@Service
public class OrderServiceImpl implements IOrderService {
	//默认2元提示用户余额不足
	private static final Long DEFAULT_MONEY_LIMIT = 200L;
	//默认10元提示用户
	private static final Long DEFAULT_MONEY_WARN_LIMIT = 1000L;
	

	@Resource
	private OrderDao orderDao;
	@Resource
	private IUserService userService;
	@Resource
	private IUserAccountService userAccountService;
	@Resource
	private ICalculateService calculateService;
	@Resource
	private IDeviceService deviceService;
	
	@Override
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	public Long  appoint(String wechatId, Long deviceId, Long carId, String macId, boolean force) {
		User user = userService.findUserByWechatId(wechatId);
		if(user == null){
			throw new EvcharException(ApiCode.ERR_USER_NOT_FOUND, "用户未注册");
		}
		Long userId = user.getId();
		//校验用户是否有已经预约的订单
		if(findAppointedOrder(userId) != null){
			throw new EvcharException(ApiCode.ERR_USER_HAS_ORDER_APPOINTED, "已存在预约订单，请先取消");
		}
		Long usefulAccount = userAccountService.usefulAccount(userId);
		//校验余额是否充足
		if(usefulAccount <= DEFAULT_MONEY_LIMIT){
			throw new EvcharException(ApiCode.ERR_USER_HAS_ENOUGH_MONEY, "用户余额不足");
		}
		//余额不足，warn用户可充值电量
		if(usefulAccount <= DEFAULT_MONEY_WARN_LIMIT && !force){
			double degree = calculateService.generateDegree(usefulAccount);
			throw new EvcharException(degree, ApiCode.ERR_USER_MONEY_WARN, "用户余额不足");
		}
		Result<Object> result = new Result<Object>();
		//TODO 预约设备，返回对应
		result = deviceService.appointDevice(deviceId);
		if(!result.isSuccess()){
			throw new EvcharException(ApiCode.ERR_DEVICE_APPOINT, result.getMessage());
		}
		Long price = 10L;
		return generateOrder(userId, deviceId, carId, macId, price);
		
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
	 * @param price 
	 * @param money
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public Long generateOrder(Long userId, Long deviceId, Long carId,
			String macId, Long price) {
		Date now = new Date();
		Order order = new Order();
		order.setCarId(carId);
		order.setCreateTime(now);
		order.setUpdateTime(now);
		order.setDeviceId(deviceId);
		order.setUserId(userId);
		order.setStatus(OrderStatus.APPOINT.code());
		order.setMacId(macId);
		order.setPrice(price);
		Long orderId = orderDao.save(order);
		return orderId;
	}


	@Override
	public Order getById(Long orderId) {
		return  orderDao.get(orderId);
	}



	@Override
	public void appointCancel(Long orderId, int type) {
		Order order = getById(orderId);
		Long deviceId = order.getDeviceId();
		Assert.state(order.getStatus() == OrderStatus.APPOINT.code(), "订单状态异常");
		if(type == 0){
			order.setStatus(OrderStatus.CANCEL_BY_USER.code());
		}else{
			order.setStatus(OrderStatus.CANCEL_AUTO.code());
		}
		orderDao.update(order);
		//调用device服务，将设备置为可用
		DeviceManager.INSANCE.cancelAppoint(deviceId);
	}



	@Override
	public void deviceMatchUser(String wechatId, Long deviceId) {
		User user = userService.findUserByWechatId(wechatId);
		if(user == null){
			throw new EvcharException(ApiCode.ERR_USER_NOT_FOUND, "用户未注册");
		}
		Long userId = user.getId();
		Order order = findAppointedOrder(userId);
		if(order != null){
			Assert.state(order.getDeviceId() == deviceId, "不是你预约的设备");
		}
	}
	
	


}
