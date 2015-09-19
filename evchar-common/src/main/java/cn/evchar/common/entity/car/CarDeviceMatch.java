package cn.evchar.common.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

@Entity
@Table(name = "evchar_car_device_match")
public class CarDeviceMatch extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "car_model")
	private Long carModel;

	@Column(name = "device_model")
	private Long deviceModel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCarModel() {
		return carModel;
	}

	public void setCarModel(Long carModel) {
		this.carModel = carModel;
	}

	public Long getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(Long deviceModel) {
		this.deviceModel = deviceModel;
	}

}
