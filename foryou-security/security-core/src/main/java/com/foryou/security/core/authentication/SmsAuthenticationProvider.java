package com.foryou.security.core.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken token = (SmsAuthenticationToken) authentication;
        String mobile = (String) authentication.getPrincipal();
        UserDetails user = null;
        try {
            user = this.userDetailsService.loadUserByUsername(mobile);
            if (user == null) {
                throw new InternalAuthenticationServiceException("用户信息获取失败");
            }
        } catch (AuthenticationException e) {
            throw new SmsAuthenticationException(e.getMessage());
        }
        SmsAuthenticationToken tokenResult = new SmsAuthenticationToken(authentication.getPrincipal(), user.getAuthorities());
        tokenResult.setDetails(authentication.getDetails());
        return tokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
