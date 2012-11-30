package com.taobao.ideabox.entity.impl;

import com.taobao.ideabox.entity.BaseDO;

/**
 * 用户表
 * User: shufj
 * Date: 11/28/12 8:00 下午
 */
public class UserDO extends BaseDO {

    private String nick; //昵称
    private String mainTag; //主标签
    private String moreTags; //更多标签

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMainTag() {
        return mainTag;
    }

    public void setMainTag(String mainTag) {
        this.mainTag = mainTag;
    }

    public String getMoreTags() {
        return moreTags;
    }

    public void setMoreTags(String moreTags) {
        this.moreTags = moreTags;
    }
}
