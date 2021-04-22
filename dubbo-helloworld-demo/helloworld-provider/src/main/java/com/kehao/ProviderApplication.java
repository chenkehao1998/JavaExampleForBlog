package com.kehao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ProviderApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-provider.xml");
        context.start();
        System.out.println("provider启动完成！");
        System.in.read();
    }
}
