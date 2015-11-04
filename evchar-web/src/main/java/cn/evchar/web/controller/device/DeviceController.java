package cn.evchar.web.controller.device;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.device.Device;
import cn.evchar.common.entity.user.User;
import cn.evchar.common.exception.EvcharException;
import cn.evchar.common.requestparam.DeviceCapacityParam;
import cn.evchar.common.requestparam.DeviceListByOwnerRequestParam;
import cn.evchar.common.requestparam.DeviceListRequestParam;
import cn.evchar.common.requestparam.DeviceOperationParam;
import cn.evchar.common.requestparam.DeviceRequestParam;
import cn.evchar.common.requestparam.DeviceRequestParamBind;
import cn.evchar.common.requestparam.DeviceRequestParamByAddr;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;
import cn.evchar.service.device.IDeviceService;
import cn.evchar.service.user.IUserService;
import cn.evchar.web.controller.AbstractController;

@Controller
@RequestMapping("device")
public class DeviceController extends AbstractController {

	@Resource
	private IDeviceService deviceService;

	@Resource
	private IUserService userService;

	@Resource
	private Validator validator;

	/**
	 * 根据地理位置获取附近的电桩
	 */
	@RequestMapping("getAll.action")
	@ResponseBody
	public String getDeviceList(DeviceListRequestParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		// initUserRequestParam校验
		validator.validate(param, errors);
		handleValidFieldError(errors);
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
		String sn = param.getSn();
		if (StringUtils.isNotBlank(deviceId) && NumberUtils.isNumber(deviceId)) {
			Long id = NumberUtils.toLong(deviceId);
			Device device = deviceService.getDevice(id);
			return createJsonResponse(ApiCode.SUCCESS, device, "获取设备成功");
		} else if (StringUtils.isNotBlank(sn)) {
			Device device = deviceService.getDeviceBySn(sn);
			return createJsonResponse(ApiCode.SUCCESS, device, "获取设备成功");
		} else {
			throw new EvcharException(ApiCode.ERR_DEVICE_NOT_FOUND, "设备参数为空");
		}
	}

	@RequestMapping("getByOwner.action")
	@ResponseBody
	public String getDeviceListByOwner(DeviceListByOwnerRequestParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		// initUserRequestParam校验
		validator.validate(param, errors);
		handleValidFieldError(errors);
		User user = userService.findUserByWechatId(param.getWechatId());
		if (user == null) {
			throw new EvcharException(ApiCode.ERR_USER_NOT_FOUND, "用户不存在");
		}
		List<Device> devList = deviceService.getDeviceListByOwner(user.getId());
		return createJsonResponse(ApiCode.SUCCESS, devList, "获取设备成功");
	}

	@RequestMapping("operate.action")
	@ResponseBody
	public String operate(final DeviceOperationParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		final Long deviceId = param.getDeviceId();
		User user = userService.findUserByWechatId(param.getWechatId());
		if (user == null) {
			throw new EvcharException(ApiCode.ERR_USER_NOT_FOUND, "用户不存在");
		}
		// TODO:设备所有权校验
		if (param.getOperation() == null) {
			throw new EvcharException(ApiCode.ERR_DEVICE_COMMAND, "操作不合法");
		} else {
			if (operate(param.getOperation(), deviceId, param.getTime())) {
				return createJsonResponse(ApiCode.SUCCESS, null, "设备操作成功");
			} else {
				return createJsonResponse(ApiCode.SUCCESS, null, "设备操作失败");
			}

		}
	}

	@RequestMapping("capacity.action")
	@ResponseBody
	public String capacity(final DeviceCapacityParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		User user = userService.findUserByWechatId(param.getWechatId());
		if (user == null) {
			throw new EvcharException(ApiCode.ERR_USER_NOT_FOUND, "用户不存在");
		}
		deviceService.setCapacity(param.getDeviceId(), param.getCapacity());
		return createJsonResponse(ApiCode.SUCCESS, null, "设备操作成功");
	}

