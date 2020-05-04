package com.foryou.security.core.processor;

import com.foryou.security.core.exception.ValidateCodeException;
import com.foryou.security.core.generator.ValidateCodeGenerator;
import com.foryou.security.core.validate.code.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;


public abstract class AbstractValidateCodeProcessor<C> implements ValidateCodeProcessor {

    protected String code;

    protected SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generator(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    protected C generator(ServletWebRequest request) {
        String type = getProcessorType(request);
        return (C) validateCodeGenerators.get(type + "CodeGenerator").generator(request);
    }

    public void save(ServletWebRequest request, C code){
        sessionStrategy.setAttribute(request, VALIDATE_CODE_PROCESSOR_KEY+getProcessorType(request).toUpperCase(), code);
    }

    private String getProcessorType(ServletWebRequest request) {
        String[] url = request.getRequest().getRequestURI().split("/");
        return url[url.length-1];
    }

    @Override
    public void validate(ServletWebRequest request) throws RuntimeException {
        ValidateCode validateCode =getValidateCode(request);
        if (StringUtils.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if(validateCode==null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(validateCode.isExpire()){
            throw new ValidateCodeException("验证码已过期");
        }
        if(!validateCode.getCode().toUpperCase().equals(code)){
            throw new ValidateCodeException("验证码填写错误");
        }
    }

    protected abstract ValidateCode getValidateCode(ServletWebRequest request);


    public abstract void send(ServletWebRequest request,C code) throws Exception;

}
