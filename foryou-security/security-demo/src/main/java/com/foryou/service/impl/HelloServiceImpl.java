package com.foryou.service.impl;

import com.foryou.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "哈喽...";
    }
}
