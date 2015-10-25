package cn.evchar.device.hardware.protocol;

public interface Protocol {
	public static final int MIN_LENGTH = 10;
	public static final int BIT_ESCAPE = 0x45;
	public static final int BIT_START = 0x56;
	public static final int BIT_END = 0x43;
	public static final String HEX_CHARS = "0123456789ABCDEFabcdef";

	public static final int[] BLANK_ARRAY = new int[] {};
	public static final int[] BLANK_SN = new int[] { 255, 255, 255, 255 };
	public static final String BLANK_STRING_SN = "FFFFFFFF";
	public static final int LENGTH_FLAG = 4;// 头尾长度
	public static final int LENGTH_CONTENT_0_ARGS = 6;// 协议固定部分除头尾长度
}
