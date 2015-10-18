package cn.evchar.common.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

@Entity
@Table(name = "evchar_car_model")
public class CarModel extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	/** 车型名称 **/
	@Column(name = "name")
	private String name;
	
	

	/**
	 * 品牌，如比亚迪、特斯拉，需要数据字典
	 */
	@Column(name = "brand")
	private String brand;

	/**
	 * 细分型号，如秦、唐，需要数据字典
	 */
	// TODO:依赖数据字典
	@Column(name = "model")
	private String model;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
