package cn.evchar.device.hardware.protocol.types;

import java.util.HashMap;
import java.util.Map;

public enum DeviceState {
	
	IDLE("01"), RESERVED("02"), CHARGING("03"), FULL("04");

	private final String str;

	DeviceState(String str) {
		this.str = str;
	}

	private static final Map<String, DeviceState> stringToEnum = new HashMap<String, DeviceState>();

	static {
		for (DeviceState op : values())
			stringToEnum.put(op.toString(), op);
	}

	public static DeviceState fromString(String symbol) {
		return stringToEnum.get(symbol);
	}

	@Override
	public String toString() {
		return str;
	}
}
