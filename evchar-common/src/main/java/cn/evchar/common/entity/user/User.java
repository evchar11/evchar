package cn.evchar.common.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

/**
 * @author wangfeng@evchar.cn
 * 用户
 */
@Entity
@Table(name="evchar_user")
public class User extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户唯一标识 
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	
	/**
	 * 用户类型，通过末端8bit判断用户类型,假设8bit分别为a1a2a3a4a5a6a7a8<br>
	 * a8=1   		用户<br>
	 * a7=1		  	桩主<br>
	 * a6=1			电桩安装师傅<br>
	 * a5=1			系统管理员<br>
	 * 通过下列方法授权和判断用户类型<br>
	 * @see User#authCustomer()
	 * @see User#authAdmin()
	 * @see User#authDeviceOwner()
	 * @see User#authInstaller()
	 * @see User#isAdmin()
	 * @see User#isCustomer()
	 * @see User#isDeviceOwner()
	 * @see User#isInstallser()
	 */
	@Column(name="token")
	private byte token;
	
	/**
	 * 微信id
	 */
	@Column(name="wechat_id")
	private String wechatId;
	
	
	/**
	 * 用户昵称
	 */
	@Column(name="nick_name")
	private String nickName;

	/**
	 * 手机号
	 */
	@Column(name="mobile")
	private String mobile;
	
	/**
	 * 用户首次注册时的设备mac地址
	 */
	@Column(name="mac_id")
	private String macId;
	
	/**
	 * 微信用户头像url
	 */
	@Column(name="mac_id")
	private String headImgUrl;
	
	/**
	 * 用户创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time")
	private Date updateTime;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public byte getToken() {
		return token;
	}

	/**
	 * 是否为用户
	 * token & 00000001
	 */
	public boolean isCustomer(){
		return (1 & token) != 0;
	}
	
	/**
	 * 是否为桩主
	 * token & 00000010
	 */
	public boolean isDeviceOwner(){
		return (2 & token) != 0;
	}
	
	/**
	 * 是否为电桩安装师傅
	 * token & 00000100
	 */
	public boolean isInstallser(){
		return (4 & token) != 0;
	}
	
	/**
	 * 是否为后台管理员
	 * token & 00001000
	 */
	public boolean isAdmin(){
		return (8 & token) != 0;
	}
	
	
	/**
	 * 授权为用户
	 * token = (token | 00000001)
	 */
	public void authCustomer(){
		this.token = (byte) (token | 1);
	}
	
	/**
	 * 授权为桩主
	 * token = (token | 00000010)
	 */
	public void authDeviceOwner(){
		this.token = (byte) (token | 2);
	}
	
	/**
	 * 授权为安装师傅
	 * token = (token | 00000100)
	 */
	public void authInstaller(){
		this.token = (byte) (token | 4);
	}
	
	/**
	 * 授权为系统管理员
	 * token = (token | 00001000)
	 */
	public void authAdmin(){
		this.token = (byte) (token | 8);
	}
	
}
