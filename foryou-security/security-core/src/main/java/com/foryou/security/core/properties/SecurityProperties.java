package com.foryou.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "foryou.security")
public class SecurityProperties {

    public SecurityProperties() {
        System.out.println("SecurityProperties创建成功...");
    }

    /**
     * 浏览器配置文件
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码配置文件
     */
    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
