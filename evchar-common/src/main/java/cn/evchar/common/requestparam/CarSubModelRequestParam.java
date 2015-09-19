package cn.evchar.common.requestparam;

import javax.validation.constraints.NotNull;

public class CarSubModelRequestParam {

	@NotNull
	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
