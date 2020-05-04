package com.foryou.security.core.holder;

import com.foryou.security.core.generator.ValidateCodeGenerator;
import com.foryou.security.core.processor.ValidateCodeProcessor;
import com.foryou.security.core.properties.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("validateCodeProcessorHolder")
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;


    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        return validateCodeProcessors.get(type.toString().toLowerCase()+"ValidateCodeProcessor");
    }

}
