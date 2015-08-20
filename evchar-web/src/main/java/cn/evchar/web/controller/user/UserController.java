package cn.evchar.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.web.controller.AbstractController;

@Controller
@RequestMapping("user")
public class UserController extends AbstractController{
	
	@RequestMapping("test.action")
	@ResponseBody
    public String testUser(HttpServletRequest request, HttpServletResponse response){
		return "test";
    }

}
