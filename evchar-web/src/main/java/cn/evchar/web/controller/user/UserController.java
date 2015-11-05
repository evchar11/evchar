package cn.evchar.web.controller.user;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.user.User;
import cn.evchar.common.exception.EvcharException;
import cn.evchar.common.requestparam.InitUserRequestParam;
import cn.evchar.common.requestparam.SetDefaultCarRequestParam;
import cn.evchar.common.requestparam.UserRequestParamUpdate;
import cn.evchar.common.view.UserInfoView;
import cn.evchar.service.user.IUserService;
import cn.evchar.web.controller.AbstractController;

@Controller
@RequestMapping("user")
public class UserController extends AbstractController {
	private static Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Resource
	private IUserService userService;

	@Resource
	private Validator validator;

	/**
	 * 获取用户信息
	 */
	@RequestMapping("get.action")
	@ResponseBody
	public String getUserInfo(String wechatId, HttpServletRequest request,
			HttpServletResponse response) {
		Assert.state(StringUtils.isNotBlank(wechatId), "wechatId 不能为空");
		if (!userService.checkUserExists(wechatId)) {
			throw new EvcharException(ApiCode.ERR_USER_NOT_FOUND, "用户未注册");
		}
		UserInfoView userInfoView = userService.getUserInfo(wechatId);
		User user = userInfoView.getUser();
		user.setNickName(decodeFromBase64(user.getNickName()));
		userInfoView.setUser(user);
		return createJsonResponse(ApiCode.SUCCESS, userInfoView, null);
	}

	@RequestMapping("update.action")
	@ResponseBody
	public String update(UserRequestParamUpdate param,
			HttpServletRequest request, HttpServletResponse response) {
		User user = userService.findUserById(param.getUserId());
		if (StringUtils.isBlank(param.getMobile())) {
			return createJsonResponse(ApiCode.ERR_WRONG_PARAMS, null, "手机号不能为空");
		} else {
			user.setMobile(param.getMobile());
			userService.updateUser(user);
			return createJsonResponse(ApiCode.SUCCESS, null, "修改成功");
		}
	}

	/**
	 * 用户注册
	 * 
	 * @param initUserRequestParam
	 * @param request
	 * @param response
	 * @param errors
	 * @return
	 */
	@RequestMapping("init.action")
	@ResponseBody
	public String initUser(InitUserRequestParam initUserRequestParam,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		// initUserRequestParam校验
		initUserRequestParam.setNickName(encodeToBase64(initUserRequestParam
				.getNickName()));
		validator.validate(initUserRequestParam, errors);
		handleValidFieldError(errors);
		if (userService.checkUserExists(initUserRequestParam.getWechatId())) {
			throw new EvcharException(ApiCode.ERR_USER_EXIST_ALREADY, "用户已注册");
		}
		userService.init(initUserRequestParam);
		return createJsonResponse(ApiCode.SUCCESS, null, "注册成功");
	}

	/**
	 * 为用户设置默认车辆
	 * 
	 * @param initUserRequestParam
	 * @param request
	 * @param response
	 * @param errors
	 * @return
	 */
	@RequestMapping("setDefaultCar.action")
	@ResponseBody
	public String setDefaultCar(SetDefaultCarRequestParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		// initUserRequestParam校验
		// validator.validate(param, errors);
		// handleValidFieldError(errors);
		// if(!userService.checkUserExists(param.getWechatId())){
		// throw new EvcharException(ApiCode.ERR_USER_NOT_FOUND, "用户未注册");
		// }
		// userService.init(param);
		return createJsonResponse(ApiCode.SUCCESS, null, "设置默认车型成功");
	}

	private String encodeToBase64(String source) {
		try {
			byte[] bytes = Base64.encodeBase64(source.getBytes("UTF-8"));
			return new String(bytes);
		} catch (UnsupportedEncodingException e) {
			throw new EvcharException(ApiCode.ERR_SYSTEM, "编码有误");
		}
	}

	private String decodeFromBase64(String encoded) {
		byte[] bytes = Base64.decodeBase64(encoded);
		return new String(bytes);
	}


	@RequestMapping("getTestUser.action")
	@ResponseBody
	public String getTestUser(Long id) {

		return createJsonResponse("200",userService.testGetUser(id),"操作成功！");
}
	@RequestMapping("list.action")
	@ResponseBody
	public String list(Integer pageSize,Integer pageNum) {

		return createJsonResponse("200",userService.testGetAllUser(pageSize,pageNum),"操作成功！");
	}
}
