package cn.evchar.common.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

/**
 * @author wangfeng@evchar.cn
 * 用户汽车相关信息
 */
@Entity
@Table(name="evchar_user_car")
public class UserCar extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 自增id
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
	 * 车品牌
	 */
	@Column(name="brand")
	private String brand; 
	
	/**
	 * 车型号
	 */
	@Column(name="type")
	private String type;
	
	/**
	 * 车牌号
	 */
	@Column(name="car_no")
	private String carNo;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time")
	private Date updateTime;

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
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
