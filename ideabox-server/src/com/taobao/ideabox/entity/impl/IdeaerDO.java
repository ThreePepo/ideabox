package com.taobao.ideabox.entity.impl;

import com.taobao.ideabox.entity.BaseDO;

/**
 * 用户关系表
 * User: shufj
 * Date: 11/29/12 8:46 下午
 */
public class IdeaerDO extends BaseDO {

    private int creatorId; //提出人
    private int ideaId; //点子
    private int parterId; //搭档
    private String description; //描述

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public int getParterId() {
        return parterId;
    }

    public void setParterId(int parterId) {
        this.parterId = parterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
