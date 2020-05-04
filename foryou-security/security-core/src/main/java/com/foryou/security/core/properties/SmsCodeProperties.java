package com.foryou.security.core.properties;

public class SmsCodeProperties {
    // 验证码字符个数
    private int codeCount = 6;
    // 验证码有效时间
    private int expireIn = 60;
    //拦截url
    private String url;

    public int getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(int codeCount) {
        this.codeCount = codeCount;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
