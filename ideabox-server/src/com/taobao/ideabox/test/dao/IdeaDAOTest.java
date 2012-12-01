package com.taobao.ideabox.test.dao;

import com.taobao.ideabox.dao.EntityDAO;
import com.taobao.ideabox.dao.IdeaDAO;
import com.taobao.ideabox.dao.impl.IdeaDAOImpl;
import com.taobao.ideabox.entity.impl.IdeaDO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-1
 * Time: ÏÂÎç4:31
 */

public class IdeaDAOTest {
    private IdeaDAOImpl ideaDAO;
    @Before
   public void init(){
       String springResourcePath = "applicationContext.xml";
       ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(springResourcePath);
       JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
       ideaDAO = (IdeaDAOImpl) ctx.getBean("ideaDAO");
       ideaDAO.setJdbcTemplate(jdbcTemplate);
   }
   @Test
   public void testInsert(){
       IdeaDO ideaDO = new IdeaDO();
       ideaDO.setClicks(10);
       ideaDO.setDescription("xxoo");
       ideaDO.setOwner("lansheng");
       ideaDO.setPhoto("xx");
       ideaDO.setStatus(1);
       ideaDO.setVideo("xxxx");
       ideaDO.setUserId(1);
       ideaDO.setGmtCreate(new Date());
       ideaDO.setGmtModified(new Date());
       ideaDAO.insert(ideaDO);
   }
   @Test
   public void testQuery(){
      List<IdeaDO> result = ideaDAO.query("where user_id=1",1,20);
       System.out.println(result.size());
   }
}
