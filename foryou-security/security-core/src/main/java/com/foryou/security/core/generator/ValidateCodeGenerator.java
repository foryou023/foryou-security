package com.foryou.security.core.generator;

import com.foryou.security.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
    /**
     * 参数初始化
     * @param request
     */
    void init(ServletWebRequest request);

    /**
     * 获取验证码
     * @param request
     * @return
     */
    ValidateCode generator(ServletWebRequest request);
}
