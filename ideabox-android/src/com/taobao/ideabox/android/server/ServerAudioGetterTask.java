package com.taobao.ideabox.android.server;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.provider.MediaStore;
import android.util.Log;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import java.io.*;
import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-4
 * Time: 下午2:13
 * 服务器端音频获取， 将在Activity中调用
 */
public class ServerAudioGetterTask {
    private static final int IO_BUFFER_SIZE= 4 * 1024;
    private String url;
    private final WeakReference<MediaStore.Audio> audioReference;

    public ServerAudioGetterTask(MediaStore.Audio audio) {
        audioReference = new WeakReference< MediaStore.Audio >(audio);
    }

    protected Bitmap doInBackground(String... params) {
        final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
        url = params[0];
        final HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse response =client.execute(getRequest);
            final int statusCode =response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                Log.w("", "从" + url + "中下载图片时出错!,错误码:" + statusCode);
                return null;
            }
            final HttpEntity entity =response.getEntity();
            if (entity != null) {
                InputStream inputStream =null;
                OutputStream outputStream =null;
                try {
                    inputStream =entity.getContent();
                    final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
                    outputStream = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
                    IOUtils.copy(inputStream, outputStream);
                    outputStream.flush();
                    final byte[] data =dataStream.toByteArray();
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    return bitmap;
                } finally {
                    if (inputStream !=null) {
                        inputStream.close();
                    }
                    if (outputStream !=null) {
                        outputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (IOException e) {
            getRequest.abort();
            Log.w( "I/O errorwhile retrieving bitmap from " + url, e);
        } catch (IllegalStateException e) {
            getRequest.abort();
            Log.w("Incorrect URL:" + url,e);
        } catch (Exception e) {
            getRequest.abort();
            Log.w("Error whileretrieving bitmap from " + url, e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return null;
    }
}
