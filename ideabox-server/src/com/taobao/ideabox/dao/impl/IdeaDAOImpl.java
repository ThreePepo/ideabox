package com.taobao.ideabox.dao.impl;

import com.taobao.ideabox.dao.BaseDAO;
import com.taobao.ideabox.dao.IdeaDAO;
import com.taobao.ideabox.entity.impl.IdeaDO;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: shufj
 * Date: 11/29/12 7:33 ÏÂÎç
 */
@Repository("ideaDAO")
public class IdeaDAOImpl  extends BaseDAO implements IdeaDAO {

    public IdeaDAOImpl(){
        super("ideas", "id");
        rowMapper = new  RowMapper<IdeaDO>() {
            public IdeaDO mapRow(ResultSet rs, int i) throws SQLException {
                IdeaDO ideaDO = new IdeaDO();
                ideaDO.setId(rs.getInt("id"));
                ideaDO.setDescription(rs.getString("description"));
                ideaDO.setVideo(rs.getString("video"));
                ideaDO.setPhoto(rs.getString("photo"));
                ideaDO.setClicks(rs.getInt("clicks"));
                ideaDO.setUserId(rs.getInt("user_id"));
                ideaDO.setStatus(rs.getInt("status"));
                ideaDO.setGmtCreate(rs.getDate("gmt_create"));
                ideaDO.setGmtModified(rs.getDate("gmt_modified"));
                return ideaDO;
            }
        };
    }


    private PreparedStatementCreator createInsertIdeaPSCreator(final String sql,final IdeaDO ideaDO){
        return  new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,ideaDO.getDescription());
                ps.setString(2, ideaDO.getVideo());
                ps.setString(3, ideaDO.getPhoto());
                ps.setInt(4, ideaDO.getClicks());
                ps.setInt(5, ideaDO.getUserId());
                ps.setInt(6, ideaDO.getStatus());
                return ps;
            }
        };
    }

    private PreparedStatementCreator createUpdateIdeaPSCreator(final String sql,final IdeaDO ideaDO){
        return  new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,ideaDO.getDescription());
                ps.setString(2,ideaDO.getVideo());
                ps.setString(3, ideaDO.getPhoto());
                ps.setInt(4, ideaDO.getClicks());
                ps.setInt(5, ideaDO.getUserId());
                ps.setInt(6, ideaDO.getStatus());
                ps.setInt(7,ideaDO.getId());
                return ps;
            }
        };
    }
    public int insert(IdeaDO ideaDO) {
        String sql = "insert into ideas(description,video,photo,clicks,user_id, status,gmt_create,gmt_modified) values(?,?,?,?,?,?,now(),now())";
        PreparedStatementCreator psc = createInsertIdeaPSCreator(sql, ideaDO);
        return jdbcTemplate.update(psc);
    }

    public int update(IdeaDO ideaDO) {
        String sql = "udpate ideas set description=?, video=?,photo=?,clicks=?,user_id=?,status=?,gmt_modified=now() where id=?";
        return jdbcTemplate.update(createUpdateIdeaPSCreator(sql,ideaDO));
    }
}
