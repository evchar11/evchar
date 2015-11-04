package cn.evchar.device.hardware.protocol.receive;

import io.netty.channel.ChannelHandlerContext;
import cn.evchar.device.hardware.protocol.DefaultStatus;
import cn.evchar.device.hardware.protocol.StatusHandler;
import cn.evchar.device.hardware.protocol.types.DataType;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;

public class CapacityStatus extends DefaultStatus {

	@Override
	public DataType getDataType() {
		return DataType.UPLOAD_CAPACITY;
	}

	@Override
	public int getContentLength() {
		return 1;
	}
	
	private int capacity;

	@Override
	public void setContent(int[] content) {
		capacity = content[0];

	}

	public int getCapacity() {
		return capacity;
	}

	public void setState(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public void handleBy(StatusHandler handler, ChannelHandlerContext ctx) {
		handler.handle(this, ctx);
	}

}
