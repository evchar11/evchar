package cn.evchar.common.entity.device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

@Entity
@Table(name = "evchar_device")
public class Device extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 所有者用户id
	 */
	@Column(name = "owner")
	private Long owner;

	/**
	 * 硬件识别码
	 */
	@Column(name = "sn")
	private String sn;

	/**
	 * 型号
	 */
	@Column(name = "model")
	private Long model;

	/**
	 * 城市，需要数据字典
	 */
	@Column(name = "city")
	private String city;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "latitude")
	private String latitude;

	/**
	 * 地址描述
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 硬件其他外设
	 */
	@Column(name = "peripherals")
	private String peripherals;

	@Column(name = "ip")
	private String ip;

	@Column(name = "server_ip")
	private String serverIp;

	@Column(name = "server_port")
	private String serverPort;

	/**
	 * 设备图片地址
	 */
	@Column(name = "pic")
	private String pic;

	@Column(name = "battery")
	private Integer battery;

	/**
	 * 设备当前状态，需要数据字典
	 */
	@Column(name = "status")
	private String status;

}
