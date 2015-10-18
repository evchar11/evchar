package cn.evchar.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;

public class UserRequestParamUpdate {
	private Long userId;
	@NotBlank
	private String mobile;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
