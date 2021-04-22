package com.kehao.controller;

import com.kehao.service.HelloService;

public class HelloController {

    private HelloService helloService;

    public void sayHello(){
        String sentence = helloService.sayHello("kehao");
        System.out.println(sentence);
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
