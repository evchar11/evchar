package cn.evchar.device.hardware.protocol;

public interface Protocol {
	public static final int MIN_LENGTH = 10;
	public static final int BIT_ESCAPE = 0x45;
	public static final int BIT_START = 0x56;
	public static final int BIT_END = 0x43;
	public static final String HEX_CHARS = "0123456789ABCDEFabcdef";
}
