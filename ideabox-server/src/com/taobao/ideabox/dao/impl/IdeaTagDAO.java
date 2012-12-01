package com.taobao.ideabox.dao.impl;

import com.taobao.ideabox.dao.BaseDAO;
import com.taobao.ideabox.dbAccess.DBAccess;
import com.taobao.ideabox.entity.impl.IdeaTagDO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * User: shufj
 * Date: 11/29/12 8:51 ÏÂÎç
 */
public class IdeaTagDAO extends BaseDAO{

    public IdeaTagDAO(){
        super("","");
    }

    public boolean insert(IdeaTagDO ideaTagDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into idea_tag(idea_id, tag_id) values(");
        sb.append(ideaTagDO.getIdeaId()).append(",");
        sb.append(ideaTagDO.getTagId()).append(")");
        return DBAccess.insertData(sb.toString());
    }

    public boolean update(IdeaTagDO ideaTagDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("update idea_tag set");
        sb.append("idea_id=").append(ideaTagDO.getIdeaId()).append(",");
        sb.append("tag_id=").append(ideaTagDO.getTagId());
        return DBAccess.updateData(sb.toString());
    }

    public boolean delete(IdeaTagDO ideaTagDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from idea_tag where idea_id=").append(ideaTagDO.getIdeaId());
        return DBAccess.updateData(sb.toString());
    }

    public List<IdeaTagDO> Query(String where, int page, int size, String order) {
        List<IdeaTagDO> list = new ArrayList<IdeaTagDO>();
        StringBuilder sb = new StringBuilder();
        sb.append("select idea_id, tag_id from idea_tag ");
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
                IdeaTagDO ideaTagDO = new IdeaTagDO();
                ideaTagDO.setIdeaId(rs.getInt("idea_id"));
                ideaTagDO.setTagId(rs.getInt("tag_id"));
                list.add(ideaTagDO);
            }
            rs.close();
            DBAccess.closeConnection();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