	private boolean operate(String operate, Long deviceId, Date time) {
		switch (operate) {
		case DeviceOperationParam.ON:
			return deviceService.setDeviceState(deviceId,
					DeviceStateType.POWER_ON, time);
		case DeviceOperationParam.OFF:
			return deviceService.setDeviceState(deviceId,
					DeviceStateType.POWER_OFF, time);
			// case DeviceOperationParam.ENABLE:
			// deviceService.setDeviceState(deviceId, DeviceStateType.IDLE,
			// time);
			// break;
			// case DeviceOperationParam.DISABLE:
			// deviceService.setDeviceState(deviceId, DeviceStateType.RESERVED,
			// time);
			// break;
		}
		return false;
	}

	@RequestMapping("distanceOptions.action")
	@ResponseBody
	public String getDistances(HttpServletRequest request,
			HttpServletResponse response, Errors errors) {
		List<Distance> distanceList = new ArrayList<>();
		distanceList.add(new Distance("0", "1km"));
		distanceList.add(new Distance("1", "2km"));
		distanceList.add(new Distance("2", "5km"));
		distanceList.add(new Distance("3", "10km"));
		distanceList.add(new Distance("4", "15km"));
		return createJsonResponse(ApiCode.SUCCESS, distanceList, "查找距离选项列表成功");
	}

	@RequestMapping("search.action")
	@ResponseBody
	public String getDeviceListByAddr(DeviceRequestParamByAddr param,
			HttpServletRequest request, HttpServletResponse response) {
		Long carModelId = null;
		String carModel = param.getCarModel();
		if (carModel != null) {
			carModelId = NumberUtils.toLong(carModel);
		}
		List<Device> deviceList = deviceService.getDeviceListByAddr(
				param.getAddr(), carModelId);
		return createJsonResponse(ApiCode.SUCCESS, deviceList, "获取设备列表成功");
	}

	@RequestMapping("bindOwner.action")
	@ResponseBody
	public String bindOwner(DeviceRequestParamBind param,
			HttpServletRequest request, HttpServletResponse response) {

		Device dev = deviceService.getDevice(NumberUtils.toLong(param
				.getDeviceId()));
		if (!NumberUtils.isNumber(param.getDeviceId())) {
			return createJsonResponse(ApiCode.ERR_WRONG_PARAMS, null, "设备参数错误");
		}
		if (dev == null) {
			return createJsonResponse(ApiCode.ERR_DEVICE_NOT_FOUND, null,
					"设备不存在，操作失败");
		}
		if (StringUtils.isNotBlank(param.getWechatId())) {
			// wechatId为空为解绑
			User user = userService.findUserByWechatId(param.getWechatId());
			if (user == null) {
				return createJsonResponse(ApiCode.ERR_USER_NOT_FOUND, null,
						"用户不存在，绑定失败");
			}
			dev.setOwner(user.getId());
		} else {
			dev.setOwner(null);
		}
		deviceService.update(dev);
		return createJsonResponse(ApiCode.SUCCESS, dev, "绑定桩主成功");
	}

	@RequestMapping("timer.action")
	@ResponseBody
	public String getDeviceTimer(String deviceId, HttpServletRequest request,
			HttpServletResponse response) {
		if (deviceId == null || !NumberUtils.isNumber(deviceId)) {
			throw new EvcharException(ApiCode.ERR_WRONG_PARAMS, "设备参数为空");
		}
		String time = deviceService
				.getDeviceTimer(NumberUtils.toLong(deviceId));
		return createJsonResponse(ApiCode.SUCCESS, time, "获取定时时间成功");
	}
}

// TODO:临时解决方案，待9.27fix
class Distance {

	public Distance(String itemCode, String itemValue) {
		super();
		this.itemCode = itemCode;
		this.itemValue = itemValue;
	}

	private String itemCode;
	private String itemValue;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
}
