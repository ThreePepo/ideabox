package com.taobao.ideabox.test.dao;

import com.taobao.ideabox.dao.impl.TagDAOImpl;
import com.taobao.ideabox.entity.impl.TagDO;
import org.junit.Before;
import org.junit.Test;

/**
 * User: shufj
 * Date: 12/1/12 6:31 ÏÂÎç
 */
public class TagDAOTest extends BaseTest{

    private TagDAOImpl tagDAO;

    @Before
    public void init(){
        super.init();
        tagDAO = (TagDAOImpl) ctx.getBean("tagDAO");
        tagDAO.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    public void testInsert(){
        TagDO tagDO = new TagDO();
        tagDO.setName("java");
        tagDO.setDescription("java tag");
        tagDO.setType(1);

        tagDAO.insert(tagDO);
    }
}
