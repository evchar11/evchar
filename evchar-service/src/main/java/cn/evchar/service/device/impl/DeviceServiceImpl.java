package cn.evchar.service.device.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.dao.device.DeviceDao;
import cn.evchar.service.device.IDeviceService;

@Service
public class DeviceServiceImpl implements IDeviceService{

	@Resource
	private DeviceDao deviceDao;
}
