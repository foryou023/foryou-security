package com.foryou.security.core.filter;

import com.foryou.security.core.exception.ValidateCodeException;
import com.foryou.security.core.holder.ValidateCodeProcessorHolder;
import com.foryou.security.core.processor.ValidateCodeProcessor;
import com.foryou.security.core.properties.SecurityConstants;
import com.foryou.security.core.properties.SecurityProperties;
import com.foryou.security.core.properties.ValidateCodeType;
import com.foryou.security.core.validate.code.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationFailureHandler authenticationFailureHandler;

    private Map<String, ValidateCodeType> urlsMap = new HashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private SecurityProperties securityProperties;

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    /**
     * 组件初始化
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlsMap.put(SecurityConstants.VALIDATE_CODE_IMAGE_AUTHENTICATION_URL, ValidateCodeType.IMAGE);
        putUrlToMap(securityProperties.getValidateCode().getImageCode().getUrl(), ValidateCodeType.IMAGE);

        urlsMap.put(SecurityConstants.VALIDATE_CODE_SMS_AUTHENTICATION_URL, ValidateCodeType.SMS);
        putUrlToMap(securityProperties.getValidateCode().getSmsCode().getUrl(), ValidateCodeType.SMS);
    }

    private void putUrlToMap(String urls, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urls)) {
            for (String url : urls.split(",")) {
                urlsMap.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        boolean action = false;

        for (String url : urlsMap.keySet()) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }

        ValidateCodeType type = getValidateCodeType(request);
        if (action) {
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response));
            } catch (AuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    protected ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        return urlsMap.get(request.getRequestURI());
    }

}
