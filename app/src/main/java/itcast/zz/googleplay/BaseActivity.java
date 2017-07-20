package itcast.zz.googleplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangx on 2016/7/28.
 */
public class BaseActivity extends AppCompatActivity {

    // 1. 共享资源
    private static List<BaseActivity> activities = new ArrayList<>();
    public static BaseActivity activity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        synchronized (activities) {
            activities.add(this);
        }

        init();//初始化
        initView();//view的初始化
        initToolBar();//toolbar的初始化
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (activities) {
            activities.remove(this);
        }
    }

    /**
     * 自杀
     */
    public void killAll() {
        //  2.  1.CopyOnWriteArrayList
        List<BaseActivity> copy;
        synchronized (activities) {
            copy = new ArrayList<>(activities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
        //退出当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 1. 初始化操作
     */
    protected void init() {
    }

    /**
     * 2. view的初始化
     */
    protected void initView() {

    }

    /**
     * 3. toolbar的初始化
     */
    protected void initToolBar() {
    }


}
