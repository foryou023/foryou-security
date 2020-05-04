package com.foryou.security.core.generator;

import com.foryou.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public abstract class AbstractValidateCodeGenerator implements ValidateCodeGenerator {
    // 验证码字符个数
    protected int codeCount;
    // 验证码有效时间
    protected int expireIn;
    // 验证码列表
    protected char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    // 随机数生成器
    protected Random random = new Random();
    // 配置文件
    @Autowired
    protected SecurityProperties securityProperties;

    /**
     * 获取随机数
     */
    protected int getRandomNumber(int number) {
        return random.nextInt(number);
    }

}
