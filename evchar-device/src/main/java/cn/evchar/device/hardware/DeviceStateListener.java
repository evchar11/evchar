package cn.evchar.device.hardware;

public interface DeviceStateListener {
	/**
	 * 上电
	 */
	void onEnergized(Long deviceId);

	/**
	 * 开始充电
	 */
	void onStartCharging(Long deviceId);

	/**
	 * 结束充电
	 */
	void onEndCharging(Long deviceId, Long degree);

	/**
	 * 充满
	 */
	void onFull(Long deviceId, Long degree);

	/**
	 * 电量度数变化
	 * @param deviceId
	 * @param degree 当前使用电量
	 */
	void onDegreeChange(Long deviceId, Long degree);
}
