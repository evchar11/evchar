package cn.evchar.service.device.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.dao.device.DeviceModelDao;
import cn.evchar.service.device.IDeviceModelService;

@Service
public class DeviceModelServiceImpl implements IDeviceModelService{

	@Resource
	private DeviceModelDao deviceModelDao;

}
