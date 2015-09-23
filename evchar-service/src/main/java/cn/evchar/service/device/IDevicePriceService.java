package cn.evchar.service.device;

public interface IDevicePriceService {
	/**
	 * 计算设备的计电单价
	 * @param deviceId
	 * @return
	 */
	Long getDevicePrice(Long deviceId);

	/**
	 * 计算可用度数
	 * @param usefulMoney
	 * @param price
	 * @return
	 */
	Long calculateDegree(Long usefulMoney, Long price);
	
	/**
	 * 计算单设备可用度数
	 * @param usefulMoney
	 * @param deviceId
	 * @return
	 */
	Long calculateDegreeByDeviceId(Long usefulMoney, Long deviceId);

	/**
	 * 转换度数为价钱
	 * @param degree
	 * @param price
	 * @return
	 */
	Long calculateMoney(Long degree, Long price);

	/**
	 * 转换度数为价钱
	 * @param degree
	 * @param deviceId
	 * @return
	 */
	Long calculateMoneyByDeviceId(Long degree, Long deviceId);
}
