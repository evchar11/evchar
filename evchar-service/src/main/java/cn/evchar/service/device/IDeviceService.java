package cn.evchar.service.device;

import java.util.Date;
import java.util.List;

import cn.evchar.common.entity.device.Device;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;

public interface IDeviceService {

	List<Device> getDeviceList(String longitude, String latitude,
			Long carModelId);

	Device getDevice(Long deviceId);
	
	Device getDeviceBySn(String sn);

	List<Device> getDeviceListByOwner(Long id);

	void setDeviceState(Long deviceId, DeviceStateType energized, Date time);
	
	List<Device> getDeviceListByAddr(String addr, Long carModelId);

	void update(Device device);

	String getDeviceTimer(Long id);
}
