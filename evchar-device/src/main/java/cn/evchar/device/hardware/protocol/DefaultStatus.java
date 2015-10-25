package cn.evchar.device.hardware.protocol;

import io.netty.channel.ChannelHandlerContext;
import cn.evchar.common.util.ConvertUtils;
import cn.evchar.device.hardware.protocol.types.DataType;

public abstract class DefaultStatus implements Status {

	protected DataType dataType;
	protected int[] sn = BLANK_SN;
	protected String stringSn = BLANK_STRING_SN;

	public void setSn(int[] sn) {
		if (sn.length != sn.length) {
			throw new IllegalArgumentException("sn参数长度有误");
		} else {
			stringSn = ConvertUtils.integersToString(sn);
		}
		this.sn = sn;
	}

	public String getSn() {
		return stringSn;
	}

	public abstract DataType getDataType();

	public abstract int getContentLength();

	public abstract void setContent(int[] content);

	public abstract void handleBy(StatusHandler handler,
			ChannelHandlerContext ctx);
}
