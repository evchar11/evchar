package cn.evchar.device.hardware.protocol;

import cn.evchar.device.hardware.protocol.types.DataType;

public class DefaultStatus implements Status {

	protected DataType dataType;
	protected int[] sn = BLANK_SN;

	public int getContentLength() {
		return 0;
	};
}
