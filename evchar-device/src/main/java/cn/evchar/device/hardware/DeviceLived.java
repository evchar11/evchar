package cn.evchar.device.hardware;

/**
 * 所有处于工作状态中的设备，记录在内存中
 * 
 * TODO:需要持久化，保证异常情况后可恢复
 */
public class DeviceLived {
	private String sn; // 硬件识别码
	private String ip;
	private String port;

	
	public DeviceLived() {
		super();
		this.sn = "121212";
		this.ip = "121212";
		this.port = "123";
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

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
