package cn.evchar.device.hardware.protocol.receive;

import io.netty.channel.ChannelHandlerContext;
import cn.evchar.device.hardware.protocol.DefaultStatus;
import cn.evchar.device.hardware.protocol.StatusHandler;
import cn.evchar.device.hardware.protocol.types.DataType;

public class ServerIpStatus extends DefaultStatus {
	private String ip;

	@Override
	public DataType getDataType() {
		return DataType.UPLOAD_SERVER_IP;
	}

	@Override
	public int getContentLength() {
		return 4;
	}

	@Override
	public void setContent(int[] content) {
		StringBuilder strBuilder = new StringBuilder();
		for (int value : content) {
			strBuilder.append(value).append(".");
		}
		strBuilder.deleteCharAt(strBuilder.length() - 1);
		ip = strBuilder.toString();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public void handleBy(StatusHandler handler, ChannelHandlerContext ctx) {
		handler.handle(this, ctx);
	}

}
