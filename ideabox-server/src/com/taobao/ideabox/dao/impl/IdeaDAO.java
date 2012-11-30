package com.taobao.ideabox.dao.impl;

import com.taobao.ideabox.dao.BaseDAO;
import com.taobao.ideabox.dbAccess.DBAccess;
import com.taobao.ideabox.entity.impl.IdeaDO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: shufj
 * Date: 11/29/12 7:33 ÏÂÎç
 */
public class IdeaDAO implements BaseDAO<IdeaDO> {

    public boolean insert(IdeaDO ideaDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ideas(description,video,photo,clicks,owner,user_id, status,gmt_create,gmt_modified) values(");
        sb.append(ideaDO.getDescription()).append(",");
        sb.append(ideaDO.getVideo()).append(",");
        sb.append(ideaDO.getPhoto()).append(",");
        sb.append(ideaDO.getClicks()).append(",");
        sb.append(ideaDO.getOwner()).append(",");
        sb.append(ideaDO.getUserId()).append(",");
        sb.append(ideaDO.getStatus()).append(",");
        sb.append(new Date().toString()).append(",");
        sb.append(new Date().toString()).append(")");
        return DBAccess.insertData(sb.toString());
    }

    public boolean update(IdeaDO ideaDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("update ideas set");
        sb.append("description=").append(ideaDO.getDescription()).append(",");
        sb.append("video=").append(ideaDO.getVideo()).append(",");
        sb.append("photo=").append(ideaDO.getPhoto()).append(",");
        sb.append("clicks=").append(ideaDO.getClicks()).append(",");
        sb.append("owner=").append(ideaDO.getOwner()).append(",");
        sb.append("user_id=").append(ideaDO.getUserId()).append(",");
        sb.append("status=").append(ideaDO.getStatus()).append(",");
        sb.append("gmt_modified=").append(new Date().toString());
        sb.append("where id=").append(ideaDO.getId());
        return DBAccess.updateData(sb.toString());
    }

    public boolean delete(IdeaDO ideaDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ideas where id=").append(ideaDO.getId());
        return DBAccess.updateData(sb.toString());
    }

    public List<IdeaDO> Query(String where, int page, int size, String order) {
        List<IdeaDO> list = new ArrayList<IdeaDO>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id,description,video,photo,clicks,owner,user_id, status,gmt_create,gmt_modified from ideas ");
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

                list.add(ideaDO);
            }
            rs.close();
            DBAccess.closeConnection();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

}
