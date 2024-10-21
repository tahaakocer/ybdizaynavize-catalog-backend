package com.tahaakocer.ybdizaynavize.exception.user;


public class TokenMissingException extends RuntimeException {
    public TokenMissingException(String tokenIsMissing) {
        super(tokenIsMissing);
    }
}
