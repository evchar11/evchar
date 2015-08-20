package cn.evchar.common.entity.pay;

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
 * 余额充值订单
 */
@Entity
@Table(name="evchar_payment_order")
public class PaymentOrder extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 充值订单id
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 支付平台支付单号，如微信平台的微信支付单号
	 */
	@Column(name="pay_id")
	private String payId;
	
	/**
	 * 用户id
	 */
	@Column(name="user_id")
	private Long userId;
	
	/**
	 * 支付订单状态
	 */
	@Column(name="status")
	private int status;
	
	/**
	 * 用户支付时的设备mac地址
	 */
	@Column(name="mac_id")
	private String macId;
	
	/**
	 * 支付平台返回的用户支付的时间
	 */
	@Column(name="pay_time")
	private Date payTime;
	
	/**
	 * 支付凭据生成时间
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

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
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

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
