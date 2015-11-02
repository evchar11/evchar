package cn.evchar.device.hardware.protocol;

import org.apache.commons.lang3.StringUtils;

import cn.evchar.device.hardware.protocol.types.DataType;

public abstract class DefaultCommand implements Command {

	protected DataType dataType;
	protected int[] sn = BLANK_SN;

	@Override
	public byte[] toBytes() {
		int[] content = new int[getArgsLength() + LENGTH_CONTENT_0_ARGS];
		int escape = 0;
		int index = 0;
		int checksum = 0;
		content[index++] = getDataType().toTransitiveInteger();
		for (int value : sn) {
			content[index++] = value;
		}
		content[index++] = getArgsLength();
		for (int value : getArgs()) {
			content[index++] = value;
		}
		for (int value : content) // 统计转义字符个数
		{
			if (value == BIT_ESCAPE) {
				escape++;
				checksum += value;
			}
		}
		byte[] result = new byte[LENGTH_FLAG + content.length + escape + 1];
		index = 0;
		result[index++] = BIT_ESCAPE;
		result[index++] = BIT_START;
		for (int value : content) {
			if (value == BIT_ESCAPE) {
				result[index++] = BIT_ESCAPE;
			}
			result[index++] = (byte) value;
			checksum += value;
		}
		result[index++] = (byte) (checksum % 0x100);
		result[index++] = BIT_ESCAPE;
		result[index++] = BIT_END;
		return result;
	}

	public void setSn(String sn) {
		if (sn.length() == 8 && StringUtils.containsOnly(sn, HEX_CHARS)) {
			for (int i = 0; i < sn.length() / 2; i++) {
				this.sn[i] = Integer.parseInt(
						StringUtils.substring(sn, 2 * i, 2 * (i + 1)), 16);
			}
		} else {
			throw new IllegalArgumentException("不合法的sn值：" + sn);
		}
	}

	public int[] getSn() {
		return sn;
	}

	public abstract DataType getDataType();

	public abstract int getArgsLength();

	protected abstract int[] getArgs();
}
