package cn.evchar.service.device.impl;

public class DevicePriceServiceImpl {

	private static final Long DEFAULT_PRICE = 200L;

	public Long getDevicePrice(Long deviceId) {
		if (deviceId == null) {
			throw new IllegalArgumentException("device id can't be null");
		}
		return DEFAULT_PRICE;
	}
}
