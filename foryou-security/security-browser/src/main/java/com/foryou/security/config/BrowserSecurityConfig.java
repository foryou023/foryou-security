package com.foryou.security.config;

import com.foryou.security.core.config.SmsSecurityAuthenticationConfig;
import com.foryou.security.core.filter.ValidateCodeFilter;
import com.foryou.security.core.properties.SecurityConstants;
import com.foryou.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsSecurityAuthenticationConfig smsSecurityAuthenticationConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        http.httpBasic().disable()
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()
                .loginPage(SecurityConstants.AUTHENTICATION_REQUIRE_URL)
                .loginProcessingUrl(SecurityConstants.VALIDATE_CODE_IMAGE_AUTHENTICATION_URL)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
            .authorizeRequests()
                .antMatchers(SecurityConstants.AUTHENTICATION_REQUIRE_URL,
                        SecurityConstants.AUTHENTICATION_FAILURE_URL,
                        SecurityConstants.VALIDATE_CODE_SMS_AUTHENTICATION_URL,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.VALIDATE_CODE_URL).permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeExpireIn())
                .userDetailsService(userDetailsService)
                .and()
            .csrf()
                .disable()
            .apply(smsSecurityAuthenticationConfig);
    }
}
