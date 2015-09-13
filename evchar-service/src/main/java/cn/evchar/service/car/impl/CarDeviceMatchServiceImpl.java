package cn.evchar.service.car.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.dao.car.CarDeviceMatchDao;
import cn.evchar.service.car.ICarDeviceMatchService;

@Service
public class CarDeviceMatchServiceImpl implements ICarDeviceMatchService {

	@Resource
	private CarDeviceMatchDao carDeviceMatchDao;
	
	
}
