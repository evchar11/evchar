package cn.evchar.service.car.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.entity.car.CarModel;
import cn.evchar.dao.car.CarModelDao;
import cn.evchar.service.car.ICarModelService;

@Service
public class CarModelServiceImpl implements ICarModelService {

	@Resource
	private CarModelDao carModelDao;

	@Override
	public CarModel getCarModel(Long id) {
		return carModelDao.get(id);
	}

	@Override
	public Collection<String> getBrands() {
		List<CarModel> carModelList = carModelDao.loadAll(CarModel.class);
		Set<String> brands = new HashSet<>();
		for (CarModel model : carModelList) {
			brands.add(model.getBrand());
		}
		return brands;
	}

	@Override
	public Collection<String> getSubModel(String brand) {
		CarModel carModel = new CarModel();
		carModel.setBrand(brand);
		List<CarModel> modelList = carModelDao.findByExample(CarModel.class,
				carModel);
		List<String> subModelList = new ArrayList<>();
		for (CarModel model : modelList) {
			subModelList.add(model.getModel());
		}
		return subModelList;
	}
}
