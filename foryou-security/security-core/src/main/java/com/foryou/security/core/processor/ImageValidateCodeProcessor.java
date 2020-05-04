package com.foryou.security.core.processor;

import com.foryou.security.core.holder.ValidateCodeProcessorHolder;
import com.foryou.security.core.properties.SecurityConstants;
import com.foryou.security.core.properties.ValidateCodeType;
import com.foryou.security.core.validate.code.ImageCode;
import com.foryou.security.core.validate.code.ValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    protected ValidateCode getValidateCode(ServletWebRequest request) {
        code = request.getParameter(SecurityConstants.VALIDATE_CODE_IMAGE_PARAM_KEY);
        return (ImageCode)sessionStrategy.getAttribute(request, VALIDATE_CODE_PROCESSOR_KEY+ ValidateCodeType.IMAGE);
    }

    @Override
    public void send(ServletWebRequest request, ImageCode code)throws Exception {
        ImageIO.write(code.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
