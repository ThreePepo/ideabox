package com.taobao.ideabox.entity.impl;

/**
 * 用户标签关系表
 * User: shufj
 * Date: 11/29/12 8:49 下午
 */
public class UserTagDO {

    private int userId;
    private int tagId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
