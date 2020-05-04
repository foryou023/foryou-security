package com.foryou.security.core.config;

import com.foryou.security.core.generator.ImageCodeGenerator;
import com.foryou.security.core.sender.Sender;
import com.foryou.security.core.sender.SmsSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ImageCodeGenerator imageCodeGenerator(){
        return new ImageCodeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(Sender.class)
    public Sender createSender(){
        return new SmsSender();
    }


}
