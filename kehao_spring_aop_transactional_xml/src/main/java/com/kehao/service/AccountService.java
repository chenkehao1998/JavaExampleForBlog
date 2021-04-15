package com.kehao.service;

import com.kehao.dao.AccountDao;
import com.kehao.pojo.Account;

import java.util.List;

public class AccountService {

    private AccountDao dao;

    public void setDao(AccountDao dao) {
        this.dao = dao;
    }

    /**
     * 转账
     */
    void transfer(String source, String target, double money){
        Account from = findByName(source);
        Account to = findByName(target);
        from.setMoney(from.getMoney()-money);
        to.setMoney(to.getMoney()+money);
        dao.update(from);
        int i = 1/0;
        dao.update(to);
    }

    /**
     * 保存
     */
    void save(Account account){
        dao.save(account);
    }

    /**
     * 根据id删除
     */
    void delete(Integer id){
        dao.delete(id);
    }

    /**
     * 更新账户
     */
    void update(Account account){
        dao.update(account);
    }

    /**
     * 根据id查询
     */
    Account findById(Integer id){
        Account account = dao.findById(id);
        return account;
    }

    /**
     * 根据名称查询账户
     */
    Account findByName(String name){
        Account account = dao.findByName(name);
        return account;
    }

    /**
     * 查询所有
     */
    List<Account> findAll(){
        List<Account> all = dao.findAll();
        return all;
    }
}
