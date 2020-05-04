package com.foryou.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截请求进行预处理...验证权限...");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        System.out.println("请求访问的组件名称:"+handlerMethod.getBean().getClass().getName());
        System.out.println("请求访问的方法名称:"+handlerMethod.getMethod().getName());
        request.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("控制器执行完成后拦截器执行后处理...对响应增强...");
        System.out.println("耗时："+(System.currentTimeMillis()-(Long) request.getAttribute("startTime"))+"毫秒");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        System.out.println("本次请求响应完成需要处理...记录日志等等...");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        System.out.println("请求访问的组件名称:"+handlerMethod.getBean().getClass().getName());
        System.out.println("请求访问的方法名称:"+handlerMethod.getMethod().getName());
        System.out.println("耗时："+(System.currentTimeMillis()-(Long) request.getAttribute("startTime"))+"毫秒");
        System.out.println("异常："+e);
    }
}
