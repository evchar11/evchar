package cn.evchar.device.hardware;

import cn.evchar.device.hardware.protocol.types.DeviceStateType;

/**
 * 所有处于工作状态中的设备，记录在内存中
 * 
 * TODO:需要持久化，保证异常情况后可恢复
 */
public class DeviceLived {
	private String sn; // 硬件识别码
	private String ip;
	private int port;
	private DeviceStateType state;

	public DeviceLived() {
		super();
		this.sn = "001";
		this.ip = "121212";
		this.port = 123;
	}

	public DeviceLived(String sn, String ip, int port, DeviceStateType state) {
		super();
		this.sn = sn;
		this.ip = ip;
		this.port = port;
		this.state = state;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public DeviceStateType getState() {
		return state;
	}

	public void setState(DeviceStateType state) {
		this.state = state;
	}

}
