package cn.evchar.common.entity.device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

@Entity
@Table(name = "evchar_device_approve")
public class DeviceApprove extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增长ID
	 * */
	@GeneratedValue
	@Id
	private Long id;

	/**
	 * 评论ID
	 * */
	@Column(name = "comm_id")
	private Long commId;

	/**
	 * 用户ID
	 * */
	@Column(name = "user_id")
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommId() {
		return commId;
	}

	public void setCommId(Long commId) {
		this.commId = commId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
