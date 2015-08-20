package cn.evchar.common.entity.user;

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
 * 用户的账户 
 * 初始化用户自动初始化余额和积分2个账户
 */
@Entity
@Table(name="evchar_user_account")
public class UserAccount extends AbstractEntity{
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
	 * 余额
	 */
	@Column(name="balance")
	private Long balance;
	
	/**
	 * 积分 换算：1积分=1分余额
	 */
	@Column(name="point")
	private Long point;
	
	/**
	 * 账号创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time")
	private Date updateTime;
	
	/**
	 * 事务版本控制version
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


	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
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
}
