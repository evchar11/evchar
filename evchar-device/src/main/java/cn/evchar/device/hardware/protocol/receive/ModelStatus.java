package cn.evchar.device.hardware.protocol.receive;

import io.netty.channel.ChannelHandlerContext;
import cn.evchar.device.hardware.protocol.DefaultStatus;
import cn.evchar.device.hardware.protocol.StatusHandler;
import cn.evchar.device.hardware.protocol.types.DataType;

public class ModelStatus extends DefaultStatus {
	private String model;

	@Override
	public DataType getDataType() {
		return DataType.UPLOAD_MODEL;
	}

	@Override
	public int getContentLength() {
		return 2;
	}

	@Override
	public void setContent(int[] content) {
		model = Integer.toHexString(content[0])
				+ Integer.toHexString(content[1]);
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public void handleBy(StatusHandler handler, ChannelHandlerContext ctx) {
		handler.handle(this, ctx);
	}
}
