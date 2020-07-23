package com.modules.common.oauth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author v_vllchen
 */
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


     /**
      * @description 生成普通用户登录token或者refresh_token
      * @param
      * @author wcf
      * @date 2018/1/23
      * @return
      */
    public static String createJWT(String name, Long userId,
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
    public static String createAccessToken(String name, Long userId,
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
    public static String createRefreshToken(String name, Long userId,
            String audience, String issuer, String base64Security){
    	return createJWT(name, userId, audience, issuer, 3600000, base64Security);
    }

}
