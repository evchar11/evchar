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
	void onEndCharging(Long deviceId);

	/**
	 * 充满
	 */
	void onFull(Long deviceId);

	/**
	 * 电量度数变化
	 */
	void onDegreeChange(Long deviceId, Long degree);
}
