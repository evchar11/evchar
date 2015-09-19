package cn.evchar.service.car.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.evchar.common.entity.car.CarDeviceMatch;
import cn.evchar.common.entity.car.CarModel;
import cn.evchar.common.entity.device.Device;
import cn.evchar.common.entity.device.DeviceModel;
import cn.evchar.dao.car.CarDeviceMatchDao;
import cn.evchar.service.car.ICarDeviceMatchService;

@Service
public class CarDeviceMatchServiceImpl implements ICarDeviceMatchService {

	@Resource
	private CarDeviceMatchDao carDeviceMatchDao;

	public List<CarModel> findMatchCarModel(DeviceModel deviceModel) {
		return carDeviceMatchDao.findMatchCarModel(deviceModel);
	}

	public List<DeviceModel> findMatchDeviceModel(CarModel carModel) {
		return carDeviceMatchDao.findMatchDeviceModel(carModel);
	}

	public boolean match(Long carModelId, Long deviceId){
		Device device = carDeviceMatchDao.get(Device.class, deviceId);
		CarDeviceMatch match = new CarDeviceMatch();
		match.setCarModel(carModelId);
		match.setDeviceModel(device.getModel());
		List<CarDeviceMatch> matchList = carDeviceMatchDao.findByExample(CarDeviceMatch.class, match);
		return !CollectionUtils.isEmpty(matchList);
	}
}
