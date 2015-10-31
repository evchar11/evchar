package cn.evchar.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;

public class DeviceRequestParamBind {
	private String wechatId;
	@NotBlank
	private String deviceId;

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
