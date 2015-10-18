package cn.evchar.common.requestparam;

public class DeviceApproveParam {

	private String wechatId;
	private Long commId;

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public Long getCommId() {
		return commId;
	}

	public void setCommId(Long commId) {
		this.commId = commId;
	}
}
