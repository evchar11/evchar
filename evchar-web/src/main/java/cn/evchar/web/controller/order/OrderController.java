package cn.evchar.web.controller.order;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.requestparam.AppointRequestParam;
import cn.evchar.common.requestparam.DeviceMatchUserRequestParam;
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

}
