package cn.evchar.common.util;

public class ConvertUtils {

	/**
	 * 将[0x00,0xFF]的byte(实际值为[-128,127])转换为[0,255]的int
	 * 
	 * @param bytes
	 * @param bytesLength
	 * @return
	 */
	public static int[] bytesToInteger(byte[] bytes, int bytesLength) {
		int[] result = new int[bytesLength];
		for (int i = 0; i < bytesLength; i++) {
			result[i] = 0xff & bytes[i];
		}
		return result;
	}

	/**
	 * 将[0,255]转为[0x00,0xFF]的byte(实际值为[-128,127])
	 * 
	 * @param intArray
	 *            对小于0x255才可获得希望的结果，内部不检验
	 * @return
	 */
	public static byte[] IntegersToByte(int[] intArray, int length) {
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[i] = (byte) intArray[i];
		}
		return result;
	}

	public static String integersToString(int[] intArray) {
		StringBuilder s = new StringBuilder();
		for (int value : intArray) {
			s.append(StringUtils.leftPad(Integer.toHexString(value), 2, "0"));
		}
		return s.toString();
	}

}
