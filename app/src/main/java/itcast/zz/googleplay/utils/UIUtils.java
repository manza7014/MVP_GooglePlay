package itcast.zz.googleplay.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

import itcast.zz.googleplay.BaseActivity;
import itcast.zz.googleplay.BaseApp;

/**
 * Created by wangx on 2016/7/28.
 */
public class UIUtils {
    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */

    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 资源id 获取字符串数组
     *
     * @param id
     * @return
     */
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    /**
     * 获取resources对象
     *
     * @return
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取上下文对象
     *
     * @return
     */
    public static Context getContext() {
        return BaseApp.getInstance();
    }

    /**
     * 根据布局id 返回view对象
     *
     * @param id
     * @return
     */
    public static View inflate(int id) {
        // 自定义组合控件
        return View.inflate(getContext(), id, null);
    }

    /**
     * 根据资源id 返回drawable对象
     *
     * @param id
     * @return
     */
    public static Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }

    /**
     * 启动新的acitivity
     *
     * @param intent
     */
    public static void startActivity(Intent intent) {
        if (BaseActivity.activity != null) {
            BaseActivity.activity.startActivity(intent);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//指定任务栈
            getContext().startActivity(intent);
        }
    }
}
