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

	@Column(name="car_model")
	private Long carModel;

	@Column(name="device_model")
	private Long deviceModel;
	
	

}
