package com.taobao.ideabox.dao.impl;

import com.taobao.ideabox.dao.BaseDAO;
import com.taobao.ideabox.dbAccess.DBAccess;
import com.taobao.ideabox.entity.impl.UserTagDO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * User: shufj
 * Date: 11/29/12 8:51 ÏÂÎç
 */
public class UserTagDAO extends BaseDAO{
    public UserTagDAO(){
      super("","");
    }

    public boolean insert(UserTagDO userTagDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into user_tag(user_id, tag_id) values(");
        sb.append(userTagDO.getUserId()).append(",");
        sb.append(userTagDO.getTagId()).append(")");
        return DBAccess.insertData(sb.toString());
    }

    public boolean update(UserTagDO userTagDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("update user_tag set");
        sb.append("user_id=").append(userTagDO.getUserId()).append(",");
        sb.append("tag_id=").append(userTagDO.getTagId());
        return DBAccess.updateData(sb.toString());
    }

    public boolean delete(UserTagDO userTagDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from user_tag where user_id=").append(userTagDO.getUserId());
        return DBAccess.updateData(sb.toString());
    }

    public List<UserTagDO> Query(String where, int page, int size, String order) {
        List<UserTagDO> list = new ArrayList<UserTagDO>();
        StringBuilder sb = new StringBuilder();
        sb.append("select user_id, tag_id from user_tag ");
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
                UserTagDO userTagDO = new UserTagDO();
                userTagDO.setUserId(rs.getInt("user_id"));
                userTagDO.setTagId(rs.getInt("tag_id"));
                list.add(userTagDO);
            }
            rs.close();
            DBAccess.closeConnection();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

}
