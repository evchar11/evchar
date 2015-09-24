package cn.evchar.common.requestparam;

import javax.validation.constraints.NotNull;

public class SetDefaultCarRequestParam {
	@NotNull
	private String wechatId;

	@NotNull
	private String carId;

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

}
