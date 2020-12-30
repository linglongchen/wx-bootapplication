package com.modules.system.api;

import com.alibaba.fastjson.JSONObject;
import com.modules.common.annotation.IgnoreSecurity;
import com.modules.common.oauth.Audience;
import com.modules.common.oauth.Result;
import com.modules.common.oauth.ResultStatusCode;
import com.modules.common.oauth.JwtHelper;
import com.modules.common.utils.RedisUtils;
import com.modules.common.base.BaseController;
import com.modules.system.entity.User;
import com.modules.system.entity.Account;
import com.modules.system.vo.PhoneVo;
import com.modules.system.service.UserService;
import com.modules.system.weixin.common.OpenApi;
import com.modules.system.weixin.utils.WeChatUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.web.bind.annotation.*;
import org.weixin4j.WeixinException;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.*;

/**
 * @author v_vllchen
 */
@RestController
@RequestMapping("/login")
@Api(value = "LoginController", tags = {"LoginController"}, description = "登录")
public class LoginController extends BaseController {
    @Resource
    private UserService userInfoService;
    @Resource
    private Audience audience;
    @Resource
    private RedisUtils redisUtils;

    /**
     * @return
     * @Description:登陆/注册
     */
    @PostMapping(value = "login")
    @ApiOperation("登陆/注册")
    @IgnoreSecurity
    public Result login(@RequestBody Account account) throws WeixinException, IOException {
        String data = OpenApi.getWeixinData(account.getJsCode());
        JSONObject jsonObj = JSONObject.parseObject(data);
        User user = new User();
        if (jsonObj.containsKey("session_key")) {
            logger.info("================调微信成功=====================");
            String openId = jsonObj.get("openid").toString();
            User userInfo = userInfoService.getCountByOpenId(openId);
            if (userInfo == null) {
                user.setWechatId(openId);
                user.setHeadImg(account.getAvatarUrl());
                user.setNickName(account.getNickName());
                userInfoService.insert(user);
            }
        } else {
            return new Result(ResultStatusCode.SYSTEM_ERR);
        }
        //一个用户同时只能有一台设备登录（用户端）
        String redisToken = redisUtils.getToken(user.getId());
        if (StringUtils.isNotEmpty(redisToken)) {
            String HeadStr = redisToken.substring(0, 6).toLowerCase();
            if (HeadStr.equals("bearer")) {
                redisToken = redisToken.substring(6);
                Claims claims = JwtHelper.parseJWT(redisToken, audience.getBase64Secret());
                //判断密钥是否相等，如果不等则认为时无效的token
                if (claims != null) {
                    return new Result(ResultStatusCode.LOGINED_IN.getCode(), ResultStatusCode.LOGINED_IN.getMsg(), null);
                }
            }
        }
        return new Result(ResultStatusCode.OK, redisLoginInfo(user));
    }

