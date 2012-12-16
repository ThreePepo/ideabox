package com.taobao.ideabox.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-1
 * Time: 下午2:11
 */
public class BaseDAO <T>{
    @Resource
    protected JdbcTemplate jdbcTemplate;

    protected String tableName;
    protected String primaryKey;
    protected RowMapper<T> rowMapper;

    protected BaseDAO(String tableName, String primaryKey){
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    public int delete(final int primaryKey){
        final String sql =
                "delete from "+tableName+" where "+this.primaryKey+"=?";

        /*
           * 使用我们所熟知的PrepardStatement来进行插入操作，不过使用方法进行了封装,
           * update()在这里可用来进行表的更新操作，它有几个重载版本，如下使用的是一个使用
           * PreparedStatementCreator对象作为参数的方法
           * */
        return jdbcTemplate.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, primaryKey);
                return ps;
            }
        });
    }

    /**
     * 公共查询方法，需指定参数
     * @param condition
     * @param page
     * @param size
     * @return
     */
    public  List<T> query(String condition, int page, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select * from ").append(tableName).append(" ");
        if (!"".endsWith(condition)) {
            sb.append(condition);
        }
        if (size != 0) {
            sb.append(" limit ").append(page * size).append(",").append(size);
        }
        String sql = sb.toString();
        return jdbcTemplate.query(sql,rowMapper);
    }

    public T selectById(int id){
        StringBuilder sb = new StringBuilder();
        sb.append(" select * from ").append(tableName).append(" where id =").append(id);
        List<T> list = jdbcTemplate.query(sb.toString(),rowMapper);
        if(list == null || list.size() <= 0){
            return null;
        }
        return list.get(0);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
