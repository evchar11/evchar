package cn.evchar.device.hardware.protocol.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.evchar.device.hardware.DeviceAcceptor;
import cn.evchar.device.hardware.protocol.DeviceProtocol;
import cn.evchar.device.hardware.protocol.impl.SetStateCommand;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(ServerHandler.class);

	private DeviceAcceptor acceptor;

	public ServerHandler(DeviceAcceptor acceptor) {
		super();
		this.acceptor = acceptor;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		logger.info("connected!");
		// ctx.write(new CmdReadSn(DeviceProtocol.SN_BROADCAST));
		// ctx.write(new SetStateCommand(DeviceStateType.ENERGIZED));
		// ctx.flush();
		acceptor.add(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.info("disconnected");
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		acceptor.remove(ctx);
		super.channelInactive(ctx);
	}
}
