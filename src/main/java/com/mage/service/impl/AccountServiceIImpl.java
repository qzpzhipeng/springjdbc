package com.mage.service.impl;

import com.mage.dao.AccountDao;
import com.mage.service.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @param
 * @author qzp
 * @create 2020-02-22 17:06
 */
@Service
public class AccountServiceIImpl implements IAccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateAccountInfo(BigDecimal amount, Integer sourceAid, Integer targetAid) {
        int result = 0;
        int m = accountDao.accountPayOut(sourceAid, amount);
        //int a = 1/0;
        int n = accountDao.accountPayIn(targetAid, amount);
        if(m==1&&n==1){
            result=1;
        }
        return result;

    }
}
