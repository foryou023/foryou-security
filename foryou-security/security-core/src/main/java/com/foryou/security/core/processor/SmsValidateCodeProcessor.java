package com.foryou.security.core.processor;

import com.foryou.security.core.holder.ValidateCodeProcessorHolder;
import com.foryou.security.core.properties.SecurityConstants;
import com.foryou.security.core.properties.ValidateCodeType;
import com.foryou.security.core.sender.Sender;
import com.foryou.security.core.validate.code.ImageCode;
import com.foryou.security.core.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private Sender sender;

    @Override
    protected ValidateCode getValidateCode(ServletWebRequest request) {
        code = request.getParameter(SecurityConstants.VALIDATE_CODE_SMS_PARAM_KEY);
        return (ValidateCode)sessionStrategy.getAttribute(request, VALIDATE_CODE_PROCESSOR_KEY+ ValidateCodeType.SMS);
    }

    @Override
    public void send(ServletWebRequest request, ValidateCode code) {
        sender.send(request.getRequest().getParameter("mobile"),code.getCode());
    }
}
