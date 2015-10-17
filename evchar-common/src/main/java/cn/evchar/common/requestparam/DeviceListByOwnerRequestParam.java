package cn.evchar.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;

public class DeviceListByOwnerRequestParam {

	@NotBlank
	private String wechatId;

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

}
