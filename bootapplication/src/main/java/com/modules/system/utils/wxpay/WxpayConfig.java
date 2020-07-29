package com.modules.system.utils.wxpay;



/**
 * @Description: 微信支付相关配置
 * @Date: 2018/4/26
 * @Author: wcf
 */
public class WxpayConfig {
    private static final String rootPath = "";
    /**
     * 微信开放平台提供appid
     */
    public final static String appId = "wx57c4db340e37e4f1";
    /**
     * 微信开放平台密钥
     */
    public final static String secret = "9d66b212145517358e21a5782eb875b2";
    /**
     * 商户号
     */
    public final static String partner = "1514729401";
    /**
     * 商户密钥
     */
    public final static String partnerkey = "liwuge18221592972qq1204702917ggg";
    /**
     * 支付方式
     */
    public final static String tradeType = "JSAPI";
    /**
     * 异步通知地址
     */
    public final static String notifyUrl = rootPath+"/api/order/wxpayNotify";
    /**
     * 异步通知地址拼团
     */
    public final static String notifyUrlGroup =   rootPath+ "/api/order/wxpayNotifyByGroup";


    public final static String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
