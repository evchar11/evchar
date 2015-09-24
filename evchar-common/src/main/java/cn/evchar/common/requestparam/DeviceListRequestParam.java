package cn.evchar.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;

public class DeviceListRequestParam {
	private String carModel;
	@NotBlank
	private String longitude;
	@NotBlank
	private String latitude;

	private String distCode;

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

	public String getDistCode() {
		return distCode;
	}

	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}

}
