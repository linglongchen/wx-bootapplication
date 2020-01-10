/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.modules.bootapplication.modules.api;

import com.alibaba.fastjson.JSONObject;
import com.modules.bootapplication.common.oauth.Audience;
import com.modules.bootapplication.common.oauth.Result;
import com.modules.bootapplication.common.oauth.ResultStatusCode;
import com.modules.bootapplication.common.oauth.utils.JwtHelper;
import com.modules.bootapplication.common.utils.StringUtils;
import com.modules.bootapplication.common.web.BaseController;
import com.modules.bootapplication.modules.paramVo.Account;
import com.modules.bootapplication.modules.user.entity.UserInfo;
import com.modules.bootapplication.modules.user.service.UserInfoService;
import com.modules.bootapplication.modules.utils.RedisUtils;
import com.modules.bootapplication.modules.weixin.common.OpenApi;
import com.modules.bootapplication.modules.weixin.utils.WeChatUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.weixin4j.WeixinException;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录注册模块Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@RestController
@RequestMapping("/api/oauth")
@Api(tags="登录接口")
public class LoginController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private Audience audience;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 判断是否包含表情
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d
                        || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c
                        || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return isEmoji;
    }


    /**
     * 过滤掉字符串中的表情
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (containsEmoji(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }
    /**
     * 判断某个字符是不是表情
     * @param codePoint
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
    public static String filterEmoji2(String source) {
        if (source != null && source.length() > 0) {
            return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
        } else {
            return source;
        }
    }


    /**
     *
     * @Description:登陆/注册
     * @return
     * @author: df
     * @date: 2018年9月5日
     */
    @PostMapping(value = "login")
    @ApiOperation("登陆/注册")
    public Result login(@RequestBody Account account) throws WeixinException {
        try{
//            if(account.getClientId() == null || !account.getClientId().equals(audience.getClientId())){
//                return new Result(ResultStatusCode.INVALID_CLIENTID, null);
//            }
            String url = "https://api.weixin.qq.com/sns/jscode2session";
            String httpUrl = url + "?appid=" + OpenApi.appId + "&secret=" + OpenApi.secret + "&js_code=" + account.getJsCode()
                    + "&grant_type="+ OpenApi.grantType;
            // 发送请求，返回Json字符串
            String str = WeChatUtil.httpRequest(httpUrl, "GET", null);
//            HttpsClient http = new HttpsClient();
//            Response res = http.get(httpUrl);
            JSONObject jsonObj = JSONObject.parseObject(str);
            UserInfo userInfo = new UserInfo();
                if (jsonObj.containsKey("errcode")) {
                    String errcode = jsonObj.get("errcode").toString();
                    logger.info("微信返回的错误码{}", errcode);
                    return new Result(ResultStatusCode.SYSTEM_ERR);
                } else if (jsonObj.containsKey("session_key")) {
                    logger.info("调微信成功");
                    // 开始处理userInfo
                    String openId = jsonObj.get("openid").toString();
                    userInfo.setWechatId(openId);
                    System.out.println("WechatId==" + openId);
                    // 先查询openId存在不存在，存在不入库，不存在就入库
                    if (userInfoService.getCountByOpenId(userInfo) > 0) {
                        logger.info("openId已经存在，不需要插入");
                        userInfo = userInfoService.getByOpenId(userInfo);
                    } else {
                        String string = filterEmoji2(account.getNickName());
                        System.out.println(string+"===============================");
                        userInfo.setName(string);
                        userInfo.setHeadImg(account.getAvatarUrl());
                        userInfoService.register(userInfo);
//                        //注册用户并发送优惠券
//                        userCouponService.insertUserCoupon();
                        logger.info("openId不存在，插入数据库");
                    }
                }
            if(userInfo != null){
                //一个用户同时只能有一台设备登录（用户端）
                String redisToken = redisUtils.getToken(userInfo.getId());
                if(StringUtils.isNotEmpty(redisToken)){
                    String HeadStr = redisToken.substring(0, 6).toLowerCase();
                    if(HeadStr.equals("bearer")) {
                        redisToken = redisToken.substring(6);
                        Claims claims = JwtHelper.parseJWT(redisToken, audience.getBase64Secret());
                        //判断密钥是否相等，如果不等则认为时无效的token
                        if (claims != null) {
                            return new Result(ResultStatusCode.LOGINED_IN.getCode(), ResultStatusCode.LOGINED_IN.getMsg(), null);
                        }
                    }
                }
                return new Result(ResultStatusCode.OK, redisLoginInfo(userInfo));
            }else{
                return new Result(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
            }


        }catch(Exception e){
            e.printStackTrace();
            return new Result(ResultStatusCode.SYSTEM_ERR, null);
        }
    }

    public Map<String, Object> redisLoginInfo(UserInfo user){
        //设置单次的token的过期时间为凌晨3点-4点，用于避免token在即将失效时继续使用旧的token访问
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, +1);
        cal.set(Calendar.HOUR_OF_DAY, 3);
        //cal.add(Calendar.MINUTE, +2);//用于测试，只有2分钟有效期

        //拼装accessToken
        String accessToken = JwtHelper.createJWT(user.getPhone(), user.getId(),
                audience.getClientId(), audience.getName(),
                cal.getTimeInMillis() - new Date().getTime(), audience.getBase64Secret());
        //将该用户的access_token储存到redis服务器，保证一段时间内只能有一个有效的access_token
        redisUtils.setToken(user.getId(), accessToken, cal.getTimeInMillis() - new Date().getTime());

        //获取refresh_token，有效期为7天，每次通过refresh_token获取access_token时，会刷新refresh_token的时间
        String refreshToken =  JwtHelper.creteaRefreshToken(user.getPhone(), user.getId(), audience.getClientId(), audience.getName(), audience.getBase64Secret());
        redisUtils.setRefreshToken(user.getId(), refreshToken);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("access_token", "bearer" + accessToken);
        result.put("refresh_token", "bearer" + refreshToken);
        result.put("user", user);

        //添加登陆日志代码

        return result;
    }

    /**
     * @Description: 用户退出登陆
     * @param
     * @return
     * @author: wcf
     * @date: 2017年7月6日
     */
    @PostMapping(value = "logout")
    @ApiOperation("用户退出登陆")
    public Result logout(HttpServletRequest request){
        redisUtils.delete(RedisUtils.ACCESS_TOKEN + getUserId(request));
        redisUtils.delete(RedisUtils.REFRESH_TOKEN + getUserId(request));
        return new Result(ResultStatusCode.OK.getCode(), ResultStatusCode.OK.getMsg(), null);
    }
    /**
     * @Description: 用于检测token是否还有效，如果无效则可以通过getToken方法获取新的token
     * @return
     * @author: wcf
     * @date: 2017年7月7日
     */
    @PostMapping(value = "checkToken")
    @ApiOperation("用于检测token是否还有效，如果无效则可以通过getToken方法获取新的token")
    public Result checkToken(){
        return new Result(ResultStatusCode.OK.getCode(), ResultStatusCode.OK.getMsg(), null);
    }

    /**
     * @Description: 通过refreshToken获取新的access_token，同时也刷新refreshToken的有效期
     * @return
     * @author: wcf
     * @date: 2017年7月7日
     */
    @PostMapping(value = "getToken")
    @ApiOperation("通过refreshToken获取新的access_token，同时也刷新refreshToken的有效期")
    public Result getToken(@RequestBody Account account){
        String refreshToken = account.getRefreshToken();
        try{
            if(StringUtils.isNotEmpty(refreshToken)){
                String HeadStr = refreshToken.substring(0, 6).toLowerCase();
                if(HeadStr.equals("bearer")){
                    refreshToken = refreshToken.substring(6);

                    Claims claims = JwtHelper.parseJWT(refreshToken, audience.getBase64Secret());
                    //判断密钥是否相等，如果不等则认为时无效的token
                    if(claims != null){
                        //refresh_token未失效，refresh_token需要和redis服务器中的储存的refresh_token值一样才有效
                        Integer userId = (Integer)claims.get("userId");
                        System.out.println(claims.getAudience());
                        System.out.println(redisUtils.getRefreshToken(userId));
                        if(claims.getAudience().equals(audience.getClientId()) && refreshToken.equals(redisUtils.getRefreshToken(userId))){

                            UserInfo user = userInfoService.get(userId);

                            Map<String, String> result = new HashMap<String, String>();

                            //设置单次的token的过期时间为凌晨3点-4点，用于避免token在即将失效时继续使用旧的token访问
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DAY_OF_MONTH, +1);
                            cal.set(Calendar.HOUR_OF_DAY, 3);

                            //拼装accessToken
                            String accessToken = JwtHelper.createJWT(user.getPhone(), user.getId(),
                                    audience.getClientId(), audience.getName(),
                                    cal.getTimeInMillis() - new Date().getTime(), audience.getBase64Secret());

                            //获取refresh_token，有效期为7天，每次通过refresh_token获取access_token时，会刷新refresh_token的时间
                            String refreshToken_new = JwtHelper.creteaRefreshToken(user.getPhone(), user.getId(),
                                    audience.getClientId(), audience.getName(), audience.getBase64Secret());

                            result.put("access_token", "bearer" + accessToken);
                            result.put("refresh_token", "bearer" + refreshToken_new);
                            //更新redis数据
                            redisUtils.delete(RedisUtils.ACCESS_TOKEN + user.getId());
                            redisUtils.delete(RedisUtils.REFRESH_TOKEN + user.getId());
                            redisUtils.setToken(user.getId(), accessToken, cal.getTimeInMillis() - new Date().getTime());
                            redisUtils.setRefreshToken(user.getId(), refreshToken_new);

                            return new Result(ResultStatusCode.OK.getCode(), ResultStatusCode.OK.getMsg(), result);
                        }
                    }
                }
            }
            return new Result(ResultStatusCode.INVALID_TOKEN.getCode(), ResultStatusCode.INVALID_TOKEN.getMsg(), null);
        }catch(Exception e){
            return new Result(ResultStatusCode.SYSTEM_ERR.getCode(), ResultStatusCode.SYSTEM_ERR.getMsg(), null);
        }
    }

}
