package cn.evchar.common.requestparam;

import javax.validation.constraints.NotNull;

public class DeviceCommentsParam {

	@NotNull
	private Long deviceId;

	@NotNull
	private int pageSize;

	@NotNull
	private int pageNum;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
