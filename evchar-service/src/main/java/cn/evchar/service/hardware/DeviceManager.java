package cn.evchar.service.hardware;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.device.Device;
import cn.evchar.common.exception.EvcharException;
import cn.evchar.device.hardware.DeviceLived;
import cn.evchar.device.hardware.DeviceStateListener;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;
import cn.evchar.service.device.IDeviceService;

@Component
public class DeviceManager {

	@Resource
	private IDeviceService deviceService;

	@PostConstruct
	public void init() {
		DeviceLived dev1 = new DeviceLived("001", "1", "2",
				DeviceStateType.IDLE);
		DeviceLived dev2 = new DeviceLived("002", "1", "2",
				DeviceStateType.IDLE);
		DeviceLived dev3 = new DeviceLived("003", "1", "2",
				DeviceStateType.IDLE);
		DeviceLived dev4 = new DeviceLived("004", "1", "2",
				DeviceStateType.IDLE);
		DeviceLived dev5 = new DeviceLived("005", "1", "2",
				DeviceStateType.IDLE);
		DeviceLived dev6 = new DeviceLived("006", "1", "2",
				DeviceStateType.IDLE);
		DeviceLived dev7 = new DeviceLived("007", "1", "2",
				DeviceStateType.IDLE);
		DeviceLived dev8 = new DeviceLived("008", "1", "2",
				DeviceStateType.IDLE);
		devices.put(dev1.getSn(), dev1);
		devices.put(dev2.getSn(), dev2);
		devices.put(dev3.getSn(), dev3);
		devices.put(dev4.getSn(), dev4);
		devices.put(dev5.getSn(), dev5);
		devices.put(dev6.getSn(), dev6);
		devices.put(dev7.getSn(), dev7);
		devices.put(dev8.getSn(), dev8);
	}

	private List<DeviceStateListener> stateListeners = new ArrayList<>();

	private ConcurrentHashMap<String, DeviceLived> devices = new ConcurrentHashMap<>();

	public void addListener(DeviceStateListener stateListener) {
		stateListeners.add(stateListener);
	}

	public void addDevice(DeviceLived device) {
		devices.put(device.getSn(), device);
	}

	public void removeDevice(String sn) {
		devices.remove(sn);
	}

	/**
	 * 预约设备
	 * 
	 * @param deviceId
	 * @return
	 */
	public void appointDevice(Long deviceId) {
		String sn = getDeviceSnById(deviceId);
		if (devices.containsKey(sn)
				&& devices.get(sn).getState() == DeviceStateType.IDLE) {
			devices.get(sn).setState(DeviceStateType.RESERVED);
		} else {
			throw new EvcharException(ApiCode.ERR_DEVICE_APPOINT,
					"设备当前无法预约，请重试");
		}
	}

	/**
	 * 取消预约
	 * 
	 * @param deviceId
	 */
	public void cancelAppoint(Long deviceId) {
	}

	/**
	 * 设备上电
	 * 
	 * @param deviceId
	 * @return
	 */
	public boolean energize(Long deviceId) {
		// if(devices.contains(deviceId))
		return true;
	}

	/**
	 * 设备是否为空闲状态
	 * 
	 * @param deviceId
	 * @return
	 */
	public boolean isIdle(Long deviceId) {
		return true;
	}

	private String getDeviceSnById(Long deviceId) {
		Device dev = deviceService.getDevice(deviceId);
		if (dev == null) {
			throw new EvcharException(ApiCode.ERR_DEVICE_NOT_FOUND, "未找到该设备信息");
		}
		String sn = dev.getSn();
		return sn;
	}
}
