package cn.evchar.common.entity.coupon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

@Entity
@Table(name = "evchar_coupon_type")
public class CouponType extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 优惠券的id，
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 可抵用现金（单位:分）
	 */
	@Column(name = "c_value")
	private Long cValue;

	/**
	 * 需要满多少金额（单位：分）
	 */
	@Column(name = "c_limit")
	private Long cLimit;

	/**
	 * 描述 例如：满50-10券
	 */
	@Column(name = "c_describe")
	private String cDescribe;

	public Long getcValue() {
		return cValue;
	}

	public void setcValue(Long cValue) {
		this.cValue = cValue;
	}

	public Long getcLimit() {
		return cLimit;
	}

	public void setcLimit(Long cLimit) {
		this.cLimit = cLimit;
	}

	public String getcDescribe() {
		return cDescribe;
	}

	public void setcDescribe(String cDescribe) {
		this.cDescribe = cDescribe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
