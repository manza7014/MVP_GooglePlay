package itcast.zz.googleplay;

import android.app.Application;
import android.content.Context;

import itcast.zz.googleplay.utils.ImageUtils;

/**
 * Created by wangx on 2016/7/28.
 */
public class BaseApp extends Application {
    private static BaseApp instance ;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;// 将当前application对象赋值

        ImageUtils.init(this);
    }


    /**
     * 返回上下文对象
     * @return
     */
    public static Context getInstance() {
        return instance;
    }
}
