package cn.evchar.common.requestparam;

import java.util.Date;

public class DeviceCommentsAddParam {

	private Long deviceId;

	private String wechatId;

	private Long commRefId;

	private String content;

	private Long starLevel;

	private Date commDate;

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

	public Long getCommRefId() {
		return commRefId;
	}

	public void setCommRefId(Long commRefId) {
		this.commRefId = commRefId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Long starLevel) {
		this.starLevel = starLevel;
	}

	public Date getCommDate() {
		return commDate;
	}

	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}
}
