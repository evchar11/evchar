package cn.evchar.web.controller.user;

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
import cn.evchar.common.entity.coupon.Coupon;
import cn.evchar.common.requestparam.CouponListParam;
import cn.evchar.service.coupon.ICouponSerice;
import cn.evchar.web.controller.AbstractController;

@Controller
@RequestMapping("coupon")
public class CouponController extends AbstractController {

	@Resource
	private ICouponSerice couponService;

	@Resource
	private Validator valiadtor;

	@RequestMapping("listCoupon.action")
	@ResponseBody
	public String listCoupon(CouponListParam param, HttpServletRequest request,
			HttpServletResponse response, Errors errors) {
		valiadtor.validate(param, errors);
		handleValidFieldError(errors);
		int pageSize = param.getPageSize();
		int pageNum = param.getPageNum();
		String wechatId = param.getWechatId();
		List<Coupon> listCoupon = couponService.listCouponById(pageSize,
				pageNum, wechatId);
		return createJsonResponse(ApiCode.SUCCESS, listCoupon, "");
	}

	public String addCoupon() {
		return "";
	}

}
