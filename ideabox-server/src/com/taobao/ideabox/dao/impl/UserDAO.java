package com.taobao.ideabox.dao.impl;

import com.taobao.ideabox.dao.BaseDAO;
import com.taobao.ideabox.dbAccess.DBAccess;
import com.taobao.ideabox.entity.impl.UserDO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: shufj
 * Date: 11/29/12 8:50 ÏÂÎç
 */
public class UserDAO  implements BaseDAO<UserDO> {

    public boolean insert(UserDO userDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into users (nick, main_tag, more_tags, status,gmt_create,gmt_modified) values(");
        sb.append(userDO.getNick()).append(",");
        sb.append(userDO.getMainTag()).append(",");
        sb.append(userDO.getMoreTags()).append(",");
        sb.append(userDO.getStatus()).append(",");
        sb.append(new Date().toString()).append(",");
        sb.append(new Date().toString()).append(")");
        return DBAccess.insertData(sb.toString());
    }

    public boolean update(UserDO userDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("update users set");
        sb.append("nick=").append(userDO.getNick()).append(",");
        sb.append("main_tag=").append(userDO.getMainTag()).append(",");
        sb.append("more_tags=").append(userDO.getMoreTags()).append(",");
        sb.append("status=").append(userDO.getStatus()).append(",");
        sb.append("gmt_modified=").append(new Date().toString());
        sb.append("where id=").append(userDO.getId());
        return DBAccess.updateData(sb.toString());
    }

    public boolean delete(UserDO userDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from users where id=").append(userDO.getId());
        return DBAccess.updateData(sb.toString());
    }

    public List<UserDO> Query(String where, int page, int size, String order) {
        List<UserDO> list = new ArrayList<UserDO>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id,nick, main_tag, more_tags, status,gmt_create,gmt_modified from users ");
        if (!"".endsWith(where)) {
            sb.append("where ").append(where);
        }
        if (!"".endsWith(order)) {
            sb.append(" order by ").append(order);
        }
        if (size != 0) {
            sb.append(" limit ").append(page * size).append(",").append(size);
        }
        ResultSet rs = DBAccess.Query(sb.toString());
        if (rs == null) {
            DBAccess.closeConnection();
            return null;
        }
        try {
            while (rs.next()) {
                UserDO userDO = new UserDO();
                userDO.setId(rs.getInt("id"));
                userDO.setNick(rs.getString("nick"));
                userDO.setMainTag(rs.getString("main_tag"));
                userDO.setMoreTags(rs.getString("more_tags"));
                userDO.setStatus(rs.getInt("status"));
                userDO.setGmtCreate(rs.getDate("gmt_create"));
                userDO.setGmtModified(rs.getDate("gmt_modified"));

                list.add(userDO);
            }
            rs.close();
            DBAccess.closeConnection();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
