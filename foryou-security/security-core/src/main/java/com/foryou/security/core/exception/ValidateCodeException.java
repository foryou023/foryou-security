package com.foryou.security.core.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -5060372166440093690L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
