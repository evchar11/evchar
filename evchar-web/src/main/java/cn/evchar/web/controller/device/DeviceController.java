package cn.evchar.web.controller.device;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.device.Device;
import cn.evchar.common.requestparam.AppointRequestParam;
import cn.evchar.common.requestparam.DeviceListRequestParam;
import cn.evchar.service.device.IDeviceService;
import cn.evchar.service.order.IOrderService;
import cn.evchar.web.controller.AbstractController;

public class DeviceController extends AbstractController {

	@Resource
	private IDeviceService deviceService;

	/**
	 * 用户预约订单
	 */
	@RequestMapping("get.action")
	@ResponseBody
	public String getDeviceList(DeviceListRequestParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
//		String longitude = param;
//		String latitude;
//		String car ;
		
		return createJsonResponse(ApiCode.SUCCESS, , "预约成功");
	}
}
