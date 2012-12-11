package com.taobao.ideabox.android.server;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
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
 * Time: 上午10:23
 * 在Activity中调用，如果缓存中得不到相应的图片则通过本类从服务器端获取图片。
 */
public class ServerPictureGetterTask extends AsyncTask<String, Void, Bitmap> {
    private static final int IO_BUFFER_SIZE= 4 * 1024;
    private String url;
    private final WeakReference<ImageView> imageViewReference;

    public ServerPictureGetterTask(ImageView imageView) {
        imageViewReference = new WeakReference< ImageView >(imageView);
    }

    protected Bitmap doInBackground(String... params) {
        final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
        url = params[0];
        final HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse response =client.execute(getRequest);
            final int statusCode =response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                Log.w("","从" + url + "中下载图片时出错!,错误码:" + statusCode);
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
