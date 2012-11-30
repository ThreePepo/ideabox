package com.taobao.ideabox.entity.impl;

import com.taobao.ideabox.entity.BaseDO;

/**
 * 标签表
 * User: shufj
 * Date: 11/28/12 8:00 下午
 */
public class TagDO extends BaseDO {

    private String name; //标签名
    private String description; //标签描述
    private int type; //类型

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
