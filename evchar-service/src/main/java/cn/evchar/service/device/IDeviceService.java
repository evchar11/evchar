package cn.evchar.service.device;

import java.util.List;

import cn.evchar.common.entity.device.Device;
import cn.evchar.common.util.Result;

public interface IDeviceService {
	
	boolean isAvailable(String deviceSn);


	List<Device> getDeviceList(String longitude, String latitude,
			Long carModelId);
	
	Device getDevice(Long deviceId);
}
