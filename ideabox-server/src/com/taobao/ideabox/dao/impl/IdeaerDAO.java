package com.taobao.ideabox.dao.impl;

import com.taobao.ideabox.dao.BaseDAO;
import com.taobao.ideabox.dbAccess.DBAccess;
import com.taobao.ideabox.entity.impl.IdeaerDO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: shufj
 * Date: 11/29/12 8:51 ÏÂÎç
 */
public class IdeaerDAO implements BaseDAO<IdeaerDO> {

    public boolean insert(IdeaerDO ideaerDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ideaers (creator_id,idea_id, parter_id, description, status,gmt_create) values(");
        sb.append(ideaerDO.getCreatorId()).append(",");
        sb.append(ideaerDO.getIdeaId()).append(",");
        sb.append(ideaerDO.getParterId()).append(",");
        sb.append(ideaerDO.getDescription()).append(",");
        sb.append(ideaerDO.getStatus()).append(",");
        sb.append(new Date().toString()).append(")");
        return DBAccess.insertData(sb.toString());
    }

    public boolean update(IdeaerDO ideaerDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("update ideaers set");
        sb.append("creator_id=").append(ideaerDO.getCreatorId()).append(",");
        sb.append("idea_id=").append(ideaerDO.getIdeaId()).append(",");
        sb.append("parter_id=").append(ideaerDO.getParterId()).append(",");
        sb.append("description=").append(ideaerDO.getDescription()).append(",");
        sb.append("status=").append(ideaerDO.getStatus());
        sb.append("where id=").append(ideaerDO.getId());
        return DBAccess.updateData(sb.toString());
    }

    public boolean delete(IdeaerDO ideaerDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ideaers where id=").append(ideaerDO.getId());
        return DBAccess.updateData(sb.toString());
    }

    public List<IdeaerDO> Query(String where, int page, int size, String order) {
        List<IdeaerDO> list = new ArrayList<IdeaerDO>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id,creator_id,idea_id, parter_id, description, status,gmt_create from ideaers ");
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
                IdeaerDO ideaerDO = new IdeaerDO();
                ideaerDO.setId(rs.getInt("id"));
                ideaerDO.setDescription(rs.getString("description"));
                ideaerDO.setCreatorId(rs.getInt("creator_id"));
                ideaerDO.setIdeaId(rs.getInt("idea_id"));
                ideaerDO.setParterId(rs.getInt("parter_id"));
                ideaerDO.setStatus(rs.getInt("status"));
                ideaerDO.setGmtCreate(rs.getDate("gmt_create"));

                list.add(ideaerDO);
            }
            rs.close();
            DBAccess.closeConnection();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
