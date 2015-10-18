package cn.evchar.device.hardware.protocol.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.evchar.common.util.ConvertUtils;
import cn.evchar.device.hardware.protocol.Protocol;
import cn.evchar.device.hardware.protocol.StatusFactory;

public class ProtocolDecoder extends ByteToMessageDecoder {

	private static Logger logger = LoggerFactory
			.getLogger(ProtocolDecoder.class);

	public int[] remain = new int[] {};
	public int[] temp;
	public int[] unescaped;
	private StatusFactory statusFactory = new StatusFactory();

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		byte[] received = new byte[in.readableBytes()];
		in.readBytes(received);
		temp = ConvertUtils.bytesToInteger(received, received.length);
		unescaped = new int[temp.length];
		temp = ArrayUtils.addAll(remain, temp);
		logger.info("temp:" + Arrays.toString(temp));
		boolean isEscape = false;
		boolean isContent = false;
		int unescapeIndex = 0;
		int remainIndex = 0;
		for (int i = 0; i < temp.length; i++) {
			if (isEscape) {
				switch (temp[i]) {
				case Protocol.BIT_START:// 必定是一段数据的开头
					if (isContent) {
						logger.error("连续的开头");
						unescaped = new int[temp.length];
						unescapeIndex = 0;
					} else {
						logger.info("数据头" + i);
					}
					isContent = true;
					break;
				case Protocol.BIT_END:
					if (isContent) {
						logger.info("数据尾" + i);
						int[] result = ArrayUtils.subarray(unescaped, 0,
								unescapeIndex);
						logger.info("总数据:" + Arrays.toString(result));
						int checksum = 0;
						for (int j = 0; j < result.length - 1; j++) {
							checksum += result[j];
						}
						if (checksum % 0x100 != result[result.length - 1]) {
							out.add(statusFactory.getStatus(result));
						} else {
							logger.error("校验和不符" + checksum + "!="
									+ result[result.length - 1]);
						}
						unescapeIndex = 0;
						unescaped = new int[temp.length];
						isContent = false;
					} else {
						logger.info("没有数据头的数据尾");
					}
					remainIndex = i + 1;
					break;
				default:
					if (isContent) {
						unescaped[unescapeIndex++] = temp[i];
					} else {
						logger.error("没有数据头的数据");
					}
					break;
				}
				isEscape = false;
			} else {
				if (temp[i] == Protocol.BIT_ESCAPE) {
					isEscape = true;
				} else {
					isEscape = false;
					if (isContent) {
						unescaped[unescapeIndex++] = temp[i];
					}
				}
			}
		}
		remain = ArrayUtils.subarray(temp, remainIndex, temp.length);
		logger.info("remain " + ArrayUtils.toString(remain));
	}
}
