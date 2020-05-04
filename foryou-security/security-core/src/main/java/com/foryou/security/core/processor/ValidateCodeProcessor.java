package com.foryou.security.core.processor;

import com.foryou.security.core.validate.code.ValidateCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {

    public static final String VALIDATE_CODE_PROCESSOR_KEY = "SESSION_KEY_CODE_";

    /**
     * 创建验证码
     * @param request
     * @return
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     * @param request
     * @throws Exception
     */
    void validate(ServletWebRequest request) throws AuthenticationException;

}
