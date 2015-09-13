package cn.evchar.web.controller.order;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.evchar.web.controller.AbstractController;
@Controller
@RequestMapping("order")
public class OrderController extends AbstractController{

	@Resource
	private Validator validator;

}
