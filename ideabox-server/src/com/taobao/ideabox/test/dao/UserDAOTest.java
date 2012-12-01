package com.taobao.ideabox.test.dao;

import com.taobao.ideabox.dao.impl.UserDAOImpl;
import com.taobao.ideabox.entity.impl.UserDO;
import org.junit.Before;
import org.junit.Test;

/**
 * User: shufj
 * Date: 12/1/12 6:13 ÏÂÎç
 */
public class UserDAOTest extends BaseTest{

    private UserDAOImpl userDAO;
    @Before
    public void init(){
        super.init();
       userDAO = (UserDAOImpl) ctx.getBean("userDAO");
       userDAO.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    public void testInsert(){
        UserDO userDO = new UserDO();
        userDO.setNick("sunloc");
        userDO.setMainTag("java");
        userDO.setMoreTags("java . . .");
        userDO.setStatus(1);

        userDAO.insert(userDO);
    }

}
