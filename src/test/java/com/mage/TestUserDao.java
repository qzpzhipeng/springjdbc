package com.mage;

import com.mage.dao.AccountDao;
import com.mage.vo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @param
 * @author qzp
 * @create 2020-02-22 7:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestUserDao {
    @Resource
    private AccountDao accountDao;
    @Test
    public void test01(){
        Account account = new Account();
        account.setAname("我的第一痛金");
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        account.setMoney(BigDecimal.valueOf(8000L));
        account.setType(1);
        account.setRemarker("我的第一桶金");
        account.setUserId(20);
        Integer integer = accountDao.saveAccount(account);
        System.out.println(integer);
    }
    @Test
    public void test02(){
        Account account = new Account();
        account.setAname("我的第二痛金");
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        account.setMoney(BigDecimal.valueOf(10000L));
        account.setType(1);
        account.setRemarker("我的第二桶金");
        account.setUserId(30);
        System.out.println(accountDao.saveAccountHasKey(account));
    }
    @Test
    public void test03(){
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Account account = new Account();
            account.setAname("我的第二痛金"+i);
            account.setCreateTime(new Date());
            account.setUpdateTime(new Date());
            account.setMoney(BigDecimal.valueOf(10000L));
            account.setType(1);
            account.setRemarker("我的第二桶金"+i);
            account.setUserId(40);
            accounts.add(account);
        }
        System.out.println(accountDao.saveAccountBatch(accounts));
    }
    @Test
    public void test04(){
        System.out.println(accountDao.countAccountsByUserId(30));
    }
    @Test
    public void test05(){
        System.out.println(accountDao.queryAccountById(167));
    }
    @Test
    public void test06(){
        List<Account> list = accountDao.queryAccountsByParams("test", null, 30, "2020-02-23");
        for (Account account : list) {
            System.out.println(account);
        }
    }
    @Test
    public void test07(){
       accountDao.queryAccountsByParams("",null,30,"",null,null).forEach(d->{
           System.out.println(d);
       });
    }
    @Test
    public void test08(){
        System.out.println(accountDao.deleteAccountById(188));
    }
    @Test
    public void test09(){
        Integer integer = accountDao.deleteAccountsBach(186, 187, 185);
        System.out.println(integer);
    }
    @Test
    public void test10(){
        Account account = new Account();
        account.setUserId(100);
        account.setAname("你好！");
        account.setType(3);
        account.setMoney(BigDecimal.valueOf(800000L));
        account.setCreateTime(new Date());
        account.setRemarker("加油！");
        account.setUpdateTime(new Date());
        account.setId(184);
        System.out.println(accountDao.updateAccountById(account));
    }
    @Test
    public void test11(){
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Account account = new Account();
            account.setUserId(100);
            account.setAname("你好!"+i);
            account.setType(3);
            account.setMoney(BigDecimal.valueOf(800000L));
            account.setCreateTime(new Date());
            account.setRemarker("加油！"+i);
            account.setUpdateTime(new Date());
            account.setId(170+i);
            accounts.add(account);
        }
        System.out.println(accountDao.updateAccountsBach(accounts));
    }
}
