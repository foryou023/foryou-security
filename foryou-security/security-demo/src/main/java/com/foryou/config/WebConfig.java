package com.foryou.config;

import com.foryou.filter.UserFilter;
import com.foryou.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /*@Autowired
    private UserInterceptor userInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor);
    }*/

    /*@Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //Callable异步处理注册拦截器
        configurer.registerCallableInterceptors();
        //DeferredResult异步处理注册拦截器
        configurer.registerDeferredResultInterceptors();
        //异常处理超时时间
        configurer.setDefaultTimeout(10000);
        //异步处理配置可重用线程池
        configurer.setTaskExecutor();
    }*/

    /*@Bean
    public FilterRegistrationBean userFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        UserFilter userFilter = new UserFilter();
        bean.setFilter(userFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        bean.setUrlPatterns(urls);
        return bean;
    }*/

}
