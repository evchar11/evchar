package cn.evchar.service.hardware;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.device.Device;
import cn.evchar.common.exception.EvcharException;
import cn.evchar.device.hardware.DeviceAcceptor;
import cn.evchar.device.hardware.DeviceLived;
import cn.evchar.device.hardware.DeviceStateListener;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;
import cn.evchar.service.device.IDeviceService;

@Component
public class DeviceManager {

	// TODO:需要解决设备时断时连时用户体验的问题
	@Resource
	private IDeviceService deviceService;

	public static void main(String[] args) {
		new DeviceManager().init();
	}

	@PostConstruct
	public void init() {
	}

	private DeviceAcceptor acceptor = DeviceAcceptor.getInstance();

	private List<DeviceStateListener> stateListeners = new ArrayList<>();

	public void addListener(DeviceStateListener stateListener) {
		stateListeners.add(stateListener);
	}

	/**
	 * 预约设备
	 * 
	 * @param deviceId
	 * @return
	 */
	public void appointDevice(Long deviceId) {
		String sn = getDeviceSnById(deviceId);
		DeviceLived dev = acceptor.getAliveDevice(sn);
		if (dev.getState() == DeviceStateType.IDLE) {
			dev.setState(DeviceStateType.RESERVED);
			acceptor.getLiveDeviceMap().put(sn, dev);
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
		String sn = getDeviceSnById(deviceId);
		DeviceLived dev = acceptor.getAliveDevice(sn);
		if (dev.getState() == DeviceStateType.RESERVED) {
			dev.setState(DeviceStateType.IDLE);
			acceptor.getLiveDeviceMap().put(sn, dev);
		} else {
			throw new EvcharException(ApiCode.ERR_DEVICE_APPOINT,
					"设备当前无法取消预约，请重试");
		}
	}

	/**
	 * 设备上电
	 * 
	 * @param deviceId
	 * @return
	 */
	public boolean energize(Long deviceId) {
		String sn = getDeviceSnById(deviceId);
		DeviceLived dev = acceptor.getAliveDevice(sn);
		if (dev.getState() == DeviceStateType.RESERVED) {
			dev.setState(DeviceStateType.IDLE);
			acceptor.getLiveDeviceMap().put(sn, dev);
			// TODO:向设备发送上电命令，并等待返回
		} else {
			throw new EvcharException(ApiCode.ERR_DEVICE_CHARGE, "设备未处于预约状态");
		}
		return true;
	}

	/**
	 * 设备是否为空闲状态，只有设备在线并
	 * 
	 * @param deviceId
	 * @return
	 */
	public boolean isIdle(Long deviceId) {
		String sn = getDeviceSnById(deviceId);
		DeviceLived dev = acceptor.getAliveDevice(sn);
		return dev.getState() == DeviceStateType.IDLE;
	}

	/**
	 * 设置设备的状态，只要设备在线，一定会成功(没有其他校验)
	 * 
	 * @param deviceId
	 * @param state
	 */
	public void setState(long deviceId, DeviceStateType state) {
		String sn = getDeviceSnById(deviceId);
		DeviceLived dev = acceptor.getAliveDevice(sn);
		// if (state == dev.getState()) {
		// return;// 目标状态和当前状态相同， 不需要处理
		switch (state) {
		case CHARGING:
			throw new EvcharException(ApiCode.ERR_DEVICE_COMMAND, "设置命令有误");
		case ENERGIZED: // 上电
			dev.setState(state);
			acceptor.on(sn);
			break;
		case FULL:
			throw new EvcharException(ApiCode.ERR_DEVICE_COMMAND, "设置命令有误");
		case IDLE:
			dev.setState(state);
			acceptor.off(sn);
			break;
		case RESERVED:
			dev.setState(state);
			acceptor.off(sn);
			break;
		}
		acceptor.getLiveDeviceMap().put(sn, dev);
		// }
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
