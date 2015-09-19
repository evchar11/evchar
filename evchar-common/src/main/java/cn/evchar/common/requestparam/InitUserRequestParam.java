package cn.evchar.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;

public class InitUserRequestParam {
	//用户昵称
    private String nickName;
    //微信id
    @NotBlank
    private String wechatId;
    //微信头像url
    private String headImgUrl;
    //手机号
    @NotBlank
    private String mobile;
    //注册时使用的设备id
    private String macId;
    //车牌号
    @NotBlank
    private String carNo;
    //车模型id
    @NotBlank
    private String carModelId;
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
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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
	public String getCarModelId() {
		return carModelId;
	}
	public void setCarModelId(String carModelId) {
		this.carModelId = carModelId;
	}
    
}
