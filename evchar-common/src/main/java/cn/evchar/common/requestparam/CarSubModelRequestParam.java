package cn.evchar.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;

public class CarSubModelRequestParam {

	@NotBlank
	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
