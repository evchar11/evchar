package cn.evchar.web.controller.order;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.order.Order;
import cn.evchar.common.exception.EvcharException;
import cn.evchar.common.requestparam.AppointRequestParam;
import cn.evchar.common.requestparam.CancelOrderRequestParam;
import cn.evchar.common.requestparam.DeviceMatchUserRequestParam;
import cn.evchar.common.requestparam.GetOrderListRequestParam;
import cn.evchar.common.util.StringUtils;
import cn.evchar.dao.PageResult;
import cn.evchar.service.order.IOrderService;
import cn.evchar.web.controller.AbstractController;
@Controller
@RequestMapping("order")
public class OrderController extends AbstractController{

	@Resource
	private Validator validator;
	@Resource
	private IOrderService orderService;
	
	
	
	/**
	 * 用户预约订单
	 */
	@RequestMapping("appoint.action")
	@ResponseBody
	public String appointOrder(AppointRequestParam appointRequestParam, HttpServletRequest request, HttpServletResponse response, Errors errors){
		// initUserRequestParam校验
		validator.validate(appointRequestParam, errors);
		handleValidFieldError(errors);
		//预约
		Long orderId = orderService.appoint(appointRequestParam.getWechatId(), appointRequestParam.getDeviceId(), appointRequestParam.getCarId(), appointRequestParam.getMacId(), appointRequestParam.isForce());
		return createJsonResponse(ApiCode.SUCCESS, orderId, "预约成功");
	}
	
	/**
	 * 设备匹配用户
	 */
	@RequestMapping("deviceMatchUser.action")
	@ResponseBody
	public String deviceMatchUser(DeviceMatchUserRequestParam deviceMatchUserRequestParam, HttpServletRequest request, HttpServletResponse response, Errors errors){
		// initUserRequestParam校验
		validator.validate(deviceMatchUserRequestParam, errors);
		handleValidFieldError(errors);
		orderService.deviceMatchUser(deviceMatchUserRequestParam.getWechatId(), deviceMatchUserRequestParam.getDeviceId(), deviceMatchUserRequestParam.getMacId(), deviceMatchUserRequestParam.getCarId());
		return createJsonResponse(ApiCode.SUCCESS, null, "匹配成功");
	}
	
	/**
	 * 获取用户所有的充电订单(分页)
	 */
	@RequestMapping("getOrderPage.action")
	@ResponseBody
	public String getOrderPage(GetOrderListRequestParam getOrderListRequestParam, HttpServletRequest request, HttpServletResponse response, Errors errors){
		// initUserRequestParam校验
		validator.validate(getOrderListRequestParam, errors);
		handleValidFieldError(errors);
		PageResult<Order> orderPage = orderService.findOrderPage(getOrderListRequestParam);
		return createJsonResponse(ApiCode.SUCCESS, orderPage, "获取成功");
	}
	
	/**
	 * 查询用户当前正在充电的订单
	 */
	@RequestMapping("getCharingOrder.action")
	@ResponseBody
	public String getCharingOrder(String wechatId, HttpServletRequest request, HttpServletResponse response){
		if(StringUtils.isBlank(wechatId)){
			throw new EvcharException(null, ApiCode.ERR_WRONG_PARAMS, "wechatId can not be empty");
		}
		List<Order> orderList = orderService.getCharingOrderList(wechatId);
		return createJsonResponse(ApiCode.SUCCESS, orderList, "获取成功");
	}
	
	/**
	 * 查询用户最近的充电订单
	 */
	@RequestMapping("getLastOrder.action")
	@ResponseBody
	public String getLastOrder(String wechatId, HttpServletRequest request, HttpServletResponse response){
		if(StringUtils.isBlank(wechatId)){
			throw new EvcharException(null, ApiCode.ERR_WRONG_PARAMS, "wechatId can not be empty");
		}
		Order order = orderService.getLastOrder(wechatId);
		return createJsonResponse(ApiCode.SUCCESS, order, null);
	}
	
	
	/**
	 * 取消已预约的订单
	 */
	@RequestMapping("appointCancel.action")
	@ResponseBody
	public String appointCancel(CancelOrderRequestParam param, HttpServletRequest request, HttpServletResponse response, Errors errors){
		orderService.appointCancel(param.getOrderId(), 0);
		return createJsonResponse(ApiCode.SUCCESS, null, null);
	}

}
