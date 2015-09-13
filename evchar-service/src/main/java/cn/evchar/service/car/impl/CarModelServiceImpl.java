package cn.evchar.service.car.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.dao.car.CarModelDao;
import cn.evchar.service.car.ICarModelService;

@Service
public class CarModelServiceImpl implements ICarModelService {

	@Resource
	private CarModelDao carModelDao;
	
	
}
