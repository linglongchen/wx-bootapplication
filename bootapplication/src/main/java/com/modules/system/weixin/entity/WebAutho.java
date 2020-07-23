package com.modules.system.weixin.entity;

/**
 *  网页授权返回对象
 */
public class WebAutho {
    private String access_token;
    private long expires_in;
    private String openid;

    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public long getExpires_in() {
        return expires_in;
    }
    public void setExpires_in(long expires_in) {
        this.expires_in = System.currentTimeMillis() + (expires_in - 100) * 1000;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
