package cn.evchar.device.hardware.protocol;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.evchar.device.hardware.protocol.receive.BatteryStatus;
import cn.evchar.device.hardware.protocol.receive.BootCompletedStatus;
import cn.evchar.device.hardware.protocol.receive.ModelStatus;
import cn.evchar.device.hardware.protocol.receive.PheriStatus;
import cn.evchar.device.hardware.protocol.receive.PowerStatus;
import cn.evchar.device.hardware.protocol.receive.ServerIpStatus;
import cn.evchar.device.hardware.protocol.receive.ServerPortStatus;
import cn.evchar.device.hardware.protocol.receive.SnStatus;
import cn.evchar.device.hardware.protocol.receive.StateStatus;
import cn.evchar.device.hardware.protocol.types.DataType;

public class StatusFactory {
	private static Logger logger = LoggerFactory.getLogger(StatusFactory.class);

	public void receive(byte[] bytes) {

	}

	// TODO:测试过程中直接抛错，后期转为返回IllegalStatus
	public Status getStatus(int[] array) {
		DefaultStatus status;
		DataType dataType = DataType.fromInteger(array[0]);
		if (dataType == null) {
			logger.error("数据类型未知: " + Arrays.toString(array));
			throw new IllegalStateException();
		} else {
			switch (dataType) {
			case UPLOAD_SN:
				status = new SnStatus();
				break;
			case UPLOAD_BATTERY:
				status = new BatteryStatus();
				break;
			case UPLOAD_SERVER_IP:
				status = new ServerIpStatus();
				break;
			case UPLOAD_MODEL:
				status = new ModelStatus();
				break;
			case UPLOAD_PHERI:
				status = new PheriStatus();
				break;
			case UPLOAD_SERVER_PORT:
				status = new ServerPortStatus();
				break;
			case UPLOAD_POWER:
				status = new PowerStatus();
				break;
			case UPLOAD_BOOT_COMPLETED:
				status = new BootCompletedStatus();
				break;
			case UPLOAD_STATE:
				status = new StateStatus();
				break;
			default:
				logger.error("数据类型错误" + Arrays.toString(array));
				throw new IllegalStateException();
			}
		}
		int contentIndex = 1 + Protocol.BLANK_SN.length + 1;
		int len = contentIndex + status.getContentLength() + 1; // 数据类型+SN+内容长度+内容+校验和
		if (array.length != len) {
			logger.error("数据长度错误" + Arrays.toString(array) + " " + array.length
					+ "!=" + len);
			throw new IllegalStateException();
		}
		status.setSn(ArrayUtils
				.subarray(array, 1, 1 + Protocol.BLANK_SN.length));
		status.setContent(ArrayUtils.subarray(array, contentIndex, contentIndex
				+ status.getContentLength()));
		return status;

	}
}
