package com.modules.bootapplication.common.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Date: 2018/1/4
 * @Author: wcf
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSecurity {

}
