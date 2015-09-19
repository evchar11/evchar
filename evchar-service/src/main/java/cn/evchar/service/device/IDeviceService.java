package cn.evchar.service.device;

import cn.evchar.common.util.Result;

public interface IDeviceService {
	
	boolean isAvailable(String deviceSn);

	/**
	 * 预约设备
	 * @param deviceId
	 * @return
	 */
	Result<Object> appointDevice(Long deviceId);
	
	
}
