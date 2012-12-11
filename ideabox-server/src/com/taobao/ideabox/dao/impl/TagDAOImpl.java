package com.taobao.ideabox.dao.impl;

import com.taobao.ideabox.dao.BaseDAO;
import com.taobao.ideabox.dao.TagDAO;
import com.taobao.ideabox.entity.impl.TagDO;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: shufj
 * Date: 11/29/12 8:51 ÏÂÎç
 */
public class TagDAOImpl extends BaseDAO<TagDO> implements TagDAO {
    public TagDAOImpl(){
        super("tags", "id");
        rowMapper = new RowMapper<TagDO>() {
            public TagDO mapRow(ResultSet rs, int i) throws SQLException {
                TagDO tagDO = new TagDO();
                tagDO.setId(rs.getInt("id"));
                tagDO.setDescription(rs.getString("description"));
                tagDO.setName(rs.getString("name"));
                tagDO.setType(rs.getInt("type"));
                tagDO.setStatus(rs.getInt("status"));
                tagDO.setGmtCreate(rs.getDate("gmt_create"));
                return tagDO;
            }
        };
    }

    private PreparedStatementCreator createInsertIdeaPSCreator(final String sql, final TagDO tagDO) {
        return new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tagDO.getName());
                ps.setString(2, tagDO.getDescription());
                ps.setInt(3, tagDO.getType());
                ps.setInt(4, tagDO.getStatus());
                return ps;
            }
        };
    }

    private PreparedStatementCreator createUpdateIdeaPSCreator(final String sql,final TagDO tagDO){
        return  new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tagDO.getName());
                ps.setString(2, tagDO.getDescription());
                ps.setInt(3, tagDO.getType());
                ps.setInt(4, tagDO.getStatus());
                ps.setInt(5,tagDO.getId());
                return ps;
            }
        };
    }

    public int insert(TagDO tagDO) {
        String sql = "insert into tags(name,description, type, status,gmt_create) values(?,?,?,?,now())";
        PreparedStatementCreator psc = createInsertIdeaPSCreator(sql, tagDO);
        return jdbcTemplate.update(psc);
    }

    public int update(TagDO tagDO) {
        String sql = "update tags set description=?, name=?, type=?, status=? where id = ?";
        PreparedStatementCreator psc = createUpdateIdeaPSCreator(sql, tagDO);
        return jdbcTemplate.update(psc);
    }

}
