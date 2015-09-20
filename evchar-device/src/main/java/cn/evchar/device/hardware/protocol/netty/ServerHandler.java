package cn.evchar.device.hardware.protocol.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.evchar.device.hardware.DeviceAcceptor;
import cn.evchar.device.hardware.protocol.DeviceProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(ServerHandler.class);
	private DeviceAcceptor acceptor;

	public ServerHandler(DeviceAcceptor acceptor) {
		this.acceptor = acceptor;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
//		ctx.write(new CmdReadSn(DeviceProtocol.SN_BROADCAST));
		ctx.flush();
		((ByteBuf) msg).release();
		// ctx.write(msg);
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
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
