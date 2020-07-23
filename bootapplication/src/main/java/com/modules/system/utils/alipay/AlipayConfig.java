package com.modules.system.utils.alipay;

import com.modules.common.config.Global;
import com.modules.common.utils.PathUtil;

/**
 * @Description:
 * @Date: 2018/4/24
 * @Author: wcf
 */
public class AlipayConfig {
    /**
     * 商户appid
     */
    public static final String APPID = "2018041902583406";
    /**
     * 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String notify_url = Global.getConfig("service.url") + "/hamster-api/api/pay/alipayNotify";

    public static String notify_url2 = Global.getConfig("service.url") + "/hamster-api/api/school/alipayNotify";
    /**
     * 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
     */
    public static String return_url = Global.getConfig("service.url") + "/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
    /**
     * 请求网关地址，固定
     */
    public static String URL = "https://openapi.alipay.com/gateway.do";
    /**
     * 请求和签名使用的字符编码格式，支持GBK和UTF-8
     */
    public static String CHARSET = "UTF-8";
    /**
     * 参数返回格式，只支持json
     */
    public static String FORMAT = "json";
    /**
     * 支付宝公钥
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsF8OKGkWhR7IcTZjJZg/rEPiq7Ehc/4jhtoyKx62YvBZr/OJ4of6Mrn/1RSOayYRj7WIAnDg87TRbTxj7xWtRXFoHpSRemUobtYFPFIUug2BImaPDvDae+/ZXJhzcmdV2ChUA/1DOchYcry/MblFZIYDVxXU+tZL+Din8c9bGPWI4uoKB7rXd3c5oSHFmAX5K4l33xGF/PtUWpK/DpzABE46DQt7ZcuRYN53C/CRWRG+7NG73vFqjgwsSnaZjQXw/c4cSSAZ4oUkVMi9nU4nIkhp140R2aW86uRKNgHUNt4sFy6NOOegRV6m5sjqtZpkAZtCkN9inwlXtda0Dm6UjwIDAQAB";
    /**
     * 开发者应用私钥，由开发者自己生成，pkcs8格式的
     */
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCeAuPmDB8RXOQq1iPJ8HYw2afhp6tpCrWFakDGlee5p9vU7t69wTrzQO5mFW41kaSztlW/whIRTrTsIN8yzVjzISuV5qDSVvHeqODj+Vg2qRk5quJ4AZUGz/wkY7CcrHSEOGgNYriL4Fp6cbLRplYGPxLFUfm9ZU9McxwEW2UXTPQEbT1a/rHCyRNv0XRQHsyb7Ljb+NsSH+4gd/dV0tosSfe2CLcynXvvdXJSegCISSqBWbuaUn7cx7t4iPJ9YK9s3FHrEKXn7Vm+IX5E2/2R6kOez4BegtF0nzHn5sk2D6/KhwKmjaf5m11o2lpElxmf8l+mfToZHsF9+KhBcjgHAgMBAAECggEBAI4LpRoyPV+bdRY5KTUpcOabU/tcgpBMa0BDc3XSXt0cVFgIYL6OzdLNxUKJ82g+xKQ1vsJC0YEzkxtOtbdyuIrgf9Ei4eIU/RwgDwOCLNX+j8myoCInsfPM1ytTXvqJYblXB9s4qxQ7nLrEKW+kq2K0Isi11992CqJ8PGyCSM93GMaQnbnz+42WGp0hIQN1DkzKt93SCuaw9Yg6Gn0hMrETuAI/3Mk6C8DShDOSUDi1sCioWaJcS1b3fp/fAUeld9HADMG0pER0MGXuXQnwP+nKT8klt8wMMTa7UUki6NOyLMOXnJb0bggTbIMUWhddP1y/Z47G2H0K7iNDBpyJ50kCgYEA190FMJE/bsTdaR7PlrSdz6+RlUXthN09OVzcsRd6Bu4GfeaUrbeofa6XxQDKdc7SWFL4jcI3Xk5w7Tf2ltO7nDoJtKgrrdC7+imBJpYSUFE1pHmiisbAAWGZmRxe3b4z3Id9jOLuxfp0a8zBY+QgNoql4FTcRh6KCaA+rcUr/6MCgYEAu2QkfkHdKvSzkln1qMRInkIqcX+SMmlgcGiDssYPGIo6zEmdsdlx7po2LktRVEhmvEnQMcYFeMa+0unAQrNlioooh9Tlro0hJP9a8L86nDt1ueJD7+6WclxbyxowC82IqfqNZNLsq+D7KtEphpaFqF/oXCFbDMpoYgeDk5nFnE0CgYB9dnXMu68dp4h0z7aiBkpu6sF3/FPH1HsAOyv+NgHzwya8yGcD7OoeWnrVwtbp0BLLyTWz1v0tb/QMvGkzUs8JR2ZavR/d/6MyDCuHjNQKIJlIBnZlqIzygJKwvh+ZTDKCa7GaUE+tfBtsiG6iq3XZnY45q7j3fEqIu72SbDZddwKBgBl+YkibBU/Wwqdifb1cFtURA101kdox4+DnFyO3Nk6MX7tzOHT9tDZV3JEnxgTFZNSw5hYfbpcj0so26uUoKuRSL3lrSWQZ5yx1YRXnrj2bCUUkt0FpICam0gYrpeW1LKaTY7EX0QMBNKp6kj+xMrXZdm8ySoTO9KyBgTEPdobxAoGAO7jrAeGy4321r5odjFMVKIRhKePPnQDnRAKDkPKXLFGx7gXcsjgSUF+VfxISfzMnm9UEtr6lD30hQpW2K5kbd6pYHYs5WIW3A4kN7CEX1z/CD/e4EJKcbjovfJQGfHbU3NRAvIiRGPFpqZR/vVVCq0Xyrn01ilYEkc0pA84z1B4=";
    /**
     * 日志记录目录
     */
    public static String log_path = PathUtil.getFileSavePath("");
    /**
     * 加密方式，RSA2
     */
    public static String SIGNTYPE = "RSA2";
    /**
     * 收款方的支付宝id
     */
    public static String seller_id = "2088031673320399";
}
