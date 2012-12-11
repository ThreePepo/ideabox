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
 * Time: ����10:25
 * SD�������ļ��洢����
 */
public class CacheStoreHelper {
    private static final int FREE_SD_SPACE_NEEDED_TO_CACHE=50; //����50M�Ž��л���
    public static final String WHOLESALE_CONV="";
    public static final int CACHE_SIZE=200; //����������޿ռ�
    public static final int MB=1024*1024;
    public static final String TAG="sd_helper";
    private static long mTimeDiff = 7*24*60*60; //����7��δ�޸ĵ���ΪʧЧ

    /**
     * ����ͼƬ��ָ��λ��
     * @param source �������ļ�λ��
     * @param dist ����λ��
     */
    public static  void saveToCache(File source, File dist) {

        if (source == null || !source.exists()) {
            Log.w("","trying to savenull bitmap");
            return;
        }
        //�ж�Դ�ļ��Ƿ��ڻ���Ŀ¼�д���
        if(containCache(source.getName())){
            return;
        }
        //�ж�sdcard�ϵĿռ�
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
     * ����ļ����ڵ�Ŀ¼
     * @param fileName
     * @return
     */
    private static String getDirectory(String fileName){
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

    /**
     * ��URLת�����ļ�����ʽ
     * @param url
     * @return
     */
    private static String convertUrlToFileName(String url){
        return null;
    }
    /**
     * ����sdcard�ϵ�ʣ��ռ�
     * @return
     */
    public static  int freeSpaceOnSd() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory() .getPath());
        double sdFreeMB = ((double)stat.getAvailableBlocks() * (double) stat.getBlockSize()) / MB;
        return (int) sdFreeMB;
    }

    /**
     * �޸��ļ�������޸�ʱ��
     * @param dir
     * @param fileName
     */
    public static  void updateFileTime(File file) {
        long newModifiedTime =System.currentTimeMillis();
        file.setLastModified(newModifiedTime);
    }

    /**
     *����洢Ŀ¼�µ��ļ���С�����ļ��ܴ�С���ڹ涨��CACHE_SIZE����sdcardʣ��ռ�С��FREE_SD_SPACE_NEEDED_TO_CACHE�Ĺ涨
     * ��ôɾ��40%���û�б�ʹ�õ��ļ�
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
     * ɾ�������ļ�
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
     * TODO �����ļ�������޸�ʱ��������� *
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
