package com.modules.system.exception;

public class TokenErrorException extends RuntimeException{

    public TokenErrorException(String message) {
        super(message);
    }

    public TokenErrorException() {
    }
}
