package com.kehao;

import com.kehao.controller.HelloController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ConsumerApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-consumer.xml");
        HelloController helloController = context.getBean(HelloController.class);
        helloController.sayHello();
        System.out.println("provider运行结束");
        System.in.read();
    }
}
