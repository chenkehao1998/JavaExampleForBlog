package com.kehao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kehao.service.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloController {

    @Reference
    private HelloService helloService;

    public void sayHello(){
        String sentence = helloService.sayHello("kehao");
        System.out.println(sentence);
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
