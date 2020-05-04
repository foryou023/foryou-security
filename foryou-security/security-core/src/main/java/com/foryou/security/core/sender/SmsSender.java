package com.foryou.security.core.sender;

public class SmsSender implements Sender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("给"+mobile+"手机号发送验证码"+code);
    }
}
