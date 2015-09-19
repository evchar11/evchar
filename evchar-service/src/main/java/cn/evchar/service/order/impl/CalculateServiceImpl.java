package cn.evchar.service.order.impl;

import cn.evchar.service.order.ICalculateService;

public class CalculateServiceImpl implements ICalculateService{
	public static final double DEFAULT_PRICE_PER_KILOWATT = 1;

	@Override
	public double generateDegree(Long money) {
		return money/DEFAULT_PRICE_PER_KILOWATT;
	}

}
