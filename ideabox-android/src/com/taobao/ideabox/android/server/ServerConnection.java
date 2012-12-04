package com.taobao.ideabox.android.server;


import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-4
 * Time: 上午9:25
 * 数据格式：JSON, 提供获取服务器数据的接口
 */
public class ServerConnection {
    static HttpClient httpClient = new DefaultHttpClient();

    /**
     * 从指定URL查询数据
     * @return
     */
     public static String query(String url){
         // HttpGet对象
         HttpGet httpRequest = new HttpGet(url);
         String strResult = "";
         try {
             // 获得HttpResponse对象
             HttpResponse httpResponse = httpClient.execute(httpRequest);
             if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                 // 取得返回的数据,需要有业务端重新封装
                 strResult = EntityUtils.toString(httpResponse.getEntity());
             }
         } catch (ClientProtocolException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return strResult;
     }

    /**
     * 通过字节流的方式提交服务器请求
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendGet(String url) throws ClientProtocolException, IOException{
        String result = null;
        HttpGet get = new HttpGet(url);
        InputStream in = null;
        try {
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                entity = new BufferedHttpEntity(entity);
                in = entity.getContent();
                byte[] read = new byte[1024];
                byte[] all = new byte[0];
                int num;
                while ((num = in.read(read)) > 0) {
                    byte[] temp = new byte[all.length + num];
                    System.arraycopy(all, 0, temp, 0, all.length);
                    System.arraycopy(read, 0, temp, all.length, num);
                    all = temp;
                }
                result = new String(all, "UTF-8");
            }
        } finally {
            if (in != null) in.close();
            get.abort();
        }
        return result;
    }

    /**
     * 以POST方式提交表单请求
     * @param url 提交的URL
     * @param params 参数
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPost(String url, Map<String, String> params) throws ClientProtocolException, IOException{
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost get = new HttpPost(url);

        // 创建表单参数列表
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            qparams.add(new BasicNameValuePair(key, params.get(key)));
        }

        // 填充表单
        get.setEntity(new UrlEncodedFormEntity(qparams,"UTF-8"));

        HttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            entity = new BufferedHttpEntity(entity);

            InputStream in = entity.getContent();
            byte[] read = new byte[1024];
            byte[] all = new byte[0];
            int num;
            while ((num = in.read(read)) > 0) {
                byte[] temp = new byte[all.length + num];
                System.arraycopy(all, 0, temp, 0, all.length);
                System.arraycopy(read, 0, temp, all.length, num);
                all = temp;
            }
            result = new String(all,"UTF-8");
            if (null != in) {
                in.close();
            }
        }
        get.abort();

        return result;
    }

    /**
     * 带参数的GET请求
     * @param url
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendGet(String url, Map<String, String> params) throws ClientProtocolException, IOException {
        Set<String> keys = params.keySet();
        StringBuilder urlBuilder = new StringBuilder(url + "?");
        for (String key : keys) {
            urlBuilder.append(key).append("=").append(params.get(key)).append("&");
        }
        urlBuilder.delete(urlBuilder.length() - 1, urlBuilder.length());
        return sendGet(urlBuilder.toString());
    }

    /* 上传文件至Server的方法 */
    public static void uploadFile(String actionUrl, String uploadFile)
    {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        try
        {
            URL url =new URL(actionUrl);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            /* 设置传送的method=POST */
            con.setRequestMethod("POST");
            /* setRequestProperty */
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary="+boundary);
            /* 设置DataOutputStream */
            DataOutputStream ds =
                    new DataOutputStream(con.getOutputStream());
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " +
                    "name=\"file1\";filename=\"" +
                    uploadFile +"\"" + end);
            ds.writeBytes(end);

            /* 取得文件的FileInputStream */
            FileInputStream fStream = new FileInputStream(uploadFile);
            /* 设置每次写入1024bytes */
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];

            int length = -1;
            /* 从文件读取数据至缓冲区 */
            while((length = fStream.read(buffer)) != -1)
            {
                /* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

            /* close streams */
            fStream.close();
            ds.flush();

            /* 取得Response内容 */
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b =new StringBuffer();
            while( ( ch = is.read() ) != -1 )
            {
                b.append( (char)ch );
            }
            /* 将Response显示于Dialog */
            Log.w("上传成功" , b.toString().trim());
            /* 关闭DataOutputStream */
            ds.close();
        }
        catch(Exception e)
        {
            Log.e("上传失败",e.getMessage());
        }
    }

}
