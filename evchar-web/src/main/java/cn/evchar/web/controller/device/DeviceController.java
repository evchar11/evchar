package cn.evchar.web.controller.device;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.evchar.common.requestparam.DeviceListByOwnerRequestParam;
import cn.evchar.common.requestparam.DeviceListRequestParam;
import cn.evchar.common.requestparam.DeviceOperationParam;
import cn.evchar.common.requestparam.DeviceRequestParam;
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
		// initUserRequestParam校验
		validator.validate(param, errors);
		handleValidFieldError(errors);
		String deviceId = param.getDeviceId();
		if (NumberUtils.isNumber(deviceId)) {
			Long id = NumberUtils.toLong(deviceId);
			Device device = deviceService.getDevice(id);
			return createJsonResponse(ApiCode.SUCCESS, device, "获取设备成功");
		} else {
			throw new EvcharException(ApiCode.ERR_DEVICE_NOT_FOUND, "设备ID参数为空");
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
			if (param.getTime() != null) {
				operate(param.getOperation(), deviceId);
			} else {
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						operate(param.getOperation(), deviceId);
					}
				}, param.getTime());
			}
			return createJsonResponse(ApiCode.SUCCESS, null, "设备操作成功");
		}
	}

	private void operate(String operate, Long deviceId) {
		switch (operate) {
		case DeviceOperationParam.ON:
			deviceService.setDeviceState(deviceId, DeviceStateType.ENERGIZED);
			break;
		case DeviceOperationParam.OFF:
			deviceService.setDeviceState(deviceId, DeviceStateType.RESERVED);
			break;
		case DeviceOperationParam.ENABLE:
			deviceService.setDeviceState(deviceId, DeviceStateType.IDLE);
			break;
		case DeviceOperationParam.DISABLE:
			deviceService.setDeviceState(deviceId, DeviceStateType.RESERVED);
			break;
		}
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
