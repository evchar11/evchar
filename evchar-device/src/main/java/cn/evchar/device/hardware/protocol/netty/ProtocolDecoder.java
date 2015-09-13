package cn.evchar.device.hardware.protocol.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import cn.evchar.common.util.ConvertUtils;
import cn.evchar.device.hardware.protocol.Protocol;

public class ProtocolDecoder extends ByteToMessageDecoder {

	public int[] remain = new int[] {};
	public int[] temp;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		// TODO:未完成
		byte[] received = new byte[in.readableBytes()];
		in.readBytes(received);
		temp = ConvertUtils.bytesToInteger(received, received.length);
		int len = temp.length;
		temp = ArrayUtils.addAll(remain, temp);
		if (len < Protocol.MIN_LENGTH) {
			int escape = 0;
			boolean isEscape = false;
			boolean isBreak = false;
			int endIndex = 0;
			if (temp[0] == Protocol.BIT_ESCAPE && temp[1] == Protocol.BIT_START) {
				for (int i = 2;; i++) {
					if (isEscape) {
						switch (temp[i]) {
						case Protocol.BIT_ESCAPE:
							escape++;
							break;
						case Protocol.BIT_END:
							endIndex = i;
							isBreak = true;
							break;
						default:
							break;
						}
					} else {
						if (temp[i] == Protocol.BIT_ESCAPE) {
							isEscape = true;
						} else {

						}
					}
				}

			}
		}
	}
}
