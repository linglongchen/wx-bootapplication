package com.modules.bootapplication.modules.exception;

/**
 * @Description: openApi请求时携带的签名错误异常
 * @Date: 2018/1/4
 * @Author: wcf
 */
public class SignErrorException extends RuntimeException{

    public SignErrorException(String message) {
        super(message);
    }

    public SignErrorException() {
    }
}
