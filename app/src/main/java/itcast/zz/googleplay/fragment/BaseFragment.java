package itcast.zz.googleplay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import itcast.zz.googleplay.utils.UIUtils;
import itcast.zz.googleplay.utils.ViewUtils;
import itcast.zz.googleplay.view.LoadingPage;

/**
 * Created by wangx on 2016/7/29.
 * 代码拆分 1.跟 界相关的代码 放在一个帧布局中
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // loadingPage 与父view 断绝关系
        ViewUtils.removeFromParent(loadingPage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 上下文 拿最长生命周期上下文
        // loadingPage  有一个父view   一个view 只能有一个父view
        if (loadingPage == null) {
            loadingPage = new LoadingPage(UIUtils.getContext()) {
                @Override
                protected int load() {
                    return BaseFragment.this.load();// 交给LoadingPage 加载网络数据实现不了 baseframgent去实现
                }

                @Override
                protected View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }
            };

        }
//        show(); //请求服务器数据 根据服务器返回状态切换为不同的界面


        return loadingPage;
    }

    /**
     * 请求服务器数据 根据服务器返回的状态切换为不同的界面
     */
    public void show() {
        if (loadingPage != null)
            loadingPage.show();
    }
    /**
     * 根据数据判断请求结果
     *
     * @param datas
     * @return
     */
    public int checkDatas(List<? extends Object> datas) {
        if (datas == null) {
            return LoadingPage.STATE_ERROR;
        } else {
            if (datas.size() == 0) {
                return LoadingPage.STATE_EMPTY;
            } else {
                return LoadingPage.STATE_SUCCESS;
            }
        }
    }

    /**
     * public static final int STATE_ERROR = 2;// 加载失败
     * public static final int STATE_EMPTY = 3;// 加载为空
     * public static final int STATE_SUCCESS = 4;// 加载成功
     * 1.请求服务器数据  ,返回几种状态
     *
     * @return
     */
    protected abstract int load();

    /**
     * 创建成功界面 返回状态为 成功的时候 才会调用 createSuccessView
     *
     * @return
     */
    protected abstract View createSuccessView();

}
