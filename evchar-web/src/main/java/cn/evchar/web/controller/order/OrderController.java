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
import cn.evchar.service.order.IOrderService;
import cn.evchar.web.controller.AbstractController;
@Controller
@RequestMapping("order")
public class OrderController extends AbstractController{

	@Resource
	private Validator validator;
	@Resource
	private IOrderService orderService;
	//默认10元提示用户余额不足
	private static final Long default_money = 1000L;
	
	
	/**
	 * 用户预约订单
	 */
	@RequestMapping("appoint.action")
	@ResponseBody
	public String appointOrder(AppointRequestParam appointRequestParam, HttpServletRequest request, HttpServletResponse response, Errors errors){
		// initUserRequestParam校验
		validator.validate(appointRequestParam, errors);
		handleValidFieldError(errors);
		Long money = appointRequestParam.getMoney();
		if(money == null || money == 0){
			money = default_money;
		}
		//预约
		Long orderId = orderService.appoint(appointRequestParam.getWechatId(), appointRequestParam.getDeviceId(), appointRequestParam.getCarId(), money, appointRequestParam.getMacId());
		return createJsonResponse(ApiCode.SUCCESS, orderId, "预约成功");
	}


}
