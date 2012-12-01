package com.taobao.ideabox.test.dao;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * User: shufj
 * Date: 12/1/12 6:31 обнГ
 */
public class BaseTest {
    public JdbcTemplate jdbcTemplate;
    public ClassPathXmlApplicationContext ctx;

    @Before
    public void init(){
        String springResourcePath = "applicationContext.xml";
       ctx = new ClassPathXmlApplicationContext(springResourcePath);
       jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
    }

}
