package cn.evchar.common.entity.user;

import java.util.Date;

import cn.evchar.common.entity.AbstractEntity;

/**
 * @author wangfeng@evchar.cn
 * 优惠券
 */
public class Coupon extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * 优惠券的id，
	 */
	private Long coupon_id;
	
	/**
	 * 状态：
	 * 1。未使用， 2.已使用，3.已过期
	 */
	private Byte status;
	
	/**
	 * 用户id
	 */
	private Long user_id;
	
	/**
	 * 可抵用现金（单位:分）
	 */
	private Long value;
	
	
	/**
	 * 需要满多少金额（单位：分）
	 */
	private Long limit; 
	
	/**
	 * 描述 
	 * 例如：满50-10券
	 */
	private String describe;
	
	/**
	 * 生成时间
	 */
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	private Date update_time;
	
	/**
	 * 失效时间
	 */
	private Date expire_time;

	public Long getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Long coupon_id) {
		this.coupon_id = coupon_id;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(Date expire_time) {
		this.expire_time = expire_time;
	}
	
}
