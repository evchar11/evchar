package cn.evchar.service.device;

import java.util.List;

import cn.evchar.common.entity.device.Device;
import cn.evchar.common.util.Result;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;

public interface IDeviceService {

	List<Device> getDeviceList(String longitude, String latitude,
			Long carModelId);

	Device getDevice(Long deviceId);

	List<Device> getDeviceListByOwner(Long id);

	void setDeviceState(Long deviceId, DeviceStateType energized);
}
