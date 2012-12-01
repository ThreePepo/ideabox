package com.taobao.ideabox.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-1
 * Time: 下午2:11
 */
public class BaseDAO {
    @Resource
    private JdbcTemplate jdbcTemplate;

    protected String tableName;
    protected String primaryKey;

    protected BaseDAO(String tableName, String primaryKey){
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    protected void delete(final int primaryKey){
        final String sql =
                "delete from "+tableName+" where "+this.primaryKey+"=?";

        /*
           * 使用我们所熟知的PrepardStatement来进行插入操作，不过使用方法进行了封装,
           * update()在这里可用来进行表的更新操作，它有几个重载版本，如下使用的是一个使用
           * PreparedStatementCreator对象作为参数的方法
           * */
        jdbcTemplate.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, primaryKey);
                return ps;
            }
        });
    }

}
