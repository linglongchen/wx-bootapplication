package com.modules.bootapplication.modules.weixin.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonParseException;
import com.modules.bootapplication.common.oauth.AccessToken;
import org.weixin4j.WeixinException;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;
import org.weixin4j.util.TokenUtil;

import java.io.IOException;
import java.util.HashMap;

public abstract class OpenApi {

    /**
     * 接口名称
     */
    protected String method;

    /**
     * 接口请求地址
     */
    public String HostUrl = "https://api.weixin.qq.com/card/";

    public static AccessToken token;

    public static String grantType = "authorization_code";
    public static String appId = "wx57c4db340e37e4f1";					//微信公众号appid
    public static String secret = "9d66b212145517358e21a5782eb875b2";	//微信公众号密钥
    public static String mchId = "1514729401";	                        //微信商户号
    public static String tradeType = "JSAPI";	                        //微信支付交易类型
    public static String key = "liwuge18221592972qq1204702917ggg";     //微信商户端密匙

    /**
     * @Description: 调用api并返回查询结果
     * @param：api的http请求包体的model，或者为json格式的请求字符串
     * @return
     * @throws IOException
     * @author: wcf
     * @throws WeixinException
     * @date: 2017年7月21日
     */
    public String QueryData(JSONObject json) throws WeixinException, IOException
    {
        String sRes = "";
        String sUrl = "";
        HashMap<String, String> dicArg = new HashMap<String,String>();
        sUrl = GetRequestUrl(dicArg);
        sRes = PostData(sUrl, json);
        return sRes;
    }

    protected  void Init(HashMap<String, String> dicArg){
        dicArg.put("access_token", TokenUtil.get());
    }

    /**
     * post 数据
     * @param sUrl
     * @param json
     * @return
     * @throws WeixinException
     */
    protected String PostData(String sUrl, JSONObject json) throws WeixinException{
        System.out.println("请求地址：" + sUrl);
        HttpsClient http = new HttpsClient();
        Response res = http.post(sUrl, json);
        System.out.println(res.asString());
        return res.asString();
    }

    /**
     * 拼接http请求地址
     * @param dicArg
     * @return
     * @throws WeixinException
     * @throws JsonParseException
     * @throws IOException
     */
    public String GetRequestUrl(HashMap<String, String> dicArg) throws WeixinException, JsonParseException, IOException{
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
     * @return
     * @throws WeixinException
     * @throws IOException
     */
    public static AccessToken getToken() throws WeixinException, IOException{
        if(token == null || token.getExpires_in() < System.currentTimeMillis()){
            //拼接参数
            String param = "?grant_type=" + grantType + "&appid=" + appId + "&secret=" + secret;
            //创建请求对象
            HttpsClient http = new HttpsClient();
            //调用获取access_token接口
            Response res = http.get("https://api.weixin.qq.com/cgi-bin/token" + param);
            System.out.println(res.asString());

            token = JSON.parseObject(res.asString(), new TypeReference<AccessToken>() {});
        }
        return token;
    }

}
