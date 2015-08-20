package cn.evchar.common.entity.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import cn.evchar.common.entity.AbstractEntity;


/**
 * @author wangfeng@evchar.cn
 * 充电消费订单
 */
@Entity
@Table(name = "evchar_order")
public class Order extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * 订单id（唯一标识）
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 用户id
	 */
	@Column(name="user_id")
	private Long userId;
	
	/**
	 * 用户支付时的设备mac地址
	 */
	@Column(name="mac_id")
	private String macId;
	
	/**
	 * 设备id
	 */
	@Column(name="device_id")
	private Long deviceId;
	
	/**
	 * 订单状态
	 */
	@Column(name="status")
	private int status;
	
	/**
	 * 总价格,单位：分
	 */
	@Column(name="total_price")
	private Long totalPrice;
	
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time")
	private Date updateTime;
	
	/**
	 * 版本号，乐观锁事务控制
	 */
	@Version
	@Column(name="version")
	private int version;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
