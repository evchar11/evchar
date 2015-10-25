package cn.evchar.service.device.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.entity.car.CarModel;
import cn.evchar.common.entity.device.Device;
import cn.evchar.common.entity.device.DeviceModel;
import cn.evchar.dao.car.CarDeviceMatchDao;
import cn.evchar.dao.device.DeviceDao;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;
import cn.evchar.service.device.IDeviceService;
import cn.evchar.service.hardware.DeviceManager;

@Service
public class DeviceServiceImpl implements IDeviceService {

	@Resource
	private DeviceDao deviceDao;

	@Resource
	private CarDeviceMatchDao carDeviceMatchDao;
	@Resource
	private DeviceManager deviceManager;

	@Override
	public List<Device> getDeviceList(String longitude, String latitude,
			Long carModelId) {
		Device device = new Device();
		device.setLongitude(longitude);
		device.setLatitude(latitude);

		List<Device> deviceList = new ArrayList<Device>();
		if (carModelId != null) {
			CarModel carModel = deviceDao.get(CarModel.class, carModelId);
			if (carModel != null) {
				List<DeviceModel> deviceModelList = carDeviceMatchDao
						.findMatchDeviceModel(carModel);
				for (DeviceModel deviceModel : deviceModelList) {
					Device modelDevice = new Device();
					modelDevice.setModel(deviceModel.getId());
					deviceList.addAll(deviceDao.findByExample(Device.class,
							modelDevice));
				}
			} else {
				return deviceList;// 没有匹配的设备，返回空列表
			}
		} else {
			deviceList = deviceDao.loadAll(Device.class);
		}
		for (Device dev : deviceList) {
		}
		// TODO:需要一个根据经纬度取出附近Device的方法
		return deviceList;
	}

	@Override
	public Device getDevice(Long deviceId) {
		Device device = deviceDao.get(Device.class, deviceId);
		return device;
	}

	@Override
	public List<Device> getDeviceListByOwner(Long id) {
		Device deviceExample = new Device();
		deviceExample.setOwner(id);
		List<Device> deviceList = deviceDao.findByExample(Device.class,
				deviceExample);
		return deviceList;
	}

	@Override
	public void setDeviceState(Long deviceId, DeviceStateType state) {
		deviceManager.setState(deviceId, state);
	}

	private void refreshList(List<Device> deviceList) {
		//TODO:刷新列表
	}

	@Override
	public List<Device> getDeviceListByAddr(String addr, Long carModelId) {
		List<Device> devList = new ArrayList<Device>();
		List<Device> all = deviceDao.getByAddr(addr);
		if (carModelId != null) {
			CarModel carModel = deviceDao.get(CarModel.class, carModelId);
			if (carModel != null) {
				List<DeviceModel> deviceModelList = carDeviceMatchDao
						.findMatchDeviceModel(carModel);
				for (Device dev : all) {
					boolean isMatch = false;
					for (DeviceModel deviceModel : deviceModelList) {
						if (deviceModel.getId().equals(dev.getModel())) {
							isMatch = true;
							break;
						}
						// Device modelDevice = new Device();
						// modelDevice.setModel(deviceModel.getId());
						// deviceList.addAll(deviceDao.findByExample(Device.class,
						// modelDevice));
					}
					if (isMatch) {
						devList.add(dev);
					}
				}
			}
		}
		return devList;
	}

	@Override
	public void update(Device device) {
		deviceDao.saveOrUpdate(device);
	}

	@Override
	public Device getDeviceBySn(String sn) {
		Device sample = new Device();
		sample.setSn(sn);
		List<Device> dev = deviceDao.findByExample(Device.class, sample);
		if (dev.size() > 0) {
			return dev.get(0);
		} else {
			return null;
		}
	}
}
