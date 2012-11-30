package com.taobao.ideabox.entity.impl;

/**
 * 点子标签关系表
 * User: shufj
 * Date: 11/29/12 8:44 下午
 */
public class IdeaTagDO {

    private int ideaId;
    private int tagId;

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
