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

	private DeviceAcceptor acceptor = DeviceAcceptor.getInstance();

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
		DeviceLived dev = getAliveDevice(sn);
		if (dev.getState() == DeviceStateType.IDLE) {
			dev.setState(DeviceStateType.RESERVED);
			devices.put(sn, dev);
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
		DeviceLived dev = getAliveDevice(sn);
		if (dev.getState() == DeviceStateType.RESERVED) {
			dev.setState(DeviceStateType.IDLE);
			devices.put(sn, dev);
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
		DeviceLived dev = getAliveDevice(sn);
		if (dev.getState() == DeviceStateType.RESERVED) {
			dev.setState(DeviceStateType.IDLE);
			devices.put(sn, dev);
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
		DeviceLived dev = getAliveDevice(sn);
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
		DeviceLived dev = getAliveDevice(sn);
		if (state == dev.getState()) {
			return;// 目标状态和当前状态相同， 不需要处理
		} else {
			switch (state) {
			case CHARGING:
				throw new EvcharException(ApiCode.ERR_DEVICE_COMMAND, "设置命令有误");
			case ENERGIZED: // 上电
				break;
			case FULL:
				throw new EvcharException(ApiCode.ERR_DEVICE_COMMAND, "设置命令有误");
			case IDLE:
				dev.setState(state);
				acceptor.off();
				break;
			case RESERVED:
				dev.setState(state);
				acceptor.on();
				break;
			}
			devices.put(sn, dev);
		}
	}

	private String getDeviceSnById(Long deviceId) {
		Device dev = deviceService.getDevice(deviceId);
		if (dev == null) {
			throw new EvcharException(ApiCode.ERR_DEVICE_NOT_FOUND, "未找到该设备信息");
		}
		String sn = dev.getSn();
		return sn;
	}

	private DeviceLived getAliveDevice(String sn) {
		if (devices.containsKey(sn)) {
			return devices.get(sn);
		} else {
			throw new EvcharException(ApiCode.ERR_DEVICE_NOT_ONLINE, "设备当前不在线");
		}
	}

	public DeviceStateType getLivedDeviceState(String devSn) {
		if (devices.get(devSn) == null) {
			return DeviceStateType.OFF;
		} else {
			return devices.get(devSn).getState();
		}
	}
}
