package cn.evchar.device.hardware.protocol.receive;

import io.netty.channel.ChannelHandlerContext;
import cn.evchar.device.hardware.protocol.DefaultStatus;
import cn.evchar.device.hardware.protocol.StatusHandler;
import cn.evchar.device.hardware.protocol.types.DataType;

public class BatteryStatus extends DefaultStatus {

	@Override
	public DataType getDataType() {
		return DataType.UPLOAD_BATTERY;
	}

	@Override
	public int getContentLength() {
		return 0;
	}

	@Override
	public void setContent(int[] content) {
	}

	@Override
	public void handleBy(StatusHandler handler, ChannelHandlerContext ctx) {
		handler.handle(this, ctx);
	}
}
