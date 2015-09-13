package cn.evchar.common.entity.device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

@Entity
@Table(name = "evchar_device_model")
public class DeviceModel extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 编号，用于硬件和协议
	 */
	@Column(name="code")
	private String code;

}
