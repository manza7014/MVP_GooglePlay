package itcast.zz.googleplay.utils;

import android.os.Handler;

import itcast.zz.googleplay.manager.ThreadPoolManager;

/**
 * Created by wangx on 2016/7/29.
 * 1.在子线程执行 2.在主线程执行
 */
public class ThreadUtils {

    /**
     * 子线程执行
     * Runnable 线程任务类
     */
    public static  void runOnBackThread(Runnable runnable){
//        new Thread().start();
//        new Thread(runnable ).start();
        ThreadPoolManager.getIntance().createThreadPool().execute(runnable);
    }

    private static Handler handler = new Handler();

    /**
     * 在主线程中执行
     * @param runnable
     */
    public static void runOnUIThread(Runnable runnable){
        handler.post(runnable);
    }
}
