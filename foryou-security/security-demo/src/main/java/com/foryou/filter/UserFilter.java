package com.foryou.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

//@Component
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("UserFilter初始化...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("UesrFilter开始执行...");
        chain.doFilter(request,response);
        System.out.println("UesrFilter结束执行...");
    }

    @Override
    public void destroy() {
        System.out.println("UserFilter销毁...");
    }
}
