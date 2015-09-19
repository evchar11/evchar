package cn.evchar.service.device;

import java.util.List;

import cn.evchar.common.entity.device.Device;
import cn.evchar.common.util.Result;

public interface IDeviceService {
	
	boolean isAvailable(String deviceSn);

	/**
	 * 预约设备
	 * @param deviceId
	 * @return
	 */
	Result<Object> appointDevice(Long deviceId);

	List<Device> getDeviceList(String longitude, String latitude,
			Long carModelId);
}
