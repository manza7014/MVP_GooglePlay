package itcast.zz.googleplay.utils;

import android.content.Context;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by wangx on 2016/7/30.
 */
public class ImageUtils {
    /**
     * 修改picaso 配置  缓存目录 是否是debug
     * @param context
     */
    public static void init(Context context){
        //修改picass缓存目录

        Picasso picasso = new Picasso.Builder(context)

                .downloader(new OkHttpDownloader(FileUtils.getIconCache()))
                .build();
        Picasso.setSingletonInstance(picasso);
    }
}
