package com.mage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author qzp
 * @create 2020-02-14 11:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestSpringJdbc {
    @Resource
    private JdbcTemplate jdbcTemplate;
    /*@Before
    public void init(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate = (JdbcTemplate) beanFactory.getBean("jdbcTemplate");
        Integer integer = jdbcTemplate.queryForObject("select count(*) from grade", Integer.class);
        System.out.println(integer);
    }*/
    @Test
    public void test(){
        System.out.println(jdbcTemplate.queryForObject("select count(*) from grade", Integer.class));
    }
}
