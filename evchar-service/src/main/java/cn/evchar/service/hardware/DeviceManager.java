package cn.evchar.service.hardware;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.device.Device;
import cn.evchar.common.exception.EvcharException;
import cn.evchar.common.util.serializer.CustomDateSerializer;
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
	private Map<String, Date> deviceTimerMap = new HashMap<>();

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
		// if (dev.getState() == DeviceStateType.IDLE) {
		// dev.setState(DeviceStateType.RESERVED);
		// acceptor.getLiveDeviceMap().put(sn, dev);
		// } else {
		// throw new EvcharException(ApiCode.ERR_DEVICE_APPOINT,
		// "设备当前无法预约，请重试");
		// }
	}

	/**
	 * 取消预约
	 * 
	 * @param deviceId
	 */
	public void cancelAppoint(Long deviceId) {
		String sn = getDeviceSnById(deviceId);
		DeviceLived dev = acceptor.getAliveDevice(sn);
		// if (dev.getState() == DeviceStateType.RESERVED) {
		// dev.setState(DeviceStateType.IDLE);
		// acceptor.getLiveDeviceMap().put(sn, dev);
		// } else {
		// throw new EvcharException(ApiCode.ERR_DEVICE_APPOINT,
		// "设备当前无法取消预约，请重试");
		// }
	}

	/**
	 * 设备上电
	 * 
	 * @param deviceId
	 * @return
	 */
	// public boolean energize(Long deviceId) {
	// String sn = getDeviceSnById(deviceId);
	// DeviceLived dev = acceptor.getAliveDevice(sn);
	// if (dev.getState().isEnegrized()) {
	// return false;
	// acceptor.getLiveDeviceMap().put(sn, dev);
	// // TODO:向设备发送上电命令，并等待返回
	// } else {
	// throw new EvcharException(ApiCode.ERR_DEVICE_CHARGE, "设备未处于预约状态");
	// }
	// return true;
	// }

	/**
	 * 设备是否为空闲状态，只有设备在线并
	 * 
	 * @param deviceId
	 * @return
	 */
	public boolean isEnergized(Long deviceId) {
		String sn = getDeviceSnById(deviceId);
		DeviceLived dev = acceptor.getAliveDevice(sn);
		return dev.getState().isEnegrized();
	}

	/**
	 * 设置设备的状态，只要设备在线，一定会成功(没有其他校验)
	 * 
	 * @param deviceId
	 * @param state
	 * @return
	 */
	public boolean setState(final long deviceId, final DeviceStateType state,
			final Date time) {
		final String sn = getDeviceSnById(deviceId);
		if (time != null) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					deviceTimerMap.put(sn, time);
					setState(deviceId, state, null);
					deviceTimerMap.remove(sn);
				}
			}, time);
			deviceTimerMap.put(sn, time);
			return true;
		}
		DeviceLived dev = acceptor.getAliveDevice(sn);
		switch (state) {
		case POWER_ON:
			if (dev.getState().isEnegrized()) {
				return false;
			} else {
				acceptor.on(sn);
			}
			break;
		case POWER_OFF:
			if (dev.getState().isEnegrized()) {
				acceptor.off(sn);
			} else {
				return false;
			}
			break;
		default:
			return false;
		}
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

	public String getDeviceTimer(Long id) {
		String sn = getDeviceSnById(id);
		Date timer = deviceTimerMap.get(sn);
		if (timer != null) {
			return new SimpleDateFormat(CustomDateSerializer.DATE_FORMATER)
					.format(timer);
		} else {
			return "";
		}
	}

	public String getStatus(String sn) {
		DeviceLived dev = acceptor.getLiveDeviceMap().get(sn);
		if (dev != null) {
			if (dev.getState().isEnegrized()) {
				return "01";
			} else {
				return "02";
			}
		}
		return null;
	}
}
