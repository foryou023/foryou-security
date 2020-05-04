package com.foryou.security.core.validate.code;

import java.time.LocalDateTime;

public class ValidateCode {
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间节点
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
