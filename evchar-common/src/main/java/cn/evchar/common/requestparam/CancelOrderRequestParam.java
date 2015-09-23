package cn.evchar.common.requestparam;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class CancelOrderRequestParam {
	//微信openId
	@NotBlank
	private String wechatId;
	
	@NotBlank
	@Min(1)
	private Long orderId;
	
	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	} 
	
}
