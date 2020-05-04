package com.foryou.security.core.generator;

import com.foryou.security.core.validate.code.ValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCodeGenerator")
public class SmsCodeGenerator extends AbstractValidateCodeGenerator {

    /**
     * 参数初始化
     */
    public void init(ServletWebRequest request) {
        codeCount = securityProperties.getValidateCode().getSmsCode().getCodeCount();
        expireIn = securityProperties.getValidateCode().getSmsCode().getExpireIn();
    }


    /**
     * 创建并获取图片
     *
     * @return
     */
    @Override
    public ValidateCode generator(ServletWebRequest request) {
        //参数初始化
        init(request);
        // 生成随机验证码
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            randomCode.append(strRand);
        }
        return new ValidateCode(randomCode.toString(), expireIn);
    }

}
