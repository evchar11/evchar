package cn.evchar.common.entity.Integral;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

@Entity
@Table(name = "evchar_integral_record")
public class IntegralRecord extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "eir_status")
	private Integer eirStatus;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "expire_time")
	private Date expireTime;

	@Column(name = "eir_value")
	private Long eirValue;

	@Column(name = "get_way")
	private Integer getWay;

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

	public Integer getEirStatus() {
		return eirStatus;
	}

	public void setEirStatus(Integer eirStatus) {
		this.eirStatus = eirStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Long getEirValue() {
		return eirValue;
	}

	public void setEirValue(Long eirValue) {
		this.eirValue = eirValue;
	}

	public Integer getGetWay() {
		return getWay;
	}

	public void setGetWay(Integer getWay) {
		this.getWay = getWay;
	}

}
