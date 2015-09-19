package cn.evchar.common.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.evchar.common.entity.AbstractEntity;
import cn.evchar.common.util.serializer.CustomDateSerializer;

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
	
	//车模型id
	@Column(name="car_model_id")
    private Long carModelId;
	
	/**
	 * 车牌号
	 */
	@Column(name="car_no")
	private String carNo;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time")
	@JsonSerialize(using = CustomDateSerializer.class)
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

	public Long getCarModelId() {
		return carModelId;
	}

	public void setCarModelId(Long carModelId) {
		this.carModelId = carModelId;
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
