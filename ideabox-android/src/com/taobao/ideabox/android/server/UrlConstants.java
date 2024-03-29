package com.taobao.ideabox.android.server;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-4
 * Time: 上午9:58
 * 服务器的请求地址列表
 */
public interface UrlConstants {

    //IDEA全量查询
    public static final String QUERY_IDEAS = "/idea/query.html";

//    public final String URL_SERVER = "http://172.16.1.102:8080/";

    //上传文件接口
    public final String URL_POST_IMG = "/idea/upload.html";

    //上传idea基本信息
    public final String URL_POST_IDEA_ADD = "/idea/add.html";
}
