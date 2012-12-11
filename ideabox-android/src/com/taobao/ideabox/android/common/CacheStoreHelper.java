package com.taobao.ideabox.android.common;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import com.taobao.ideabox.android.common.cache.CacheConstants;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-4
 * Time: 上午10:25
 * SD卡缓存文件存储管理
 */
public class CacheStoreHelper {
    private static final int FREE_SD_SPACE_NEEDED_TO_CACHE=50; //至少50M才进行缓存
    public static final String WHOLESALE_CONV="";
    public static final int CACHE_SIZE=200; //缓存最大上限空间
    public static final int MB=1024*1024;
    public static final String TAG="sd_helper";
    private static long mTimeDiff = 7*24*60*60; //超过7天未修改的视为失效

    /**
     * 保存图片至指定位置
     * @param source 待缓存文件位置
     * @param dist 缓存位置
     */
    public static  void saveToCache(File source, File dist) {

        if (source == null || !source.exists()) {
            Log.w("","trying to savenull bitmap");
            return;
        }
        //判断源文件是否在缓存目录中存在
        if(containCache(source.getName())){
            return;
        }
        //判断sdcard上的空间
        if (FREE_SD_SPACE_NEEDED_TO_CACHE >freeSpaceOnSd()) {
            Log.w(TAG, "Low free space onsd, do not cache");
            return;
        }
        try {
           FileInputStream fis = new FileInputStream(source);
           FileOutputStream fos = new FileOutputStream(dist);
            IOUtils.copy(fis,fos);
            Log.i(TAG, "Image saved tosd");
        } catch (FileNotFoundException e) {
            Log.w(TAG,"FileNotFoundException");
        } catch (IOException e) {
            Log.w(TAG,"IOException");
        }
    }

    public static boolean containCache(String fileName){
        File file = new File(CacheConstants.STORE_PATH+fileName);
        return file.exists();
    }

    /**
     * 获得文件所在的目录
     * @param fileName
     * @return
     */
    private static String getDirectory(String fileName){
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

    /**
     * 将URL转换成文件名格式
     * @param url
     * @return
     */
    private static String convertUrlToFileName(String url){
        return null;
    }
    /**
     * 计算sdcard上的剩余空间
     * @return
     */
    public static  int freeSpaceOnSd() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory() .getPath());
        double sdFreeMB = ((double)stat.getAvailableBlocks() * (double) stat.getBlockSize()) / MB;
        return (int) sdFreeMB;
    }

    /**
     * 修改文件的最后修改时间
     * @param dir
     * @param fileName
     */
    public static  void updateFileTime(File file) {
        long newModifiedTime =System.currentTimeMillis();
        file.setLastModified(newModifiedTime);
    }

    /**
     *计算存储目录下的文件大小，当文件总大小大于规定的CACHE_SIZE或者sdcard剩余空间小于FREE_SD_SPACE_NEEDED_TO_CACHE的规定
     * 那么删除40%最近没有被使用的文件
     * @param dirPath
     */
    public static  void removeCache(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        int dirSize = 0;
        for (int i = 0; i < files.length;i++) {
            if(files[i].getName().contains(WHOLESALE_CONV)) {
                dirSize += files[i].length();
            }
        }
        if (dirSize > CACHE_SIZE * MB ||FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
            int removeFactor = (int) ((0.4 *files.length) + 1);

            Arrays.sort(files, new FileLastModifSort());

            Log.i(TAG, "Clear some expiredcache files ");

            for (int i = 0; i <removeFactor; i++) {

                if(files[i].getName().contains(WHOLESALE_CONV)) {

                    files[i].delete();

                }

            }

        }

    }
    /**
     * 删除过期文件
     * @param dirPath
     * @param filename
     */
    public static  void removeExpiredCache(String dirPath, String filename) {

        File file = new File(dirPath,filename);

        if (System.currentTimeMillis() -file.lastModified() > mTimeDiff) {

            Log.i(TAG, "Clear some expiredcache files ");

            file.delete();

        }

    }

    /**
     * TODO 根据文件的最后修改时间进行排序 *
     */
   static class FileLastModifSort implements Comparator<File> {
        public int compare(File arg0, File arg1) {
            if (arg0.lastModified() >arg1.lastModified()) {
                return 1;
            } else if (arg0.lastModified() ==arg1.lastModified()) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
