package com.taobao.ideabox.entity.impl;

import com.taobao.ideabox.entity.BaseDO;

/**
 * �û���
 * User: shufj
 * Date: 11/28/12 8:00 ����
 */
public class UserDO extends BaseDO {

    private String nick; //�ǳ�
    private String mainTag; //����ǩ
    private String moreTags; //�����ǩ

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
