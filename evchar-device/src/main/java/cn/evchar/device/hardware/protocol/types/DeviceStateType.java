package cn.evchar.device.hardware.protocol.types;

import java.util.HashMap;
import java.util.Map;

public enum DeviceStateType {
	// 空闲/已预约/已上电/正在充电/已充满
	IDLE("01"), RESERVED("02"), ENERGIZED("03"), CHARGING("04"), FULL("05"),OFF("06");

	private final String str;

	DeviceStateType(String str) {
		this.str = str;
	}

	private static final Map<String, DeviceStateType> stringToEnum = new HashMap<String, DeviceStateType>();

	static {
		for (DeviceStateType op : values())
			stringToEnum.put(op.toString(), op);
	}

	public static DeviceStateType fromString(String symbol) {
		return stringToEnum.get(symbol);
	}

	@Override
	public String toString() {
		return str;
	}
}