    public Map<String, Object> redisLoginInfo(User user) {
        //设置单次的token的过期时间为凌晨3点-4点，用于避免token在即将失效时继续使用旧的token访问
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, +1);
        cal.set(Calendar.HOUR_OF_DAY, 3);
        //拼装accessToken
        String accessToken = JwtHelper.createJWT(user.getPhone(), user.getId(),
                audience.getClientId(), audience.getName(),
                cal.getTimeInMillis() - System.currentTimeMillis(), audience.getBase64Secret());
        //将该用户的access_token储存到redis服务器，保证一段时间内只能有一个有效的access_token
        redisUtils.setToken(user.getId(), accessToken, cal.getTimeInMillis() - System.currentTimeMillis());
        //获取refresh_token，有效期为7天，每次通过refresh_token获取access_token时，会刷新refresh_token的时间
        String refreshToken = JwtHelper.createRefreshToken(user.getPhone(), user.getId(), audience.getClientId(), audience.getName(), audience.getBase64Secret());
        redisUtils.setRefreshToken(user.getId(), refreshToken);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("access_token", "bearer" + accessToken);
        result.put("refresh_token", "bearer" + refreshToken);
        result.put("user", user);
        return result;
    }

    /**
     * @param
     * @return
     * @Description: 用户退出登陆
     */
    @PostMapping(value = "logout")
    @ApiOperation("用户退出登陆")
    public Result logout(HttpServletRequest request) {
        redisUtils.delete(RedisUtils.ACCESS_TOKEN + getUserId(request));
        redisUtils.delete(RedisUtils.REFRESH_TOKEN + getUserId(request));
        return new Result(ResultStatusCode.OK.getCode(), ResultStatusCode.OK.getMsg(), null);
    }

    /**
     * @return
     * @Description: 用于检测token是否还有效，如果无效则可以通过getToken方法获取新的token
     */
    @PostMapping(value = "checkToken")
    @ApiOperation("用于检测token是否还有效，如果无效则可以通过getToken方法获取新的token")
    @IgnoreSecurity
    public Result checkToken() {
        return new Result(ResultStatusCode.OK.getCode(), ResultStatusCode.OK.getMsg(), null);
    }

    /**
     * @return
     * @Description: 通过refreshToken获取新的access_token，同时也刷新refreshToken的有效期
     */
    @PostMapping(value = "getToken")
    @ApiOperation("通过refreshToken获取新的access_token，同时也刷新refreshToken的有效期")
    @IgnoreSecurity
    public Result getToken(@RequestBody Account account) {
        String refreshToken = account.getRefreshToken();
        Calendar cal = Calendar.getInstance();
        try {
            if (StringUtils.isNotEmpty(refreshToken)) {
                String HeadStr = refreshToken.substring(0, 6).toLowerCase();
                if (HeadStr.equals("bearer")) {
                    refreshToken = refreshToken.substring(6);
                    Claims claims = JwtHelper.parseJWT(refreshToken, audience.getBase64Secret());
                    //判断密钥是否相等，如果不等则认为时无效的token
                    if (claims != null) {
                        //refresh_token未失效，refresh_token需要和redis服务器中的储存的refresh_token值一样才有效
                        Long userId = (Long) claims.get("userId");
                        System.out.println(claims.getAudience());
                        System.out.println(redisUtils.getRefreshToken(userId));
                        if (claims.getAudience().equals(audience.getClientId()) && refreshToken.equals(redisUtils.getRefreshToken(userId))) {
                            User user = userInfoService.get(userId);
                            Map<String,String> tokenVO = new HashMap<>();
                            Map<String, Object> resultToken = redisLoginInfo(user);
                            tokenVO.put("access_token", "bearer" + resultToken.get("access_token"));
                            tokenVO.put("refresh_token", "bearer" + resultToken.get("refresh_token"));
                            //更新redis数据
                            redisUtils.delete(RedisUtils.ACCESS_TOKEN + user.getId());
                            redisUtils.delete(RedisUtils.REFRESH_TOKEN + user.getId());
                            redisUtils.setToken(user.getId(), resultToken.get("access_token").toString(), cal.getTimeInMillis() - System.currentTimeMillis());
                            redisUtils.setRefreshToken(user.getId(), resultToken.get("refresh_token").toString());
                            return new Result(ResultStatusCode.OK.getCode(), ResultStatusCode.OK.getMsg(), tokenVO);
                        }
                    }
                }
            }
            return new Result(ResultStatusCode.INVALID_TOKEN.getCode(), ResultStatusCode.INVALID_TOKEN.getMsg(), null);
        } catch (Exception e) {
            return new Result(ResultStatusCode.SYSTEM_ERR.getCode(), ResultStatusCode.SYSTEM_ERR.getMsg(), null);
        }
    }


    //解析电话号码
    public JSONObject getPhoneNumber(String session_key, String encryptedData, String iv) {
        System.out.println(session_key);
        byte[] dataByte = org.bouncycastle.util.encoders.Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = org.bouncycastle.util.encoders.Base64.decode(session_key);
        // 偏移量
        byte[] ivByte = org.bouncycastle.util.encoders.Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostMapping(value = "getPhoneByWeChat")
    @ApiOperation("授权手机号")
    @IgnoreSecurity
    public Result getPhoneByWeChat(@RequestBody PhoneVo phoneVo) {
        try {
            //解密电话号码
            JSONObject obj = getPhoneNumber(phoneVo.getSessionKey(), phoneVo.getEncryptedData(), phoneVo.getIv());
            return new Result(ResultStatusCode.PHONE_SUCCESS, obj);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultStatusCode.SYSTEM_ERR);
        }
    }

}
