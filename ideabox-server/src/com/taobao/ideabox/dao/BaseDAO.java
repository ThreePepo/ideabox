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
 * Time: ����2:11
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
           * ʹ����������֪��PrepardStatement�����в������������ʹ�÷��������˷�װ,
           * update()��������������б�ĸ��²��������м������ذ汾������ʹ�õ���һ��ʹ��
           * PreparedStatementCreator������Ϊ�����ķ���
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
     * ������ѯ��������ָ������
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
