package cn.evchar.common.requestparam;

import java.util.Date;

public class DeviceOperationParam {

	public static final String ON = "on"; // 打开
	public static final String OFF = "off";// 关闭
	public static final String DISABLE = "disable"; // 禁用，不提供给其他用户使用
	public static final String ENABLE = "enable"; // 启用，提供给其他用户使用

	private String operation;
	private Long deviceId;
	private String wechatId;
	private Date time;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
