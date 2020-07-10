package com.modules.bootapplication.modules.intercepter;

import com.modules.bootapplication.common.annotation.IgnoreSecurity;
import com.modules.bootapplication.common.oauth.Audience;
import com.modules.bootapplication.common.oauth.JwtHelper;
import com.modules.bootapplication.common.utils.StringUtils;
import com.modules.bootapplication.modules.exception.TokenErrorException;
import com.modules.bootapplication.modules.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求api服务器时，对accessToken进行拦截判断，有效则可以反问接口，否则返回错误
 *
 * @author Win7
 */
@Component
public class InterceptorJWT extends HandlerInterceptorAdapter {

    @Autowired
    private Audience audienceEntity;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        // 若目标方法忽略了安全性检查，则直接调用目标方法
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            //如果方法上有@IgnoreSecurity注解，则不需要进行token验证
            IgnoreSecurity ignoreSecurity = ((HandlerMethod) handler).getMethodAnnotation(IgnoreSecurity.class);
            if (ignoreSecurity != null) {
                return true;
            }
        }
        String accessToken = StringUtils.isNotEmpty(request.getParameter("token")) ? request.getParameter("token") : request.getHeader("token");
        if (StringUtils.isNotEmpty(accessToken)) {
            String HeadStr = accessToken.substring(0, 6).toLowerCase();
            if (HeadStr.equals("bearer")) {
                accessToken = accessToken.substring(6);
                Claims claims = JwtHelper.parseJWT(accessToken, audienceEntity.getBase64Secret());
                //判断密钥是否相等，如果不等则认为时无效的token
                if (claims != null) {
                    Long userId = (Long) claims.get("userId");
                    //token未失效，token需要和redis服务器中的储存的token值一样才有效
                    String serviceToken = redisUtils.getToken(userId);
                    System.out.println("service redis token : " + serviceToken);
                    System.out.println("request accessToken : " + accessToken);
                    System.out.println("token" + (accessToken.equals(serviceToken) ? "一致" : "不一致"));
                    if (claims.getAudience().equals(audienceEntity.getClientId()) && accessToken.equals(serviceToken)) {
                        request.setAttribute("userId", userId);
                        return true;
                    }
                } else {
                    System.out.println("token解码失败");
                }
            }
        } else {
            System.out.println("请传递token");
        }
        throw new TokenErrorException();
    }


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        super.postHandle(request, response, handler, modelAndView);
    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        super.afterCompletion(request, response, handler, ex);
    }


    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request,
                                               HttpServletResponse response, Object handler) throws Exception {

        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
