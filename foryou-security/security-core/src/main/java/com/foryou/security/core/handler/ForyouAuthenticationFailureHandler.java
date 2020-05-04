package com.foryou.security.core.handler;

import com.foryou.security.core.exception.ValidateCodeException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ForyouAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof ValidateCodeException){
            request.setAttribute("ValidateCodeException",e);
            request.getRequestDispatcher("/authentication/failure").forward(request,response);
        }
    }
}
