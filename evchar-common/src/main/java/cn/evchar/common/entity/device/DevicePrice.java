package cn.evchar.common.entity.device;

import javax.persistence.Column;
import javax.persistence.Id;

import cn.evchar.common.entity.AbstractEntity;

/**
 * 设备的计价
 * @author wangfeng@evchar.cn
 */
@SuppressWarnings("serial")
public class DevicePrice extends AbstractEntity{
	
	/**
	 * 自增主键
	 */
	@Id
	private Long id;
	
	/**
	 * 设备id
	 */
	@Column(name = "device_id")
	private Long deviceId;
	
	/**
	 * 单价
	 */
	@Column(name = "price")
	private Long price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	
}
