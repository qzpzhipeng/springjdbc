package com.mage.dao;

import com.mage.vo.Account;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @param
 * @author qzp
 * @create 2020-02-21 22:16
 */
@Repository
public interface AccountDao {
    /*添加*/
    public Integer saveAccount(Account account);

    public Integer saveAccountHasKey(Account account);

    public Integer saveAccountBatch(List<Account> list);
    /*统计和查询*/
    public Integer countAccountsByUserId(Integer userId);

    public Account queryAccountById(Integer aid);

    public List<Account> queryAccountsByParams(String aname,Integer type,Integer userId,String time);

    public List<Account> queryAccountsByParams(String aname,Integer type,Integer userId,String time,Integer pageNum,Integer pageSize);

    /*更新*/
    public Integer updateAccountById(Account account);

    public Integer updateAccountsBach(List<Account> accounts);

    /*删除*/
    public Integer deleteAccountById(Integer aid);

    public Integer deleteAccountsBach(Integer... aids);

    public int accountPayOut(Integer sourceAid, BigDecimal amount);

    public int accountPayIn(Integer targetAid,BigDecimal amount);
}
