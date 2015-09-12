package cn.evchar.web.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.requestparam.InitUserRequestParam;
import cn.evchar.service.user.IUserService;
import cn.evchar.web.controller.AbstractController;

@Controller
@RequestMapping("user")
public class UserController extends AbstractController{
	@Resource
	private IUserService userService;
	
	@Resource
	private Validator validator;

	/**
	 * 获取用户信息
	 */
	@RequestMapping("get.action")
	@ResponseBody
	public String getUser(String wechat_id, HttpServletRequest request, HttpServletResponse response){
		return "测试";
	}

	/**
	 * 用户注册
	 * @param initUserRequestParam
	 * @param request
	 * @param response
	 * @param errors
	 * @return
	 */
	@RequestMapping("init.action")
	@ResponseBody
	public String initUser(InitUserRequestParam initUserRequestParam, HttpServletRequest request, HttpServletResponse response, Errors errors){
		// initUserRequestParam校验
		validator.validate(initUserRequestParam, errors);
		handleValidFieldError(errors);
		userService.init(initUserRequestParam);
		return createJsonResponse(ApiCode.SUCCESS, null, "注册成功");
	}


}
