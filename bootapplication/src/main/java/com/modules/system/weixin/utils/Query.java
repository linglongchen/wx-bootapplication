package com.modules.system.weixin.utils;


import com.modules.common.config.Global;

import java.util.HashMap;

public class Query {
    public String logistics(String com, String num){
        String param ="{\"com\":\""+com+"\",\"num\":\""+num+"\"}";
        String customer ="B299B1E143F00EEEF0923E25F30D2227";
        String key = "GGDzGkjl3576";
        String sign = MD5.encode(param+key+customer);
        HashMap params = new HashMap();
        params.put("param",param);
        params.put("sign",sign);
        params.put("customer",customer);
        String resp;
        try {
            resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
            System.out.println(resp);
            return resp;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Global.FALSE;
        }
    }
    public static void main(String[] args) throws Exception {
//        Query query = new Query();
//        query.logistics("yunda","3983940813157");
//        String param ="{\"com\":\"yunda\",\"num\":\"3983940813157\"}";
//        String customer ="B299B1E143F00EEEF0923E25F30D2227";
//        String key = "GGDzGkjl3576";
//        String sign = MD5.encode(param+key+customer);
//        HashMap params = new HashMap();
//        params.put("param",param);
//        params.put("sign",sign);
//        params.put("customer",customer);
//        String resp;
//        try {
//            resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
//            System.out.println(resp);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
}
