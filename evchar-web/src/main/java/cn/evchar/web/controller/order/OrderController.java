package cn.evchar.web.controller.order;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
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
	public String appointOrder(String wechatId, Long deviceId, HttpServletRequest request, HttpServletResponse response){
		Assert.state(StringUtils.isNotBlank(wechatId), "wechatId为空");
		Assert.state(deviceId != null && deviceId >0, "deviceId不能为空");
		//预约
		orderService.appoint(wechatId, deviceId);
		return createJsonResponse(ApiCode.SUCCESS, userInfoView, null);
	}


}
