package com.taobao.ideabox.android.test;

import com.taobao.ideabox.android.server.ServerConnection;
import com.taobao.ideabox.android.server.UrlConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-5
 * Time: ионГ10:31
 */
public class ServerConnectionTest {
     public static void main(String[] args){
       String result =  ServerConnection.query(UrlConstants.QUERY_IDEAS);
        System.out.println(result);
     }

}
