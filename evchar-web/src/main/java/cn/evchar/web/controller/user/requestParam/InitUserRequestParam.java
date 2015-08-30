package cn.evchar.web.controller.user.requestParam;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by wangfeng on 15-8-30.
 */
public class InitUserRequestParam {
    //用户昵称
    @NotBlank
    private String nickName;
    //微信id
    @NotBlank
    private String wechatId;
    //手机号
    @NotBlank
    private String mobile;
    //注册时使用的设备id
    @NotBlank
    private String macId;

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
}
