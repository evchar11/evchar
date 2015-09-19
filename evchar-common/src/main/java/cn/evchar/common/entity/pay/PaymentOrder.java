package cn.evchar.common.entity.pay;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import cn.evchar.common.entity.AbstractEntity;
import cn.evchar.common.util.serializer.CustomDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author wangfeng@evchar.cn
 * 余额充值订单
 */
@Entity
@Table(name="evchar_payment_order")
public class PaymentOrder extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 充值订单id
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 微信支付交易单号
	 */
	@Column(name="transaction_id")
	private String transactionId;
	
	/**
	 * 商户单号
	 */
	@Column(name="out_trade_no")
	private String outTradeNo;
	
	/**
	 * 支付银行类型
	 */
	@Column(name="bank_type")
	private String bankType;
	
	/**
	 * 用户id
	 */
	@Column(name="user_id")
	private Long userId;
	
	/**
	 * 支付订单状态
	 */
	@Column(name="status")
	private int status;
	
	/**
	 * 用户支付时的设备mac地址
	 */
	@Column(name="mac_id")
	private String macId;
	
	/**
	 *涉及到的金额 
	 */
	@Column(name="money")
	private Long money;
	
	/**
	 * 支付平台返回的用户支付的时间
	 */
	@Column(name="pay_time")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date payTime;
	
	/**
	 * 支付凭据生成时间
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
	
	/**
	 * 版本号，乐观锁事务控制
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


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getMacId() {
		return macId;
	}


	public void setMacId(String macId) {
		this.macId = macId;
	}


	public Long getMoney() {
		return money;
	}


	public void setMoney(Long money) {
		this.money = money;
	}


	public Date getPayTime() {
		return payTime;
	}


	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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


	public enum PaymentOrderStatus{
	        NOT_PAY(0,"待付款"),
	        PAY(1,"已付款");
	        private Integer value;
	        private String msg;
	        PaymentOrderStatus(Integer value, String msg){
	            this.value = value;
	            this.msg = msg;
	        }
			public Integer getValue() {
				return value;
			}
			public void setValue(Integer value) {
				this.value = value;
			}
			public String getMsg() {
				return msg;
			}
			public void setMsg(String msg) {
				this.msg = msg;
			}
	        
	}
}
