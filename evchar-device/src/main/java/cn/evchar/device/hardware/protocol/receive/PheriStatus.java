package cn.evchar.device.hardware.protocol.receive;

import io.netty.channel.ChannelHandlerContext;
import cn.evchar.device.hardware.protocol.DefaultStatus;
import cn.evchar.device.hardware.protocol.StatusHandler;
import cn.evchar.device.hardware.protocol.types.DataType;

public class PheriStatus extends DefaultStatus {
	private String pheri;

	@Override
	public DataType getDataType() {
		return DataType.UPLOAD_PHERI;
	}

	@Override
	public int getContentLength() {
		return 2;
	}

	@Override
	public void setContent(int[] content) {
		pheri = Integer.toHexString(content[0])
				+ Integer.toHexString(content[1]);
	}

	public String getPheri() {
		return pheri;
	}

	public void setPheri(String pheri) {
		this.pheri = pheri;
	}

	@Override
	public void handleBy(StatusHandler handler, ChannelHandlerContext ctx) {
		handler.handle(this, ctx);
	}

}
