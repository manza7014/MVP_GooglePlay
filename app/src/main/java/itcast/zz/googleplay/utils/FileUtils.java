package itcast.zz.googleplay.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by wangx on 2016/7/29.
 * 管理所有的缓存目  数据的缓存 图片的缓存
 */
public class FileUtils {
    public static final String ROOT = "googleplayz15";//根目录
    public static final String CACHE = "cache";//数据的缓存目
    public static final String ICON = "icon";

    /**
     * 根据 文件夹名称返回 file对象
     *
     * @param dir
     * @return
     */
    public static File getDir(String dir) {
        // 拿到完整的目录 据对路径
        //  /mnt/sdcard/googleplayz15/cache
        StringBuilder stringBuilder = new StringBuilder();
        if (isSDCardAvailable()) {
            stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());  //  /mnt/sdcard
            stringBuilder.append(File.separator); //    /mnt/sdcard/
            stringBuilder.append(ROOT);//    /mnt/sdcard/googleplayz15
            stringBuilder.append(File.separator);  //    /mnt/sdcard/googleplayz15/
            stringBuilder.append(dir);  //   /mnt/sdcard/googleplayz15/cache
        }else{
            //   /data/data/包名/cache/icon
            stringBuilder.append(UIUtils.getContext().getCacheDir().getAbsolutePath()); //   /data/data/包名/cache
            stringBuilder.append(File.separator);  //   /data/data/包名/cache/
            stringBuilder.append(dir);
        }

        File file = new File(stringBuilder.toString());
        if (!file.exists() && !file.isDirectory()) {
            //创建的文件夹
//            file.createNewFile()//创建文件
            file.mkdirs();
        }

        return file;
    }

    /**
     * sd卡是否存在
     * @return
     */
    private static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取数据的缓存目录
     *
     * @return
     */
    public static File getCache() {
        return getDir(CACHE);
    }

    /**
     * 获取图片的缓存目录
     * @return
     */
    public static  File getIconCache(){
        return getDir(ICON);
    }
}
