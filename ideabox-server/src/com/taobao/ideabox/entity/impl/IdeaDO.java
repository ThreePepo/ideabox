package com.taobao.ideabox.entity.impl;


import com.taobao.ideabox.entity.BaseDO;

/**
 * 点子表
 * User: shufj
 * Date: 11/28/12 7:59 下午
 */
public class IdeaDO extends BaseDO {

    private String description; //描述
    private String video; //录音
    private String photo; //图片
    private int clicks; //点击量热度
    private String owner; //创建人
    private int userId; //创建人Id
    private int status; //状态 0：保存 1：分享 2：建团中 3：完成


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
