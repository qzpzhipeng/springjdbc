package com.mage.service;

import java.math.BigDecimal;

/**
 * @param
 * @author qzp
 * @create 2020-02-22 16:48
 */
public interface IAccountService {
    public int updateAccountInfo(BigDecimal amount,Integer sourceAid,Integer targetAid);
}
