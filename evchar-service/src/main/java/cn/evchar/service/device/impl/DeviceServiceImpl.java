package cn.evchar.service.device.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.entity.car.CarDeviceMatch;
import cn.evchar.common.entity.device.Device;
import cn.evchar.common.util.Result;
import cn.evchar.dao.car.CarDeviceMatchDao;
import cn.evchar.dao.device.DeviceDao;
import cn.evchar.service.device.IDeviceService;
import cn.evchar.service.hardware.DeviceManager;

@Service
public class DeviceServiceImpl implements IDeviceService {

	@Resource
	private DeviceDao deviceDao;

	@Resource
	private CarDeviceMatchDao carDeviceMatchDao;

	@Override
	public boolean isAvailable(String deviceSn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Result<Object> appointDevice(Long deviceId) {
		DeviceManager instance = DeviceManager.INSANCE;
		return new Result<Object>();
	}

	@Override
	public List<Device> getDeviceList(String longitude, String latitude,
			Long carModelId) {
		Device device = new Device();
		device.setLongitude(longitude);
		device.setLatitude(latitude);
		if (carModelId != null) {
			CarDeviceMatch match = carDeviceMatchDao.get(carModelId);
			Long deviceModel = match.getDeviceModel();
			device.setModel(deviceModel);
		}
		return null;
	}
}
