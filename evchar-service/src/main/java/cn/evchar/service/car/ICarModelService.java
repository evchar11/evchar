package cn.evchar.service.car;

import java.util.Collection;

import cn.evchar.common.entity.car.CarModel;

public interface ICarModelService {
	CarModel getCarModel(Long id);

	Collection<String> getBrands();

	Collection<String> getSubModel(String brand);
}
