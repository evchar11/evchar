package cn.evchar.service.device.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.entity.device.DevicePrice;
import cn.evchar.dao.device.DevicePriceDao;
import cn.evchar.service.device.IDevicePriceService;

@Service
public class DevicePriceServiceImpl implements IDevicePriceService {

	private static final Long DEFAULT_PRICE = 200L;

	@Resource
	private DevicePriceDao devicePriceDao;

	@Override
	public Long getDevicePrice(Long deviceId) {
		if (deviceId == null) {
			throw new IllegalArgumentException("device id can't be null");
		} else {
			DevicePrice devicePrice = new DevicePrice();
			devicePrice.setDeviceId(deviceId);
			List<DevicePrice> priceList = devicePriceDao.findByExample(
					DevicePrice.class, devicePrice);
			if (priceList == null || priceList.size() == 0) {
				return DEFAULT_PRICE;
			} else {
				return priceList.get(0).getPrice();
			}
		}
	}

	@Override
	public Long calculateDegree(Long usefulMoney, Long price) {
		return usefulMoney/price;
	}

	@Override
	public Long calculateDegreeByDeviceId(Long usefulMoney, Long deviceId) {
		Long price = getDevicePrice(deviceId);
		return calculateDegree(usefulMoney, price);
	}

	@Override
	public Long calculateMoney(Long degree, Long price) {
		return degree*price;
		
	}

	@Override
	public Long calculateMoneyByDeviceId(Long degree, Long deviceId) {
		Long price = getDevicePrice(deviceId);
		return calculateMoney(degree, price);
	}
	
}
