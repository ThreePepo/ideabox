package com.taobao.ideabox.entity;

import java.util.Date;

/**
 * ��������
 * User: shufj
 * Date: 11/28/12 8:05 ����
 */
public class BaseDO {

    private int id;
    private int status; //״̬

    private Date gmtCreate; //����ʱ��
    private Date gmtModified; //�޸�ʱ��

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
