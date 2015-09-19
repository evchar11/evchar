package cn.evchar.device.hardware;

import java.util.concurrent.ConcurrentHashMap;

public enum DeviceManager {
	
	INSANCE;
	
	DeviceManager(){
		DeviceLived device= new DeviceLived();
	}
	
	private ConcurrentHashMap<String, DeviceLived> devices = new ConcurrentHashMap<>();

	public void addDevice(DeviceLived device) {
		devices.put(device.getSn(), device);
	}
	
	public void removeDevice(String sn)
	{
		devices.remove(sn);
	}
	
	
}
