package cn.evchar.device.hardware.protocol;

public interface DeviceProtocol {
	public static final String SN_BROADCAST = "FFFFFFFF";
	public static final int START_1 = 0x45;
	public static final int START_2 = 0x56;
	public static final int END_1 = START_1;
	public static final int END_2 = 0x43;

	public static final int ESCAPE = START_1;

	public static final String HEX_CHARS = "0123456789ABCDEFabcdef";

}
