package cn.evchar.common.entity.coupon;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;
import cn.evchar.common.util.serializer.CustomDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author wangfeng@evchar.cn 优惠券
 */
@Entity
@Table(name = "evchar_coupon")
public class Coupon extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 优惠券的id，
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 状态： 1。未使用， 2.已使用，3.已过期
	 */
	@Column(name = "use_status")
	private Byte useStatus = 1;

	public Byte getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Byte useStatus) {
		this.useStatus = useStatus;
	}

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 可抵用现金（单位:分）
	 */
	@Column(name = "c_value")
	private Long cValue;

	public Long getcValue() {
		return cValue;
	}

	public void setcValue(Long cValue) {
		this.cValue = cValue;
	}

	/**
	 * 需要满多少金额（单位：分）
	 */
	@Column(name = "c_limit")
	private Long cLimit;

	public Long getcLimit() {
		return cLimit;
	}

	public void setcLimit(Long cLimit) {
		this.cLimit = cLimit;
	}

	/**
	 * 描述 例如：满50-10券
	 */
	@Column(name = "c_describe")
	private String cDescribe;

	public String getcDescribe() {
		return cDescribe;
	}

	public void setcDescribe(String cDescribe) {
		this.cDescribe = cDescribe;
	}

	/**
	 * 生成时间
	 */
	@Column(name = "create_time")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createTime = new Date();

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date updateTime;

	/**
	 * 失效时间
	 */
	@Column(name = "expire_time")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date expireTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
