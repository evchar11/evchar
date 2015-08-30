package cn.evchar.web.controller.user;

import cn.evchar.common.entity.user.User;
import cn.evchar.service.user.IUserService;
import cn.evchar.web.controller.AbstractController;
import cn.evchar.web.controller.user.requestParam.GetUserRequestParam;
import cn.evchar.web.controller.user.requestParam.InitUserRequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserController extends AbstractController{
	@Resource
	IUserService userService;

	/**
	 * 获取用户信息
	 */
	@RequestMapping("get.action")
	@ResponseBody
	public String getUser(GetUserRequestParam getUserRequestParam,HttpServletRequest request, HttpServletResponse response){
		return "test";
	}

	@RequestMapping("init.action")
	@ResponseBody
	public String initUser(InitUserRequestParam initUserRequestParam, HttpServletRequest request, HttpServletResponse response){
		//TODO initUserRequestParam校验
		User user = new User();
		user.setMacId(initUserRequestParam.getMacId());
		user.setMobile(initUserRequestParam.getMobile());
		user.setNickName(initUserRequestParam.getNickName());
		user.setWechatId(initUserRequestParam.getWechatId());
		userService.init(user);
		return "test";
	}

	@RequestMapping("test.action")
	@ResponseBody
	public String testUser(HttpServletRequest request, HttpServletResponse response){
		return "test";
	}

}
