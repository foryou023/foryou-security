package com.foryou.security.controller;

import com.foryou.security.core.exception.ValidateCodeException;
import com.foryou.security.core.properties.SecurityProperties;
import com.foryou.security.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {

    /**
     * 请求缓存
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 引发跳转对象
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //拿到缓存中的请求
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if (savedRequest!=null) {
            //拿到引发转发的url
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的url是："+targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")) {
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要做登录认证，请引导用户到登录页面");
    }

    /**
     * 认证失败时跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/failure")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse failureAuthentication(HttpServletRequest request, HttpServletResponse response){
        //拿到缓存中的请求
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if (savedRequest!=null) {
            //拿到引发转发的url
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的url是："+targetUrl);
        }
        return new SimpleResponse(((ValidateCodeException)request.getAttribute("ValidateCodeException")).getMessage());
    }


}
