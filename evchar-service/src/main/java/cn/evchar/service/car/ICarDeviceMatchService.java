package cn.evchar.service.car;

import java.util.List;

import cn.evchar.common.entity.car.CarModel;
import cn.evchar.common.entity.device.DeviceModel;

public interface ICarDeviceMatchService {
	List<CarModel> findMatchCarModel(DeviceModel deviceModel);

	List<DeviceModel> findMatchDeviceModel(CarModel carModel);
}
