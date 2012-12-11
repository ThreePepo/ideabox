package com.taobao.ideabox.android.common.cache;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-9
 * Time: ����2:09
 * �������
 */
public class CacheFile {
    private String fileName; //�����ļ���
    private String path; //�ļ�·��
    private File file; //�ļ�����
    private long expire; //ʧЧʱ��

    public CacheFile(URL file){

    }

    public CacheFile(){

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
