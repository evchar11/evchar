package cn.evchar.device.hardware.protocol.types;

import java.util.HashMap;
import java.util.Map;

public enum DataType {
	READ_SN(1), READ_MODEL(2), READ_PHERI(3), READ_IP(4), READ_PORT(5), READ_BATTERY(
			6), READ_STATUS(7), RESET(8), SET_IP(0x14), SET_PORT(0x15), SET_STATUS(
			0x17), SET_CAPACITY(0x12), UPLOAD_SN(0x81), UPLOAD_MODEL(0x82), UPLOAD_PHERI(
			0x83), UPLOAD_SERVER_IP(0x84), UPLOAD_SERVER_PORT(0x85), UPLOAD_POWER(
			0x86), UPLOAD_STATE(0x87), UPLOAD_BOOT_COMPLETED(0x88),UPLOAD_CAPACITY(0x88), UPLOAD_BATTERY(
			0x89);

	public final static int DATA_TYPE_LENGTH = 2;

	private static final Map<Integer, DataType> IntegerToEnum = new HashMap<Integer, DataType>();

	static {
		for (DataType dt : values()) {
			IntegerToEnum.put(dt.strDataType, dt);
		}
	}

	private Integer strDataType;

	DataType(Integer str) {
		this.strDataType = str;
	}

	public Integer toTransitiveInteger() {
		return strDataType;
	}

	public static DataType fromInteger(Integer str) {
		return IntegerToEnum.get(str);
	}
}
