package com.mage;

import com.mage.service.IAccountService;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @param
 * @author qzp
 * @create 2020-02-22 18:59
 */
public class TestService extends TestBase {
    @Resource
    private IAccountService iAccountService;
    @Test
    public void test(){
        System.out.println(iAccountService.updateAccountInfo(BigDecimal.valueOf(1000L), 167, 168));
    }
}
