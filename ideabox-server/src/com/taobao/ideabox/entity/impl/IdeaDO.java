package com.taobao.ideabox.entity.impl;


import com.taobao.ideabox.entity.BaseDO;

/**
 * ���ӱ�
 * User: shufj
 * Date: 11/28/12 7:59 ����
 */
public class IdeaDO extends BaseDO {

    private String description; //����
    private String video; //¼��
    private String photo; //ͼƬ
    private int clicks; //������ȶ�
    private String owner; //������
    private int userId; //������Id
    private int status; //״̬ 0������ 1������ 2�������� 3�����


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
