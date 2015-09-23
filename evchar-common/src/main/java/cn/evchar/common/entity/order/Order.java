package cn.evchar.common.entity.order;

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
 * 充电消费订单
 */
@Entity
@Table(name = "evchar_order")
public class Order extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * 订单id（唯一标识）
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
	 * 用户支付时的设备mac地址
	 */
	@Column(name="mac_id")
	private String macId;
	
	/**
	 * 设备id
	 */
	@Column(name="device_id")
	private Long deviceId;
	
	@Column(name="price")
	private Long price;
	
	@Column(name="start_degree")
	private Long startDegree;
	
	@Column(name="end_degree")
	private Long endDegree;
	
	/**
	 * 车id
	 */
	@Column(name="car_id")
	private Long carId;
	
	/**
	 * 订单状态; 0:已预约状态,1:已匹配设备状态,2:手动取消预约（终态）,3:超时自动取消预约（终态）,4:正在使用,5:订单完成
	 */
	@Column(name="status")
	private Integer status;
	
	/**
	 * 总价格,单位：分
	 */
	@Column(name="total_price")
	private Long totalPrice;
	
	
	/**
	 * 创建时间
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
	 * 结束时间
	 */
	@Column(name="end_time")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date endTime;
	
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCarId() {
		return carId;
	}
	

	public void setCarId(Long carId) {
		this.carId = carId;
	}
	
	 public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getStartDegree() {
		return startDegree;
	}

	public void setStartDegree(Long startDegree) {
		this.startDegree = startDegree;
	}

	public Long getEndDegree() {
		return endDegree;
	}

	public void setEndDegree(Long endDegree) {
		this.endDegree = endDegree;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isStatusFinal(){
		return status.byteValue() == OrderStatus.COMPLETE.code() || 
				status.byteValue() == OrderStatus.CANCEL_BY_USER.code() ||
				status.byteValue() == OrderStatus.CANCEL_AUTO.code();
	}


	public static enum OrderStatus{
	        APPOINT((byte)0,"已预约"),
	        DEVICE_MATCH((byte)1,"已适配设备"),
	        CANCEL_BY_USER((byte)2,"手动取消预约"),
	        CANCEL_AUTO((byte)3,"超时自动取消预约"),  
	        CHARGING((byte)4,"正在充电"),
	        COMPLETE((byte)5,"订单完成"),
	        UNKNOWN((byte)-1,"未知状态");
	        private byte code;
	        private String msg;
	        OrderStatus(byte code,String msg){
	            this.code = code;
	            this.msg = msg;
	        }
	        public int code(){
	            return this.code;
	        }
	        public String msg(){
	            return this.msg;
	        }
	        public String getMsg(){
	            return this.msg;
	        }


	        public static OrderStatus valueOf(Integer code){
	            if(null==code){
	                return UNKNOWN;
	            }
	            for(OrderStatus status :values()){
	                if(status.code == code){
	                    return status;
	                }
	            }
	            return UNKNOWN;
	        }


	        public boolean isUnknown() {
	            return equals(UNKNOWN);
	        }
	    }
	
}
