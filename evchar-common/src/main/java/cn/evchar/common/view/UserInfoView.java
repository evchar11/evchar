package cn.evchar.common.view;

import org.hibernate.validator.constraints.NotBlank;

public class UserInfoView {
	//用户昵称
    private String nickName;
    //微信id
    private String wechatId;
    //手机号
    private String mobile;
    //注册时使用的设备id
    private String macId;
    //车牌号
    private String carNo;
    //车品牌
    private String carBrand;
    //车型号
    private String carModel;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getWechatId() {
		return wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMacId() {
		return macId;
	}
	public void setMacId(String macId) {
		this.macId = macId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
    
}
