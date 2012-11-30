package com.taobao.ideabox.dao.impl;

import com.taobao.ideabox.dao.BaseDAO;
import com.taobao.ideabox.dbAccess.DBAccess;
import com.taobao.ideabox.entity.impl.TagDO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: shufj
 * Date: 11/29/12 8:51 ÏÂÎç
 */
public class TagDAO implements BaseDAO<TagDO> {

    public boolean insert(TagDO tagDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into tags(name,description, type, status,gmt_create) values(");
        sb.append(tagDO.getName()).append(",");
        sb.append(tagDO.getDescription()).append(",");
        sb.append(tagDO.getType()).append(",");
        sb.append(tagDO.getStatus()).append(",");
        sb.append(new Date().toString()).append(")");
        return DBAccess.insertData(sb.toString());
    }

    public boolean update(TagDO tagDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("update tags set");
        sb.append("description=").append(tagDO.getDescription()).append(",");
        sb.append("name=").append(tagDO.getName()).append(",");
        sb.append("description=").append(tagDO.getDescription()).append(",");
        sb.append("type=").append(tagDO.getType()).append(",");
        sb.append("status=").append(tagDO.getStatus());
        sb.append("where id=").append(tagDO.getId());
        return DBAccess.updateData(sb.toString());
    }

    public boolean delete(TagDO tagDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from tags where id=").append(tagDO.getId());
        return DBAccess.updateData(sb.toString());
    }

    public List<TagDO> Query(String where, int page, int size, String order) {
        List<TagDO> list = new ArrayList<TagDO>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id,name,description, type, status,gmt_create from tags ");
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
                TagDO tagDO = new TagDO();
                tagDO.setId(rs.getInt("id"));
                tagDO.setDescription(rs.getString("description"));
                tagDO.setName(rs.getString("name"));
                tagDO.setType(rs.getInt("type"));
                tagDO.setStatus(rs.getInt("status"));
                tagDO.setGmtCreate(rs.getDate("gmt_create"));

                list.add(tagDO);
            }
            rs.close();
            DBAccess.closeConnection();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
