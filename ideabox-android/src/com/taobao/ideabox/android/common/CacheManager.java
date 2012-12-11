package com.taobao.ideabox.android.common;


import java.io.File;
import java.io.FileInputStream;
import com.taobao.ideabox.android.common.cache.CacheConstants;
/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-9
 * Time: 下午1:51
 * 管理图片和音频的本地缓存服务类
 */
public class CacheManager {

    /**
     * 缓存指定对象,目前只考虑文件名方式，后续通过fileName+fileSize生成缓存ID
     * @param cobj 缓存对象
     */
      public void save(File cobj){
          String fileName = cobj.getName();
          File dist = new File(CacheConstants.STORE_PATH+fileName);
          //清理缓存
          //todo: 还有删除到期的缓存内容，后续使用service or 上升至应用层面处理
          CacheStoreHelper.removeCache(CacheConstants.STORE_PATH);
          //存放缓存
          CacheStoreHelper.saveToCache(cobj,dist);
      }

    /**
     * 缓存指定文件流
     * @param fis
     */
    public void save(FileInputStream fis){
       //todo
    }

    /**
     * 通过文件名获得本地缓存中的文件
     * todo: 不合理， 应该通过文件名+大小方式表示KEY
     * @param fileName 文件名
     * @return 命中的文件path
     */
    public String get(String fileName){
        File file = new File(CacheConstants.STORE_PATH+fileName);
        if(file.exists()){
            //更新最近修改时间
            CacheStoreHelper.updateFileTime(file);
            return file.getPath();
        }
        return null;
    }
}
