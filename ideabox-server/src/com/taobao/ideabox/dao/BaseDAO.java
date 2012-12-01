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
 * Time: ����2:11
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
           * ʹ����������֪��PrepardStatement�����в������������ʹ�÷��������˷�װ,
           * update()��������������б�ĸ��²��������м������ذ汾������ʹ�õ���һ��ʹ��
           * PreparedStatementCreator������Ϊ�����ķ���
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
