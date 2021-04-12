package com.kehao.dao;

import com.kehao.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void selectUserByName(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserDao userDao = (UserDao) ac.getBean("userDao");
        User user = userDao.selectUserByName("zs");
        System.out.println(user);
    }
}