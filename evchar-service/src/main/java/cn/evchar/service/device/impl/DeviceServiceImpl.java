package cn.evchar.service.device.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.util.Result;
import cn.evchar.dao.device.DeviceDao;
import cn.evchar.service.device.IDeviceService;
import cn.evchar.service.hardware.DeviceManager;

@Service
public class DeviceServiceImpl implements IDeviceService {

	@Resource
	private DeviceDao deviceDao;

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

}
