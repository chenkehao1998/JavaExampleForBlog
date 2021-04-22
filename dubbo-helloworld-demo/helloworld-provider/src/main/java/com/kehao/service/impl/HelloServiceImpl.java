package com.kehao.service.impl;

import com.kehao.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("service:hello!");
        return "Hello,"+name;
    }
}
