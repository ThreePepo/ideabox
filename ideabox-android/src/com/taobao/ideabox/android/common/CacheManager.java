package com.taobao.ideabox.android.common;


import java.io.File;
import java.io.FileInputStream;
import com.taobao.ideabox.android.common.cache.CacheConstants;
/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-9
 * Time: ����1:51
 * ����ͼƬ����Ƶ�ı��ػ��������
 */
public class CacheManager {

    /**
     * ����ָ������,Ŀǰֻ�����ļ�����ʽ������ͨ��fileName+fileSize���ɻ���ID
     * @param cobj �������
     */
      public void save(File cobj){
          String fileName = cobj.getName();
          File dist = new File(CacheConstants.STORE_PATH+fileName);
          //������
          //todo: ����ɾ�����ڵĻ������ݣ�����ʹ��service or ������Ӧ�ò��洦��
          CacheStoreHelper.removeCache(CacheConstants.STORE_PATH);
          //��Ż���
          CacheStoreHelper.saveToCache(cobj,dist);
      }

    /**
     * ����ָ���ļ���
     * @param fis
     */
    public void save(FileInputStream fis){
       //todo
    }

    /**
     * ͨ���ļ�����ñ��ػ����е��ļ�
     * todo: ������ Ӧ��ͨ���ļ���+��С��ʽ��ʾKEY
     * @param fileName �ļ���
     * @return ���е��ļ�path
     */
    public String get(String fileName){
        File file = new File(CacheConstants.STORE_PATH+fileName);
        if(file.exists()){
            //��������޸�ʱ��
            CacheStoreHelper.updateFileTime(file);
            return file.getPath();
        }
        return null;
    }
}
