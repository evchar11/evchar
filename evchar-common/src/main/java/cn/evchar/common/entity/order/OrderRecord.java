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
 * 订单支付记录
 */
@Entity
@Table(name="evchar_order_record")
public class OrderRecord extends AbstractEntity{
	private static final long serialVersionUID = 1L;

	/**
	 * 支付记录id
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
	 * 所属订单id
	 */
	@Column(name="order_id")
	private Long orderId;
	
	/**
	 * 支付类型：1.余额支付 ，2.积分支付，3.优惠券抵扣
	 */
	@Column(name="type")
	private Integer type; 
	
	/**
	 * 消费金额：
	 * 单位： 1.余额支付 （分），2.积分支付（个），3.优惠券抵扣（分）
	 */
	@Column(name="value")
	private Long value;
	
	/**
	 * type=3时 优惠券id
	 */
	@Column(name="coupon_id")
	private Long couponId;
	
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


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
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
	
}
