package com.modules.system.weixin.utils;

import com.modules.system.weixin.common.OpenApi;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class WXPayUtil {

    /**
     * 统一下单
     * 获得PrePayId
     * @param body   商品或支付单简要描述
     * @param out_trade_no 商户系统内部的订单号,32个字符内、可包含字母
     * @param total_fee  订单总金额，单位为分
     * @param IP    APP和网页支付提交用户端ip
     * @param notify_url 接收微信支付异步通知回调地址
     * @param openid 用户openId
     * @throws IOException
     * @throws DocumentException
     */
    public static Map unifiedorder(String nonce_str, String body, String out_trade_no, String total_fee, String ip, String notify_url, String openId)throws IOException, DocumentException {
        /**
         * 设置访问统一支付路径
         */
        HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
        //String nonce_str = getNonceStr();//随机数据
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        /**
         * 组装请求参数
         * 按照ASCII排序
         */
        parameters.put("appid", OpenApi.appId);
        parameters.put("body", body);
        parameters.put("mch_id", OpenApi.mchId );
        parameters.put("nonce_str", nonce_str);
        parameters.put("out_trade_no", out_trade_no);
        parameters.put("notify_url", notify_url);
        parameters.put("spbill_create_ip", ip);
        parameters.put("total_fee",total_fee );
        parameters.put("trade_type",OpenApi.tradeType );
        parameters.put("openid", openId);

        String sign = WXSignUtil.createSign(parameters, OpenApi.key);

        /**
         * 组装XML
         */
        StringBuilder sb = new StringBuilder("");
        sb.append("<xml>");
        setXmlKV(sb,"appid",OpenApi.appId);
        setXmlKV(sb,"body",body);
        setXmlKV(sb,"mch_id",OpenApi.mchId);
        setXmlKV(sb,"nonce_str",nonce_str);
        setXmlKV(sb,"notify_url",notify_url);
        setXmlKV(sb,"out_trade_no",out_trade_no);
        setXmlKV(sb,"spbill_create_ip",ip);
        setXmlKV(sb,"total_fee",total_fee);
        setXmlKV(sb,"trade_type",OpenApi.tradeType);
        setXmlKV(sb,"sign",sign);
        setXmlKV(sb,"openid",openId);
        sb.append("</xml>");

        StringEntity reqEntity = new StringEntity(new String (sb.toString().getBytes("UTF-8"),"ISO8859-1"));//这个处理是为了防止传中文的时候出现签名错误
        httppost.setEntity(reqEntity);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httppost);
        String strResult = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));

        return XMLUtil.doXMLParse(strResult);

    }

    //获得随机字符串
    public static String getNonceStr(){
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }

    //插入XML标签
    public static StringBuilder setXmlKV(StringBuilder sb,String Key,String value){
        sb.append("<");
        sb.append(Key);
        sb.append(">");

        sb.append(value);

        sb.append("</");
        sb.append(Key);
        sb.append(">");

        return sb;
    }

    //解析XML  获得 PrePayId
    public static String getPrePayId(String xml){
        int start = xml.indexOf("<prepay_id>");
        int end = xml.indexOf("</prepay_id>");
        if(start < 0 && end < 0){
            return null;
        }
        return xml.substring(start + "<prepay_id>".length(),end).replace("<![CDATA[","").replace("]]>","");
    }

    //商户订单号
    public static String getOut_trade_no(String start,String end){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String str=start+df.format(new Date())+end;
        String regex="\\w{14,32}";
        if(!str.matches(regex)){
            throw new RuntimeException("订单号格式错误");
        }
        return  str;
    }

    //时间戳
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    //随机4位数字
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) (random * num);
    }

    public static String inputStream2String(InputStream inStream, String encoding){
        String result = null;
        try {
            if(inStream != null){
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[1024];
                int count = -1;
                while((count = inStream.read(tempBytes, 0, 1024)) != -1){
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();
                result = new String(outStream.toByteArray(), encoding);
            }
        } catch (Exception e) {
            result = null;
        }
        return result;
    }
    /**
     * 生成length为随机数
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static void main(String[] args) throws IOException, DocumentException {
        String openId="oZsi60VCxHvHGZLzeRhNmutNKWvk";
        String nonce_str=getNonceStr();
        String str="|";
        String out_trade_no = "1524896165732";
        Map strResult = WXPayUtil.unifiedorder(nonce_str,"test", out_trade_no, "1", "127.0.0.1", "http://www.jufengou.com",openId);
        System.out.println(strResult);
    }


}
