package com.modules.system.exception;

/**
 * @Description: app请求api时携带的token失效异常
 * @Date: 2018/1/4
 * @Author: wcf
 */
public class TokenErrorException extends RuntimeException{

    public TokenErrorException(String message) {
        super(message);
    }

    public TokenErrorException() {
    }
}
