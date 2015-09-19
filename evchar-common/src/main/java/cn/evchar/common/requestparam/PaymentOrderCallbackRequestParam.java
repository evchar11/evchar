package cn.evchar.common.requestparam;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class PaymentOrderCallbackRequestParam {
	//微信openId
	@NotBlank
	private String wechatId; 
	
	//金额
	@NotNull
	@Min(1)
	private Long money;
	
	//支付订单的id
	@NotNull
	@Min(1)
	private Long paymentOrderId;
	
	//用户支付时的设备mac地址
	private String macId;
	
	//微信支付交易单号，外部订单号
	@NotBlank
	private String transactionId;
	
	//商户单号
	private String outTradeNo;
	
	//支付银行信息
	private String bankType;
	
	//支付时间
	@NotNull
	private Date payTime;

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

	public Long getPaymentOrderId() {
		return paymentOrderId;
	}

	public void setPaymentOrderId(Long paymentOrderId) {
		this.paymentOrderId = paymentOrderId;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

}
