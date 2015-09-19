package cn.evchar.dao.car;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.car.CarDeviceMatch;
import cn.evchar.common.entity.car.CarModel;
import cn.evchar.common.entity.device.DeviceModel;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class CarDeviceMatchDao extends AbstractBaseDao<CarDeviceMatch, Long> {

	/**
	 * 按设备型号查找可用的车辆型号
	 **/
	public List<CarModel> findMatchCarModel(DeviceModel deviceModel) {
		List<CarModel> carModelList = new ArrayList<>();
		CarDeviceMatch match = new CarDeviceMatch();
		match.setDeviceModel(deviceModel.getId());
		List<CarDeviceMatch> matchList = findByExample(CarDeviceMatch.class,
				match);
		for (CarDeviceMatch cdm : matchList) {
			carModelList.add(get(CarModel.class, cdm.getCarModel()));
		}
		return carModelList;
	}

	/**
	 * 按车辆型号查找可用的设备型号
	 */
	public List<DeviceModel> findMatchDeviceModel(CarModel carModel) {
		List<DeviceModel> deviceModelList = new ArrayList<>();
		CarDeviceMatch match = new CarDeviceMatch();
		match.setCarModel(carModel.getId());
		List<CarDeviceMatch> matchList = findByExample(CarDeviceMatch.class,
				match);
		for (CarDeviceMatch cdm : matchList) {
			deviceModelList.add(get(DeviceModel.class, cdm.getDeviceModel()));
		}
		return deviceModelList;
	}
}
