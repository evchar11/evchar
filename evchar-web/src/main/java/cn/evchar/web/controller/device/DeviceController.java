package cn.evchar.web.controller.device;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.device.Device;
import cn.evchar.common.exception.EvcharException;
import cn.evchar.common.requestparam.DeviceListRequestParam;
import cn.evchar.common.requestparam.DeviceRequestParam;
import cn.evchar.service.device.IDeviceService;
import cn.evchar.web.controller.AbstractController;

@Controller
@RequestMapping("device")
public class DeviceController extends AbstractController {

	@Resource
	private IDeviceService deviceService;

	/**
	 * 用户预约订单
	 */
	@RequestMapping("getAll.action")
	@ResponseBody
	public String getDeviceList(DeviceListRequestParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		Long carModelId = null;
		String carModel = param.getCarModel();
		if (carModel != null) {
			carModelId = NumberUtils.toLong(carModel);
		}
		List<Device> deviceList = deviceService.getDeviceList(
				param.getLongitude(), param.getLatitude(), carModelId);
		return createJsonResponse(ApiCode.SUCCESS, deviceList, "获取设备列表成功");
	}

	@RequestMapping("get.action")
	@ResponseBody
	public String getDevice(DeviceRequestParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		String deviceId = param.getDeviceId();
		if (NumberUtils.isNumber(deviceId)) {
			Long id = NumberUtils.toLong(deviceId);
			Device device = deviceService.getDevice(id);
			return createJsonResponse(ApiCode.SUCCESS, device, "获取设备成功");
		} else {
			throw new EvcharException(ApiCode.ERR_DEVICE_NOT_FOUND, "设备ID参数为空");
		}
	}
}
