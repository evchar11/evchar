package cn.evchar.web.controller.user;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.user.UserCar;
import cn.evchar.common.requestparam.UserCarRequestParam;
import cn.evchar.service.user.IUserCarService;
import cn.evchar.service.user.impl.UserCarServiceImpl;
import cn.evchar.web.controller.AbstractController;

@Controller
@RequestMapping("usercar")
public class UserCarController extends AbstractController {

	@Resource
	private IUserCarService userCarService;

	@RequestMapping("update.action")
	@ResponseBody
	private String update(UserCarRequestParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		if (NumberUtils.isNumber(param.getCarId())) {
			UserCar oldCar = userCarService.getById(NumberUtils.toLong(param
					.getCarId()));
			if (oldCar != null) {
				if (param.getCarNo() != null) {
					oldCar.setCarNo(param.getCarNo());
				}
				if (param.getCarModelId() != null) {
					oldCar.setCarModelId(param.getCarModelId());
				}
				oldCar.setUpdateTime(new Date());
				userCarService.updateUserCar(oldCar);
				return createJsonResponse(ApiCode.SUCCESS, null,
						"修改车辆信息成功");
			} else {
				return createJsonResponse(ApiCode.ERR_WRONG_PARAMS, null,
						"车辆ID不存在");
			}
		} else {
			return createJsonResponse(ApiCode.ERR_WRONG_PARAMS, null, "车辆ID不正确");
		}

	}
}
