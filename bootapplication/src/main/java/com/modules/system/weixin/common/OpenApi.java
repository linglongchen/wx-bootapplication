package com.modules.system.weixin.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonParseException;
import com.modules.common.oauth.AccessToken;
import com.modules.system.weixin.utils.WeChatUtil;
import org.weixin4j.WeixinException;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;
import org.weixin4j.util.TokenUtil;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author
 */
public abstract class OpenApi {

    /**
     * 接口名称
     */
    protected String method;

    /**
     * 接口请求地址
     */
    public String HostUrl = "https://api.weixin.qq.com/card/";
    public static final String url = "https://api.weixin.qq.com/sns/jscode2session";

    public static AccessToken token;

    public static String grantType = "authorization_code";
    public static String appId = "wxb77348a94ff13e2f";                    //微信公众号appid
    public static String secret = "9c9e6ad4a592f641364b126399f03952";    //微信公众号密钥
    public static String mchId = "";                            //微信商户号
    public static String tradeType = "";                            //微信支付交易类型
    public static String key = "";     //微信商户端密匙

    /**
     * @return
     * @throws IOException
     * @throws WeixinException
     * @Description: 调用api并返回查询结果
     * @param：api的http请求包体的model，或者为json格式的请求字符串
     * @author: wcf
     * @date: 2017年7月21日
     */
    public static String getWeixinData(String jsCode) {
        String httpUrl = url + "?appid=" + OpenApi.appId + "&secret=" + OpenApi.secret + "&js_code=" + jsCode
                + "&grant_type=" + OpenApi.grantType;
        // 发送请求，返回Json字符串
        String str = WeChatUtil.httpRequest(httpUrl, "GET", null);
        return str;
    }

    protected void Init(HashMap<String, String> dicArg) {
        dicArg.put("access_token", TokenUtil.get());
    }

    /**
     * post 数据
     *
     * @param sUrl
     * @param json
     * @return
     * @throws WeixinException
     */
    protected String PostData(String sUrl, JSONObject json) throws WeixinException {
        System.out.println("请求地址：" + sUrl);
        HttpsClient http = new HttpsClient();
        Response res = http.post(sUrl, json);
        System.out.println(res.asString());
        return res.asString();
    }

    /**
     * 拼接http请求地址
     *
     * @param dicArg
     * @return
     * @throws WeixinException
     * @throws JsonParseException
     * @throws IOException
     */
    public String GetRequestUrl(HashMap<String, String> dicArg) throws WeixinException, JsonParseException, IOException {
        //初始化基础请求参数
        Init(dicArg);

        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(HostUrl)
                .append(method)
                .append("?access_token=");
        getToken();
        sbUrl.append(token.getAccess_token());
        return sbUrl.toString();
    }

    /**
     * 获取token值
     *
     * @return
     * @throws WeixinException
     * @throws IOException
     */
    public static AccessToken getToken() throws WeixinException, IOException {
        if (token == null || token.getExpires_in() < System.currentTimeMillis()) {
            //拼接参数
            String param = "?grant_type=" + grantType + "&appid=" + appId + "&secret=" + secret;
            //创建请求对象
            HttpsClient http = new HttpsClient();
            //调用获取access_token接口
            Response res = http.get("https://api.weixin.qq.com/cgi-bin/token" + param);
            System.out.println(res.asString());
            token = JSON.parseObject(res.asString(), new TypeReference<AccessToken>() {
            });
        }
        return token;
    }

}
