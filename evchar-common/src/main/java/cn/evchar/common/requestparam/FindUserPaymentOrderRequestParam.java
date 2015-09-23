package cn.evchar.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;

public class FindUserPaymentOrderRequestParam extends BasePageRequestParam{
	//微信openId
	@NotBlank
	private String wechatId;
	
	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	} 
		
}
