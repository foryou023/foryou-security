package com.foryou.security.core.properties;

public class BrowserProperties {

    private String loginPage = "/login.html";

    private int rememberMeExpireIn = 3600;

    public int getRememberMeExpireIn() {
        return rememberMeExpireIn;
    }

    public void setRememberMeExpireIn(int rememberMeExpireIn) {
        this.rememberMeExpireIn = rememberMeExpireIn;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

}
