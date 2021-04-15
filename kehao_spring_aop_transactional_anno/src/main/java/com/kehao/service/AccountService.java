package com.kehao.service;

import com.kehao.dao.AccountDao;
import com.kehao.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AccountService {

    @Autowired
    private AccountDao dao;

    public void setDao(AccountDao dao) {
        this.dao = dao;
    }

    /**
     * 转账
     */
    public void transfer(String source, String target, double money){
        Account from = findByName(source);
        Account to = findByName(target);
        from.setMoney(from.getMoney()-money);
        to.setMoney(to.getMoney()+money);
        dao.update(from);
        dao.update(to);
//        throw new RuntimeException();
    }

    /**
     * 保存
     */
    public void save(Account account){
        dao.save(account);
    }

    /**
     * 根据id删除
     */
    public void delete(Integer id){
        dao.delete(id);
    }

    /**
     * 更新账户
     */
    public void update(Account account){
        dao.update(account);
    }

    /**
     * 根据id查询
     */
    public Account findById(Integer id){
        Account account = dao.findById(id);
        return account;
    }

    /**
     * 根据名称查询账户
     */
    public Account findByName(String name){
        Account account = dao.findByName(name);
        return account;
    }

    /**
     * 查询所有
     */
    public List<Account> findAll(){
        List<Account> all = dao.findAll();
        return all;
    }
}
