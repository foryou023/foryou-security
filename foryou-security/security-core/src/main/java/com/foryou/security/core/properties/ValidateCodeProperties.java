package com.foryou.security.core.properties;

public class ValidateCodeProperties {

    /**
     * 短信验证码配置
     */
    private SmsCodeProperties smsCode = new SmsCodeProperties();
    /**
     * 图片验证码配置
     */
    private ImageCodeProperties imageCode = new ImageCodeProperties();

    public SmsCodeProperties getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SmsCodeProperties smsCode) {
        this.smsCode = smsCode;
    }

    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
    }
}
