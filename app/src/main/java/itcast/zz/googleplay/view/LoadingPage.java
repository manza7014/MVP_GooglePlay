package itcast.zz.googleplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import itcast.zz.googleplay.R;
import itcast.zz.googleplay.utils.ThreadUtils;
import itcast.zz.googleplay.utils.UIUtils;

/**
 * Created by wangx on 2016/7/29.
 * 1将BaseFragment的代码拆分
 */
public abstract class LoadingPage extends FrameLayout {

    //五种状态
    public static final int STATE_UNKNOWN = 0;// 未知
    public static final int STATE_LOADING = 1;// 加载中
    public static final int STATE_ERROR = 2;// 加载失败
    public static final int STATE_EMPTY = 3;// 加载为空
    public static final int STATE_SUCCESS = 4;// 加载成功


    private int state = STATE_LOADING;//默认是加载中的状态

    private View loadingView;//加载中的view对象
    private View errorView;//加载失败view对象
    private View emptyView;//加载为空view对象
    private View successView;//加载成功view对象

    public LoadingPage(Context context) {
        super(context);
        init();//将几种不同的界面添加帧布局中
    }


    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();//将几种不同的界面添加帧布局中
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();//将几种不同的界面添加帧布局中
    }

    /**
     * 将几种不同的界面添加帧布局中
     */
    private void init() {
        if (loadingView == null) {
            loadingView = createLoadingView();
            this.addView(loadingView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));
        }

        if (errorView == null) {
            errorView = createErrorView();
            this.addView(errorView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));

        }
        if (emptyView == null) {
            emptyView = createEmptyView();
            this.addView(emptyView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));
        }

//        成功界面添加
        showPage(); //界面的切换
    }

    /**
     * 创建为空的view对象
     */
    private View createEmptyView() {
        // xml -- view
        return UIUtils.inflate(R.layout.page_empty);
    }

    /**
     * 创建加载失败的view对象
     */
    private View createErrorView() {
        // xml --- view
        View view = UIUtils.inflate(R.layout.page_error);
        view.findViewById(R.id.page_bt).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新请求网络数据
                show();
            }
        });
        return view;
    }

    /**
     * 创建加载中的界面
     */
    private View createLoadingView() {
        // xml --- view
        return UIUtils.inflate(R.layout.page_loading);
    }


    /**
     * 根据状态切换为不同的界面
     */
    private void showPage() {

        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_LOADING || state == STATE_UNKNOWN ? View.VISIBLE : View.INVISIBLE);
        }

        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }

        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }


        // 成功状态  创建成界面 添加到帧布局中
        if (state == STATE_SUCCESS) {
            if (successView == null) {
                successView = createSuccessView();
                this.addView(successView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));
            } else {
                successView.setVisibility(View.VISIBLE);
            }
        } else {
            if (successView != null) {
                successView.setVisibility(View.INVISIBLE);
            }
        }
    }


    /**
     * 根据服务器返回状态切换为不同的界面
     */
    public void show() {
        if (state == STATE_ERROR) {
            state = STATE_LOADING;
        }

        showPage();

        ThreadUtils.runOnBackThread(new Runnable() {
            @Override
            public void run() {
                state = load();
                ThreadUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        //根据状态切换为不同的界面
                        showPage();
                    }
                });

            }
        });


    }

    /**
     * 请求服务器数据 返回三种状态
     *
     * @return
     */
    protected abstract int load();

    /**
     * 创建成功界面 请求服务器完成后 success才会调用
     *
     * @return
     */
    protected abstract View createSuccessView();
}
