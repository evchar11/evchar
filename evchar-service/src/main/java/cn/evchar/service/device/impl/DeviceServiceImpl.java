package cn.evchar.service.device.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.util.Result;
import cn.evchar.dao.device.DeviceDao;
import cn.evchar.service.device.IDeviceService;

@Service
public class DeviceServiceImpl implements IDeviceService{

	@Resource
	private DeviceDao deviceDao;

	@Override
	public boolean isAvailable(String deviceSn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Result<Object> appointDevice(Long deviceId) {
		// TODO Auto-generated method stub
		return new Result<Object>();
	}
	
	
	
	
}
