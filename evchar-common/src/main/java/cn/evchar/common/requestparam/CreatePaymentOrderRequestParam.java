package cn.evchar.common.requestparam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CreatePaymentOrderRequestParam {
	//微信openId
	@NotBlank
	private String wechatId; 
	
	@NotNull
	@Min(1)
	private Long money;

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}
	
}
