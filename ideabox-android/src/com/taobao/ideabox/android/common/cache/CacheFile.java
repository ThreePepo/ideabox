package com.taobao.ideabox.android.common.cache;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-9
 * Time: 下午2:09
 * 缓存对象
 */
public class CacheFile {
    private String fileName; //等于文件名
    private String path; //文件路径
    private File file; //文件引用
    private long expire; //失效时间

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
