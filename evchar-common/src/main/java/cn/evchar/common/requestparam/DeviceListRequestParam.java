package cn.evchar.common.requestparam;

import javax.validation.constraints.NotNull;

public class DeviceListRequestParam {
	private String carModel;
	@NotNull
	private String longitude;
	@NotNull
	private String latitude;

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
