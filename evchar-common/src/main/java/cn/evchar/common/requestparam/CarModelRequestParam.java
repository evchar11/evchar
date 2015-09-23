package cn.evchar.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;


public class CarModelRequestParam {
	@NotBlank
	private String carModelId;

	public String getCarModelId() {
		return carModelId;
	}

	public void setCarModelId(String carModelId) {
		this.carModelId = carModelId;
	}

}
