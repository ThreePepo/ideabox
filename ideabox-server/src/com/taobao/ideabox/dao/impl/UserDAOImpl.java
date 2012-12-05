package com.taobao.ideabox.dao.impl;

import com.taobao.ideabox.dao.BaseDAO;
import com.taobao.ideabox.dao.UserDAO;
import com.taobao.ideabox.entity.impl.UserDO;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: shufj
 * Date: 11/29/12 8:50 ÏÂÎç
 */
public class UserDAOImpl extends BaseDAO<UserDO> implements UserDAO {

    public UserDAOImpl() {
        super("users", "id");
        rowMapper = new RowMapper<UserDO>() {
            public UserDO mapRow(ResultSet rs, int i) throws SQLException {
                UserDO userDO = new UserDO();
                userDO.setId(rs.getInt("id"));
                userDO.setNick(rs.getString("nick"));
                userDO.setMainTag(rs.getString("main_tag"));
                userDO.setMoreTags(rs.getString("more_tag"));
                userDO.setStatus(rs.getInt("status"));
                userDO.setGmtCreate(rs.getDate("gmt_create"));
                userDO.setGmtModified(rs.getDate("gmt_modified"));
                return userDO;
            }
        };
    }

    private PreparedStatementCreator createInsertIdeaPSCreator(final String sql, final UserDO userDO) {
        return new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, userDO.getNick());
                ps.setString(2, userDO.getMainTag());
                ps.setString(3, userDO.getMoreTags());
                ps.setInt(4, userDO.getStatus());
                return ps;
            }
        };
    }

    private PreparedStatementCreator createUpdateIdeaPSCreator(final String sql, final UserDO userDO) {
        return new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, userDO.getNick());
                ps.setString(2, userDO.getMainTag());
                ps.setString(3, userDO.getMoreTags());
                ps.setInt(4, userDO.getStatus());
                ps.setInt(5, userDO.getId());
                return ps;
            }
        };
    }

    public int insert(UserDO userDO) {
         final String sql = "insert into " + tableName+ " (nick, main_tag, more_tags, status,gmt_create,gmt_modified) values(?,?,?,?,now(),now())";
        PreparedStatementCreator psc = createInsertIdeaPSCreator(sql, userDO);
        return jdbcTemplate.update(psc);
    }

    public int update(UserDO userDO) {
        final String sql = "update " + tableName +  " set nick=?, main_tag=?, more_tags=?, status=?,gmt_modified=now() where id=?";
        PreparedStatementCreator psc = createUpdateIdeaPSCreator(sql, userDO);
        return jdbcTemplate.update(psc);
    }

}
