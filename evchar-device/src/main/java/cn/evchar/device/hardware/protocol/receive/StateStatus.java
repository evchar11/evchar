package cn.evchar.device.hardware.protocol.receive;

import io.netty.channel.ChannelHandlerContext;
import cn.evchar.device.hardware.protocol.DefaultStatus;
import cn.evchar.device.hardware.protocol.StatusHandler;
import cn.evchar.device.hardware.protocol.types.DataType;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;

public class StateStatus extends DefaultStatus {

	private DeviceStateType state;

	@Override
	public DataType getDataType() {
		return DataType.UPLOAD_STATE;
	}

	@Override
	public int getContentLength() {
		return 1;
	}

	@Override
	public void setContent(int[] content) {
		// TODO:目前由电脑端负责交换大小端
		state = DeviceStateType.fromInteger(content[0]);

	}

	public DeviceStateType getState() {
		return state;
	}

	public void setState(DeviceStateType state) {
		this.state = state;
	}

	@Override
	public void handleBy(StatusHandler handler, ChannelHandlerContext ctx) {
		handler.handle(this, ctx);
	}

}
