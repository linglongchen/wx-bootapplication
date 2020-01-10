package com.modules.bootapplication.modules.paramVo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Description:
 * @Date: 2018/1/4
 * @Author: wcf
 */
public class Account {

    @NotEmpty(message = "请填写手机号")
    private String phone;
    @NotEmpty( message = "请填写clientId")
    private String clientId;
    @NotEmpty(message = "第三方登录id不能为空")
    private String unionId;

    private String jsCode;

    private String nickName;//昵称

    private String avatarUrl;//头像

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

}
