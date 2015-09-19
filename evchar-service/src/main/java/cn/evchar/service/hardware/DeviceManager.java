package cn.evchar.service.hardware;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import cn.evchar.device.hardware.DeviceLived;
import cn.evchar.device.hardware.DeviceStateListener;

public enum DeviceManager {

	INSANCE;

	DeviceManager() {
		DeviceLived liveDevice = new DeviceLived();
		DeviceLived liveDevice2 = new DeviceLived();
		devices.put(liveDevice.getSn(), liveDevice);
		devices.put(liveDevice.getSn(), liveDevice2);
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
	 * 取消预约
	 * @param deviceId
	 */
	public void cancelAppoint(Long deviceId) {
		// TODO Auto-generated method stub
		
	}

}
