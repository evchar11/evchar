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
	@Column(name = "peripheral")
	private String peripheral;

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

	/**
	 * 当前设备功率
	 * */
	@Column(name = "capacity")
	private Integer capacity;

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Long getModel() {
		return model;
	}

	public void setModel(Long model) {
		this.model = model;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPeripheral() {
		return peripheral;
	}

	public void setPeripherals(String peripheral) {
		this.peripheral = peripheral;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
