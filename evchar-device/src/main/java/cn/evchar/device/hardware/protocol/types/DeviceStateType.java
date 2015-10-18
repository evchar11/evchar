package cn.evchar.device.hardware.protocol.types;

import java.util.HashMap;
import java.util.Map;

public enum DeviceStateType {
	// 空闲/已预约/已上电/正在充电/已充满
	IDLE(1), RESERVED(2), ENERGIZED(3), CHARGING(4), FULL(5),OFF(6);

	private final Integer value;

	DeviceStateType(Integer value) {
		this.value = value;
	}

	private static final Map<Integer, DeviceStateType> integerToEnum = new HashMap<Integer, DeviceStateType>();

	static {
		for (DeviceStateType op : values())
			integerToEnum.put(op.toInteger(), op);
	}

	public static DeviceStateType fromInteger(Integer symbol) {
		return integerToEnum.get(symbol);
	}

	public Integer toInteger() {
		return value;
	}
}
