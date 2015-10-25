package cn.evchar.device.hardware.protocol.receive;

import io.netty.channel.ChannelHandlerContext;
import cn.evchar.device.hardware.protocol.DefaultStatus;
import cn.evchar.device.hardware.protocol.StatusHandler;
import cn.evchar.device.hardware.protocol.types.DataType;

public class ServerPortStatus extends DefaultStatus {
	private int port;

	@Override
	public DataType getDataType() {
		return DataType.UPLOAD_SERVER_PORT;
	}

	@Override
	public int getContentLength() {
		return 2;
	}

	@Override
	public void setContent(int[] content) {
		port = 0x100 * content[0] + content[1];
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void handleBy(StatusHandler handler, ChannelHandlerContext ctx) {
		handler.handle(this, ctx);
	}
	


}
