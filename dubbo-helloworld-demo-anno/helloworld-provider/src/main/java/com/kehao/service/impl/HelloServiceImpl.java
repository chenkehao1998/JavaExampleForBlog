package com.kehao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kehao.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("service:hello!");
        return "Hello,"+name;
    }
}
