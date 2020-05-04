package com.foryou.security.core.authentication;

import org.springframework.security.core.AuthenticationException;

public class SmsAuthenticationException extends AuthenticationException {
    public SmsAuthenticationException(String msg) {
        super(msg);
    }
}
