package cn.evchar.common.entity.device;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.evchar.common.entity.AbstractEntity;
import cn.evchar.common.util.serializer.CustomDateSerializer;

@Entity
@Table(name = "evchar_device_comments")
public class DeviceComments extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 评论ID
	 * */
	@GeneratedValue
	@Id
	@Column(name="comm_id")
	private Long commId;

	/**
	 * 充电桩ID
	 * */
	@Column(name = "device_id")
	private Long deviceId;

	/**
	 * 用户ID
	 * */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 评论引用ID
	 * */
	@Column(name = "comm_ref_id")
	private Long commRefId;

	/**
	 * 评论内容
	 * */
	@Column(name = "content")
	private String content;

	/**
	 * 评论星级
	 * */
	@Column(name = "star_level")
	private Long starLevel;

	/**
	 * 评论时间
	 **/
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "comm_date")
	private Date commDate;

	
	public Long getCommId() {
		return commId;
	}

	public void setCommId(Long commId) {
		this.commId = commId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCommRefId() {
		return commRefId;
	}

	public void setCommRefId(Long commRefId) {
		this.commRefId = commRefId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Long starLevel) {
		this.starLevel = starLevel;
	}

	/**
	 * 好评列表
	 * */
	@Transient
	private List<DeviceApprove> approves;
	
	/**
	 * 是否已经好评
	 * */
	@Transient
	private int isApprove;

	public Date getCommDate() {
		return commDate;
	}

	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}

	public List<DeviceApprove> getApproves() {
		return approves;
	}

	public void setApproves(List<DeviceApprove> approves) {
		this.approves = approves;
	}

	public int getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(int isApprove) {
		this.isApprove = isApprove;
	}
}
