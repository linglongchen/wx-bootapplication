package com.modules.system.weixin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.modules.common.oauth.AccessToken;
import com.modules.system.weixin.common.OpenApi;
import com.modules.system.weixin.entity.ApiTicket;
import com.modules.system.weixin.entity.WxPaySendData;
import org.weixin4j.WeixinException;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SignUtils {

    private static ApiTicket ticket = null;

    /**
     * @Description: 获取api_ticket，并进行签名
     * @param url
     * @return
     */
    public Map<String, String> getTicket(String url) throws WeixinException, IOException {
        if(ticket == null || ticket.getExpires_in() < System.currentTimeMillis()){
            //创建请求对象
            HttpsClient http = new HttpsClient();

            AccessToken token = OpenApi.getToken();

            //调用获取api_ticket接口
            System.out.println("ticket请求接口：" + "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token.getAccess_token() + "&type=jsapi");
            Response res = http.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token.getAccess_token() + "&type=jsapi");
            System.out.println("ticket返回结果：" + res.asString());
            ticket = JSON.parseObject(res.asString(), new TypeReference<ApiTicket>() {});
        }
        return sign(ticket.getTicket(), url);
    }

    /**
     * @Description: 获取签名
     */
    public static Map<String, String> sign(String api_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String signature = "";


        StringBuilder sb = new StringBuilder();
        //按照字段名的ASCII 码从小到大排序
        sb.append("jsapi_ticket=" + api_ticket)
                .append("&noncestr=" + nonce_str)
                .append("&timestamp=" + timestamp)
                .append("&url=" + url);

        System.out.println("签名前内容：" + sb.toString());

        //对上面拼接的字符串进行sha1加密，得到signature
        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sb.toString().getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        System.out.println("签名后内容：" + signature);
        //返回jssdk需要的参数，其中nonceStr和timestamp必须和签名中的保持一致
        //ret.put("url", url);
        ret.put("api_ticket", api_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    /**
     * 微信支付 获得PrePayId
     *
     * @param payURL
     *            调用支付的完整URL request.getRequestURL().toString();
     * @param body
     *            商品或支付单简要描述
     * @param out_trade_no
     *            商户系统内部的订单号,32个字符内、可包含字母 。用WXPayUtil生成
     * @param total_fee
     *            订单总金额，单位为分
     * @param IP
     *            APP和网页支付提交用户端ip
     * @param notify_url
     *            接收微信支付异步通知回调地址
     * @param openid
     *            用户openId request.getSession().getAttribute("openid")
     * @throws IOException
     */
    public Map<String, Object> wxPay(String payURL,
                                     String body,
                                     String out_trade_no,
                                     String total_fee,
                                     String ip,
                                     String notify_url,
                                     String openId) throws Exception {

        WxPaySendData result = new WxPaySendData();
        Map<String,Object>map=new HashMap<String, Object>();
        String nonce_str = WXPayUtil.getNonceStr();
        String timestamp = create_timestamp();
        //统一下单
        Map resultMap=WXPayUtil.unifiedorder(nonce_str, body, out_trade_no, total_fee, ip, notify_url, openId);
        System.out.println("统一下单返回数据==="+JSON.toJSONString(resultMap));
        String result_code=resultMap.get("result_code")+"";
        String return_code=resultMap.get("return_code")+"";
        if("SUCCESS".equals(return_code)&&"SUCCESS".equals(result_code)){
            //result.setResult_code(result_code);
            result.setAppid(OpenApi.appId);
            result.setTimeStamp(timestamp);
            result.setNonce_str(nonce_str);
            result.setPackageStr("prepay_id="+resultMap.get("prepay_id"));
            result.setSignType("MD5");
            //　WeChatUtil.unifiedorder(.....) 下单操作中，也有签名操作，那个只针对统一下单，要区别于下面的paySign
            //第二次签名,将微信返回的数据再进行签名
            SortedMap<Object,Object> signMap = new TreeMap<Object,Object>();
            signMap.put("appId", OpenApi.appId);
            signMap.put("timeStamp", timestamp);
            signMap.put("nonceStr", nonce_str);
            signMap.put("package", "prepay_id="+resultMap.get("prepay_id"));  //注：看清楚，值为：prepay_id=xxx,别直接放成了wxReturnData.getPrepay_id()
            signMap.put("signType", "MD5");
            String paySign = WXSignUtil.createSign(signMap,  OpenApi.key);//支付签名
            result.setSign(paySign);

            map.put("code", 1);
            map.put("msg", "success");
            map.put("data", result);

        }else{
            map.put("code", 2);
            map.put("msg", "获取prepay_id失败");
            map.put("data", "");
        }
        return map;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
