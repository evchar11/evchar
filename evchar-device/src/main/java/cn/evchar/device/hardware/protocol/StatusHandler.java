package cn.evchar.device.hardware.protocol;

import io.netty.channel.ChannelHandlerContext;
import cn.evchar.device.hardware.protocol.receive.BatteryStatus;
import cn.evchar.device.hardware.protocol.receive.BootCompletedStatus;
import cn.evchar.device.hardware.protocol.receive.ModelStatus;
import cn.evchar.device.hardware.protocol.receive.PheriStatus;
import cn.evchar.device.hardware.protocol.receive.PowerStatus;
import cn.evchar.device.hardware.protocol.receive.ServerIpStatus;
import cn.evchar.device.hardware.protocol.receive.ServerPortStatus;
import cn.evchar.device.hardware.protocol.receive.StateStatus;

public interface StatusHandler {

	void handle(BatteryStatus batteryStatus, ChannelHandlerContext ctx);

	void handle(BootCompletedStatus bootCompletedStatus, ChannelHandlerContext ctx);

	void handle(ModelStatus modelStatus, ChannelHandlerContext ctx);

	void handle(PheriStatus pheriStatus, ChannelHandlerContext ctx);

	void handle(PowerStatus powerStatus, ChannelHandlerContext ctx);

	void handle(ServerIpStatus serverIpStatus, ChannelHandlerContext ctx);

	void handle(ServerPortStatus serverPortStatus, ChannelHandlerContext ctx);

	void handle(StateStatus stateStatus, ChannelHandlerContext ctx);
}
