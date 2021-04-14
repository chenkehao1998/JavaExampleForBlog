package com.kehao.pojo;

import org.springframework.stereotype.Component;

@Component
public class Student {
    public void speak(){
        System.out.println("i love Java!");
//        throw new RuntimeException("123456");
    }
}
