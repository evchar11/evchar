package cn.evchar.web.controller.pay;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.requestparam.CreatePaymentOrderRequestParam;
import cn.evchar.common.requestparam.PaymentOrderCallbackRequestParam;
import cn.evchar.service.pay.IPaymentOrderService;
import cn.evchar.web.controller.AbstractController;


@Controller
@RequestMapping("pay")
public class PayOrderController extends AbstractController{
	@Resource
	private Validator validator;
	@Resource
	private IPaymentOrderService payOrderService;
	
	/**
	 * 生成用户支付订单
	 */
	@RequestMapping("create.action")
	@ResponseBody
	public String createPaymentOrder(CreatePaymentOrderRequestParam createPayOrderRequestParam, HttpServletRequest request, HttpServletResponse response, Errors errors){
		// initUserRequestParam校验
		validator.validate(createPayOrderRequestParam, errors);
		handleValidFieldError(errors);
		//预约
		Long payOrderId = payOrderService.create(createPayOrderRequestParam.getWechatId(), createPayOrderRequestParam.getMoney());
		return createJsonResponse(ApiCode.SUCCESS, payOrderId, "支付订单创建成功");
	}
	
	@RequestMapping("callback.action")
	@ResponseBody
	public String paymentOrderCallback(PaymentOrderCallbackRequestParam paymentOrderCallbackRequestParam, HttpServletRequest request, HttpServletResponse response, Errors errors){
		// initUserRequestParam校验
		validator.validate(paymentOrderCallbackRequestParam, errors);
		handleValidFieldError(errors);
		//预约
		payOrderService.updateForPay(paymentOrderCallbackRequestParam);
		return createJsonResponse(ApiCode.SUCCESS, null, "支付订单回调通知成功");
	}

}
