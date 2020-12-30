package com.modules.common.config;

import com.modules.common.utils.LogBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LogAspect {
    @Autowired
    private MongoTemplate mongoTemplate;


    //切点的注解  是指那些方法需要被执行"AOP"
    @Pointcut("execution(* com.modules.system.api..*.*(..))")
    public void logPoinCut(){}//定义一个切入点
    //返回后通知value="logPoinCut()"是指通知是在logPoinCut()切点返回后通知的
    //returning="rtv"是返回值
    //@AfterReturning这个注解是返回后通知的注解
    @AfterReturning(value="logPoinCut()",returning="rtv")
    //JoinPoint是连接点的意思我们要获取到的如类名，方法名，请求参数等都是从连接点中取出来的
    public void afterLog(JoinPoint joinpoint, Object rtv) {
        System.out.println("进去切点。。。。。");
        LogBean logBean = new LogBean();
        logBean.setCreateDate(new Date());
        //获取类名
        String classname = joinpoint.getTarget().getClass().getSimpleName();
        logBean.setClassName(classname);
        //获取方法名
        String method = joinpoint.getSignature().getName();
        logBean.setMethod(method);
        //获取请求参数
        String reqparam = "";
        logBean.setReqParam(reqparam);
        //返回值
        if (rtv != null) {
            logBean.setReqParam(rtv.toString());
        }
        //获取request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
		/*	UserBean user = (UserBean) request.getSession().getAttribute("user");
		if(user!=null){
			logBean.setUserId(user.getId());
		}*/
        //获取ip地址是封装好的一个类
        String ip = CommonUtils.getIp(request);
        logBean.setIp(ip);
        //保存mongodb
        mongoTemplate.save(logBean);
    }
}
