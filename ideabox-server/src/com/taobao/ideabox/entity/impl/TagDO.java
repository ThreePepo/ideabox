package com.taobao.ideabox.entity.impl;

import com.taobao.ideabox.entity.BaseDO;

/**
 * ��ǩ��
 * User: shufj
 * Date: 11/28/12 8:00 ����
 */
public class TagDO extends BaseDO {

    private String name; //��ǩ��
    private String description; //��ǩ����
    private int type; //����

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
