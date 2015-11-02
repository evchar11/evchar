package cn.evchar.device.hardware.protocol.types;

import java.util.HashMap;
import java.util.Map;

public enum DeviceStateType {

	// 上电状态 未连接 已连接 充电中 已充满
	// 未上电-- 0x00-- 0x01-- ——-- 0x03--
	// 逻辑上电 0x10-- 0x11-- 0x12-- 0x13--
	// 物理上电 0x20-- 0x21-- 0x22-- 0x23--
	// 物理断电 0x30-- 0x31-- ——-- 0x33--

	STATE0(0x00), STATE1(0x01), STATE2(0x02), STATE3(0x03), STATE4(0x10), STATE5(
			0x11), STATE6(0x12), STATE7(0x13), STATE8(0x20), STATE9(0x21), STATEA(
			0x22), STATEB(0x23), STATEC(0x30), STATED(0x31), STATEE(0x32), STATEF(
			0x33), POWER_ON(0x01), POWER_OFF(0x02), OFFLINE(0xFF);

	private final Integer value;

	DeviceStateType(Integer value) {
		this.value = value;
	}

	private static final Map<Integer, DeviceStateType> integerToEnum = new HashMap<Integer, DeviceStateType>();

	static {
		for (DeviceStateType op : values()) {
			if (op != POWER_ON && op != POWER_OFF && op != OFFLINE) { // TODO:这三个状态要从此类中剥离
				integerToEnum.put(op.toInteger(), op);
			}
		}
	}

	public static DeviceStateType fromInteger(Integer symbol) {
		return integerToEnum.get(symbol);
	}

	public Integer toInteger() {
		return value;
	}

	public boolean isEnegrized() {
		return ((value & 0x30) == 0x20 || (value & 0x30) == 0x10);
	}

	public boolean isDisconnected() {
		return ((value & 0x03) == 0x00);
	}

	public boolean isConnectedOnly() {
		return ((value & 0x03) == 0x01);
	}

	public boolean isCharging() {
		return ((value & 0x03) == 0x02);
	}

	public boolean isFull() {
		return ((value & 0x03) == 0x03);
	}
}
