package cn.evchar.common.requestparam;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class DeviceMatchUserRequestParam {
	//微信openId
	@NotBlank
	private String wechatId; 
	//设备id
	@NotNull
	private Long deviceId; 
	//用户carid
	@NotNull
	private Long carId;
	//设备id
	private String macId;
	public String getWechatId() {
		return wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public String getMacId() {
		return macId;
	}
	public void setMacId(String macId) {
		this.macId = macId;
	}
	
}
