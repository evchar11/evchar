package cn.evchar.device.hardware.protocol.netty;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.evchar.device.hardware.protocol.Command;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ProtocolEncoder extends MessageToByteEncoder<Command> {
	private static final Logger logger = LoggerFactory
			.getLogger(ProtocolEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, Command cmd, ByteBuf out)
			throws Exception {
		byte[] bytes = cmd.toBytes();
		logger.info("发送数据至:" + Arrays.toString(bytes));
		out.writeBytes(bytes);
		ctx.flush();
	}
}
