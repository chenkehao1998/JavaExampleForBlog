package com.kehao.service;

import com.kehao.pojo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AccountServiceTest {

    @Autowired
    private AccountService service;

    @Test
    public void transfer() {
        service.transfer("aaa","bbb",1);
    }

    @Test
    public void save() {
        Account account = new Account();
        account.setName("小明");
        account.setMoney(123d);
        service.save(account);
    }

    @Test
    public void delete() {
        service.delete(5);
    }

    @Test
    public void update() {
        Account account = new Account();
        account.setId(3);
        account.setName("小xxx");
        account.setMoney(123d);
        service.update(account);
    }

    @Test
    public void findById() {
        Account account = service.findById(1);
        System.out.println(account);
    }

    @Test
    public void findByName() {
        Account account = service.findByName("asd");
        System.out.println(account);
    }

    @Test
    public void findAll() {
        List<Account> all = service.findAll();
        for (Account i : all) {
            System.out.println(i);
        }
    }
}