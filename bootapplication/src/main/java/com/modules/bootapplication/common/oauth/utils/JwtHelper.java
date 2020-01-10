package com.modules.bootapplication.common.oauth.utils;

import com.modules.bootapplication.common.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtHelper {
	public static Claims parseJWT(String jsonWebToken, String base64Security){
        try{
            Claims claims = Jwts.parser()
                       .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                       .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }catch(Exception ex){
            return null;
        }
    }

    public static void main(String[] args){
        /*String accessToken = "bearer" + JwtHelper.creteaRefreshToken("18806295334", 2,
                "325eopa1wfesikcxk9hi5bd1fncbtj0u", "restapiuser"
                , "VDk4ZjSiY2Q0TjIxZDM3g9FhZGU0ZTgzQjYyM2I0KjP");
        System.out.println(accessToken);*/
        String accessToken = "bearereyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlxdWVfbmFtZSI6IjEzODY1NjI1NDUiLCJkcml2ZXJJZCI6MSwiaXNzIjoicmVzdGFwaXVzZXIiLCJhdWQiOiIzMjVlb3BhMXdmZXNpa2N4azloaTViZDFmbmNidGowdSIsImV4cCI6MTUxNTE3OTgwMiwibmJmIjoxNTE1MTM2NjAyfQ.XMwDedeOcltkhWalM8SyX3aW119JZ3LWpDJhPsrUvgE";

        if(StringUtils.isNotEmpty(accessToken)){
            String HeadStr = accessToken.substring(0, 6).toLowerCase();
            if(HeadStr.equals("bearer")){
                accessToken = accessToken.substring(6);

                Claims claims = JwtHelper.parseJWT(accessToken, "VDk4ZjSiY2Q0TjIxZDM3g9FhZGU0ZTgzQjYyM2I0KjP");
                //判断密钥是否相等，如果不等则认为时无效的token
                if(claims != null){
                    Integer driverId = (Integer)claims.get("userId");
                    System.out.println("userId=" + driverId);
                }else{
                    System.out.println("error");
                }
            }
        }
    }

     /**
      * @description 生成普通用户登录token或者refresh_token
      * @param
      * @author wcf
      * @date 2018/1/23
      * @return
      */
    public static String createJWT(String name, Integer userId,
            String audience, String issuer, long TTLMillis, String base64Security){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

          //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                                        .claim("unique_name", name)
                                        .claim("userId", userId)
                                        .setIssuer(issuer)		//设置发行人
                                        .setAudience(audience)	//设置角色
                                        .signWith(signatureAlgorithm, signingKey);
         //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

         //生成JWT
        return builder.compact();
    }

    /**
     * @Description: 获取accessToken，有效期为1小时，用于请求用户数据接口
     * @param name
     * @param userId
     * @param audience
     * @param issuer
     * @param base64Security
     * @return
     * @author ：wcf
     * @date ： 2017年7月2日
     */
    public static String createAccessToken(String name, Integer userId,
            String audience, String issuer, String base64Security){
    	return createJWT(name, userId, audience, issuer, 3600000, base64Security);
    }
    /**
     * @Description: 获取refreshToken，有效期7天，仅用于更新accessToken
     * @param name
     * @param userId
     * @param audience
     * @param issuer
     * @param base64Security
     * @return
     * @author ：wcf
     * @date ： 2017年7月2日
     */
    public static String creteaRefreshToken(String name, Integer userId,
            String audience, String issuer, String base64Security){
    	return createJWT(name, userId, audience, issuer, 3600000, base64Security);
    }

}
